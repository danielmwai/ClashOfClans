package com.bw.looters.ms.web.vo;

/**
 * 定义返回常量,及部分通用功能
 *
 * @author zhYou
 */
public class ResUtil {

	/**
	 * 返回成功
	 */
	public static final Res SUCCESS = new Res(0, "success");

	public static final String sqlUpdateBwCharactersPropertiesLevelVO = "UPDATE bw_characters_properties_level SET"
			+ "character_id=?," + "character_level=?," + "laboratory_level=?," + "hit_points=?,"
			+ "training_resource_cost=?," + "upgrade_time=?," + "upgrade_resource_cose=?," + "damage=?,"
			+ "damage_radius=?," + "projectile_name=?," + "attack_effect=?," + "hited_effect=?," + "animation=?"
			+ "WHERE id=?";
}
