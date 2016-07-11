package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwClansCharacterRequestVO;
import com.bw.exception.CacheDaoException;

public interface BwClansCharacterRequestDAO {

    public final static String seq = "bw_clans_character_request_id_seq";

    /**
     * @param BwClansCharacterRequestVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param BwClansCharacterRequestVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwClansCharacterRequestVO 根据id查询记录
     */
    public BwClansCharacterRequestVO queryBwClansCharacterRequestVOById(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param BwClansCharacterRequestVO
     * @throws CacheDaoException
     * @return BwClansCharacterRequestVO列表 根据更多条件查询 BwClansCharacterRequestVO
     */
    public List<BwClansCharacterRequestVO> queryBwClansCharacterRequestVO(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param BwClansCharacterRequestVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwClansCharacterRequestVO
     */
    public List<Long> queryBwClansCharacterRequestVOIds(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;

    /**
     * @param BwClansCharacterRequestVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwClansCharacterRequestVO
     */
    public long queryBwClansCharacterRequestVOCount(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException;
}
