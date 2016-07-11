package com.bw.application.utils;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.bw.application.message.OperateSequence.OperateSequenceRequest.Operate;
import com.bw.application.resourceManager.ResGlobal;
import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.cache.vo.BwUserBankVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.common.GameChargeType;
import com.bw.common.ResourceType;

public class CommonMethod {

    private static final Logger LOGGER = Logger.getLogger("com.bw.application.manager.impl.UserManagerImpl");

    public static long creatVerifyCode() {
        UUID uid = UUID.randomUUID();
        return Math.abs(uid.getMostSignificantBits());
    }

    // 1 Troop Housing
    // 2 Barrack
    // 3 Laboratory
    // 4 Alliance Castle
    // 5 Spell Forge
    // 100 Town Hall
    // 200 Elixir Pump
    // 201 Elixir Storage
    // 202 Gold Mine
    // 203 Gold Storage
    // 300 Cannon
    // 301 Archer Tower
    // 302 Wizard Tower
    // 303 Air Defense
    // 304 Mortar
    // 305 Tesla Tower
    // 400 Wall
    // 500 Worker Building
    // 600 Communications mast
    // 601 Goblin main building
    // 602 Goblin hut
    // 如果不扩展就用switch
    public static ResourceType getResourceType(int buildingId) {
        if (buildingId == 1) {
            return ResourceType.TROOP_HOUSING;
        } else if (buildingId == 2) {
            return ResourceType.BARRACK;
        } else if (buildingId == 3) {
            return ResourceType.LABORATORY;
        } else if (buildingId == 4) {
            return ResourceType.ALLIANCE_CASTLE;
        } else if (buildingId == 5) {
            return ResourceType.SPELL_FORGE;
        } else if (buildingId == 100) {
            return ResourceType.TOWN_HALL;
        } else if (buildingId == 200) {
            return ResourceType.ELIXIR_PUMP;
        } else if (buildingId == 201) {
            return ResourceType.ELIXIR_STORAGE;
        } else if (buildingId == 202) {
            return ResourceType.GOLD_MINE;
        } else if (buildingId == 203) {
            return ResourceType.GOLD_STORAGE;
        } else if (buildingId == 300) {
            return ResourceType.CANNON;
        } else if (buildingId == 301) {
            return ResourceType.ARCHER_TOWER;
        } else if (buildingId == 302) {
            return ResourceType.WIZARD_TOWER;
        } else if (buildingId == 303) {
            return ResourceType.AIR_DEFENSE;
        } else if (buildingId == 304) {
            return ResourceType.MORTAR;
        } else if (buildingId == 305) {
            return ResourceType.TESLA_TOWER;
        } else if (buildingId == 400) {
            return ResourceType.WALL;
        } else if (buildingId == 500) {
            return ResourceType.WORKER_BUILDING;
        } else if (buildingId == 600) {
            return ResourceType.COMMUNICATIONS_MAST;
        } else if (buildingId == 601) {
            return ResourceType.GOBLIN_MAIN_BUILDING;
        } else if (buildingId == 602) {
            return ResourceType.GOBLIN_HUT;
        }

        return ResourceType.NOTHING;
    }

    /**
     * 计算并扣除用户在建造时的花费资源
     *
     * @param operate 操作对象
     * @param bwuservo 用户对象
     * @param level 建筑的等级
     * @return 0, 操作成功
     */
    public static int chargeStatus(Operate operate, BwUserVO bwuservo, int level) {
        long checkValue = 0;
        // 要判断是否有充足的金币和药水来扣除
        // ErrorCodeInterface.ERROR
        // 根据建筑ID获取 创建一个建筑需要花费的 金币或者药水的数量
        BwBuildingVO bvo = ResGlobal.getInstance().bwBuildingVOMap.get((long) operate.getBuildingId());
        BwBuildingPropertiesLevelVO blvo = ResGlobal.getInstance().bwBuildingPropertiesLevelVOMap.get(operate.getBuildingId() + "_" + level);

        switch (GameChargeType.valueOf((int) bvo.getBuildResourceType())) {
            case GOLDEN_GAME_CHARGE:
                checkValue = bwuservo.getGoldencount() - blvo.getBuildcostcount();
                if (checkValue < 0) {// 金币不够无法购买
                    return ErrorCodeInterface.NO_ENOUGH_GOLDEN;
                }
                // 先扣钱再建房子
                bwuservo.setGoldencount(checkValue);
                break;
            case ELIXIR_GAME_CHARGE:
                checkValue = bwuservo.getElixircount() - blvo.getBuildcostcount();
                if (checkValue < 0) {// 药水不够无法
                    return ErrorCodeInterface.NO_ENOUGH_ELIXIR;
                }
                // 先扣药水再建房子
                bwuservo.setElixircount(checkValue);
                break;
            case GEM_GAME_CHARGE:
                break;
        }
        return ErrorCodeInterface.SUCESS;
    }

    /**
     * 加速完成要扣除的宝石
     *
     * @param bwUserBankVO
     * @param remainMillis 距离完成时间的毫秒数
     * @param bwInitUserVO
     * @return
     */
    public static boolean accelerateAndCut(BwUserBankVO bwUserBankVO, long remainMillis, BwInitUserVO bwInitUserVO) {
        long oneMinutesMillis = 60 * 1000;
        long oneHourMillis = 60 * oneMinutesMillis;
        long oneDayMillis = 24 * oneHourMillis;
        long oneWeekMillis = oneDayMillis * 7;
        long checkGemCount = 0;
        if (remainMillis <= oneMinutesMillis) {// 加速一分钟
            checkGemCount = bwUserBankVO.getGemtotalcount() - bwInitUserVO.getOneMinuteCost();
            if (checkGemCount < 0) {
                return false;
            }
            bwUserBankVO.setGemtotalcount(checkGemCount);

        } else if (remainMillis > oneMinutesMillis && remainMillis <= oneHourMillis) {
            checkGemCount = bwUserBankVO.getGemtotalcount() - bwInitUserVO.getOneHourCost();
            if (checkGemCount < 0) {
                return false;
            }
            bwUserBankVO.setGemtotalcount(checkGemCount);

        } else if (remainMillis > oneHourMillis && remainMillis <= oneDayMillis) {
            checkGemCount = bwUserBankVO.getGemtotalcount() - bwInitUserVO.getOneDayCost();
            if (checkGemCount < 0) {
                return false;
            }
            bwUserBankVO.setGemtotalcount(checkGemCount);

        } else if (remainMillis > oneDayMillis && remainMillis <= oneWeekMillis) {
            checkGemCount = bwUserBankVO.getGemtotalcount() - bwInitUserVO.getOneWeekCost();
            if (checkGemCount < 0) {
                return false;
            }
            bwUserBankVO.setGemtotalcount(checkGemCount);
        }

        return true;
    }

    /**
     * @param type 0资源建筑 1角色
     * @param res_id 建筑或者角色id
     * @param level 等级
     * @return
     */
    public static String getMD5ofBaseInfor(int type, int res_id, int level) {
        String result = "";
        // 根据类型 判断是资源 还是角色

        // 根据id和等级获取信息 进行md5加密
        return "";
    }

    /**
     * 扣除用户的资源, 当用户资源不足时, 返回错误码
     *
     * @param user 角色信息
     * @param type 资源的类型 0 金币 1 药水 2 绿宝石
     * @param num 数量
     * @return 0, 表示成功; 其他为错误代码
     */
    public static int costResource(BwUserVO user, int type, long num) {
        LOGGER.debug(String.format("user[%d,%d,%d] cost: %d,%d", user.getUserBankVO() == null ? 0 : user.getUserBankVO().getGemtotalcount(),
                user.getGoldencount(), user.getElixircount(), type, num));
        switch (type) {
            case 0: // 金币
            {
                long cur = user.getGoldencount() - num;
                if (cur < 0) {
                    return ErrorCodeInterface.NO_ENOUGH_GOLDEN;
                }
                user.setGoldencount(cur);
                break;
            }
            case 1: // 药水
            {
                long cur = user.getElixircount() - num;
                if (cur < 0) {
                    return ErrorCodeInterface.NO_ENOUGH_ELIXIR;
                }
                user.setElixircount(cur);
                break;
            }
            case 2: // 绿宝石
            {
                BwUserBankVO bank = user.getUserBankVO();
                if (bank == null) {
                    return ErrorCodeInterface.BANK_NOT_FOUND;
                }
                long cur = bank.getGemtotalcount() - num;
                if (cur < 0) {
                    return ErrorCodeInterface.NO_ENOUGH_GEM_CONSUME;
                }
                bank.setGemtotalcount(cur);
                break;
            }
            default: {
                return ErrorCodeInterface.COST_TYPE_ERROR;
            }
        }

        return 0;
    }

    /**
     * 为用户增加资源, 用户的资源数量不能超过上限
     *
     * @param user 用户数据
     * @param type 资源类型: 0, 金币; 1, 药水; 2, 绿宝石
     * @param num 资源数量
     */
    public static void addResource(BwUserVO user, int type, long num) {
        LOGGER.debug(String.format("user[%d,%d,%d] add: %d,%d", user.getUserBankVO() == null ? 0 : user.getUserBankVO().getGemtotalcount(),
                user.getGoldencount(), user.getElixircount(), type, num));
        switch (type) {
            case 0: // 金币
            {
                long cur = user.getGoldencount() + num;
                if (cur > user.getMaxGoldenCount()) {
                    cur = user.getMaxGoldenCount();
                }
                user.setGoldencount(cur);
                break;
            }
            case 1: // 药水
            {
                long cur = user.getElixircount() + num;
                if (cur > user.getMaxElixirCount()) {
                    cur = user.getMaxElixirCount();
                }
                user.setElixircount(cur);
                break;
            }
            case 2: // 绿宝石
            {
                BwUserBankVO bank = user.getUserBankVO();
                if (bank != null) {
                    long cur = bank.getGemtotalcount() + num;
                    bank.setGemtotalcount(cur);
                }
                break;
            }
        }
    }

    /**
     * @param acceTime 需要加速的时间
     * @return 返回加速需要花费的宝石
     */
    public static long accelerateCostGem(long acceTime) {
        long tsMin = 60 * 1000;
        long tsHour = 60 * tsMin;
        long tsDay = 24 * tsHour;
        long tsWeek = tsDay * 7;
        BwInitUserVO vo = ResGlobal.getInstance().bwInitUserVO;
        long gem = 0;
        if (acceTime <= tsMin) {// 加速一分钟
            gem = vo.getOneMinuteCost();
        } else if (acceTime > tsMin && acceTime <= tsHour) {
            gem = vo.getOneHourCost();
        } else if (acceTime > tsHour && acceTime <= tsDay) {
            gem = vo.getOneDayCost();
        } else if (acceTime > tsDay && acceTime <= tsWeek) {
            gem = vo.getOneWeekCost();
        }
        return gem;
    }

}
