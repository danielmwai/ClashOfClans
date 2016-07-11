package com.bw.application.manager.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.bw.application.config.AppConfig;
import com.bw.application.exception.ManagerServerException;
import com.bw.application.manager.IOffLineManager;
import com.bw.baseJar.common.CommonGameData;
import com.bw.baseJar.common.LineStatusEnum;
import com.bw.cache.vo.BattleLineVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.dao.BattleLineDAO;
import com.bw.dao.BwUserBattleStatisticsDAO;
import com.bw.dao.BwUserDAO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.exception.CacheDaoException;

public class OffLineManagerImpl implements IOffLineManager {

    private AppConfig appConfig;
    /**
     * pvp分数区域
     */
    private String[] keys;
    public BattleLineDAO battleLineDAOImpl;
    /**
     * 按pvp分数划分的防御性用户的视图
     */
//	private ConcurrentHashMap<String, ConcurrentHashMap<String, java.util.Date>> defenceQueueMap = null;
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> defenceQueueMap = null;
    private AtomicBoolean started = new AtomicBoolean(false);
    private ConcurrentHashMap<String, java.util.Date> offhashm = null;
    public BwUserDAO bwUserCacheDAOImpl;
    public BwUserMapDataDAO bwUserMapDataCacheDAOImpl;
    //战斗统计
    public BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl;
    int stageOfPer = 0;
    int maxPVPGrade = 0;

    public void start() {
        if (this.started.compareAndSet(false, true)) {
            stageOfPer = appConfig.getStageOfPer();
            maxPVPGrade = appConfig.getMaxPVPGrade();
            defenceQueueMap = new ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>();
            int size = maxPVPGrade / stageOfPer;
            keys = new String[size];
            for (int i = 1; i < size + 1; i++) {
                String key = String.valueOf(i * stageOfPer);
                defenceQueueMap.put(key, new ConcurrentLinkedQueue<String>());
                keys[i - 1] = key;
            }
            loadOfflineUser();
        }
    }

    @Override
    public boolean IsContainMember(String mailAddress, int pvpMark)
            throws ManagerServerException {
        pvpMark = this.getMaxPVPMark(mailAddress, pvpMark);
        String pvpLevel = this.checkPVPMark(pvpMark);
        ConcurrentLinkedQueue<String> pvpLevelHashmap = defenceQueueMap
                .get(pvpLevel);
        if (pvpLevelHashmap == null) {
            pvpLevelHashmap = new ConcurrentLinkedQueue<String>();
        }
        return pvpLevelHashmap.contains(mailAddress);
    }

    @Override
    public void addOfflineMember(String mailAddress, int pvpMark)
            throws ManagerServerException {
        String pvpLevel = "0";
        try {
            pvpMark = this.getMaxPVPMark(mailAddress, pvpMark);
            //Remove lower than the other stages of this phase value inside
            //移除低于此阶段的其它阶段里面的值
            for (int i = 0; i < keys.length; i++) {
                int keyLevel = new Integer(keys[i]).intValue();
                if (pvpMark <= keyLevel) {
                    pvpLevel = keys[i];
                    break;
                }
//			else{//去除原来可能相同的key 因为用户升级造成的 影响性能先屏蔽掉
                //Removal in the same key as the original may cause users to upgrade the first masked
//				List<String> pvpLevelHashmap = defenceQueueMap
//				.get(keyLevel);
//				if(pvpLevelHashmap!=null){
//					pvpLevelHashmap.remove(mailAddress);
//				}
//			}
            }
            ConcurrentLinkedQueue<String> pvpLevelHashmap = defenceQueueMap
                    .get(pvpLevel);
            if (pvpLevelHashmap == null) {
                pvpLevelHashmap = new ConcurrentLinkedQueue<String>();

            }
            pvpLevelHashmap.add(mailAddress);
            this.updateBattleLineStatus(mailAddress, LineStatusEnum.OFF_LINE);
//			defenceQueueMap.put(pvpLevel, pvpLevelHashmap);

        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException();
        }
    }

    @Override
    public void removeOfflineMember(String mailAddress, int pvpMark, LineStatusEnum lineStatus)
            throws ManagerServerException {//是队列此方法就没有意义
//		String pvpLevel = this.checkPVPMark(pvpMark);
//		LinkedList<String> pvpLevelHashmap = (LinkedList<String>)defenceQueueMap
//				.get(pvpLevel);
//		if (pvpLevelHashmap == null) {
//			pvpLevelHashmap =(LinkedList<String>)Collections.synchronizedList(new LinkedList<String>());
//		}
//		
//		pvpLevelHashmap.poll();
    }

    public void stop() {
        if (this.started.compareAndSet(true, false)) {
            if (this.offhashm != null) {
                this.offhashm.clear();
                this.offhashm = null;
            }

        }
    }

    /**
     * 根据玩家pvp分数，判断所在的区域
     *
     * @param level
     * @return
     */
    public String checkPVPMark(int level) {
        for (int i = 0; i < keys.length; i++) {
            int keyLevel = new Integer(keys[i]).intValue();
            if (level <= keyLevel) {
                return keys[i];
            }
        }
        return null;
    }

    public BwUserDAO getBwUserCacheDAOImpl() {
        return bwUserCacheDAOImpl;
    }

    public void setBwUserCacheDAOImpl(BwUserDAO bwUserCacheDAOImpl) {
        this.bwUserCacheDAOImpl = bwUserCacheDAOImpl;
    }

    public String battleMatching(String mailAddress, int pvpMark) throws ManagerServerException {
        int maxPVPMark = this.getMaxPVPMark(mailAddress, pvpMark);
        String pvpLevel = this.checkPVPMark(maxPVPMark);
//		LinkedList tempList=new LinkedList();
//		String temp="";
//		tempList.contains(temp);
        ConcurrentLinkedQueue<String> pvpLevelHashmap = defenceQueueMap.get(pvpLevel);
        if (pvpLevelHashmap == null) {
            int upflag = 0;
            int downflag = 0;
            //向下再找一段
            int downLevel = Integer.parseInt(pvpLevel.trim()) - stageOfPer;
            downLevel = downLevel > 0 ? downLevel : 0;
            pvpLevelHashmap = defenceQueueMap.get(downLevel);
            //向上再找一段
            if (pvpLevelHashmap == null) {
                downflag = 1;
                int upLevel = Integer.parseInt(pvpLevel.trim()) + stageOfPer;
                upLevel = upLevel > maxPVPGrade ? maxPVPGrade : upLevel;
                pvpLevelHashmap = defenceQueueMap.get(upLevel);
            }
            if (pvpLevelHashmap == null) {
                upflag = 1;

            }
            if (downflag == 1 && upflag == 1) {
                //pvpLevelHashmap = new ConcurrentLinkedQueue<String>();
                return null;
            }

        }
        while (!pvpLevelHashmap.isEmpty() && pvpLevelHashmap.size() > 0) {
            String key = pvpLevelHashmap.poll();
            if (pvpLevelHashmap.size() <= 0) {
                this.loadOfflineUser();
            }
            BattleLineVO blvo = battleLineDAOImpl.getBattleLineVO(key);
            //找不到信息或者已经在战斗状态
            if (null == blvo) {
                continue;
            }
            if (blvo.getLineStatus() == LineStatusEnum.BATTLE_LINE.value()) {
                pvpLevelHashmap.remove(blvo.getMailAddress());
                continue;
            }
            if (blvo.getLineStatus() == LineStatusEnum.WAITING_LINE.value()) {
                pvpLevelHashmap.remove(blvo.getMailAddress());
                continue;
            }
            if (blvo.getMailAddress().equalsIgnoreCase(mailAddress)) {
                pvpLevelHashmap.remove(mailAddress);
                continue;
            }
            BwUserVO bwuservo = new BwUserVO();
            bwuservo.setMailaddress(blvo.getMailAddress());
            bwuservo = bwUserCacheDAOImpl.queryBwUserVOById(bwuservo);
            if (null == bwuservo) {
                continue;
            }
//			int defencer_pvpMark=(int)bwuservo.getPvpmark();
//			//根据pvp分数获取对应的等级
//			int defenceLevel= Integer.parseInt(this.checkPVPMark(defencer_pvpMark));
//			 defenceLevel=this.getMaxPVPMark(bwuservo.getMailaddress(), defenceLevel);
//			 //因为可以上下跨阶段所以 去掉次判断
//			if(defenceLevel!=Integer.parseInt(pvpLevel)){//要判断防御者的 pvpmark分数等级是否已经过时了(因为用户升级)??????
//				//不是同一个等级,要继续查找
//				continue;
//			}
            blvo.setBattleStartTime(new java.util.Date());
            blvo.setLineStatus(LineStatusEnum.BATTLE_LINE.value());
            if (battleLineDAOImpl.updateBattleLineVO(blvo)) {
                return blvo.getMailAddress();
            }
        }
//		for(String key:pvpLevelHashmap.keySet()){
//			BattleLineVO blvo=battleLineDAOImpl.getBattleLineVO(key);
//			//找不到信息或者已经在战斗状态
//			if(null==blvo){
//				continue;
//			}
//			if(blvo.getLineStatus()==LineStatusEnum.BATTLE_LINE.value()){
//				pvpLevelHashmap.remove(mailAddress);
//				continue;
//			}
//			blvo.setBattleStartTime(new java.util.Date());
//			blvo.setLineStatus(LineStatusEnum.BATTLE_LINE.value());
//			if(battleLineDAOImpl.updateBattleLineVO(blvo)){
//				return blvo.getMailAddress();
//			}
//		}
        return null;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public BattleLineDAO getBattleLineDAOImpl() {
        return battleLineDAOImpl;
    }

    public void setBattleLineDAOImpl(BattleLineDAO battleLineDAOImpl) {
        this.battleLineDAOImpl = battleLineDAOImpl;
    }

    /* (non-Javadoc)
	 * @see com.bw.application.manager.IOffLineManager#updateBattleLineStatus(java.lang.String, com.bw.baseJar.common.LineStatusEnum)
	 * 目前只用到 修改为 offline 状态
     */
    @Override
    public boolean updateBattleLineStatus(String mailAddress,
            LineStatusEnum lineStatus) throws ManagerServerException {
        //修改用户的在线状态

        try {
            BattleLineVO battleLive = battleLineDAOImpl.getBattleLineVO(mailAddress);
            if (null != battleLive) {
                //如果用户处于wait状态则 不在更新为offline状态
                if (battleLive.getLineStatus() == LineStatusEnum.WAITING_LINE.value()) {
                    return true;
                }
                battleLive.setLineStatus(lineStatus.value());
                battleLineDAOImpl.updateBattleLineVO(battleLive);
            } else {
                battleLive = new BattleLineVO();
                battleLive.setMailAddress(mailAddress);
                battleLive.setCasflag(0);
                battleLive.setLineStatus(lineStatus.value());
                battleLineDAOImpl.updateBattleLineVONoCas(battleLive);
            }
            battleLive = null;
        } catch (CacheDaoException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int getMaxPVPMark(String mailAddress, int currentPVPMark) throws ManagerServerException {
        int town_level;
        int maxPVPMark;
        BwUserBattleStatisticsVO bwuserbattlestatisticsvo = new BwUserBattleStatisticsVO();
        bwuserbattlestatisticsvo.setMailaddress(mailAddress);
        bwuserbattlestatisticsvo = bwUserBattleStatisticsDaoImpl.queryBwUserBattleStatisticsVOById(bwuserbattlestatisticsvo);
        maxPVPMark = (int) bwuserbattlestatisticsvo.getMaxpvpmark();
        BwUserMapDataVO bwusermapdatavoT = new BwUserMapDataVO();
        bwusermapdatavoT.setMailaddress(mailAddress);
        bwusermapdatavoT.setUniquenessbuildid(CommonGameData.TOWN_HALL_UUID_COMMON_GAME_DATA);
        bwusermapdatavoT.setBuildid(CommonGameData.TOWN_HALL_ID_COMMON_GAME_DATA);
        bwusermapdatavoT = bwUserMapDataCacheDAOImpl.queryBwUserMapDataVOById(bwusermapdatavoT);
        town_level = bwusermapdatavoT.getBuildlevel();
        int tempValue = 0;
        if (town_level > currentPVPMark) {
            tempValue = town_level;
        } else {
            tempValue = currentPVPMark;
        }
        if (tempValue > maxPVPMark) {
            return tempValue;
        } else {
            return maxPVPMark;
        }

    }

    public BwUserMapDataDAO getBwUserMapDataCacheDAOImpl() {
        return bwUserMapDataCacheDAOImpl;
    }

    public void setBwUserMapDataCacheDAOImpl(
            BwUserMapDataDAO bwUserMapDataCacheDAOImpl) {
        this.bwUserMapDataCacheDAOImpl = bwUserMapDataCacheDAOImpl;
    }

    public BwUserBattleStatisticsDAO getBwUserBattleStatisticsDaoImpl() {
        return bwUserBattleStatisticsDaoImpl;
    }

    public void setBwUserBattleStatisticsDaoImpl(
            BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl) {
        this.bwUserBattleStatisticsDaoImpl = bwUserBattleStatisticsDaoImpl;
    }

    public void loadOfflineUser() {
        //查询以test开头的所有用户作为机器人用户
        //All user queries to the beginning of the test as a robot user
//   	this.addOfflineMember(String.valueOf(10516), 0);
//    	for(int i=28926;i<=28927;i++){
//    	for(int i=11606;i<=11615;i++){
        for (int i = 12500; i <= 12509; i++) {

            String mailAddress = "";
            //return  to  this 
//    		if(i<10){
//    			mailAddress="test_000000"+i;
//    		}else{
//    			mailAddress="test_00000"+i;
//    		}

            if (i < 10) {
                mailAddress = "test_000000" + i;
            } else {
                mailAddress = "test_00000" + i;
            }
            /*   
            /*  this enabled  <code>this.addOfflineMember(String.valueOf(i), 0); </code>  causes  the  app  to  fail  to  run with
             * org.springframework.beans.factory.BeanCreationException: Error
             * creating bean with name 'minaIoHandler' defined in file
             * [/home/nzaniela/NetBeansProjects/GameServer/target/classes/config/serverCfg/mnet.xml]:
             * Cannot resolve reference to bean 'offLineManager' while setting
             * bean property 'offLineManager'; nested exception is
             * org.springframework.beans.factory.BeanCreationException: Error
             * creating bean with name 'offLineManager' defined in file
             * [/home/nzaniela/NetBeansProjects/GameServer/target/classes/config/serverCfg/p1-manager.xml]:
             * Invocation of init method failed; nested exception is
             * java.lang.NullPointerException
             *
             *
             */
            //lets  first  without it 
            //this.addOfflineMember(String.valueOf(i), 0);
            //System.out.println(" Chinese "+"加载机器人用户index:"+i);
            System.out.println("English " + " Loading robot users index:" + i);
        }
    }
}
