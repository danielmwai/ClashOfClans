package com.bw.dao.cachedao;

import net.rubyeye.xmemcached.GetsResponse;

import com.bw.cache.vo.BattleLineVO;
import com.bw.dao.BattleLineDAO;
import com.bw.exception.CacheDaoException;

public class BattleLineDAOImpl extends CacheDao implements BattleLineDAO {

    String key_prefix = "bw_battle_Line_";

    @Override
    public BattleLineVO getBattleLineVO(String mailAddress) throws CacheDaoException {
        String key = key_prefix + mailAddress;
        GetsResponse<Object> grobject = this.cache.gets(key);
        if (null != grobject && null != grobject.getValue()) {
            BattleLineVO t = (BattleLineVO) grobject.getValue();
            t.setCasflag(grobject.getCas());
            return t;
        }
        return null;
    }

    @Override
    public synchronized boolean updateBattleLineVO(BattleLineVO blvo)
            throws CacheDaoException {
        String key = key_prefix + blvo.getMailAddress();
        GetsResponse<Object> getsResponse = new GetsResponse<Object>(blvo.getCasflag(), blvo);
        boolean result = this.cache.cas(key, getsResponse);
        getsResponse = null;
        return result;
    }

    @Override
    public boolean updateBattleLineVONoCas(BattleLineVO blvo)
            throws CacheDaoException {
        String key = key_prefix + blvo.getMailAddress();
        GetsResponse<Object> getsResponse = new GetsResponse<Object>(blvo.getCasflag(), blvo);
//		this.getCache().cas(key, getsResponse);
        this.getCache().put(key, blvo);
        return true;
    }

    @Override
    public boolean updateBattleLineVOWaitTimes(BattleLineVO blvo, int waitTimes)
            throws CacheDaoException {
        String key = key_prefix + blvo.getMailAddress();
        GetsResponse<Object> getsResponse = new GetsResponse<Object>(blvo.getCasflag(), blvo);
        boolean result = this.cache.cas(key, getsResponse);
        getsResponse = null;
        return result;
    }

}
