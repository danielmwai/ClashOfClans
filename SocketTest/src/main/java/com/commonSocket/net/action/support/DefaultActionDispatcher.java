package com.commonSocket.net.action.support;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.ActionDispatcher;
import com.commonSocket.net.action.ActionFactory;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.filter.FilterChainFactory;
import com.commonSocket.net.filter.support.DefaultFilterChain;
import com.commonSocket.net.filter.support.DefaultFilterChainFactory;

import org.apache.log4j.Logger;

public class DefaultActionDispatcher
        implements ActionDispatcher {

    private Logger logger = Logger.getLogger(getClass());
//  FilterChainFactory filterChainFactory = new DefaultFilterChainFactory();
    private FilterChainFactory filterChainFactory;
    ActionFactory actionFactory;

    public void dispatcher(Request request, Response response) throws Exception {
        try {
            Action action = this.actionFactory.createAction(request.getCommandId());
            if (action == null) {
                this.logger.error("create action fail!");
            }
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("create action success");
            }
            DefaultFilterChain filterChain = this.filterChainFactory.createApplicationFilterChain();
            filterChain.setAction(action);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("begin do filter");
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            this.logger.error("dispatcher exception", e);
        }
    }

    public ActionFactory getActionFactory() {
        return this.actionFactory;
    }

    public void setActionFactory(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    public FilterChainFactory getFilterChainFactory() {
        return this.filterChainFactory;
    }

    public void setFilterChainFactory(FilterChainFactory filterChainFactory) {
        this.filterChainFactory = filterChainFactory;
    }
}
