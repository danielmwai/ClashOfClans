package com.bw.dao.springdao;

import com.bw.dao.*;
import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserBuildingInfoDAOImpl extends BaseSpringDao implements CGUserBuildingInfoDAO {

    @Override
    public void batchSave(final String mailAddress, final List<CGUserBuildingInfoVO> list) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_building_info(pointx, pointy, building_uuid, building_id, status, mail_address) VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserBuildingInfoVO vo = list.get(index);
                ps.setInt(1, vo.getPointX());
                ps.setInt(2, vo.getPointY());
                ps.setLong(3, vo.getBuildingUuid());
                ps.setInt(4, vo.getBuildingId());
                ps.setInt(5, vo.getStatus());
                ps.setString(6, mailAddress);
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public void removeByMailAddress(final String mailAddress) throws CityCacheDaoException {
        final String sql = "delete from cg_user_building_info where mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }

    @Override
    public List<CGUserBuildingInfoVO> queryInfoByMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT pointx, pointy, building_uuid, building_id, status FROM cg_user_building_info where mail_address = ?";
        return (List<CGUserBuildingInfoVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, new RowMapper() {
            @Override
            public CGUserBuildingInfoVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserBuildingInfoVO vo = new CGUserBuildingInfoVO();
                vo.setPointX(rs.getInt(1));
                vo.setPointY(rs.getInt(2));
                vo.setBuildingUuid(rs.getLong(3));
                vo.setBuildingId(rs.getInt(4));
                vo.setStatus(rs.getInt(5));
                return vo;
            }
        });
    }
}
