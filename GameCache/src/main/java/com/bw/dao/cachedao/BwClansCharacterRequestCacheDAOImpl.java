package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwClansCharacterRequestVO;
import com.bw.dao.BwClansCharacterRequestDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 部落联盟请求逻辑
 */
public class BwClansCharacterRequestCacheDAOImpl extends CacheDao implements
        BwClansCharacterRequestDAO {

    private BwClansCharacterRequestDAO bwClansCharacterRequestDaoImpl;

    String key_prefix_request = "bw_clans_character_request_";

    String key_prefix_reply = "bw_clans_character_reply_";

    public void delete(BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {
    }

    @Override
    public BwClansCharacterRequestVO queryBwClansCharacterRequestVOById(
            BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansCharacterRequestVOCount(
            BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwClansCharacterRequestVOIds(
            BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {

    }

    @Override
    public void update(BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwClansCharacterRequestVO> queryBwClansCharacterRequestVO(
            BwClansCharacterRequestVO bwclanscharacterrequestvo)
            throws CacheDaoException {
        String key = key_prefix_reply + bwclanscharacterrequestvo.getRequestmailaddress();
        Object o = this.getCache().get(key);
        if (o != null) {
            return (List<BwClansCharacterRequestVO>) o;
        }
        return null;
    }

    public BwClansCharacterRequestDAO getBwClansCharacterRequestDaoImpl() {
        return bwClansCharacterRequestDaoImpl;
    }

    public void setBwClansCharacterRequestDaoImpl(
            BwClansCharacterRequestDAO bwClansCharacterRequestDaoImpl) {
        this.bwClansCharacterRequestDaoImpl = bwClansCharacterRequestDaoImpl;
    }

}
