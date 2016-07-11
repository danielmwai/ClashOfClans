package com.commonSocket.net.filter.support;

import com.google.protobuf.ByteString;
import java.util.Date;
import org.apache.mina.core.session.IoSession;

class Tag {

    private Date createTime;
    private long threadId;
    private ByteString message;
    private IoSession session;

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public ByteString getMessage() {
        return this.message;
    }

    public void setMessage(ByteString message) {
        this.message = message;
    }

    public long getThreadId() {
        return this.threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public IoSession getSession() {
        return this.session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }
}
