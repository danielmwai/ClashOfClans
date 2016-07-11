package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwBattleVO;
import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.dao.BwMineCollectorAllDAO;
import com.bw.exception.CacheDaoException;

public class BwMineCollectorAllDaoImpl extends BaseSpringDao implements
        BwMineCollectorAllDAO {

    private static Logger log = Logger
            .getLogger(BwMineCollectorAllDaoImpl.class);

    @Override
    public void delete(BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
        String sql = "delete from bw_mine_collector_all where user_building_data_id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{bwminecollectorallvo.getUserbuildingdataid()});
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwMineCollectorAllVO> queryBwMineCollectorAllVO(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {

        String sql = "select id,collect_count,user_building_data_id,second_elixir_count from bw_mine_collector_all";
        List<BwMineCollectorAllVO> result = null;
        try {
            if (null == bwminecollectorallvo) {
                result = getJdbcTemplate().query(sql,
                        new BwMineCollectorAllRowMapper());
            } else {
                String sql1 = "select id,collect_count,user_building_data_id,second_elixir_count from bw_mine_collector_all where 1=1";
                if (!"".equals(bwminecollectorallvo.getUserbuildingdataid())) {
                    sql1 += " and user_building_data_id = '" + bwminecollectorallvo.getUserbuildingdataid() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwMineCollectorAllRowMapper());

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            throw new CacheDaoException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwMineCollectorAllVO queryBwMineCollectorAllVOById(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {
        List<BwMineCollectorAllVO> result = null;
        String sql = "select id,collect_count,user_building_data_id,second_elixir_count from bw_mine_collector_all where user_building_data_id=?";
        try {
            result = getJdbcTemplate().query(sql, new Object[]{bwminecollectorallvo.getUserbuildingdataid()}, new BwMineCollectorAllRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
        return result == null || result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwMineCollectorAllVOCount(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {
        String sql = "select count(*) from bw_mine_collector_all";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwMineCollectorAllVOIds(
            BwMineCollectorAllVO bwminecollectorallvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
        final String insertsql = "insert into bw_mine_collector_all (collect_count,user_building_data_id,second_elixir_count) values (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {

            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwminecollectorallvo.getCollectcount());
                    ps.setLong(2, bwminecollectorallvo.getUserbuildingdataid());
                    ps.setLong(3, bwminecollectorallvo.getSecondElixirCount());
                    return ps;
                }
            }, holder);
            bwminecollectorallvo.setId(holder.getKey().longValue());
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwMineCollectorAllVO bwminecollectorallvo)
            throws CacheDaoException {
        String sql = "update bw_mine_collector_all set collect_count=?,second_elixir_count=? where user_building_data_id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwminecollectorallvo.getCollectcount(),
                        bwminecollectorallvo.getSecondElixirCount(),
                        bwminecollectorallvo.getUserbuildingdataid()

                    });
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }
    }

    private class BwMineCollectorAllRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwMineCollectorAllVO fb = new BwMineCollectorAllVO();
            fb.setId(rs.getLong("id"));
            fb.setUserbuildingdataid(rs.getLong("user_building_data_id"));
            fb.setCollectcount(rs.getLong("collect_count"));
            fb.setSecondElixirCount(rs.getLong("second_elixir_count"));
            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwMineCollectorAllVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_mine_collector_all set collect_count=?,second_elixir_count=? where user_building_data_id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwMineCollectorAllVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwMineCollectorAllVO bwbattlevo = it.next();
                        ps.setInt(col++, (int) bwbattlevo.getCollectcount());
                        ps.setInt(col++, (int) bwbattlevo.getSecondElixirCount());
                        ps.setLong(col++, bwbattlevo.getUserbuildingdataid());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新金库 药库信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新金库药库信息发送异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
