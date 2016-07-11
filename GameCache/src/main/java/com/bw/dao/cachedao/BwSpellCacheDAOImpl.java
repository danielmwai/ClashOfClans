package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwSpellVO;
import com.bw.dao.BwSpellDAO;
import com.bw.dao.springdao.BwBarrackDaoImpl;
import com.bw.exception.CacheDaoException;

public class BwSpellCacheDAOImpl extends CacheDao implements BwSpellDAO {

    String key_prefix_single = "bw_spell";
    String key_prefix_multiable = "bw_multiable_spell";

    private BwSpellDAO bwSpellDaoImpl;

    public BwSpellDAO getBwSpellDaoImpl() {
        return bwSpellDaoImpl;
    }

    public void setBwSpellDaoImpl(BwSpellDAO bwSpellDaoImpl) {
        this.bwSpellDaoImpl = bwSpellDaoImpl;
    }

    @Override
    public void delete(BwSpellVO bwSpellVO) throws CacheDaoException {
        bwSpellDaoImpl.delete(bwSpellVO);
        String key = key_prefix_single + bwSpellVO.getUsermapdataid() + "_" + bwSpellVO.getSpelltypeid();
        this.getCache().remove(key);
    }

    @Override
    public List<BwSpellVO> queryBwSpellVO(BwSpellVO bwSpellVO)
            throws CacheDaoException {

        return bwSpellDaoImpl.queryBwSpellVO(bwSpellVO);
    }

    @Override
    public BwSpellVO queryBwSpellVOById(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        String key = key_prefix_single + bwSpellVO.getMailaddress() + "_" + bwSpellVO.getSpelltypeid();
        Object o = this.getCache().get(key);
        if (o != null) {
            return (BwSpellVO) o;
        } else {
            BwSpellVO tempVO = bwSpellDaoImpl.queryBwSpellVOById(bwSpellVO);
            if (null != tempVO) {
                this.getCache().put(key, tempVO);
                return tempVO;
            }
        }
        return null;
    }

    @Override
    public void save(BwSpellVO bwSpellVO) throws CacheDaoException {
        bwSpellDaoImpl.save(bwSpellVO);
        String keySing_save = key_prefix_single + bwSpellVO.getMailaddress() + "_" + bwSpellVO.getSpelltypeid();
        this.getCache().put(keySing_save, bwSpellVO);
    }

    @Override
    public void update(BwSpellVO bwSpellVO) throws CacheDaoException {
        bwSpellDaoImpl.update(bwSpellVO);
        String keySingle = key_prefix_single + bwSpellVO.getMailaddress() + "_" + bwSpellVO.getSpelltypeid();
        this.getCache().put(keySingle, bwSpellVO);
    }

    @Override
    public List<Long> getAllSpellUserMapId(String mailAddress)
            throws CacheDaoException {
        return null;
    }

    @Override
    public void putAllBarrackUserMapId(String mailAddress,
            List<Long> userMapIdList) throws CacheDaoException {
        // TODO Auto-generated method stub
    }

    @Override
    public long queryBwSpellVOCount(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Long> queryBwSpellVOIds(BwSpellVO bwSpellVO)
            throws CacheDaoException {

        return null;
    }
}
