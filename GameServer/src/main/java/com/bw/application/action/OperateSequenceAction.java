package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IUserManager;
import com.bw.application.message.OperateSequence.OperateSequenceRequest;
import com.bw.application.message.OperateSequence.OperateSequenceResponse;
import com.bw.exception.CacheDaoException;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class OperateSequenceAction implements Action {

    public IUserManager userManagerImpl;
    private static Logger logger = Logger.getLogger(OperateSequenceAction.class);

    @Override
    public String execute(Request request, Response paramResponse)
            throws Exception {
        OperateSequenceRequest reqMsg = OperateSequenceRequest.parseFrom(request.getMessage());
        OperateSequenceResponse.Builder builder = OperateSequenceResponse.newBuilder();
        try {
            int result = userManagerImpl.ProcessInforOfUpLoadFromClient(reqMsg);
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

    public IUserManager getUserManagerImpl() {
        return userManagerImpl;
    }

    public void setUserManagerImpl(IUserManager userManagerImpl) {
        this.userManagerImpl = userManagerImpl;
    }

}
