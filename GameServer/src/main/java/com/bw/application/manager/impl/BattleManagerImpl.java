package com.bw.application.manager.impl;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.jmsUpdate.BwBattleVOUpdateJMS;
import com.bw.application.jmsUpdate.BwMineCollectorAllVOUpdateJMS;
import com.bw.application.jmsUpdate.BwMineCollectorVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserBattleStatisticsVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserCharacterVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserSpellVOUpdateJMS;
import com.bw.application.jmsUpdate.BwUserVOUpdateJMS;
import com.bw.application.manager.IBattleManager;
import com.bw.application.manager.IOffLineManager;
import com.bw.application.manager.IUserManager;
import com.bw.application.message.BattleMatching.BattleMatchingResponse;
import com.bw.application.message.BattleMatching.BattleMatchingResponse.UserMapData;
import com.bw.application.message.BattleStart.BattleStartRequest;
import com.bw.application.message.BattleStart.BattleStartResponse;
import com.bw.application.message.CancleBattle.CancleBattleRequest;
import com.bw.application.message.DownloadBattleInfor.DownloadBattleInforResponse;
import com.bw.application.message.RevengeBattle.RevengeBattleResponse.Builder;
import com.bw.application.message.UploadBattleResult.UploadBattleResultRequest;
import com.bw.application.message.UploadBattleResultPVE.UploadBattleResultPVERequest;
import com.bw.application.resourceManager.ResGlobal;
import com.bw.application.resourceManager.ResHelper;
import com.bw.application.utils.CommonMethod;
import com.bw.baseJar.common.CommonGameData;
import com.bw.baseJar.common.LineStatusEnum;
import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwHallBuildsRelationVO;
import com.bw.cache.vo.BattleLineVO;
import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleCharacterUsedCountVO;
import com.bw.cache.vo.BwBattleDestoryVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.common.GameChargeType;
import com.bw.common.ResourceType;
import com.bw.dao.BattleLineDAO;
import com.bw.dao.BwBarrackDAO;
import com.bw.dao.BwBattleDAO;
import com.bw.dao.BwMineCollectorAllDAO;
import com.bw.dao.BwMineCollectorDAO;
import com.bw.dao.BwUserBattleStatisticsDAO;
import com.bw.dao.BwUserCharacterDAO;
import com.bw.dao.BwUserDAO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.dao.BwUserSpellDAO;
import com.bw.exception.CacheDaoException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author denny zhao 战斗管理
 */
public class BattleManagerImpl implements IBattleManager {

    private IOffLineManager offLineManager;
    //用户信息
    public BwUserDAO bwUserCacheDAOImpl;
    public BwUserMapDataDAO bwUserMapDataCacheDAOImpl;
    //小的药水收集罐
    public BwMineCollectorDAO bwMineCollectorCacheDAOImpl;
    //药水总得收集器
    public BwMineCollectorAllDAO bwMineCollectorAllCacheDAOImpl;
    public DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //战斗dao
    public BwBattleDAO bwBattleCacheDAOImpl;
    //
    public BattleLineDAO battleLineDAOImpl;
    //战斗统计
    public BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl;
    //用户魔法表
    public BwUserSpellDAO bwUserSpellCacheDAOImpl;
    //用户兵力表
    public BwUserCharacterDAO bwUserCharacterCacheDAOImpl;
    //用户兵营造兵表
    public BwBarrackDAO bwBarrackCacheDAOImpl;
    //用户业务逻辑manager
    public IUserManager userManagerImpl;

    public BwUserVOUpdateJMS bwUserVOUpdateJMS;
    public BwBattleVOUpdateJMS bwBattleVOUpdateJMS;
    public BwMineCollectorAllVOUpdateJMS bwMineCollectorAllVOUpdateJMS;
    public BwMineCollectorVOUpdateJMS bwMineCollectorVOUpdateJMS;
    public BwUserBattleStatisticsVOUpdateJMS bwUserBattleStatisticsVOUpdateJMS;
    public BwUserCharacterVOUpdateJMS bwUserCharacterVOUpdateJMS;
    public BwUserSpellVOUpdateJMS bwUserSpellVOUpdateJMS;

    @Override
    public int battleMatching(BattleMatchingResponse.Builder builder,
            String mailAddress, String previousMailAddress, int battleStatus) throws ManagerServerException {
        BwUserVO bwuservo = new BwUserVO();
        bwuservo.setMailaddress(mailAddress);
        BwUserVO defencer_bwuservo;
        try {
            bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(bwuservo);
            String defanceMailAddress = "";
            //只有匹配战斗是才扣除金币,复仇不扣除金币
            //先扣掉每匹配一次需要扣除的金币数量
            //根据大厅等级获取应当扣除的金币数量
            BwUserMapDataVO bwusermapdatavoT = new BwUserMapDataVO();
            bwusermapdatavoT.setMailaddress(mailAddress);
            bwusermapdatavoT.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
            bwusermapdatavoT.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
            bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);
            BwHallBuildsRelationVO bhbrvo = ResGlobal.getInstance().bwHallBuildsRelationVOMap.get((long) bwusermapdatavoT.getBuildlevel());
            if (battleStatus == 1) {//复仇
                defanceMailAddress = previousMailAddress;
            } else {
                //把上一个被攻击者的 在线状态改成 下线
                if (previousMailAddress != null && !"".equalsIgnoreCase(previousMailAddress)) {
                    boolean result = offLineManager.updateBattleLineStatus(previousMailAddress, LineStatusEnum.OFF_LINE);
                    if (result == false) {//再重试一次
                        offLineManager.updateBattleLineStatus(previousMailAddress, LineStatusEnum.OFF_LINE);
                    }
                }

                bwuservo.setGoldencount(bwuservo.getGoldencount() - bhbrvo.getAttackcost());
                if (bwuservo.getGoldencount() < 0) {//金币不足
                    return ErrorCodeInterface.NO_ENOUGH_GOLDEN;
                }
                //找到像匹配的对手
                defanceMailAddress = offLineManager.battleMatching(mailAddress, (int) bwuservo.getPvpmark());
            }

            if (null == defanceMailAddress) {
                return ErrorCodeInterface.NO_MATCHING_USER;
            }
            //修改攻击者的保护时间

            defencer_bwuservo = new BwUserVO();
            defencer_bwuservo.setMailaddress(defanceMailAddress);
            defencer_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(defencer_bwuservo);
            //获取该对手对应的信息
            builder.setDefencerMailaddress(defanceMailAddress);
            builder.setNickName(defencer_bwuservo.getNickname());
            builder.setLevel(defencer_bwuservo.getLevel());
            //获取防守者的地图信息
            BwUserMapDataVO bwusermapdatavo = new BwUserMapDataVO();
            bwusermapdatavo.setMailaddress(defanceMailAddress);
            //获取地图信息
            List<BwUserMapDataVO> bwUserMapDataVOList = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVO(bwusermapdatavo);
            long plunderGoldenCount = 0;
            long plunderElieirCount = 0;
            //被攻击过的痕迹(墓碑) 开始
            if (null != previousMailAddress && !"".equalsIgnoreCase(previousMailAddress)) {
                List<BwBattleDestoryVO> tempList = bwBattleCacheDAOImpl.queryBwBattleDestoryVO(previousMailAddress);
                if (tempList != null) {
                    for (BwBattleDestoryVO bbvo : tempList) {
                        BattleMatchingResponse.BuildDestoryStatus.Builder binfor = BattleMatchingResponse.BuildDestoryStatus.newBuilder();
                        binfor.setBuildingId(bbvo.getBuilding_id());
                        binfor.setMapIndexX(bbvo.getMap_index_x());
                        binfor.setMapIndexX(bbvo.getMap_index_y());
                        binfor.setUuid(bbvo.getUuid());
                        builder.addBuildDistoryStatusList(binfor.build());
                    }
                    tempList.clear();
                    tempList = null;
                }
            }
            //被攻击过的痕迹(墓碑) 结束
            if (null != bwUserMapDataVOList) {
                for (BwUserMapDataVO bumdvo : bwUserMapDataVOList) {
                    BattleMatchingResponse.UserMapData.Builder umd = UserMapData.newBuilder();
                    umd.setMapIndexX(bumdvo.getMapindexx());
                    umd.setUniquenessBuildId((int) bumdvo.getUniquenessbuildid());
                    umd.setStatus(bumdvo.getStatus());
                    umd.setBuildingId(bumdvo.getBuildid());
                    umd.setBuildingLevel(bumdvo.getBuildlevel());
                    umd.setMapIndexY(bumdvo.getMapindexy());
                    //建筑物状态
                    if (null != bumdvo.getUpgradefinishtime() && !"".equalsIgnoreCase(bumdvo.getUpgradefinishtime())) {
                        userManagerImpl.checkUpgrade(defencer_bwuservo, bumdvo, System.currentTimeMillis());
                        if (null != bumdvo.getUpgradefinishtime() && !"".equalsIgnoreCase(bumdvo.getUpgradefinishtime().trim())) {
                            umd.setBuildingStatus(1);//建筑物升级中
                            if (umd.getBuildingLevel() == 0) {
                                umd.setBuildingLevel(1);
                            }
                        } else {//升级完成
                            //更新用户信息,user_map_data 已经更新 ,这里不用更新
                            umd.setBuildingLevel(bumdvo.getBuildlevel());
                            //send jms
                            bwUserVOUpdateJMS.addBwUserVO(defencer_bwuservo);
                            bwUserCacheDAOImpl.update(defencer_bwuservo);
                            umd.setBuildingStatus(0);//正常状态
                        }

                    } else {
                        umd.setBuildingStatus(0);//正常状态
                    }
                    //攻击者获取到的金币或者药水数量
                    //根据建筑物ID获取建筑类型
                    ResourceType resourceType = CommonMethod.getResourceType(bumdvo.getBuildid());
                    switch (resourceType) {
                        case GOLD_STORAGE://金库

                            BwMineCollectorAllVO bwminecollectorallvoGolden = new BwMineCollectorAllVO();
                            bwminecollectorallvoGolden.setUserbuildingdataid(bumdvo.getId());
                            bwminecollectorallvoGolden = bwMineCollectorAllCacheDAOImpl.queryBwMineCollectorAllVOById(bwminecollectorallvoGolden);
                            if (null != bwminecollectorallvoGolden) {
                                umd.setCollectCount((int) Math.floor(bwminecollectorallvoGolden.getCollectcount() * bhbrvo.getPlunder_arg2() / 100));
                            }
                            plunderGoldenCount += umd.getCollectCount();
                            bwminecollectorallvoGolden = null;

                            break;
                        case ELIXIR_STORAGE://药水 水罐
                            BwMineCollectorAllVO bwminecollectorallvo = new BwMineCollectorAllVO();
                            bwminecollectorallvo.setUserbuildingdataid(bumdvo.getId());
                            bwminecollectorallvo = bwMineCollectorAllCacheDAOImpl.queryBwMineCollectorAllVOById(bwminecollectorallvo);
                            if (null != bwminecollectorallvo) {
                                umd.setCollectCount((int) Math.floor(bwminecollectorallvo.getCollectcount() * bhbrvo.getPlunder_arg2() / 100));
                            }
                            plunderElieirCount += umd.getCollectCount();
                            bwminecollectorallvo = null;

                            break;
                        case ELIXIR_PUMP://药水 水井
                            BwMineCollectorVO bwminecollectorvoElixir = new BwMineCollectorVO();
                            bwminecollectorvoElixir.setUserbuildingdataid(bumdvo.getId());
                            bwminecollectorvoElixir = bwMineCollectorCacheDAOImpl.queryBwMineCollectorVOById(bwminecollectorvoElixir);
                            if (null != bwminecollectorvoElixir) {//先根据等级拿出每小时的收获数量,然后在根据收获时间和当前时间计算 存储的数量
                                String buildingPropertiesKey = bumdvo.getBuildid() + "_" + bumdvo.getBuildlevel();
                                BwBuildingPropertiesLevelVO bbplvo = ResGlobal.getInstance().bwBuildingPropertiesLevelVOMap.get(buildingPropertiesKey);
                                int procPerH = (int) bbplvo.getProduceresourceperhour();
                                int procPerM = procPerH / 60;
                                int count = procPerM * (int) ((System.currentTimeMillis() - sdf.parse(bwminecollectorvoElixir.getHarveststarttime()).getTime()) / (1000 * 60));
                                umd.setCollectCount((int) Math.floor(count * bhbrvo.getPlunder_arg1() / 100));
                                bwminecollectorvoElixir.setHarveststarttime(sdf.format(new Date()));
                                bwminecollectorvoElixir.setProducecount(count);
                                bwMineCollectorVOUpdateJMS.addBwMineCollectorVO(bwminecollectorvoElixir);
                                bwMineCollectorCacheDAOImpl.update(bwminecollectorvoElixir);
                            }
                            plunderElieirCount += umd.getCollectCount();
                            bwminecollectorvoElixir = null;
                            break;
                        case GOLD_MINE://金矿
                            BwMineCollectorVO bwminecollectorvo = new BwMineCollectorVO();
                            bwminecollectorvo.setUserbuildingdataid(bumdvo.getId());
                            bwminecollectorvo = bwMineCollectorCacheDAOImpl.queryBwMineCollectorVOById(bwminecollectorvo);
                            if (null != bwminecollectorvo) {//先根据等级拿出每小时的收获数量,然后在根据收获时间和当前时间计算 存储的数量
                                String buildingPropertiesKey = bumdvo.getBuildid() + "_" + bumdvo.getBuildlevel();
                                BwBuildingPropertiesLevelVO bbplvo = ResGlobal.getInstance().bwBuildingPropertiesLevelVOMap.get(buildingPropertiesKey);
                                int procPerH = (int) bbplvo.getProduceresourceperhour();
                                int procPerM = procPerH / 60;
                                int count = procPerM * (int) ((System.currentTimeMillis() - sdf.parse(bwminecollectorvo.getHarveststarttime()).getTime()) / (1000 * 60));
                                umd.setCollectCount((int) Math.floor(count * bhbrvo.getPlunder_arg1() / 100));
                                bwminecollectorvo.setHarveststarttime(sdf.format(new Date()));
                                bwminecollectorvo.setProducecount(count);
                                bwMineCollectorVOUpdateJMS.addBwMineCollectorVO(bwminecollectorvo);
                                bwMineCollectorCacheDAOImpl.update(bwminecollectorvo);
                            }
                            plunderGoldenCount += umd.getCollectCount();
                            bwminecollectorvo = null;
                            break;
                        case TOWN_HALL:// 大厅

                            //umd.setCollectCount(1000);
                            BwMineCollectorAllVO bwminecollectorallvoHall = new BwMineCollectorAllVO();
                            bwminecollectorallvoHall.setUserbuildingdataid(bumdvo.getId());
                            bwminecollectorallvoHall = bwMineCollectorAllCacheDAOImpl.queryBwMineCollectorAllVOById(bwminecollectorallvoHall);
                            if (null != bwminecollectorallvoHall) {
                                umd.setCollectCount((int) bwminecollectorallvoHall.getCollectcount());
                                umd.setSecondElixirCount((int) bwminecollectorallvoHall.getSecondElixirCount());
                            }
                            plunderElieirCount += umd.getCollectCount();
                            plunderGoldenCount += umd.getCollectCount();
                            bwminecollectorallvo = null;
                            break;
                        case CANNON://获取工会里面的兵力
                            break;
                    }

                    builder.addUserMapDataList(umd.build());

                }
            }
            //缺少pvp分数的规则 以及战斗的损失
            builder.setGoldenCount((int) plunderGoldenCount);
            builder.setElixirCount((int) plunderElieirCount);

            //此处保存战斗信息的目的是???,因为战斗结果中会保存
            BwBattleVO tempBattlevo = new BwBattleVO();
            tempBattlevo.setAttacker(mailAddress);
            tempBattlevo.setDefencer(defanceMailAddress);
            tempBattlevo.setBattletime(sdf.format(new Date()));
            tempBattlevo.setGetgolden(builder.getGoldenCount());
            tempBattlevo.setGetelixir(builder.getElixirCount());
            tempBattlevo.setPvpmark(builder.getPvpMark());
            bwBattleCacheDAOImpl.save(tempBattlevo);
            tempBattlevo = null;
            //计算攻击者获取的pvp分数
            //攻击者当前的pvp分数
            int arg_k = 32;
            int arg_n = 400;
            int actual_result = 3;
            long RA = bwuservo.getPvpmark();
            //防御者当前的 pvp分数
            long RB = defencer_bwuservo.getPvpmark();
            //默认最大星数 满分
            int SA = 3;
            //预期攻击者的胜负数
            //相对于攻击者来说的 胜负结果
            int result_sa = 1;

            double EA = 1 / (1 + Math.pow(10, ((RB - RA) / 400)));
            //预期防御者的生负数
            double EB = 1 / (1 + Math.pow(10, ((RA - RB) / 400)));
            double result = arg_k * (result_sa - EA) * (SA + (1 + Math.pow(-1, result_sa)) / 2) / (Math.pow(3, result_sa));
            //失败

            long actualAttackPVPMark = (long) Math.ceil(result);
            builder.setPvpMark((int) (actualAttackPVPMark));
            result_sa = 0;
            //计算防御者获取的pvp分数
            result = arg_k * (result_sa - EA) * (0 + (1 + Math.pow(-1, result_sa)) / 2) / (Math.pow(3, result_sa));
            builder.setLosePvpMark((int) Math.ceil(result));
            //获取 攻击者 携带的兵力,魔法,工会成员的数量
            //获取兵营已经造好的兵,同时更新到user 兵力表里面,再更新兵营的 造兵开始和结束时间 以及造兵的数量
            List<Long> barrackUserMapIdList = bwBarrackCacheDAOImpl.getAllBarrackUserMapId(mailAddress);
            //
            if (null != barrackUserMapIdList && barrackUserMapIdList.size() > 0) {
                List<BwBarrackVO> barrackvoListResult = new ArrayList<BwBarrackVO>();
                for (Long userMapId : barrackUserMapIdList) {
                    BwBarrackVO bwbarrackvo = new BwBarrackVO();
                    bwbarrackvo.setMailAddress(mailAddress);
                    bwbarrackvo.setUsermapdataid(userMapId);
                    //一个兵营的兵力
                    List<BwBarrackVO> barrackvoList = bwBarrackCacheDAOImpl.queryBwBarrackVO(bwbarrackvo);
                    //新修改333
                    if (null != barrackvoList && barrackvoList.size() > 0) {
                        barrackvoListResult.addAll(barrackvoList);
                        barrackvoList.clear();
                    }

                    //新修改333
//					if(null!=barrackvoList&&barrackvoList.size()>0){
//						for(BwBarrackVO bvo:barrackvoList){
//							//首先排除停造的
//							if(bvo.getProduceStatus()==1){
//								continue ;
//							}else{//先判断结束时间,结束时间到的话就全部造完了,需要更新到user_character 表里面然后删除本条记录
//							     Date endTime=sdf.parse(bvo.getEndtime());
//							     Date currentTime=new Date();
//					    	   	 BwUserCharacterVO bwusercharactervo=new BwUserCharacterVO();
//						    	 bwusercharactervo.setMailaddress(mailAddress);
//						    	 bwusercharactervo.setChararchterid((int)bvo.getUsercharacterid());
//						    	 BwUserCharacterVO bucvo=bwUserCharacterCacheDAOImpl.queryBwUserCharacterVOById(bwusercharactervo);
//							     if(endTime.before(currentTime)){//已经造完
//							    	 //先查询已经造好的兵记录 然后在删除造兵记录
//							    	 if(bucvo==null){
//							 			BwUserCharacterVO bwUserCharacterVO=new BwUserCharacterVO();
//										bwUserCharacterVO.setMailaddress(mailAddress);
//										bwUserCharacterVO.setCharactercount(bvo.getProducecount());
//										bwUserCharacterVO.setCharacterlevel(1);
//										bwUserCharacterVO.setChararchterid((int)bvo.getUsercharacterid());
////										bwUserCharacterVO.setUpgradecharacterfinishtime(getFinishTime(1,operate.getOperationTime(),operate.getCharacterId()).toString());
//										bwUserCharacterVO.setUpgradecharacterfinishtime("");
//										bwUserCharacterCacheDAOImpl.save(bwUserCharacterVO);
//										bwUserCharacterVO=null;
//							    	 }else{
//								    	 int allCount=bucvo.getCharactercount()+bvo.getProducecount();
//								    	 //判断营地是否能够放下 先省去??????
//								    	 bucvo.setCharactercount(allCount);
//								    	 bwUserCharacterVOUpdateJMS.addBwUserCharacterVO(bucvo);
//								    	 bwUserCharacterCacheDAOImpl.update(bucvo);					    		 
//							    	 }
//							    	
//							    	 bwBarrackCacheDAOImpl.delete(bvo);
//							     }else{//没有造完,根据开始时间来判断可以造几个兵,更新造好的记录,同时要判断是否满员
//							    	int createCount= produceSolderCount((int)bvo.getUsercharacterid(),bvo.getStartTime());
//							    	System.out.println("兵营中 已经造好的兵的数量:"+createCount);
//							    	if(createCount<bvo.getProducecount()){
//								    	 //1111
//								    	 int allCount=0;
//								    	 if(bucvo==null){
//									 			BwUserCharacterVO bwUserCharacterVO=new BwUserCharacterVO();
//												bwUserCharacterVO.setMailaddress(mailAddress);
//												bwUserCharacterVO.setCharactercount(createCount);
//												bwUserCharacterVO.setCharacterlevel(1);
//												bwUserCharacterVO.setChararchterid((int)bvo.getUsercharacterid());
////												bwUserCharacterVO.setUpgradecharacterfinishtime(getFinishTime(1,operate.getOperationTime(),operate.getCharacterId()).toString());
//												bwUserCharacterVO.setUpgradecharacterfinishtime("");
//												bwUserCharacterCacheDAOImpl.save(bwUserCharacterVO);
//												bwUserCharacterVO=null;
//									    	 }else{
//										    	  allCount=bucvo.getCharactercount()+createCount;
//										    	 //判断营地是否能够放下 先省去??????
//										    	 bucvo.setCharactercount(allCount);
//										    	 bwUserCharacterVOUpdateJMS.addBwUserCharacterVO(bucvo);
//										    	 bwUserCharacterCacheDAOImpl.update(bucvo);
//
//									    	 }
//								    	 //1111
//								    	 //同时更新兵营正在造兵的数量 ,也更新造兵的开始时间
//								    	 bvo.setStartTime(sdf.format(new Date()));
//								    	 bvo.setProducecount(bvo.getProducecount()-createCount);						    	
//								    	 bwBarrackCacheDAOImpl.update(bvo);
//							    	}
//							     }
//							     bwusercharactervo=null;
//								
//							}
//						}
//					}
                }
                if (barrackvoListResult.size() > 0) {
                    userManagerImpl.barrackProduce(bwuservo, barrackvoListResult, System.currentTimeMillis());
                }

                barrackUserMapIdList.clear();
                barrackUserMapIdList = null;
            }

            //获取已经造好的兵力 和魔法
            BwUserCharacterVO bwusercharactervo = new BwUserCharacterVO();
            bwusercharactervo.setMailaddress(bwuservo.getMailaddress());
            List<BwUserCharacterVO> ucList = bwUserCharacterCacheDAOImpl.queryBwUserCharacterVO(bwusercharactervo);
            for (BwUserCharacterVO ucvo : ucList) {
                BattleMatchingResponse.AttackerSoldiersAndSpell.Builder assBuilder = BattleMatchingResponse.AttackerSoldiersAndSpell.newBuilder();
                if (ucvo.getCharactercount() > 0) {
                    assBuilder.setCharacterIdOrSpellId(ucvo.getChararchterid());
                    assBuilder.setCount(ucvo.getCharactercount());
                    assBuilder.setCharacterOrSpellLevel(ucvo.getCharacterlevel());
                    assBuilder.setUsedType(0);
                    builder.addSoldiersAndSpellList(assBuilder.build());
                }
            }
            ucList = null;
            BwUserSpellVO bwuserspellvo = new BwUserSpellVO();
            bwuserspellvo.setMailaddress(mailAddress);
            List<BwUserSpellVO> bwuserspellvoList = bwUserSpellCacheDAOImpl.queryBwUserSpellVO(bwuserspellvo);
            if (null != bwuserspellvoList && bwuserspellvoList.size() > 0) {
                for (BwUserSpellVO bus : bwuserspellvoList) {
                    BattleMatchingResponse.AttackerSoldiersAndSpell.Builder assBuilder = BattleMatchingResponse.AttackerSoldiersAndSpell.newBuilder();
                    if (bus.getSpellcount() > 0) {
                        assBuilder.setCharacterIdOrSpellId(bus.getSpelltypeid());
                        assBuilder.setCount(bus.getSpellcount());
                        assBuilder.setCharacterOrSpellLevel(bus.getSpelllevel());
                        assBuilder.setUsedType(1);
                        builder.addSoldiersAndSpellList(assBuilder.build());
                    }
                }
                bwuserspellvoList = null;
                bwuserspellvo = null;
            }
            bwuservo.setShieldEndTime(null);
            bwUserVOUpdateJMS.addBwUserVO(bwuservo);
            bwUserCacheDAOImpl.update(bwuservo);
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            defencer_bwuservo = null;
            bwuservo = null;
            throw new ManagerServerException(e);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            defencer_bwuservo = null;
            bwuservo = null;
            throw new ManagerServerException(e);
        }

        defencer_bwuservo = null;
        bwuservo = null;

        return ErrorCodeInterface.SUCESS;
    }

    @Override
    public boolean checkBattleInfor() throws ManagerServerException {
        return false;
    }

    @Override
    public boolean createBattle(BwBattleVO bwBattleVO)
            throws ManagerServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean uplaodBattleAnimation() throws ManagerServerException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean uploadBattleResult() throws ManagerServerException {
        // TODO Auto-generated method stub
        return false;
    }

    public IOffLineManager getOffLineManager() {
        return offLineManager;
    }

    public void setOffLineManager(IOffLineManager offLineManager) {
        this.offLineManager = offLineManager;
    }

    public BwUserDAO getBwUserCacheDAOImpl() {
        return bwUserCacheDAOImpl;
    }

    public void setBwUserCacheDAOImpl(BwUserDAO bwUserCacheDAOImpl) {
        this.bwUserCacheDAOImpl = bwUserCacheDAOImpl;
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

    @Override
    public int saveBattleResult(UploadBattleResultRequest request)
            throws ManagerServerException {
        String attackMailAddress = request.getMailAddress();
        BwBattleVO bwbattlevo = new BwBattleVO();
        bwbattlevo.setAttacker(attackMailAddress);
        try {
            bwbattlevo = bwBattleCacheDAOImpl.queryBwBattleVOById(bwbattlevo);
            if (null == bwbattlevo) {
                return ErrorCodeInterface.ERROR;
            } else {
                String defencerMailAddress = bwbattlevo.getDefencer();
                //获取攻击者和防御者的信息
                BwUserVO defencer_bwuservo = new BwUserVO();
                defencer_bwuservo.setMailaddress(defencerMailAddress);
                defencer_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(defencer_bwuservo);
                //战斗结算
                //验证双方获取的pvp分数 开始
                //根据星数 计算攻守双方的分数 同时要判断守方的当前分数和最大pvp分数的大小比较
                //计算攻击者获取的pvp分数
                //参数k,n
                int arg_k = 32;
                int arg_n = 400;
                //攻击者当前的pvp分数
                long RA = bwbattlevo.getPvpmark();
                //防御者当前的 pvp分数
                long RB = defencer_bwuservo.getPvpmark();
                //攻击者获取到的分数
                int star_count = request.getStarCount();
                //相对于攻击者来说的 胜负结果
                int result_sa = star_count >= 1 ? 1 : 0;
                //现对于防守者来说的胜负结果
                int result_sb = star_count >= 1 ? 0 : 1;
                //预期攻击者的胜利分数
                double EA = 1 / (1 + Math.pow(10, ((RB - RA) / arg_n)));
                //预期防御者的胜利分数
                double EB = 1 / (1 + Math.pow(10, ((RA - RB) / arg_n)));

                //攻击者获取的实际分数
                double result = arg_k * (result_sa - EA) * (star_count + (1 + Math.pow(-1, result_sa)) / 2) / (Math.pow(3, result_sa));
                long actualAttackPVPMark = (long) Math.ceil(result);

                //防御者获取的实际分数
                long actualDefencerPVPMark = 0;
                BwUserBattleStatisticsVO bwuserbattlestatisticsDefancerVO = new BwUserBattleStatisticsVO();
                bwuserbattlestatisticsDefancerVO.setMailaddress(defencerMailAddress);
                bwuserbattlestatisticsDefancerVO = bwUserBattleStatisticsDaoImpl.queryBwUserBattleStatisticsVOById(bwuserbattlestatisticsDefancerVO);
                if (defencer_bwuservo.getPvpmark() < bwuserbattlestatisticsDefancerVO.getMaxpvpmark()) {
                    EA = 1 / (1 + Math.pow(10, ((bwuserbattlestatisticsDefancerVO.getMaxpvpmark() - RA) / arg_n)));
//					EB=1/(1+Math.pow(10, ((RA-bwuserbattlestatisticsDefancerVO.getMaxpvpmark())/arg_n)));
                    //还用原来的 比赛之后的得分公式
                    result = arg_k * (result_sa - EA) * (star_count + (1 + Math.pow(-1, result_sa)) / 2) / (Math.pow(3, result_sa));
//					result=arg_k*(result_sb-EB)*(star_count+(1+Math.pow(-1, result_sb))/2)/(Math.pow(3, result_sb));
                    //actualDefencerPVPMark=(long)Math.ceil(result);
                    actualDefencerPVPMark = -(long) Math.ceil(result);
                } else {
                    actualDefencerPVPMark = -actualAttackPVPMark;
                }
                //验证双方获取的pvp分数 结束
                defencer_bwuservo.setElixircount(defencer_bwuservo.getElixircount() - request.getElixirCount());
                defencer_bwuservo.setGoldencount(defencer_bwuservo.getGoldencount() - request.getGoldenCount());
                long resultPvp_defencer = defencer_bwuservo.getPvpmark() + actualDefencerPVPMark;
                defencer_bwuservo.setPvpmark(resultPvp_defencer >= 0 ? resultPvp_defencer : 0);
                //大于等于90%要保护16小时
                if (request.getStarPercent() >= 90) {//
                    Date t = new Date();
                    t.setTime(System.currentTimeMillis() + 16 * 60 * 60 * 1000);
                    defencer_bwuservo.setShieldEndTime(sdf.format(t));
                } else if (request.getStarPercent() >= 40) {//保护12小时
                    Date t = new Date();
                    t.setTime(System.currentTimeMillis() + 12 * 60 * 60 * 1000);
                    defencer_bwuservo.setShieldEndTime(sdf.format(t));
                }
                bwUserVOUpdateJMS.addBwUserVO(defencer_bwuservo);
                bwUserCacheDAOImpl.update(defencer_bwuservo);
                BwUserVO attack_bwuservo = new BwUserVO();
                attack_bwuservo.setMailaddress(attackMailAddress);
                attack_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(attack_bwuservo);
//			attack_bwuservo.setElixircount(attack_bwuservo.getElixircount()+request.getElixirCount());
//			attack_bwuservo.setGoldencount(attack_bwuservo.getGoldencount()+request.getGoldenCount());
                long resultPvp_attacker = attack_bwuservo.getPvpmark() + actualAttackPVPMark;
                attack_bwuservo.setPvpmark(resultPvp_attacker >= 0 ? resultPvp_attacker : 0);
                //bwUserCacheDAOImpl.update(attack_bwuservo);
                //更新攻击者的用户的存储仓库（金币和药水）
                int attackGetElixirCount = request.getElixirCount();
                int attackGetGoldencount = request.getGoldenCount();
                long remainElixirCount = attack_bwuservo.getMaxElixirCount() - attack_bwuservo.getElixircount();
                long remainGoldenCount = attack_bwuservo.getMaxGoldenCount() - attack_bwuservo.getGoldencount();
                if (attackGetElixirCount > remainElixirCount) {
                    userManagerImpl.harvestElixirOrGolden((byte) GameChargeType.ELIXIR_GAME_CHARGE.value(), null, attack_bwuservo, null, remainElixirCount);
                } else if (attackGetElixirCount > 0) {
                    userManagerImpl.harvestElixirOrGolden((byte) GameChargeType.ELIXIR_GAME_CHARGE.value(), null, attack_bwuservo, null, attackGetElixirCount);
                }
                if (attackGetGoldencount > remainGoldenCount) {
                    userManagerImpl.harvestElixirOrGolden((byte) GameChargeType.GOLDEN_GAME_CHARGE.value(), null, attack_bwuservo, null, remainGoldenCount);
                } else if (remainGoldenCount > 0) {
                    userManagerImpl.harvestElixirOrGolden((byte) GameChargeType.GOLDEN_GAME_CHARGE.value(), null, attack_bwuservo, null, attackGetGoldencount);
                }
//				if(request.getGoldenCount()>0){
//					userManagerImpl.harvestElixirOrGolden((byte)GameChargeType.GOLDEN_GAME_CHARGE.value(), null, attack_bwuservo, null, (long)request.getGoldenCount());
//				}
//				if(request.getElixirCount()>0){
//					userManagerImpl.harvestElixirOrGolden((byte)GameChargeType.ELIXIR_GAME_CHARGE.value(), null, attack_bwuservo, null, (long)request.getElixirCount());
//				}			
                //判断是否需要更新攻守双方用户的最大pvp分数
                //更新攻方
                BwUserBattleStatisticsVO bwuserbattlestatisticsvo = new BwUserBattleStatisticsVO();
                bwuserbattlestatisticsvo.setMailaddress(attackMailAddress);
                bwuserbattlestatisticsvo = bwUserBattleStatisticsDaoImpl.queryBwUserBattleStatisticsVOById(bwuserbattlestatisticsvo);
                if (attack_bwuservo.getPvpmark() > bwuserbattlestatisticsvo.getMaxpvpmark()) {
                    bwuserbattlestatisticsvo.setMaxpvpmark(attack_bwuservo.getPvpmark());
                    bwUserBattleStatisticsVOUpdateJMS.addBwUserBattleStatisticsVO(bwuserbattlestatisticsvo);
                    bwUserBattleStatisticsDaoImpl.update(bwuserbattlestatisticsvo);
                }

                //更新守方pvp分数
                if (defencer_bwuservo.getPvpmark() > bwuserbattlestatisticsDefancerVO.getMaxpvpmark()) {
                    bwuserbattlestatisticsDefancerVO.setMaxpvpmark(defencer_bwuservo.getPvpmark());
                    bwUserBattleStatisticsVOUpdateJMS.addBwUserBattleStatisticsVO(bwuserbattlestatisticsDefancerVO);
                    bwUserBattleStatisticsDaoImpl.update(bwuserbattlestatisticsDefancerVO);
                }

                //新增一条战斗结果记录
                //保存或者更新战斗列表
                BwBattleVO bwbattlevoForDefencer = new BwBattleVO();
                bwbattlevoForDefencer.setAttacker(attackMailAddress);
                bwbattlevoForDefencer.setBattletime(sdf.format(new java.util.Date()));
                bwbattlevoForDefencer.setDefencer(defencerMailAddress);
                bwbattlevoForDefencer.setPvpmark(-request.getPvpMark());
                bwbattlevoForDefencer.setGetelixir(request.getElixirCount());
                bwbattlevoForDefencer.setGetgolden(request.getGoldenCount());
                bwbattlevoForDefencer.setReplayName(defencerMailAddress + "_" + attackMailAddress + "_" + bwbattlevo.getBattleid());
                if (request.getCharacterUsedStatusListCount() > 0) {
                    List<BwBattleCharacterUsedCountVO> bwBattleCharacterUsedCountVOList = new ArrayList<BwBattleCharacterUsedCountVO>();
                    for (UploadBattleResultRequest.CharacterUsedStatus custatus : request.getCharacterUsedStatusListList()) {
                        BwBattleCharacterUsedCountVO temp = new BwBattleCharacterUsedCountVO();
                        temp.setCharacterIdOrSpellId(custatus.getCharacterIdOrSpellId());
                        temp.setCount(custatus.getCount());
                        temp.setUsedType(custatus.getUsedType());
                        bwBattleCharacterUsedCountVOList.add(temp);
                        //同时更新 攻击者的兵力,魔法数量
                        if (custatus.getUsedType() == 0) {//更新兵力
                            BwUserCharacterVO bwusercharactervo = new BwUserCharacterVO();
                            bwusercharactervo.setMailaddress(attackMailAddress);
                            bwusercharactervo.setChararchterid(custatus.getCharacterIdOrSpellId());
                            bwusercharactervo = bwUserCharacterCacheDAOImpl.queryBwUserCharacterVOById(bwusercharactervo);
                            int allCount = bwusercharactervo.getCharactercount() - custatus.getCount();
                            //判断营地是否能够放下 先省去??????
                            bwusercharactervo.setCharactercount(allCount > 0 ? allCount : 0);
                            bwUserCharacterVOUpdateJMS.addBwUserCharacterVO(bwusercharactervo);
                            bwUserCharacterCacheDAOImpl.update(bwusercharactervo);
                        } else if (custatus.getUsedType() == 1) {//更新魔法
                            BwUserSpellVO bwuserspellvo = new BwUserSpellVO();
                            bwuserspellvo.setMailaddress(attackMailAddress);
                            bwuserspellvo.setSpelltypeid(custatus.getCharacterIdOrSpellId());
                            bwuserspellvo = bwUserSpellCacheDAOImpl.queryBwUserSpellVOById(bwuserspellvo);
                            int allCount = bwuserspellvo.getSpellcount() - custatus.getCount();
                            bwuserspellvo.setSpellcount(allCount > 0 ? allCount : 0);
                            bwUserSpellVOUpdateJMS.addBwUserSpellVO(bwuserspellvo);
                            bwUserSpellCacheDAOImpl.update(bwuserspellvo);
                        }
                    }
                    bwbattlevoForDefencer.setBwBattleCharacterUsedCountVOList(bwBattleCharacterUsedCountVOList);
                    bwbattlevoForDefencer.setUseClansFlag(request.getUseClansFlag());
                    bwbattlevoForDefencer.setStarCount(request.getStarCount());
                    bwBattleCacheDAOImpl.saveBattledList(defencerMailAddress, bwbattlevoForDefencer);
                    bwBattleCharacterUsedCountVOList.clear();
                    bwBattleCharacterUsedCountVOList = null;
                    bwbattlevoForDefencer = null;

                }
                //保存建筑物被摧毁的信息,包含僵尸,以及没有被彻底摧毁的(大厅,金币,药水) 对于每一个用户来说只有一份
                if (request.getBuildDistoryStatusListCount() > 0) {//
                    List<BwBattleDestoryVO> bwBattleDestoryVOList = new ArrayList<BwBattleDestoryVO>();
                    for (UploadBattleResultRequest.BuildDestoryStatus bds : request.getBuildDistoryStatusListList()) {
                        //判断一下build_id 和 destory_status
                        if (bds.getDestoryStatus() == 0) {//全部损坏
                            BwBattleDestoryVO bwBattleDestoryVO = new BwBattleDestoryVO();
                            bwBattleDestoryVO.setBuilding_id(bds.getBuildingId());
                            bwBattleDestoryVO.setMap_index_x(bds.getMapIndexX());
                            bwBattleDestoryVO.setMap_index_y(bds.getMapIndexY());
                            bwBattleDestoryVO.setUuid(bds.getUuid());
                            bwBattleDestoryVOList.add(bwBattleDestoryVO);
                            //在判断是否是资源建筑 大厅,金币,药水
                            //如果是大厅需要给攻击者增加 经验
                            if (ResourceType.TOWN_HALL == CommonMethod.getResourceType(bds.getBuildingId())) {
                                BwUserMapDataVO bwusermapdatavoTemp = new BwUserMapDataVO();
                                bwusermapdatavoTemp.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
                                bwusermapdatavoTemp.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
                                bwusermapdatavoTemp.setMailaddress(attackMailAddress);
                                bwusermapdatavoTemp = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoTemp);
                                String buildingPropertiesKey = bds.getBuildingId() + "_" + bwusermapdatavoTemp.getBuildlevel();
                                BwBuildingPropertiesLevelVO bbplvo = ResGlobal.getInstance().bwBuildingPropertiesLevelVOMap.get(buildingPropertiesKey);
                                long exp = bbplvo.getDestructionxp();
                                //增加经验和升级
                                ResHelper.addExp(attack_bwuservo, (int) exp);
                                //attack_bwuservo.setExp(attack_bwuservo.getExp()+exp);
                            }
                        } else {//部分损坏
                            //在判断是否是资源建筑 大厅,金币,药水
                        }
                        //不管是全部或者是部分都要重置金币或者药水的
                        ResourceType resourceType = CommonMethod.getResourceType(bds.getBuildingId());
                        if (CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA == bds.getBuildingId()) {
                            BwUserMapDataVO bwusermapdatavoT = new BwUserMapDataVO();
                            bwusermapdatavoT.setMailaddress(defencerMailAddress);
                            bwusermapdatavoT.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
                            bwusermapdatavoT.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
                            bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);

                            BwMineCollectorAllVO bwMineCollectorAllVO = new BwMineCollectorAllVO();
                            bwMineCollectorAllVO.setUserbuildingdataid((bwusermapdatavoT.getId()));
                            bwMineCollectorAllVO = bwMineCollectorAllCacheDAOImpl.queryBwMineCollectorAllVOById(bwMineCollectorAllVO);
                            bwMineCollectorAllVO.setCollectcount(bwMineCollectorAllVO.getCollectcount() - bds.getLoseCount());
                            bwMineCollectorAllVO.setSecondElixirCount(bwMineCollectorAllVO.getSecondElixirCount() - bds.getLoseCount());
                            bwMineCollectorAllVOUpdateJMS.addBwMineCollectorAllVO(bwMineCollectorAllVO);
                            bwMineCollectorAllCacheDAOImpl.update(bwMineCollectorAllVO);
                            bwusermapdatavoT = null;
                            break;
                        }
                        switch (resourceType) {
                            case GOLD_MINE:
                            //查询具体的信息,然后在update
                            case ELIXIR_PUMP:
                                BwMineCollectorVO bwminecollectorvo = new BwMineCollectorVO();

                                BwUserMapDataVO bwusermapdatavoT = new BwUserMapDataVO();
                                bwusermapdatavoT.setMailaddress(defencerMailAddress);
                                bwusermapdatavoT.setUniquenessbuildid(bds.getUuid());
                                bwusermapdatavoT.setBuildid(bds.getBuildingId());
                                bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);
                                bwminecollectorvo.setUserbuildingdataid(bwusermapdatavoT.getId());
                                bwminecollectorvo = bwMineCollectorCacheDAOImpl.queryBwMineCollectorVOById(bwminecollectorvo);
                                if (null != bwminecollectorvo) {
                                    bwminecollectorvo.setStatus(0);
                                    bwminecollectorvo.setHarveststarttime(sdf.format(new Date()));
                                    bwminecollectorvo.setProducecount(bwminecollectorvo.getProducecount() - bds.getLoseCount());
                                    //umd.setHarvestStartTime(bwminecollectorvo.getHarveststarttime());
                                    //更新数据库和cache
                                    bwMineCollectorVOUpdateJMS.addBwMineCollectorVO(bwminecollectorvo);
                                    bwMineCollectorCacheDAOImpl.update(bwminecollectorvo);
                                }
                                bwusermapdatavoT = null;
                                bwminecollectorvo = null;
                                break;
                            case GOLD_STORAGE:
                            case ELIXIR_STORAGE:
                                BwMineCollectorAllVO bwMineCollectorAllVO = new BwMineCollectorAllVO();
                                BwUserMapDataVO bwusermapdatavoTT = new BwUserMapDataVO();
                                bwusermapdatavoTT.setMailaddress(defencerMailAddress);
                                bwusermapdatavoTT.setUniquenessbuildid(bds.getUuid());
                                bwusermapdatavoTT.setBuildid(bds.getBuildingId());
                                bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoTT);
                                bwMineCollectorAllVO.setUserbuildingdataid((bwusermapdatavoT.getId()));
                                bwMineCollectorAllVO = bwMineCollectorAllCacheDAOImpl.queryBwMineCollectorAllVOById(bwMineCollectorAllVO);
                                bwMineCollectorAllVO.setCollectcount(bwMineCollectorAllVO.getCollectcount() - bds.getLoseCount());
                                bwMineCollectorAllVOUpdateJMS.addBwMineCollectorAllVO(bwMineCollectorAllVO);
                                bwMineCollectorAllCacheDAOImpl.update(bwMineCollectorAllVO);
                                bwusermapdatavoT = null;
                                bwMineCollectorAllVO = null;
                                break;
//						case GOLD_MINE:
//							break;

                        }
                        //111111111
//						BwBattleDestoryVO bwBattleDestoryVO=new BwBattleDestoryVO();
//						bwBattleDestoryVO.setBuilding_id(bds.getBuildingId());
//						bwBattleDestoryVO.setMap_index_x(bds.getMapIndexX());
//						bwBattleDestoryVO.setMap_index_y(bds.getMapIndexY());
//						bwBattleDestoryVO.setUuid(bds.getUuid());
//						bwBattleDestoryVOList.add(bwBattleDestoryVO);
                    }
                    bwBattleCacheDAOImpl.updateBattleDestory(defencerMailAddress, bwBattleDestoryVOList);
                }
                bwUserCacheDAOImpl.update(attack_bwuservo);
                //修改用户的在线状态,有战斗状态改为先下状态
                //需要判断一下是否有保护时间,没有保护时间的情况下,再重新加入战斗匹配序列里面 暂时屏蔽掉
//				if(null==defencer_bwuservo.getShieldEndTime()||"".equalsIgnoreCase(defencer_bwuservo.getShieldEndTime())){
//					offLineManager.updateBattleLineStatus(defencerMailAddress, LineStatusEnum.OFF_LINE);
//				}
                //打完就直接放进去 用于测试 后面
                offLineManager.updateBattleLineStatus(defencerMailAddress, LineStatusEnum.OFF_LINE);
            }
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return 0;
    }

    public BwBattleDAO getBwBattleCacheDAOImpl() {
        return bwBattleCacheDAOImpl;
    }

    public void setBwBattleCacheDAOImpl(BwBattleDAO bwBattleCacheDAOImpl) {
        this.bwBattleCacheDAOImpl = bwBattleCacheDAOImpl;
    }

    @Override
    public int cancleBattle(CancleBattleRequest request)
            throws ManagerServerException {
        String mailAddress = request.getMailAddress();
        BwBattleVO bwbattlevo = new BwBattleVO();
        bwbattlevo.setAttacker(mailAddress);
        //清楚战斗数据
        try {
            bwbattlevo = bwBattleCacheDAOImpl.queryBwBattleVOById(bwbattlevo);
            String defencerMailAddress = bwbattlevo.getDefencer();
            bwBattleCacheDAOImpl.delete(bwbattlevo);
            bwbattlevo = null;
            //清楚用户的战斗状态
            offLineManager.updateBattleLineStatus(defencerMailAddress, LineStatusEnum.OFF_LINE);
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return 0;
    }

    /* (non-Javadoc)
	 * 开始战斗时 战斗的等待时间更精确
     */
    @Override
    public int startBattle(BattleStartRequest request, BattleStartResponse.Builder build)
            throws ManagerServerException {
        //默认是3分钟30秒
        try {
            String mailAddress = request.getMailAddress();
            BwBattleVO tempBattlevo = new BwBattleVO();
            tempBattlevo.setAttacker(mailAddress);
            tempBattlevo.setDefencer(request.getDefenceMailAddress());
            tempBattlevo = bwBattleCacheDAOImpl.queryBwBattleVOById(tempBattlevo);
            if (null == tempBattlevo) {
                return ErrorCodeInterface.ERROR;
            } else {//更新被攻击者的战斗时间
                tempBattlevo.setBattletime(sdf.format(new Date()));
                //获取被攻击者的信息 更新被攻击时间   不更新的话 是 默认的3分钟30秒 
                BattleLineVO blvo = battleLineDAOImpl.getBattleLineVO(request.getDefenceMailAddress());
                blvo.setBattleStartTime(new Date());
                battleLineDAOImpl.updateBattleLineVO(blvo);
                blvo = null;
                tempBattlevo.setBattleid(System.currentTimeMillis() / (1000 * 60));
                //bwBattleVOUpdateJMS.addBwBattleVO(tempBattlevo);
                long verifyCode = CommonMethod.creatVerifyCode();
                tempBattlevo.setVerifyCode(verifyCode);
                bwBattleCacheDAOImpl.update(tempBattlevo);
                build.setBattleId(tempBattlevo.getBattleid());
                build.setVerifyCode(verifyCode);

            }
            tempBattlevo = null;
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return 0;
    }

    @Override
    public int uploadBattleResultPVE(UploadBattleResultPVERequest request)
            throws ManagerServerException {
        try {
            BwUserVO attack_bwuservo = new BwUserVO();
            attack_bwuservo.setMailaddress(request.getMailAddress());
            attack_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(attack_bwuservo);
            attack_bwuservo.setElixircount(attack_bwuservo.getElixircount() + request.getElixirCount());
            attack_bwuservo.setGoldencount(attack_bwuservo.getGoldencount() + request.getGoldenCount());
            attack_bwuservo.setPvpmark(attack_bwuservo.getPvpmark() + request.getPvpMark());
            bwUserVOUpdateJMS.addBwUserVO(attack_bwuservo);
            bwUserCacheDAOImpl.update(attack_bwuservo);
        } catch (CacheDaoException e) {
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return 0;
    }

    @Override
    public void downloadBattleInforList(String mailAddress, DownloadBattleInforResponse.Builder builder, int countFlag)
            throws ManagerServerException {

        try {
            List<BwBattleVO> tempList = bwBattleCacheDAOImpl.queryBwBattleVO(mailAddress);

            if (null == tempList) {
                return;
            } else {
                long getCount = bwBattleCacheDAOImpl.queryBwBattleVOCountFlag(mailAddress);
                if (countFlag == 0) {//获取部分
                    if (getCount > 0) {
                        int battleListCount = tempList.size();
                        for (long x = getCount; x > 0; x--) {
                            battleListCount--;
                            BwBattleVO bbvo = tempList.get(battleListCount);
                            DownloadBattleInforResponse.BattleInfor.Builder bi = DownloadBattleInforResponse.BattleInfor.newBuilder();
                            //根据mail地址获取攻击者的信息 
                            BwUserVO attack_bwuservo = new BwUserVO();
                            attack_bwuservo.setMailaddress(bbvo.getAttacker());
                            attack_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(attack_bwuservo);
                            bi.setBowieId(attack_bwuservo.getMailaddress());
                            bi.setAttackerLevel(attack_bwuservo.getLevel());
                            bi.setAttackerNickName(attack_bwuservo.getNickname());
                            bi.setAttackTime(bbvo.getBattletime());
                            bi.setDefencerGetPvpMark(-(int) bbvo.getPvpmark());
                            bi.setClansImageName("clans_1000");
                            bi.setClansName("blus");
                            bi.setAttackerGetElixirCount((int) bbvo.getGetgolden());
                            bi.setAttackerGetGoldenCount((int) bbvo.getGetelixir());
                            bi.setReplyFileName(bbvo.getReplayName());
                            bi.setStarCount(bbvo.getStarCount());
                            List<BwBattleCharacterUsedCountVO> bcuedList = bbvo.getBwBattleCharacterUsedCountVOList();
                            for (BwBattleCharacterUsedCountVO bcuvo : bcuedList) {
                                DownloadBattleInforResponse.BattleInfor.AttackerUsed.Builder attackUsed = DownloadBattleInforResponse.BattleInfor.AttackerUsed.newBuilder();
                                attackUsed.setUsedId(bcuvo.getCharacterIdOrSpellId());
                                attackUsed.setUsedCount(bcuvo.getCount());
                                attackUsed.setUsedType(bcuvo.getUsedType());
                                bi.addAttackerUsedList(attackUsed.build());
                            }
                            builder.addBattleInforList(bi.build());
                        }
                    }
                } else if (countFlag == 1) {//获取所有
                    for (BwBattleVO bbvo : tempList) {
                        DownloadBattleInforResponse.BattleInfor.Builder bi = DownloadBattleInforResponse.BattleInfor.newBuilder();
                        //根据mail地址获取攻击者的信息 
                        BwUserVO attack_bwuservo = new BwUserVO();
                        attack_bwuservo.setMailaddress(bbvo.getAttacker());
                        attack_bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(attack_bwuservo);
                        bi.setBowieId(attack_bwuservo.getMailaddress());
                        bi.setAttackerLevel(attack_bwuservo.getLevel());
                        bi.setAttackerNickName(attack_bwuservo.getNickname());
                        bi.setAttackTime(bbvo.getBattletime());
                        bi.setDefencerGetPvpMark(-(int) bbvo.getPvpmark());
                        bi.setClansImageName("clans_1000");
                        bi.setClansName("blus");
                        bi.setAttackerGetElixirCount((int) bbvo.getGetgolden());
                        bi.setAttackerGetGoldenCount((int) bbvo.getGetelixir());
                        bi.setStarCount(bbvo.getStarCount());
                        bi.setReplyFileName(bbvo.getReplayName());
                        List<BwBattleCharacterUsedCountVO> bcuedList = bbvo.getBwBattleCharacterUsedCountVOList();
                        for (BwBattleCharacterUsedCountVO bcuvo : bcuedList) {
                            DownloadBattleInforResponse.BattleInfor.AttackerUsed.Builder attackUsed = DownloadBattleInforResponse.BattleInfor.AttackerUsed.newBuilder();
                            attackUsed.setUsedId(bcuvo.getCharacterIdOrSpellId());
                            attackUsed.setUsedCount(bcuvo.getCount());
                            attackUsed.setUsedType(bcuvo.getUsedType());
                            bi.addAttackerUsedList(attackUsed.build());
                        }
                        builder.addBattleInforList(bi.build());
                    }
                }
                bwBattleCacheDAOImpl.updateBwBattleVOCountFlag(mailAddress, 0);

            }
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
    }

    public BattleLineDAO getBattleLineDAOImpl() {
        return battleLineDAOImpl;
    }

    public void setBattleLineDAOImpl(BattleLineDAO battleLineDAOImpl) {
        this.battleLineDAOImpl = battleLineDAOImpl;
    }

    public BwUserBattleStatisticsDAO getBwUserBattleStatisticsDaoImpl() {
        return bwUserBattleStatisticsDaoImpl;
    }

    public void setBwUserBattleStatisticsDaoImpl(
            BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl) {
        this.bwUserBattleStatisticsDaoImpl = bwUserBattleStatisticsDaoImpl;
    }

    public BwUserSpellDAO getBwUserSpellCacheDAOImpl() {
        return bwUserSpellCacheDAOImpl;
    }

    public void setBwUserSpellCacheDAOImpl(BwUserSpellDAO bwUserSpellCacheDAOImpl) {
        this.bwUserSpellCacheDAOImpl = bwUserSpellCacheDAOImpl;
    }

    public BwUserCharacterDAO getBwUserCharacterCacheDAOImpl() {
        return bwUserCharacterCacheDAOImpl;
    }

    public void setBwUserCharacterCacheDAOImpl(
            BwUserCharacterDAO bwUserCharacterCacheDAOImpl) {
        this.bwUserCharacterCacheDAOImpl = bwUserCharacterCacheDAOImpl;
    }

    public BwBarrackDAO getBwBarrackCacheDAOImpl() {
        return bwBarrackCacheDAOImpl;
    }

    public void setBwBarrackCacheDAOImpl(BwBarrackDAO bwBarrackCacheDAOImpl) {
        this.bwBarrackCacheDAOImpl = bwBarrackCacheDAOImpl;
    }

    public int produceSolderCount(int characterId, String startTime) {
        int count = 0;
        //找出兵的类型
        try {
            BwCharactersVO bwCharactersVO = ResGlobal.getInstance().bwCharactersVOMap.get((long) (characterId));
            //以秒为单位
            long createSolderTime = bwCharactersVO.getTrainingtime();
            long usedTime = (System.currentTimeMillis() - sdf.parse(startTime).getTime()) / 1000;
            count = (int) (usedTime / createSolderTime);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return count;
    }

    @Override
    public int revengeBattle(Builder builder, String mailAddress,
            String defanceMailAddress) throws ManagerServerException {
        //复仇的检测规则
        //判断被攻击者是否在线,或者正在被攻击
        try {
            //先判断攻击者是否有充足的金币
            BwUserVO bwuservo = new BwUserVO();
            bwuservo.setMailaddress(defanceMailAddress);
            bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(bwuservo);
            //去掉金币检测
//			BwUserMapDataVO bwusermapdatavoT=new BwUserMapDataVO();
//			bwusermapdatavoT.setMailaddress(mailAddress);
//			bwusermapdatavoT.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
//			bwusermapdatavoT.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
//			bwusermapdatavoT=bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);
//			BwHallBuildsRelationVO bhbrvo=ResGlobal.getInstance().bwHallBuildsRelationVOMap.get((long)bwusermapdatavoT.getBuildlevel());
//			
//			bwuservo.setGoldencount(bwuservo.getGoldencount()-bhbrvo.getAttackcost());
//			if(bwuservo.getGoldencount()<0){//金币不足
//				bwuservo=null;
//				return ErrorCodeInterface.NO_ENOUGH_GOLDEN;
//			}

            //
            BwUserVO bwuservoDefencer = new BwUserVO();
            bwuservoDefencer.setMailaddress(defanceMailAddress);
            bwuservoDefencer = bwUserCacheDAOImpl.queryBwUserVOById(bwuservoDefencer);
            BattleLineVO blvo = battleLineDAOImpl.getBattleLineVO(defanceMailAddress);
            //如果在保护期也无法复仇
            if (bwuservoDefencer.getShieldEndTime() != null && !"".equalsIgnoreCase(bwuservoDefencer.getShieldEndTime())) {
                return ErrorCodeInterface.USER_IS_IN_SHIELD_TIME;
            }
            if (blvo != null) {
                //如果正在战斗,或者在线状态就无法复仇
                if (blvo.getLineStatus() == LineStatusEnum.BATTLE_LINE.value()) {
                    return ErrorCodeInterface.USER_IS_BATTLEING;
                } else if (blvo.getLineStatus() == LineStatusEnum.ON_LINE.value()) {
                    return ErrorCodeInterface.USER_IS_ONLINE_NOT_REVENGE;
                }
            }
            //结束
            bwuservoDefencer = null;
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
        }
        return ErrorCodeInterface.SUCESS;
    }

    public IUserManager getUserManagerImpl() {
        return userManagerImpl;
    }

    public void setUserManagerImpl(IUserManager userManagerImpl) {
        this.userManagerImpl = userManagerImpl;
    }

    public BwUserVOUpdateJMS getBwUserVOUpdateJMS() {
        return bwUserVOUpdateJMS;
    }

    public void setBwUserVOUpdateJMS(BwUserVOUpdateJMS bwUserVOUpdateJMS) {
        this.bwUserVOUpdateJMS = bwUserVOUpdateJMS;
    }

    public BwBattleVOUpdateJMS getBwBattleVOUpdateJMS() {
        return bwBattleVOUpdateJMS;
    }

    public void setBwBattleVOUpdateJMS(BwBattleVOUpdateJMS bwBattleVOUpdateJMS) {
        this.bwBattleVOUpdateJMS = bwBattleVOUpdateJMS;
    }

    public BwMineCollectorAllVOUpdateJMS getBwMineCollectorAllVOUpdateJMS() {
        return bwMineCollectorAllVOUpdateJMS;
    }

    public void setBwMineCollectorAllVOUpdateJMS(
            BwMineCollectorAllVOUpdateJMS bwMineCollectorAllVOUpdateJMS) {
        this.bwMineCollectorAllVOUpdateJMS = bwMineCollectorAllVOUpdateJMS;
    }

    public BwMineCollectorVOUpdateJMS getBwMineCollectorVOUpdateJMS() {
        return bwMineCollectorVOUpdateJMS;
    }

    public void setBwMineCollectorVOUpdateJMS(
            BwMineCollectorVOUpdateJMS bwMineCollectorVOUpdateJMS) {
        this.bwMineCollectorVOUpdateJMS = bwMineCollectorVOUpdateJMS;
    }

    public BwUserBattleStatisticsVOUpdateJMS getBwUserBattleStatisticsVOUpdateJMS() {
        return bwUserBattleStatisticsVOUpdateJMS;
    }

    public void setBwUserBattleStatisticsVOUpdateJMS(
            BwUserBattleStatisticsVOUpdateJMS bwUserBattleStatisticsVOUpdateJMS) {
        this.bwUserBattleStatisticsVOUpdateJMS = bwUserBattleStatisticsVOUpdateJMS;
    }

    public BwUserCharacterVOUpdateJMS getBwUserCharacterVOUpdateJMS() {
        return bwUserCharacterVOUpdateJMS;
    }

    public void setBwUserCharacterVOUpdateJMS(
            BwUserCharacterVOUpdateJMS bwUserCharacterVOUpdateJMS) {
        this.bwUserCharacterVOUpdateJMS = bwUserCharacterVOUpdateJMS;
    }

    public BwUserSpellVOUpdateJMS getBwUserSpellVOUpdateJMS() {
        return bwUserSpellVOUpdateJMS;
    }

    public void setBwUserSpellVOUpdateJMS(
            BwUserSpellVOUpdateJMS bwUserSpellVOUpdateJMS) {
        this.bwUserSpellVOUpdateJMS = bwUserSpellVOUpdateJMS;
    }

}
