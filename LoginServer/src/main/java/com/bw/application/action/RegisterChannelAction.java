package com.bw.application.action;

import org.apache.log4j.*;
import com.bw.application.manager.channel.*;
import com.commonSocket.net.action.*;
import com.bw.application.message.*;

public class RegisterChannelAction implements Action {

    private Logger logger;
    private ChannelManager channelManager;

    public RegisterChannelAction() {
        this.logger = Logger.getLogger(this.getClass());
    }

    public ChannelManager getChannelManager() {
        return this.channelManager;
    }

    public void setChannelManager(final ChannelManager channelManager) {
        this.channelManager = channelManager;
    }

    @Override
    public String execute(final Request paramRequest, final Response paramResponse) throws Exception {
        final RegisterChannel.RegisterChannelRequest reqMsg = RegisterChannel.RegisterChannelRequest.parseFrom(paramRequest.getMessage());
        this.channelManager.registerChannelActive(reqMsg.getAppId(), reqMsg.getRoleCount());
        this.logger.debug(reqMsg);
        return null;
    }
}
