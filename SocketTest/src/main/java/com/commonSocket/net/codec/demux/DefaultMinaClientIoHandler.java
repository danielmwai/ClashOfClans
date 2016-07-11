package com.commonSocket.net.codec.demux;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class DefaultMinaClientIoHandler extends IoHandlerAdapter {

    public void sessionCreated(IoSession session)
            throws Exception {
        super.sessionCreated(session);
    }

    public void sessionOpened(IoSession session)
            throws Exception {
        super.sessionOpened(session);
    }

    public void sessionClosed(IoSession session)
            throws Exception {
        super.sessionClosed(session);
    }

    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        super.sessionIdle(session, status);
    }

    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        super.exceptionCaught(session, cause);
    }

    public void messageReceived(IoSession session, Object message)
            throws Exception {
        super.messageReceived(session, message);
    }

    public void messageSent(IoSession session, Object message)
            throws Exception {
        super.messageSent(session, message);
    }
}
