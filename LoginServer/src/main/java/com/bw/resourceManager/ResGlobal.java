package com.bw.resourceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.bw.baseJar.vo.BwAreaVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwFileServerVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.baseJar.vo.BwInitUserVO;
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
    public ConcurrentHashMap<String, BwBuildingPropertiesLevelVO> bwBuildingPropertiesLevelVOMap;

    //角色属性表
    public ConcurrentHashMap<Long, BwCharactersVO> bwCharactersVOMap;
    //角色属性等级表
    public ConcurrentHashMap<String, BwCharactersPropertiesLevelVO> bwCharactersPropertiesLevelVOMap;
    //用户等级表
    public ConcurrentHashMap<Long, BwUserLevelReqVO> bwUserLevelReqVOMap;
    //区域游戏服务器列表
    public ConcurrentHashMap<Long, BwAreaVO> bwAreaVOMap;
    //用户初始数据
    public BwInitUserVO bwInitUserVO;
    //文件服务器列表
    public List<BwFileServerVO> fileServerMap;

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
        bwInitUserVO = new BwInitUserVO();
        bwAreaVOMap = new ConcurrentHashMap<Long, BwAreaVO>();
        fileServerMap = new ArrayList<BwFileServerVO>();
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
        BwInitUserVO bwInitUserVO = dbBaseInforDAOImpl.queryBwInitUserVO();
        if (bwInitUserVO != null) {
            this.bwInitUserVO = bwInitUserVO;
        }
        //初始化区域信息
        List<BwAreaVO> tempAreaVOList = dbBaseInforDAOImpl.queryBwAreaVOList();
        if (null != tempAreaVOList && tempAreaVOList.size() > 0) {
            for (BwAreaVO arvo : tempAreaVOList) {
                //根据区域iD获取区域服务器列表
                List<BwGameChannleVO> cityServerChannleList = dbBaseInforDAOImpl.getGameServerChannleList(arvo.getAreaId());
                if (null != cityServerChannleList && cityServerChannleList.size() > 0) {
                    ConcurrentHashMap<Long, BwGameChannleVO> gameChannleHashMap = new ConcurrentHashMap<Long, BwGameChannleVO>();
                    for (BwGameChannleVO bgcvo : cityServerChannleList) {
                        gameChannleHashMap.put((long) bgcvo.getId(), bgcvo);
                    }
                    arvo.setGameChannleHashMap(gameChannleHashMap);
                    //cityServerChannleList.clear();
                    //cityServerChannleList=null;
                }
                bwAreaVOMap.put(arvo.getAreaId(), arvo);
            }
            //tempAreaVOList.clear();
            //tempAreaVOList=null;
        }
        List<BwFileServerVO> fileServerList = dbBaseInforDAOImpl.queryBwFileServerVO();
        if (fileServerList != null || fileServerList.size() > 0) {
            for (BwFileServerVO fileServer : fileServerList) {
                fileServerMap.add(fileServer);
            }
        } else {
            log.error("此地方需要获取文件服务器列表");
        }

        //
        log.info("初始化基础信息 结束................");
    }

}
