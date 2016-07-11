package com.common.BaseInfor.DAO;

import com.bw.baseJar.vo.BwAreaVO;
import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBankGemVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwFileServerVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.baseJar.vo.BwHallBuildsRelationVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwSpellPropertiesLevelVO;
import com.bw.baseJar.vo.BwSpellVOSource;
import com.bw.baseJar.vo.BwTreasureVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.bw.baseJar.vo.BwWorkerVO;
import com.bw.exception.CacheDaoException;

import java.util.List;

/**
 * @author dennyzhao
 */
public interface DBBaseInforDAO {

    /**
     * @return @throws CacheDaoException 获取服务器列表
     */
    public List<BwGameChannleVO> getGameServerChannleList() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取建筑的基础信息
     */
    public List<BwBuildingVO> queryBwBuildingVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取文件服务器列表
     */
    public List<BwFileServerVO> queryBwFileServerVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取建筑的等级信息
     */
    public List<BwBuildingPropertiesLevelVO> queryBwBuildingPropertiesLevelVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取基础角色信息
     */
    public List<BwCharactersVO> queryBwCharactersVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取基础角色等级信息
     */
    public List<BwCharactersPropertiesLevelVO> queryBwCharactersPropertiesLevelVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取角色升级信息
     */
    public List<BwUserLevelReqVO> queryBwUserLevelReqVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 大厅对应建筑关系表
     */
    List<BwHallBuildsRelationVO> queryBwHallBuildsRelationVO() throws CacheDaoException;

    /**
     * 魔法表
     *
     * @return
     * @throws CacheDaoException
     */
    public List<BwSpellPropertiesLevelVO> queryBwSpellPropertiesLevelVO() throws CacheDaoException;

    /**
     * 魔法资源表
     *
     * @return
     * @throws CacheDaoException
     */
    public List<BwSpellVOSource> queryBwSpellVO() throws CacheDaoException;

    /**
     * 初始化用户数据表
     */
    public BwInitUserVO queryBwInitUserVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException
     */
    public List<BwBankGemVO> queryBwBankGemVO() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException
     */
    public List<BwExchangeVO> queryBwExchanges() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException
     */
    public List<BwQuickenVO> queryBwQuickens() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException
     */
    public List<BwTreasureVO> queryBwTreasures() throws CacheDaoException;

    /**
     * @param bwclvo
     * @return
     * @throws CacheDaoException 根据兵力ID获取对应的兵力等级信息
     */
    public List<BwCharactersPropertiesLevelVO> queryBwCharactersPropertiesLevelVOByCharactarId(BwCharactersPropertiesLevelVO bwclvo) throws CacheDaoException;

    /**
     * @param bwblevelvo
     * @return
     * @throws CacheDaoException 根据建筑ID获取对应的建筑等级信息
     */
    public List<BwBuildingPropertiesLevelVO> queryBwBuildingPropertiesLevelVOByBuildingId(BwBuildingPropertiesLevelVO bwblevelvo) throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取农民屋基础信息
     */
    public List<BwWorkerVO> queryBwWorkerVOList() throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 获取区域列表
     */
    public List<BwAreaVO> queryBwAreaVOList() throws CacheDaoException;

    /**
     * @param areaId
     * @return
     * @throws CacheDaoException 根据区域ID获取服务器列表
     */
    public List<BwGameChannleVO> getGameServerChannleList(long areaId) throws CacheDaoException;

    /**
     * 查询返回服务器常量
     *
     * @return
     */
    List<BwArgsVO> getArgs();
}
