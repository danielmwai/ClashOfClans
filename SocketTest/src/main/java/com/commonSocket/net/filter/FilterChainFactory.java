package com.commonSocket.net.filter;

import com.commonSocket.net.filter.support.DefaultFilterChain;

public abstract interface FilterChainFactory {

    public abstract DefaultFilterChain createApplicationFilterChain();
}
