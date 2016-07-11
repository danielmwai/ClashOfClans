package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwBattleDestoryVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.dao.BwBattleDAO;
import com.bw.exception.CacheDaoException;

public class BwBattleDaoImpl extends BaseSpringDao implements BwBattleDAO {

    private static Logger log = Logger.getLogger(BwBattleDaoImpl.class);

    @Override
    public void delete(BwBattleVO bwbattlevo) throws CacheDaoException {
        String sql = "delete from bw_battle where id=?";
        Long id = bwbattlevo.getBattleid();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public LinkedList<BwBattleVO> queryBwBattleVO(String mailAddress)
            throws CacheDaoException {

        String sql = "select attacker,battle_id,battle_time,defencer,get_elixir,get_golden,pvp_mark from bw_battle";
        LinkedList<BwBattleVO> result = null;
//		try {
//			if (null == bwbattlevo) {
//				result = getJdbcTemplate().query(sql, new BwBattleRowMapper());
//			} else {
//				String sql1 = "select attacker,battle_id,battle_time,defencer,get_elixir,get_golden,pvp_mark from bw_battle where 1=1";
//				if (!"".equals(bwbattlevo.getBattleid())) {
//					sql1 += " and id = '" + bwbattlevo.getBattleid() + "'";
//				}
//				result = this.getJdbcTemplate().query(sql1,
//						new BwBattleRowMapper());
//
//			}
//		} catch (Exception e) {
//		}
        return result;
    }

    @Override
    public BwBattleVO queryBwBattleVOById(BwBattleVO bwbattlevo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwBattleVOCount(BwBattleVO bwbattlevo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_battle";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwBattleVOIds(BwBattleVO bwbattlevo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwBattleVO bwbattlevo) throws CacheDaoException {
        final String insertsql = "insert into bw_battle (attacker,battle_time,defencer,get_elixir,get_golden,pvp_mark,battle_result) values (?,?,?,?,?,?,?)";
        // final	String insertsql = "insert into bw_user_map_data (build_id,build_level,mail_address,map_index_x,map_index_y,status,uniqueness_build_id,upgrade_finish_time) values (?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setString(1, bwbattlevo.getAttacker());
                    ps.setString(2, bwbattlevo.getBattletime());
                    ps.setString(3, bwbattlevo.getDefencer());
                    ps.setInt(4, (int) bwbattlevo.getGetelixir());
                    ps.setInt(5, (int) bwbattlevo.getGetgolden());
                    ps.setInt(6, (int) bwbattlevo.getPvpmark());
                    ps.setInt(7, bwbattlevo.getBattleResult());
                    return ps;
                }
            }, holder);
            bwbattlevo.setBattleid(holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwBattleVO bwbattlevo) throws CacheDaoException {
        String sql = "update bw_battle set attacker=?,battle_time=?,defencer=?,get_elixir=?,get_golden=?,pvp_mark=? where battle_id=?";
        try {
            this.getJdbcTemplate()
                    .update(
                            sql,
                            new Object[]{
                                bwbattlevo.getAttacker(),
                                bwbattlevo.getBattletime(),
                                bwbattlevo.getDefencer(),
                                bwbattlevo.getGetelixir(),
                                bwbattlevo.getGetgolden(),
                                bwbattlevo.getPvpmark(),
                                bwbattlevo.getBattleid()
                            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwBattleRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBattleVO fb = new BwBattleVO();

            fb.setAttacker(rs.getString("attacker"));
            fb.setBattleid(rs.getLong("battle_id"));
            fb.setBattletime(rs.getString("battle_time"));
            fb.setDefencer(rs.getString("defencer"));
            fb.setGetelixir(rs.getLong("get_elixir"));
            fb.setGetgolden(rs.getLong("get_golden"));
            fb.setPvpmark(rs.getLong("pvp_mark"));
            return fb;
        }
    }

    @Override
    public void deleteBattleDestory(String mailAddress)
            throws CacheDaoException {

    }

    @Override
    public void deleteBwBattledListVO(String mailAddress)
            throws CacheDaoException {

    }

    @Override
    public List<BwBattleDestoryVO> queryBwBattleDestoryVO(String mailAddress)
            throws CacheDaoException {
        return null;
    }

    @Override
    public void updateBattleDestory(String mailAddress,
            List<BwBattleDestoryVO> bwBattleDestoryVOList)
            throws CacheDaoException {

    }

    @Override
    public void saveBattledList(String defencerMailAddress,
            BwBattleVO bwBattledListVO) throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void batchUpdate(final List<BwBattleVO> bwbattlevoList)
            throws CacheDaoException {
        String updateSql = "update bw_battle set attacker=?,battle_time=?,defencer=?,get_elixir=?,get_golden=?,pvp_mark=? where battle_id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwBattleVO> it = bwbattlevoList.iterator();

                public int getBatchSize() {
                    return bwbattlevoList.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwBattleVO bwbattlevo = it.next();
                        ps.setString(col++, bwbattlevo.getAttacker());
                        ps.setString(col++, bwbattlevo.getBattletime());
                        ps.setString(col++, bwbattlevo.getDefencer());
                        ps.setInt(col++, (int) bwbattlevo.getGetelixir());
                        ps.setInt(col++, (int) bwbattlevo.getGetgolden());
                        ps.setInt(col++, (int) bwbattlevo.getPvpmark());
                        ps.setLong(col++, bwbattlevo.getBattleid());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(bwbattlevoList.get(x));
                    log.info("更新战斗信息变插入战斗信息.....");
                }
            }
        } catch (Exception e) {
            log.error("" + e);
            throw new CacheDaoException(e);
        }
    }

    @Override
    public long queryBwBattleVOCountFlag(String mailAddress)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void updateBwBattleVOCountFlag(String mailAddress, int count)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

}
