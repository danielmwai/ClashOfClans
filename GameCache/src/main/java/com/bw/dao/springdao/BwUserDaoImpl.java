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

import com.bw.baseJar.vo.StopServerMessageVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.cache.vo.PVPGradeOrderVO;
import com.bw.dao.BwUserDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao 获取用户信息
 */
public class BwUserDaoImpl extends BaseSpringDao implements BwUserDAO {

    private static Logger log = Logger.getLogger(BwUserDaoImpl.class);

    @Override
    public void delete(BwUserVO bwuservo) throws CacheDaoException {
        String sql = "delete from bw_user where id=?";
        Long id = bwuservo.getGoldencount();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserVO> queryBwUserVO(BwUserVO bwuservo)
            throws CacheDaoException {

        //String sql = "select elixir_count,exp,golden_count,last_login_time,level,mail_address,nick_name,pvp_mark,mac_address,worker_count from bw_user";
        List<BwUserVO> result = null;
        try {
            result = (List<BwUserVO>) getJdbcTemplate().query(bwuservo.getPvpGradeOrderSQL(), new BwUserRowMapperPVPGrade());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserVO queryBwUserVOById(BwUserVO bwuservo)
            throws CacheDaoException {
        String sql = "select id, elixir_count,exp,golden_count,last_login_time,level,mail_address,nick_name,pvp_mark,mac_address,worker_count,max_golden_count,max_elixir_count,area_id,last_login_time,shield_end_time from bw_user where mail_address=?";
        List<BwUserVO> result = null;
        try {

            result = getJdbcTemplate().query(sql, new Object[]{bwuservo.getMailaddress()}, new BwUserRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwUserVOCount(BwUserVO bwuservo) throws CacheDaoException {
        String sql = "select count(*) from bw_user";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwUserVOIds(BwUserVO bwuservo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwUserVO bwuservo) throws CacheDaoException {
        final String insertsql = "insert into bw_user (golden_count,elixir_count,pvp_mark,exp,level,nick_name,mail_address,last_login_time,mac_address,worker_count,max_golden_count,max_elixir_count,area_id,shield_end_time ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwuservo.getGoldencount());
                    ps.setLong(2, bwuservo.getElixircount());
                    ps.setLong(3, bwuservo.getPvpmark());
                    ps.setLong(4, bwuservo.getExp());
                    ps.setInt(5, bwuservo.getLevel());
                    ps.setString(6, bwuservo.getNickname());
                    ps.setString(7, bwuservo.getMailaddress());
                    ps.setString(8, bwuservo.getLastlogintime());
                    ps.setString(9, bwuservo.getMacAddress());
                    ps.setInt(10, bwuservo.getWorkCount());
                    ps.setInt(11, bwuservo.getMaxGoldenCount());
                    ps.setInt(12, bwuservo.getMaxElixirCount());
                    ps.setInt(13, bwuservo.getAreaId());
                    ps.setString(14, bwuservo.getShieldEndTime());
                    return ps;
                }
            }, holder);
            bwuservo.setId(holder.getKey().longValue());
            System.out.println("@@@@@@@@@@@@@@@:User_id value:"
                    + holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwUserVO bwuservo) throws CacheDaoException {
        String sql = "update bw_user set elixir_count=?,exp=?,golden_count=?,last_login_time=?,level=?,nick_name=?,pvp_mark=?,mac_address=?,worker_count=? ,max_golden_count=?,max_elixir_count=?,last_login_time=?,shield_end_time=? where mail_address=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwuservo.getElixircount(),
                        bwuservo.getExp(),
                        bwuservo.getGoldencount(),
                        bwuservo.getLastlogintime(),
                        bwuservo.getLevel(),
                        bwuservo.getNickname(),
                        bwuservo.getPvpmark(),
                        bwuservo.getMacAddress(),
                        bwuservo.getWorkCount(),
                        bwuservo.getMaxGoldenCount(),
                        bwuservo.getMaxElixirCount(),
                        bwuservo.getLastlogintime(),
                        bwuservo.getShieldEndTime(),
                        bwuservo.getMailaddress(),});
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private class BwUserRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserVO fb = new BwUserVO();
            //mac_address,worker_count
            //area_id,last_login_time,shield_end_time
            fb.setId(rs.getLong("id"));
            fb.setElixircount(rs.getLong("elixir_count"));
            fb.setExp(rs.getLong("exp"));
            fb.setLevel(rs.getInt("level"));
            fb.setGoldencount(rs.getLong("golden_count"));
            fb.setLastlogintime(rs.getString("last_login_time"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setNickname(rs.getString("nick_name"));
            fb.setPvpmark(rs.getLong("pvp_mark"));
            fb.setMacAddress(rs.getString("mac_address"));
            fb.setWorkCount(rs.getInt("worker_count"));
            fb.setMaxGoldenCount(rs.getInt("max_golden_count"));
            fb.setMaxElixirCount(rs.getInt("max_elixir_count"));
            fb.setAreaId(rs.getInt("area_id"));
            fb.setLastlogintime(rs.getString("last_login_time"));
            fb.setShieldEndTime(rs.getString("shield_end_time"));
            return fb;
        }
    }

    private class BwUserRowMapperPVPGrade implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserVO fb = new BwUserVO();
            fb.setLevel(rs.getInt("level"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setNickname(rs.getString("nick_name"));
            fb.setPvpmark(rs.getLong("pvp_mark"));
            fb.setAreaId(rs.getInt("area_id"));
            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwUserVO> listtime1) throws CacheDaoException {
        String updateSql = "update bw_user set elixir_count=?,exp=?,golden_count=?,last_login_time=?,level=?,nick_name=?,pvp_mark=?,mac_address=?,worker_count=? ,max_golden_count=?,max_elixir_count=? where mail_address=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwUserVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwUserVO bwuservo = it.next();
                        ps.setInt(col++, (int) bwuservo.getElixircount());
                        ps.setInt(col++, (int) bwuservo.getExp());
                        ps.setInt(col++, (int) bwuservo.getGoldencount());
                        ps.setString(col++, bwuservo.getLastlogintime());
                        ps.setInt(col++, (int) bwuservo.getLevel());
                        ps.setString(col++, bwuservo.getNickname());
                        ps.setInt(col++, (int) bwuservo.getPvpmark());
                        ps.setString(col++, bwuservo.getMacAddress());
                        ps.setInt(col++, (int) bwuservo.getWorkCount());
                        ps.setInt(col++, bwuservo.getMaxGoldenCount());
                        ps.setInt(col++, bwuservo.getMaxElixirCount());
                        ps.setString(col++, bwuservo.getMailaddress());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新用户信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新用户信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

    @Override
    public StopServerMessageVO getServerStatus() throws CacheDaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PVPGradeOrderVO> getPVPGradeOrder(PVPGradeOrderVO pVPGradeOrderVO)
            throws CacheDaoException {
        return null;
    }

    @Override
    public long getPVPGradeOrderByGrade(long pvpgrade) throws CacheDaoException {
        String sql = "select count(pvp_mark) FROM lootesserver.bw_user where pvp_mark>" + pvpgrade;
        long temp = this.getJdbcTemplate().queryForLong(sql);

        return temp;
    }

    @Override
    public void setPVPGradeOrder() throws CacheDaoException {
        // TODO Auto-generated method stub
    }

//	@Override
//	public int getWorkCount(BwUserVO bwuservo) throws CacheDaoException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void updateWorkCount(BwUserVO bwuservo) throws CacheDaoException {
//		// TODO Auto-generated method stub
//		
//	}
}
