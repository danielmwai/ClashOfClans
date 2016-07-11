package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.exception.CacheDaoException;

public interface BwUserCharacterDAO {

    public final static String seq = "bw_user_character_id_seq";

    /**
     * @param BwUserCharacterVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param BwUserCharacterVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwUserCharacterVO 根据id查询记录
     */
    public BwUserCharacterVO queryBwUserCharacterVOById(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param BwUserCharacterVO
     * @throws CacheDaoException
     * @return BwUserCharacterVO列表 根据更多条件查询 BwUserCharacterVO
     */
    public List<BwUserCharacterVO> queryBwUserCharacterVO(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param BwUserCharacterVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwUserCharacterVO
     */
    public List<Long> queryBwUserCharacterVOIds(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param BwUserCharacterVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwUserCharacterVO
     */
    public long queryBwUserCharacterVOCount(BwUserCharacterVO bwusercharactervo) throws CacheDaoException;

    /**
     * @param listtime1
     * @throws CacheDaoException 批量更新用户兵力信息
     */
    public void batchUpdate(List<BwUserCharacterVO> listtime1) throws CacheDaoException;

}
