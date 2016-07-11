package com.bw.cache;

import net.rubyeye.xmemcached.GetsResponse;

/**
 *
 *
 * @creator:赵清有 2010-7-19下午05:48:34 cache 基础类
 */
public interface Cache {

    public void put(String key, Object value);

    public void put(String key, Object value, int expiry);

    public Object get(String key);

    public Object[] get(String[] keys);

    public boolean remove(String key);

    public void clear();

    public boolean keyExists(String key);

    /**
     * @param key
     * @return 用户原子性操作
     */
    public GetsResponse<Object> gets(String key);

    /**
     * @param key
     * @param getsResponse
     * @return 带检测的set操作
     */
    public boolean cas(String key, GetsResponse<Object> getsResponse);

    /**
     * @param key
     * @param getsResponse
     * @param times 重复次数
     * @return
     */
    public boolean cas(String key, GetsResponse<Object> getsResponse, int times);

}
