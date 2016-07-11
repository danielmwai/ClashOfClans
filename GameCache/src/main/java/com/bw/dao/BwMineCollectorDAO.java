package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.exception.CacheDaoException;

public interface BwMineCollectorDAO {

    public final static String seq = "bw_mine_collector_id_seq";

    /**
     * @param BwMineCollectorVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwMineCollectorVO 根据id查询记录
     */
    public BwMineCollectorVO queryBwMineCollectorVOById(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorVO
     * @throws CacheDaoException
     * @return BwMineCollectorVO列表 根据更多条件查询 BwMineCollectorVO
     */
    public List<BwMineCollectorVO> queryBwMineCollectorVO(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwMineCollectorVO
     */
    public List<Long> queryBwMineCollectorVOIds(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwMineCollectorVO
     */
    public long queryBwMineCollectorVOCount(BwMineCollectorVO bwminecollectorvo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新金矿和药水
     */
    public void batchUpdate(List<BwMineCollectorVO> listtime1) throws CacheDaoException;
}
