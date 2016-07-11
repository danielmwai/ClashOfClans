package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.exception.CacheDaoException;

public interface BwBarrackDAO {

    public final static String seq = "bw_barrack_id_seq";

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException 增加记录
     */
    public void save(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException 更新记录
     */
    public void update(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException 根据id删除记录
     */
    public void delete(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param id
     * @throws CacheDaoException
     * @return BwBarrackVO 根据id查询记录
     */
    public BwBarrackVO queryBwBarrackVOById(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return BwBarrackVO列表 根据更多条件查询 BwBarrackVO
     */
    public List<BwBarrackVO> queryBwBarrackVO(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return id列表 根据更多条件查询 BwBarrackVO
     */
    public List<Long> queryBwBarrackVOIds(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException
     * @return 记录个数 根据相应条件查询表记录数量 BwBarrackVO
     */
    public long queryBwBarrackVOCount(BwBarrackVO bwbarrackvo) throws CacheDaoException;

    /**
     * @param mailAddress
     * @return 获取该用户所有的兵营userMapId
     * @throws CacheDaoException
     *
     */
    public List<Long> getAllBarrackUserMapId(String mailAddress) throws CacheDaoException;

    /**
     * @param mailAddress
     * @param userMapIdList
     * @throws CacheDaoException 把用户对应的兵营
     */
    public void putAllBarrackUserMapId(String mailAddress, List<Long> userMapIdList) throws CacheDaoException;

    /**
     * @param BwBarrackVO
     * @throws CacheDaoException 批量更新记录
     */
    public void batchUpdate(List<BwBarrackVO> bwbarrackvolist) throws CacheDaoException;

    /**
     * @param bwbarrackvo
     * @return
     * @throws CacheDaoException 获取每个兵营所有的charactar_id
     */
    public List<Long> queryBwBarrackVOForCharactarId(BwBarrackVO bwbarrackvo) throws CacheDaoException;
}
