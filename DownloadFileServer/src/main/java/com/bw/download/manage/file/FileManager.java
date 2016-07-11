package com.bw.download.manage.file;

import java.util.ArrayList;
import java.util.List;

import com.bw.download.exception.ManagerServerException;
import com.bw.download.message.IoFile.IoFileRequest;
import com.bw.download.message.IoFile.IoFileResponse;
import com.bw.download.message.IoFile.IoFileResponse.IoData;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowRequest;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowResponse;

/**
 * @author zhaoqingyou
 *
 */
public interface FileManager {

    /**
     * @param b
     * @param mailAddress
     * @throws ManagerServerException 拼接临时文件
     */
    void saveTempUploadFile(byte[] b, String mailAddress) throws ManagerServerException;

    void clearTempUploadFile(String mailAddress) throws ManagerServerException;

    /**
     * @param request
     * @param builder
     * @throws ManagerServerException 上传文件
     */
    public void uploadFile(UploadBattleFlowRequest request, UploadBattleFlowResponse.Builder builder) throws ManagerServerException;

    /**
     * @param request
     * @param builder
     * @return
     * @throws ManagerServerException 下载文件
     */
    public int downloadFile(IoFileRequest request, IoFileResponse.Builder builder) throws ManagerServerException;
}
