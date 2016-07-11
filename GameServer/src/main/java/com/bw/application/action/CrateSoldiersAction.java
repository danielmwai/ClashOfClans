package com.bw.application.action;

import org.apache.log4j.Logger;

import com.bw.application.manager.IUserManager;
import com.bw.application.message.ProduceSoldierMessage.ProduceSoldiersRequest;
import com.bw.application.message.ProduceSoldierMessage.ProduceSoldiersResponse;
import com.bw.application.resourceManager.ResGlobal;
import com.bw.exception.CacheDaoException;
import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public class CrateSoldiersAction implements Action {

    private static Logger log = Logger.getLogger(CrateSoldiersAction.class);
    public IUserManager userManagerImpl;

    @Override
    public String execute(Request paramRequest, Response paramResponse)
            throws Exception {
        ProduceSoldiersRequest request = ProduceSoldiersRequest.parseFrom(paramRequest.getMessage());
        ProduceSoldiersResponse.Builder builder = ProduceSoldiersResponse.newBuilder();
        try {
            int result = userManagerImpl.createSoldiersFromClient(request);
            builder.setResult(result);
        } catch (Exception e) {

            builder.setResult(1);
            builder.setInfo(e.getMessage());

        } finally {
            paramResponse.write(builder.build());
        }
        // TODO Auto-generated method stub
        return null;
    }

    public IUserManager getUserManagerImpl() {
        return userManagerImpl;
    }

    public void setUserManagerImpl(IUserManager userManagerImpl) {
        this.userManagerImpl = userManagerImpl;
    }

}
