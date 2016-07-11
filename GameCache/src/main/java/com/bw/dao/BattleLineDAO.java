package com.bw.dao;

import com.bw.cache.vo.BattleLineVO;
import com.bw.exception.CacheDaoException;

public interface BattleLineDAO {

    public BattleLineVO getBattleLineVO(String key) throws CacheDaoException;

    /**
     * @param blvo
     * @return
     * @throws CacheDaoException
     */
    public boolean updateBattleLineVO(BattleLineVO blvo) throws CacheDaoException;

    /**
     * @param blvo
     * @param waitTimes 重复次数
     * @return
     * @throws CacheDaoException
     */
    public boolean updateBattleLineVOWaitTimes(BattleLineVO blvo, int waitTimes) throws CacheDaoException;

    /**
     * @param blvo
     * @return
     * @throws CacheDaoException 直接set
     */
    public boolean updateBattleLineVONoCas(BattleLineVO blvo) throws CacheDaoException;
}
