package com.bw.looters.ms.web.ctrl.looters;

import com.bw.looters.base.dao.DbBaseInfoUpdater;
import com.bw.looters.ms.web.vo.Res;
import com.bw.looters.ms.web.vo.ResUtil;
import com.bw.looters.ms.web.vo.res.JsonStoreRes;
import com.bw.application.resourceManager.ResManager;
import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.bw.dao.BwGameChannleDAO;
import com.common.BaseInfor.DAO.DBBaseInforDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏配置数据修改
 *
 * @author zhYou
 */
@Controller
@RequestMapping("/looters/cfg")
public class CfgCtrl implements InitializingBean {

	//
	// bw_building: 建筑信息数据管理
	//
	@ResponseBody
	@RequestMapping("/building/all")
	public Res getBuildings() {
		List<BwBuildingVO> voList = dbBaseInforDAO.queryBwBuildingVO();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	@ResponseBody
	@RequestMapping("/building/save")
	public Res saveBuilding(@RequestBody BwBuildingVO vo) {
		dbBaseInfoUpdater.updateBwBuildingVO(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	//
	// 建筑等级配置管理
	//
	@ResponseBody
	@RequestMapping("/buildingpropertieslevel/all")
	public Res getBuildingPropertiesLevels() {
		List<BwBuildingPropertiesLevelVO> voList = dbBaseInforDAO.queryBwBuildingPropertiesLevelVO();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	@ResponseBody
	@RequestMapping("/buildingproperties/save")
	public Res saveBuildingPropertiesLevel(@RequestBody BwBuildingPropertiesLevelVO vo) {
		dbBaseInfoUpdater.updateBwBuildingPropertiesLevelVO(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	//
	// 角色信息管理
	//
	@ResponseBody
	@RequestMapping("/character/all")
	public Res getCharacters() {
		List<BwCharactersVO> voList = dbBaseInforDAO.queryBwCharactersVO();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	@ResponseBody
	@RequestMapping("/character/save")
	public Res saveCharacter(@RequestBody BwCharactersVO vo) {
		dbBaseInfoUpdater.updateBwCharactersVO(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	//
	// 角色等级管理
	//
	@ResponseBody
	@RequestMapping("/characterpropertieslevel/all")
	public Res getCharacterPropertiesLevels() {
		List<BwCharactersPropertiesLevelVO> voList = this.dbBaseInforDAO.queryBwCharactersPropertiesLevelVO();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	@ResponseBody
	@RequestMapping("/characterpropertieslevel/save")
	public Res saveCharacterPropertiesLevel(@RequestBody BwCharactersPropertiesLevelVO vo) {
		dbBaseInfoUpdater.updateBwCharactersPropertiesLevelVO(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	//
	// bw_game_channel(服务器状态) 数据管理
	//
	@ResponseBody
	@RequestMapping("/gamechannel/all")
	public Res getGameChannels() {
		List<BwGameChannleVO> channels = bwGameChannleDAO.selectAll();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(channels);
		return res;
	}

	@ResponseBody
	@RequestMapping("/gamechannel/save")
	public Res saveGameChannel(@RequestBody BwGameChannleVO vo) {
		bwGameChannleDAO.update(vo);
		// 这里只修改数据库, 涉及到RMI的修改, 需要重启管理平台
		return ResUtil.SUCCESS;
	}

	//
	// 游戏服务器常数配置: bw_args
	//
	@ResponseBody
	@RequestMapping("/args/all")
	public Res getBwArgs() {
		List<BwArgsVO> list = dbBaseInforDAO.getArgs();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/args/save")
	public Res saveArgs(@RequestBody BwArgsVO args) {
		dbBaseInfoUpdater.updateArgs(args);
		// TODO - zhYou: 是否有RMI调用?
		return ResUtil.SUCCESS;
	}

	//
	// bw_init_user, 初始用户信息
	//
	@ResponseBody
	@RequestMapping("/inituser/all")
	public Res getInitUsers() {
		BwInitUserVO vo = dbBaseInforDAO.queryBwInitUserVO();
		List<BwInitUserVO> list = new ArrayList<BwInitUserVO>();
		list.add(vo);
		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/inituser/save")
	public Res saveInitUser(@RequestBody BwInitUserVO user) {
		dbBaseInfoUpdater.updateInitUser(user);
		// TODO - zhYou: 是否有RMI调用?
		return ResUtil.SUCCESS;
	}

	//
	// bw_quicken: 加速基础表
	//
	@ResponseBody
	@RequestMapping("/quicken/all")
	public Res getQuickens() {
		List<BwQuickenVO> list = dbBaseInforDAO.queryBwQuickens();

		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/quicken/save")
	public Res saveQuicken(@RequestBody BwQuickenVO vo) {
		dbBaseInfoUpdater.updateQuicken(vo);
		// TODO - zhYou: 是否有RMI调用?
		return ResUtil.SUCCESS;
	}

	//
	// bw_exchange: 商店兑换配置
	//
	@ResponseBody
	@RequestMapping("/exchange/all")
	public Res getExchanges() {
		List<BwExchangeVO> list = dbBaseInforDAO.queryBwExchanges();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/exchange/save")
	public Res saveExchange(@RequestBody BwExchangeVO vo) {
		dbBaseInfoUpdater.updateExchange(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	//
	// bw_user_level_req: 等级配置
	//
	@ResponseBody
	@RequestMapping("/userlevel/all")
	public Res getUserLevels() {
		List<BwUserLevelReqVO> list = dbBaseInforDAO.queryBwUserLevelReqVO();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/userlevel/save")
	public Res saveUserLevel(@RequestBody BwUserLevelReqVO vo) {
		dbBaseInfoUpdater.updateUserLevel(vo);
		updateConfig(vo);
		return ResUtil.SUCCESS;
	}

	public void afterPropertiesSet() throws Exception {
		List<BwGameChannleVO> channelList = bwGameChannleDAO.selectAll();
		for (BwGameChannleVO channel : channelList) {
			try {
				RmiProxyFactoryBean fb = new RmiProxyFactoryBean();
				fb.setServiceUrl(channel.getServiceurl());
				fb.setServiceInterface(Class.forName(channel.getServiceinterface()));
				fb.afterPropertiesSet();
				resManagers.add((ResManager) fb.getObject());
			} catch (Exception e) {
				LOGGER.error("init rmi error: {},{},{}",
						new Object[] { e, channel.getServiceurl(), channel.getServiceinterface() });
				LOGGER.trace("init rmi error: {},{},{}",
						new Object[] { e, channel.getServiceurl(), channel.getServiceinterface() });
			}
		}
	}

	/**
	 * 更新所有服务器的配置对象
	 *
	 * @param obj
	 */
	private void updateConfig(Object obj) {
		for (ResManager resManager : resManagers) {
			resManager.updateConfig(obj);
		}
	}

	private List<ResManager> resManagers = new ArrayList<ResManager>();

	@Resource
	private DbBaseInfoUpdater dbBaseInfoUpdater;
	@Resource
	private BwGameChannleDAO bwGameChannleDAO;
	@Resource
	private DBBaseInforDAO dbBaseInforDAO;
	private static final Logger LOGGER = LoggerFactory.getLogger(CfgCtrl.class);
}
