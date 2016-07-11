package com.bw.dao.springdao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import org.springframework.jdbc.core.PreparedStatementCreator;

import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.SqlProvider;

import org.springframework.util.Assert;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.support.KeyHolder;

import com.bw.dao.springdao.BaseSpringDao;

import com.bw.dao.BwCanclePersionDAO;
import com.bw.baseJar.vo.BwCanclePersionVO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 销毁用户数据
 */
public class BwCanclePersionDaoImpl extends BaseSpringDao implements
        BwCanclePersionDAO {

    private static Logger log = Logger.getLogger(BwCanclePersionDaoImpl.class);

    @Override
    public void delete(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {
        String sql = "delete from bw_cancle_persion where id=?";
        Long id = bwcanclepersionvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwCanclePersionVO> queryBwCanclePersionVO(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        String sql = "select id,bowei_id,create_time from bw_cancle_persion";
        List<BwCanclePersionVO> result = null;
        try {
            if (null == bwcanclepersionvo) {
                result = getJdbcTemplate().query(sql,
                        new BwCanclePersionRowMapper());
            } else {
                String sql1 = "select id,bowei_id,create_time from bw_cancle_persion where 1=1";
                if (!"".equals(bwcanclepersionvo.getId())) {
                    sql1 += " and id = '" + bwcanclepersionvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwCanclePersionRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwCanclePersionVO queryBwCanclePersionVOById(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        List<BwCanclePersionVO> result = null;

        String sql = "select id,bowei_id,create_time from bw_cancle_persion";

        try {

            result = getJdbcTemplate().query(sql,
                    new BwCanclePersionRowMapper());

        } catch (DataAccessException e) {

            e.printStackTrace();

            log.error(e.getMessage());

            throw new CacheDaoException(e);

        }
        return result == null || result.size() <= 0 ? null : result.get(0);

    }

    @Override
    public long queryBwCanclePersionVOCount(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_cancle_persion";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwCanclePersionVOIds(
            BwCanclePersionVO bwcanclepersionvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {
        final String insertsql = "insert into bw_cancle_persion (bowei_id,create_time) values (?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        try {

            this.getJdbcTemplate().update(new PreparedStatementCreator() {

                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {

                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, bwcanclepersionvo.getBoweiId());
                    ps.setString(2, bwcanclepersionvo.getCreateTime());

                    return ps;

                }

            }, holder);

            bwcanclepersionvo.setId(holder.getKey().longValue());

        } catch (InvalidDataAccessApiUsageException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);

        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);

        }

    }

    @Override
    public void update(BwCanclePersionVO bwcanclepersionvo)
            throws CacheDaoException {
        String sql = "update bw_cancle_persion set bowei_id=?,create_time=? where id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{bwcanclepersionvo.getBoweiId(),
                        bwcanclepersionvo.getCreateTime(),
                        bwcanclepersionvo.getId()});
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void batchUpdate(final List<BwCanclePersionVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_cancle_persion set bowei_id=?,create_time=? where id=?";

        try {

            int result[] = getJdbcTemplate().batchUpdate(updateSql,
                    new BatchPreparedStatementSetter() {

                Iterator<BwCanclePersionVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();

                }

                public void setValues(PreparedStatement ps, int index)
                        throws SQLException {
                    if (it.hasNext()) {
                        BwCanclePersionVO bwcanclepersionvo = it.next();
                        ps.setString(1, bwcanclepersionvo.getBoweiId());
                        ps.setString(2,
                                bwcanclepersionvo.getCreateTime());
                        ps.setLong(3, bwcanclepersionvo.getId());

                    }

                }

            });

            for (int x = 0; x < result.length; x++) {

                if (result[x] == 0) {

                    this.save(listtime1.get(x));

                }

            }

        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);

        }

    }

    private class BwCanclePersionRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwCanclePersionVO fb = new BwCanclePersionVO();

            fb.setId(rs.getLong("id"));
            fb.setBoweiId(rs.getString("bowei_id"));
            fb.setCreateTime(rs.getString("create_time"));
            return fb;
        }
    }

}
