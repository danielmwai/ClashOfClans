package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwUserFriendsVO;
import com.bw.dao.BwUserFriendsDAO;
import com.bw.exception.CacheDaoException;

public class BwUserFriendsCacheDAOImpl extends CacheDao implements BwUserFriendsDAO {

    public void delete(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {
    }

    @Override
    public BwUserFriendsVO queryBwUserFriendsVOById(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwUserFriendsVOCount(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserFriendsVOIds(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

    }

    @Override
    public void update(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

    }

    @Override
    public List<BwUserFriendsVO> queryBwUserFriendsVO(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return null;
    }
}
