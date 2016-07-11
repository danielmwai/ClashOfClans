package com.bw.dao.springdao;

import com.bw.dao.*;
import org.apache.log4j.*;
import org.springframework.jdbc.core.*;
import com.bw.cache.vo.*;
import java.sql.*;
import com.bw.exception.*;
import java.util.*;

public class CGFriendRelationimpl extends BaseSpringDao implements CGFriendRelationDAO {

    private static Logger log;
    RowMapper mapper;

    static {
        CGFriendRelationimpl.log = Logger.getLogger(CGFriendRelationimpl.class);
    }

    public CGFriendRelationimpl() {
        this.mapper = new RowMapper() {
            @Override
            public CGFriendRelationVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGFriendRelationVO vo = new CGFriendRelationVO();
                vo.setUserMailAddress(rs.getString("user_mail_address"));
                vo.setFriendMailAddress(rs.getString("friend_mail_address"));
                return vo;
            }
        };
    }

    @Override
    public void delete(final CGFriendRelationVO vo) throws CityCacheDaoException {
        final String sql = "delete from cg_friend_relation where user_mail_address=? and friend_mail_address=?";
        final Object[] objects = {vo.getUserMailAddress(), vo.getFriendMailAddress()};
        this.getJdbcTemplate().update(sql, objects);
    }

    @Override
    public List<String> queryAllCGFriendRelationVO(final CGFriendRelationVO vo) throws CityCacheDaoException {
        final String sql = "select friend_mail_address from cg_friend_relation where  user_mail_address= ?";
        return (List<String>) this.getJdbcTemplate().query(sql, new Object[]{vo.getUserMailAddress()}, new RowMapper() {
            @Override
            public String mapRow(final ResultSet rs, final int arg1) throws SQLException {
                return rs.getString("friend_mail_address");
            }
        });
    }

    @Override
    public long queryCGFriendRelationVOCount(final CGFriendRelationVO vo) throws CityCacheDaoException {
        final String sql = "select count(1) from cg_friend_relation where user_mail_address=?";
        return this.getJdbcTemplate().queryForLong(sql, new Object[]{vo.getUserMailAddress()});
    }

    @Override
    public void save(final CGFriendRelationVO vo) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_friend_relation( user_mail_address, friend_mail_address) VALUES (?, ?)";
        final Object[] objects = {vo.getUserMailAddress(), vo.getFriendMailAddress()};
        this.getJdbcTemplate().update(sql, objects);
    }
}
