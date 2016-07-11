package com.bw.application.action;

import org.apache.log4j.*;
import com.bw.application.manager.channel.*;
import com.bw.application.config.*;
import com.bw.application.manager.user.*;
import com.commonSocket.net.action.*;
import com.bw.application.message.*;
import com.bw.cache.vo.*;
import com.bw.baseJar.vo.*;
import com.bw.application.utils.*;

public class UserLoginAction implements Action {

    private Logger logger;
    private ChannelManager channelManager;
    private AppConfig appConfig;
    private UserManager userManager;

    public UserLoginAction() {
        this.logger = Logger.getLogger(this.getClass());
    }

    @Override
    public String execute(final Request request, final Response paramResponse) throws Exception {
        final UserLoginInfo.UserLoginRequest reqMsg = UserLoginInfo.UserLoginRequest.parseFrom(request.getMessage());
        final UserLoginInfo.UserLoginResponse.Builder builder = UserLoginInfo.UserLoginResponse.newBuilder();
        final String mailAddress = reqMsg.getMailAddress();
        final String machineNum = reqMsg.getMachineNum();
        this.logger.info("\u7528\u6237\u767b\u5f55\u7684\u8d26\u6237:" + mailAddress);
        CGUserVO uservo = new CGUserVO();
        uservo.setMailAddress(mailAddress);
        final boolean existFlag = this.userManager.isExistForUser(uservo);
        uservo = this.userManager.getUserVOByMailAddress(uservo);
        final int instance_id = reqMsg.getInstanceId();
        if (!existFlag) {
            builder.setUpdateAble(0);
            builder.setInfo("\u8be5\u7528\u6237\u4e0d\u5b58\u5728\u8bf7\u91cd\u65b0\u6ce8\u518c");
            builder.setResult(5);
        } else if (!machineNum.equalsIgnoreCase(uservo.getMachineNum())) {
            builder.setUpdateAble(0);
            builder.setInfo("\u6e38\u620f\u6570\u636e\u5df2\u7ecf\u8fc1\u79fb\uff0c\u65e0\u6cd5\u901a\u8fc7\u8be5\u8bbe\u5907\u8fde\u63a5");
            builder.setResult(4);
        } else if (this.channelManager != null && this.channelManager.getChannelActiveMap().size() > 0) {
            if (this.channelManager.getChannelActiveMap().size() >= 1) {
                if (instance_id > 0 && this.channelManager.getChannelInfoMap().containsKey(new Integer(instance_id))) {
                    final CityServerChannleVO cchvo = this.channelManager.getChannelInfoMap().get(new Integer(instance_id));
                    builder.setGameServerAddress(cchvo.getAddress());
                } else {
                    final CityServerChannleVO cchvo = this.channelManager.getChannelInfoMap().get(new Integer(1));
                    builder.setGameServerAddress(cchvo.getAddress());
                }
                builder.setVerifyCode(CommonMethod.creatVerifyCode());
                builder.setUpdateAble(0);
                System.out.println("\u767b\u5f55\u6210\u529f");
                builder.setInfo("\u7528\u6237\u6210\u529f\u767b\u9646");
                builder.setResult(0);
            }
        } else {
            builder.setUpdateAble(0);
            builder.setInfo("\u7528\u6237\u767b\u5f55\u5931\u8d25");
            System.out.println("\u767b\u5f55\u5931\u8d25");
            builder.setResult(1);
        }
        paramResponse.write(builder.build());
        return null;
    }

    public ChannelManager getChannelManager() {
        return this.channelManager;
    }

    public void setChannelManager(final ChannelManager channelManager) {
        this.channelManager = channelManager;
    }

    public AppConfig getAppConfig() {
        return this.appConfig;
    }

    public void setAppConfig(final AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public void setUserManager(final UserManager userManager) {
        this.userManager = userManager;
    }
}
