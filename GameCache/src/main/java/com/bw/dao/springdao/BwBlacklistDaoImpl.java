package com.bw.dao.springdao;

import com.bw.cache.vo.BwBlacklistVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.dao.BwBlacklistDAO;
import com.bw.exception.CacheDaoException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator 黑名单基础操作
 */
public class BwBlacklistDaoImpl extends BaseSpringDao implements BwBlacklistDAO {

    @Override
    public long insert(final BwBlacklistVO blacklist) throws CacheDaoException {
        KeyHolder kh = new GeneratedKeyHolder();
        try {
            getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, blacklist.getBoweiId());
                    ps.setString(2, blacklist.getOperator());
                    ps.setTimestamp(3, new Timestamp(blacklist.getStartTime().getTime()));
                    ps.setTimestamp(4, new Timestamp(blacklist.getEndTime().getTime()));
                    ps.setString(5, blacklist.getReason());
                    return ps;
                }
            }, kh);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return kh.getKey().longValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwBlacklistVO select(String boweiId) throws CacheDaoException {
        try {
            List<BwBlacklistVO> result = getJdbcTemplate().query(SQL_SELECT, new Object[]{boweiId}, new BwBlacklistVORowMapper());
            if (null != result && result.size() > 0) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwBlacklistVO> selectAll() throws CacheDaoException {
        // return getJdbcTemplate().query(SQL_SELECTALL, ROW_MAPPER_BLACKLIST);
        return null;
    }

    @Override
    public void update(BwBlacklistVO blacklist) throws CacheDaoException {
        try {
            getJdbcTemplate().update(SQL_UPDATE, blacklist.getBoweiId(), blacklist.getOperator(), blacklist.getStartTime(), blacklist.getEndTime(), blacklist.getReason(), blacklist.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
    }

    @Override
    public void delete(final String boweiId) throws CacheDaoException {
        try {
            getJdbcTemplate().execute(SQL_DELETE + boweiId);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }

    }

    private class BwBlacklistVORowMapper implements RowMapper {

        @Override
        public BwBlacklistVO mapRow(ResultSet rs, int idx) throws SQLException {
            BwBlacklistVO vo = new BwBlacklistVO();
            vo.setId(rs.getLong("id"));
            vo.setBoweiId(rs.getString("bowei_id"));
            vo.setOperator(rs.getString("operator"));
            vo.setStartTime(rs.getTimestamp("start_time"));
            vo.setEndTime(rs.getTimestamp("end_time"));
            vo.setReason(rs.getString("reason"));
            return vo;
        }
    };

    private static final String SQL_UPDATE = "UPDATE bw_blacklist SET bowei_id= ?,operator=?,start_time=?,end_time=?,reason=? WHERE id=?";
    //private static final String SQL_SELECTALL = "SELECT id, bowei_id, operator, start_time, end_time, reason FROM bw_blacklist";
    private static final String SQL_INSERT = "insert into bw_blacklist(bowei_id, operator, start_time, end_time, reason) values(?,?,?,?,?)";
    private static final String SQL_SELECT = "select id, bowei_id, operator, start_time, end_time, reason from bw_blacklist where bowei_id = ?";
    private static final String SQL_DELETE = "delete from bw_blacklist where bowei_id = ";
    private static final String SQL_ALL_BOWEI_ID = "select bowei_id from bw_blacklist ";

    @SuppressWarnings("unchecked")
    @Override
    public List<String> queryBoweiIdList() throws CacheDaoException {
        List resultList = null;

        try {
            resultList = this.getJdbcTemplate().queryForList(SQL_ALL_BOWEI_ID);

            if (null != resultList && resultList.size() > 0) {
                Iterator ite = resultList.iterator();
                resultList = new ArrayList<String>();
                while (ite.hasNext()) {
                    Map user = (Map) ite.next();
                    resultList.add(user.get("bowei_id").toString());
                }
            }
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CacheDaoException(e);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        return resultList;
    }
}
