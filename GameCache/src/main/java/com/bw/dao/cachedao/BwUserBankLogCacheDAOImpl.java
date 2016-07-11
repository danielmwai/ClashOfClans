package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bw.cache.vo.BwUserBankLogVO;
import com.bw.dao.BwUserBankLogDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBankLogCacheDAOImpl extends CacheDao implements
        BwUserBankLogDAO {

    String success_key = "bw_user_bank_log_";
    public BwUserBankLogDAO bwUserBankLogDaoImpl;

    public void delete(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
    }

    @Override
    public BwUserBankLogVO queryBwUserBankLogVOById(
            BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwUserBankLogVOCount(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserBankLogVOIds(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        return null;
    }

    @Override
    public void save(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException {
        bwUserBankLogDaoImpl.save(bwuserbanklogvo);
        String key = success_key + bwuserbanklogvo.getBoweiId() + "_" + bwuserbanklogvo.getAppId();
        Object o = this.getCache().get(key);
        int result = bwuserbanklogvo.getBuyStatus();
        if (result == 0) {
            if (null != o) {
                LinkedList<String> t = (LinkedList<String>) o;
                if (t.size() >= 20) {
                    t.removeLast();
                }
                t.add(bwuserbanklogvo.getOrderSn());
                this.getCache().put(key, t);
                t.clear();
                t = null;
            } else {
                List<String> successList = new LinkedList<String>();
                successList.add(bwuserbanklogvo.getOrderSn());
                this.getCache().put(key, successList);
            }
        }

    }

    @Override
    public void update(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {

    }

    @Override
    public List<BwUserBankLogVO> queryBwUserBankLogVO(
            BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException {
        return bwUserBankLogDaoImpl.queryBwUserBankLogVO(bwuserbanklogvo);
    }

    public BwUserBankLogDAO getBwUserBankLogDaoImpl() {
        return bwUserBankLogDaoImpl;
    }

    public void setBwUserBankLogDaoImpl(BwUserBankLogDAO bwUserBankLogDaoImpl) {
        this.bwUserBankLogDaoImpl = bwUserBankLogDaoImpl;
    }

    @Override
    public LinkedList<String> querySuccessOrder(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        String key = success_key + bwuserbanklogvo.getBoweiId() + "_" + bwuserbanklogvo.getAppId();
        return (LinkedList<String>) this.getCache().get(key);
    }

}
