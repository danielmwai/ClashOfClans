package com.bw.resourceManager;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.common.BaseInfor.DAO.DBBaseInforDAO;
import com.commonSocket.net.AppContext;

public class ResGlobal {

    private static Logger log = Logger.getLogger(ResGlobal.class);
    private static ResGlobal instance;
    //private ConcurrentHashMap<String, Vector<String>> goodsMap;
    private DBBaseInforDAO dbBaseInforDAOImpl = (DBBaseInforDAO) AppContext.getInstance().getBean("DBBaseInforDAOImpl");
    //建筑物基础信息表
    private ConcurrentHashMap<Long, BwBuildingVO> bwBuildingVOMap;
    //建筑等级基础信息表
    private ConcurrentHashMap<String, BwBuildingPropertiesLevelVO> bwBuildingPropertiesLevelVOMap;

    //角色属性表
    private ConcurrentHashMap<Long, BwCharactersVO> bwCharactersVOMap;
    //角色属性等级表
    private ConcurrentHashMap<String, BwCharactersPropertiesLevelVO> bwCharactersPropertiesLevelVOMap;
    //用户等级表
    private ConcurrentHashMap<Long, BwUserLevelReqVO> bwUserLevelReqVOMap;

    public static synchronized ResGlobal getInstance() {
        if (instance == null) {
            instance = new ResGlobal();
            instance.init();
        }
        return instance;
    }

    public void init() {
        log.info("初始化基础信息 开始................");
        //建筑基础信息
        bwBuildingVOMap = new ConcurrentHashMap<Long, BwBuildingVO>();
        //建筑等级信息
        bwBuildingPropertiesLevelVOMap = new ConcurrentHashMap<String, BwBuildingPropertiesLevelVO>();
        bwCharactersVOMap = new ConcurrentHashMap<Long, BwCharactersVO>();
        bwCharactersPropertiesLevelVOMap = new ConcurrentHashMap<String, BwCharactersPropertiesLevelVO>();
        bwUserLevelReqVOMap = new ConcurrentHashMap<Long, BwUserLevelReqVO>();
        List<BwBuildingVO> bwBuildingVOList = dbBaseInforDAOImpl.queryBwBuildingVO();
        if (null != bwBuildingVOList) {
            for (BwBuildingVO bvo : bwBuildingVOList) {
                bwBuildingVOMap.put(bvo.getBuildingId(), bvo);
            }
        }
        List<BwBuildingPropertiesLevelVO> bwBuildingPropertiesLevelVOList = dbBaseInforDAOImpl.queryBwBuildingPropertiesLevelVO();
        if (null != bwBuildingPropertiesLevelVOList) {
            for (BwBuildingPropertiesLevelVO bvo : bwBuildingPropertiesLevelVOList) {
                bwBuildingPropertiesLevelVOMap.put(bvo.getBuildingid() + "_" + bvo.getBuildlevel(), bvo);
            }
        }

        List<BwCharactersVO> bwCharactersVOList = dbBaseInforDAOImpl.queryBwCharactersVO();
        if (null != bwCharactersVOList) {
            for (BwCharactersVO bvo : bwCharactersVOList) {
                bwCharactersVOMap.put(bvo.getCharacterid(), bvo);
            }
        }

        List<BwCharactersPropertiesLevelVO> bwCharactersPropertiesLevelVOList = dbBaseInforDAOImpl.queryBwCharactersPropertiesLevelVO();
        if (null != bwCharactersPropertiesLevelVOList) {
            for (BwCharactersPropertiesLevelVO bvo : bwCharactersPropertiesLevelVOList) {
                bwCharactersPropertiesLevelVOMap.put(bvo.getCharacterid() + "_" + bvo.getCharacterlevel(), bvo);
            }
        }

        List<BwUserLevelReqVO> bwUserLevelReqVOList = dbBaseInforDAOImpl.queryBwUserLevelReqVO();
        if (null != bwUserLevelReqVOList) {
            for (BwUserLevelReqVO bvo : bwUserLevelReqVOList) {
                bwUserLevelReqVOMap.put(bvo.getLevelid(), bvo);
            }
        }
        log.info("初始化基础信息 结束................");
    }

}
