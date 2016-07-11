package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserInteractiveMailDaoImpl extends BaseSpringDao implements CGUserInteractiveMailDao {

    RowMapper mapper;

    public CGUserInteractiveMailDaoImpl() {
        this.mapper = new RowMapper() {
            @Override
            public CGUserInteractiveMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserInteractiveMailVO vo = new CGUserInteractiveMailVO();
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
    public void save(final CGUserInteractiveMailVO mailVO) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_interactive_mail(id, sender, receiver, mail_type, last_modify, building_id) VALUES (?, ?, ?, ?, ?, ?)";
        mailVO.setId(this.getId("cg_user_interactive_mail_id_seq"));
        final Object[] objects = {mailVO.getId(), mailVO.getSender(), mailVO.getReceiver(), mailVO.getMailType(), mailVO.getLastModify(), mailVO.getBuildingId()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public void saveBatch(final List<CGUserInteractiveMailVO> list) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_interactive_mail(id, sender, receiver, mail_type, last_modify, building_id) VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserInteractiveMailVO vo = list.get(index);
                vo.setId(CGUserInteractiveMailDaoImpl.this.getId("cg_user_interactive_mail_id_seq"));
                ps.setLong(1, vo.getId());
                ps.setString(2, vo.getSender());
                ps.setString(3, vo.getReceiver());
                ps.setInt(4, vo.getMailType());
                ps.setTimestamp(5, vo.getLastModify());
                ps.setLong(6, vo.getBuildingId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public void remove(final CGUserInteractiveMailVO mailVO) throws CityCacheDaoException {
    }

    @Override
    public void removeBatch(final List<CGUserInteractiveMailVO> list) throws CityCacheDaoException {
        final String sql = "delete from cg_user_interactive_mail where id = ?";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int arg1) throws SQLException {
                final CGUserInteractiveMailVO vo = list.get(arg1);
                ps.setLong(1, vo.getId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public List<CGUserInteractiveMailVO> queryAllInteractiveMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT id, sender, receiver, mail_type, last_modify, building_id FROM cg_user_interactive_mail where receiver = ? order by id desc limit 40";
        return (List<CGUserInteractiveMailVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, this.mapper);
    }

    @Override
    public List<CGUserInteractiveMailVO> queryInteractiveMailByPage(final String mailAddress, final int number, final int type) throws CityCacheDaoException {
        final String sql = "SELECT id, sender, receiver, mail_type, last_modify, building_id FROM cg_user_interactive_mail where receiver = ? and mail_type = " + type + " order by id desc limit " + number;
        return (List<CGUserInteractiveMailVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, this.mapper);
    }

    @Override
    public List<CGUserInteractiveMailVO> findInteractiveMailTimeout() throws CityCacheDaoException {
        final String sql = "select id,receiver from cg_user_interactive_mail where last_modify + interval '2 day' <= CURRENT_DATE;";
        return (List<CGUserInteractiveMailVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CGUserInteractiveMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserInteractiveMailVO vo = new CGUserInteractiveMailVO();
                vo.setId(rs.getLong("id"));
                vo.setReceiver(rs.getString("receiver"));
                return vo;
            }
        });
    }
}
