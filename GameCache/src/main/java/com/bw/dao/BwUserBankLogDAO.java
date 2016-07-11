package com.bw.dao;

import java.util.LinkedList;
import java.util.List;

import com.bw.cache.vo.BwUserBankLogVO;

import com.bw.exception.CacheDaoException;

public interface BwUserBankLogDAO {

    public final static String seq = "bw_user_bank_log_id_seq";

    /**
     * @param BwUserBankLogVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param BwUserBankLogVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserBankLogVO 根据id查询记录
     */
    public BwUserBankLogVO queryBwUserBankLogVOById(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param BwUserBankLogVO
     * @throws CacheDaoException
     * @return BwUserBankLogVO列表 根据更多条件查询 BwUserBankLogVO
     */
    public List<BwUserBankLogVO> queryBwUserBankLogVO(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param BwUserBankLogVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserBankLogVO
     */
    public List<Long> queryBwUserBankLogVOIds(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param bwuserbanklogvo
     * @return
     * @throws CacheDaoException 查询成功的订单
     */
    public LinkedList<String> querySuccessOrder(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;

    /**
     * @param BwUserBankLogVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserBankLogVO
     */
    public long queryBwUserBankLogVOCount(BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException;
}
