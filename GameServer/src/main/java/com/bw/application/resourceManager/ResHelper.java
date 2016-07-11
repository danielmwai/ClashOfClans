package com.bw.application.resourceManager;

import com.bw.baseJar.vo.*;
import com.bw.cache.vo.BwUserVO;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 配置辅助类
 *
 * @author zhYou
 */
public class ResHelper {

    private static final ResGlobal RES = ResGlobal.getInstance();
    private static Logger logger = Logger.getLogger(ResHelper.class);

    /**
     * @param buildingId 建筑编号
     * @return 返回建筑的配置
     */
    public static final BwBuildingVO getBuilding(long buildingId) {
        return RES.bwBuildingVOMap.get(buildingId);
    }

    /**
     * @param buildingId 建筑编号
     * @param buildingLevel 建筑等级
     * @return 返回建筑等级配置信息
     */
    public static final BwBuildingPropertiesLevelVO getBuildingPropertiesLevel(long buildingId, int buildingLevel) {
        return RES.bwBuildingPropertiesLevelVOMap.get(buildingId + "_" + buildingLevel);
    }

    /**
     * @param charaId 兵种编号
     * @return 返回该兵种所需要的人口数量
     */
    public static final long getHousingspace(long charaId) {
        return RES.bwCharactersVOMap.get(charaId).getHousingspace();
    }

    /**
     * @param charaId 兵种编号
     * @return 返回兵种配置
     */
    public static BwCharactersVO getCharacters(long charaId) {
        return RES.bwCharactersVOMap.get(charaId);
    }

    /**
     * @param charaId 兵种的编号
     * @param charaLv 兵种的等级
     * @return 返回兵种等级配置
     */
    public static BwCharactersPropertiesLevelVO getCharactersPropertiesLevel(int charaId, int charaLv) {
        return RES.bwCharactersPropertiesLevelVOMap.get(charaId + "_" + charaLv);
    }

    /**
     * @param buildid 建筑ID
     * @return 根据建筑ID返回建筑最大等级
     */
    public static int getBuildingMaxLevel(long buildid) {
        return RES.buildingMaxLevel.get(buildid);
    }

    /**
     * 为用户添加经验, 并验证是否升级
     *
     * @param user 用户对象
     * @param exp 添加的经验值
     */
    public static void addExp(BwUserVO user, int exp) {
        // 为用户增加经验
        if (exp <= 0) {
            // ignore
            return;
        }
        user.setExp(user.getExp() + exp);
        // 获取用户当前等级
        ConcurrentHashMap<Long, BwUserLevelReqVO> lvConfMap = ResGlobal.getInstance().bwUserLevelReqVOMap;
        long currentLevel = user.getLevel();
        BwUserLevelReqVO temp = lvConfMap.get(currentLevel);
        if (null != temp) {
            long needExpNextLevel = temp.getExpreq();
            if (needExpNextLevel <= user.getExp()) {// 升级
                user.setExp(user.getExp() - needExpNextLevel);
                user.setLevel((int) (currentLevel + 1));
            }
        } else {
            user.setExp(user.getExp() - exp);
            logger.error("用户达到最大等级,无法升级");
        }
    }
}
