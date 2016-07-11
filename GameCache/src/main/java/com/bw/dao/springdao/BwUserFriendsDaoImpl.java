package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwUserFriendsVO;
import com.bw.dao.BwUserFriendsDAO;
import com.bw.exception.CacheDaoException;

public class BwUserFriendsDaoImpl extends BaseSpringDao implements BwUserFriendsDAO {

    private static Logger log = Logger.getLogger(BwUserFriendsDaoImpl.class);

    @Override
    public void delete(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {
        String sql = "delete from bw_user_friends where id=?";
        Long id = bwuserfriendsvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwUserFriendsVO> queryBwUserFriendsVO(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        String sql = "select id,friend_address,mail_address from bw_user_friends";
        List<BwUserFriendsVO> result = null;
        try {
            if (null == bwuserfriendsvo) {
                result = getJdbcTemplate().query(sql, new BwUserFriendsRowMapper());
            } else {
                String sql1 = "select id,friend_address,mail_address from bw_user_friends where 1=1";
                if (!"".equals(bwuserfriendsvo.getId())) {
                    sql1 += " and id = '" + bwuserfriendsvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1, new BwUserFriendsRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwUserFriendsVO queryBwUserFriendsVOById(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwUserFriendsVOCount(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {
        String sql = "select count(*) from bw_user_friends";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwUserFriendsVOIds(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {
        String insertsql = "insert into bw_user_friends (id,friend_address,mail_address) values (?,?,?)";
        try {
            long id = this.getId(seq);
            getJdbcTemplate().update(insertsql, new Object[]{id,
                bwuserfriendsvo.getMailaddress(),
                bwuserfriendsvo.getFriendaddress(),});
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwUserFriendsVO bwuserfriendsvo) throws CacheDaoException {
        String sql = "update bw_user_friends set friend_address=?,mail_address=? where id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwuserfriendsvo.getMailaddress(),
                bwuserfriendsvo.getFriendaddress(),
                bwuserfriendsvo.getId()
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwUserFriendsRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserFriendsVO fb = new BwUserFriendsVO();
            fb.setId(rs.getLong("id"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setFriendaddress(rs.getString("friend_address"));
            return fb;
        }
    }

}
