package com.bw.looters.base.dao;

import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.bw.exception.CacheDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbBaseInfoUpdater {

	@Autowired
	@Qualifier("lootersManagerJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_UPDATE_BUILDING = "UPDATE bw_building SET " + "building_name = ?,"
			+ "building_type = ?," + "swf = ?," + "build_time_date = ?," + "build_time_hour = ? ,"
			+ "build_time_minutes = ? ," + "build_resource_type = ? ," + "width = ? ," + "height = ? ,"
			+ "building_animation = ? ," + "is_bunker = ? ," + "produces_resource_type = ? ," + "upgrade_units = ? ,"
			+ "attack_range = ? ," + "attack_speed = ? ," + "building_crash_w = ? ," + "building_crash_h = ? ,"
			+ "air_targets = ? ," + "ground_targets = ? ," + "min_attack_range = ? ," + "damage_radius = ? ,"
			+ "push_back = ? ," + "is_sell = ? ," + "is_locked = ? ," + "is_hidden = ? ," + "trigger_radius = ? WHERE "
			+ "building_id = ?";

	public int updateBwBuildingVO(BwBuildingVO vo) throws CacheDaoException {
		return this.jdbcTemplate.update(SQL_UPDATE_BUILDING,
				new Object[] { vo.getBuildingName(), vo.getBuildingType(), vo.getSwf(), vo.getBuildTimeDate(),
						vo.getBuildTimeHour(), vo.getBuildTimeMinutes(), vo.getBuildResourceType(), vo.getWidth(),
						vo.getHeight(), vo.getBuildingAnimation(), vo.getIsBunker(), vo.getProducesResourceType(),
						vo.getUpgradeUnits(), vo.getAttackRange(), vo.getAttackSpeed(), vo.getBuildingCrashW(),
						vo.getBuildingCrashH(), vo.getAirTargets(), vo.getGroundTargets(), vo.getMinAttackRange(),
						vo.getDamageRadius(), vo.getPushBack(), vo.getIsSell(), vo.getIsLocked(), vo.getIsHidden(),
						vo.getTriggerRadius(), vo.getBuildingId() });
	}

	private static final String SQL_UPDATE_BUILDING_PROPERTIES_LEVEL = "UPDATE bw_building_properties_level SET "
			+ "build_level = ?" + ",building_id = ?" + ",build_export_name = ?" + ",build_time_date = ?"
			+ ",build_time_hour = ?" + ",build_time_minutes = ?" + ",build_cost_count = ?" + ",town_hall_level = ?"
			+ ",max_stored_gold = ?" + ",max_stored_elixir = ?" + ",houseing_space = ?"
			+ ",produce_resource_per_hour = ?" + ",resource_max = ?" + ",unit_production = ?" + ",hit_point = ?"
			+ ",regen_time = ?" + ",damage = ?" + ",projectile_name = ?" + ",bw_characters_properties_level_id = ?"
			+ ",characters_count = ?" + ",destruction_XP = ?" + ",export_name_triggered = ?" + "WHERE id = ?";

	public int updateBwBuildingPropertiesLevelVO(BwBuildingPropertiesLevelVO vo) throws CacheDaoException {
		return jdbcTemplate.update(SQL_UPDATE_BUILDING_PROPERTIES_LEVEL,
				new Object[] { vo.getBuildlevel(), vo.getBuildingid(), vo.getBuildexportname(), vo.getBuildtimedate(),
						vo.getBuildtimehour(), vo.getBuildtimeminutes(), vo.getBuildcostcount(), vo.getTownhalllevel(),
						vo.getMaxstoredgold(), vo.getMaxstoredelixir(), vo.getHouseingspace(),
						vo.getProduceresourceperhour(), vo.getResourcemax(), vo.getUnitproduction(), vo.getHitpoint(),
						vo.getRegentime(), vo.getDamage(), vo.getProjectilename(),
						vo.getBwcharacterspropertieslevelid(), vo.getCharacterscount(), vo.getDestructionxp(),
						vo.getExportnametriggered(), vo.getId() });
	}

	private static final String SQL_UPDATE_CHARACTERS_PROPERTIES_LEVEL = "UPDATE bw_characters_properties_level SET "
			+ "character_id=?," + "character_level=?," + "laboratory_level=?," + "hit_points=?,"
			+ "training_resource_cost=?," + "upgrade_time=?," + "upgrade_resource_cose=?," + "damage=?,"
			+ "damage_radius=?," + "projectile_name=?," + "attack_effect=?," + "hited_effect=?," + "animation=?"
			+ " WHERE id=?";

	public int updateBwCharactersPropertiesLevelVO(BwCharactersPropertiesLevelVO vo) throws CacheDaoException {
		return jdbcTemplate.update(SQL_UPDATE_CHARACTERS_PROPERTIES_LEVEL,
				new Object[] { vo.getCharacterid(), vo.getCharacterlevel(), vo.getLaboratorylevel(), vo.getHitpoints(),
						vo.getTrainingresourcecost(), vo.getUpgradetime(), vo.getUpgraderesourcecose(), vo.getDamage(),
						vo.getDamageradius(), vo.getProjectilename(), vo.getAttackeffect(), vo.getHitedeffect(),
						vo.getAnimation(), vo.getId() });
	}

	private static final String SQL_UPDATE_CHARACTERS = "UPDATE bw_characters SET " + "character_name=?," + "swf=?,"
			+ "housing_space=?," + "barrack_level=?," + "speed=?," + "training_time=?," + "resource_type=?,"
			+ "UpgradeResource=?," + "attack_rang=?," + "attack_speed=?," + "damage_mod=?," + "ui_name=?,"
			+ "icon_name=?," + "big_picture=?," + "attack_prefered_target=?," + "deploy_effect=?," + "is_flying=?,"
			+ "air_target=?," + "ground_targets=?," + "attack_count=?," + "die_effect=? WHERE " + "character_id=?";

	public int updateBwCharactersVO(BwCharactersVO vo) throws CacheDaoException {
		return jdbcTemplate.update(SQL_UPDATE_CHARACTERS,
				new Object[] { vo.getCharactername(), vo.getSwf(), vo.getHousingspace(), vo.getBarracklevel(),
						vo.getSpeed(), vo.getTrainingtime(), vo.getResourcetype(), vo.getUpgraderesource(),
						vo.getAttackrang(), vo.getAttackspeed(), vo.getDamagemod(), vo.getUiname(), vo.getIconname(),
						vo.getBigpicture(), vo.getAttackpreferedtarget(), vo.getDeployeffect(), vo.getIsflying(),
						vo.getAirtarget(), vo.getGroundtargets(), vo.getAttackcount(), vo.getDieeffect(),
						vo.getCharacterid() });
	}

	private static final String SQL_UPDATE_ARGS = "UPDATE bw_args SET pvp_max_k = ?, pvp_n = ? WHERE id = ?";

	public void updateArgs(BwArgsVO vo) throws CacheDaoException {
		jdbcTemplate.update(SQL_UPDATE_ARGS, vo.getPvpMaxK(), vo.getPvpN(), vo.getId());
	}

	private static final String SQL_UPDATE_INIT_USER = "UPDATE bw_init_user" + " SET " + " golden_count=?, "
			+ " elixir_count=?, " + " gem_count=?, " + " exp=?, " + " one_minute_cost=?, " + " one_hour_cost=?, "
			+ " one_day_cost=?, " + " one_week_cost=? " + " WHERE id= ?";

	public void updateInitUser(BwInitUserVO user) {
		jdbcTemplate.update(SQL_UPDATE_INIT_USER, user.getGoldenCount(), user.getElixirCount(), user.getGemCount(),
				user.getExp(), user.getOneMinuteCost(), user.getOneHourCost(), user.getOneDayCost(),
				user.getOneWeekCost(), user.getId());
	}

	private static final String SQL_UPDATE_QUICKEN = "UPDATE bw_quicken " + " SET " + " quicken_time=?, " + " price=?, "
			+ " quicken_type=? " + " WHERE id=?";

	public void updateQuicken(BwQuickenVO vo) {
		jdbcTemplate.update(SQL_UPDATE_QUICKEN, vo.getQuickenTime(), vo.getPrice(), vo.getQuickenType(), vo.getId());
	}

	private static final String SQL_UPDATE_EXCHANGE = "update bw_exchange set count = ?, gem = ? where count = ?";

	public void updateExchange(BwExchangeVO vo) {
		jdbcTemplate.update(SQL_UPDATE_EXCHANGE, vo.getCount(), vo.getGem(), vo.getCount());
	}

	private static final String SQL_UPDATE_USER_LEVEL = "update bw_user_level_req set level_id = ?, exp_req = ? where level_id = ?";

	public void updateUserLevel(BwUserLevelReqVO vo) {
		jdbcTemplate.update(SQL_UPDATE_USER_LEVEL, vo.getLevelid(), vo.getExpreq(), vo.getLevelid());
	}
}
