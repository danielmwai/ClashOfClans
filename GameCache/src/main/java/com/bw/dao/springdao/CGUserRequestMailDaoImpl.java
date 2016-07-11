package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserRequestMailDaoImpl extends BaseSpringDao implements CGUserRequestMailDao {

    RowMapper mapper;

    public CGUserRequestMailDaoImpl() {
        this.mapper = new RowMapper() {
            @Override
            public CGUserRequestMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserRequestMailVO vo = new CGUserRequestMailVO();
                vo.setId(rs.getLong("id"));
                vo.setBuildingId(rs.getLong("building_id"));
                vo.setLastModify(rs.getTimestamp("last_modify"));
                vo.setMailType(rs.getInt("mail_type"));
                vo.setReceiver(rs.getString("receiver"));
                vo.setSender(rs.getString("sender"));
                return vo;
            }
        };
    }

    @Override
    public void save(final CGUserRequestMailVO mailVO) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_request_mail( id, sender, receiver, mail_type, last_modify, building_id) VALUES (?, ?, ?, ?, ?, ?)";
        mailVO.setId(this.getId("cg_user_request_mail_id_seq"));
        final Object[] object = {mailVO.getId(), mailVO.getSender(), mailVO.getReceiver(), mailVO.getMailType(), new Timestamp(System.currentTimeMillis()), mailVO.getBuildingId()};
        this.getJdbcTemplate().update(sql, object);
    }

    @Override
    public void remove(final CGUserRequestMailVO mailVO) throws CityCacheDaoException {
        final String sql = "DELETE FROM cg_user_request_mail WHERE id = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailVO.getId()});
    }

    @Override
    public void removeBatch(final List<CGUserRequestMailVO> list) throws CityCacheDaoException {
        final String sql = "DELETE FROM cg_user_request_mail WHERE id = ?";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserRequestMailVO vo = list.get(index);
                ps.setLong(1, vo.getId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public List<CGUserRequestMailVO> queryAllRequestMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT id, sender, receiver, mail_type, last_modify, building_id FROM cg_user_request_mail where receiver = ? order by id desc limit 40 OFFSET 10";
        return (List<CGUserRequestMailVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, this.mapper);
    }

    @Override
    public List<CGUserRequestMailVO> queryRequestMailByPage(final String mailAddress, final int number, final int type) throws CityCacheDaoException {
        final String sql = "SELECT id, sender, receiver, mail_type, last_modify, building_id FROM cg_user_request_mail where mail_type = " + type + " and receiver = ? order by id desc limit " + number;
        return (List<CGUserRequestMailVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, this.mapper);
    }

    @Override
    public List<CGUserRequestMailVO> findRequestMailTimeout() throws CityCacheDaoException {
        final String sql = "select id,receiver from cg_user_request_mail where (last_modify + interval '7 day' <= CURRENT_DATE and mail_type = 1) or (last_modify + interval '2 day' <= CURRENT_DATE and mail_type = 2)";
        return (List<CGUserRequestMailVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CGUserRequestMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserRequestMailVO vo = new CGUserRequestMailVO();
                vo.setId(rs.getLong("id"));
                vo.setReceiver(rs.getString("receiver"));
                return vo;
            }
        });
    }
}
