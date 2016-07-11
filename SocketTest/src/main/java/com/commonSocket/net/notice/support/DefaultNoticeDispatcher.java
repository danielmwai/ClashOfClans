package com.commonSocket.net.notice.support;

import com.commonSocket.net.filter.FilterChainFactory;
import com.commonSocket.net.filter.support.DefaultFilterChain;
import com.commonSocket.net.notice.Notice;
import com.commonSocket.net.notice.NoticeDispatcher;
import com.commonSocket.net.notice.NoticeFactory;
import com.commonSocket.net.notice.Session;

import org.apache.log4j.Logger;

public class DefaultNoticeDispatcher
        implements NoticeDispatcher {

    private Logger logger = Logger.getLogger(getClass());
    private FilterChainFactory filterChainFactory;
    private NoticeFactory noticeFactory;

    public void circular(Session session)
            throws Exception {
        Notice notice = this.noticeFactory.creatNotice(session.getNoticeId());
        if (notice == null) {
            this.logger.error("create notice fail!");
        }
        DefaultFilterChain filterChain = this.filterChainFactory.createApplicationFilterChain();
        filterChain.setNotice(notice);
        filterChain.doFilter(session);
    }

    public FilterChainFactory getFilterChainFactory() {
        return this.filterChainFactory;
    }

    public void setFilterChainFactory(FilterChainFactory filterChainFactory) {
        this.filterChainFactory = filterChainFactory;
    }

    public NoticeFactory getNoticeFactory() {
        return this.noticeFactory;
    }

    public void setNoticeFactory(NoticeFactory noticeFactory) {
        this.noticeFactory = noticeFactory;
    }
}
