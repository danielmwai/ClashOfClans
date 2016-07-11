package com.bw.dao.springdao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.dao.BwUserBattleStatisticsDAO;
import com.bw.exception.CacheDaoException;

public class BwUserBattleStatisticsDaoImpl extends BaseSpringDao implements
        BwUserBattleStatisticsDAO {

    private static Logger log = Logger
            .getLogger(BwUserBattleStatisticsDaoImpl.class);

    @Override
    public void delete(BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        String sql = "delete from bw_user_battle_statistics where id=?";
        String id = bwuserbattlestatisticsvo.getMailaddress();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwUserBattleStatisticsVO> queryBwUserBattleStatisticsVO(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {

        String sql = "select clans_id,fail_times,get_elixir_count,get_golden_count,mail_address,max_pvp_mark,win_times from bw_user_battle_statistics";
        List<BwUserBattleStatisticsVO> result = null;
        try {
            if (null == bwuserbattlestatisticsvo) {
                result = getJdbcTemplate().query(sql,
                        new BwUserBattleStatisticsRowMapper());
            } else {
                String sql1 = "select clans_id,fail_times,get_elixir_count,get_golden_count,mail_address,max_pvp_mark,win_times from bw_user_battle_statistics where 1=1";
                if (!"".equals(bwuserbattlestatisticsvo.getMailaddress())) {
                    sql1 += " and id = '"
                            + bwuserbattlestatisticsvo.getMailaddress() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwUserBattleStatisticsRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserBattleStatisticsVO queryBwUserBattleStatisticsVOById(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        String sql = "select clans_id,fail_times,get_elixir_count,get_golden_count,mail_address,max_pvp_mark,win_times from bw_user_battle_statistics where mail_address=?";
        List<BwUserBattleStatisticsVO> result = null;
        result = (List<BwUserBattleStatisticsVO>) this.getJdbcTemplate().query(
                sql,
                new Object[]{bwuserbattlestatisticsvo.getMailaddress()},
                new BwUserBattleStatisticsRowMapper());
        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwUserBattleStatisticsVOCount(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_battle_statistics";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwUserBattleStatisticsVOIds(
            BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        final String insertsql = "insert into bw_user_battle_statistics (clans_id,"
                + "fail_times,get_elixir_count,"
                + "get_golden_count,mail_address,max_pvp_mark,win_times) values (?,?,?,?,?,?,?)";
        try {
            this.getJdbcTemplate().update(
                    insertsql,
                    new Object[]{bwuserbattlestatisticsvo.getClansid(),
                        bwuserbattlestatisticsvo.getFailtimes(),
                        bwuserbattlestatisticsvo.getGetelixircount(),
                        bwuserbattlestatisticsvo.getGetgoldencount(),
                        bwuserbattlestatisticsvo.getMailaddress(),
                        bwuserbattlestatisticsvo.getMaxpvpmark(),
                        bwuserbattlestatisticsvo.getWintimes()});
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwUserBattleStatisticsVO bwuserbattlestatisticsvo)
            throws CacheDaoException {
        String sql = "update bw_user_battle_statistics set clans_id=?,fail_times=?,get_elixir_count=?,get_golden_count=?,max_pvp_mark=?,win_times=? where mail_address=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwuserbattlestatisticsvo.getClansid(),
                        bwuserbattlestatisticsvo.getFailtimes(),
                        bwuserbattlestatisticsvo.getGetelixircount(),
                        bwuserbattlestatisticsvo.getGetgoldencount(),
                        bwuserbattlestatisticsvo.getMaxpvpmark(),
                        bwuserbattlestatisticsvo.getWintimes(),
                        bwuserbattlestatisticsvo.getMailaddress(),});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    private class BwUserBattleStatisticsRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserBattleStatisticsVO fb = new BwUserBattleStatisticsVO();

            fb.setClansid(rs.getLong("clans_id"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setFailtimes(rs.getLong("fail_times"));
            fb.setGetelixircount(rs.getLong("get_elixir_count"));
            fb.setGetgoldencount(rs.getLong("get_golden_count"));
            fb.setMaxpvpmark(rs.getLong("max_pvp_mark"));
            fb.setWintimes(rs.getLong("win_times"));
            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwUserBattleStatisticsVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_user_battle_statistics set clans_id=?,fail_times=?,get_elixir_count=?,get_golden_count=?,max_pvp_mark=?,win_times=? where mail_address=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwUserBattleStatisticsVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwUserBattleStatisticsVO bwuserbattlestatisticsvo = it.next();
                        ps.setLong(col++, bwuserbattlestatisticsvo.getClansid());
                        ps.setInt(col++, (int) bwuserbattlestatisticsvo.getFailtimes());
                        ps.setLong(col++, bwuserbattlestatisticsvo.getGetelixircount());
                        ps.setLong(col++, bwuserbattlestatisticsvo.getGetgoldencount());
                        ps.setInt(col++, (int) bwuserbattlestatisticsvo.getMaxpvpmark());
                        ps.setInt(col++, (int) bwuserbattlestatisticsvo.getWintimes());
                        ps.setString(col++, bwuserbattlestatisticsvo.getMailaddress());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新用户战斗统计信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新用户战斗统计信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
