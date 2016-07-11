package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.dao.BwMineCollectorAllDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator
 *
 */
public class BwMineCollectorAllCacheDAOImpl extends CacheDao implements
        BwMineCollectorAllDAO {

    public BwMineCollectorAllDAO bwMineCollectorAllDaoImpl;
    public String key_prefix_single = "bw_mine_collectorall_";

    public BwMineCollectorAllDAO getBwMineCollectorAllDaoImpl() {
        return bwMineCollectorAllDaoImpl;
    }

    public void setBwMineCollectorAllDaoImpl(
            BwMineCollectorAllDAO bwMineCollectorAllDaoImpl) {
        this.bwMineCollectorAllDaoImpl = bwMineCollectorAllDaoImpl;
    }

    public void delete(BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
    }

    @Override
    public BwMineCollectorAllVO queryBwMineCollectorAllVOById(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {
        String key = key_prefix_single + bwminecollectorallvo.getUserbuildingdataid();
        Object o = this.getCache().get(key);
        if (null != o) {
            return (BwMineCollectorAllVO) o;
        } else {
            BwMineCollectorAllVO t = bwMineCollectorAllDaoImpl.queryBwMineCollectorAllVOById(bwminecollectorallvo);
            if (t != null) {
                this.getCache().put(key, t);
                return t;
            } else {
                return null;
            }
        }

    }

    @Override
    public long queryBwMineCollectorAllVOCount(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwMineCollectorAllVOIds(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
        String part_key_save = String.valueOf(bwminecollectorallvo.getUserbuildingdataid());
        bwMineCollectorAllDaoImpl.save(bwminecollectorallvo);
        String key = key_prefix_single + part_key_save;
        this.getCache().put(key, bwminecollectorallvo);

    }

    @Override
    public void update(BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
        this.getCache().put(key_prefix_single, bwminecollectorallvo);
        bwMineCollectorAllDaoImpl.update(bwminecollectorallvo);
    }

    @Override
    public List<BwMineCollectorAllVO> queryBwMineCollectorAllVO(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void batchUpdate(List<BwMineCollectorAllVO> listtime1)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }
}
