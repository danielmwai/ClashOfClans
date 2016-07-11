package com.commonSocket.net;

import com.commonSocket.net.onlinemanage.OnlineManage;

import java.util.Map;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Application {

    private static Application instance;
    private IoAcceptor acceptor;
    private OnlineManage onlineManage;
    private NioSocketConnector connector;
    private long appId;

    public static synchronized Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public boolean canStart() {
        return true;
    }

    public void bindAppId(long appId) {
        this.appId = appId;
    }

    public Map<Long, IoSession> getSessionMap() {
        return this.acceptor.getManagedSessions();
    }

    public int getSessionCount() {
        if (this.acceptor == null) {

            System.out.println("accepter is null .........");
            System.out.println("appId: ........." + this.appId);
        }
        return this.acceptor.getManagedSessionCount();
    }

    public void bindAcceptor(IoAcceptor acceptor) {
        this.acceptor = acceptor;
    }

    public IoAcceptor getAcceptor() {
        return this.acceptor;
    }

    public long getAppId() {
        return this.appId;
    }

    public OnlineManage getOnlineManage() {
        return this.onlineManage;
    }

    public void setOnlineManage(OnlineManage onlineManage) {
        this.onlineManage = onlineManage;
    }

    public int getOnLineUserCount() {
        return this.onlineManage.getOnlineUserCount();
    }

    public void bindConnector(NioSocketConnector connector) {
        this.connector = connector;
    }
}
