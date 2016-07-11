package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.exception.CacheDaoException;

public interface BwUserBattleStatisticsDAO {

    public final static String seq = "bw_user_battle_statistics_id_seq";

    /**
     * @param BwUserBattleStatisticsVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param BwUserBattleStatisticsVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserBattleStatisticsVO 根据id查询记录
     */
    public BwUserBattleStatisticsVO queryBwUserBattleStatisticsVOById(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param BwUserBattleStatisticsVO
     * @throws CacheDaoException
     * @return BwUserBattleStatisticsVO列表 根据更多条件查询 BwUserBattleStatisticsVO
     */
    public List<BwUserBattleStatisticsVO> queryBwUserBattleStatisticsVO(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param BwUserBattleStatisticsVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserBattleStatisticsVO
     */
    public List<Long> queryBwUserBattleStatisticsVOIds(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param BwUserBattleStatisticsVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserBattleStatisticsVO
     */
    public long queryBwUserBattleStatisticsVOCount(BwUserBattleStatisticsVO bwuserbattlestatisticsvo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新战斗统计信息
     */
    public void batchUpdate(List<BwUserBattleStatisticsVO> listtime1) throws CacheDaoException;
}
