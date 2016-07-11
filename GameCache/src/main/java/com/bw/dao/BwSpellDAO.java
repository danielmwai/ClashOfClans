package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwSpellVO;
import com.bw.exception.CacheDaoException;

public interface BwSpellDAO {

    public final static String seq = "bw_barrack_id_seq";

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwBarrackVO 根据id查询记录
     */
    public BwSpellVO queryBwSpellVOById(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return BwBarrackVO列表 根据更多条件查询 BwBarrackVO
     */
    public List<BwSpellVO> queryBwSpellVO(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwBarrackVO
     */
    public List<Long> queryBwSpellVOIds(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwBarrackVO
     */
    public long queryBwSpellVOCount(BwSpellVO bwSpellVO) throws CacheDaoException;

    /**
     * @param mailAddress
     * @return 获取该用户所有的兵营userMapId
     * @throws CacheDaoException
     *
     */
    public List<Long> getAllSpellUserMapId(String mailAddress) throws CacheDaoException;

    /**
     * @param mailAddress
     * @param userMapIdList
     * @throws CacheDaoException 把用户对应的兵营
     */
    public void putAllBarrackUserMapId(String mailAddress, List<Long> userMapIdList) throws CacheDaoException;
}
