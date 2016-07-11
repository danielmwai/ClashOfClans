package com.commonSockt.test.filter;

import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * @author Administrator 过滤器测试类
 */
public class MyIoFilter2 implements IoFilter {

    private Logger logger = Logger.getLogger(MyIoFilter2.class);

    @Override
    public void destroy() throws Exception {
        logger.info("destory MyIoFilter2................");
        System.out.println("destory MyIoFilter2................");

    }

    @Override
    public void exceptionCaught(NextFilter nextFilter, IoSession session,
            Throwable cause) throws Exception {
        logger.info("exceptionCaught MyIoFilter2................");
        System.out.println("exceptionCaught MyIoFilter2................");
        cause.printStackTrace();
        nextFilter.exceptionCaught(session, cause);
    }

    @Override
    public void filterClose(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("filterClose MyIoFilter2................");
        System.out.println("filterClose MyIoFilter2................");
        nextFilter.filterClose(session);

    }

    @Override
    public void filterWrite(NextFilter nextFilter, IoSession session,
            WriteRequest writeRequest) throws Exception {
        logger.info("filterWrite MyIoFilter2................");
        System.out.println("filterWrite MyIoFilter2................");
        nextFilter.filterWrite(session, writeRequest);
    }

    @Override
    public void init() throws Exception {
        logger.info("init MyIoFilter2................");
        System.out.println("init MyIoFilter2................");
    }

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session,
            Object message) throws Exception {
        logger.info("messageReceived MyIoFilter2................");
        System.out.println("messageReceived MyIoFilter2................");
        nextFilter.messageReceived(session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session,
            WriteRequest writeRequest) throws Exception {
        logger.info("messageSent MyIoFilter2................");
        System.out.println("messageSent MyIoFilter2................");
        nextFilter.messageSent(session, writeRequest);
    }

    @Override
    public void onPostAdd(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPostAdd MyIoFilter2................");
        System.out.println("onPostAdd MyIoFilter2................");

    }

    @Override
    public void onPostRemove(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPostRemove MyIoFilter2................");
        System.out.println("onPostRemove MyIoFilter2................");

    }

    @Override
    public void onPreAdd(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPreAdd MyIoFilter2................");
        System.out.println("onPreAdd MyIoFilter2................");
    }

    @Override
    public void onPreRemove(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPreRemove MyIoFilter2................");
        System.out.println("onPreRemove MyIoFilter2................");

    }

    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionClosed MyIoFilter2................");
        System.out.println("sessionClosed MyIoFilter2................");
        nextFilter.sessionClosed(session);
    }

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionCreated MyIoFilter2................");
        System.out.println("sessionCreated MyIoFilter2................");
        nextFilter.sessionCreated(session);

    }

    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session,
            IdleStatus status) throws Exception {
        logger.info("sessionIdle MyIoFilter2................");
        System.out.println("sessionIdle MyIoFilter2................");
        nextFilter.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionOpened MyIoFilter2................");
        System.out.println("sessionOpened MyIoFilter2................");
        nextFilter.sessionOpened(session);
    }

}
