package com.bw.dao;

import java.util.LinkedList;
import java.util.List;

import com.bw.cache.vo.BwBattleDestoryVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.exception.CacheDaoException;

public interface BwBattleDAO {

    public final static String seq = "bw_battle_id_seq";

    /**
     * @param BwBattleVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param bwBattledListVO
     * @throws CacheDaoException 保存战斗兵力使用情况
     */
    public void saveBattledList(String defencerMailAddress, BwBattleVO bwBattledListVO) throws CacheDaoException;

    /**
     * @param bwBattleDestoryVO
     * @throws CacheDaoException 战斗摧毁情况vo
     */
    public void updateBattleDestory(String mailAddress, List<BwBattleDestoryVO> bwBattleDestoryVOList) throws CacheDaoException;

    /**
     * @param BwBattleVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param bwbattlevoList
     * @throws CacheDaoException 批量更新战斗信息
     */
    public void batchUpdate(List<BwBattleVO> bwbattlevoList) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param mailAddress
     * @throws CacheDaoException 删除战斗被摧毁的信息
     */
    public void deleteBattleDestory(String mailAddress) throws CacheDaoException;

    /**
     * @param mailAddress
     * @throws CacheDaoException 删除被攻击者的战斗列表
     */
    public void deleteBwBattledListVO(String mailAddress) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwBattleVO 根据id查询记录
     */
    public BwBattleVO queryBwBattleVOById(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param BwBattleVO
     * @throws CacheDaoException
     * @return BwBattleVO列表 根据更多条件查询 BwBattleVO
     */
    public LinkedList<BwBattleVO> queryBwBattleVO(String mailAddress) throws CacheDaoException;

    /**
     * @param mailAddress
     * @return
     * @throws CacheDaoException 查询用户的建筑摧毁记录
     */
    public List<BwBattleDestoryVO> queryBwBattleDestoryVO(String mailAddress) throws CacheDaoException;

    /**
     * @param BwBattleVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwBattleVO
     */
    public List<Long> queryBwBattleVOIds(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param BwBattleVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwBattleVO
     */
    public long queryBwBattleVOCount(BwBattleVO bwbattlevo) throws CacheDaoException;

    /**
     * @param mailAddress
     * @return
     * @throws CacheDaoException 查询战斗数量标记
     */
    public long queryBwBattleVOCountFlag(String mailAddress) throws CacheDaoException;

    /**
     * @param mailAddress
     * @param count
     * @throws CacheDaoException 更新战斗数量标记
     */
    public void updateBwBattleVOCountFlag(String mailAddress, int count) throws CacheDaoException;
}
