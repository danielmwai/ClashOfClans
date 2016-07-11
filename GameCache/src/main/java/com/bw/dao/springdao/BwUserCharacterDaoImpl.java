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

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.dao.BwUserCharacterDAO;
import com.bw.exception.CacheDaoException;

public class BwUserCharacterDaoImpl extends BaseSpringDao implements
        BwUserCharacterDAO {

    private static Logger log = Logger.getLogger(BwUserCharacterDaoImpl.class);

    @Override
    public void delete(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
        String sql = "delete from bw_user_character where id=?";
        Long id = bwusercharactervo.getId();
        try {
            this.getJdbcTemplate().update(sql, new Object[]{id});
        } catch (DataAccessException e) {
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserCharacterVO> queryBwUserCharacterVO(
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {

        String sql = "select id,mail_address,chararchter_id,character_level,upgrade_character_finish_time,character_count from bw_user_character where mail_address=? ";
        List<BwUserCharacterVO> result = null;
        try {
            result = this.getJdbcTemplate().query(sql, new Object[]{bwusercharactervo.getMailaddress()},
                    new BwUserCharacterRowMapper());

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public BwUserCharacterVO queryBwUserCharacterVOById(
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {

        String sql = "select id,character_count,character_level,chararchter_id,mail_address,upgrade_character_finish_time from bw_user_character where mail_address=? and chararchter_id=?";
        List<BwUserCharacterVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new Object[]{bwusercharactervo.getMailaddress(), bwusercharactervo.getChararchterid()},
                    new BwUserCharacterRowMapper());
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

        return result.size() <= 0 ? null : result.get(0);
    }

    @Override
    public long queryBwUserCharacterVOCount(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
        String sql = "select count(*) from bw_user_character";
        return this.getJdbcTemplate().queryForLong(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> queryBwUserCharacterVOIds(//此方法没有对应的索引索引不要用
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {
        String sql = "select chararchter_id from bw_user_character where mail_address=?";
        List resultList = null;
        resultList = this.getJdbcTemplate().queryForList(sql,
                new Object[]{bwusercharactervo.getMailaddress()});

        if (null != resultList && resultList.size() > 0) {
            Iterator ite = resultList.iterator();
            resultList = new ArrayList<Long>();
            while (ite.hasNext()) {
                Map user = (Map) ite.next();
                resultList.add(new Long(user.get("chararchter_id")
                        .toString()));
            }
        }
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(final BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
        //如果有此兵种就不再insert,防止主键冲突     可以在userManagerImpl里判断
        String sql = "select id,character_count,character_level,chararchter_id,mail_address,upgrade_character_finish_time from bw_user_character where mail_address=? and chararchter_id=?";
        List<BwUserCharacterVO> result = null;
        try {
            result = getJdbcTemplate().query(sql,
                    new Object[]{bwusercharactervo.getMailaddress(), bwusercharactervo.getChararchterid()},
                    new BwUserCharacterRowMapper());
        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }
        if (result.size() > 0) {
            return;
        }

        final String insertsql = "insert into bw_user_character (mail_address,chararchter_id,character_level,upgrade_character_finish_time,character_count) values (?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        try {
            this.getJdbcTemplate().update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(insertsql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, bwusercharactervo.getMailaddress());
                    ps.setInt(2, bwusercharactervo.getChararchterid());
                    ps.setInt(3, bwusercharactervo.getCharacterlevel());
                    ps.setString(4, bwusercharactervo.getUpgradecharacterfinishtime());
                    ps.setInt(5, bwusercharactervo.getCharactercount());
                    return ps;
                }
            }, holder);
            bwusercharactervo.setId(holder.getKey().longValue());

        } catch (DataAccessException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException(e);
        }

    }

    @Override
    public void update(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
        String sql = "update bw_user_character set character_count=?,character_level=?,upgrade_character_finish_time=? where chararchter_id=? and mail_address=?";
        try {
            this.getJdbcTemplate().update(
                    sql,
                    new Object[]{
                        bwusercharactervo.getCharactercount(),
                        bwusercharactervo.getCharacterlevel(),
                        bwusercharactervo.getUpgradecharacterfinishtime(),
                        bwusercharactervo.getChararchterid(),
                        bwusercharactervo.getMailaddress()
                    });
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CacheDaoException();
        }
    }

    private class BwUserCharacterRowMapper implements RowMapper {

        public Object mapRow(ResultSet rs, int i) throws SQLException {
            BwUserCharacterVO fb = new BwUserCharacterVO();
            fb.setId(rs.getLong("id"));
            fb.setMailaddress(rs.getString("mail_address"));
            fb.setChararchterid(rs.getInt("chararchter_id"));
            fb.setCharacterlevel(rs.getInt("character_level"));
            fb.setUpgradecharacterfinishtime(rs.getString("upgrade_character_finish_time"));
            fb.setCharactercount(rs.getInt("character_count"));

            return fb;
        }
    }

    @Override
    public void batchUpdate(final List<BwUserCharacterVO> listtime1)
            throws CacheDaoException {
        String updateSql = "update bw_user_character set character_count=?,character_level=?,chararchter_id=?,upgrade_character_finish_time=? where id=?";
        try {
            int result[] = getJdbcTemplate().batchUpdate(updateSql, new BatchPreparedStatementSetter() {
                Iterator<BwUserCharacterVO> it = listtime1.iterator();

                public int getBatchSize() {
                    return listtime1.size();
                }

                public void setValues(PreparedStatement ps, int index) throws SQLException {
                    if (it.hasNext()) {
                        int col = 1;
                        BwUserCharacterVO bwusercharactervo = it.next();
                        ps.setInt(col++, bwusercharactervo.getCharactercount());
                        ps.setInt(col++, (int) bwusercharactervo.getCharacterlevel());
                        ps.setInt(col++, (int) bwusercharactervo.getChararchterid());
                        ps.setString(col++, bwusercharactervo.getUpgradecharacterfinishtime());
                        ps.setLong(col++, bwusercharactervo.getId());
                    }
                }
            });
            for (int x = 0; x < result.length; x++) {
                if (result[x] == 0) {
                    //没有进行更新，需要插入数据库
                    this.save(listtime1.get(x));
                    log.info("更新用户兵力表信息变插入信息.....");
                }
            }
        } catch (Exception e) {
            log.error("更新用户兵力表信息发生异常" + e);
            throw new CacheDaoException(e);
        }

    }

}
