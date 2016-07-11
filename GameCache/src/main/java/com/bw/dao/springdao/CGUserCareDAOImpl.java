package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import org.springframework.jdbc.core.*;
import java.sql.*;
import java.util.*;

public class CGUserCareDAOImpl extends BaseSpringDao implements CGUserCareDAO {

    @Override
    public void save(final CGUserCareVO vo) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_care(mail_address, building_id, last_modify) VALUES (?, ?, ?)";
        final Object[] objects = {vo.getMailAddress(), vo.getBuildingId(), vo.getLastModify()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public void refresh(final CGUserCareVO vo) throws CityCacheDaoException {
        final String sql = "UPDATE cg_user_care SET  last_modify=? WHERE mail_address=? and building_id=?";
        final Object[] objects = {vo.getLastModify(), vo.getMailAddress(), vo.getBuildingId()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public Timestamp queryLastModify(final CGUserCareVO vo) throws CityCacheDaoException {
        final String sql = "select last_modify from cg_user_care where mail_address=? and building_id=?";
        final List<Timestamp> list = (List<Timestamp>) this.getJdbcTemplate().query(sql, new Object[]{vo.getMailAddress(), vo.getBuildingId()}, new RowMapper() {
            @Override
            public Timestamp mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getTimestamp(1);
            }
        });
        return (list.size() == 0) ? null : list.get(0);
    }
}
