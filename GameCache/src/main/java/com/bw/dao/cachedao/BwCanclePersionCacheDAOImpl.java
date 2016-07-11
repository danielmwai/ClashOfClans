package com.bw.dao.cachedao;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Comparator;

import java.util.List;

import com.bw.dao.BwCanclePersionDAO;
import com.bw.baseJar.vo.BwCanclePersionVO;
import com.bw.exception.CacheDaoException;
import com.bw.dao.cachedao.CacheDao;

/**
 * @author Administrator 销毁用户数据
 *
 */
public class BwCanclePersionCacheDAOImpl extends CacheDao implements
        BwCanclePersionDAO {

    public void delete(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {
    }

    @Override
    public BwCanclePersionVO queryBwCanclePersionVOById(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwCanclePersionVOCount(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwCanclePersionVOIds(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {

    }

    @Override
    public void update(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {

    }

    @Override
    public List<BwCanclePersionVO> queryBwCanclePersionVO(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        return null;

    }

    @Override
    public void batchUpdate(List<BwCanclePersionVO> listtime1)
            throws CacheDaoException {

    }

}
