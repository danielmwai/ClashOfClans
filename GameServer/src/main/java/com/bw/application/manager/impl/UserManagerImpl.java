package com.bw.application.manager.impl;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.jmsUpdate.BwMineCollectorAllVOUpdateJMS;
import com.bw.application.jmsUpdate.BwMineCollectorVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserCharacterVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserMapDataVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserSpellVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserVOUpdateJMS;
import com.bw.application.manager.IOffLineManager;
import com.bw.application.manager.IUserManager;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse.Builder;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse.UserCharacter;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse.UserMapData;
import com.bw.application.message.DownloadMapInfor.DownloadMapInforResponse.UserMapData.BarrackOrLib;
import com.bw.application.message.OperateSequence;
import com.bw.application.message.ProduceSoldierMessage;
import com.bw.application.resourceManager.ResGlobal;
import com.bw.application.resourceManager.ResHelper;
import com.bw.application.utils.CommonMethod;
import com.bw.application.utils.DateUtil;
import com.bw.baseJar.common.CommonGameData;
import com.bw.baseJar.common.LineStatusEnum;
import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwHallBuildsRelationVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwSpellVOSource;
import com.bw.baseJar.vo.BwTreasureVO;
import com.bw.cache.vo.BattleLineVO;
import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleDestoryVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.cache.vo.BwUserBankVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.common.OperateType;
import com.bw.common.ResourceType;
import com.bw.dao.BattleLineDAO;
import com.bw.dao.BwBarrackDAO;
import com.bw.dao.BwBattleDAO;
import com.bw.dao.BwClansCharacterRequestDAO;
import com.bw.dao.BwMineCollectorAllDAO;
import com.bw.dao.BwMineCollectorDAO;
import com.bw.dao.BwPlantUserDAO;
import com.bw.dao.BwSpellDAO;
import com.bw.dao.BwUserBankDAO;
import com.bw.dao.BwUserCharacterDAO;
import com.bw.dao.BwUserDAO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.dao.BwUserSpellDAO;
import com.bw.exception.CacheDaoException;
import com.bw.log.ActionLog;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhYou
 */
public class UserManagerImpl implements IUserManager {

    @Override
    public void getUserAllInfor(DownloadMapInforResponse.Builder builder, String mailAddress) throws ManagerServerException {
        try {
            // 用户数据
            BwUserVO user = getUser(mailAddress);
            if (user == null) {
                throw new RuntimeException("user not found");
            }
            // 建筑数据
            List<BwUserMapDataVO> maps = getUserMapDatas(user);
            // 资源数据
            List<BwMineCollectorVO> collectors = new ArrayList<BwMineCollectorVO>();
            user.setFree_worker_count(user.getWorkCount());
            for (BwUserMapDataVO map : maps) {
                if (map.getBuildid() == ResourceType.GOLD_MINE.getId() || map.getBuildid() == ResourceType.ELIXIR_PUMP.getId()) {
                    BwMineCollectorVO collector = getMineCollector(map, System.currentTimeMillis());
                    if (collector != null) {
                        collectors.add(collector);
                    }
                }
                // 计算空闲农民
                if (isUpgrading(map.getUpgradefinishtime())) {
                    user.setFree_worker_count(user.getFree_worker_count() - 1);
                }
            }
            // 兵营数据
            List<Long> barrackMapIds = new ArrayList<Long>();
            for (BwUserMapDataVO map : maps) {
                if (map.getBuildid() == ResourceType.BARRACK.getId()) {
                    barrackMapIds.add(map.getId());
                }
            }
            List<BwBarrackVO> barracks = new ArrayList<BwBarrackVO>();
            for (Long barrackMapId : barrackMapIds) {
                List<BwBarrackVO> b = getBarracks(user, barrackMapId, System.currentTimeMillis());
                if (b != null && b.size() > 0) {
                    barracks.addAll(b);
                }
            }
            // 兵种数据
            List<BwUserCharacterVO> userCharas = getUserCharacters(user.getMailaddress(), System.currentTimeMillis());

            // 战斗数据
            // 如果正在战斗,进行倒计时战斗提示
            BattleLineVO blvo = battleLineDAOImpl.getBattleLineVO(mailAddress);
            if (blvo != null) {
                if (blvo.getLineStatus() == LineStatusEnum.BATTLE_LINE.value()) {
                    // builder.setDestroyStatus(2);
                    // 计算等待时间
                    int waitTime = (int) ((System.currentTimeMillis() - blvo.getBattleStartTime().getTime()) / 1000);
                    if (waitTime <= (3 * 60 + 30)) {// 正常状态的 进行战斗 等待状态,如果战斗中途停止,运行用户直接登录
                        builder.setBattleEndTime(waitTime);
                        // set 用户battle状态为等待状态
                        blvo.setLineStatus(LineStatusEnum.WAITING_LINE.value());
                        boolean result = battleLineDAOImpl.updateBattleLineVOWaitTimes(blvo, 5);
                        if (!result) {
                            logger.error("更改战斗状态发生错误:" + mailAddress);
                        }
                        return;
                    }
                }
            }

            blvo = battleLineDAOImpl.getBattleLineVO(mailAddress);
            if (null == blvo) {
                blvo = new BattleLineVO();
                blvo.setMailAddress(mailAddress);
                blvo.setLineStatus(LineStatusEnum.ON_LINE.value());
                battleLineDAOImpl.updateBattleLineVONoCas(blvo);
            } else {
                blvo.setLineStatus(LineStatusEnum.ON_LINE.value());
                if (battleLineDAOImpl.updateBattleLineVO(blvo)) {// 成功说明没有在战斗状态
                    // 同时要移除每个服务器cache中得状态
                    // offLineManagerImpl.removeOfflineMember(mailAddress, (int)bwuservo.getPvpmark(), LineStatusEnum.ON_LINE);
                } else {// 和30秒匹配发生毫秒差别,等待1秒重新处理
                    Thread.sleep(1000);
                }
            }

            // 提示被 攻击的信息
            // 获取战斗信息
            long getCount = bwBattleCacheDAOImpl.queryBwBattleVOCountFlag(mailAddress);
            if (getCount > 0) {
                List<BwBattleVO> tempList = bwBattleCacheDAOImpl.queryBwBattleVO(mailAddress);
                int battleListCount = tempList.size();
                for (long x = getCount; x > 0; x--) {
                    battleListCount--;
                    BwBattleVO bbvo = tempList.get(battleListCount);
                    DownloadMapInforResponse.BattleInfor.Builder binfor = DownloadMapInforResponse.BattleInfor.newBuilder();
                    binfor.setAttackerNickName(bbvo.getAttacker());
                    binfor.setAttackTime(bbvo.getBattletime());
                    binfor.setDefencerGetPvpMark((int) bbvo.getPvpmark());
                    builder.addBattleInforList(binfor.build());
                }
            }

            bwUserVOUpdateJMS.addBwUserVO(user);
            bwUserCacheDAOImpl.update(user);
            // 使用builder进行数据封装
            setBuilder(builder, user, maps, collectors, barracks, userCharas);
        } catch (CacheDaoException e) {
            e.printStackTrace();
            throw new ManagerServerException(e);
        } catch (InterruptedException e) {
            throw new ManagerServerException(e);
        }
    }

    /**
     * 使用builder封装下发数据
     *
     * @param builder builder
     * @param user 用户数据
     * @param maps 地图数据
     * @param collectors 收集器数据
     * @param barracks 士兵生产数据
     * @param userCharas 用户士兵数据
     */
    private void setBuilder(Builder builder,
            BwUserVO user,
            List<BwUserMapDataVO> maps,
            List<BwMineCollectorVO> collectors,
            List<BwBarrackVO> barracks,
            List<BwUserCharacterVO> userCharas) {

        // 用户数据
        builder.setNickName(user.getNickname());
        builder.setLevel(user.getLevel());
        builder.setExp((int) user.getExp());
        builder.setGoldenCount((int) user.getGoldencount());
        builder.setElixirCount((int) user.getElixircount());
        builder.setGemCount(user.getUserBankVO() == null ? 0 : (int) user.getUserBankVO().getGemtotalcount());
        builder.setPvpMark((int) user.getPvpmark());
        // 兵营数据
        Map<Long, BarrackOrLib.Builder> budrBarrackMap = new HashMap<Long, BarrackOrLib.Builder>();
        if (barracks != null && barracks.size() > 0) {
            for (BwBarrackVO barrack : barracks) {
                // 兵种等级
                int lv = 1;
                for (BwUserCharacterVO userChara : userCharas) {
                    if (userChara.getChararchterid() == barrack.getUsercharacterid()) {
                        lv = userChara.getCharacterlevel();
                        break;
                    }
                }

                BarrackOrLib.Builder budrBarrack = BarrackOrLib.newBuilder();
                budrBarrack.setCharacterId((int) barrack.getUsercharacterid());
                budrBarrack.setCharacterLevel(lv);
                budrBarrack.setProduceCount(barrack.getProducecount());
                budrBarrack.setEndTime(barrack.getStartTime());
                budrBarrackMap.put(barrack.getUsermapdataid(), budrBarrack);
            }
        }

        // 建筑数据
        for (BwUserMapDataVO map : maps) {
            // 生产数量和时间
            long produceCount = 0;
            String produceTime = null;
            if (map.getBuildid() == ResourceType.ELIXIR_PUMP.getId()
                    || map.getBuildid() == ResourceType.GOLD_MINE.getId()) {
                if (collectors != null && collectors.size() > 0) {
                    for (BwMineCollectorVO collector : collectors) {
                        if (collector.getUserbuildingdataid() == map.getId()) {
                            produceCount = collector.getProducecount();
                            produceTime = collector.getHarveststarttime();
                            break;
                        }
                    }
                }
            }

            UserMapData.Builder budrMap = UserMapData.newBuilder();
            budrMap.setBuildingId(map.getBuildid());
            budrMap.setUniquenessBuildId((int) map.getUniquenessbuildid());
            budrMap.setBuildingLevel(map.getBuildlevel() == 0 ? 1 : map.getBuildlevel());
            budrMap.setMapIndexX(map.getMapindexx());
            budrMap.setMapIndexY(map.getMapindexy());
            budrMap.setProduceCount((int) produceCount);
            if (produceTime != null) {
                budrMap.setHarvestStartTime(produceTime);
            }
            budrMap.setStatus(map.getStatus());
            if (isUpgrading(map.getUpgradefinishtime())) {
                budrMap.setBuildUpgradeEndTime(map.getUpgradefinishtime());
            }

            // 计算建筑升级的等级和状态
            int buildingStatus = 1;
            if (isUpgrading(map.getUpgradefinishtime())) {
                if (map.getBuildlevel() == 0) {
                    map.setBuildlevel(1);
                    buildingStatus = 2;
                } else {
                    buildingStatus = 3;
                }
            }
            budrMap.setBuildStatus(buildingStatus);

            if (budrBarrackMap.containsKey(map.getId())) {
                budrMap.addBarrackOrLibList(budrBarrackMap.get(map.getId()));
            }
            builder.addUserMapDataList(budrMap);
        }

        // 用户士兵数据
        if (userCharas != null && userCharas.size() > 0) {
            for (BwUserCharacterVO userChara : userCharas) {
                UserCharacter.Builder budrUserChara = UserCharacter.newBuilder();
                budrUserChara.setCharacterId(userChara.getChararchterid());
                budrUserChara.setCharacterCount(userChara.getCharactercount());
                budrUserChara.setCharacterLevel(userChara.getCharacterlevel());
                if (userChara.getUpgradecharacterfinishtime() != null) {
                    budrUserChara.setUpgradeCharacterFinishTime(userChara.getUpgradecharacterfinishtime());
                }

                builder.addUserCharacterList(budrUserChara);
            }
        }
        // 下发被攻击的信息
        List<BwBattleDestoryVO> tempListDestory = bwBattleCacheDAOImpl.queryBwBattleDestoryVO(user.getMailaddress());
        if (tempListDestory != null) {
            for (BwBattleDestoryVO bbvo : tempListDestory) {
                DownloadMapInforResponse.BuildDestoryStatus.Builder binfor = DownloadMapInforResponse.BuildDestoryStatus.newBuilder();
                binfor.setBuildingId(bbvo.getBuilding_id());
                binfor.setMapIndexX(bbvo.getMap_index_x());
                binfor.setMapIndexX(bbvo.getMap_index_y());
                binfor.setUuid(bbvo.getUuid());
                builder.addBuildDistoryStatusList(binfor);
            }
            tempListDestory.clear();
            tempListDestory = null;
        }
        // 根据大厅等级获取应当扣除的金币数量
        BwUserMapDataVO bwusermapdatavoT = new BwUserMapDataVO();
        bwusermapdatavoT.setMailaddress(user.getMailaddress());
        bwusermapdatavoT.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
        bwusermapdatavoT.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
        bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);
        BwHallBuildsRelationVO bhbrvo = ResGlobal.getInstance().bwHallBuildsRelationVOMap.get((long) bwusermapdatavoT.getBuildlevel());
        builder.setBattleMathingUseCount((int) bhbrvo.getAttackcost());
    }

    @Override
    public int createSoldiersFromClient(ProduceSoldierMessage.ProduceSoldiersRequest reqMsg) throws ManagerServerException {
        return 0;
    }

    @Override
    public int userRegister(BwPlantUserVO bwPlantUserVO) throws ManagerServerException {
        return 0;
    }

    @Override
    public int harvestElixirOrGolden(byte type, OperateSequence.OperateSequenceRequest.Operate operate, BwUserVO bwuservo, BwUserMapDataVO bwusermapdatavo, long attackAddCount) throws ManagerServerException {
        return 0;
    }

    @Override
    public int ProcessInforOfUpLoadFromClient(OperateSequence.OperateSequenceRequest reqMsg) throws ManagerServerException {
        int rtn = 0;

        try {
            if (reqMsg.getOperateListList() != null && reqMsg.getOperateListList().size() > 0) {
                // 判断用户是否存在
                BwUserVO user = getUser(reqMsg.getMailAddress());
                if (user == null) {
                    logger.error(String.format("user not found: %s", reqMsg.getMailAddress()));
                    rtn = ErrorCodeInterface.USER_NOT_FOUND;
                } else {
                    for (OperateSequence.OperateSequenceRequest.Operate op : reqMsg.getOperateListList()) {
                        // 操作类型,时间
                        OperateType opType = OperateType.valueOf(op.getType());
                        long opTime = DateUtil.parse(op.getOperationTime());
                        // 分类执行每种操作
                        if (opType == OperateType.BUILDING_BUILD) {
                            // 建筑建造
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int x = op.getMoveToMapIndexX();
                            int y = op.getMoveToMapIndexY();
                            int status = op.getBuildUpgradeStatus();
                            rtn = operateBuildingBuild(user, opTime, buildingType, uuid, x, y, status);
                            logger.debug(String.format("operate building build: %s, %d, %d, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    buildingType.getId(),
                                    opTime,
                                    uuid,
                                    x,
                                    y,
                                    status));
                        } else if (opType == OperateType.ACCELERATE_BUILDING_BUILD) {
                            // 建筑建造加速
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateAccelerateBuildingBuild(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate accelerate building build: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.CANCEL_BUILD) {
                            // 建筑取消建造
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateCancelBuild(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate cancel build: %s, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.BUILDING_UPGRADE) {
                            // 建筑升级
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateBuildingUpgrade(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate building upgrade: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.ACCELERATE_BUILDING_UPGRADE) {
                            // 加速建筑升级
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateAccelerateBuildingUpgrade(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate accelerate building upgrade: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.CANCEL_UPGRADE) {
                            // 取消建筑升级
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateCancelUpgrade(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate cancel upgrade: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.BUILDING_MOVED) {
                            // 移动建筑
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int x = op.getMoveToMapIndexX();
                            int y = op.getMoveToMapIndexY();
                            rtn = operateBuildingMoved(user, opTime, buildingType, uuid, x, y);
                            logger.debug(String.format("operate building moved: %s, %d, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    buildingType.getId(),
                                    opTime,
                                    uuid,
                                    x,
                                    y));
                        } else if (opType == OperateType.REMOVE_BUILD) {
                            // 删除建筑
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateRemoveBuild(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate remove build: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.PRODUCE_CHARACTER) {
                            // 生产士兵
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int charaId = op.getCharacterId();
                            int count = op.getProduceCount();
                            rtn = operateProduceCharacter(user, buildingType, opTime, uuid, charaId, count);
                            logger.debug(String.format("operate produce character: %s, %d, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid,
                                    charaId,
                                    count));
                        } else if (opType == OperateType.ACCELERATE_PRODUCE_CHARACTER) {
                            // 加速生产士兵
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateAccelerateProduceCharacter(user, buildingType, opTime, uuid);
                            logger.debug(String.format("operate accelerate produce character: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    buildingType.getId(),
                                    opTime,
                                    uuid));
                        } else if (opType == OperateType.CANCEL_PRODUCE_CHARACTER) {
                            // 取消造兵
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int count = op.getProduceCount();
                            int charaId = op.getCharacterId();
                            rtn = operateCancelProduceCharacter(user, buildingType, opTime, uuid, charaId, count);
                            logger.debug(String.format("operate cancel produce character: %s, %d, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    buildingType.getId(),
                                    opTime,
                                    uuid,
                                    charaId,
                                    count));
                        } else if (opType == OperateType.UPGRADE_CHARACTER) {
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int charaId = op.getCharacterId();
                            rtn = operateUpgradeCharacter(user, opTime, buildingType, uuid, charaId);
                            logger.debug(String.format("operate upgrade character: %s, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid,
                                    charaId));
                        } else if (opType == OperateType.ACCELERATE_UPGRADE_CHARACTER) {
                            // 加速兵种升级
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            int charaId = op.getCharacterId();
                            rtn = operateAccelerateUpgradeCharacter(user, opTime, buildingType, uuid, charaId);
                            logger.debug(String.format("operate accelerate upgrade character: %s, %d, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid,
                                    charaId));
                        } /*
                         * else if (opType == OperateType.PRODUCE_SPELL) {
                         * } else if (opType == OperateType.ACCELERATE_PRODUCE_SPELL) {
                         * } else if (opType == OperateType.UPGRADE_SPELL) {
                         * } else if (opType == OperateType.ACCELERATE_UPGRADE_SPELL) {
                         * }
                         */ else if (opType == OperateType.HARVEST_ELIXIR) {
                            // 收货药水
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateHarvestElixir(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate harvest elixir: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } else if (opType == OperateType.HARVEST_GOLDEN) {
                            // 收货金币
                            ResourceType buildingType = ResourceType.valueOf(op.getBuildingId());
                            int uuid = op.getUuid();
                            rtn = operateHarvestGolden(user, opTime, buildingType, uuid);
                            logger.debug(String.format("operate harvest golden: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    buildingType.getId(),
                                    uuid));
                        } /*
                         * else if (opType == OperateType.REMOVE_GRAVE) {}
                         */ else if (opType == OperateType.BUY_GEM) {
                            // 购买宝石
                            rtn = operateBuyGem(user);
                            logger.debug(String.format("operate buy gem: %s, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime));
                        } else if (opType == OperateType.BUY_GOLDEN) {
                            int treasureId = op.getTreasureId();
                            rtn = operateBuyGolden(user, treasureId);
                            logger.debug(String.format("operate buy golden: %s, %d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    treasureId));
                        } else if (opType == OperateType.BUY_ELIXIR) {
                            int treasureId = op.getTreasureId();
                            rtn = operateBuyElixir(user, treasureId);
                            logger.debug(String.format("operate buy elixir: %s,%d, %d, %d",
                                    user.getMailaddress(),
                                    rtn,
                                    opTime,
                                    treasureId));
                        } /*
                         * else if (opType == OperateType.BUY_SHIELD) {
                         * }
                         */ else if (opType == OperateType.BUY_RESOURCE) {
                            int resType = op.getUuid(); // 0, 金币; 1, 药水
                            int count = op.getProduceCount();
                            rtn = operateBuyResource(user, resType, count);
                            logger.debug(String.format("operate buy resource: %s, %d, %d, %d, %d",
                                    user.getMailaddress(),
                                    opTime,
                                    rtn,
                                    resType,
                                    count));
                        } else {
                            logger.error(String.format("operate type error: %s, %d", user.getMailaddress(), op.getType()));
                            rtn = ErrorCodeInterface.OPERATE_TYPE_ERROR;
                        }
                        if (rtn != 0) {
                            break;
                        }
                    }
                    if (rtn == 0) {
                        bwUserVOUpdateJMS.addBwUserVO(user);
                        bwUserCacheDAOImpl.update(user);
                    }
                }
            }
        } catch (CacheDaoException e) {
            e.printStackTrace();
            logger.error("process update load error:" + reqMsg.getMailAddress(), e);
            throw new ManagerServerException(e);
        }

        return rtn;
    }

    /**
     * 宝石购买资源
     *
     * @param user
     * @param resType 购买资源类型: 0, 金币; 1, 药水
     * @param count 购买资源的数量
     * @return
     */
    private int operateBuyResource(BwUserVO user, int resType, int count) {
        // 判断类型是否正确
        if (resType != 0 && resType != 1) {
            return ErrorCodeInterface.BUY_RESOURCE_NOT_FOUND;
        }
        // 扣除用户宝石
        long[] res = fillCostGem(0, count, 100);
        int rtn = CommonMethod.costResource(user, 2, res[0]);
        if (rtn != 0) {
            return rtn;
        }
        // 为用户增加资源
        if (resType == 0) {
            user.setGoldencount(user.getGoldencount() + res[1]);
        } else if (resType == 1) {
            user.setElixircount(user.getMaxElixirCount() + res[1]);
        }

        return 0;
    }

    /**
     * 购买金币
     *
     * @param user 用户信息
     * @param treasureId 物品编号
     * @return 错误码
     */
    private int operateBuyElixir(BwUserVO user, int treasureId) {
        // 获得兑换信息
        BwTreasureVO treasureConf = ResGlobal.getInstance().treasureMap.get((long) treasureId);
        if (treasureConf == null || treasureConf.getPriceType() != 1 || treasureConf.getTreasureType() != 1) {
            return ErrorCodeInterface.BUY_RESOURCE_NOT_FOUND;
        }
        long[] res = fillCostGem(user.getElixircount(), user.getMaxElixirCount(), treasureConf.getCount());
        int rtn = CommonMethod.costResource(user, 2, res[0]);
        if (rtn != 0) {
            return rtn;
        }
        CommonMethod.addResource(user, 1, res[1]);

        actionLog.logBuyResource(Integer.valueOf(user.getMailaddress()), (int) res[0], treasureId, (int) res[1]);

        return 0;
    }

    /**
     * 购买金币
     *
     * @param user 用户信息
     * @param treasureId 物品编号
     * @return 错误码
     */
    private int operateBuyGolden(BwUserVO user, int treasureId) {
        // 得到购买资源的配置
        BwTreasureVO treasureConf = ResGlobal.getInstance().treasureMap.get((long) treasureId);
        if (treasureConf == null || treasureConf.getPriceType() != 1 || treasureConf.getTreasureType() != 0) {
            return ErrorCodeInterface.BUY_RESOURCE_NOT_FOUND;
        }
        // 计算购买到资源的数量, 以及扣除的宝石
        long[] res = fillCostGem(user.getGoldencount(), user.getMaxGoldenCount(), treasureConf.getCount());
        int rtn = CommonMethod.costResource(user, 2, res[0]);
        if (rtn != 0) {
            return rtn;
        }
        CommonMethod.addResource(user, 0, res[1]);
        // 宝石消耗日志
        actionLog.logBuyResource(Integer.valueOf(user.getMailaddress()), (int) res[0], treasureId, (int) res[1]);
        return 0;
    }

    /**
     * 购买宝石
     *
     * @param user 用户信息
     */
    private int operateBuyGem(BwUserVO user) {
        BwUserBankVO userBank = getUserBank(user.getMailaddress());
        if (null != userBank) {
            if (null != user.getUserBankVO()) {
                user.getUserBankVO().setLastupdatetime(userBank.getLastupdatetime());
                user.getUserBankVO().setGemtotalcount(userBank.getGemtotalcount());
            } else {
                user.setUserBankVO(userBank);
            }
        }
        return 0;
    }

    /**
     * 收集金币
     *
     * @param user 用户信息
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateHarvestGolden(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        return operateHarvest(user, opTime, buildingType, uuid);
    }

    /**
     * 收货药水
     *
     * @param user 用户信息
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateHarvestElixir(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        return operateHarvest(user, opTime, buildingType, uuid);
    }

    private int operateHarvest(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 建筑类型错误
        if (buildingType != ResourceType.ELIXIR_PUMP && buildingType != ResourceType.GOLD_MINE) {
            return ErrorCodeInterface.BUILDING_TYPE_ERROR;
        }
        // 判断建筑是否存在
        BwUserMapDataVO pumpMap = getUserMapData(user, buildingType, uuid);
        if (pumpMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断收集器信息是否有效
        BwMineCollectorVO collector = getMineCollector(pumpMap, opTime);
        if (collector == null) {
            return ErrorCodeInterface.COLLECTOR_NOT_FOUND;
        }
        // 根据类型, 增加资源
        if (buildingType == ResourceType.ELIXIR_PUMP) {
            CommonMethod.addResource(user, 1, collector.getProducecount());
        } else {
            CommonMethod.addResource(user, 0, collector.getProducecount());
        }
        // 保存收集器信息
        collector.setProducecount(0);
        bwMineCollectorCacheDAOImpl.update(collector);
        return 0;
    }

    /**
     * 加速升级兵种
     *
     * @param user 角色信息
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @param charaId 兵种编号
     * @return 错误码
     */
    private int operateAccelerateUpgradeCharacter(BwUserVO user, long opTime, ResourceType buildingType, int uuid, int charaId) {
        // 判断类型是否正确
        if (buildingType != ResourceType.LABORATORY) {
            return ErrorCodeInterface.BUILDING_TYPE_ERROR;
        }
        // 判断数据是否存在
        BwUserMapDataVO labMap = getUserMapData(user, buildingType, uuid);
        if (labMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断升级是否已经完成
        BwUserCharacterVO userChara = getUserCharacter(user.getMailaddress(), charaId, opTime);
        if (userChara == null) {
            return ErrorCodeInterface.CHARACTER_NOT_FOUND;
        }
        if (!isUpgrading(userChara.getUpgradecharacterfinishtime())) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        // 计算并扣除用户的宝石数量
        long acceTime = DateUtil.parse(userChara.getUpgradecharacterfinishtime()) - opTime;
        long gem = accelerateCostGem(acceTime);
        int rtn = CommonMethod.costResource(user, 2, gem);
        if (rtn != 0) {
            return rtn;
        }
        // 加速升级
        userChara.setCharacterlevel(userChara.getCharacterlevel() + 1);
        userChara.setUpgradecharacterfinishtime("");
        bwUserCharacterCacheDAOImpl.update(userChara);
        return 0;
    }

    /**
     * 升级兵种
     *
     * @param user 角色信息
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @param charaId 兵种编号
     * @return 错误码
     */
    private int operateUpgradeCharacter(BwUserVO user, long opTime, ResourceType buildingType, int uuid, int charaId) {
        // 判断类型是否正确
        if (buildingType != ResourceType.LABORATORY) {
            return ErrorCodeInterface.BUILDING_TYPE_ERROR;
        }
        // 判断数据是否存在
        BwUserMapDataVO labMap = getUserMapData(user, buildingType, uuid);
        if (labMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断士兵正在升级
        BwUserCharacterVO userChara = getUserCharacter(user.getMailaddress(), charaId, opTime);
        if (userChara == null) {
            return ErrorCodeInterface.CHARACTER_NOT_FOUND;
        }
        if (userChara.getUpgradecharacterfinishtime() != null && userChara.getUpgradecharacterfinishtime().trim().length() != 0) {
            return ErrorCodeInterface.BUILDING_UPGRADING;
        }
        // 判断是否满级
        int newLv = userChara.getCharacterlevel() + 1;
        BwCharactersPropertiesLevelVO charaLvConf = ResHelper.getCharactersPropertiesLevel(charaId, newLv);
        if (charaLvConf == null) {
            return ErrorCodeInterface.FULL_LEVEL;
        }
        // 实验室等级不足
        if (charaLvConf.getLaboratorylevel() > labMap.getBuildlevel()) {
            return ErrorCodeInterface.NO_ENOUGH_LEVEL_OF_LIBORATORY;
        }
        int rtn = CommonMethod.costResource(user, ResHelper.getCharacters(charaId).getUpgraderesource(), charaLvConf.getUpgraderesourcecose());
        if (rtn != 0) {
            return rtn;
        }
        // 保存升级数据
        userChara.setUpgradecharacterfinishtime(DateUtil.format(opTime + charaLvConf.getUpgradetime() * 1000 * 60 * 60));
        bwUserCharacterCacheDAOImpl.update(userChara);
        return 0;
    }

    /**
     * 取消生产士兵
     *
     * @param user 角色信息
     * @param buildingType 建筑类型
     * @param opTime 操作时间
     * @param uuid 建筑唯一时间
     * @param charaId 兵种编号
     * @param count 取消生产的数量
     * @return 错误码
     */
    private int operateCancelProduceCharacter(BwUserVO user, ResourceType buildingType, long opTime, int uuid, int charaId, int count) {
        // 判断建筑是否存在
        if (buildingType != ResourceType.BARRACK) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        BwUserMapDataVO barrackMap = getUserMapData(user, buildingType, uuid);
        if (barrackMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 造兵数据不存在
        List<BwBarrackVO> barracks = getBarracks(user, barrackMap.getId(), opTime);
        if (barracks == null || barracks.size() == 0) {
            return ErrorCodeInterface.BARRACK_NOT_FOUND;
        }
        // 判断是否能够找到该兵种
        BwBarrackVO target = null;
        for (BwBarrackVO barrack : barracks) {
            if (barrack.getUsercharacterid() == charaId) {
                target = barrack;
            }
        }
        if (target == null) {
            return ErrorCodeInterface.CHARACTER_NOT_FOUND;
        }
        // 判断是否有足够的士兵
        if (target.getProducecount() < count) {
            return ErrorCodeInterface.NO_ENOUGH_SOLDIER;
        }
        // 当target正在生产, 并且生产数量等于取消数量, 需删除该生产数据, 并设置下一生产数据的开始时间
        if (target.getStartTime() != null && target.getStartTime().trim().length() > 0 && target.getProducecount() == count) {
            barracks.remove(target);
            bwBarrackCacheDAOImpl.delete(target);
            if (barracks.size() > 0) {
                BwBarrackVO next = barracks.get(0);
                next.setStartTime(target.getStartTime());
                bwBarrackCacheDAOImpl.update(next);
            }
        } else {
            target.setProducecount(target.getProducecount() - count);
            bwBarrackCacheDAOImpl.update(target);
        }
        // 返还用户造兵资源
        BwUserCharacterVO userChara = getUserCharacter(user.getMailaddress(), charaId, opTime);
        BwCharactersVO charaConf = ResGlobal.getInstance().bwCharactersVOMap.get((long) charaId);
        BwCharactersPropertiesLevelVO charaLvConf = ResHelper.getCharactersPropertiesLevel(charaId, userChara == null ? 1 : userChara.getCharacterlevel());
        CommonMethod.addResource(user, charaConf.getResourcetype(), charaLvConf.getTrainingresourcecost());

        return 0;
    }

    /**
     * 加速生产士兵
     *
     * @param user 用户信息
     * @param buildingType 建筑类型
     * @param opTime 操作时间
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateAccelerateProduceCharacter(BwUserVO user, ResourceType buildingType, long opTime, int uuid) {
        // 判断建筑是否存在
        if (buildingType != ResourceType.BARRACK) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        BwUserMapDataVO barrackMap = getUserMapData(user, buildingType, uuid);
        if (barrackMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断建造是否已经完成
        List<BwBarrackVO> barracks = getBarracks(user, barrackMap.getId(), opTime);
        if (barracks == null || barracks.size() == 0) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        List<BwUserCharacterVO> userCharas = getUserCharacters(user.getMailaddress(), opTime);
        if (userCharas == null || userCharas.size() == 0) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        // 得到可加速的士兵数量
        int maxMem = getMaxSoldierCount(user);
        int curMem = 0;
        for (BwUserCharacterVO userChara : userCharas) {
            curMem += userChara.getCharactercount() * ResHelper.getCharacters(userChara.getChararchterid()).getHousingspace();
        }
        int less = maxMem - curMem;
        if (less <= 0) {
            return ErrorCodeInterface.NO_ENOUGH_HOUSE_SPACE;
        }
        // 计算加速需要的时间和人口数
        int acceMem = 0;
        long acceTime = 0;
        for (BwBarrackVO barrack : barracks) {
            BwCharactersVO charaConf = ResGlobal.getInstance().bwCharactersVOMap.get(barrack.getUsercharacterid());
            acceMem += barrack.getProducecount() * charaConf.getHousingspace();
            if (barrack.getStartTime() != null && barrack.getStartTime().trim().length() > 0) {
                acceTime = DateUtil.parse(barrack.getStartTime()) + charaConf.getTrainingtime() * 1000 - opTime;
            } else {
                acceTime += charaConf.getTrainingtime() * 1000 * barrack.getProducecount();
            }
        }
        // 没有足够的人口, 无法进行造兵
        if (less < acceMem) {
            return ErrorCodeInterface.NO_ENOUGH_HOUSE_SPACE;
        }
        // 扣除用户宝石
        long gem = accelerateCostGem(acceTime);
        int rtn = CommonMethod.costResource(user, 2, gem);
        if (rtn != 0) {
            return rtn;
        }
        // 加速造兵
        for (BwBarrackVO barrack : barracks) {
            boolean found = false;
            for (BwUserCharacterVO userChara : userCharas) {
                if (userChara.getChararchterid() == barrack.getUsercharacterid()) {
                    found = true;
                    userChara.setCharactercount(userChara.getCharactercount() + barrack.getProducecount());
                    bwUserCharacterCacheDAOImpl.update(userChara);
                    break;
                }
            }
            if (!found) {
                BwUserCharacterVO vo = new BwUserCharacterVO();
                vo.setMailaddress(barrack.getMailAddress());
                vo.setChararchterid((int) barrack.getUsercharacterid());
                vo.setCharactercount(barrack.getProducecount());
                vo.setCharacterlevel(1);
                bwUserCharacterCacheDAOImpl.save(vo);
            }
            bwBarrackCacheDAOImpl.delete(barrack);
        }

        return 0;
    }

    /**
     * 生产士兵
     *
     * @param user 用户信息
     * @param buildingType 兵营编号
     * @param opTime 操作时间
     * @param uuid 建筑以为编号
     * @param charaId 兵种编号
     * @param count 士兵数量
     * @return 错误码
     */
    private int operateProduceCharacter(BwUserVO user, ResourceType buildingType, long opTime, int uuid, int charaId, int count) {
        // 判断该兵营是否存在
        if (buildingType != ResourceType.BARRACK) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        BwUserMapDataVO barrackMap = getUserMapData(user, buildingType, uuid);
        if (barrackMap == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断兵营是否正在升级
        if (isUpgrading(barrackMap.getUpgradefinishtime())) {
            return ErrorCodeInterface.BUILDING_UPGRADING;
        }
        // 判断兵营等级是否足够
        BwCharactersVO charaConf = ResGlobal.getInstance().bwCharactersVOMap.get((long) charaId);
        if (charaConf.getBarracklevel() > barrackMap.getBuildlevel()) {
            return ErrorCodeInterface.NO_ENOUGH_LEVEL_OF_BARRACK;
        }
        // 获得兵种配置数据
        int charaLv = 1;
        BwUserCharacterVO userChara = getUserCharacter(user.getMailaddress(), charaId, opTime);
        if (userChara != null) {
            charaLv = userChara.getCharacterlevel();
        }
        BwCharactersPropertiesLevelVO charaLvConf = ResHelper.getCharactersPropertiesLevel(charaId, charaLv);
        // 判断是否有足够的资源
        int rtn = CommonMethod.costResource(user, charaConf.getResourcetype(), charaLvConf.getTrainingresourcecost());
        if (rtn != 0) {
            return rtn;
        }
        // 判断兵营建造人口数是否已满
        BwBuildingPropertiesLevelVO barrackLvConf = ResHelper.getBuildingPropertiesLevel(barrackMap.getBuildid(), barrackMap.getBuildlevel());
        List<BwBarrackVO> barracks = getBarracks(user, barrackMap.getId(), opTime);
        int memCount = 0;
        int maxIdx = 0;
        BwBarrackVO vo = null;
        if (barracks != null) {
            for (BwBarrackVO barrack : barracks) {
                memCount += barrack.getProducecount() * ResHelper.getCharacters(barrack.getUsercharacterid()).getHousingspace();
                maxIdx = maxIdx < barrack.getIndex() ? barrack.getIndex() : maxIdx;
                if (barrack.getUsercharacterid() == charaId) {
                    vo = barrack;
                }
            }
        }
        if (memCount + count * charaConf.getHousingspace() > barrackLvConf.getUnitproduction()) {
            return ErrorCodeInterface.BARRACK_PRODUCE_QUEUE_FULL;
        }
        // 添加士兵数据
        if (vo != null) {
            vo.setProducecount(vo.getProducecount() + count);
            bwBarrackCacheDAOImpl.update(vo);
        } else {
            vo = new BwBarrackVO();
            vo.setMailAddress(user.getMailaddress());
            vo.setUsermapdataid(barrackMap.getId());
            vo.setStartTime(DateUtil.format(opTime));
            vo.setUsercharacterid(charaId);
            vo.setProducecount(count);
            vo.setIndex(maxIdx + 1);
            bwBarrackCacheDAOImpl.save(vo);
        }

        return 0;
    }

    /**
     * 删除建筑
     *
     * @param user 角色信息
     * @param opTime 操作时间按
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateRemoveBuild(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 判断建筑是否存在
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // TODO - zhYou: 判断建筑是否能够删除

        // 删除建筑
        bwUserMapDataCacheDAOImpl.delete(building);
        return 0;
    }

    /**
     * 建筑移动
     *
     * @param user 用户信息
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @param x 移动位置x
     * @param y 移动位置y
     * @return 错误码
     */
    private int operateBuildingMoved(BwUserVO user, long opTime, ResourceType buildingType, int uuid, int x, int y) {
        // 判断建筑是否存在
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // TODO - zhYou: 判断建筑是否可以移动到该位置

        // 移动并保存建筑
        building.setMapindexx(x);
        building.setMapindexy(y);

        bwUserMapDataVOUpdateJMS.addBwUserMapDataVO(building);
        bwUserMapDataCacheDAOImpl.update(building);
        return 0;
    }

    /**
     * 取消建筑升级, 这里的逻辑和取消建筑建造相同
     *
     * @param user 用户数据
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateCancelUpgrade(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 判断是否存在该建筑
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 建筑升级已经完成
        if (!isUpgrading(building.getUpgradefinishtime())) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        // 返还用户资源
        BwBuildingVO buildConf = ResHelper.getBuilding(buildingType.getId());
        BwBuildingPropertiesLevelVO buildLvConf = ResHelper.getBuildingPropertiesLevel(buildingType.getId(), building.getBuildlevel() + 1);
        long num = buildLvConf.getBuildcostcount() / 2;
        CommonMethod.addResource(user, buildConf.getBuildResourceType(), num);
        // 清空升级信息, 并添加工人数
        user.setFree_worker_count(user.getFree_worker_count() + 1);

        building.setUpgradefinishtime("");

        bwUserMapDataVOUpdateJMS.addBwUserMapDataVO(building);
        bwUserMapDataCacheDAOImpl.update(building);
        refreshProductionByBuilding(user, building, opTime);

        return 0;
    }

    /**
     * 加速建筑升级, 和加速建筑建造使用同一套逻辑
     *
     * @param user 用户数据
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateAccelerateBuildingUpgrade(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        return operateAccelerateBuildingBuild(user, opTime, buildingType, uuid);
    }

    /**
     * 建筑升级
     *
     * @param user 用户数据
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateBuildingUpgrade(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 判断建筑是否存在
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 判断建筑是否正在建造/升级
        if (isUpgrading(building.getUpgradefinishtime())) {
            return ErrorCodeInterface.BUILDING_UPGRADING;
        }
        // 判断工人是否足够
        if (user.getFree_worker_count() <= 0) {
            return ErrorCodeInterface.NO_HAVE_FREE_WORKER;
        }
        user.setFree_worker_count(user.getFree_worker_count() - 1);
        // 判断是否等于最大等级
        if (building.getBuildlevel() >= ResHelper.getBuildingMaxLevel(buildingType.getId())) {
            return ErrorCodeInterface.ALREADY_MAX_LEVEL;
        }
        // 判断大厅是否支持该升级
        BwBuildingPropertiesLevelVO buildLvConf = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel() + 1);
        BwUserMapDataVO hallMap = getUserMapData(user, ResourceType.TOWN_HALL, CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
        if (buildLvConf == null || hallMap == null || hallMap.getBuildlevel() < buildLvConf.getTownhalllevel()) {
            return ErrorCodeInterface.NO_ENOUGH_LEVEL_OF_HALL;
        }
        // 扣除用户资源数
        BwBuildingVO buildConf = ResHelper.getBuilding(buildingType.getId());
        int rtn = CommonMethod.costResource(user, buildConf.getBuildResourceType(), buildLvConf.getBuildcostcount());
        if (rtn != 0) {
            return rtn;
        }

        // 计算升级完成时间
        if (ResourceType.WALL == buildingType) {
            building.setBuildlevel(building.getBuildlevel() + 1);
            building.setUpgradefinishtime("");
            user.setFree_worker_count(user.getFree_worker_count() + 1);
        } else {
            long finishedTime = buildLvConf.getBuildtimedate() * 1000 * 60 * 60 * 24
                    + buildLvConf.getBuildtimehour() * 1000 * 60 * 60
                    + buildLvConf.getBuildtimeminutes() * 1000 * 60
                    + opTime;
            building.setUpgradefinishtime(DateUtil.format(finishedTime));
        }

        bwUserMapDataVOUpdateJMS.addBwUserMapDataVO(building);
        bwUserMapDataCacheDAOImpl.update(building);
        refreshProductionByBuilding(user, building, opTime);

        return 0;
    }

    /**
     * 取消建筑建造
     *
     * @param user 用户数据
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑唯一编号
     * @return 错误码
     */
    private int operateCancelBuild(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 判断建筑是否存在
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 建筑已经建造完成
        if (building.getBuildlevel() > 0 || !isUpgrading(building.getUpgradefinishtime())) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        // 增加用户资源
        BwBuildingVO buildConf = ResHelper.getBuilding(buildingType.getId());
        BwBuildingPropertiesLevelVO buildLvConf = ResHelper.getBuildingPropertiesLevel(buildingType.getId(), 1);
        long num = buildLvConf.getBuildcostcount() / 2;
        CommonMethod.addResource(user, buildConf.getBuildResourceType(), num);
        // 删除建筑并添加工人数量
        user.setFree_worker_count(user.getFree_worker_count() + 1);
        bwUserMapDataCacheDAOImpl.delete(building);
        return 0;
    }

    /**
     * 宝石加速建筑建造
     *
     * @param user 用户对象
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑uuid
     * @return 错误码
     */
    private int operateAccelerateBuildingBuild(BwUserVO user, long opTime, ResourceType buildingType, int uuid) {
        // 建筑不存在
        BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
        if (building == null) {
            return ErrorCodeInterface.BUILDING_NOT_FOUND;
        }
        // 建筑已经升级完成
        if (!isUpgrading(building.getUpgradefinishtime())) {
            return ErrorCodeInterface.ALREADY_COMPLETE;
        }
        // 计算并扣除资源花费
        long time = DateUtil.parse(building.getUpgradefinishtime()) - opTime;
        long gem = accelerateCostGem(time);
        int rtn = CommonMethod.costResource(user, 2, gem);
        if (rtn != 0) {
            return rtn;
        }
        // 建筑升级完成的数据处理
        building.setUpgradefinishtime("");
        building.setBuildlevel(building.getBuildlevel() + 1);

        bwUserMapDataVOUpdateJMS.addBwUserMapDataVO(building);
        bwUserMapDataCacheDAOImpl.update(building);
        afterBuildingUpgraded(user, building, opTime);

        // TODO - zhYou: 添加宝石消费日志
        return 0;
    }

    /**
     * 建筑建造
     *
     * @param user 用户数据
     * @param opTime 操作时间
     * @param buildingType 建筑类型
     * @param uuid 建筑uuid
     * @param x 建造位置x
     * @param y 建造位置y
     * @return 错误码
     */
    private int operateBuildingBuild(BwUserVO user, long opTime, ResourceType buildingType, int uuid, int x, int y, int status) {
        // 建筑升级
        if (status == 2) {
            BwUserMapDataVO building = getUserMapData(user, buildingType, uuid);
            if (building == null) {
                return ErrorCodeInterface.BUILDING_NOT_FOUND;
            }
            checkUpgrade(user, building, opTime);
        }
        // 判断类型是否正确
        if (buildingType == null || buildingType == ResourceType.NOTHING) {
            return ErrorCodeInterface.BUILDING_TYPE_NOT_FOUND;
        }
        // 判断是否有足够的农民
        if (user.getFree_worker_count() <= 0) {
            return ErrorCodeInterface.NO_HAVE_FREE_WORKER;
        }
        user.setFree_worker_count(user.getFree_worker_count() - 1);
        // 判断用户是否有足够的资源来进行建造, 工房(农民屋)属于特殊类型, 需要特殊处理
        int rtn = 0;
        long finishedTime = opTime;
        if (buildingType == ResourceType.WORKER_BUILDING) {
            // TODO - zhYou: 添加工房的消费扣除代码
        } else {
            BwBuildingVO buildingConf = ResHelper.getBuilding(buildingType.getId());
            BwBuildingPropertiesLevelVO buildingLvConf = ResHelper.getBuildingPropertiesLevel(buildingType.getId(), 1);
            // 计算升级结束时间
            finishedTime += buildingConf.getBuildTimeDate() * 1000 * 60 * 60 * 24
                    + buildingConf.getBuildTimeHour() * 1000 * 60 * 60
                    + buildingConf.getBuildTimeMinutes() * 1000 * 60;
            rtn = CommonMethod.costResource(user, buildingConf.getBuildResourceType(), buildingLvConf.getBuildcostcount());
        }
        if (rtn != 0) {
            return rtn;
        }
        // 判断大厅等级是否满足条件
        BwUserMapDataVO hallMap = getUserMapData(user, ResourceType.TOWN_HALL, CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
        BwHallBuildsRelationVO relationConf = ResGlobal.getInstance().bwHallBuildsRelationVOMap.get((long) hallMap.getBuildlevel());
        int maxCount;
        switch (buildingType) {
            case AIR_DEFENSE:
                maxCount = relationConf.getAirdefense();
                break;
            case ALLIANCE_CASTLE:
                maxCount = relationConf.getAlliancecastle();
                break;
            case ARCHER_TOWER:
                maxCount = relationConf.getArchertower();
                break;
            case BARRACK:
                maxCount = relationConf.getBarrack();
                break;
            case CANNON:
                maxCount = relationConf.getCannon();
                break;
            case ELIXIR_PUMP:
                maxCount = relationConf.getElixirpump();
                break;
            case ELIXIR_STORAGE:
                maxCount = relationConf.getElixirstorage();
                break;
            case GOLD_MINE:
                maxCount = relationConf.getGoldmine();
                break;
            case GOLD_STORAGE:
                maxCount = relationConf.getGoldenstorage();
                break;
            case LABORATORY:
                maxCount = relationConf.getLaboratory();
                break;
            case MORTAR:
                maxCount = relationConf.getMortar();
                break;
            case SPELL_FORGE:
                maxCount = relationConf.getSpellforge();
                break;
            case TESLA_TOWER:
                maxCount = relationConf.getTeslatower();
                break;
            case TROOP_HOUSING:
                maxCount = relationConf.getTroophousing();
                break;
            case WALL:
                maxCount = relationConf.getWall();
                break;
            case WIZARD_TOWER:
                maxCount = relationConf.getWizardtower();
                break;
            case WORKER_BUILDING:
                maxCount = relationConf.getWorkerbuilding();
                break;
            case GOBLIN_HUT:
                maxCount = 0;
                break;
            case GOBLIN_MAIN_BUILDING:
                maxCount = 0;
                break;
            case COMMUNICATIONS_MAST:
                maxCount = 0;
                break;
            case NOTHING:
                maxCount = 0;
                break;
            case TOWN_HALL:
                maxCount = 1;
                break;
            default:
                maxCount = 0;
                break;
        }
        List<Long> uuids = getUniquenessBuildIds(user.getMailaddress(), buildingType);
        int buildedCount = 0;
        if (uuids != null) {
            buildedCount = uuids.size();
            if (uuids.contains(new Long(uuid))) { // 该建筑uuid已经存在
                return ErrorCodeInterface.BUILDING_EXISTS;
            }
        }
        if (buildedCount >= maxCount) {
            return ErrorCodeInterface.NO_ENOUGH_LEVEL_OF_HALL;
        }
        // 保存建筑信息到数据库
        BwUserMapDataVO vo = new BwUserMapDataVO();
        vo.setMailaddress(user.getMailaddress());
        vo.setBuildid(buildingType.getId());
        vo.setUniquenessbuildid(uuid);
        if (buildingType == ResourceType.WALL || buildingType == ResourceType.WORKER_BUILDING) {
            vo.setBuildlevel(1);
            vo.setUpgradefinishtime("");
            afterBuildingUpgraded(user, vo, System.currentTimeMillis());
        } else {
            vo.setBuildlevel(0);
            vo.setUpgradefinishtime(DateUtil.format(finishedTime));
        }
        vo.setMapindexx(x);
        vo.setMapindexy(y);
        bwUserMapDataCacheDAOImpl.save(vo);

        return 0;
    }

    /**
     * @param acceTime 需要加速的时间
     * @return 返回加速需要花费的宝石
     */
    private long accelerateCostGem(long acceTime) {
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

    /**
     * 计算填充该资源的花费
     *
     * @param cur 玩家资源当前值
     * @param max 玩家资源的最大值
     * @param rate 购买的填充率
     * @return arr[0]: 需要的宝石数量, arr[1]: 购买资源的数量
     */
    private static long[] fillCostGem(long cur, int max, long rate) {
        long buyCount;
        if ((cur / (double) max + rate / 100.0) > 1) {
            buyCount = max - cur;
        } else {
            buyCount = (long) (max * rate / 100.0);
        }

        BwExchangeVO minConf = null;
        BwExchangeVO maxConf = null;
        for (BwExchangeVO conf : ResGlobal.getInstance().bwExchanges) {
            if (minConf == null) {
                minConf = conf;
                continue;
            }
            if (maxConf == null) {
                maxConf = conf;
            }

            if (maxConf.getCount() < buyCount) {
                minConf = maxConf;
                maxConf = null;
            } else if (minConf.getCount() < buyCount && maxConf.getCount() >= buyCount) {
                break;
            }
        }

        assert maxConf != null;
        double y = maxConf.getCount() - minConf.getCount();
        return new long[]{
            (long) Math.floor((buyCount - minConf.getCount()) / y * maxConf.getGem() + (maxConf.getCount() - buyCount) / y * minConf.getGem()),
            buyCount
        };
    }

    /**
     * 获得建筑数据<br/>
     * 如果该建筑可升级: 修改等级+1,完成时间为"", 并对建筑升级后功能进行处理
     *
     * @param user 用户对象
     * @param buildingType 建筑类型
     * @param uuid 同类型建筑的唯一编号
     * @return 返回用户地图数据, 即建筑数据
     */
    private BwUserMapDataVO getUserMapData(BwUserVO user, ResourceType buildingType, long uuid) {
        BwUserMapDataVO vo = new BwUserMapDataVO();
        vo.setMailaddress(user.getMailaddress());
        vo.setBuildid(buildingType.getId());
        vo.setUniquenessbuildid(uuid);
        vo = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(vo);
        // checkUpgrade(user, vo, opTime);
        return vo;
    }

    @Override
    public void checkUpgrade(BwUserVO user, BwUserMapDataVO map, long opTime) {
        // 时间不为空, 该建筑正在升级中, 计算判断升级是否完成
        if (map != null && isUpgrading(map.getUpgradefinishtime())) {
            long finishTime = DateUtil.parse(map.getUpgradefinishtime());
            if (opTime > finishTime) {
                int maxLv = ResHelper.getBuildingMaxLevel(map.getBuildid());
                if (maxLv > map.getBuildlevel()) {
                    map.setBuildlevel(map.getBuildlevel() + 1);
                    afterBuildingUpgraded(user, map, opTime);
                }
                map.setUpgradefinishtime("");

                bwUserMapDataVOUpdateJMS.addBwUserMapDataVO(map);
                bwUserMapDataCacheDAOImpl.update(map);
            }
        }
    }

    /**
     * 查询并返回角色所有的建筑信息, 并计算建筑升级情况
     *
     * @param user 角色信息
     * @return 返回用户所有的建筑信息
     */
    private List<BwUserMapDataVO> getUserMapDatas(BwUserVO user) {
        BwUserMapDataVO vo = new BwUserMapDataVO();
        vo.setMailaddress(user.getMailaddress());
        List<BwUserMapDataVO> maps = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVO(vo);
        if (maps != null && maps.size() > 0) {
            for (BwUserMapDataVO map : maps) {
                checkUpgrade(user, map, System.currentTimeMillis());
            }
        }
        return maps;
    }

    /**
     * 根据建筑的建造/取消/升级, 来更新生产时间<br/>
     * 开始建造(升级), 建筑时间不为空, 则设置生产开始时间为建筑完成时间<br/>
     * 完成(取消)建造(升级), 建筑时间为空, 则生产开始时间为当前时间
     *
     * @param user 用户数据
     * @param building 建筑数据
     * @param opTime 操作时间
     */
    private void refreshProductionByBuilding(BwUserVO user, BwUserMapDataVO building, long opTime) {
        ResourceType buildingType = ResourceType.valueOf(building.getBuildid());

        // 开始建造(升级), 建筑时间不为空, 则设置生产开始时间为建筑完成时间
        // 完成(取消)建造(升级), 建筑时间为空, 则生产开始时间为当前时间
        long startTime = !isUpgrading(building.getUpgradefinishtime())
                ? opTime : DateUtil.parse(building.getUpgradefinishtime());

        if (buildingType == ResourceType.GOLD_MINE || buildingType == ResourceType.ELIXIR_PUMP) {
            // 收集器暂停生产
            BwMineCollectorVO collector = getMineCollector(building, opTime);
            if (collector != null) {
                collector.setHarveststarttime(DateUtil.format(startTime));
                bwMineCollectorCacheDAOImpl.update(collector);
            }
        } else if (buildingType == ResourceType.BARRACK) {
            // 士兵暂停生产
            List<BwBarrackVO> barracks = getBarracks(user, building.getId(), opTime);
            if (barracks != null) {
                for (BwBarrackVO barrack : barracks) {
                    if (barrack != null && barrack.getStartTime() != null && barrack.getStartTime().trim().length() > 0) {
                        barrack.setStartTime(DateUtil.format(startTime));
                        bwBarrackCacheDAOImpl.update(barrack);
                        break; // 正常情况下, 只有一个在生产
                    }
                }
            }
        }
    }

    /**
     * 建筑升级后,执行的操作
     * <ol>
     * <li>金矿, 药水泵升级后, 初始化生产数据到数据库中</li>
     * <li>金库升级后, 增加角色金币存储量</li>
     * <li>药水库升级后, 增加药水的存储量</li>
     * <li>工房升级(建造,只有在1级的时候)后, 增加工人的数量</li>
     * <li>兵营升级后, 新增可制造的兵种(数量为0)</li>
     * <li>魔法工厂升级后, 增加可新造的魔法(数量为0)</li>
     * </ol>
     *
     * @param user 用户数据
     * @param building 建筑对象
     */
    private void afterBuildingUpgraded(BwUserVO user, BwUserMapDataVO building, long opTime) {
        ResourceType buildingType = ResourceType.valueOf(building.getBuildid());
        if (buildingType == ResourceType.GOLD_MINE || buildingType == ResourceType.ELIXIR_PUMP) {
            // 初始化生产数据到数据库中
            if (building.getBuildlevel() == 1) {
                BwMineCollectorVO collector = getMineCollector(building, opTime);
                if (collector == null) {
                    collector = new BwMineCollectorVO();
                    collector.setMailAddress(building.getMailaddress());
                    collector.setProducecount(0);
                    collector.setUserbuildingdataid(building.getId());
                    collector.setHarveststarttime(DateUtil.format(opTime));
                    bwMineCollectorCacheDAOImpl.save(collector);
                }
            }
        } else if (buildingType == ResourceType.GOLD_STORAGE) {
            // 增加角色最大金币容量
            BwBuildingPropertiesLevelVO pre = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel() - 1);
            BwBuildingPropertiesLevelVO now = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel());
            long preCapa = pre == null ? 0 : pre.getMaxstoredgold();
            long nowCapa = now == null ? 0 : now.getMaxstoredgold();
            if (nowCapa > preCapa) {
                user.setMaxGoldenCount((int) (user.getMaxGoldenCount() + nowCapa - preCapa));
            }
        } else if (buildingType == ResourceType.ELIXIR_STORAGE) {
            // 增加角色最大药水容量
            BwBuildingPropertiesLevelVO pre = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel() - 1);
            BwBuildingPropertiesLevelVO now = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel());
            long preCapa = pre == null ? 0 : pre.getMaxstoredelixir();
            long nowCapa = now == null ? 0 : now.getMaxstoredelixir();
            if (nowCapa > preCapa) {
                user.setMaxElixirCount((int) (user.getMaxElixirCount() + nowCapa - preCapa));
            }
        } else if (buildingType == ResourceType.WORKER_BUILDING) {
            // 工房, 之后建造的时候才会增加工人数量
            if (building.getBuildlevel() == 1) {
                user.setWorkCount(user.getWorkCount() + 1);
                user.setFree_worker_count(user.getFree_worker_count() + 1);
            }
        } else if (buildingType == ResourceType.BARRACK) {
            // 兵营升级后, 新增可制造的兵种, 士兵取消暂停建造
            // 找到可解锁的兵种
            List<Integer> charaIds = new ArrayList<Integer>();
            for (BwCharactersVO charaConf : ResGlobal.getInstance().bwCharactersVOMap.values()) {
                if (charaConf.getBarracklevel() <= building.getBuildlevel()) {
                    charaIds.add((int) charaConf.getCharacterid());
                }
            }
            // 移除已经解锁的兵种
            List<BwUserCharacterVO> charaList = getUserCharacters(user.getMailaddress(), System.currentTimeMillis());
            for (BwUserCharacterVO chara : charaList) {
                charaIds.remove(new Integer(chara.getChararchterid()));
            }
            // 将解锁的兵种添加到数据
            for (Integer charaId : charaIds) {
                BwUserCharacterVO vo = new BwUserCharacterVO();
                vo.setMailaddress(user.getMailaddress());
                vo.setChararchterid(charaId);
                vo.setCharacterlevel(1);
                vo.setCharactercount(0);
                vo.setUpgradecharacterfinishtime("");
                bwUserCharacterCacheDAOImpl.save(vo);
            }

            // 取消暂停生产士兵
            List<BwBarrackVO> barracks = getBarracks(user, building.getId(), opTime);
            if (barracks != null) {
                for (BwBarrackVO barrack : barracks) {
                    if (barrack == null || barrack.getProducecount() == 0) {
                        continue;
                    }
                    barrack.setStartTime(DateUtil.format(opTime));
                    bwBarrackCacheDAOImpl.update(barrack);
                }
            }
        } else if (buildingType == ResourceType.SPELL_FORGE) {
            // 魔法工厂升级后, 增加可新造的魔法
            // TODO - zhYou: 有必要在这里添加可造的魔法吗?
            // 找到已经解锁的技能
            List<Integer> spellIds = new ArrayList<Integer>();
            for (BwSpellVOSource spell : ResGlobal.getInstance().bwSpellVOSourceMap.values()) {
                if (spell.getUnlockspell() <= building.getBuildlevel()) {
                    spellIds.add((int) spell.getSpellid());
                }
            }
            // 移除已经解锁的技能
            List<BwUserSpellVO> spellList = getUserSpells(user.getMailaddress());
            for (BwUserSpellVO userSpell : spellList) {
                spellIds.remove(new Integer(userSpell.getSpelltypeid()));
            }
            // 将解锁的魔法添加到数据
            for (Integer spellId : spellIds) {
                BwUserSpellVO vo = new BwUserSpellVO();
                vo.setMailaddress(user.getMailaddress());
                vo.setSpelltypeid(spellId);
                vo.setSpelllevel(1);
                vo.setSpellcount(0);
                vo.setSpellupgradeendtime("");

                bwUserSpellVOUpdateJMS.addBwUserSpellVO(vo);
                bwUserSpellCacheDAOImpl.update(vo);
            }
        }

        // 恢复工人数量
        user.setFree_worker_count(user.getFree_worker_count() + 1);
        // 计算并增加经验
        if (building.getBuildid() != ResourceType.WORKER_BUILDING.getId()) {
            BwBuildingPropertiesLevelVO buildinglvConf = ResHelper.getBuildingPropertiesLevel(building.getBuildid(), building.getBuildlevel());
            long time = buildinglvConf.getBuildtimedate() * 60 * 60 * 24
                    + buildinglvConf.getBuildtimehour() * 60 * 60
                    + buildinglvConf.getBuildtimeminutes() * 60;
            int exp = (int) Math.floor(Math.sqrt(time) * 1.5);
            ResHelper.addExp(user, exp);
        }

        bwUserVOUpdateJMS.addBwUserVO(user);
        bwUserCacheDAOImpl.update(user);
    }

    /**
     * @param mail 用户唯一编号
     * @return 返回用户宝石数据
     */
    private BwUserBankVO getUserBank(String mail) {
        BwUserBankVO vo = new BwUserBankVO();
        vo.setMailaddress(mail);
        vo.setBoweiId(mail);
        return bwUserBankCacheDAOImpl.queryBwUserBankVOById(vo);
    }

    /**
     * 查询获得收集器的数据, 并计算收集的产量
     *
     * @param pumpMap 地图信息
     * @param opTime 操作时间
     * @return 返回收集器的数据
     */
    private BwMineCollectorVO getMineCollector(BwUserMapDataVO pumpMap, long opTime) {
        BwMineCollectorVO vo = new BwMineCollectorVO();
        vo.setMailAddress(pumpMap.getMailaddress());
        vo.setUserbuildingdataid(pumpMap.getId());
        vo = bwMineCollectorCacheDAOImpl.queryBwMineCollectorVOById(vo);
        if (vo != null) {
            // 计算药水产量
            long produceTime = opTime - DateUtil.parse(vo.getHarveststarttime());
            long produceCount = vo.getProducecount();
            BwBuildingPropertiesLevelVO pumpLvConf = ResHelper.getBuildingPropertiesLevel(pumpMap.getBuildid(), pumpMap.getBuildlevel());
            produceCount += pumpLvConf.getProduceresourceperhour() * produceTime / (1000 * 60 * 60);
            int maxCount = (int) (pumpMap.getBuildid() == ResourceType.GOLD_MINE.getId() ? pumpLvConf.getResourcemax() : pumpLvConf.getResourcemax());
            if (produceCount > maxCount) {
                produceCount = maxCount;
            }
            vo.setProducecount(produceCount);
            vo.setHarveststarttime(DateUtil.format(opTime));
        }
        return vo;
    }

    /**
     * @param mail 角色唯一标识
     * @return 返回所有技能生产信息
     */
    private List<BwUserSpellVO> getUserSpells(String mail) {
        BwUserSpellVO vo = new BwUserSpellVO();
        vo.setMailaddress(mail);
        return bwUserSpellCacheDAOImpl.queryBwUserSpellVO(vo);
    }

    /**
     * 查询该类型建筑的唯一编号
     *
     * @param mail 邮箱
     * @param buildingType 建筑类型
     * @return 返回建筑所有的唯一编号
     */
    private List<Long> getUniquenessBuildIds(String mail, ResourceType buildingType) {
        BwUserMapDataVO vo = new BwUserMapDataVO();
        vo.setMailaddress(mail);
        vo.setBuildid(buildingType.getId());
        return bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOIds(vo);
    }

    /**
     * 查询到用户兵种数据后, 判断该兵种是否可以升级
     *
     * @param mail 用户唯一编号
     * @param charaId 士兵类型
     * @param opTime 操作时间
     * @return 返回用户兵种数据
     */
    private BwUserCharacterVO getUserCharacter(String mail, int charaId, long opTime) {
        BwUserCharacterVO vo = new BwUserCharacterVO();
        vo.setMailaddress(mail);
        vo.setChararchterid(charaId);
        vo = bwUserCharacterCacheDAOImpl.queryBwUserCharacterVOById(vo);
        // 判断该兵种是否升级
        if (vo != null) {
            String upgradeTime = vo.getUpgradecharacterfinishtime();
            if (isUpgrading(upgradeTime) && opTime > DateUtil.parse(upgradeTime)) {
                vo.setCharacterlevel(vo.getCharacterlevel() + 1);
                vo.setUpgradecharacterfinishtime("");
            }
        }
        return vo;
    }

    /**
     * 查询出兵种数据后, 计算了兵种数据是否完成
     *
     * @param mailaddress 唯一标识
     * @return 返回用户兵种的数据
     */
    private List<BwUserCharacterVO> getUserCharacters(String mailaddress, long opTime) {
        BwUserCharacterVO vo = new BwUserCharacterVO();
        vo.setMailaddress(mailaddress);
        List<BwUserCharacterVO> userCharas = bwUserCharacterCacheDAOImpl.queryBwUserCharacterVO(vo);
        if (userCharas != null && userCharas.size() > 0) {
            // 验证兵种升级是否完成
            for (BwUserCharacterVO userChara : userCharas) {
                String upgradeTime = userChara.getUpgradecharacterfinishtime();
                if (isUpgrading(upgradeTime) && opTime > DateUtil.parse(upgradeTime)) {
                    userChara.setCharacterlevel(userChara.getCharacterlevel() + 1);
                    userChara.setUpgradecharacterfinishtime("");
                }
            }
        }
        return userCharas;
    }

    /**
     * @param user 用户信息
     * @param barrackMapId 用户地图编号
     * @param opTime 操作时间
     * @return 返回该兵营的士兵生产数据
     */
    public List<BwBarrackVO> getBarracks(BwUserVO user, long barrackMapId, long opTime) {
        BwBarrackVO vo = new BwBarrackVO();
        vo.setMailAddress(user.getMailaddress());
        vo.setUsermapdataid(barrackMapId);
        List<Long> charaIds = bwBarrackCacheDAOImpl.queryBwBarrackVOForCharactarId(vo);
        List<BwBarrackVO> barracks = new ArrayList<BwBarrackVO>();
        if (charaIds != null && charaIds.size() > 0) {
            for (Long charaId : charaIds) {
                vo.setUsercharacterid(charaId);
                BwBarrackVO barrack = bwBarrackCacheDAOImpl.queryBwBarrackVOById(vo);
                if (barrack != null) {
                    barracks.add(barrack);
                }
            }
        }
        if (barracks.size() > 0) {
            barrackProduce(user, barracks, opTime);
        }

        return barracks;
    }

    /**
     * 兵营生产士兵
     *
     * @param barracks 兵营生产数据
     * @param opTime 操作时间
     */
    public void barrackProduce(BwUserVO user, List<BwBarrackVO> barracks, long opTime) {
        // 验证参数有效性
        if (user == null || barracks == null || barracks.size() == 0) {
            return;
        }
        // 最大人口数
        int maxCount = getMaxSoldierCount(user);
        // 当前人口数
        int curCount = 0;
        List<BwUserCharacterVO> userCharas = getUserCharacters(user.getMailaddress(), opTime);
        for (BwUserCharacterVO userChara : userCharas) {
            curCount += userChara.getCharactercount();
        }

        /*
         * 生产士兵逻辑
         */
        Collections.sort(barracks, COMPARE_BY_INDEX);
        int needCount = maxCount - curCount;
        Map<Integer, Integer> charaCountMap = new HashMap<Integer, Integer>();
        long startTime = 0;
        for (BwBarrackVO barrack : barracks) {
            // 初始化开始生产时间
            if (startTime == 0) {
                if (barrack.getStartTime() != null && barrack.getStartTime().trim().length() > 0) {
                    startTime = DateUtil.parse(barrack.getStartTime());
                } else {
                    logger.warn("produce soldier start time error, set now.");
                    startTime = System.currentTimeMillis();
                }
            }
            // 计算可生产的士兵数量
            // 根据生产总量, 可造数量和时间来循环造兵逻辑
            int count = 0;
            long traninTime = ResGlobal.getInstance().bwCharactersVOMap.get(barrack.getUsercharacterid()).getTrainingtime() * 1000;
            while (needCount > 0 && barrack.getProducecount() > 0
                    && opTime > (startTime + traninTime)) {
                count++;
                needCount--;
                barrack.setProducecount(barrack.getProducecount() - 1);
                startTime += traninTime;
            }
            // 将兵种ID和数量放入map中
            if (count > 0) {
                if (charaCountMap.containsKey((int) barrack.getUsercharacterid())) {
                    count += charaCountMap.get((int) barrack.getUsercharacterid());
                }
                charaCountMap.put((int) barrack.getUsercharacterid(), count);
            }
            // 生产量完成, 删除数据; 否则, 修改造兵开始时间
            if (barrack.getProducecount() == 0) {
                bwBarrackCacheDAOImpl.delete(barrack);
            } else {
                barrack.setStartTime(DateUtil.format(startTime));
                bwBarrackCacheDAOImpl.update(barrack);
            }
        }
        // 将生产的士兵添加到用户士兵表中
        for (BwUserCharacterVO userChara : userCharas) {
            if (charaCountMap.containsKey(userChara.getChararchterid())) {
                userChara.setCharactercount(userChara.getCharactercount() + charaCountMap.get(userChara.getChararchterid()));
                charaCountMap.remove(userChara.getChararchterid());
                bwUserCharacterCacheDAOImpl.update(userChara);
            }
        }
        for (Map.Entry<Integer, Integer> charaCount : charaCountMap.entrySet()) {
            BwUserCharacterVO userChara = new BwUserCharacterVO();
            userChara.setMailaddress(user.getMailaddress());
            userChara.setChararchterid(charaCount.getKey());
            userChara.setCharactercount(charaCount.getValue());
            userChara.setCharacterlevel(1);
            userCharas.add(userChara);
            bwUserCharacterCacheDAOImpl.save(userChara);
        }
    }

    /**
     * 查询角色所有的营火数据, 计算最大的兵种数量
     *
     * @param user 角色数据
     * @return 返回该角色最大可造兵的数量
     */
    private int getMaxSoldierCount(BwUserVO user) {
        int maxCount = 0;

        // 得到所有营火数据, 计算最大人口数
        List<Long> buildingUuids = getUniquenessBuildIds(user.getMailaddress(), ResourceType.TROOP_HOUSING);
        for (Long uuid : buildingUuids) {
            BwUserMapDataVO troopHouseMap = getUserMapData(user, ResourceType.TROOP_HOUSING, uuid);
            if (troopHouseMap != null) {
                BwBuildingPropertiesLevelVO troopHouseConf = ResHelper.getBuildingPropertiesLevel(troopHouseMap.getBuildid(), troopHouseMap.getBuildlevel());
                maxCount += troopHouseConf == null ? 0 : troopHouseConf.getHouseingspace();
            }
        }

        return maxCount;
    }

    /**
     * BwBarrackVO比较器: 比较index字段
     */
    private static final Comparator<BwBarrackVO> COMPARE_BY_INDEX = new Comparator<BwBarrackVO>() {
        @Override
        public int compare(BwBarrackVO o1, BwBarrackVO o2) {
            return o1.getIndex() - o2.getIndex();
        }
    };

    /**
     * 根据时间判断该过程是否正在进行(升级/建造/生产)
     *
     * @param time 时间
     * @return true, 表示进行中
     */
    private boolean isUpgrading(String time) {
        return (time != null && time.trim().length() > 0);
    }

    /**
     * @param mail 邮箱
     * @return 返回用户信息
     */
    private BwUserVO getUser(String mail) {
        BwUserVO user = new BwUserVO();
        user.setMailaddress(mail);
        user = bwUserCacheDAOImpl.queryBwUserVOById(user);
        if (user != null) {
            // 初始化宝石数据
            if (user.getUserBankVO() == null) {
                user.setUserBankVO(getUserBank(mail));
            }
        }
        return user;
    }

    private static final Logger logger = Logger.getLogger(UserManagerImpl.class);

    //
    // Spring依赖注入
    //
    public ActionLog actionLog;
    public BwUserDAO bwUserCacheDAOImpl;
    public BwUserCharacterDAO bwUserCharacterCacheDAOImpl;
    public BwUserMapDataDAO bwUserMapDataCacheDAOImpl;
    public BwBarrackDAO bwBarrackCacheDAOImpl;
    public BwMineCollectorDAO bwMineCollectorCacheDAOImpl;
    public BwMineCollectorAllDAO bwMineCollectorAllCacheDAOImpl;
    public BwClansCharacterRequestDAO bwClansCharacterRequestCacheDAOImpl;
    public BwUserSpellDAO bwUserSpellCacheDAOImpl;
    public BwSpellDAO bwSpellCacheDAOImpl;
    public IOffLineManager offLineManagerImpl;
    public BattleLineDAO battleLineDAOImpl;
    public BwBattleDAO bwBattleCacheDAOImpl;
    public BwUserVOUpdateJMS bwUserVOUpdateJMS;
    public BwUserMapDataVOUpdateJMS bwUserMapDataVOUpdateJMS;
    public BwMineCollectorAllVOUpdateJMS bwMineCollectorAllVOUpdateJMS;
    public BwMineCollectorVOUpdateJMS bwMineCollectorVOUpdateJMS;
    public BwUserCharacterVOUpdateJMS bwUserCharacterVOUpdateJMS;
    public BwUserSpellVOUpdateJMS bwUserSpellVOUpdateJMS;
    public BwUserBankDAO bwUserBankCacheDAOImpl;
    public BwPlantUserDAO bwPlantUserCacheDAOImpl;

    public ActionLog getActionLog() {
        return actionLog;
    }

    public void setActionLog(ActionLog actionLog) {
        this.actionLog = actionLog;
    }

    public BwUserVOUpdateJMS getBwUserVOUpdateJMS() {
        return bwUserVOUpdateJMS;
    }

    public void setBwUserVOUpdateJMS(BwUserVOUpdateJMS bwUserVOUpdateJMS) {
        this.bwUserVOUpdateJMS = bwUserVOUpdateJMS;
    }

    public BwSpellDAO getBwSpellCacheDAOImpl() {
        return bwSpellCacheDAOImpl;
    }

    public void setBwSpellCacheDAOImpl(BwSpellDAO bwSpellCacheDAOImpl) {
        this.bwSpellCacheDAOImpl = bwSpellCacheDAOImpl;
    }

    public BwUserMapDataVOUpdateJMS getBwUserMapDataVOUpdateJMS() {
        return bwUserMapDataVOUpdateJMS;
    }

    public void setBwUserMapDataVOUpdateJMS(BwUserMapDataVOUpdateJMS bwUserMapDataVOUpdateJMS) {
        this.bwUserMapDataVOUpdateJMS = bwUserMapDataVOUpdateJMS;
    }

    public BwMineCollectorAllVOUpdateJMS getBwMineCollectorAllVOUpdateJMS() {
        return bwMineCollectorAllVOUpdateJMS;
    }

    public void setBwMineCollectorAllVOUpdateJMS(BwMineCollectorAllVOUpdateJMS bwMineCollectorAllVOUpdateJMS) {
        this.bwMineCollectorAllVOUpdateJMS = bwMineCollectorAllVOUpdateJMS;
    }

    public BwMineCollectorVOUpdateJMS getBwMineCollectorVOUpdateJMS() {
        return bwMineCollectorVOUpdateJMS;
    }

    public void setBwMineCollectorVOUpdateJMS(BwMineCollectorVOUpdateJMS bwMineCollectorVOUpdateJMS) {
        this.bwMineCollectorVOUpdateJMS = bwMineCollectorVOUpdateJMS;
    }

    public BwUserCharacterVOUpdateJMS getBwUserCharacterVOUpdateJMS() {
        return bwUserCharacterVOUpdateJMS;
    }

    public void setBwUserCharacterVOUpdateJMS(BwUserCharacterVOUpdateJMS bwUserCharacterVOUpdateJMS) {
        this.bwUserCharacterVOUpdateJMS = bwUserCharacterVOUpdateJMS;
    }

    public BwUserSpellVOUpdateJMS getBwUserSpellVOUpdateJMS() {
        return bwUserSpellVOUpdateJMS;
    }

    public void setBwUserSpellVOUpdateJMS(BwUserSpellVOUpdateJMS bwUserSpellVOUpdateJMS) {
        this.bwUserSpellVOUpdateJMS = bwUserSpellVOUpdateJMS;
    }

    public BwUserBankDAO getBwUserBankCacheDAOImpl() {
        return bwUserBankCacheDAOImpl;
    }

    public void setBwUserBankCacheDAOImpl(BwUserBankDAO bwUserBankCacheDAOImpl) {
        this.bwUserBankCacheDAOImpl = bwUserBankCacheDAOImpl;
    }

    public BwPlantUserDAO getBwPlantUserCacheDAOImpl() {
        return bwPlantUserCacheDAOImpl;
    }

    public void setBwPlantUserCacheDAOImpl(BwPlantUserDAO bwPlantUserCacheDAOImpl) {
        this.bwPlantUserCacheDAOImpl = bwPlantUserCacheDAOImpl;
    }

    public IOffLineManager getOffLineManagerImpl() {
        return offLineManagerImpl;
    }

    public void setOffLineManagerImpl(IOffLineManager offLineManagerImpl) {
        this.offLineManagerImpl = offLineManagerImpl;
    }

    public BattleLineDAO getBattleLineDAOImpl() {
        return battleLineDAOImpl;
    }

    public void setBattleLineDAOImpl(BattleLineDAO battleLineDAOImpl) {
        this.battleLineDAOImpl = battleLineDAOImpl;
    }

    public BwBattleDAO getBwBattleCacheDAOImpl() {
        return bwBattleCacheDAOImpl;
    }

    public void setBwBattleCacheDAOImpl(BwBattleDAO bwBattleCacheDAOImpl) {
        this.bwBattleCacheDAOImpl = bwBattleCacheDAOImpl;
    }

    public BwUserDAO getBwUserCacheDAOImpl() {
        return bwUserCacheDAOImpl;
    }

    public void setBwUserCacheDAOImpl(BwUserDAO bwUserCacheDAOImpl) {
        this.bwUserCacheDAOImpl = bwUserCacheDAOImpl;
    }

    public BwUserCharacterDAO getBwUserCharacterCacheDAOImpl() {
        return bwUserCharacterCacheDAOImpl;
    }

    public void setBwUserCharacterCacheDAOImpl(
            BwUserCharacterDAO bwUserCharacterCacheDAOImpl) {
        this.bwUserCharacterCacheDAOImpl = bwUserCharacterCacheDAOImpl;
    }

    public BwUserMapDataDAO getBwUserMapDataCacheDAOImpl() {
        return bwUserMapDataCacheDAOImpl;
    }

    public void setBwUserMapDataCacheDAOImpl(
            BwUserMapDataDAO bwUserMapDataCacheDAOImpl) {
        this.bwUserMapDataCacheDAOImpl = bwUserMapDataCacheDAOImpl;
    }

    public BwMineCollectorDAO getBwMineCollectorCacheDAOImpl() {
        return bwMineCollectorCacheDAOImpl;
    }

    public void setBwMineCollectorCacheDAOImpl(
            BwMineCollectorDAO bwMineCollectorCacheDAOImpl) {
        this.bwMineCollectorCacheDAOImpl = bwMineCollectorCacheDAOImpl;
    }

    public BwMineCollectorAllDAO getBwMineCollectorAllCacheDAOImpl() {
        return bwMineCollectorAllCacheDAOImpl;
    }

    public void setBwMineCollectorAllCacheDAOImpl(
            BwMineCollectorAllDAO bwMineCollectorAllCacheDAOImpl) {
        this.bwMineCollectorAllCacheDAOImpl = bwMineCollectorAllCacheDAOImpl;
    }

    public BwBarrackDAO getBwBarrackCacheDAOImpl() {
        return bwBarrackCacheDAOImpl;
    }

    public void setBwBarrackCacheDAOImpl(BwBarrackDAO bwBarrackCacheDAOImpl) {
        this.bwBarrackCacheDAOImpl = bwBarrackCacheDAOImpl;
    }

    public BwClansCharacterRequestDAO getBwClansCharacterRequestCacheDAOImpl() {
        return bwClansCharacterRequestCacheDAOImpl;
    }

    public void setBwClansCharacterRequestCacheDAOImpl(
            BwClansCharacterRequestDAO bwClansCharacterRequestCacheDAOImpl) {
        this.bwClansCharacterRequestCacheDAOImpl = bwClansCharacterRequestCacheDAOImpl;
    }

    public BwUserSpellDAO getBwUserSpellCacheDAOImpl() {
        return bwUserSpellCacheDAOImpl;
    }

    public void setBwUserSpellCacheDAOImpl(BwUserSpellDAO bwUserSpellCacheDAOImpl) {
        this.bwUserSpellCacheDAOImpl = bwUserSpellCacheDAOImpl;
    }
}
