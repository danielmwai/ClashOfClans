package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.exception.*;
import com.bw.cache.vo.*;
import org.springframework.jdbc.core.*;
import java.sql.*;
import java.util.*;

public class CGUserFavorTimeDAOImpl extends BaseSpringDao implements CGUserFavorTimeDAO {

    @Override
    public void save(final String mailAddress, final Timestamp date) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_favor_time(mail_address, favor_time, last_modify) VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress, 0, date});
    }

    @Override
    public void favoring(final String mailAddress) throws CityCacheDaoException {
        final String sql = "update cg_user_favor_time set favor_time = favor_time + 1 where mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }

    @Override
    public void remove(final String mailAddress) throws CityCacheDaoException {
        final String sql = "delete from cg_user_favor_time where mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }

    @Override
    public CGUserFavorTimeVO queryFavorTime(final String mailAddress) throws CityCacheDaoException {
        final String sql = "select favor_time,last_modify from cg_user_favor_time where mail_address = ?";
        final List<CGUserFavorTimeVO> list = (List<CGUserFavorTimeVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, new RowMapper() {
            @Override
            public CGUserFavorTimeVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserFavorTimeVO vo = new CGUserFavorTimeVO();
                vo.setFavorTime(rs.getInt(1));
                vo.setLastModify(rs.getTimestamp(2));
                return vo;
            }
        });
        return (list.size() == 0) ? null : list.get(0);
    }
}
