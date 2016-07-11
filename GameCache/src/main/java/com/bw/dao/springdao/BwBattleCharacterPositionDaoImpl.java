package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwBattleCharacterPositionVO;
import com.bw.dao.BwBattleCharacterPositionDAO;
import com.bw.exception.CacheDaoException;

public class BwBattleCharacterPositionDaoImpl extends BaseSpringDao implements BwBattleCharacterPositionDAO {

    private static Logger log = Logger.getLogger(BwBattleCharacterPositionDaoImpl.class);

    @Override
    public void delete(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {
        String sql = "delete from bw_battle_character_position where id=?";
        Long id = bwbattlecharacterpositionvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwBattleCharacterPositionVO> queryBwBattleCharacterPositionVO(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {

        String sql = "select id,battle_id,character_id,count,level,map_index from bw_battle_character_position";
        List<BwBattleCharacterPositionVO> result = null;
        try {
            if (null == bwbattlecharacterpositionvo) {
                result = getJdbcTemplate().query(sql, new BwBattleCharacterPositionRowMapper());
            } else {
                String sql1 = "select id,battle_id,character_id,count,level,map_index from bw_battle_character_position where 1=1";
                if (!"".equals(bwbattlecharacterpositionvo.getId())) {
                    sql1 += " and id = '" + bwbattlecharacterpositionvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1, new BwBattleCharacterPositionRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwBattleCharacterPositionVO queryBwBattleCharacterPositionVOById(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwBattleCharacterPositionVOCount(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {
        String sql = "select count(*) from bw_battle_character_position";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwBattleCharacterPositionVOIds(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {
        String insertsql = "insert into bw_battle_character_position (id,battle_id,character_id,count,level,map_index) values (?,?,?,?,?,?)";
        try {
            long id = this.getId(seq);
            getJdbcTemplate().update(insertsql, new Object[]{id,
                bwbattlecharacterpositionvo.getBattleid(),
                bwbattlecharacterpositionvo.getCharacterid(),
                bwbattlecharacterpositionvo.getMapindex(),
                bwbattlecharacterpositionvo.getLevel(),
                bwbattlecharacterpositionvo.getCount()
            });
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwBattleCharacterPositionVO bwbattlecharacterpositionvo) throws CacheDaoException {
        String sql = "update bw_battle_character_position set battle_id=?,character_id=?,count=?,level=?,map_index=? where id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwbattlecharacterpositionvo.getBattleid(),
                bwbattlecharacterpositionvo.getCharacterid(),
                bwbattlecharacterpositionvo.getMapindex(),
                bwbattlecharacterpositionvo.getId(),
                bwbattlecharacterpositionvo.getLevel(),
                bwbattlecharacterpositionvo.getCount()
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwBattleCharacterPositionRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBattleCharacterPositionVO fb = new BwBattleCharacterPositionVO();

            fb.setId(rs.getLong("id"));
            fb.setBattleid(rs.getLong("battle_id"));
            return fb;
        }
    }

}
