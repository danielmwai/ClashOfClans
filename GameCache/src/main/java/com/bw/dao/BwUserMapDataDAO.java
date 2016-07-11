package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.exception.CacheDaoException;

public interface BwUserMapDataDAO {

    public final static String seq = "bw_user_map_data_id_seq";

    /**
     * @param BwUserMapDataVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param BwUserMapDataVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserMapDataVO 根据id查询记录
     */
    public BwUserMapDataVO queryBwUserMapDataVOById(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param BwUserMapDataVO
     * @throws CacheDaoException
     * @return BwUserMapDataVO列表 根据更多条件查询 BwUserMapDataVO
     */
    public List<BwUserMapDataVO> queryBwUserMapDataVO(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param BwUserMapDataVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserMapDataVO
     */
    public List<Long> queryBwUserMapDataVOIds(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param BwUserMapDataVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserMapDataVO
     */
    public long queryBwUserMapDataVOCount(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param bwusermapdatavo mailAddress ,buildId
     * @return
     * @throws CacheDaoException 查询用户每种建筑的数量
     */
    public int queryUserBuildCount(String mailAddress, int buildId) throws CacheDaoException;

    /**
     * @param mailAddress
     * @param buildMap 保存用户建筑数量
     * @throws CacheDaoException
     */
    public void updateUserBuildCount(String mailAddress, int buildId, int buildCount) throws CacheDaoException;

    /**
     *
     * @param bwusermapdatavo
     * @return根据uuid和mailAddress得到ID
     * @throws CacheDaoException
     */
//public Long queryIdByUuIdAndMaiAddress(String mailAddress,int uuId) throws CacheDaoException;
    public List<BwUserMapDataVO> queryBwUserMapDataVOIdsForUUID(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新用户地图信息
     */
    public void batchUpdate(List<BwUserMapDataVO> listtime1) throws CacheDaoException;
}
