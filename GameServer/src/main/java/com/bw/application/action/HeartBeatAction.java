package com.bw.application.action;

import org.apache.log4j.Logger;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.test.application.message.HeartBeat.HeartBeatRequest;
import com.test.application.message.HeartBeat.HeartBeatResponse;

public class HeartBeatAction implements Action {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public String execute(Request request, Response paramResponse)
            throws Exception {
        HeartBeatRequest reqMsg = HeartBeatRequest.parseFrom(request.getMessage());
        HeartBeatResponse.Builder builder = HeartBeatResponse.newBuilder();
        String userName = reqMsg.getUserName();
        String password = reqMsg.getPassword();
        logger.info("用户登录的用户名:" + userName);
        logger.info("用户登录的密码是:" + password);

        builder.setInfo("用户成功登陆");
        builder.setResult(0);

        try {
        } catch (Exception e) {
            e.printStackTrace();
            builder.setResult(1);
            builder.setInfo(e.getMessage());
            logger.error(e);
        } finally {
            paramResponse.write(builder.build());
        }
        return null;
    }

}
