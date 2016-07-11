package com.bw.application.action;

import com.bw.application.manager.IBattleManager;
import com.bw.application.message.UploadBattleResult.UploadBattleResultRequest;
import com.bw.application.message.UploadBattleResult.UploadBattleResultResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class UploadBattleResultAction implements Action {

    public IBattleManager battleManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        UploadBattleResultRequest request = UploadBattleResultRequest.parseFrom(paramRequest.getMessage());
        UploadBattleResultResponse.Builder builder = UploadBattleResultResponse.newBuilder();
        try {
            int result = battleManagerImpl.saveBattleResult(request);
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
