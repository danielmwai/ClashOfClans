package com.bw.application.action;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.UploadBattleFlow.UploadBattleFlowRequest;
import com.bw.application.message.UploadBattleFlow.UploadBattleFlowResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao 上传战斗过程
 *
 */
public class UploadBattleFlowAction implements Action {

    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        UploadBattleFlowRequest request = UploadBattleFlowRequest.parseFrom(paramRequest.getMessage());
        UploadBattleFlowResponse.Builder builder = UploadBattleFlowResponse.newBuilder();
        try {

            builder.setResult(0);
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
