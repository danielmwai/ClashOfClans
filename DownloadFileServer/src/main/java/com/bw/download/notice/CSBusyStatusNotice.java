package com.bw.download.notice;

import com.commonSocket.net.notice.Notice;
import com.commonSocket.net.notice.Session;

/**
 * @author zhaoqingyou
 *
 */
public class CSBusyStatusNotice implements Notice {

    /**
     * 双端空闲最大次数
     */
    private int bothIdleCount;

    /**
     * 写端空闲最大次数
     */
    private int readerIdleCount;

    /**
     * 读端空闲最大次数
     */
    private int writerIdleCount;

    @Override
    public void readerIdleEvent(Session session) throws Exception {
//		System.out.println("readerIdleEvent-"+session.getIoSession().getReaderIdleCount());

    }

    @Override
    public void bothIdleEvent(Session session) throws Exception {
        if (session.getIoSession().getBothIdleCount() >= bothIdleCount) {
            session.getIoSession().close(true);
        }
    }

    @Override
    public void writerIdleEvent(Session session) throws Exception {
//		System.out.println("writerIdleEvent-"+session.getIoSession().getWriterIdleCount());

    }

    public int getBothIdleCount() {
        return bothIdleCount;
    }

    public void setBothIdleCount(int bothIdleCount) {
        this.bothIdleCount = bothIdleCount;
    }

    public int getReaderIdleCount() {
        return readerIdleCount;
    }

    public void setReaderIdleCount(int readerIdleCount) {
        this.readerIdleCount = readerIdleCount;
    }

    public int getWriterIdleCount() {
        return writerIdleCount;
    }

    public void setWriterIdleCount(int writerIdleCount) {
        this.writerIdleCount = writerIdleCount;
    }

}
