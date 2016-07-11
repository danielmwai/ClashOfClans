package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.CGUserCurrencyVO;
import com.bw.dao.CGUserCurrencyDAO;
import com.bw.exception.CacheDaoException;

public class CGUserCurrencyDaoImpl extends BaseSpringDao implements CGUserCurrencyDAO {

    /**
     * 添加用户代币数量信息
     */
    @Override
    public void save(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {
        String sql = "INSERT INTO cg_user_currency (id, mail_address, token_coin, update_time) VALUES (?, ?, ?, ?)";

        cgUserCurrencyVO.setId(getId(seq));
        Object[] objects = new Object[4];
        objects[0] = cgUserCurrencyVO.getId();
        objects[1] = cgUserCurrencyVO.getMailAddress();
        objects[2] = cgUserCurrencyVO.getTokenCoin();
        objects[3] = cgUserCurrencyVO.getUpdateTime();
        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 更新用户代币数量
     */
    @Override
    public void update(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {
        String sql = "UPDATE cg_user_currency SET token_coin=?, update_time=? where mail_address = ?";
        Object[] objects = new Object[3];
        objects[0] = cgUserCurrencyVO.getTokenCoin();
        objects[1] = cgUserCurrencyVO.getUpdateTime();
        objects[2] = cgUserCurrencyVO.getMailAddress();
        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 删除用户代币数量信息
     */
    @Override
    public void delete(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {
        String sql = "delete from cg_user_currency where mail_address=?";
        getJdbcTemplate().update(sql, new Object[]{cgUserCurrencyVO.getMailAddress()});
    }

    /**
     * 根据ID查询用户代币数量信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public CGUserCurrencyVO queryCGUserCurrencyVOById(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        String sql = "select id, mail_address, token_coin, update_time from cg_user_currency where mail_address=?";
        List<CGUserCurrencyVO> list = getJdbcTemplate().query(sql,
                new Object[]{cgUserCurrencyVO.getMailAddress()}, mapper);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 查询用户代币数量信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CGUserCurrencyVO> queryCGUserCurrencyVO(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        String sql = "select id, mail_address, token_coin, update_time from cg_user_currency where mail_address=? ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        if (cgUserCurrencyVO.getTokenCoin() != 0) {
            sb.append(" and token_coin = ?");
            list.add(cgUserCurrencyVO.getTokenCoin());
        }
        if (cgUserCurrencyVO.getUpdateTime() != null) {
            sb.append(" and update_time = ?");
            list.add(cgUserCurrencyVO.getUpdateTime());
        }
        return getJdbcTemplate().query(sb.toString(), list.toArray(), mapper);
    }

    @Override
    public List<Long> queryCGUserCurrencyVOIds(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        return null;
    }

    /**
     * 统计用户代币表
     */
    @Override
    public long queryCGUserCurrencyVOCount(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        String sql = "select count(1) from cg_user_currency where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        if (cgUserCurrencyVO.getMailAddress() != null) {
            sb.append(" and mail_address = ?");
            list.add(cgUserCurrencyVO.getMailAddress());
        }
        if (cgUserCurrencyVO.getTokenCoin() != 0) {
            sb.append(" and token_coin = ?");
            list.add(cgUserCurrencyVO.getTokenCoin());
        }
        if (cgUserCurrencyVO.getUpdateTime() != null) {
            sb.append(" and update_time = ?");
            list.add(cgUserCurrencyVO.getUpdateTime());
        }
        if (cgUserCurrencyVO.getId() != 0L) {
            sb.append(" and id=?");
            list.add(cgUserCurrencyVO.getId());
        }
        return getJdbcTemplate().queryForInt(sb.toString(), list.toArray());
    }

    RowMapper mapper = new RowMapper() {

        @Override
        public CGUserCurrencyVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CGUserCurrencyVO cgUserCurrencyVO = new CGUserCurrencyVO();
            cgUserCurrencyVO.setId(rs.getLong("id"));
            cgUserCurrencyVO.setMailAddress(rs.getString("mail_address"));
            cgUserCurrencyVO.setTokenCoin(rs.getInt("token_coin"));
            cgUserCurrencyVO.setUpdateTime(rs.getDate("update_time"));
            return cgUserCurrencyVO;
        }
    };

}
