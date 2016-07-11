package com.bw.looters.ms.web.ctrl.looters;

import com.bw.looters.ms.web.vo.Res;
import com.bw.looters.ms.web.vo.ResUtil;
import com.bw.looters.ms.web.vo.res.JsonStoreRes;
import com.bw.cache.vo.BwBlacklistVO;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.cache.vo.BwUserBankVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.dao.BwBlacklistDAO;
import com.bw.dao.BwPlantUserDAO;
import com.bw.dao.BwUserBankDAO;
import com.bw.dao.BwUserDAO;
import com.bw.jms.sender.BwUserVOSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 玩家数据对象的数据管理
 *
 * @author zhYou
 */
@Controller
@RequestMapping("/looters/obj")
public class ObjCtrl {

	/**
	 * 查询角色信息
	 *
	 * @param vo
	 *            该vo包含了email地址
	 */
	@ResponseBody
	@RequestMapping("/user/getByMail")
	public Res getUserByEmail(@RequestBody BwPlantUserVO vo) {
		if (vo.getMailaddress() == null || vo.getMailaddress().trim().length() == 0) {
			throw new RuntimeException("邮箱不能为空");
		}
		String bwId = this.bwPlantUserDao.queryBwPlantUserVOByMailAddress(vo);
		if (bwId == null || bwId.trim().length() == 0) {
			throw new RuntimeException("BwId不存在:" + vo.getMailaddress());
		}
		BwUserVO userVo = new BwUserVO();
		userVo.setMailaddress(bwId);
		userVo = this.bwUserDao.queryBwUserVOById(userVo);
		if (userVo == null) {
			throw new RuntimeException("玩家不存在: " + bwId);
		}

		List<BwUserVO> voList = new ArrayList<BwUserVO>();
		voList.add(userVo);
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	/**
	 * 更新玩家数据
	 *
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user/save")
	public Res saveUser(@RequestBody Map<String, Object> map) {
		// 有BwUserVo中的部分属性不符合java规范, jackson无法进行数据装载, 所以在这里使用map进行转换
		BwUserVO vo = new BwUserVO();
		vo.setMailaddress((String) map.get("mailaddress"));
		vo.setElixircount(new Long(map.get("elixircount").toString()));
		vo.setExp(new Long(map.get("exp").toString()));
		vo.setGoldencount(new Long(map.get("goldencount").toString()));
		vo.setId(new Long(map.get("id").toString()));
		vo.setLastlogintime(map.get("lastlogintime").toString());
		vo.setLevel(new Integer(map.get("level").toString()));
		vo.setNickname(map.get("nickname").toString());
		vo.setPvpmark(new Long(map.get("pvpmark").toString()));
		vo.setShieldEndTime(map.get("shieldEndtime").toString());
		vo.setBattleStatus(Boolean.valueOf(map.get("battleStatus").toString()));
		vo.setWorkCount(new Integer(map.get("workCount").toString()));
		vo.setMacAddress(map.get("macAddress").toString());
		vo.setMaxGoldenCount((Integer.parseInt(map.get("maxGoldenCount").toString())));
		vo.setMaxElixirCount((Integer.parseInt(map.get("maxElixirCount").toString())));
		this.bwUserDao.update(vo);
		this.bwUserVOSender.send(vo);
		return ResUtil.SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/blacklist/all")
	public Res getBlacklists() {
		List<BwBlacklistVO> blacklists = bwBlacklistDao.selectAll();
		JsonStoreRes res = new JsonStoreRes();
		res.setData(blacklists);
		return res;
	}

	@ResponseBody
	@RequestMapping("/blacklist/save")
	public Res saveBlacklist(@RequestBody BwBlacklistVO blacklist) {
		if (blacklist.getId() == 0) {
			long id = bwBlacklistDao.insert(blacklist);
			blacklist.setId(id);
		} else {
			bwBlacklistDao.update(blacklist);
		}
		Map<String, Long> data = new HashMap<String, Long>();
		data.put("id", blacklist.getId());
		JsonStoreRes res = new JsonStoreRes();
		res.setData(data);
		return res;
	}

	@ResponseBody
	@RequestMapping("/blacklist/get")
	public Res getBlacklist(@RequestBody BwBlacklistVO vo) {
		if (vo.getBoweiId() == null || vo.getBoweiId().trim().equals("")) {
			throw new RuntimeException("boweiId不能为空");
		}
		vo = bwBlacklistDao.select(vo.getBoweiId());
		List<BwBlacklistVO> voList = new ArrayList<BwBlacklistVO>();
		voList.add(vo);
		JsonStoreRes res = new JsonStoreRes();
		res.setData(voList);
		return res;
	}

	@ResponseBody
	@RequestMapping("/blacklist/del")
	public Res delBlacklist(@RequestBody long id) {
		bwBlacklistDao.delete(String.valueOf(id));
		return ResUtil.SUCCESS;
	}

	//
	// bw_user_bank: 用户宝石数据
	//
	@ResponseBody
	@RequestMapping("/userbank/get")
	public Res getUserBank(@RequestBody String mail) {
		BwPlantUserVO plantUser = new BwPlantUserVO();
		plantUser.setMailaddress(mail);
		String boweiId = bwPlantUserDao.queryBwPlantUserVOByMailAddress(plantUser);
		if (boweiId == null) {
			throw new RuntimeException("plant user not found: " + mail);
		}
		BwUserBankVO bank = new BwUserBankVO();
		bank.setMailaddress(boweiId);
		bank = bwUserBankDao.queryBwUserBankVOById(bank);
		if (bank == null) {
			throw new RuntimeException("宝石记录不存在:" + mail);
		}
		List<BwUserBankVO> data = new ArrayList<BwUserBankVO>();
		data.add(bank);
		JsonStoreRes res = new JsonStoreRes();
		res.setData(data);
		return res;
	}

	@ResponseBody
	@RequestMapping("/userbank/save")
	public Res saveUserBank(@RequestBody BwUserBankVO vo) {
		bwUserBankDao.update(vo);
		return ResUtil.SUCCESS;
	}

	//
	// bw_plant_user数据
	//
	@ResponseBody
	@RequestMapping("/plantuser/get")
	public Res getPlantUser(@RequestBody String mail) {
		BwPlantUserVO vo = new BwPlantUserVO();
		vo.setMailaddress(mail);
		String boweiId = bwPlantUserDao.queryBwPlantUserVOByMailAddress(vo);
		vo.setBoweiid(boweiId);
		vo = bwPlantUserDao.queryBwPlantUserVOById(vo);
		List<BwPlantUserVO> list = new ArrayList<BwPlantUserVO>();
		if (vo != null) {
			list.add(vo);
		}
		JsonStoreRes res = new JsonStoreRes();
		res.setData(list);
		return res;
	}

	@ResponseBody
	@RequestMapping("/plantuser/save")
	public Res savePlantUser(@RequestBody BwPlantUserVO vo) {
		bwPlantUserDao.update(vo);
		return ResUtil.SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/plantuser/del")
	public Res delPlantUser(@RequestBody String boweiId) {
		BwPlantUserVO vo = new BwPlantUserVO();
		vo.setBoweiid(boweiId);
		bwPlantUserDao.delete(vo);
		return ResUtil.SUCCESS;
	}

	@Resource
	private BwUserBankDAO bwUserBankDao;
	@Resource
	private BwBlacklistDAO bwBlacklistDao;
	@Resource
	private BwUserVOSender bwUserVOSender;
	@Resource
	private BwUserDAO bwUserDao;
	@Resource
	private BwPlantUserDAO bwPlantUserDao;
}
