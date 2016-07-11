package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwClansVO;
import com.bw.dao.BwClansDAO;
import com.bw.exception.CacheDaoException;

public class BwClansDaoImpl extends BaseSpringDao implements BwClansDAO {

    private static Logger log = Logger.getLogger(BwClansDaoImpl.class);

    @Override
    public void delete(BwClansVO bwclansvo) throws CacheDaoException {
        String sql = "delete from bw_clans where id=?";
        Long id = bwclansvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwClansVO> queryBwClansVO(BwClansVO bwclansvo) throws CacheDaoException {

        String sql = "select id,clans_name,clans_owner from bw_clans";
        List<BwClansVO> result = null;
        try {
            if (null == bwclansvo) {
                result = getJdbcTemplate().query(sql, new BwClansRowMapper());
            } else {
                String sql1 = "select id,clans_name,clans_owner from bw_clans where 1=1";
                if (!"".equals(bwclansvo.getId())) {
                    sql1 += " and id = '" + bwclansvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1, new BwClansRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwClansVO queryBwClansVOById(BwClansVO bwclansvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansVOCount(BwClansVO bwclansvo) throws CacheDaoException {
        String sql = "select count(*) from bw_clans";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwClansVOIds(BwClansVO bwclansvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(BwClansVO bwclansvo) throws CacheDaoException {
        String insertsql = "insert into bw_clans (id,clans_name,clans_owner) values (?,?,?)";
        try {
            long id = this.getId(seq);
            getJdbcTemplate().update(insertsql, new Object[]{id,
                bwclansvo.getClansname(),
                bwclansvo.getClansowner(),});
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwClansVO bwclansvo) throws CacheDaoException {
        String sql = "update bw_clans set clans_name=?,clans_owner=? where id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwclansvo.getClansname(),
                bwclansvo.getClansowner(),
                bwclansvo.getId()
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwClansRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwClansVO fb = new BwClansVO();

            fb.setId(rs.getLong("id"));
            fb.setClansname(rs.getString("clans_name"));
            fb.setClansowner(rs.getString("clans_owner"));
            return fb;
        }
    }

}
