package com.commonSocket.net.filter.support;

import com.commonSocket.net.filter.Filter;
import com.commonSocket.net.filter.FilterChainFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DefaultFilterChainFactory
        implements FilterChainFactory {

    private List<Filter> filterList;

    public DefaultFilterChainFactory() {
        this.filterList = new LinkedList();
    }

    public List<Filter> getFilterList() {
        return this.filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public synchronized DefaultFilterChain createApplicationFilterChain() {
        DefaultFilterChain chain = new DefaultFilterChain();
        Iterator it = this.filterList.iterator();
        while (it.hasNext()) {
            Filter filter = (Filter) it.next();
            chain.addFilter(filter);
        }
        return chain;
    }
}
