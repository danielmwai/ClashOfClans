package com.bw.dao.springdao;

import com.bw.dao.*;
import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserIslandInfoDAOImpl extends BaseSpringDao implements CGUserIslandInfoDAO {

    @Override
    public void batchSave(final String mailAddress, final List<CGUserIslandInfoVO> list) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_island_info(island_id, grid_opintx, grid_opinty, mail_address) VALUES (?, ?, ?, ?)";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserIslandInfoVO vo = list.get(index);
                ps.setInt(1, vo.getIslandId());
                ps.setInt(2, vo.getGridOpintX());
                ps.setInt(3, vo.getGridOpintY());
                ps.setString(4, mailAddress);
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public void removeByMailAddress(final String mailAddress) throws CityCacheDaoException {
        final String sql = "delete from cg_user_island_info where mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }

    @Override
    public List<CGUserIslandInfoVO> queryInfoByMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT island_id, grid_opintx, grid_opinty FROM cg_user_island_info where mail_address = ?";
        return (List<CGUserIslandInfoVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, new RowMapper() {
            @Override
            public CGUserIslandInfoVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserIslandInfoVO vo = new CGUserIslandInfoVO();
                vo.setIslandId(rs.getInt(1));
                vo.setGridOpintX(rs.getInt(2));
                vo.setGridOpintY(rs.getInt(3));
                return vo;
            }
        });
    }
}
