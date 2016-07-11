package com.bw.download.action;

import com.bw.download.manage.file.FileManager;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowRequest;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class UploadBattleFlowAction implements Action {

    private FileManager fileManager;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        UploadBattleFlowRequest request = UploadBattleFlowRequest.parseFrom(paramRequest.getMessage());
        UploadBattleFlowResponse.Builder builder = UploadBattleFlowResponse.newBuilder();
        try {
            fileManager.uploadFile(request, builder);
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
        } finally {
            paramResponse.write(builder.build());
        }
        return null;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

}
