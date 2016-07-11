package com.commonSocket.active;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.test.application.message.UserLoginInfo.UserLoginRequest;
import com.test.application.message.UserLoginInfo.UserLoginResponse;

/**
 * 对Mina启动类的封装
 *
 * @author dennyzhao
 *
 */
public class UserLoginAction implements Action {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public String execute(Request request, Response response) throws Exception {
        UserLoginRequest reqMsg = UserLoginRequest.parseFrom(request.getMessage());
        UserLoginResponse.Builder builder = UserLoginResponse.newBuilder();
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
            response.write(builder.build());
        }
        return null;
    }

    private String Now() {// 18 length 8 bit
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        // 年|月|日|小时|分钟|秒|毫秒
        SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sft.format(calendar.getTime());
    }

}
