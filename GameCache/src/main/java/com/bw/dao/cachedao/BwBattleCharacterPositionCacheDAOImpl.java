package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwBattleCharacterPositionVO;
import com.bw.dao.BwBattleCharacterPositionDAO;
import com.bw.exception.CacheDaoException;

public class BwBattleCharacterPositionCacheDAOImpl extends CacheDao implements
        BwBattleCharacterPositionDAO {

    private BwBattleCharacterPositionDAO bwClansCharacterRequestDaoImpl;

    public void delete(BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {
    }

    @Override
    public BwBattleCharacterPositionVO queryBwBattleCharacterPositionVOById(
            BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwBattleCharacterPositionVOCount(
            BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwBattleCharacterPositionVOIds(
            BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

    }

    @Override
    public void update(BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

    }

    @Override
    public List<BwBattleCharacterPositionVO> queryBwBattleCharacterPositionVO(
            BwBattleCharacterPositionVO bwbattlecharacterpositionvo)
            throws CacheDaoException {

        return null;
    }

    public BwBattleCharacterPositionDAO getBwClansCharacterRequestDaoImpl() {
        return bwClansCharacterRequestDaoImpl;
    }

    public void setBwClansCharacterRequestDaoImpl(
            BwBattleCharacterPositionDAO bwClansCharacterRequestDaoImpl) {
        this.bwClansCharacterRequestDaoImpl = bwClansCharacterRequestDaoImpl;
    }

}
