package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwClansMemberVO;
import com.bw.exception.CacheDaoException;

public interface BwClansMemberDAO {

    public final static String seq = "bw_clans_member_id_seq";

    /**
     * @param BwClansMemberVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param BwClansMemberVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwClansMemberVO 根据id查询记录
     */
    public BwClansMemberVO queryBwClansMemberVOById(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param BwClansMemberVO
     * @throws CacheDaoException
     * @return BwClansMemberVO列表 根据更多条件查询 BwClansMemberVO
     */
    public List<BwClansMemberVO> queryBwClansMemberVO(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param BwClansMemberVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwClansMemberVO
     */
    public List<Long> queryBwClansMemberVOIds(BwClansMemberVO bwclansmembervo) throws CacheDaoException;

    /**
     * @param BwClansMemberVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwClansMemberVO
     */
    public long queryBwClansMemberVOCount(BwClansMemberVO bwclansmembervo) throws CacheDaoException;
}
