package com.bw.dao.springdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.bw.cache.vo.CGUserVO;
import com.bw.dao.CGUserDAO;
import com.bw.exception.CacheDaoException;

public class CGUserDaoImpl extends BaseSpringDao implements CGUserDAO {

    /**
     * 用户注册
     */
    @Override
    public void register(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "INSERT INTO cg_user(id, mail_address, local_area, nick_name, image_name, move_password, machine_num, screen_width, screen_height,sex)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        cgUserVO.setId(getId(seq));
        Object[] objects = new Object[10];
        objects[0] = cgUserVO.getId();
        objects[1] = cgUserVO.getMailAddress();
        objects[2] = cgUserVO.getLocalArea();
        objects[3] = cgUserVO.getNickName();
        objects[4] = cgUserVO.getImageName();
        objects[5] = cgUserVO.getMovePassword();
        objects[6] = cgUserVO.getMachineNum();
        objects[7] = cgUserVO.getScreenWidth();
        objects[8] = cgUserVO.getScreenHeight();
        objects[9] = cgUserVO.getSex();
        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 初始化用户信息
     */
    @Override
    public void initUserInfo(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "INSERT INTO cg_user_info( mail_address, exp, levels, golden_count, praises_count, cm_island_data_id) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] objects = new Object[6];
        objects[0] = cgUserVO.getMailAddress();
        objects[1] = cgUserVO.getExp();
        objects[2] = cgUserVO.getLevels();
        objects[3] = cgUserVO.getGoldenCount();
        objects[4] = cgUserVO.getPraisesCount();
        objects[5] = cgUserVO.getCmIslandDataId();

        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 修改用户信息
     */
    @Override
    public void updateUser(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "UPDATE cg_user SET local_area=?, nick_name=?, image_name=?, move_password=?, machine_num=?, screen_width=?, screen_height=?, last_login = now() WHERE id=?";
        Object[] objects = new Object[8];
        objects[0] = cgUserVO.getLocalArea();
        objects[1] = cgUserVO.getNickName();
        objects[2] = cgUserVO.getImageName();
        objects[3] = cgUserVO.getMovePassword();
        objects[4] = cgUserVO.getMachineNum();
        objects[5] = cgUserVO.getScreenWidth();
        objects[6] = cgUserVO.getScreenHeight();
        objects[7] = cgUserVO.getId();
        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 刷新用户信息
     */
    @Override
    public void refreshUserInfo(CGUserVO cgUserVO) {
        String sql = "UPDATE cg_user_info SET exp=?, levels=?, golden_count=?, cm_island_data_id=? WHERE mail_address=?";
        Object[] objects = new Object[5];
        objects[0] = cgUserVO.getExp();
        objects[1] = cgUserVO.getLevels();
        objects[2] = cgUserVO.getGoldenCount();
        objects[3] = cgUserVO.getCmIslandDataId();
        objects[4] = cgUserVO.getMailAddress();

        getJdbcTemplate().update(sql, objects);
    }

    /**
     * 删除用户信息
     */
    @Override
    public void removeUser(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "delete from cg_user where id = ?";
        getJdbcTemplate().update(sql, new Object[]{cgUserVO.getId()});
    }

    /**
     * 删除用户信息
     */
    @Override
    public void removeUserInfo(CGUserVO cgUserVO) {
        String sql = "delete from cg_user_info where mail_address = ?";
        getJdbcTemplate().update(sql, new Object[]{cgUserVO.getMailAddress()});
    }

    /**
     * 根据MailAddress查询用户信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public CGUserVO queryCGUserVOByMailAddress(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "select id, u.mail_address, local_area, nick_name, image_name, cm_island_data_id, last_login, move_password, machine_num, screen_width, screen_height, exp, levels, golden_count, praises_count ,sex from cg_user u,cg_user_info i where u.mail_address = ? and u.mail_address = i.mail_address";
        List<CGUserVO> list = getJdbcTemplate().query(sql,
                new Object[]{cgUserVO.getMailAddress()}, mapper);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 根据条件查询用户信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CGUserVO> queryCGUserVO(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "select id, u.mail_address, local_area, nick_name, image_name, cm_island_data_id, last_login, move_password, machine_num, screen_width, screen_height, exp, levels, golden_count, praises_count,sex from cg_user u,cg_user_info i where u.mail_address = i.mail_address";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        if (cgUserVO.getNickName() != null) {
            sb.append(" and nick_name = ?");
            list.add(cgUserVO.getNickName());
        }
        if (cgUserVO.getLocalArea() != null) {
            sb.append(" and local_area = ?");
            list.add(cgUserVO.getLocalArea());
        }
        if (cgUserVO.getMailAddress() != null) {
            sb.append(" and mail_address = ?");
            list.add(cgUserVO.getMailAddress());
        }
        if (cgUserVO.getMachineNum() != null) {
            sb.append(" and machine_num = ?");
            list.add(cgUserVO.getMachineNum());
        }
        if (sb.length() == sql.length()) {
            return null;
        }
        return getJdbcTemplate().query(sb.toString(), list.toArray(), mapper);
    }

    /**
     * 根据条件统计用户表
     */
    @Override
    public long queryCGUserVOCount(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "select count(1) from cg_user where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        List<Object> list = new ArrayList<Object>();
        if (cgUserVO.getNickName() != null) {
            sb.append(" and nick_name = ?");
            list.add(cgUserVO.getNickName());
        }
        if (cgUserVO.getLocalArea() != null) {
            sb.append(" and local_area = ?");
            list.add(cgUserVO.getLocalArea());
        }
        if (cgUserVO.getMailAddress() != null) {
            sb.append(" and mail_address = ?");
            list.add(cgUserVO.getMailAddress());
        }
        if (cgUserVO.getMachineNum() != null) {
            sb.append(" and machine_num = ?");
            list.add(cgUserVO.getMachineNum());
        }
        return getJdbcTemplate().queryForInt(sb.toString(), list.toArray());
    }

    RowMapper mapper = new RowMapper() {

        @Override
        public CGUserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CGUserVO cgUserVO = new CGUserVO();
            cgUserVO.setId(rs.getLong("id"));
            cgUserVO.setLocalArea(rs.getString("local_area"));
            cgUserVO.setMailAddress(rs.getString("mail_address"));
            cgUserVO.setCmIslandDataId(rs.getInt("cm_island_data_id"));
            cgUserVO.setImageName(rs.getString("image_name"));
            cgUserVO.setLastLogin(rs.getDate("last_login"));
            cgUserVO.setMachineNum(rs.getString("machine_num"));
            cgUserVO.setMovePassword(rs.getString("move_password"));
            cgUserVO.setNickName(rs.getString("nick_name"));
            cgUserVO.setScreenHeight(rs.getInt("screen_height"));
            cgUserVO.setScreenWidth(rs.getInt("screen_width"));
            cgUserVO.setExp(rs.getLong("exp"));
            cgUserVO.setGoldenCount(rs.getInt("golden_count"));
            cgUserVO.setLevels(rs.getInt("levels"));
            cgUserVO.setPraisesCount(rs.getInt("praises_count"));
            cgUserVO.setSex(rs.getInt("sex"));
            return cgUserVO;
        }
    };

    /**
     * 根据ID查询记录
     */
    @SuppressWarnings("unchecked")
    @Override
    public CGUserVO queryCGUserVOById(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "select id, u.mail_address, local_area, nick_name, image_name, cm_island_data_id, last_login, move_password, machine_num, screen_width, screen_height, exp, levels, golden_count, praises_count,sex from cg_user u,cg_user_info i where u.id = ? and u.mail_address = i.mail_address";
        List<CGUserVO> list = getJdbcTemplate().query(sql, new Object[]{cgUserVO.getId()},
                mapper);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 根据昵称查询记录
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> queryCGUserVOByNickName(CGUserVO cgUserVO) throws CacheDaoException {
        String sql = "select mail_address from cg_user where nick_name = ?";

        return getJdbcTemplate().query(sql, new Object[]{cgUserVO.getNickName()},
                new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString(1);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> randomRecommend(String mailAddress) throws CacheDaoException {

        String sql = "select mail_Address from cg_user order by random() limit 50";

        return getJdbcTemplate().query(sql, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int arg1) throws SQLException {
                return rs.getString("mail_Address");
            }
        });
    }

    @Override
    public void refreshPraises(CGUserVO cgUserVO) {

        String sql = "UPDATE cg_user_info SET praises_count = ? where mail_address = ?";

        getJdbcTemplate().update(sql,
                new Object[]{cgUserVO.getPraisesCount(), cgUserVO.getMailAddress()});
    }

}
