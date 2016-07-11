package com.bw.application.action;

import org.apache.log4j.*;
import com.bw.application.manager.channel.*;
import com.bw.application.manager.user.*;
import com.commonSocket.net.action.*;
import com.bw.application.message.*;
import com.bw.cache.vo.*;
import com.bw.baseJar.errorCode.*;

public class UserRegisterAction implements Action {

    private Logger logger;
    private ChannelManager channelManager;
    private UserManager manager;

    public UserRegisterAction() {
        this.logger = Logger.getLogger(this.getClass());
    }

    public void setManager(final UserManager manager) {
        this.manager = manager;
    }

    @Override
    public String execute(final Request request, final Response response) throws Exception {
        final UserRegisterInfo.UserRegisterResponse.Builder builder = UserRegisterInfo.UserRegisterResponse.newBuilder();
        final UserRegisterInfo.UserRegisterRequest userReg = UserRegisterInfo.UserRegisterRequest.parseFrom(request.getMessage());
        final String mailAddress = userReg.getMailAddress();
        final CGUserVO cgUserVO = new CGUserVO();
        cgUserVO.setMailAddress(mailAddress);
        try {
            final boolean isExist = this.manager.isExistForUser(cgUserVO);
            if (!isExist) {
                final String language = userReg.getLanguage();
                final String machineNum = userReg.getMachineNum();
                final String nickName = userReg.getNickName();
                final int screenHeight = userReg.getScreenHeight();
                final int screenWidth = userReg.getScreenWidth();
                cgUserVO.setLocalArea(language);
                cgUserVO.setMachineNum(machineNum);
                cgUserVO.setNickName(nickName);
                cgUserVO.setScreenHeight(screenHeight);
                cgUserVO.setScreenWidth(screenWidth);
                //       cgUserVO.setSex(Integer.parseInt(userReg.getSex()));
                cgUserVO.setSex(userReg.getSex());
                this.manager.UserRegister(cgUserVO);
                builder.setResult(ErrorCode.SUCCESS.value());
                builder.setInfo("\u6210\u529f\u4e86~OMG");
                System.out.println("\u6ce8\u518c\u6210\u529f!");
            } else {
                builder.setResult(ErrorCode.MAIL_ADDRESS_EXIST.value());
                builder.setInfo("\u5df2\u7ecf\u5b58\u5728~OMG");
                System.out.println("\u6ce8\u518c\u5931\u8d25\u6539\u7528\u6237\u5df2\u7ecf\u5b58\u5728!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(ErrorCode.ERROR.value());
            builder.setInfo("\u51fa\u9519\u4e86~OMG");
            return null;
        } finally {
            final UserRegisterInfo.UserRegisterResponse build = builder.build();
            response.write(build);
        }
        final UserRegisterInfo.UserRegisterResponse build = builder.build();
        response.write(build);
        return null;
    }
}
