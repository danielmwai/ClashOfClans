package com.bw.application.manager.user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bw.application.exception.ManagerServerException;
import com.bw.baseJar.errorCode.ErrorCodeInterface;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.cache.vo.BwUserBankVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.dao.BwMineCollectorDAO;
import com.bw.dao.BwPlantUserDAO;
import com.bw.dao.BwUserBattleStatisticsDAO;
import com.bw.dao.BwUserDAO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.exception.CacheDaoException;

public class UserManagerImpl implements UserManager {

    public BwUserDAO bwUserCacheDAOImpl;
    public DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public BwUserMapDataDAO bwUserMapDataCacheDAOImpl;
    public BwMineCollectorDAO bwMineCollectorCacheDAOImpl;
    //初始化战斗统计信息
    public BwUserBattleStatisticsDAO bwUserBattleStatisticsDaoImpl;
    //平台用户
    public BwPlantUserDAO bwPlantUserCacheDAOImpl;

    @Override
    public int isExistForUser(BwPlantUserVO bwPlantUserVO) throws ManagerServerException {
        String bowei_id = bwPlantUserCacheDAOImpl.queryBwPlantUserVOByMailAddress(bwPlantUserVO);
        if (bowei_id != null) {//
            bwPlantUserVO.setBoweiid(bowei_id);
            BwPlantUserVO t = bwPlantUserCacheDAOImpl.queryBwPlantUserVOById(bwPlantUserVO);
            if (!t.getPassword().equals(bwPlantUserVO.getPassword())) {
                return ErrorCodeInterface.USER_NAME_OR_PASSWORD_NO_RIGHT;
            }
        } else {
            return ErrorCodeInterface.USER_NAME_OR_PASSWORD_NO_RIGHT;
        }
        return ErrorCodeInterface.SUCESS;

//		BwUserVO uservo=new BwUserVO();
//  		uservo.setMailaddress(bwPlantUserVO.getMailaddress());
//  		uservo.setAreaId(bwPlantUserVO.getAreaId());
//		BwUserVO uservoTemp =bwUserCacheDAOImpl.queryBwUserVOById(uservo);
//		if (null == uservoTemp) {
//			return false;
//		} else {
//			return true;
//		}
    }

    @Override
    public BwUserVO getUserVOByMailAddress(BwUserVO uservo) throws ManagerServerException {
        return bwUserCacheDAOImpl.queryBwUserVOById(uservo);
    }

    /**
     * 用户注册
     */
    public int UserRegister(BwPlantUserVO bwPlantUserVO) throws ManagerServerException {
        //先查找在更新
        BwPlantUserVO t = bwPlantUserCacheDAOImpl.queryBwPlantUserVOById(bwPlantUserVO);
        if (null != t) {
            t.setMailaddress(bwPlantUserVO.getMailaddress());
            t.setNickname(bwPlantUserVO.getNickname());
            t.setPassword(bwPlantUserVO.getPassword());
            bwPlantUserCacheDAOImpl.update(t);
            t = null;
            return ErrorCodeInterface.SUCESS;
        }
        return ErrorCodeInterface.ERROR;

    }

    public BwUserDAO getBwUserCacheDAOImpl() {
        return bwUserCacheDAOImpl;
    }

    public void setBwUserCacheDAOImpl(BwUserDAO bwUserCacheDAOImpl) {
        this.bwUserCacheDAOImpl = bwUserCacheDAOImpl;
    }

    /**
     * @param uservo
     * @throws ManagerServerExceptio 初始化用户信息 包括 用户、大厅,数目,石头的基本信息
     */
    @Override
    public void initUserInfor(BwUserVO uservo) throws ManagerServerException {
        try {
            uservo.setGoldencount(750);
            uservo.setElixircount(750);
            //上线以后这个地方要去掉
            BwUserBankVO userBankVO = new BwUserBankVO();
            userBankVO.setGemtotalcount(500);
            userBankVO.setLastupdatetime(sdf.format(new java.util.Date()));
            userBankVO.setMailaddress(uservo.getMailaddress());
            uservo.setLastlogintime(sdf.format(new java.util.Date()));
            uservo.setLevel(1);
            uservo.setNickname("player");
            uservo.setPvpmark(0);
            //保存完用户信息，还需要保存其它的比如: 大厅位置,数目,石头,基本信息
            //大厅和树石头的uuid在1~200之间
            BwUserMapDataVO bwusermapdatavoTemp = new BwUserMapDataVO();
            bwusermapdatavoTemp.setMailaddress(uservo.getMailaddress());
            bwusermapdatavoTemp.setMapindexx(20);
            bwusermapdatavoTemp.setMapindexy(20);
            bwusermapdatavoTemp.setStatus(1);
            bwusermapdatavoTemp.setUniquenessbuildid(1);
            bwusermapdatavoTemp.setBuildid(100);
            bwusermapdatavoTemp.setBuildlevel(1);
            bwusermapdatavoTemp.setUpgradefinishtime("");
            //保存大厅信息
            bwUserMapDataCacheDAOImpl.save(bwusermapdatavoTemp);
            //保存用户信息
            bwUserCacheDAOImpl.save(uservo);
            //一个 金矿,一个营地,一个农民屋

            //一个金矿
            BwUserMapDataVO bwusermapdatavoGolden = new BwUserMapDataVO();
            bwusermapdatavoGolden.setMailaddress(uservo.getMailaddress());
            bwusermapdatavoGolden.setMapindexx(30);
            bwusermapdatavoGolden.setMapindexy(30);
            bwusermapdatavoGolden.setStatus(1);
            bwusermapdatavoGolden.setUniquenessbuildid(2);
            bwusermapdatavoGolden.setBuildid(202);
            bwusermapdatavoGolden.setBuildlevel(1);
            bwusermapdatavoGolden.setUpgradefinishtime("");
            bwUserMapDataCacheDAOImpl.save(bwusermapdatavoGolden);
            BwMineCollectorVO bwminecollectorvo = new BwMineCollectorVO();
            bwminecollectorvo.setHarveststarttime(sdf.format(new Date()));
            bwminecollectorvo.setUserbuildingdataid(bwusermapdatavoGolden.getId());
            bwMineCollectorCacheDAOImpl.save(bwminecollectorvo);
            //一个营地
            BwUserMapDataVO bwusermapdatavoTroop = new BwUserMapDataVO();
            bwusermapdatavoTroop.setMailaddress(uservo.getMailaddress());
            bwusermapdatavoTroop.setMapindexx(15);
            bwusermapdatavoTroop.setMapindexy(15);
            bwusermapdatavoTroop.setStatus(1);
            bwusermapdatavoTroop.setUniquenessbuildid(3);
            bwusermapdatavoTroop.setBuildid(1);
            bwusermapdatavoTroop.setBuildlevel(1);
            bwusermapdatavoTroop.setUpgradefinishtime("");
            bwUserMapDataCacheDAOImpl.save(bwusermapdatavoTroop);
            //一个农民屋
            BwUserMapDataVO bwusermapdatavoWorker = new BwUserMapDataVO();
            bwusermapdatavoWorker.setMailaddress(uservo.getMailaddress());
            bwusermapdatavoWorker.setMapindexx(25);
            bwusermapdatavoWorker.setMapindexy(25);
            bwusermapdatavoWorker.setStatus(1);
            bwusermapdatavoWorker.setUniquenessbuildid(4);
            bwusermapdatavoWorker.setBuildid(500);
            bwusermapdatavoWorker.setBuildlevel(1);
            bwusermapdatavoWorker.setUpgradefinishtime("");
            bwUserMapDataCacheDAOImpl.save(bwusermapdatavoWorker);
            //保存用户默认 cache数据
//		1	Troop Housing
//		2	Barrack
//		3	Laboratory
//		4	Alliance Castle
//		5	Spell Forge
//		100	Town Hall
//		200	Elixir Pump
//		201	Elixir Storage
//		202	Gold Mine
//		203	Gold Storage
//		300	Cannon
//		301	Archer Tower
//		302	Wizard Tower
//		303	Air Defense
//		304	Mortar
//		305	Tesla Tower
//		400	Wall
//		500	Worker Building
//		600	Communications mast
//		601	Goblin main building
//		602	Goblin hut		
//		for(int x=0;x<21;x++){
//			
//		}
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 1, 1);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 2, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 3, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 4, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 5, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 100, 1);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 200, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 201, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 202, 1);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 203, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 300, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 301, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 302, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 303, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 304, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 305, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 400, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 500, 1);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 600, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 601, 0);
            bwUserMapDataCacheDAOImpl.updateUserBuildCount(uservo.getMailaddress(), 602, 0);
            bwusermapdatavoTemp = null;
            //初始化战斗统计信息
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo = new BwUserBattleStatisticsVO();
            bwuserbattlestatisticsvo.setMailaddress(uservo.getMailaddress());
            bwuserbattlestatisticsvo.setClansid(0);
            bwuserbattlestatisticsvo.setFailtimes(0);
            bwuserbattlestatisticsvo.setGetelixircount(0);
            bwuserbattlestatisticsvo.setGetgoldencount(0);
            bwuserbattlestatisticsvo.setMaxpvpmark(0);
            bwuserbattlestatisticsvo.setWintimes(0);
            bwUserBattleStatisticsDaoImpl.save(bwuserbattlestatisticsvo);
        } catch (CacheDaoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new ManagerServerException(e);
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

    public BwMineCollectorDAO getBwMineCollectorCacheDAOImpl() {
        return bwMineCollectorCacheDAOImpl;
    }

    public void setBwMineCollectorCacheDAOImpl(
            BwMineCollectorDAO bwMineCollectorCacheDAOImpl) {
        this.bwMineCollectorCacheDAOImpl = bwMineCollectorCacheDAOImpl;
    }

    public BwPlantUserDAO getBwPlantUserCacheDAOImpl() {
        return bwPlantUserCacheDAOImpl;
    }

    public void setBwPlantUserCacheDAOImpl(BwPlantUserDAO bwPlantUserCacheDAOImpl) {
        this.bwPlantUserCacheDAOImpl = bwPlantUserCacheDAOImpl;
    }

    @Override
    public void initPlantUserInfor(BwPlantUserVO bwPlantUserVO)
            throws ManagerServerException {
        //bowei_id 用第三方服务器来生成
        bwPlantUserCacheDAOImpl.save(bwPlantUserVO);
        BwUserVO uservo = new BwUserVO();
        uservo.setMailaddress(bwPlantUserVO.getBoweiid());
        uservo.setMacAddress(bwPlantUserVO.getMacaddress());
        this.initUserInfor(uservo);
    }

    @Override
    public int initAndLoginForThirdParty(BwPlantUserVO bwplantuservo)
            throws ManagerServerException {
        BwPlantUserVO t = bwPlantUserCacheDAOImpl.queryBwPlantUserVOById(bwplantuservo);
        if (t == null) {//初始化用户数据
            bwPlantUserCacheDAOImpl.save(bwplantuservo);
        }
        return ErrorCodeInterface.SUCESS;
    }

}
