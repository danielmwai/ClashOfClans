package com.bw.dao;

import com.bw.cache.vo.BwBlacklistVO;
import com.bw.exception.CacheDaoException;

import java.util.List;

/**
 * @author zhYou
 */
public interface BwBlacklistDAO {

    /**
     * 添加删号数据
     *
     * @param blacklist
     * @return
     */
    public long insert(BwBlacklistVO blacklist) throws CacheDaoException;

    /**
     * 根据boweiId查询黑名单对象
     *
     * @param boweiId
     * @return
     */
    public BwBlacklistVO select(String boweiId) throws CacheDaoException;

    /**
     * @return 查询所有删号记录
     */
    public List<BwBlacklistVO> selectAll() throws CacheDaoException;

    /**
     * 更新黑名单信息
     *
     * @param blacklist
     */
    public void update(BwBlacklistVO blacklist) throws CacheDaoException;

    /**
     * 根据boweiId删除黑名单记录
     *
     * @param boweiId
     */
    void delete(String boweiId) throws CacheDaoException;

    public List<String> queryBoweiIdList() throws CacheDaoException;
}
