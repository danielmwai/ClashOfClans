package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.UploadBattleResultPVE.UploadBattleResultPVERequest;
import com.bw.application.message.UploadBattleResultPVE.UploadBattleResultPVEResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao pve战斗结果
 */
public class UploadBattleResultPVEAction implements Action {

    private static Logger logger = Logger.getLogger(UploadBattleResultPVEAction.class);
    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        UploadBattleResultPVERequest request = UploadBattleResultPVERequest.parseFrom(paramRequest.getMessage());
        UploadBattleResultPVEResponse.Builder builder = UploadBattleResultPVEResponse.newBuilder();
        try {
            int result = battleManagerImpl.uploadBattleResultPVE(request);
            builder.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
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
