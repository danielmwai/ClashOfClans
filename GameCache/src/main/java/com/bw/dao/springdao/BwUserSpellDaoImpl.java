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
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.dao.BwUserSpellDAO;
import com.bw.exception.CacheDaoException;

public class BwUserSpellDaoImpl extends BaseSpringDao implements BwUserSpellDAO {

    private static Logger log = Logger.getLogger(BwUserSpellDaoImpl.class);

    @Override
    public void delete(BwUserSpellVO bwuserspellvo) throws CacheDaoException {
        String sql = "delete from bw_user_spell where id=?";
        Long id = bwuserspellvo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserSpellVO> queryBwUserSpellVO(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {

        String sql = "select id,mail_address,spell_count,spell_level,spell_type_id,spell_upgrade_end_time from bw_user_spell where mail_address=?";
        List<BwUserSpellVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new Object[]{bwuserspellvo.getMailaddress()},
                    new BwUserSpellRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserSpellVO queryBwUserSpellVOById(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {
        String sql = "select mail_address,spell_count,spell_level,spell_type_id,spell_upgrade_end_time from bw_user_spell where mail_address=? and spell_type_id=?";

        List<BwUserSpellVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new Object[]{bwuserspellvo.getMailaddress(), bwuserspellvo.getSpelltypeid()},
                    new BwUserSpellRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }

        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwUserSpellVOCount(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_spell";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> queryBwUserSpellVOIds(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {

        String sql = "select distinct spell_type_id from bw_user_spell where  mail_address=? ";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwuserspellvo.getMailaddress()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<Long>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(new Long(user.get("spell_type_id")
                        .toString()));
            }
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(final BwUserSpellVO bwuserspellvo) throws CacheDaoException {
        //如果有此兵种就不再insert,防止主键冲突     可以在userManagerImpl里判断
        String sql = "select mail_address,spell_count,spell_level,spell_type_id,spell_upgrade_end_time from bw_user_spell where mail_address=? and spell_type_id=?";
        List<BwUserSpellVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new Object[]{bwuserspellvo.getMailaddress(), bwuserspellvo.getSpelltypeid()},
                    new BwUserSpellRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new CacheDaoException(e);
        }
        if (result.size() > 0) {
            return;

        }

        final String insertsql = "insert into bw_user_spell (mail_address,spell_count,spell_level,spell_type_id,spell_upgrade_end_time) values (?,?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, bwuserspellvo.getMailaddress());
                    ps.setInt(2, bwuserspellvo.getSpellcount());
                    ps.setInt(3, bwuserspellvo.getSpelllevel());
                    ps.setInt(4, bwuserspellvo.getSpelltypeid());
                    ps.setString(5, bwuserspellvo.getSpellupgradeendtime());

                    return ps;
                }
            }, holder);
            //bwuserspellvo.setId(holder.getKey().longValue());

        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwUserSpellVO bwuserspellvo) throws CacheDaoException {
        String sql = "update bw_user_spell set spell_count=?,spell_level=?,spell_upgrade_end_time=? where mail_address=? and spell_type_id=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwuserspellvo.getSpellcount(),
                        bwuserspellvo.getSpelllevel(),
                        bwuserspellvo.getSpellupgradeendtime(),
                        bwuserspellvo.getMailaddress(),
                        bwuserspellvo.getSpelltypeid(),});
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }
    }

    private class BwUserSpellRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserSpellVO fb = new BwUserSpellVO();
//			select id,mail_address,spell_count,spell_create_end_time,spell_level,spell_type_id,
//			spell_upgrade_end_time from bw_user_spell where mail_address=? and spell_type_id=?
            //fb.setId(rs.getLong("id"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setSpellcount(rs.getInt("spell_count"));
            fb.setSpelllevel(rs.getInt("spell_level"));
            fb.setSpelltypeid(rs.getInt("spell_type_id"));
            fb.setSpellupgradeendtime(rs.getString("spell_upgrade_end_time"));
            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwUserSpellVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_user_spell set spell_count=?,spell_level=?,spell_type_id=?,spell_upgrade_end_time=? where id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwUserSpellVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwUserSpellVO bwuserspellvo = it.next();
                        ps.setInt(col++, (int) bwuserspellvo.getSpellcount());
                        ps.setInt(col++, bwuserspellvo.getSpelllevel());
                        ps.setInt(col++, bwuserspellvo.getSpelltypeid());
                        ps.setString(col++, bwuserspellvo.getSpellupgradeendtime());
                        ps.setLong(col++, bwuserspellvo.getId());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新用户魔法信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新用户魔法信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
