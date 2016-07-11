package com.bw.dao;

import java.util.List;

import com.bw.baseJar.vo.BwCanclePersionVO;

import com.bw.exception.CacheDaoException;

public interface BwCanclePersionDAO {

    public final static String seq = "bw_cancle_persion_id_seq";

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwCanclePersionVO 根据id查询记录
     */
    public BwCanclePersionVO queryBwCanclePersionVOById(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException
     * @return BwCanclePersionVO列表 根据更多条件查询 BwCanclePersionVO
     */
    public List<BwCanclePersionVO> queryBwCanclePersionVO(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwCanclePersionVO
     */
    public List<Long> queryBwCanclePersionVOIds(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwCanclePersionVO
     */
    public long queryBwCanclePersionVOCount(BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException;

    /**
     * @param BwCanclePersionVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件批量更新数据库信息 BwCanclePersionVO
     */
    public void batchUpdate(List<BwCanclePersionVO> listtime1) throws CacheDaoException;
}
