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
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.dao.BwMineCollectorDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 金矿和药水DAO
 */
public class BwMineCollectorDaoImpl extends BaseSpringDao implements
        BwMineCollectorDAO {

    private static Logger log = Logger.getLogger(BwMineCollectorDaoImpl.class);

    @Override
    public void delete(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
        String sql = "delete from bw_mine_collector where user_building_data_id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{bwminecollectorvo.getUserbuildingdataid()});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwMineCollectorVO> queryBwMineCollectorVO(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {

        String sql = "select id,harvest_start_time,produce_count,user_building_data_id from bw_mine_collector";
        List<BwMineCollectorVO> result = null;
        try {
            if (null == bwminecollectorvo) {
                result = getJdbcTemplate().query(sql,
                        new BwMineCollectorRowMapper());
            } else {
                String sql1 = "select id,harvest_start_time,produce_count,user_building_data_id from bw_mine_collector where 1=1";
                if (!"".equals(bwminecollectorvo.getId())) {
                    sql1 += " and id = '" + bwminecollectorvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwMineCollectorRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwMineCollectorVO queryBwMineCollectorVOById(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {

        String sql = "select id,harvest_start_time,user_building_data_id,produce_count from bw_mine_collector where user_building_data_id=?";
        List<BwMineCollectorVO> result = null;

        try {
            result = getJdbcTemplate().query(sql, new Object[]{bwminecollectorvo.getUserbuildingdataid()}, new BwMineCollectorRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);

        }

        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwMineCollectorVOCount(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_mine_collector";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwMineCollectorVOIds(
            BwMineCollectorVO bwminecollectorvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {

        final String insertsql = "insert into bw_mine_collector (harvest_start_time,produce_count,user_building_data_id) values (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, bwminecollectorvo.getHarveststarttime());
                    ps.setLong(2, bwminecollectorvo.getProducecount());
                    ps.setLong(3, bwminecollectorvo.getUserbuildingdataid());
                    return ps;
                }
            }, holder);
            bwminecollectorvo.setId(holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwMineCollectorVO bwminecollectorvo)
            throws CacheDaoException {
        String sql = "update bw_mine_collector set harvest_start_time=?,produce_count=? where user_building_data_id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwminecollectorvo.getHarveststarttime(),
                        bwminecollectorvo.getProducecount(),
                        bwminecollectorvo.getUserbuildingdataid(),});
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwMineCollectorRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwMineCollectorVO fb = new BwMineCollectorVO();

            fb.setId(rs.getLong("id"));
            fb.setProducecount(rs.getLong("produce_count"));
            fb.setHarveststarttime(rs.getString("harvest_start_time"));
            fb.setUserbuildingdataid(rs.getLong("user_building_data_id"));
            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwMineCollectorVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_mine_collector set harvest_start_time=?,produce_count=? where user_building_data_id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwMineCollectorVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwMineCollectorVO bwbattlevo = it.next();
                        ps.setString(col++, bwbattlevo.getHarveststarttime());
                        ps.setInt(col++, (int) bwbattlevo.getProducecount());
                        ps.setLong(col++, bwbattlevo.getUserbuildingdataid());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新收集金币和药水信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新收集金币和药水信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
