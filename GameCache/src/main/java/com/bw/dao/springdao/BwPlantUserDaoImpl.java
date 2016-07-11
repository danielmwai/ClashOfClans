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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwPlantUserVO;
import com.bw.dao.BwPlantUserDAO;
import com.bw.exception.CacheDaoException;

public class BwPlantUserDaoImpl extends BaseSpringDao implements BwPlantUserDAO {

    private static Logger log = Logger.getLogger(BwPlantUserDaoImpl.class);

    @Override
    public void delete(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String sql = "delete from bw_plant_user where bowei_id=?";
        String id = bwplantuservo.getBoweiid();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwPlantUserVO> queryBwPlantUserVO(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {

        String sql = "select bowei_id,mac_address,mail_address,nick_name,pass_word,platform_type from bw_plant_user where ";
        List<BwPlantUserVO> result = null;
        try {
            if (null == bwplantuservo) {
                result = getJdbcTemplate().query(sql,
                        new BwPlantUserRowMapper());
            } else {
                String sql1 = "select bowei_id,mac_address,mail_address,nick_name,pass_word,platform_type from bw_plant_user where 1=1";
                if (!"".equals(bwplantuservo.getBoweiid())) {
                    sql1 += " and id = '" + bwplantuservo.getBoweiid() + "'";
                }
                result = this.getJdbcTemplate().query(sql1,
                        new BwPlantUserRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwPlantUserVO queryBwPlantUserVOById(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {
        String sql = "select bowei_id,mac_address,mail_address,nick_name,pass_word,platform_type from bw_plant_user where bowei_id=?";
        List<BwPlantUserVO> result = null;
        result = this.getJdbcTemplate().query(sql, new Object[]{bwplantuservo.getBoweiid()}, new BwPlantUserRowMapper());
        if (null != result && result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public long queryBwPlantUserVOCount(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_plant_user";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwPlantUserVOIds(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {

        return null;
    }

    @Override
    public void save(final BwPlantUserVO bwplantuservo) throws CacheDaoException {
        final String insertsql = "insert into bw_plant_user (bowei_id,mac_address,mail_address,nick_name,pass_word,platform_type) values (?,?,?,?,?,?)";
        try {

            getJdbcTemplate().update(
                    insertsql,
                    new Object[]{bwplantuservo.getBoweiid(), bwplantuservo.getMacaddress(), bwplantuservo.getMailaddress(),
                        bwplantuservo.getNickname(),
                        bwplantuservo.getPassword(),
                        bwplantuservo.getPlatformtype()
                    });
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
//		 KeyHolder holder = new GeneratedKeyHolder();
//			try {
//				this.getJdbcTemplate().update(new PreparedStatementCreator() {
//					public PreparedStatement createPreparedStatement(Connection con)
//							throws SQLException {
//						PreparedStatement ps = con.prepareStatement(insertsql,
//								Statement.RETURN_GENERATED_KEYS);
//
//						ps.setLong(1, Long.parseLong(bwplantuservo.getBoweiid()));
//						ps.setString(2, bwplantuservo.getMacaddress());
//						ps.setString(3,bwplantuservo.getMailaddress());
//						ps.setString(4, bwplantuservo.getNickname());
//						ps.setString(5, bwplantuservo.getPassword());
//						ps.setInt(6, bwplantuservo.getPlatformtype());
//						return ps;
//					}
//				}, holder);
//				bwplantuservo.setBoweiid(String.valueOf(holder.getKey().longValue()));
//			} catch (InvalidDataAccessApiUsageException e) {
//				e.printStackTrace();
//				throw new CacheDaoException(e);
//			} catch (DataAccessException e) {
//				
//			}
    }

    @Override
    public void update(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String sql = "update bw_plant_user set mail_address=?,nick_name=?,pass_word=?,platform_type=? where bowei_id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{bwplantuservo.getMailaddress(),
                        bwplantuservo.getMacaddress(),
                        bwplantuservo.getNickname(),
                        bwplantuservo.getPassword(),
                        bwplantuservo.getPlatformtype(),
                        bwplantuservo.getBoweiid(),});
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwPlantUserRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwPlantUserVO fb = new BwPlantUserVO();
            fb.setBoweiid(String.valueOf(rs.getLong("bowei_id")));
            fb.setMacaddress(rs.getString("mac_address"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setNickname(rs.getString("nick_name"));
            fb.setPassword(rs.getString("pass_word"));
            fb.setPlatformtype(rs.getInt("platform_type"));
            return fb;
        }
    }

    @Override
    public String queryBwPlantUserVOByMailAddress(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String sql = "select bowei_id from bw_plant_user where mail_address=?";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwplantuservo.getMailaddress()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<String>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(user.get("bowei_id").toString());
            }
            return resultList.get(0).toString();
        }
        return null;
    }

}
