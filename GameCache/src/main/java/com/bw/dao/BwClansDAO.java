package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwClansVO;
import com.bw.exception.CacheDaoException;

public interface BwClansDAO {

    public final static String seq = "bw_clans_id_seq";

    /**
     * @param BwClansVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param BwClansVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwClansVO 根据id查询记录
     */
    public BwClansVO queryBwClansVOById(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param BwClansVO
     * @throws CacheDaoException
     * @return BwClansVO列表 根据更多条件查询 BwClansVO
     */
    public List<BwClansVO> queryBwClansVO(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param BwClansVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwClansVO
     */
    public List<Long> queryBwClansVOIds(BwClansVO bwclansvo) throws CacheDaoException;

    /**
     * @param BwClansVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwClansVO
     */
    public long queryBwClansVOCount(BwClansVO bwclansvo) throws CacheDaoException;
}
