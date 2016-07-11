package com.bw.application.resourceManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.bw.application.config.AppConfig;
import com.bw.application.utils.MD5Util;
import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.baseJar.vo.BwHallBuildsRelationVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwSpellPropertiesLevelVO;
import com.bw.baseJar.vo.BwSpellVOSource;
import com.bw.baseJar.vo.BwTreasureVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.bw.common.ResourceType;
import com.common.BaseInfor.DAO.DBBaseInforDAO;
import com.commonSocket.net.AppContext;

public class ResGlobal {

    private static Logger log = Logger.getLogger(ResGlobal.class);
    private static ResGlobal instance;
    private DBBaseInforDAO dbBaseInforDAOImpl = (DBBaseInforDAO) AppContext.getInstance().getBean("DBBaseInforDAOImpl");
    private AppConfig appConfig = (AppConfig) AppContext.getInstance().getBean("appConfig");

    /**
     * 建筑物基础信息配置
     */
    public ConcurrentHashMap<Long, BwBuildingVO> bwBuildingVOMap;
    /**
     * 建筑等级基础信息配置
     */
    public ConcurrentHashMap<String, BwBuildingPropertiesLevelVO> bwBuildingPropertiesLevelVOMap;
    /**
     * 防御性建筑MD5加密
     */
    public ConcurrentHashMap<String, String> bwBuildingPropertiesLevelVOMD5Map;
    /**
     * 大厅与其他建筑关系配置
     */
    public ConcurrentHashMap<Long, BwHallBuildsRelationVO> bwHallBuildsRelationVOMap;
    /**
     * 角色属性配置
     */
    public ConcurrentHashMap<Long, BwCharactersVO> bwCharactersVOMap;
    /**
     * 角色属性等级配置
     */
    public ConcurrentHashMap<String, BwCharactersPropertiesLevelVO> bwCharactersPropertiesLevelVOMap;
    /**
     * 兵力属性md5加密
     */
    public ConcurrentHashMap<String, String> bwCharactersPropertiesLevelVOMD5Map;
    /**
     * 用户等级经验配置
     */
    public ConcurrentHashMap<Long, BwUserLevelReqVO> bwUserLevelReqVOMap;
    /**
     * 魔法等级配置
     */
    public ConcurrentHashMap<String, BwSpellPropertiesLevelVO> bwSpellPropertiesLevelVOMap;

    /**
     * 魔法MD5加密
     */
    public ConcurrentHashMap<String, String> bwSpellPropertiesLevelVOMD5Map;
    /**
     * 魔法配置
     */
    public ConcurrentHashMap<Long, BwSpellVOSource> bwSpellVOSourceMap;
    /**
     * 宝石兑换资源配置
     */
    public List<BwExchangeVO> bwExchanges;
    /**
     * 用户初始数据
     */
    public BwInitUserVO bwInitUserVO;
    /**
     * 加速配置
     */
    public Map<Long, BwQuickenVO> quickenMap;
    /**
     * 商店兑换配置
     */
    public Map<Long, BwTreasureVO> treasureMap;

    /**
     * 建筑最大等级
     */
    public Map<Long, Integer> buildingMaxLevel;
    public BwArgsVO bwArgsVO;
    //本分区的服务器列表 string=areaId_appid
    public ConcurrentHashMap<String, BwGameChannleVO> cityServerChannleVOForAreaMap;

    public static synchronized ResGlobal getInstance() {
        if (instance == null) {
            instance = new ResGlobal();
            instance.init();
        }
        return instance;
    }

    public void init() {
        log.info("configuration init start ................");
        // 建筑基础信息
        bwBuildingVOMap = new ConcurrentHashMap<Long, BwBuildingVO>();
        // 建筑等级信息
        bwBuildingPropertiesLevelVOMap = new ConcurrentHashMap<String, BwBuildingPropertiesLevelVO>();
        bwHallBuildsRelationVOMap = new ConcurrentHashMap<Long, BwHallBuildsRelationVO>();
        bwCharactersVOMap = new ConcurrentHashMap<Long, BwCharactersVO>();
        bwCharactersPropertiesLevelVOMap = new ConcurrentHashMap<String, BwCharactersPropertiesLevelVO>();
        bwUserLevelReqVOMap = new ConcurrentHashMap<Long, BwUserLevelReqVO>();
        bwSpellPropertiesLevelVOMap = new ConcurrentHashMap<String, BwSpellPropertiesLevelVO>();
        bwSpellVOSourceMap = new ConcurrentHashMap<Long, BwSpellVOSource>();
        bwInitUserVO = new BwInitUserVO();
        bwBuildingPropertiesLevelVOMD5Map = new ConcurrentHashMap<String, String>();
        bwCharactersPropertiesLevelVOMD5Map = new ConcurrentHashMap<String, String>();
        bwSpellPropertiesLevelVOMD5Map = new ConcurrentHashMap<String, String>();
        cityServerChannleVOForAreaMap = new ConcurrentHashMap<String, BwGameChannleVO>();
        List<BwBuildingVO> bwBuildingVOList = dbBaseInforDAOImpl.queryBwBuildingVO();
        if (null != bwBuildingVOList) {
            for (BwBuildingVO bvo : bwBuildingVOList) {
                bwBuildingVOMap.put(bvo.getBuildingId(), bvo);
                //获取建筑的md5吗
                BwBuildingPropertiesLevelVO t = new BwBuildingPropertiesLevelVO();
                t.setBuildingid(bvo.getBuildingId());
                List<BwBuildingPropertiesLevelVO> tempList = dbBaseInforDAOImpl.queryBwBuildingPropertiesLevelVOByBuildingId(t);
                if (null != tempList) {
                    for (BwBuildingPropertiesLevelVO tbvo : tempList) {
                        String md5key = bvo.getBuildingId() + "_" + tbvo.getBuildlevel();
                        String value = tbvo.getHitpoint() + "_" + bvo.getAttackRange() + "" + bvo.getAttackSpeed() + "_" + tbvo.getDamage();
                        //对应的子弹类型 不处理
                        value += "_" + bvo.getAirTargets() + "_" + bvo.getGroundTargets();
                        //盲区范围最小攻击范围
                        value += "_" + bvo.getMinAttackRange();
                        //攻击的溅射范围
                        value += "_" + bvo.getDamageRadius();
                        //攻击附带击退效果 标示
                        value += "_" + bvo.getPushBack();
                        System.out.println("建筑等级信息MD5key:" + md5key);
                        System.out.println("建筑等级信息MD5value:" + value);
                        bwBuildingPropertiesLevelVOMD5Map.put(md5key, MD5Util.getMD5String(value.getBytes()));
                        System.out.println("建筑等级信息MD5码:" + bwBuildingPropertiesLevelVOMD5Map.get(md5key));
                    }
                }
            }
        }
        List<BwBuildingPropertiesLevelVO> bwBuildingPropertiesLevelVOList = dbBaseInforDAOImpl.queryBwBuildingPropertiesLevelVO();
        buildingMaxLevel = new ConcurrentHashMap<Long, Integer>();
        if (null != bwBuildingPropertiesLevelVOList) {
            for (BwBuildingPropertiesLevelVO bvo : bwBuildingPropertiesLevelVOList) {
                String key = bvo.getBuildingid() + "_" + bvo.getBuildlevel();
                bwBuildingPropertiesLevelVOMap.put(key, bvo);
                Integer maxLevel = buildingMaxLevel.get(bvo.getBuildingid());
                if (maxLevel == null || bvo.getBuildlevel() > maxLevel) {
                    buildingMaxLevel.put(bvo.getBuildingid(), bvo.getBuildlevel());
                }
            }
        }
        buildingMaxLevel.put((long) ResourceType.WORKER_BUILDING.getId(), 1);

        List<BwCharactersVO> bwCharactersVOList = dbBaseInforDAOImpl
                .queryBwCharactersVO();
        if (null != bwCharactersVOList) {
            for (BwCharactersVO bvo : bwCharactersVOList) {
                bwCharactersVOMap.put(bvo.getCharacterid(), bvo);
                //根据charactarId 查询对应的角色等级信息
                BwCharactersPropertiesLevelVO temp = new BwCharactersPropertiesLevelVO();
                temp.setCharacterid(bvo.getCharacterid());
                List<BwCharactersPropertiesLevelVO> tempList = dbBaseInforDAOImpl.queryBwCharactersPropertiesLevelVOByCharactarId(temp);
                if (null != tempList) {
                    for (BwCharactersPropertiesLevelVO clevelvo : tempList) {
                        String md5key = bvo.getCharacterid() + "_" + clevelvo.getCharacterlevel();
                        String value = bvo.getSpeed() + "_" + clevelvo.getHitpoints() + "_" + bvo.getAttackrang() + "_"
                                + bvo.getAttackspeed() + "" + clevelvo.getDamage() + "_" + bvo.getDamagemod();
                        //溅射伤害先不处理
                        //优先攻击的类型
                        value += "_" + bvo.getAttackpreferedtarget();
                        //攻击效果 受击效果 不处理
                        //是否为飞行单位
                        value += "_" + bvo.getIsflying();
                        //攻击对空
                        value += "_" + bvo.getAirtarget();
                        //攻击对地
                        value += "_" + bvo.getGroundtargets();
                        //攻击死亡次数
                        value += "_" + bvo.getAttackcount();
                        System.out.println("角色属性信息MD5key:" + md5key);
                        System.out.println("角色属性信息MD5value:" + value);
                        bwCharactersPropertiesLevelVOMD5Map.put(md5key, MD5Util.getMD5String(value.getBytes()));
                        System.out.println("角色属性信息MD5码:" + bwCharactersPropertiesLevelVOMD5Map.get(md5key));
                    }
                }
            }
        }

        List<BwCharactersPropertiesLevelVO> bwCharactersPropertiesLevelVOList = dbBaseInforDAOImpl
                .queryBwCharactersPropertiesLevelVO();
        if (null != bwCharactersPropertiesLevelVOList) {
            for (BwCharactersPropertiesLevelVO bvo : bwCharactersPropertiesLevelVOList) {
                bwCharactersPropertiesLevelVOMap.put(String.valueOf(bvo.getCharacterid()) + "_"
                        + bvo.getCharacterlevel(), bvo);
                // bwCharactersPropertiesLevelVOMD5Map.put(key, MD5Util.getMD5String(value.getBytes()));
            }
        }

        List<BwUserLevelReqVO> bwUserLevelReqVOList = dbBaseInforDAOImpl
                .queryBwUserLevelReqVO();
        if (null != bwUserLevelReqVOList) {
            for (BwUserLevelReqVO bvo : bwUserLevelReqVOList) {
                bwUserLevelReqVOMap.put(bvo.getLevelid(), bvo);
            }
        }

        List<BwHallBuildsRelationVO> bwHallBuildsRelationVOList = dbBaseInforDAOImpl.queryBwHallBuildsRelationVO();
        if (null != bwHallBuildsRelationVOList) {
            for (BwHallBuildsRelationVO bbvo : bwHallBuildsRelationVOList) {
                System.out.println("大厅等级:" + bbvo.getHalllevel() + "  attackCost" + bbvo.getAttackcost());

                bwHallBuildsRelationVOMap.put(bbvo.getHalllevel(), bbvo);
                System.out.println("11大厅等级:" + bwHallBuildsRelationVOMap.get((long) 1).getAttackcost());
            }
        }

        List<BwSpellPropertiesLevelVO> bwSpellPropertiesLevelVOList = dbBaseInforDAOImpl.queryBwSpellPropertiesLevelVO();
        if (bwSpellPropertiesLevelVOList != null) {
            for (BwSpellPropertiesLevelVO bsp : bwSpellPropertiesLevelVOList) {
                String key = bsp.getSpellid() + "_" + bsp.getSpelllevel();
                bwSpellPropertiesLevelVOMap.put(key, bsp);
                // bwSpellPropertiesLevelVOMD5Map.put(key, MD5Util.getMD5String(value.getBytes()));
            }
        }

        List<BwSpellVOSource> bwSpellList = dbBaseInforDAOImpl.queryBwSpellVO();
        if (bwSpellList != null) {
            for (BwSpellVOSource bvs : bwSpellList) {
                bwSpellVOSourceMap.put(bvs.getSpellid(), bvs);
            }
        }

        // 用户初始化数据
        BwInitUserVO bwInitUserVO = dbBaseInforDAOImpl.queryBwInitUserVO();
        if (bwInitUserVO != null) {
            this.bwInitUserVO = bwInitUserVO;
        }

        // 宝石兑换资源配置
        bwExchanges = dbBaseInforDAOImpl.queryBwExchanges();
        Collections.sort(bwExchanges, new Comparator<BwExchangeVO>() {
            @Override
            public int compare(BwExchangeVO o1, BwExchangeVO o2) {
                return (int) (o1.getCount() - o2.getCount());
            }
        });

        // 商店兑换数据
        treasureMap = new ConcurrentHashMap<Long, BwTreasureVO>();
        List<BwTreasureVO> treasure = dbBaseInforDAOImpl.queryBwTreasures();
        for (BwTreasureVO vo : treasure) {
            treasureMap.put(vo.getId(), vo);
        }

        // 加速配置
        quickenMap = new ConcurrentHashMap<Long, BwQuickenVO>();
        List<BwQuickenVO> quickenList = dbBaseInforDAOImpl.queryBwQuickens();
        for (BwQuickenVO quicken : quickenList) {
            quickenMap.put(quicken.getId(), quicken);
        }
        //查询游戏参数信息
        List<BwGameChannleVO> cscvoList = dbBaseInforDAOImpl.getGameServerChannleList((long) appConfig.getAreaId());
        if (null != cscvoList && cscvoList.size() > 0) {
            for (BwGameChannleVO cschvo : cscvoList) {
                cityServerChannleVOForAreaMap.put(cschvo.getAreaId() + "_" + cschvo.getId(), cschvo);
            }

        }

//		bwArgsVO=
        log.info("configuration init end ................");
    }

}
