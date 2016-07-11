package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwPlantUserVO;

import com.bw.exception.CacheDaoException;

public interface BwPlantUserDAO {

    public final static String seq = "bw_plant_user_id_seq";

    /**
     * @param BwPlantUserVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param BwPlantUserVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwPlantUserVO 根据id查询记录
     */
    public BwPlantUserVO queryBwPlantUserVOById(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param bwplantuservo
     * @return
     * @throws CacheDaoException 根据mailaddress获取plantUserInfor
     */
    public String queryBwPlantUserVOByMailAddress(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param BwPlantUserVO
     * @throws CacheDaoException
     * @return BwPlantUserVO列表 根据更多条件查询 BwPlantUserVO
     */
    public List<BwPlantUserVO> queryBwPlantUserVO(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param BwPlantUserVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwPlantUserVO
     */
    public List<Long> queryBwPlantUserVOIds(BwPlantUserVO bwplantuservo) throws CacheDaoException;

    /**
     * @param BwPlantUserVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwPlantUserVO
     */
    public long queryBwPlantUserVOCount(BwPlantUserVO bwplantuservo) throws CacheDaoException;
}
