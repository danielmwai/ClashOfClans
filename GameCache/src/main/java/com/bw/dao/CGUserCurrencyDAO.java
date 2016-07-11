package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.CGUserCurrencyVO;
import com.bw.exception.CacheDaoException;

public interface CGUserCurrencyDAO {

    public final static String seq = "cg_user_currency_id_seq";

    /**
     * 增加记录
     */
    public void save(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException;

    /**
     * 更新记录
     */
    public void update(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException;

    /**
     * 根据id删除记录
     */
    public void delete(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException;

    /**
     * 根据id查询记录
     */
    public CGUserCurrencyVO queryCGUserCurrencyVOById(
            CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException;

    /**
     * 列表 根据更多条件查询
     */
    public List<CGUserCurrencyVO> queryCGUserCurrencyVO(
            CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException;

    /**
     * 列表 根据更多条件查询
     */
    public List<Long> queryCGUserCurrencyVOIds(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException;

    /**
     * 记录个数 根据相应条件查询表记录数量
     */
    public long queryCGUserCurrencyVOCount(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException;
}
