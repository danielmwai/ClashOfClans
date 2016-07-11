package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.CancleBattle.CancleBattleRequest;
import com.bw.application.message.CancleBattle.CancleBattleResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class CancleBattleAction implements Action {

    private static Logger logger = Logger.getLogger(CancleBattleAction.class);
    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        CancleBattleRequest request = CancleBattleRequest.parseFrom(paramRequest.getMessage());
        CancleBattleResponse.Builder builder = CancleBattleResponse.newBuilder();
        try {
            int result = battleManagerImpl.cancleBattle(request);
            builder.setResult(result);
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
