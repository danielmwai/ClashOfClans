package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserBankVO;

import com.bw.exception.CacheDaoException;

public interface BwUserBankDAO {

    public final static String seq = "bw_user_bank_id_seq";

    /**
     * @param BwUserBankVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param BwUserBankVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserBankVO 根据id查询记录
     */
    public BwUserBankVO queryBwUserBankVOById(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param BwUserBankVO
     * @throws CacheDaoException
     * @return BwUserBankVO列表 根据更多条件查询 BwUserBankVO
     */
    public List<BwUserBankVO> queryBwUserBankVO(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param BwUserBankVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserBankVO
     */
    public List<Long> queryBwUserBankVOIds(BwUserBankVO bwuserbankvo) throws CacheDaoException;

    /**
     * @param BwUserBankVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserBankVO
     */
    public long queryBwUserBankVOCount(BwUserBankVO bwuserbankvo) throws CacheDaoException;
}
