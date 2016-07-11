package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.CGUserVO;
import com.bw.exception.CacheDaoException;

public interface CGUserDAO {

    public final static String seq = "cg_user_id_seq";

    /**
     * 增加记录
     */
    public void register(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 初始化用户信息
     */
    public void initUserInfo(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 更新用户数据
     */
    public void updateUser(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 定时更新用户信息
     */
    public void refreshUserInfo(CGUserVO cgUserVO);

    /**
     * 更新用户赞币
     */
    public void refreshPraises(CGUserVO cgUserVO);

    /**
     * 根据ID删除用户数据
     */
    public void removeUser(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 根据ID删除用户信息
     */
    public void removeUserInfo(CGUserVO cgUserVO);

    /**
     * 根据MailAddress查询记录
     */
    public CGUserVO queryCGUserVOByMailAddress(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 根据ID查询记录
     */
    public CGUserVO queryCGUserVOById(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 根据昵称查询记录
     */
    public List<String> queryCGUserVOByNickName(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 更多条件查询
     */
    public List<CGUserVO> queryCGUserVO(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 更多条件统计用户数量
     */
    public long queryCGUserVOCount(CGUserVO cgUserVO) throws CacheDaoException;

    /**
     * 随机推荐
     */
    public List<String> randomRecommend(String mailAddress) throws CacheDaoException;

}
