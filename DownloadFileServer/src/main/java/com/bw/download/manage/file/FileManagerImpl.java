package com.bw.download.manage.file;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.dao.CommonDAO;
import com.bw.download.exception.ManagerServerException;
import com.bw.download.message.IoFile.IoFileRequest;
import com.bw.download.message.IoFile.IoFileResponse.IoData;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowRequest;
import com.bw.download.message.UploadBattleFlow.UploadBattleFlowResponse.Builder;
import com.commonSocket.net.Service;
import com.google.protobuf.ByteString;

/**
 * @author zhaoqingyou
 *
 */
public class FileManagerImpl implements FileManager {

    private Logger logger = Logger.getLogger(FileManagerImpl.class);

    /**
     * 分解后的文件数据
     */
    private ConcurrentHashMap<String, ArrayList<IoData>> fileMap = new ConcurrentHashMap<String, ArrayList<IoData>>();

    private ConcurrentHashMap<String, byte[]> tempMap = new ConcurrentHashMap<String, byte[]>();

    private int limit;

    private CommonDAO commonCacheDAOImpl;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void saveTempUploadFile(byte[] b, String mailAddress)
            throws ManagerServerException {

        byte[] cs = tempMap.get(mailAddress);

        if (cs == null) {

            System.out.println("tempMap is null ------ first put");

            tempMap.put(mailAddress, b);
        } else {

            int length = cs.length;
            System.out.println("tempMap not null ------ tempMap length【"
                    + length + "】");
            int length2 = b.length;
            System.out
                    .println("b is not null ------ b length【" + length2 + "】");
            byte[] temp = new byte[length + length2];

            System.arraycopy(cs, 0, temp, 0, length);
            System.arraycopy(b, 0, temp, length, length2);

            tempMap.put(mailAddress, temp);
        }
    }

    @Override
    public void clearTempUploadFile(String mailAddress)
            throws ManagerServerException {

        tempMap.remove(mailAddress);

    }

    @Override
    public void uploadFile(UploadBattleFlowRequest request, Builder builder)
            throws ManagerServerException {
        String defenceMailAddress = request.getDefenceMailAddress();
        String attackMailAddress = request.getAttackMailAddress();
        int battleId = request.getBattleId();
        int index = request.getIndex();
        int maxIndex = request.getMaxIndex();
        UploadBattleFlowRequest.IoData iodata = request.getIoData();
        List<ByteString> dataList = iodata.getDataList();
        byte[] byteArray = dataList.get(0).toByteArray();
        System.out.println("length:" + byteArray.length);
        String key = defenceMailAddress + "_" + attackMailAddress + "_"
                + battleId;
        saveTempUploadFile(byteArray, key);
        if (index == maxIndex - 1) {
            commonCacheDAOImpl
                    .saveFileToCache(key, tempMap.get(key));
//			byte[] fileContent = commonCacheDAOImpl.getFileFromCache(key);
            //存入文件
//			byte[] fileContentMap=tempMap.get(key);
//			String temp=fileContentMap.toString();
//			System.out.println(temp.getBytes());
//			this.saveToFile(fileContent);
//			saveToFileByte(fileContentMap);
            this.clearTempUploadFile(key);
        }
        builder.setResult(0);

    }

    public CommonDAO getCommonCacheDAOImpl() {
        return commonCacheDAOImpl;
    }

    public void setCommonCacheDAOImpl(CommonDAO commonCacheDAOImpl) {
        this.commonCacheDAOImpl = commonCacheDAOImpl;
    }

    @Override
    public int downloadFile(IoFileRequest request,
            com.bw.download.message.IoFile.IoFileResponse.Builder builder)
            throws ManagerServerException {
        String fileName = request.getRequiredFile();
        long verifyCode = request.getVerifyCode();

        if (!commonCacheDAOImpl.isExistFileFromCache(fileName)) {
            return ErrorCodeInterface.NOT_FIND_REPLY_FILE;
        } else {
            ArrayList<IoData> list = fileMap.get(fileName);
            if (null == list || list.size() == 0) {
                list = getFileDataFromCache(fileName);
            }
            list = getFileDataFromCache(fileName);
            int index = request.getIndex();
            int maxIndex = list.size();
            if (index >= maxIndex) {
                System.out.println("index :" + index);
                return ErrorCodeInterface.ERROR;
            }
            builder.setIndex(index);
            builder.setMaxPackage(maxIndex);
            builder.setIoData(list.get(index));
            System.out.println("发送数据块大小【" + list.get(index).getDataList().get(0).toByteArray().length + "】");
            if ((index + 1) == maxIndex) {//下载完成 从map中移除fileContent
                fileMap.remove(fileName);
            }
        }
        return ErrorCodeInterface.SUCESS;
    }

    public ArrayList<IoData> getFileDataFromCache(String fileName) {
        int maxPackage = 0;
        byte[] temp = commonCacheDAOImpl.getFileFromCache(fileName);
        ArrayList<IoData> list = new ArrayList<IoData>();
        int allLength = temp.length;
        float f = (float) allLength / limit;
        Float iobj = new Float(f);
        if (iobj.intValue() == f) {// 整数拆包（刚刚好）
            maxPackage = iobj.intValue();
        } else {
            maxPackage = iobj.intValue() + 1;
        }

        for (int i = 0; i < maxPackage; i++) {
            int length = ((i + 1) == maxPackage) ? (allLength - (i * limit))
                    : limit;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            bos.write(temp, i * limit, length);

            byte[] b = bos.toByteArray();

            IoData.Builder builderIoData = IoData.newBuilder();
            ByteString copyFrom = ByteString.copyFrom(b);

            builderIoData.addData(copyFrom);
            IoData ioData = builderIoData.build();
            list.add(ioData);
        }
        fileMap.put(fileName, list);
        return list;
    }
//	public void saveToFile(byte[] fileContent){
//		try {
//			DataOutputStream o=new DataOutputStream(new FileOutputStream(new File("E:\\fileTest\\aa")));
//			o.write(fileContent);
//			o.flush();
//			o.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public void saveToFileByte(byte[] fileContent){
//		try {
//			DataOutputStream o=new DataOutputStream(new FileOutputStream(new File("E:\\fileTest\\bb")));
//			o.write(fileContent);
//			o.flush();
//			o.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
}
