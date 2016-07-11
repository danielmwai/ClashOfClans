package com.bw.dao.cachedao;

import org.apache.log4j.Logger;

import com.bw.cache.Cache;

/**
 * @creator:赵清有 2010-7-19下午06:18:10 dao层 基础cache类 主要做 cache key 的生成和 key remove
 */
public abstract class CacheDao {

    protected Cache cache;

    protected String keySplit = "_";

    protected Logger log = Logger.getLogger(CacheDao.class);
    //区域ID
    public int area_id;

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    /**
     * 返回cache组名
     *
     * @return
     */
    protected String getCacheGroup() {
        return "FarmCache" + keySplit + getKeyPre();
    }

    protected String generatekey(String keyName, String keyValue) {
        return getCacheGroup() + keySplit + keyName + "[" + keyValue + "]";
    }

    protected String generatekey(String value) {
        return getCacheGroup() + keySplit + "[" + value + "]";
    }

    /**
     * 从缓存中移除掉指定key的数据
     *
     * @param keyName
     * @param keyValue
     */
    protected void removeCache(String keyName, String keyValue) {
        String key = generatekey(keyName, keyValue);
        getCache().remove(key);
    }

    protected void removeCache(String value) {
        String key = generatekey(value);
        getCache().remove(key);
    }

    protected String getKeyPre() {
        return "[ ]";
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

}
