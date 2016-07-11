package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserFriendsVO;
import com.bw.exception.CacheDaoException;

public interface BwUserFriendsDAO {

    public final static String seq = "bw_user_friends_id_seq";

    /**
     * @param BwUserFriendsVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param BwUserFriendsVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserFriendsVO 根据id查询记录
     */
    public BwUserFriendsVO queryBwUserFriendsVOById(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param BwUserFriendsVO
     * @throws CacheDaoException
     * @return BwUserFriendsVO列表 根据更多条件查询 BwUserFriendsVO
     */
    public List<BwUserFriendsVO> queryBwUserFriendsVO(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param BwUserFriendsVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserFriendsVO
     */
    public List<Long> queryBwUserFriendsVOIds(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;

    /**
     * @param BwUserFriendsVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserFriendsVO
     */
    public long queryBwUserFriendsVOCount(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException;
}
