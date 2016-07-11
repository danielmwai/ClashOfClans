package com.commonSocket.net.filter.support;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.filter.Filter;
import com.commonSocket.net.filter.FilterChain;
import com.commonSocket.net.notice.Session;

public class CommandCollectFilter
        implements Filter {

    private Logger logger = Logger.getLogger(getClass());
    private String destinationName;

    public String getDestinationName() {
        return this.destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public void init() {
    }

    public void doFilter(Request request, Response response, FilterChain chain) throws Exception {
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Message:" + request.getMessage().toStringUtf8());
            this.logger.debug("REQUEST START:sessionId=" + request.getSession().getId() + ", sequence="
                    + request.getSequence());
        }
        IoSession session = request.getSession();
        CommandNotify notify = new CommandNotify();
        notify.setSessionId(String.valueOf(request.getSession().getId()));
        notify.setAppId((String) session.getAttribute("appid"));
        notify.setUserId((String) session.getAttribute("userid"));
        notify.setRefId((String) session.getAttribute("refid"));
        notify.setMobile((String) session.getAttribute("mobile"));
        notify.setReleaseFlag((String) session.getAttribute("releaseflag"));
        notify.setVersion((String) session.getAttribute("medletversion"));
        notify.setUa((String) session.getAttribute("ua"));
        notify.setChannelKey((String) session.getAttribute("rinfo"));
        notify.setFid((String) session.getAttribute("fid"));
        notify.setMessage(request.getMessage());
        chain.doFilter(request, response);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("RESPONSE FINISHED:sessionId=" + request.getSession().getId() + ", sequence="
                    + request.getSequence());
        }
    }

    public void doFilter(Session session, FilterChain filterchain)
            throws Exception {
        filterchain.doFilter(session);
    }

    public void destroy() {
    }
}
