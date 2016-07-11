package com.common.BaseInfor.DAO;

import com.bw.baseJar.vo.BwAreaVO;
import com.bw.baseJar.vo.BwArgsVO;
import com.bw.baseJar.vo.BwBankGemVO;
import com.bw.baseJar.vo.BwBuildingPropertiesLevelVO;
import com.bw.baseJar.vo.BwBuildingVO;
import com.bw.baseJar.vo.BwCharactersPropertiesLevelVO;
import com.bw.baseJar.vo.BwCharactersVO;
import com.bw.baseJar.vo.BwExchangeVO;
import com.bw.baseJar.vo.BwFileServerVO;
import com.bw.baseJar.vo.BwGameChannleVO;
import com.bw.baseJar.vo.BwHallBuildsRelationVO;
import com.bw.baseJar.vo.BwInitUserVO;
import com.bw.baseJar.vo.BwQuickenVO;
import com.bw.baseJar.vo.BwSpellPropertiesLevelVO;
import com.bw.baseJar.vo.BwSpellVOSource;
import com.bw.baseJar.vo.BwTreasureVO;
import com.bw.baseJar.vo.BwUserLevelReqVO;
import com.bw.baseJar.vo.BwWorkerVO;
import com.bw.dao.springdao.BaseSpringDao;
import com.bw.exception.CacheDaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBBaseInforDAOImpl extends BaseSpringDao implements DBBaseInforDAO {

    @SuppressWarnings("unchecked")
    @Override
    public List<BwGameChannleVO> getGameServerChannleList()
            throws CacheDaoException {
        String sql = "SELECT id,channle_name,service_url,service_interface,max_user_count,address,status,area_id FROM bw_game_channle WHERE status=1 ";
        List<BwGameChannleVO> gameChannleVOList;
        try {
            gameChannleVOList = (List<BwGameChannleVO>) this
                    .getJdbcTemplate().query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return gameChannleVOList.size() > 0 ? gameChannleVOList : null;

    }

    RowMapper mapper = new RowMapper() {

        @Override
        public BwGameChannleVO mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            BwGameChannleVO gameServerChannleVO = new BwGameChannleVO();
            gameServerChannleVO.setId(rs.getInt("id"));
            gameServerChannleVO.setChannlename(rs.getString("channle_name"));
            gameServerChannleVO.setServiceurl(rs.getString("service_url"));
            gameServerChannleVO.setServiceinterface(rs
                    .getString("service_interface"));
            gameServerChannleVO.setMaxusercount(rs.getInt("max_user_count"));
            gameServerChannleVO.setAddress(rs.getString("address"));
            gameServerChannleVO.setStatus(rs.getInt("status"));
            gameServerChannleVO.setAreaId(rs.getInt("area_id"));
            return gameServerChannleVO;
        }
    };

    @SuppressWarnings("unchecked")
    @Override
    public List<BwBuildingVO> queryBwBuildingVO() throws CacheDaoException {

        String sql = "SELECT air_targets,attack_range,attack_speed,build_resource_type,build_time_date,build_time_hour,build_time_minutes,building_animation,building_crash_h,building_crash_w,building_id,building_name,building_type,damage_radius,ground_targets,height,is_bunker,is_hidden,is_locked,is_sell,min_attack_range,produces_resource_type,push_back,swf,trigger_radius,upgrade_units,width FROM bw_building";
        List<BwBuildingVO> result = null;
        try {

            result = getJdbcTemplate().query(sql, new BwBuildingRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    private class BwBuildingRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBuildingVO fb = new BwBuildingVO();

            fb.setAirTargets(rs.getInt("air_targets"));
            fb.setAttackRange(rs.getLong("attack_range"));
            fb.setAttackSpeed(rs.getLong("attack_speed"));
            fb.setBuildResourceType(rs.getInt("build_resource_type"));
            fb.setBuildTimeDate(rs.getLong("build_time_date"));
            fb.setBuildTimeHour(rs.getLong("build_time_hour"));
            fb.setBuildTimeMinutes(rs.getLong("build_time_minutes"));
            fb.setBuildingAnimation(rs.getString("building_animation"));
            fb.setBuildingCrashH(rs.getInt("building_crash_h"));
            fb.setBuildingCrashW(rs.getInt("building_crash_w"));
            fb.setBuildingId(rs.getLong("building_id"));
            fb.setBuildingName(rs.getString("building_name"));
            fb.setBuildingType(rs.getLong("building_type"));
            fb.setDamageRadius(rs.getLong("damage_radius"));
            fb.setGroundTargets(rs.getInt("ground_targets"));
            fb.setHeight(rs.getInt("height"));
            fb.setIsBunker(rs.getInt("is_bunker"));
            fb.setIsHidden(rs.getInt("is_hidden"));
            fb.setIsLocked(rs.getInt("is_locked"));
            fb.setIsSell(rs.getInt("is_sell"));
            fb.setMinAttackRange(rs.getLong("min_attack_range"));
            fb.setProducesResourceType(rs.getInt("produces_resource_type"));
            fb.setPushBack(rs.getInt("push_back"));
            fb.setSwf(rs.getString("swf"));
            fb.setTriggerRadius(rs.getLong("trigger_radius"));
            fb.setUpgradeUnits(rs.getInt("upgrade_units"));
            fb.setWidth(rs.getInt("width"));
            return fb;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwBuildingPropertiesLevelVO> queryBwBuildingPropertiesLevelVO()
            throws CacheDaoException {

        String sql = "SELECT id,build_cost_count,build_export_name,build_level,build_time_date,build_time_hour,build_time_minutes,building_id,bw_characters_properties_level_id,characters_count,damage,destruction_XP,export_name_triggered,hit_point,houseing_space,max_stored_elixir,max_stored_gold,produce_resource_per_hour,projectile_name,regen_time,resource_max,town_hall_level,unit_production FROM bw_building_properties_level";
        List<BwBuildingPropertiesLevelVO> result = null;
        try {

            result = getJdbcTemplate().query(sql,
                    new BwBuildingPropertiesLevelRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwBuildingPropertiesLevelVO> queryBwBuildingPropertiesLevelVOByBuildingId(BwBuildingPropertiesLevelVO bwblevelvo)
            throws CacheDaoException {

        String sql = "SELECT id,build_cost_count,build_export_name,build_level,build_time_date,build_time_hour,build_time_minutes,building_id,bw_characters_properties_level_id,characters_count,damage,destruction_XP,export_name_triggered,hit_point,houseing_space,max_stored_elixir,max_stored_gold,produce_resource_per_hour,projectile_name,regen_time,resource_max,town_hall_level,unit_production FROM bw_building_properties_level WHERE building_id=?";
        List<BwBuildingPropertiesLevelVO> result = null;
        try {

            result = getJdbcTemplate().query(sql, new Object[]{bwblevelvo.getBuildingid()},
                    new BwBuildingPropertiesLevelRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwCharactersPropertiesLevelVO> queryBwCharactersPropertiesLevelVO()
            throws CacheDaoException {

        String sql = "SELECT id,animation,attack_effect,character_id,character_level,damage,damage_radius,hit_points,hited_effect,laboratory_level,projectile_name,training_resource_cost,upgrade_resource_cose,upgrade_time FROM bw_characters_properties_level";
        List<BwCharactersPropertiesLevelVO> result = null;
        try {

            result = getJdbcTemplate().query(sql,
                    new BwCharactersPropertiesLevelRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwCharactersPropertiesLevelVO> queryBwCharactersPropertiesLevelVOByCharactarId(BwCharactersPropertiesLevelVO bwclvo)
            throws CacheDaoException {

        String sql = "SELECT id,animation,attack_effect,character_id,character_level,damage,damage_radius,hit_points,hited_effect,laboratory_level,projectile_name,training_resource_cost,upgrade_resource_cose,upgrade_time FROM bw_characters_properties_level WHERE character_id=? ";
        List<BwCharactersPropertiesLevelVO> result = null;
        try {

            result = getJdbcTemplate().query(sql, new Object[]{bwclvo.getCharacterid()},
                    new BwCharactersPropertiesLevelRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwCharactersVO> queryBwCharactersVO() throws CacheDaoException {

        String sql = "SELECT UpgradeResource,air_target,attack_count,attack_prefered_target,attack_rang,attack_speed,barrack_level,big_picture,character_id,character_name,damage_mod,deploy_effect,die_effect,ground_targets,housing_space,icon_name,is_flying,resource_type,speed,swf,training_time,ui_name FROM bw_characters";
        List<BwCharactersVO> result = null;
        try {
            result = getJdbcTemplate().query(sql, new BwCharactersRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserLevelReqVO> queryBwUserLevelReqVO()
            throws CacheDaoException {

        String sql = "SELECT exp_req,level_id FROM bw_user_level_req";
        List<BwUserLevelReqVO> result = null;
        try {
            result = getJdbcTemplate()
                    .query(sql, new BwUserLevelReqRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result.size() <= 0 ? null : result;
    }

    private class BwBuildingPropertiesLevelRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBuildingPropertiesLevelVO fb = new BwBuildingPropertiesLevelVO();
//			select id,build_cost_count,build_export_name,build_level,build_time_date,
//			build_time_hour,build_time_minutes,building_id,bw_characters_properties_level_id,
//			characters_count,damage,destruction_XP,export_name_triggered,hit_point,houseing_space,
//			max_stored_elixir,max_stored_gold,produce_resource_per_hour,projectile_name,regen_time,
//			resource_max,town_hall_level,unit_production 
            fb.setId(rs.getLong("id"));
            fb.setBuildcostcount(rs.getLong("build_cost_count"));
            fb.setBuildexportname(rs.getString("build_export_name"));
            fb.setBuildlevel(rs.getInt("build_level"));
            fb.setBuildtimedate(rs.getLong("build_time_date"));
            fb.setBuildtimehour(rs.getLong("build_time_hour"));
            fb.setBuildtimeminutes(rs.getLong("build_time_minutes"));
            fb.setBuildingid(rs.getLong("building_id"));
            fb.setBwcharacterspropertieslevelid(rs
                    .getLong("bw_characters_properties_level_id"));
            fb.setCharacterscount(rs.getString("characters_count"));
            fb.setDamage(rs.getLong("damage"));
            fb.setDestructionxp(rs.getLong("destruction_xp"));
            fb.setExportnametriggered(rs.getString("export_name_triggered"));
            fb.setHitpoint(rs.getLong("hit_point"));
            fb.setHouseingspace(rs.getLong("houseing_space"));

            fb.setMaxstoredelixir(rs.getLong("max_stored_elixir"));
            fb.setMaxstoredgold(rs.getLong("max_stored_gold"));
            fb.setProduceresourceperhour(rs.getLong("produce_resource_per_hour"));
            fb.setProjectilename(rs.getString("projectile_name"));
            fb.setRegentime(rs.getInt("regen_time"));
            fb.setResourcemax(rs.getLong("resource_max"));
            fb.setTownhalllevel(rs.getLong("town_hall_level"));
            fb.setUnitproduction(rs.getLong("unit_production"));
            return fb;
        }
    }

    private class BwCharactersPropertiesLevelRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwCharactersPropertiesLevelVO fb = new BwCharactersPropertiesLevelVO();

            fb.setDamageradius(rs.getLong("damage_radius"));
            fb.setDamage(rs.getLong("damage"));
            fb.setId(rs.getLong("id"));
            fb.setProjectilename(rs.getString("projectile_name"));
            fb.setCharacterid(rs.getLong("character_id"));
            fb.setAnimation(rs.getString("animation"));
            fb.setAttackeffect(rs.getString("attack_effect"));
            fb.setCharacterlevel(rs.getLong("character_level"));
            fb.setHitpoints(rs.getLong("hit_points"));
            fb.setHitedeffect(rs.getString("hited_effect"));
            fb.setLaboratorylevel(rs.getLong("laboratory_level"));
            fb.setTrainingresourcecost(rs.getLong("training_resource_cost"));
            fb.setUpgraderesourcecose(rs.getLong("upgrade_resource_cose"));
            fb.setUpgradetime(rs.getLong("upgrade_time"));
            return fb;
        }
    }

    private class BwCharactersRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwCharactersVO fb = new BwCharactersVO();

            fb.setAttackspeed(rs.getLong("attack_speed"));
            fb.setSwf(rs.getString("swf"));
            fb.setAttackrang(rs.getLong("attack_rang"));
            fb.setBarracklevel(rs.getLong("barrack_level"));
            fb.setBigpicture(rs.getString("big_picture"));
            fb.setCharacterid(rs.getLong("character_id"));
            fb.setCharactername(rs.getString("character_name"));
            fb.setDamagemod(rs.getLong("damage_mod"));
            fb.setDeployeffect(rs.getString("deploy_effect"));
            fb.setDieeffect(rs.getString("die_effect"));
            fb.setHousingspace(rs.getLong("housing_space"));
            fb.setIconname(rs.getString("icon_name"));
            fb.setSpeed(rs.getLong("speed"));
            fb.setTrainingtime(rs.getLong("training_time"));
            fb.setUiname(rs.getString("ui_name"));
            return fb;
        }
    }

    private class BwUserLevelReqRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserLevelReqVO fb = new BwUserLevelReqVO();

            fb.setExpreq(rs.getLong("exp_req"));
            fb.setLevelid(rs.getLong("level_id"));
            return fb;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwHallBuildsRelationVO> queryBwHallBuildsRelationVO()
            throws CacheDaoException {
        String sql = "SELECT air_defense,alliance_castle,archer_tower,attack_cost,barrack,cannon,ejector,elixir_pump,elixir_storage,gold_mine,golden_storage,hall_level,"
                + "laboratory,mine,mortar,spell_forge,superbomb,tesla_tower,troop_housing,wall,wizard_tower,worker_building,plunder_arg1,plunder_arg2 FROM bw_hall_builds_relation";
        List<BwHallBuildsRelationVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new BwHallBuildsRelationRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwSpellPropertiesLevelVO> queryBwSpellPropertiesLevelVO() throws CacheDaoException {
        String sql = "SELECT id,attack_speed_boost,boost_time_ms,damage,damage_boost_percent,icon_export_name,jump_boost_ms,jump_housing_limit,laboratory_level,move_speed_boost,number_of_hits,radius,random_radius,spell_id,spell_level,time_betweenHits_ms,training_cost,upgrade_cost,upgrade_time_H FROM bw_spell_properties_level";
        List<BwSpellPropertiesLevelVO> result = null;
        try {
            result = getJdbcTemplate().query(sql, new BwSpellPropertiesLevelRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwSpellVOSource> queryBwSpellVO() throws CacheDaoException {
        String sql = "SELECT charging_Time_ms,cooldown_s,hit_time_ms,housing_space,spell_id,spell_name,training_resource,training_time,unlock_spell,upgrade_resource FROM bw_spell";
        List<BwSpellVOSource> result = null;
        try {
            result = getJdbcTemplate().query(sql, new BwSpellRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result;
    }

    @Override
    public BwInitUserVO queryBwInitUserVO() throws CacheDaoException {
        String sql = "SELECT id,golden_count,elixir_count,gem_count,exp,one_minute_cost,one_hour_cost,one_day_cost,one_week_cost FROM bw_init_user";
        List<BwInitUserVO> result = null;
        try {
            result = getJdbcTemplate().query(sql, new BwInitUserRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }

        return result.size() > 0 ? result.get(0) : null;
    }

    @SuppressWarnings("unused")
    private class BwInitUserRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwInitUserVO bwInitUserVO = new BwInitUserVO();
            bwInitUserVO.setId(rs.getInt("id"));
            bwInitUserVO.setGoldenCount(rs.getInt("golden_count"));
            bwInitUserVO.setElixirCount(rs.getInt("elixir_count"));
            bwInitUserVO.setGemCount(rs.getInt("gem_count"));
            bwInitUserVO.setExp(rs.getInt("exp"));
            bwInitUserVO.setOneMinuteCost(rs.getInt("one_minute_cost"));
            bwInitUserVO.setOneHourCost(rs.getInt("one_hour_cost"));
            bwInitUserVO.setOneDayCost(rs.getInt("one_day_cost"));
            bwInitUserVO.setOneWeekCost(rs.getInt("one_week_cost"));

            return bwInitUserVO;
        }
    }

    private class BwSpellRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwSpellVOSource fb = new BwSpellVOSource();

            fb.setSpellid(rs.getLong("spell_id"));
            fb.setSpellname(rs.getString("spell_name"));
            fb.setUnlockspell(rs.getInt("unlock_spell"));
            fb.setTrainingresource(rs.getInt("training_resource"));
            fb.setHousingspace(rs.getInt("housing_space"));
            fb.setTrainingtime(rs.getInt("training_time"));
            fb.setChargingtimems(rs.getInt("charging_Time_ms"));
            fb.setHittimems(rs.getInt("hit_time_ms"));
            fb.setCooldowns(rs.getInt("cooldown_s"));
            fb.setUpgraderesource(rs.getInt("upgrade_resource"));
            return fb;
        }
    }

    private class BwHallBuildsRelationRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwHallBuildsRelationVO fb = new BwHallBuildsRelationVO();
            fb.setAirdefense(rs.getInt("air_defense"));
            fb.setAlliancecastle(rs.getInt("alliance_castle"));
            fb.setArchertower(rs.getInt("archer_tower"));
            fb.setAttackcost(rs.getLong("attack_cost"));
            fb.setBarrack(rs.getInt("barrack"));
            fb.setCannon(rs.getInt("cannon"));
            fb.setEjector(rs.getInt("ejector"));
            fb.setElixirpump(rs.getInt("elixir_pump"));
            fb.setElixirstorage(rs.getInt("elixir_storage"));
            fb.setGoldmine(rs.getInt("gold_mine"));
            fb.setGoldenstorage(rs.getInt("golden_storage"));
            fb.setHalllevel(rs.getLong("hall_level"));
            fb.setLaboratory(rs.getInt("laboratory"));
            fb.setMine(rs.getInt("mine"));
            fb.setMortar(rs.getInt("mortar"));
            fb.setSpellforge(rs.getInt("spell_forge"));
            fb.setSuperbomb(rs.getInt("superbomb"));
            fb.setTeslatower(rs.getInt("tesla_tower"));
            fb.setTroophousing(rs.getInt("troop_housing"));
            fb.setWall(rs.getInt("wall"));
            fb.setWizardtower(rs.getInt("wizard_tower"));
            fb.setWorkerbuilding(rs.getInt("worker_building"));
            fb.setPlunder_arg1(rs.getFloat("plunder_arg1"));
            fb.setPlunder_arg2(rs.getFloat("plunder_arg2"));
            return fb;
        }
    }

    private class BwSpellPropertiesLevelRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwSpellPropertiesLevelVO bpl = new BwSpellPropertiesLevelVO();
            bpl.setId(rs.getInt("id"));
            bpl.setSpelllevel(rs.getInt("spell_level"));
            bpl.setSpellid(rs.getInt("spell_id"));
            bpl.setLaboratorylevel(rs.getInt("laboratory_level"));
            bpl.setTrainingcost(rs.getLong("training_cost"));
            bpl.setUpgradetimeh(rs.getInt("upgrade_time_H"));
            bpl.setUpgradecost(rs.getLong("upgrade_cost"));
            bpl.setBoosttimems(rs.getLong("boost_time_ms"));
            bpl.setMovespeedboost(rs.getLong("move_speed_boost"));
            bpl.setMovespeedboost(rs.getLong("move_speed_boost"));
            bpl.setAttackspeedboost(rs.getLong("attack_speed_boost"));
            bpl.setJumphousinglimit(rs.getShort("jump_housing_limit"));
            bpl.setJumpboostms(rs.getShort("jump_boost_ms"));
            bpl.setDamageboostpercent(rs.getShort("damage_boost_percent"));
            bpl.setDamage(rs.getInt("damage"));
            bpl.setRadius(rs.getInt("radius"));
            bpl.setNumberofhits(rs.getInt("number_of_hits"));
            bpl.setRandomradius(rs.getInt("random_radius"));
            bpl.setTimebetweenhitsms(rs.getInt("time_betweenHits_ms"));
            bpl.setIconexportname(rs.getString("icon_export_name"));
            return bpl;
        }

    }

    @SuppressWarnings("unchecked")
    public List<BwBankGemVO> queryBwBankGemVO() {
        String sql = "SELECT gem_count,price,product_id,status FROM bw_bank_gem WHERE status=0";
        List<BwBankGemVO> result = null;
        try {
            result = this.getJdbcTemplate().query(sql, new BwBankGemRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return result;
    }

    private class BwBankGemRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBankGemVO fb = new BwBankGemVO();
            fb.setGemCount(rs.getLong("gem_count"));
            fb.setPrice(rs.getLong("price"));
            fb.setProductId(rs.getString("product_id"));
            fb.setStatus(rs.getInt("status"));
            return fb;
        }
    }

    private static final String SQL_QUERY_BW_EXCHANGES = "SELECT COUNT, gem FROM bw_exchange";
    private static final RowMapper<BwExchangeVO> ROW_MAPPER_BW_EXCHANGE = new RowMapper<BwExchangeVO>() {
        @Override
        public BwExchangeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BwExchangeVO vo = new BwExchangeVO();
            vo.setCount(rs.getLong("count"));
            vo.setGem(rs.getLong("gem"));
            return vo;
        }
    };

    public List<BwExchangeVO> queryBwExchanges() throws CacheDaoException {
        try {
            return getJdbcTemplate().query(SQL_QUERY_BW_EXCHANGES, ROW_MAPPER_BW_EXCHANGE);
        } catch (DataAccessException e) {
            throw new CacheDaoException(e);
        }
    }

    private static final String SQL_QUERY_BW_QUICKEN = "SELECT id, quicken_time, price, quicken_type FROM bw_quicken";
    private static final RowMapper<BwQuickenVO> ROW_MAPPER_BW_QUICKEN = new RowMapper<BwQuickenVO>() {
        @Override
        public BwQuickenVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BwQuickenVO vo = new BwQuickenVO();
            vo.setId(rs.getLong("id"));
            vo.setPrice(rs.getInt("price"));
            vo.setQuickenTime(rs.getInt("quicken_time"));
            vo.setQuickenType(rs.getInt("quicken_type"));
            return vo;
        }
    };

    public List<BwQuickenVO> queryBwQuickens() throws CacheDaoException {
        try {
            return getJdbcTemplate().query(SQL_QUERY_BW_QUICKEN, ROW_MAPPER_BW_QUICKEN);
        } catch (DataAccessException e) {
            throw new CacheDaoException(e);
        }
    }

    //    private class BwTreasureRowMapper implements RowMapper {
//    	public Object mapRow(ResultSet rs, int i) throws SQLException {
//    	BwTreasureVO fb = new BwTreasureVO();
//
//    	fb.setId(rs.getLong("id"));
//    	fb.setPrice(rs.getLong("price"));
//    	fb.setPriceType(rs.getInt("price_type"));
//    	fb.setCount(rs.getLong("count"));
//    	fb.setImagepath(rs.getString("imagepath"));
//    	fb.setTreasureType(rs.getInt("treasure_type"));
//    	fb.setName(rs.getString("name"));
//    	return fb;
//    	}
//    	}
    private static final String SQL_QUERY_BW_TREASURES = "SELECT id, NAME, treasure_type, COUNT, price, price_type, imagePath FROM bw_treasure";
    private final static RowMapper<BwTreasureVO> ROW_MAPPER_BW_TREASURE = new RowMapper<BwTreasureVO>() {
        @Override
        public BwTreasureVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BwTreasureVO fb = new BwTreasureVO();
            fb.setId(rs.getLong("id"));
            fb.setPrice(rs.getLong("price"));
            fb.setPriceType(rs.getInt("price_type"));
            fb.setCount(rs.getLong("count"));
            fb.setImagepath(rs.getString("imagepath"));
            fb.setTreasureType(rs.getInt("treasure_type"));
            fb.setName(rs.getString("name"));
            return fb;
        }
    };

    /**
     * @return @throws CacheDaoException
     */
    public List<BwTreasureVO> queryBwTreasures() throws CacheDaoException {
        try {
            return getJdbcTemplate().query(SQL_QUERY_BW_TREASURES, ROW_MAPPER_BW_TREASURE);
        } catch (Exception e) {
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwFileServerVO> queryBwFileServerVO() throws CacheDaoException {
        List<BwFileServerVO> result = null;
        try {
            String sql1 = "SELECT id,NAME,rul,status FROM bw_file_server WHERE status=1";
            result = this.getJdbcTemplate().query(sql1,
                    new BwFileServerRowMapper());

        } catch (Exception e) {
        }
        return result;
    }

    private class BwFileServerRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwFileServerVO fb = new BwFileServerVO();

            fb.setId(rs.getLong("id"));
            fb.setRul(rs.getString("rul"));
            fb.setName(rs.getString("name"));
            fb.setStatus(rs.getInt("status"));
            return fb;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwWorkerVO> queryBwWorkerVOList() throws CacheDaoException {
        List<BwWorkerVO> result = null;
        try {
            String sql = "SELECT id,NAME,next_count,next_price FROM bw_worker";
            result = this.getJdbcTemplate().query(sql,
                    new BwWorkerRowMapper());

        } catch (Exception e) {
            throw new CacheDaoException(e);
        }
        return result;
    }

    private class BwWorkerRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwWorkerVO fb = new BwWorkerVO();
            fb.setId(rs.getLong("id"));
            fb.setNextCount(rs.getInt("next_count"));
            fb.setNextPrice(rs.getInt("next_price"));
            fb.setName(rs.getString("name"));
            return fb;
        }
    }

    @SuppressWarnings("unchecked")
    public List<BwAreaVO> queryBwAreaVOList()
            throws CacheDaoException {
        List<BwAreaVO> result = null;
        try {
            String sql1 = "SELECT area_id,area_name,status FROM bw_area WHERE status=1";
            result = this.getJdbcTemplate().query(sql1,
                    new BwAreaRowMapper());

        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    private class BwAreaRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwAreaVO fb = new BwAreaVO();

            fb.setAreaId(rs.getLong("area_id"));
            fb.setAreaName(rs.getString("area_name"));
            fb.setStatus(rs.getInt("status"));
            return fb;
        }
    }

    @Override
    public List<BwGameChannleVO> getGameServerChannleList(long areaId)
            throws CacheDaoException {
        String sql = "SELECT id,channle_name,service_url,service_interface,max_user_count,address,status,area_id FROM bw_game_channle WHERE area_id=" + areaId + " AND  status=1 ";
        List<BwGameChannleVO> gameChannleVOList;
        try {
            gameChannleVOList = (List<BwGameChannleVO>) this
                    .getJdbcTemplate().query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return gameChannleVOList.size() > 0 ? gameChannleVOList : null;
    }

    private static final String SQL_QUERY_ARGS = "SELECT id, pvp_max_k, pvp_n FROM bw_args";
    private static final RowMapper<BwArgsVO> ROW_MAPPER_BW_ARGS = new RowMapper<BwArgsVO>() {
        @Override
        public BwArgsVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BwArgsVO vo = new BwArgsVO();
            vo.setId(rs.getInt("id"));
            vo.setPvpN(rs.getInt("pvp_n"));
            vo.setPvpMaxK(rs.getInt("pvp_max_k"));
            return vo;
        }
    };

    @Override
    public List<BwArgsVO> getArgs() {
        try {
            return getJdbcTemplate().query(SQL_QUERY_ARGS, ROW_MAPPER_BW_ARGS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }
}
