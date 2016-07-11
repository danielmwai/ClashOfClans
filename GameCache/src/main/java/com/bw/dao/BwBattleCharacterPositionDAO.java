package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwBattleCharacterPositionVO;
import com.bw.exception.CacheDaoException;

public interface BwBattleCharacterPositionDAO {

    public final static String seq = "bw_battle_character_position_id_seq";

    /**
     * @param BwBattleCharacterPositionVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param BwBattleCharacterPositionVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwBattleCharacterPositionVO 根据id查询记录
     */
    public BwBattleCharacterPositionVO queryBwBattleCharacterPositionVOById(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param BwBattleCharacterPositionVO
     * @throws CacheDaoException
     * @return BwBattleCharacterPositionVO列表 根据更多条件查询
     * BwBattleCharacterPositionVO
     */
    public List<BwBattleCharacterPositionVO> queryBwBattleCharacterPositionVO(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param BwBattleCharacterPositionVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwBattleCharacterPositionVO
     */
    public List<Long> queryBwBattleCharacterPositionVOIds(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;

    /**
     * @param BwBattleCharacterPositionVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwBattleCharacterPositionVO
     */
    public long queryBwBattleCharacterPositionVOCount(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException;
}
