package com.bw.dao;

import java.util.List;

import com.bw.baseJar.vo.StopServerMessageVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.cache.vo.PVPGradeOrderVO;
import com.bw.exception.CacheDaoException;

public interface BwUserDAO {

    public final static String seq = "bw_user_id_seq";

    /**
     * @param BwUserVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param BwUserVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserVO 根据id查询记录
     */
    public BwUserVO queryBwUserVOById(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param BwUserVO
     * @throws CacheDaoException
     * @return BwUserVO列表 根据更多条件查询 BwUserVO
     */
    public List<BwUserVO> queryBwUserVO(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param BwUserVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserVO
     */
    public List<Long> queryBwUserVOIds(BwUserVO bwuservo) throws CacheDaoException;

    /**
     * @param BwUserVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserVO
     */
    public long queryBwUserVOCount(BwUserVO bwuservo) throws CacheDaoException;
///**
//* @param BwUserVO
//* @throws CacheDaoException
//* 更新worker 正在使用的数量
//*/
//public void updateWorkCount(BwUserVO bwuservo)throws CacheDaoException;
///**
//* @param BwUserVO
//* @throws CacheDaoException
//* 获取worker数量
//*/
//public int getWorkCount(BwUserVO bwuservo)throws CacheDaoException;

    public void batchUpdate(List<BwUserVO> listtime1) throws CacheDaoException;

    public StopServerMessageVO getServerStatus() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取用户的pvp分数排名
     */
    public List<PVPGradeOrderVO> getPVPGradeOrder(PVPGradeOrderVO pVPGradeOrderVO) throws CacheDaoException;

    /**
     * @param pvpgrade
     * @return
     * @throws CacheDaoException 根据pvp分数获取pvp等级
     */
    public long getPVPGradeOrderByGrade(long pvpgrade) throws CacheDaoException;

    /**
     * @throws CacheDaoException 设置前200名pvp分数排名
     */
    public void setPVPGradeOrder() throws CacheDaoException;
}
