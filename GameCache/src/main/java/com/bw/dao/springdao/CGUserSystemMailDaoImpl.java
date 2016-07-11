package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserSystemMailDaoImpl extends BaseSpringDao implements CGUserSystemMailDao {

    RowMapper mapper;

    public CGUserSystemMailDaoImpl() {
        this.mapper = new RowMapper() {
            @Override
            public CGUserSystemMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserSystemMailVO vo = new CGUserSystemMailVO();
                vo.setId(rs.getLong("id"));
                vo.setLastModify(rs.getDate("last_modify"));
                vo.setMailContent(rs.getString("mail_content"));
                vo.setMailTo(rs.getString("mail_to"));
                vo.setTitle(rs.getString("title"));
                vo.setTop(rs.getBoolean("is_top"));
                return vo;
            }
        };
    }

    @Override
    public void save(final CGUserSystemMailVO mailVO) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_system_mail(id, title, mail_content, mail_to, is_top, last_modify) VALUES (?, ?, ?, ?, ?, ?)";
        mailVO.setId(this.getId("cg_user_system_mail_id_seq"));
        final Object[] objects = {mailVO.getId(), mailVO.getTitle(), mailVO.getMailContent(), mailVO.getMailTo(), mailVO.isTop(), mailVO.getLastModify()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public void update(final CGUserSystemMailVO mailVO) throws CityCacheDaoException {
        final String sql = "UPDATE cg_user_system_mail SET title=?, mail_content=?, mail_to=?, is_top=?, last_modify=? WHERE id= ?";
        final Object[] objects = {mailVO.getTitle(), mailVO.getMailContent(), mailVO.getMailTo(), mailVO.isTop(), mailVO.getLastModify(), mailVO.getId()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public void remove(final CGUserSystemMailVO mailVO) throws CityCacheDaoException {
        final String sql = "delete from cg_user_system_mail where id = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailVO.getId()});
    }

    @Override
    public void clearAll() throws CityCacheDaoException {
        final String sql = "delete from cg_user_system_mail";
        this.getJdbcTemplate().update(sql);
    }

    @Override
    public CGUserSystemMailVO querySystemMailById(final long id, final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT id, title, mail_content, mail_to, is_top, last_modify FROM cg_user_system_mail where id = ?";
        final List<CGUserSystemMailVO> list = (List<CGUserSystemMailVO>) this.getJdbcTemplate().query(sql, new Object[]{id}, this.mapper);
        return (list.size() > 0) ? list.get(0) : null;
    }

    @Override
    public List<Long> queryAllSystemMail() throws CityCacheDaoException {
        final String sql = "SELECT id FROM cg_user_system_mail where mail_to = 'ALL' order by id desc";
        return (List<Long>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public Long mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getLong("id");
            }
        });
    }

    @Override
    public List<Long> queryMySystemMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT id FROM cg_user_system_mail where mail_to = ? order by id desc";
        return (List<Long>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, new RowMapper() {
            @Override
            public Long mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getLong("id");
            }
        });
    }

    @Override
    public void removeBatch(final List<CGUserSystemMailVO> list) throws CityCacheDaoException {
        final String sql = "delete from cg_user_system_mail where id = ?";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserSystemMailVO vo = list.get(index);
                ps.setLong(1, vo.getId());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public List<CGUserSystemMailVO> findSystemMailTimeout() throws CityCacheDaoException {
        final String sql = "select id,mail_to from cg_user_system_mail where last_modify + interval '7 day' <= CURRENT_DATE;";
        return (List<CGUserSystemMailVO>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public CGUserSystemMailVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserSystemMailVO vo = new CGUserSystemMailVO();
                vo.setId(rs.getLong("id"));
                vo.setMailTo(rs.getString("mail_to"));
                return vo;
            }
        });
    }

    @Override
    public int countSystemMail() throws CityCacheDaoException {
        final String sql = "select count(1) from cg_user_system_mail";
        return this.getJdbcTemplate().queryForInt(sql);
    }

    @Override
    public Long queryPastSystemMail() throws CityCacheDaoException {
        final String sql = "select min(id) from cg_user_system_mail";
        final List<Long> list = (List<Long>) this.getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public Long mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getLong(1);
            }
        });
        return (list != null && list.size() > 0) ? list.get(0) : null;
    }
}
