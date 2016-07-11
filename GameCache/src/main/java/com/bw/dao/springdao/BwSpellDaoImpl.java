package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwSpellVO;
import com.bw.dao.BwSpellDAO;
import com.bw.exception.CacheDaoException;

public class BwSpellDaoImpl extends BaseSpringDao implements BwSpellDAO {

    @Override
    public void delete(BwSpellVO bwSpellVO) throws CacheDaoException {
        String sql = "delete from bw_spell where id=?";
        Long id = bwSpellVO.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public List<Long> getAllSpellUserMapId(String mailAddress)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAllBarrackUserMapId(String mailAddress,
            List<Long> userMapIdList) throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwSpellVO> queryBwSpellVO(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        List<BwSpellVO> result = null;
        String sql = "select id,user_map_data_id,spell_type_id,spell_level,spell_count,spell_create_start_time,spell_status,spell_create_end_time,mai_address from bw_spell where mai_address=? ";
        try {
            result = getJdbcTemplate().query(sql, new Object[]{bwSpellVO.getMailaddress()}, new BwSpellRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwSpellVO queryBwSpellVOById(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        List<BwSpellVO> result = null;
        String sql = "select id,mai_address,user_map_data_id,spell_type_id,spell_level,spell_count,spell_create_start_time,spell_status,spell_create_end_time from bw_spell where mai_address=? and spell_type_id=? ";
        try {
            result = getJdbcTemplate().query(sql, new Object[]{bwSpellVO.getMailaddress(), bwSpellVO.getSpelltypeid()}, new BwSpellRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwSpellVOCount(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Long> queryBwSpellVOIds(BwSpellVO bwSpellVO)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(final BwSpellVO bwSpellVO) throws CacheDaoException {

        final String insertSql = "insert into bw_spell (user_map_data_id,spell_type_id,spell_level,spell_count,spell_create_start_time,spell_status,spell_create_end_time,mai_address) values (?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwSpellVO.getUsermapdataid());
                    ps.setInt(2, bwSpellVO.getSpelltypeid());
                    ps.setLong(3, bwSpellVO.getSpelllevel());
                    ps.setInt(4, bwSpellVO.getSpellcount());
                    ps.setString(5, bwSpellVO.getSpellcreatestarttime());
                    ps.setShort(6, bwSpellVO.getSpellstatus());
                    ps.setString(7, bwSpellVO.getSpellcreateendtime());
                    ps.setString(8, bwSpellVO.getMailaddress());
                    return ps;
                }
            }, holder);

        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }

    }

    @Override
    public void update(BwSpellVO bwSpellVO) throws CacheDaoException {
        String sql = "update bw_spell set spell_level=?,spell_count=?,spell_create_start_time=?,spell_status=?,spell_create_end_time=?,user_map_data_id=? where mai_address=? and spell_type_id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwSpellVO.getSpelllevel(),
                bwSpellVO.getSpellcount(),
                bwSpellVO.getSpellcreatestarttime(),
                bwSpellVO.getSpellstatus(),
                bwSpellVO.getSpellcreateendtime(),
                bwSpellVO.getUsermapdataid(),
                bwSpellVO.getMailaddress(),
                bwSpellVO.getSpelltypeid(),});

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }

    }

    private class BwSpellRowMapper implements RowMapper {

        @Override
        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwSpellVO bv = new BwSpellVO();
            bv.setId(rs.getLong("id"));
            bv.setUsermapdataid(rs.getLong("user_map_data_id"));
            bv.setMailaddress(rs.getString("mai_address"));
            bv.setSpelltypeid(rs.getInt("spell_type_id"));
            bv.setSpelllevel(rs.getInt("spell_level"));
            bv.setSpellcount(rs.getInt("spell_count"));
            bv.setSpellcreatestarttime(rs.getString("spell_create_start_time"));
            bv.setSpellstatus(rs.getShort("spell_status"));
            bv.setSpellcreateendtime(rs.getString("spell_create_end_time"));
            return bv;
        }

    }

}
