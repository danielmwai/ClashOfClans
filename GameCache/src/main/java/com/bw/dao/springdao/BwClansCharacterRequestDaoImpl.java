package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.BwClansCharacterRequestVO;
import com.bw.dao.BwClansCharacterRequestDAO;
import com.bw.exception.CacheDaoException;

public class BwClansCharacterRequestDaoImpl extends BaseSpringDao implements BwClansCharacterRequestDAO {

    private static Logger log = Logger.getLogger(BwClansCharacterRequestDaoImpl.class);

    @Override
    public void delete(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {
        String sql = "delete from bw_clans_character_request where id=?";
        Long id = bwclanscharacterrequestvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @Override
    public List<BwClansCharacterRequestVO> queryBwClansCharacterRequestVO(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {

        String sql = "select id,clans_id,produce_count,reply_mail_address,request_mail_address,request_time,user_character_id from bw_clans_character_request";
        List<BwClansCharacterRequestVO> result = null;
        try {
            if (null == bwclanscharacterrequestvo) {
                result = getJdbcTemplate().query(sql, new BwClansCharacterRequestRowMapper());
            } else {
                String sql1 = "select id,clans_id,produce_count,reply_mail_address,request_mail_address,request_time,user_character_id from bw_clans_character_request where 1=1";
                if (!"".equals(bwclanscharacterrequestvo.getId())) {
                    sql1 += " and id = '" + bwclanscharacterrequestvo.getId() + "'";
                }
                result = this.getJdbcTemplate().query(sql1, new BwClansCharacterRequestRowMapper());

            }
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public BwClansCharacterRequestVO queryBwClansCharacterRequestVOById(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryBwClansCharacterRequestVOCount(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {
        String sql = "select count(*) from bw_clans_character_request";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @Override
    public List<Long> queryBwClansCharacterRequestVOIds(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {

        return null;
    }

    @Override
    public void save(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {
        String insertsql = "insert into bw_clans_character_request (id,clans_id,produce_count,reply_mail_address,request_mail_address,request_time,user_character_id) values (?,?,?,?,?,?,?)";
        try {
            long id = this.getId(seq);
            getJdbcTemplate().update(insertsql, new Object[]{id,
                bwclanscharacterrequestvo.getProducecount(),
                bwclanscharacterrequestvo.getUsercharacterid(),
                bwclanscharacterrequestvo.getClansid(),
                bwclanscharacterrequestvo.getReplymailaddress(),
                bwclanscharacterrequestvo.getRequestmailaddress(),
                bwclanscharacterrequestvo.getRequesttime(),});
        } catch (DataAccessException e) {

            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwClansCharacterRequestVO bwclanscharacterrequestvo) throws CacheDaoException {
        String sql = "update bw_clans_character_request set clans_id=?,produce_count=?,reply_mail_address=?,request_mail_address=?,request_time=?,user_character_id=? where id=?";
        try {
            this.getJdbcTemplate().update(sql, new Object[]{
                bwclanscharacterrequestvo.getProducecount(),
                bwclanscharacterrequestvo.getUsercharacterid(),
                bwclanscharacterrequestvo.getClansid(),
                bwclanscharacterrequestvo.getReplymailaddress(),
                bwclanscharacterrequestvo.getRequestmailaddress(),
                bwclanscharacterrequestvo.getRequesttime(),
                bwclanscharacterrequestvo.getId()
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private class BwClansCharacterRequestRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwClansCharacterRequestVO fb = new BwClansCharacterRequestVO();

            fb.setId(rs.getLong("id"));
            fb.setUsercharacterid(rs.getLong("user_character_id"));
            fb.setClansid(rs.getLong("clans_id"));
            fb.setReplymailaddress(rs.getString("reply_mail_address"));
            fb.setRequestmailaddress(rs.getLong("request_mail_address"));
            fb.setRequesttime(rs.getString("request_time"));
            return fb;
        }
    }

}
