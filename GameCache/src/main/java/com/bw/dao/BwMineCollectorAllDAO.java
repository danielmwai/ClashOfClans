package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.exception.CacheDaoException;

public interface BwMineCollectorAllDAO {

    public final static String seq = "bw_mine_collector_all_id_seq";

    /**
     * @param BwMineCollectorAllVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorAllVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwMineCollectorAllVO 根据id查询记录
     */
    public BwMineCollectorAllVO queryBwMineCollectorAllVOById(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorAllVO
     * @throws CacheDaoException
     * @return BwMineCollectorAllVO列表 根据更多条件查询 BwMineCollectorAllVO
     */
    public List<BwMineCollectorAllVO> queryBwMineCollectorAllVO(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorAllVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwMineCollectorAllVO
     */
    public List<Long> queryBwMineCollectorAllVOIds(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param BwMineCollectorAllVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwMineCollectorAllVO
     */
    public long queryBwMineCollectorAllVOCount(BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新BwMineCollectorAllVO
     */
    public void batchUpdate(List<BwMineCollectorAllVO> listtime1) throws CacheDaoException;
}
