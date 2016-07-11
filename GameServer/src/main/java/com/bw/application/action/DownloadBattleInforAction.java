package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.CancleBattle.CancleBattleRequest;
import com.bw.application.message.CancleBattle.CancleBattleResponse;
import com.bw.application.message.DownloadBattleInfor.DownloadBattleInforRequest;
import com.bw.application.message.DownloadBattleInfor.DownloadBattleInforResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class DownloadBattleInforAction implements Action {

    private static Logger logger = Logger.getLogger(DownloadBattleInforAction.class);
    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        DownloadBattleInforRequest request = DownloadBattleInforRequest.parseFrom(paramRequest.getMessage());
        DownloadBattleInforResponse.Builder builder = DownloadBattleInforResponse.newBuilder();
        try {
            String mailAddress = request.getMailAddress();
            int countFlag = 0;
            countFlag = Integer.parseInt(request.getBattleCouont());
            battleManagerImpl.downloadBattleInforList(mailAddress, builder, countFlag);
            builder.setResult(0);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            logger.error("开始战斗产生异常", e);
        } finally {

            paramResponse.write(builder.build());
        }
        return null;
    }

    public IBattleManager getBattleManagerImpl() {
        return battleManagerImpl;
    }

    public void setBattleManagerImpl(IBattleManager battleManagerImpl) {
        this.battleManagerImpl = battleManagerImpl;
    }

}
