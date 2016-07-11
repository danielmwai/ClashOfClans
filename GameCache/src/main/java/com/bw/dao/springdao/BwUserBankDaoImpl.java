package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwUserBankVO;
import com.bw.dao.BwUserBankDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBankDaoImpl extends BaseSpringDao implements BwUserBankDAO {

    private static Logger log = Logger.getLogger(BwUserBankDaoImpl.class);

    @Override
    public void delete(BwUserBankVO bwuserbankvo) throws CacheDaoException {
        String sql = "delete from bw_user_bank where id=?";
        Long id = bwuserbankvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwUserBankVO> queryBwUserBankVO(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {

        String sql = "select gem_total_count,last_update_time,mail_address from bw_user_bank";
        List<BwUserBankVO> result = null;
        try {
            if (null == bwuserbankvo) {
                result = getJdbcTemplate()
                        .query(sql, new BwUserBankRowMapper());
            } else {
                String sql1 = "select gem_total_count,last_update_time,mail_address from bw_user_bank where 1=1";
                if (!"".equals(bwuserbankvo.getMailaddress())) {
                    sql1 += " and id = '" + bwuserbankvo.getMailaddress() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwUserBankRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserBankVO queryBwUserBankVOById(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {
        String sql = "select gem_total_count,last_update_time,mail_address,id,pay_total_money,bowei_id from bw_user_bank where bowei_id=?";
        List<BwUserBankVO> result = null;
        result = this.getJdbcTemplate().query(sql, new Object[]{bwuserbankvo.getBoweiId()}, new BwUserBankRowMapper());
        if (null != result && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public long queryBwUserBankVOCount(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_bank";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwUserBankVOIds(BwUserBankVO bwuserbankvo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwUserBankVO bwuserbankvo) throws CacheDaoException {
        final String insertsql = "insert into bw_user_bank (gem_total_count,last_update_time,mail_address,pay_total_money,bowei_id) values (?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwuserbankvo.getGemtotalcount());
                    ps.setString(2, bwuserbankvo.getLastupdatetime());
                    ps.setString(3, bwuserbankvo.getMailaddress());
                    ps.setLong(4, bwuserbankvo.getPayTotalMoney());
                    ps.setString(5, bwuserbankvo.getBoweiId());
                    return ps;
                }
            }, holder);
            bwuserbankvo.setId(holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwUserBankVO bwuserbankvo) throws CacheDaoException {
        String sql = "update bw_user_bank set gem_total_count=?,last_update_time=?,pay_total_money=? where id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{bwuserbankvo.getGemtotalcount(),
                        bwuserbankvo.getLastupdatetime(),
                        bwuserbankvo.getPayTotalMoney(),
                        bwuserbankvo.getId()
                    });
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    private class BwUserBankRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserBankVO fb = new BwUserBankVO();

            fb.setGemtotalcount(rs.getLong("gem_total_count"));
            fb.setLastupdatetime(rs.getString("last_update_time"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setId(rs.getLong("id"));
            fb.setPayTotalMoney(rs.getLong("pay_total_money"));
            fb.setBoweiId(rs.getString("bowei_id"));
            return fb;
        }
    }

}
