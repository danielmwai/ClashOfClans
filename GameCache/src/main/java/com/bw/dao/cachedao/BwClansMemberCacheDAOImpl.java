package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwClansMemberVO;
import com.bw.dao.BwClansMemberDAO;
import com.bw.exception.CacheDaoException;

public class BwClansMemberCacheDAOImpl extends CacheDao implements BwClansMemberDAO {

    public void delete(BwClansMemberVO bwclansmembervo) throws CacheDaoException {
    }

    @Override
    public BwClansMemberVO queryBwClansMemberVOById(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansMemberVOCount(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwClansMemberVOIds(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

    }

    @Override
    public void update(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

    }

    @Override
    public List<BwClansMemberVO> queryBwClansMemberVO(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return null;
    }
}
