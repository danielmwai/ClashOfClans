package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.dao.BwUserBattleStatisticsDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBattleStatisticsCacheDAOImpl extends CacheDao implements
        BwUserBattleStatisticsDAO {

    public BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl;
    String key_prefix = "BW_USER_BATTLE_STATISTICS_";

    public void delete(BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
    }

    @Override
    public BwUserBattleStatisticsVO queryBwUserBattleStatisticsVOById(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        String key = key_prefix + bwuserbattlestatisticsvo.getMailaddress();
        Object o = this.getCache().get(key);
        if (null != o) {
            return (BwUserBattleStatisticsVO) o;
        } else {
            BwUserBattleStatisticsVO t = bwUserBattleStatisticsDaoImpl.queryBwUserBattleStatisticsVOById(bwuserbattlestatisticsvo);
            if (null != t) {
                this.getCache().put(key, t);
                return t;
            }
        }
        return null;
    }

    @Override
    public long queryBwUserBattleStatisticsVOCount(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserBattleStatisticsVOIds(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        bwUserBattleStatisticsDaoImpl.save(bwuserbattlestatisticsvo);
        String key = key_prefix + bwuserbattlestatisticsvo.getMailaddress();
        this.getCache().put(key, bwuserbattlestatisticsvo);
    }

    @Override
    public void update(BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        // bwUserBattleStatisticsDaoImpl.update(bwuserbattlestatisticsvo);
        String key = key_prefix + bwuserbattlestatisticsvo.getMailaddress();
        this.getCache().put(key, bwuserbattlestatisticsvo);
    }

    @Override
    public List<BwUserBattleStatisticsVO> queryBwUserBattleStatisticsVO(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {

        return null;
    }

    public BwUserBattleStatisticsDAO getBwUserBattleStatisticsDaoImpl() {
        return bwUserBattleStatisticsDaoImpl;
    }

    public void setBwUserBattleStatisticsDaoImpl(
            BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl) {
        this.bwUserBattleStatisticsDaoImpl = bwUserBattleStatisticsDaoImpl;
    }

    @Override
    public void batchUpdate(List<BwUserBattleStatisticsVO> listtime1)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

}
