package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwUserBankVO;
import com.bw.dao.BwUserBankDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBankCacheDAOImpl extends CacheDao implements BwUserBankDAO {

    public BwUserBankDAO bwUserBankDaoImpl;
    String prefix_single = "BW_USER_BANK_";

    public void delete(BwUserBankVO bwuserbankvo) throws CacheDaoException {
    }

    @Override
    public BwUserBankVO queryBwUserBankVOById(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {
        String key = prefix_single + bwuserbankvo.getBoweiId();
        Object o = this.getCache().get(key);
        if (null != o) {
            return (BwUserBankVO) o;
        } else {
            BwUserBankVO temp = bwUserBankDaoImpl.queryBwUserBankVOById(bwuserbankvo);
            if (temp != null) {
                this.getCache().put(key, temp);
                return temp;
            }
        }
        return null;
    }

    @Override
    public long queryBwUserBankVOCount(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserBankVOIds(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserBankVO bwuserbankvo) throws CacheDaoException {
        bwUserBankDaoImpl.save(bwuserbankvo);
        String key = prefix_single + bwuserbankvo.getBoweiId();
        this.getCache().put(key, bwuserbankvo);
    }

    @Override
    public void update(BwUserBankVO bwuserbankvo) throws CacheDaoException {
        bwUserBankDaoImpl.update(bwuserbankvo);
        String key = prefix_single + bwuserbankvo.getBoweiId();
        this.getCache().put(key, bwuserbankvo);
    }

    @Override
    public List<BwUserBankVO> queryBwUserBankVO(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {

        return null;
    }

    public BwUserBankDAO getBwUserBankDaoImpl() {
        return bwUserBankDaoImpl;
    }

    public void setBwUserBankDaoImpl(BwUserBankDAO bwUserBankDaoImpl) {
        this.bwUserBankDaoImpl = bwUserBankDaoImpl;
    }
}
