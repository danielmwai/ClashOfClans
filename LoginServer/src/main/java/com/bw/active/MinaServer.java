/**
 * @author dennyZhao
 *
 *         2012-4-11 下午04:02:57
 */
package com.bw.active;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.firewall.ConnectionThrottleFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.integration.jmx.IoFilterMBean;
import org.apache.mina.integration.jmx.IoServiceMBean;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.bw.application.config.AppConfig;
import com.commonSocket.net.Application;

/**
 * 对Mina启动类的封装
 *
 */
public class MinaServer {

    private Logger logger = Logger.getLogger(getClass());
    private ProtocolCodecFactory protocolCodecFactory;
    private IoAcceptor acceptor;
    private AppConfig appConfig;
    private int port;
    private String address;
    private IoHandler handler;
    /**
     * 核心线程池数量
     */
    private int corePoolSize = 16;
    /**
     * 线程池最大数量
     */
    private int maximumPoolSize = 500;
    /**
     * 保持连接的时间，单位：秒
     */
    private int keepAliveTime = 60;
    /**
     * 队列的大小
     */
    private int queueSize = 20;
    /**
     * 两次连接的间隔时间 单位:ms(毫秒)
     */
    private int connectionInterval = 300;
    /**
     * 读端空闲最大时间
     */
    private int readerIdleMaxTime;
    /**
     * 写端空闲最大时间
     */
    private int writerIdleMaxTime;
    /**
     * 双端空闲时间最大时间
     */
    private int bothIdleMaxTime;

    private AtomicBoolean started = new AtomicBoolean(false);

    /**
     *
     */
    public MinaServer() {
    }

    private void init() {
        try {
            acceptor = new NioSocketAcceptor();

            // 日志
            acceptor.getFilterChain().addLast("logger", new LoggingFilter());

            // ---IP地址过滤----
            // BlacklistFilter blacklistFilter = new BlacklistFilter();
            //
            // blacklistFilter.block(InetAddress.getByName("192.168.1.102"));
            //
            // acceptor.getFilterChain().addLast("blackList", blacklistFilter);
            // ----------------
            // 同一IP时间内请求次数限制
            ConnectionThrottleFilter connectionThrottleFilter = new ConnectionThrottleFilter();
            connectionThrottleFilter.setAllowedInterval(connectionInterval);
            acceptor.getFilterChain().addLast("ctf", connectionThrottleFilter);

            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(protocolCodecFactory));
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(queueSize), new ThreadPoolExecutor.AbortPolicy());
            // 线程池过滤器：调用下一个过滤器的事件方法时，把其交给 Executor来异步执行
            //默认请求的filter handler 是有执行顺序的
            acceptor.getFilterChain().addLast("threadpool", new ExecutorFilter());
            acceptor.setHandler(handler);
            acceptor.getSessionConfig().setReadBufferSize(2048);
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, bothIdleMaxTime);
            SocketAddress socketAddress = null;
            if (address == null || address.equals("")) {
                socketAddress = new InetSocketAddress(port);
            } else {
                socketAddress = new InetSocketAddress(address, port);
            }
            acceptor.bind(socketAddress);
            Application.getInstance().bindAppId(appConfig.getAppId());
            Application.getInstance().bindAcceptor(acceptor);
            logger.info("<-WGLoginServer application[" + appConfig.getAppId() + "] started->");
        } catch (UnknownHostException e) {
            logger.error("<-Failed to start AppServer->", e);
            System.exit(1);
        } catch (IOException e) {
            logger.error("<-Failed to start AppServer->", e);
            System.exit(1);
        }
    }

    /**
     * 启用 JConsole，连接 MyServer所在的 JVM进程，同样提供了{@link IoFilterMBean}
     *
     * @param acceptor
     * @throws MalformedObjectNameException
     * @throws NullPointerException
     * @throws InstanceAlreadyExistsException
     * @throws MBeanRegistrationException
     * @throws NotCompliantMBeanException
     */
    public void registerJMX(IoFilter source) {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        IoServiceMBean acceptorMBean = new IoServiceMBean(acceptor);
        IoFilterMBean ioFilterMBean = new IoFilterMBean(source);
        ObjectName acceptorName;
        ObjectName ioFilterName;
        try {
            acceptorName = new ObjectName(acceptor.getClass().getPackage().getName() + ":type=acceptor,name=" + acceptor.getClass().getSimpleName());
            ioFilterName = new ObjectName(ioFilterMBean.getClass().getPackage().getName() + ":type=ioFilterMBean,name="
                    + ioFilterMBean.getClass().getSimpleName());
            mBeanServer.registerMBean(acceptorMBean, acceptorName);
            mBeanServer.registerMBean(ioFilterMBean, ioFilterName);
        } catch (MalformedObjectNameException e) {
            logger.error(e);
        } catch (NullPointerException e) {
            logger.error(e);
        } catch (InstanceAlreadyExistsException e) {
            logger.error(e);
        } catch (MBeanRegistrationException e) {
            logger.error(e);
        } catch (NotCompliantMBeanException e) {
            logger.error(e);
        }

    }

    public void start() {
        if (started.compareAndSet(false, true)) {
            init();
            if (logger.isInfoEnabled()) {
                if (address == null || address.equals("")) {
                    logger.info("mina server started,listening:" + acceptor.getLocalAddress());
                } else {
                    logger.info("mina server started,listening:/" + address + ":" + port);
                }
            }
        }
    }

    public void stop() {
        if (started.compareAndSet(true, false)) {
            acceptor.unbind();
            if (logger.isDebugEnabled()) {
                logger.debug("mina server stoped");
            }
        }
    }

    public ProtocolCodecFactory getProtocolCodecFactory() {
        return protocolCodecFactory;
    }

    public void setProtocolCodecFactory(ProtocolCodecFactory protocolCodecFactory) {
        this.protocolCodecFactory = protocolCodecFactory;
    }

    public IoAcceptor getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(IoAcceptor acceptor) {
        this.acceptor = acceptor;
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

    public IoHandler getHandler() {
        return handler;
    }

    public void setHandler(IoHandler handler) {
        this.handler = handler;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getConnectionInterval() {
        return connectionInterval;
    }

    public void setConnectionInterval(int connectionInterval) {
        this.connectionInterval = connectionInterval;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public int getReaderIdleMaxTime() {
        return readerIdleMaxTime;
    }

    public void setReaderIdleMaxTime(int readerIdleMaxTime) {
        this.readerIdleMaxTime = readerIdleMaxTime;
    }

    public int getWriterIdleMaxTime() {
        return writerIdleMaxTime;
    }

    public void setWriterIdleMaxTime(int writerIdleMaxTime) {
        this.writerIdleMaxTime = writerIdleMaxTime;
    }

    public int getBothIdleMaxTime() {
        return bothIdleMaxTime;
    }

    public void setBothIdleMaxTime(int bothIdleMaxTime) {
        this.bothIdleMaxTime = bothIdleMaxTime;
    }

}
