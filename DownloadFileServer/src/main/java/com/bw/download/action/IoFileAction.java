package com.bw.download.action;

import org.apache.log4j.Logger;

import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.download.manage.file.FileManager;
import com.bw.download.message.IoFile.IoFileRequest;
import com.bw.download.message.IoFile.IoFileResponse;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

/**
 * @author denny zhao 下载文件
 *
 */
public class IoFileAction implements Action {

    private Logger logger = Logger.getLogger(getClass());
    private FileManager fileManager;

    public String execute(Request request, Response response) throws Exception {
        IoFileRequest reqMsg = IoFileRequest.parseFrom(request.getMessage());
        IoFileResponse.Builder builder = IoFileResponse.newBuilder();
        try {
            int result = fileManager.downloadFile(reqMsg, builder);
            builder.setResult(result);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                builder.setResult(ErrorCodeInterface.ERROR);
                builder.setInfo("");
            }
            logger.error("IoFileAction", e);
        } finally {
            response.write(builder.build());
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
