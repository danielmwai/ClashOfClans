package com.bw.dao.springdao;

import com.bw.dao.*;
import java.util.*;
import org.springframework.jdbc.core.*;
import java.sql.*;
import com.bw.baseJar.vo.*;

public class CmTableDaoImpl extends BaseSpringDao implements CmTableDao {

    @Override
    public List<CmBuildingVO> initAllBuilding() {
        final String sql = "select building_name, perchase_type, unlock_role_level, build_terrain, floor_grids, buillding_id, gold_price, occupation_properties, is_can_upgrade, next_level_id, income_cd, build_cd, income_exp, income_gold, is_favor, building_type, building_describe, sell_price, unlock_castle_level, action_id, point_width, point_height from cm_building";
        return (List<CmBuildingVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmBuildingVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CmBuildingVO building = new CmBuildingVO();
                building.setBuildCd(rs.getLong("build_cd"));
                building.setBuildingType(rs.getInt("building_type"));
                building.setBuildTerrain(rs.getInt("build_terrain"));
                building.setCanUpgrade(rs.getBoolean("is_can_upgrade"));
                building.setBuildingDescribe(rs.getString("desc"));
                building.setFavor(rs.getBoolean("is_favor"));
                building.setFloorGrids(rs.getInt("floor_grids"));
                building.setGoldPrice(rs.getInt("gold_price"));
                building.setBuildingId(rs.getLong("building_id"));
                building.setIncomeCd(rs.getLong("income_cd"));
                building.setIncomeExp(rs.getInt("income_exp"));
                building.setIncomeGold(rs.getInt("income_gold"));
                building.setBuildingName(rs.getString("building_name"));
                building.setNextLevelId(rs.getLong("next_level_id"));
                building.setOccupationProperties(rs.getInt("occupation_properties"));
                building.setSellPrice(rs.getInt("sell_price"));
                building.setUnlockCastleLevel(rs.getInt("unlock_castle_level"));
                building.setUnlockRoleLevel(rs.getInt("unlock_role_level"));
                building.setPointWidth(rs.getInt("point_width"));
                building.setPointHeight(rs.getInt("point_height"));
                building.setActionId(rs.getInt("action_id"));
                building.setPerchaseType(rs.getInt("perchase_type"));
                return building;
            }
        });
    }

    @Override
    public List<CmCropsVO> initAllCrops() {
        final String sql = "select id, crops_name, gold_price, perchase_type, income_exp, income_gold, plant_cd, income_cd, wither_cd, is_care, plant_action_id, unlock_role_level from cm_crops";
        return (List<CmCropsVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmCropsVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CmCropsVO cmCrops = new CmCropsVO();
                cmCrops.setCare(rs.getBoolean("is_care"));
                cmCrops.setGoldPrice(rs.getInt("gold_price"));
                cmCrops.setId(rs.getInt("id"));
                cmCrops.setIncomeCd(rs.getLong("income_cd"));
                cmCrops.setIncomeExp(rs.getInt("income_exp"));
                cmCrops.setIncomeGold(rs.getInt("income_gold"));
                cmCrops.setName(rs.getString("name"));
                cmCrops.setPlantActionId(rs.getString("plant_action_id"));
                cmCrops.setPlantCd(rs.getLong("plant_cd"));
                cmCrops.setWitherCd(rs.getLong("wither_cd"));
                cmCrops.setPerchaseType(rs.getInt("perchase_type"));
                cmCrops.setUnlockRoleLevel(rs.getInt("unlock_role_level"));
                return cmCrops;
            }
        });
    }

    @Override
    public List<CityServerChannleVO> initAllServerChannle() {
        final String sql = "select * from csgame_channle";
        return (List<CityServerChannleVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CityServerChannleVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CityServerChannleVO channleVO = new CityServerChannleVO();
                channleVO.setAddress(rs.getString("address"));
                channleVO.setChannleName(rs.getString("channle_name"));
                channleVO.setId(rs.getLong("id"));
                channleVO.setMaxUserCount(rs.getInt("max_user_count"));
                channleVO.setServiceInterface(rs.getString("service_interface"));
                channleVO.setServiceUrl(rs.getString("service_url"));
                channleVO.setUserCount(rs.getInt("user_count"));
                return channleVO;
            }
        });
    }

    @Override
    public List<CmIslandDataVO> initAllIslandData() {
        final String sql = "select * from cm_island_data";
        return (List<CmIslandDataVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmIslandDataVO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final CmIslandDataVO cmIslandDataVO = new CmIslandDataVO();
                cmIslandDataVO.setId(rs.getInt("id"));
                cmIslandDataVO.setIslandLevel(rs.getInt("island_level"));
                cmIslandDataVO.setModuleOccupy(rs.getInt("module_occupy"));
                cmIslandDataVO.setPerchase_type(rs.getInt("perchase_type"));
                cmIslandDataVO.setGoldPrice(rs.getInt("gold_price"));
                cmIslandDataVO.setRoleLevel(rs.getInt("role_level"));
                return cmIslandDataVO;
            }
        });
    }

    @Override
    public List<CmSeaDataVO> initAllSeaData() {
        final String sql = "select * from cm_sea_data";
        return (List<CmSeaDataVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmSeaDataVO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final CmSeaDataVO cmSeaDataVO = new CmSeaDataVO();
                cmSeaDataVO.setId(rs.getLong("id"));
                cmSeaDataVO.setPieceCount(rs.getInt("piece_count"));
                cmSeaDataVO.setPointX(rs.getString("point_x"));
                cmSeaDataVO.setPointY(rs.getString("point_y"));
                return cmSeaDataVO;
            }
        });
    }

    @Override
    public List<CmLevelVO> initAllLevel() {
        final String sql = "SELECT id, user_level, exp, total_exp FROM cm_level";
        return (List<CmLevelVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmLevelVO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final CmLevelVO vo = new CmLevelVO();
                vo.setId(rs.getInt("id"));
                vo.setExp(rs.getInt("exp"));
                vo.setUserLevel(rs.getInt("user_level"));
                vo.setTotal_exp(rs.getLong("total_exp"));
                return vo;
            }
        });
    }

    @Override
    public List<CmCastleVO> initAllCastle() {
        final String sql = "SELECT id, castle_name, castle_level, unlock_role_level, unlock_building_number, occupation_level FROM cm_castle";
        return (List<CmCastleVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmCastleVO mapRow(final ResultSet rs, final int rowNum) throws SQLException {
                final CmCastleVO vo = new CmCastleVO();
                vo.setId(rs.getInt("id"));
                vo.setCastleLevel(rs.getInt("castle_level"));
                vo.setCastleName(rs.getString("castle_name"));
                vo.setOccupationLevel(rs.getInt("occupation_level"));
                vo.setUnlockBuildingNumber(rs.getInt("unlock_building_number"));
                vo.setUnlockRoleLevel(rs.getInt("unlock_role_level"));
                return vo;
            }
        });
    }

    @Override
    public List<CmPackageVO> initAllPackage() {
        final String sql = "SELECT id, label_number, expand_price FROM cm_package";
        return (List<CmPackageVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CmPackageVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CmPackageVO vo = new CmPackageVO();
                vo.setExpandPrice(rs.getInt("expand_price"));
                vo.setId(rs.getInt("id"));
                vo.setLabelNumber(rs.getInt("label_number"));
                return vo;
            }
        });
    }
}
