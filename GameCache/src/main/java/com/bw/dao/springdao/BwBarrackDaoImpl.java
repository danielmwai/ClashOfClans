package com.bw.dao.springdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.dao.BwBarrackDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 兵营DAO
 */
public class BwBarrackDaoImpl extends BaseSpringDao implements BwBarrackDAO {

    private static Logger log = Logger.getLogger(BwBarrackDaoImpl.class);

    @Override
    public void delete(BwBarrackVO bwbarrackvo) throws CacheDaoException {
        String sql = "delete from bw_barrack where id=?";
        Long id = bwbarrackvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwBarrackVO> queryBwBarrackVO(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String sql = "";
        List<BwBarrackVO> result = null;
        if (bwbarrackvo.getUsermapdataid() == 0) {
            sql = "select id,mail_address,user_map_data_id,user_character_id,end_time,produce_count,start_time,produce_status,indexss from bw_barrack where mail_address=? and produce_count>0";
            try {
                result = getJdbcTemplate().query(sql, new Object[]{bwbarrackvo.getMailAddress()}, new BwBarrackRowMapper());
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new CacheDaoException(e);

            }
        } else {
            sql = "select id,mail_address,user_map_data_id,user_character_id,end_time,produce_count,start_time,produce_status,indexss from bw_barrack where mail_address=? and user_map_data_id=? and produce_count>0";
            try {
                result = getJdbcTemplate().query(sql, new Object[]{bwbarrackvo.getMailAddress(), bwbarrackvo.getUsermapdataid()}, new BwBarrackRowMapper());
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new CacheDaoException(e);
            }
        }

        return result.size() <= 0 ? null : result;
//		String sql = "select id,mail_address,user_map_data_id,user_character_id,end_time,produce_count,start_time,produce_status from bw_barrack where mail_address=? ";
//		List<BwBarrackVO> result = null;
//		try {
//				result = getJdbcTemplate().query(sql,new Object[]{bwbarrackvo.getMailAddress()},new BwBarrackRowMapper());
//		} catch (Exception e) {
//		}
//		return result.size()<=0?null:result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwBarrackVO queryBwBarrackVOById(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        List<BwBarrackVO> result = null;
        String sql = "select id,user_map_data_id,end_time,produce_count,start_time,produce_status,mail_address,user_character_id,indexss  from bw_barrack where mail_address=? and user_character_id=? and user_map_data_id=?";

        try {
            result = getJdbcTemplate().query(sql, new Object[]{bwbarrackvo.getMailAddress(), bwbarrackvo.getUsercharacterid(), bwbarrackvo.getUsermapdataid()}, new BwBarrackRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwBarrackVOCount(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_barrack";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> queryBwBarrackVOIds(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String sql = "select distinct user_map_data_id from bw_barrack where  mail_address=? ";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwbarrackvo.getMailAddress()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<Long>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(new Long(user.get("user_map_data_id")
                        .toString()));
            }
        }
        return resultList;
    }

    @Override
    public void save(final BwBarrackVO bwbarrackvo) throws CacheDaoException {

        final String insertsql = "insert into bw_barrack (user_map_data_id,user_character_id,end_time,produce_count,start_time,produce_status,mail_address,indexss) values (?,?,?,?,?,?,?,?)";
        // final String sql =
        // "insert into bw_barrack_lib (character_id,character_level,delete_flag,end_time,produce_count,type,user_building_data_id) values (?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, bwbarrackvo.getUsermapdataid());
                    ps.setLong(2, bwbarrackvo.getUsercharacterid());
                    ps.setString(3, bwbarrackvo.getEndtime());
                    ps.setInt(4, bwbarrackvo.getProducecount());
                    ps.setString(5, bwbarrackvo.getStartTime());
                    ps.setInt(6, bwbarrackvo.getProduceStatus());
                    ps.setString(7, bwbarrackvo.getMailAddress());
                    ps.setInt(8, bwbarrackvo.getIndex());
                    return ps;
                }
            }, holder);
            bwbarrackvo.setId(holder.getKey().longValue());
            System.out.println("@@@@@@@@@@@@@@@:value:"
                    + holder.getKey().longValue());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void update(BwBarrackVO bwbarrackvo) throws CacheDaoException {
        String sql = "update bw_barrack set user_map_data_id=?,end_time=?,produce_count=?,start_time=?,produce_status=? where mail_address=? and user_character_id=? and user_map_data_id=?";
        try {
            this.getJdbcTemplate()
                    .update(
                            sql,
                            new Object[]{bwbarrackvo.getUsermapdataid(),
                                bwbarrackvo.getEndtime(),
                                bwbarrackvo.getProducecount(),
                                bwbarrackvo.getStartTime(),
                                bwbarrackvo.getProduceStatus(),
                                bwbarrackvo.getMailAddress(),
                                bwbarrackvo.getUsercharacterid(),
                                bwbarrackvo.getUsermapdataid()
                            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }
    }

    private class BwBarrackRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwBarrackVO fb = new BwBarrackVO();
//			select id,end_time,produce_count,user_character_id,user_map_data_id,start_time,
//			produce_status,mail_address from bw_barrack where mail_address=?

            fb.setId(rs.getLong("id"));
            fb.setEndtime(rs.getString("end_time"));
            fb.setProducecount(rs.getInt("produce_count"));
            fb.setUsercharacterid(rs.getLong("user_character_id"));
            fb.setUsermapdataid(rs.getLong("user_map_data_id"));
            fb.setStartTime(rs.getString("start_time"));
            fb.setProduceStatus(rs.getInt("produce_status"));
            fb.setMailAddress(rs.getString("mail_address"));
            fb.setIndex(rs.getInt("indexss"));
            return fb;
        }
    }

    @Override
    public List<Long> getAllBarrackUserMapId(String mailAddress)
            throws CacheDaoException {
        //this.queryBwBarrackVOById(bwbarrackvo);
        return null;
    }

    @Override
    public void putAllBarrackUserMapId(String mailAddress,
            List<Long> userMapIdList) throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void batchUpdate(final List<BwBarrackVO> bwbarrackvolist)
            throws CacheDaoException {
        String sql = "update bw_barrack set end_time=?,produce_count=?,start_time=?,produce_status=? where mail_address=? and user_character_id=? and user_map_data_id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
                Iterator<BwBarrackVO> it = bwbarrackvolist.iterator();

                public int getBatchSize() {
                    return bwbarrackvolist.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwBarrackVO bwbarrackvo = it.next();
                        ps.setString(col++, bwbarrackvo.getEndtime());
                        ps.setInt(col++, bwbarrackvo.getProducecount());
                        ps.setString(col++, bwbarrackvo.getStartTime());
                        ps.setInt(col++, bwbarrackvo.getProduceStatus());
                        ps.setString(col++, bwbarrackvo.getMailAddress());
                        ps.setLong(col++, bwbarrackvo.getUsercharacterid());
                        ps.setLong(col++, bwbarrackvo.getUsermapdataid());
                    }
                }
            });
//			for(int x=0;x<result.length;x++){
//				if(result[x]==0){
//					//没有进行更新，需要插入数据库
//					this.save(bwbarrackvolist.get(x));
//					log.info("更新战斗信息变插入战斗信息.....");
//				}
//			}
        } catch (Exception e) {
            log.error("" + e);
            throw new CacheDaoException(e);
        }

    }

    @Override
    public List<Long> queryBwBarrackVOForCharactarId(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String sql = "select  user_character_id from bw_barrack where  mail_address=? and user_map_data_id=?";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwbarrackvo.getMailAddress(), bwbarrackvo.getUsermapdataid()});
        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<Long>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(new Long(user.get("user_character_id")
                        .toString()));
            }
        }
        return resultList;
    }

}
