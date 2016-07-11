package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwClansMemberVO;
import com.bw.dao.BwClansMemberDAO;
import com.bw.exception.CacheDaoException;

public class BwClansMemberDaoImpl extends BaseSpringDao implements BwClansMemberDAO {

    private static Logger log = Logger.getLogger(BwClansMemberDaoImpl.class);

    @Override
    public void delete(BwClansMemberVO bwclansmembervo) throws CacheDaoException {
        String sql = "delete from bw_clans_member where id=?";
        Long id = bwclansmembervo.getClansid();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwClansMemberVO> queryBwClansMemberVO(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        String sql = "select clans_id,member_mail_address,privilege_id from bw_clans_member";
        List<BwClansMemberVO> result = null;
        try {
            if (null == bwclansmembervo) {
                result = getJdbcTemplate().query(sql, new BwClansMemberRowMapper());
            } else {
                String sql1 = "select clans_id,member_mail_address,privilege_id from bw_clans_member where 1=1";
                if (!"".equals(bwclansmembervo.getClansid())) {
                    sql1 += " and id = '" + bwclansmembervo.getClansid() + "'";
                }
                result = this.getJdbcTemplate().query(sql1, new BwClansMemberRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwClansMemberVO queryBwClansMemberVOById(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansMemberVOCount(BwClansMemberVO bwclansmembervo) throws CacheDaoException {
        String sql = "select count(*) from bw_clans_member";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwClansMemberVOIds(BwClansMemberVO bwclansmembervo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(BwClansMemberVO bwclansmembervo) throws CacheDaoException {
        String insertsql = "insert into bw_clans_member (clans_id,member_mail_address,privilege_id) values (?,?,?)";
        try {
            long id = this.getId(seq);
            getJdbcTemplate().update(insertsql, new Object[]{id,
                bwclansmembervo.getClansid(),
                bwclansmembervo.getMembermailaddress(),
                bwclansmembervo.getPrivilegeid()
            });
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwClansMemberVO bwclansmembervo) throws CacheDaoException {
        String sql = "update bw_clans_member set clans_id=?,member_mail_address=?,privilege_id=? where id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwclansmembervo.getClansid(),
                bwclansmembervo.getMembermailaddress(),
                bwclansmembervo.getPrivilegeid()
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwClansMemberRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwClansMemberVO fb = new BwClansMemberVO();

            fb.setClansid(rs.getLong("clans_id"));
            fb.setMembermailaddress(rs.getString("member_mail_address"));
            return fb;
        }
    }

}
