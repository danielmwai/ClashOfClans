package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwUserBankLogVO;
import com.bw.dao.BwUserBankLogDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBankLogDaoImpl extends BaseSpringDao implements
        BwUserBankLogDAO {

    private static Logger log = Logger.getLogger(BwUserBankLogDaoImpl.class);

    @Override
    public void delete(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        String sql = "delete from bw_user_bank_log where id=?";
        Long id = bwuserbanklogvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserBankLogVO> queryBwUserBankLogVO(
            BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException {

        List<BwUserBankLogVO> result = null;
        try {
            String sql1 = "select id,buy_gem_count,mail_address,old_gem_total_count,pay_money,pay_time,treasure_id,buy_status,bowei_id,app_id,plant_type,order_sn from bw_user_bank_log where bowei_id=? and buy_status=0 ";
            result = this.getJdbcTemplate().query(sql1,
                    new Object[]{bwuserbanklogvo.getBoweiId()},
                    new BwUserBankLogRowMapper());
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwUserBankLogVO queryBwUserBankLogVOById(
            BwUserBankLogVO bwuserbanklogvo) throws CacheDaoException {
        String sql = "";
        return null;
    }

    @Override
    public long queryBwUserBankLogVOCount(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_bank_log  where bowei_id=? and buy_status=0 and order_sn=? ";
        return this.getJdbcTemplate().queryForLong(sql, new Object[]{bwuserbanklogvo.getBoweiId(), bwuserbanklogvo.getBuyStatus(), bwuserbanklogvo.getOrderSn()});
    }

    @Override
    public List<Long> queryBwUserBankLogVOIds(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        final String insertsql = "insert into bw_user_bank_log (buy_gem_count,mail_address,old_gem_total_count,pay_money,pay_time,treasure_id,buy_status,bowei_id,app_id,plant_type,order_sn ) values (?,?,?,?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwuserbanklogvo.getBuygemcount());
                    ps.setString(2, bwuserbanklogvo.getMailaddress());
                    ps.setLong(3, bwuserbanklogvo.getOldgemtotalcount());
                    ps.setLong(4, bwuserbanklogvo.getPaymoney());
                    ps.setString(5, bwuserbanklogvo.getPaytime());
                    ps.setString(6, bwuserbanklogvo.getTreasureid());
                    ps.setLong(7, bwuserbanklogvo.getBuyStatus());
                    ps.setString(8, bwuserbanklogvo.getBoweiId());
                    ps.setInt(9, bwuserbanklogvo.getAppId());
                    ps.setInt(10, bwuserbanklogvo.getPlantType());
                    ps.setString(11, bwuserbanklogvo.getOrderSn());
                    return ps;
                }
            }, holder);
            bwuserbanklogvo.setId(holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        String sql = "update bw_user_bank_log set buy_gem_count=?,mail_address=?,old_gem_total_count=?,pay_money=?,pay_time=?,treasure_id=? where id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{bwuserbanklogvo.getMailaddress(),
                        bwuserbanklogvo.getBuygemcount(),
                        bwuserbanklogvo.getOldgemtotalcount(),
                        bwuserbanklogvo.getPaymoney(),
                        bwuserbanklogvo.getPaytime(),
                        bwuserbanklogvo.getTreasureid(),
                        bwuserbanklogvo.getId()});
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwUserBankLogRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserBankLogVO fb = new BwUserBankLogVO();
            //select id,buy_gem_count,mail_address,old_gem_total_count,pay_money,pay_time,
            //treasure_id,buy_status,bowei_id from bw_user_bank_log 
            fb.setId(rs.getLong("id"));
            fb.setBuygemcount(rs.getLong("buy_gem_count"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setOldgemtotalcount(rs.getLong("old_gem_total_count"));
            fb.setPaymoney(rs.getInt("pay_money"));
            fb.setPaytime(rs.getString("pay_time"));
            fb.setTreasureid(rs.getString("treasure_id"));
            fb.setBuyStatus(rs.getInt("buy_status"));
            fb.setBoweiId(rs.getString("bowei_id"));
            fb.setAppId(rs.getInt("app_id"));
            fb.setPlantType(rs.getInt("plant_type"));
            fb.setOrderSn(rs.getString("order_sn"));
            return fb;
        }
    }

    @Override
    public LinkedList<String> querySuccessOrder(BwUserBankLogVO bwuserbanklogvo)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return null;
    }

}
