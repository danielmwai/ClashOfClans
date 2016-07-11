package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.dao.BwMineCollectorDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 金矿和药水收集单位
 */
public class BwMineCollectorCacheDAOImpl extends CacheDao implements
        BwMineCollectorDAO {

    public BwMineCollectorDAO bwMineCollectorDaoImpl;
    public String key_prefix_single = "BW_MINE_COLLECTOR_";

    public void delete(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
    }

    @Override
    public BwMineCollectorVO queryBwMineCollectorVOById(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {
        String key = key_prefix_single + bwminecollectorvo.getUserbuildingdataid();
        Object o = this.getCache().get(key);
        if (null != o) {
            return (BwMineCollectorVO) o;
        } else {
            BwMineCollectorVO t = bwMineCollectorDaoImpl.queryBwMineCollectorVOById(bwminecollectorvo);
            if (t != null) {
                this.getCache().put(key, t);
                return t;
            } else {
                return null;
            }
        }
    }

    @Override
    public long queryBwMineCollectorVOCount(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwMineCollectorVOIds(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
        bwMineCollectorDaoImpl.save(bwminecollectorvo);
        String key = key_prefix_single + bwminecollectorvo.getUserbuildingdataid();
        this.getCache().put(key, bwminecollectorvo);

    }

    @Override
    public void update(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
        //bwMineCollectorDaoImpl.update(bwminecollectorvo);
        String key = key_prefix_single + bwminecollectorvo.getUserbuildingdataid();
        this.getCache().put(key, bwminecollectorvo);

    }

    @Override
    public List<BwMineCollectorVO> queryBwMineCollectorVO(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {

        return null;
    }

    public BwMineCollectorDAO getBwMineCollectorDaoImpl() {
        return bwMineCollectorDaoImpl;
    }

    public void setBwMineCollectorDaoImpl(BwMineCollectorDAO bwMineCollectorDaoImpl) {
        this.bwMineCollectorDaoImpl = bwMineCollectorDaoImpl;
    }

    @Override
    public void batchUpdate(List<BwMineCollectorVO> listtime1)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

}
