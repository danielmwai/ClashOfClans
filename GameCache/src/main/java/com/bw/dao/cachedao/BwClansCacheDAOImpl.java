package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwClansVO;
import com.bw.dao.BwClansDAO;
import com.bw.exception.CacheDaoException;

public class BwClansCacheDAOImpl extends CacheDao implements BwClansDAO {

    public void delete(BwClansVO bwclansvo) throws CacheDaoException {
    }

    @Override
    public BwClansVO queryBwClansVOById(BwClansVO bwclansvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansVOCount(BwClansVO bwclansvo) throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwClansVOIds(BwClansVO bwclansvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwClansVO bwclansvo) throws CacheDaoException {

    }

    @Override
    public void update(BwClansVO bwclansvo) throws CacheDaoException {

    }

    @Override
    public List<BwClansVO> queryBwClansVO(BwClansVO bwclansvo) throws CacheDaoException {

        return null;
    }
}
