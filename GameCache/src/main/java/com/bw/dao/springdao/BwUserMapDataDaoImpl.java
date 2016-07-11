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

import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.exception.CacheDaoException;

public class BwUserMapDataDaoImpl extends BaseSpringDao implements
        BwUserMapDataDAO {

    private static Logger log = Logger.getLogger(BwUserMapDataDaoImpl.class);

    @Override
    public void delete(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {
        String sql = "delete from bw_user_map_data where mail_address=? and uniqueness_build_id=? and build_id=?";

        try {
            this.getJdbcTemplate().update(sql, new Object[]{bwusermapdatavo.getMailaddress(), bwusermapdatavo.getUniquenessbuildid(), bwusermapdatavo.getBuildid()});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserMapDataVO> queryBwUserMapDataVO(
            BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {

        String sql = "select id,build_id,build_level,mail_address,map_index_x,map_index_y,status,uniqueness_build_id,upgrade_finish_time from bw_user_map_data";
        List<BwUserMapDataVO> result = null;
        try {
            if (null == bwusermapdatavo) {
                result = getJdbcTemplate().query(sql,
                        new BwUserMapDataRowMapper());
            } else {
                String sql1 = "select id,build_id,build_level,mail_address,map_index_x,map_index_y,status,uniqueness_build_id,upgrade_finish_time from bw_user_map_data where 1=1";
                if (!"".equals(bwusermapdatavo.getId())) {
                    sql1 += " and id = '" + bwusermapdatavo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwUserMapDataRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserMapDataVO queryBwUserMapDataVOById(
            BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {
        String sql = "select id,build_id,build_level,mail_address,map_index_x,status,uniqueness_build_id,upgrade_finish_time,map_index_y from bw_user_map_data where mail_address=? and uniqueness_build_id=? and build_id=?";
        System.out.println("Sql Contains  " + sql);
        List<BwUserMapDataVO> result = null;
        try {
            result = getJdbcTemplate().query(
                    sql,
                    new Object[]{bwusermapdatavo.getMailaddress(),
                        bwusermapdatavo.getUniquenessbuildid(),
                        bwusermapdatavo.getBuildid()
                    },
                    new BwUserMapDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            throw new CacheDaoException();
        }
        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwUserMapDataVOCount(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_map_data";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> queryBwUserMapDataVOIds(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {

        String sql = "select  uniqueness_build_id from bw_user_map_data where mail_address=? and build_id=?";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwusermapdatavo.getMailaddress(), bwusermapdatavo.getBuildid()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<Long>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(new Long(user.get("uniqueness_build_id")
                        .toString()));
            }
        }

        return resultList;
    }
//

    @Override
    public List<BwUserMapDataVO> queryBwUserMapDataVOIdsForUUID(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {

        String sql = "select uniqueness_build_id,build_id from bw_user_map_data where mail_address=?";
//		List resultList = null;
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwusermapdatavo.getMailaddress()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<BwUserMapDataVO>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                BwUserMapDataVO bm = new BwUserMapDataVO();
                bm.setUniquenessbuildid(new Long(user.get("uniqueness_build_id")
                        .toString()));
                bm.setBuildid(new Integer(user.get("build_id")
                        .toString()));
                resultList.add(bm);
//				resultList.add(new Long(user.get("uniqueness_build_id")
//						.toString()));
            }
        }

        return resultList;
    }

    @Override
    public void save(final BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {
        final String insertsql = "insert into bw_user_map_data (build_id,build_level,mail_address,map_index_x,map_index_y,status,uniqueness_build_id,upgrade_finish_time) values (?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql,
                            Statement.RETURN_GENERATED_KEYS);

                    ps.setInt(1, bwusermapdatavo.getBuildid());
                    ps.setInt(2, bwusermapdatavo.getBuildlevel());
                    ps.setString(3, bwusermapdatavo.getMailaddress());
                    ps.setInt(4, bwusermapdatavo.getMapindexx());
                    ps.setInt(5, bwusermapdatavo.getMapindexy());
                    ps.setInt(6, bwusermapdatavo.getStatus());
                    ps.setLong(7, bwusermapdatavo.getUniquenessbuildid());
                    ps.setString(8, bwusermapdatavo.getUpgradefinishtime());
                    return ps;
                }
            }, holder);
            bwusermapdatavo.setId(holder.getKey().longValue());
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (DataAccessException e) {

        }

    }

    @Override
    public void update(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {
        String sql = "update bw_user_map_data set build_level=?,map_index_x=?,map_index_y=?,upgrade_finish_time=?,status=?,delete_flag=? where  mail_address=? and build_id=? and uniqueness_build_id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwusermapdatavo.getBuildlevel(),
                        bwusermapdatavo.getMapindexx(),
                        bwusermapdatavo.getMapindexy(),
                        bwusermapdatavo.getUpgradefinishtime(),
                        bwusermapdatavo.getStatus(),
                        bwusermapdatavo.getDeleteFlag(),
                        bwusermapdatavo.getMailaddress(),
                        bwusermapdatavo.getBuildid(),
                        bwusermapdatavo.getUniquenessbuildid(),});
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwUserMapDataRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserMapDataVO fb = new BwUserMapDataVO();
            // select
            // id,build_id,build_level,mail_address,map_index,status,uniqueness_build_id,upgrade_finish_time
            fb.setId(rs.getLong("id"));
            fb.setBuildid(rs.getInt("build_id"));
            fb.setBuildlevel(rs.getInt("build_level"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setMapindexx(rs.getInt("map_index_x"));
            fb.setStatus(rs.getInt("status"));
            fb.setUniquenessbuildid(rs.getLong("uniqueness_build_id"));
            fb.setUpgradefinishtime(rs.getString("upgrade_finish_time"));
            fb.setMapindexy(rs.getInt("map_index_y"));
            return fb;
        }
    }

    @Override
    public int queryUserBuildCount(String mailAddress, int buildId)
            throws CacheDaoException {
        String sql = "select count(uniqueness_build_id) from bw_user_map_data where mail_address=? and build_id=?";
        int count = this.getJdbcTemplate().queryForInt(sql, new Object[]{mailAddress, buildId});
        return count;
    }

    @Override
    public void updateUserBuildCount(String mailAddress, int buildId,
            int buildCount) throws CacheDaoException {
        //String udpateSql="update bw_user_map_data set build";

    }

    @Override
    public void batchUpdate(final List<BwUserMapDataVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_user_map_data set build_level=?,map_index_x=?,map_index_y=?,upgrade_finish_time=?,status=?,delete_flag=? where  mail_address=? and build_id=? and uniqueness_build_id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwUserMapDataVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwUserMapDataVO bwusermapdatavo = it.next();
                        ps.setInt(col++, (int) bwusermapdatavo.getBuildlevel());
                        ps.setInt(col++, bwusermapdatavo.getMapindexx());
                        ps.setInt(col++, bwusermapdatavo.getMapindexy());
                        ps.setString(col++, bwusermapdatavo.getUpgradefinishtime());
                        ps.setInt(col++, (int) bwusermapdatavo.getStatus());
                        ps.setInt(col++, bwusermapdatavo.getDeleteFlag());
                        ps.setString(col++, bwusermapdatavo.getMailaddress());
                        ps.setInt(col++, bwusermapdatavo.getBuildid());
                        ps.setInt(col++, (int) bwusermapdatavo.getUniquenessbuildid());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新用户地图信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新用户地图信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
