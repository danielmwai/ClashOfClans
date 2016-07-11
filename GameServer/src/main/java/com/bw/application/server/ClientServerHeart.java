package com.bw.application.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.bw.application.config.AppConfig;
import com.bw.application.message.RegisterChannel.RegisterChannelRequest;
import com.commonSocket.net.Application;
import com.commonSocket.net.Service;
import com.commonSocket.net.IMessage.Head;
import com.commonSocket.net.IMessage.PNPCMessage;
import com.commonSocket.net.util.PnpcAdler32;

/**
 * @author zhaoqingyou
 *
 */
public class ClientServerHeart implements Service {

    private Logger logger = Logger.getLogger(getClass());
    private AtomicBoolean started = new AtomicBoolean(false);
    private SocketAddress socketAddress;
    private int sendBufferSize;

    public int getSendBufferSize() {
        return sendBufferSize;
    }

    public void setSendBufferSize(int sendBufferSize) {
        this.sendBufferSize = sendBufferSize;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public int getEgisTime() {
        return egisTime;
    }

    public void setEgisTime(int egisTime) {
        this.egisTime = egisTime;
    }
    private int port;
    private String address;
    /**
     * 超时时间（毫秒为单位）
     */
    private int timeout;
    private AppConfig appConfig;
    /**
     * 维护线程
     */
    private ScheduledExecutorService egisService;
    /**
     * 心跳间隔（毫秒为单位）
     */
    private int egisTime;
    private ProtocolCodecFactory protocolCodecFactory;

    @Override
    public void start() {
        if (started.compareAndSet(false, true)) {
//			this.socketAddress = new InetSocketAddress(address, port);
            this.egisService = Executors.newSingleThreadScheduledExecutor();
            this.egisService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    sendToAllLogin();
                }
            }, 1, egisTime, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void stop() {
        if (started.compareAndSet(true, false)) {
        }

    }

    public void sendHeartbeat(String address, int port) {

        NioSocketConnector connector = new NioSocketConnector();
        Application.getInstance().bindAppId(9999);
        Application.getInstance().bindConnector(connector);
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(protocolCodecFactory));
        connector.setConnectTimeoutMillis(10000);
        connector.setHandler(new IoHandlerAdapter());// 设置事件处理器
        ConnectFuture connFuture = connector.connect(new InetSocketAddress(address, port));
        connFuture.awaitUninterruptibly();
        IoSession session = connFuture.getSession();
        // region 包装注册频道的协议
        RegisterChannelRequest.Builder builder = RegisterChannelRequest.newBuilder();
        builder.setAppId((int) appConfig.getAppId());
        builder.setRoleCount(10);
        RegisterChannelRequest request = builder.build();

        Head.Builder headbBuilder = Head.newBuilder();
        headbBuilder.setCommandId(1008);// action id
        headbBuilder.setCheckSum(PnpcAdler32.adler32(request.toByteArray()));
        headbBuilder.setSequence(0l);
        headbBuilder.setIsHttp(true);
        Head head = headbBuilder.build();

        PNPCMessage.Builder pMessagebBuilder = PNPCMessage.newBuilder();
        pMessagebBuilder.setMsgHead(head.toByteString());
        pMessagebBuilder.setMsgBody(request.toByteString());
        PNPCMessage pMessage = pMessagebBuilder.build();
        byte[] data = pMessage.toByteArray();
        //endregion
        IoBuffer ioBuffer = IoBuffer.allocate(4 + data.length);
        ioBuffer.putInt(data.length);
        ioBuffer.put(data);
        ioBuffer.flip();
        session.write(ioBuffer);
        if (logger.isDebugEnabled()) {
            logger.debug("<Server heart beat !>");
        }
        session.close(true);
        connector.dispose();
        connector = null;
    }

    public ProtocolCodecFactory getProtocolCodecFactory() {
        return protocolCodecFactory;
    }

    public void setProtocolCodecFactory(ProtocolCodecFactory protocolCodecFactory) {
        this.protocolCodecFactory = protocolCodecFactory;
    }

    public void sendToAllLogin() {
        if (this.getAddress().indexOf(";") != -1) {
            String[] addressArray = this.getAddress().split(";");
            for (int i = 0; i < addressArray.length; i++) {
                sendHeartbeat(addressArray[i], this.getPort());
            }
        } else {
            sendHeartbeat(this.getAddress(), this.getPort());
        }

    }
}
