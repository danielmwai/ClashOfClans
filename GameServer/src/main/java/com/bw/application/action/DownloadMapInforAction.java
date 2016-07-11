package com.bw.application.action;

import java.util.Date;

import org.apache.log4j.Logger;

import com.bw.application.config.AppConfig;
import com.bw.application.manager.IUserManager;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforRequest;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse;
import com.bw.application.resourceManager.ResGlobal;
import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.onlinemanage.OnlineManage;

public class DownloadMapInforAction implements Action {

    public IUserManager userManagerImpl;
    public OnlineManage onLineManagerImpl;
    private AppConfig appConfig;
    private static Logger logger = Logger.getLogger(DownloadMapInforAction.class);

    @Override
    public String execute(Request request, Response paramResponse) throws Exception {
        DownloadMapInforRequest reqMsg = DownloadMapInforRequest.parseFrom(request.getMessage());
        DownloadMapInforResponse.Builder builder = DownloadMapInforResponse.newBuilder();
        String mailAddress = reqMsg.getMailAddress();
        try {
            //处理在线用户最大数量
            String key = appConfig.getAreaId() + "_" + appConfig.getAppId();
            BwGameChannleVO cscv = ResGlobal.getInstance().cityServerChannleVOForAreaMap.get(key);
            if (onLineManagerImpl.getOnlineUserCount() > cscv.getMaxusercount()) {
                builder.setResult(ErrorCodeInterface.SERVER_IS_OVER_FLOW);
            } else {
                onLineManagerImpl.addOnlineRole(mailAddress, new Date());
                userManagerImpl.getUserAllInfor(builder, mailAddress);
                builder.setResult(0);
            }

            request.getSession().setAttribute("mailAddress", mailAddress);
            request.getSession().setAttribute("pvpMark", builder.getPvpMark());
            //同时更新用户的在线状态有离线改为在线,如果发生战斗,提示用户等待
            //1 先检测缓存数据
            //2检测状态 如果战斗等待,如果被毁,显示 被保护时间和
            //
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            System.out.println("登录失败!");
            logger.error("下发用户建筑信息产生异常:", e);
        } finally {

            paramResponse.write(builder.build());
        }
        return null;

    }

    public IUserManager getUserManagerImpl() {
        return userManagerImpl;
    }

    public void setUserManagerImpl(IUserManager userManagerImpl) {
        this.userManagerImpl = userManagerImpl;
    }

    public OnlineManage getOnLineManagerImpl() {
        return onLineManagerImpl;
    }

    public void setOnLineManagerImpl(OnlineManage onLineManagerImpl) {
        this.onLineManagerImpl = onLineManagerImpl;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

}
