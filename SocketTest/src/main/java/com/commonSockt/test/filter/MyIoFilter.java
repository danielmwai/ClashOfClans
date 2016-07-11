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
public class MyIoFilter implements IoFilter {

    private Logger logger = Logger.getLogger(MyIoFilter.class);

    @Override
    public void destroy() throws Exception {
        logger.info("destory MyIoFilter................");
        System.out.println("destory MyIoFilter................");

    }

    @Override
    public void exceptionCaught(NextFilter nextFilter, IoSession session,
            Throwable cause) throws Exception {
        logger.info("exceptionCaught MyIoFilter................");
        System.out.println("exceptionCaught MyIoFilter................");
        cause.printStackTrace();
        nextFilter.exceptionCaught(session, cause);
    }

    @Override
    public void filterClose(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("filterClose MyIoFilter................");
        System.out.println("filterClose MyIoFilter................");
        nextFilter.filterClose(session);

    }

    @Override
    public void filterWrite(NextFilter nextFilter, IoSession session,
            WriteRequest writeRequest) throws Exception {
        logger.info("filterWrite MyIoFilter................");
        System.out.println("filterWrite MyIoFilter................");
        nextFilter.filterWrite(session, writeRequest);
    }

    @Override
    public void init() throws Exception {
        logger.info("init MyIoFilter................");
        System.out.println("init MyIoFilter................");
    }

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session,
            Object message) throws Exception {
        logger.info("messageReceived MyIoFilter................");
        System.out.println("messageReceived MyIoFilter................");
        nextFilter.messageReceived(session, message);
    }

    @Override
    public void messageSent(NextFilter nextFilter, IoSession session,
            WriteRequest writeRequest) throws Exception {
        logger.info("messageSent MyIoFilter................");
        System.out.println("messageSent MyIoFilter................");
        nextFilter.messageSent(session, writeRequest);
    }

    @Override
    public void onPostAdd(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPostAdd MyIoFilter................");
        System.out.println("onPostAdd MyIoFilter................");

    }

    @Override
    public void onPostRemove(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPostRemove MyIoFilter................");
        System.out.println("onPostRemove MyIoFilter................");

    }

    @Override
    public void onPreAdd(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPreAdd MyIoFilter................");
        System.out.println("onPreAdd MyIoFilter................");
    }

    @Override
    public void onPreRemove(IoFilterChain parent, String name,
            NextFilter nextFilter) throws Exception {
        logger.info("onPreRemove MyIoFilter................");
        System.out.println("onPreRemove MyIoFilter................");

    }

    @Override
    public void sessionClosed(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionClosed MyIoFilter................");
        System.out.println("sessionClosed MyIoFilter................");
        nextFilter.sessionClosed(session);
    }

    @Override
    public void sessionCreated(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionCreated MyIoFilter................");
        System.out.println("sessionCreated MyIoFilter................");
        nextFilter.sessionCreated(session);

    }

    @Override
    public void sessionIdle(NextFilter nextFilter, IoSession session,
            IdleStatus status) throws Exception {
        logger.info("sessionIdle MyIoFilter................");
        System.out.println("sessionIdle MyIoFilter................");
        nextFilter.sessionIdle(session, status);
    }

    @Override
    public void sessionOpened(NextFilter nextFilter, IoSession session)
            throws Exception {
        logger.info("sessionOpened MyIoFilter................");
        System.out.println("sessionOpened MyIoFilter................");
        nextFilter.sessionOpened(session);
    }

}
