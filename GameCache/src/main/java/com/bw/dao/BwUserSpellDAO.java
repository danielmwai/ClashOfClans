package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserSpellVO;
import com.bw.exception.CacheDaoException;

public interface BwUserSpellDAO {

    public final static String seq = "bw_user_spell_id_seq";

    /**
     * @param BwUserSpellVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param BwUserSpellVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserSpellVO 根据id查询记录
     */
    public BwUserSpellVO queryBwUserSpellVOById(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param BwUserSpellVO
     * @throws CacheDaoException
     * @return BwUserSpellVO列表 根据更多条件查询 BwUserSpellVO
     */
    public List<BwUserSpellVO> queryBwUserSpellVO(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param BwUserSpellVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserSpellVO
     */
    public List<Long> queryBwUserSpellVOIds(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param BwUserSpellVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserSpellVO
     */
    public long queryBwUserSpellVOCount(BwUserSpellVO bwuserspellvo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新用户魔法信息
     */
    public void batchUpdate(List<BwUserSpellVO> listtime1) throws CacheDaoException;
}
