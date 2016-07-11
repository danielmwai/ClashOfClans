package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.exception.*;
import org.springframework.jdbc.core.*;
import java.sql.*;
import java.util.*;

public class CGUserFavorDAOImpl extends BaseSpringDao implements CGUserFavorDAO {

    @Override
    public void favorFriend(final String mailAddress, final String friendMail, final Timestamp time) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_favor(mail_address, last_modify, favor_mail_address) VALUES (?, ?, ?)";
        final Object[] objects = {mailAddress, time, friendMail};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public void remove(final String mailAddress, final String friendMail) throws CityCacheDaoException {
        final String sql = "delete from cg_user_favor where mail_address = ? and favor_mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress, friendMail});
    }

    @Override
    public Timestamp isFavor(final String mailAddress, final String friendMail) throws CityCacheDaoException {
        final String sql = "select last_modify from cg_user_favor where mail_address = ? and favor_mail_address = ?";
        final List<Timestamp> list = (List<Timestamp>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress, friendMail}, new RowMapper() {
            @Override
            public Timestamp mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getTimestamp(1);
            }
        });
        return (list.size() == 0) ? null : list.get(0);
    }
}
