package com.bw.cache;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.CASOperation;
import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;

/**
 *
 * @creator:赵清有 2010-7-19下午06:03:33
 */
public class CityMemCachedClient implements Cache {

    private Logger log = Logger.getLogger(CityMemCachedClient.class);
    MemcachedClient memcachedClient;

    // public FarmMemCachedClient(String serverArray) {
    // MemcachedClientBuilder builder = new
    // XMemcachedClientBuilder(AddrUtil.getAddresses(serverArray));
    // builder.setSessionLocator(new KetamaMemcachedSessionLocator());
    // try {
    // mc = builder.build();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // public FarmMemCachedClient(ClassLoader classLoader) {
    // super(classLoader);
    // }
    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    /*
	 * (non-Javadoc)
	 * 
	 * @see com.zxwx.cache.Cache#clear() 这个方法很危险最好不要用，它会情况cache服务器里面所以的内容
     */
    public void clear() {
        try {
            memcachedClient.flushAllWithNoReply();
            // memcachedClient.flushAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("清空缓存发生异常:", e);
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("清空缓存发生异常:", e);
        }

    }

    public Object get(String key) {
        Object result = null;
        try {
            result = memcachedClient.get(key, 5000);
            // memcachedClient.get(arg0, arg1).get(arg0, arg1)
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.error("获取 key发生异常:", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("获取 key发生异常:", e);
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("获取 key发生异常:", e);
        }
        log.debug("get from cache key[" + key + "] value[" + result + "]");
        return result;
    }

    public void put(String key, Object value) {
        if (value == null) {
            return;
        }
        try {
            memcachedClient.setWithNoReply(key, 60 * 60 * 24 * 30, value);
            // memcachedClient.set(key,60*60*24*30 ,value);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("put key发生异常:", e);
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("put key发生异常:", e);
        }
        log.debug("Push cache key[" + key + "] value[" + value + "].");
    }

    public void put(String key, Object value, int expiry) {
        if (value == null) {
            return;
        }
        // long exp=((expiry.getTime()-System.currentTimeMillis())/1000);
        try {
            memcachedClient.setWithNoReply(key, expiry, value);
            // memcachedClient.set(key,(int)exp,value);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("put key发生异常:", e);
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("put key发生异常:", e);
        }
        log.debug("Push cache key[" + key + "] value[" + value + "] expiry date[" + expiry + "].");
    }

    public boolean remove(String key) {
        boolean result = false;
        try {
            // result = memcachedClient.delete(key);
            memcachedClient.deleteWithNoReply(key);
            result = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("移除key发生异常:", e);
            return result;
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("移除key发生异常:", e);
            return result;
        }
        log.debug("Delete cache key[" + key + "] " + result + ".");
        return result;

    }

    public boolean keyExists(String key) {
        boolean exit = false;
        try {// 一秒之内检测key是否存在
            Object result = memcachedClient.get(key);
            if (null != result) {
                return true;
            } else {
                return false;
            }
            // exit=memcachedClient.cas(key, 0, 0, 1,0);
            // exit=memcachedClient.cas(key, 0, 0, 1,0);
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.error("检测key是否存在发生异常:", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("检测key是否存在发生异常:", e);
        } catch (MemcachedException e) {
            e.printStackTrace();
            log.error("检测key是否存在发生异常:", e);
        }
        return exit;
    }

    /**
     * @param key
     * @return 用户原子性操作
     */
    public GetsResponse<Object> gets(String key) {
        GetsResponse<Object> grobject = null;
        try {

            grobject = memcachedClient.gets(key, 5000);
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("获取 key发生异常(gets):", e);
            return null;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("获取 key发生异常(gets):", e);
            return null;
        } catch (MemcachedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("获取 key发生异常(gets):", e);
            return null;
        }
        //log.debug("gets from cache key[" + key + "] value[" + grobject.getValue().toString()+ "]");
        return grobject;
    }

    /**
     * @param key
     * @param getsResponse
     * @return 带检测的set操作
     */
    @SuppressWarnings("unchecked")
    public boolean cas(String key, GetsResponse<Object> getsResponse) {
        boolean result = false;
        try {
            result = memcachedClient.cas(key, getsResponse, new CASOperation() {

                @Override
                public int getMaxTries() {
                    // TODO Auto-generated method stub
                    return 0;
                }

                @Override
                public Object getNewValue(long arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    return arg1;
                }

            });
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        } catch (MemcachedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        }
        log.debug("cas from cache key[" + key + "] result[" + result + "]");
        return result;
    }

    @Override
    public Object[] get(String[] keys) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean cas(String key, final GetsResponse<Object> getsResponse, final int times) {
        boolean result = false;
        try {

            memcachedClient.cas(key, getsResponse, new CASOperation() {

                @Override
                public int getMaxTries() {
                    // TODO Auto-generated method stub
                    return times;
                }

                @Override
                public Object getNewValue(long arg0, Object arg1) {
                    // TODO Auto-generated method stub
                    arg1 = getsResponse.getValue();
                    return arg1;
                }

            });
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        } catch (MemcachedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error("cas key发生异常:", e);
        }
        log.debug("cas from cache key[" + key + "] result[" + result + "]");
        return result;
    }
}
