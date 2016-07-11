package com.commonSocket.net.filter.support;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.filter.Filter;
import com.commonSocket.net.filter.FilterChain;
import com.commonSocket.net.notice.Notice;
import com.commonSocket.net.notice.Session;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;

public class DefaultFilterChain implements FilterChain {

    private Logger logger = Logger.getLogger(getClass());
    private List<Filter> filters = new LinkedList();
    private Action action;
    private Notice notice;
    private Iterator<Filter> it = null;

    public void doFilter(Request request, Response response) throws Exception {
        if (hasMoreFilter()) {
            getFilter().doFilter(request, response, this);
        } else if (this.action != null) {
            this.action.execute(request, response);
        } else {
            this.logger.warn("Action is null,instruction is not executed!" + request.getMessage().toString());
        }
    }

    public void doFilter(Session session) throws Exception {
        if (this.notice != null) {
            if (session.isIdleEvent(IdleStatus.READER_IDLE)) {
                this.notice.readerIdleEvent(session);
            } else if (session.isIdleEvent(IdleStatus.WRITER_IDLE)) {
                this.notice.writerIdleEvent(session);
            } else if (session.isIdleEvent(IdleStatus.BOTH_IDLE)) {
                this.notice.bothIdleEvent(session);
            }
        } else {
            this.logger.warn("Notice is null,instruction is not executed!" + session.getMessage().toString());
        }
    }

    private boolean hasMoreFilter() {
        if (this.it == null) {
            this.it = this.filters.iterator();
        }
        return this.it.hasNext();
    }

    private Filter getFilter() {
        if (this.it == null) {
            this.it = this.filters.iterator();
        }
        return (Filter) this.it.next();
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return this.action;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return null;
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }
}
