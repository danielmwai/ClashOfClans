package com.bw.dao.springdao;

import com.bw.dao.*;
import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;
import org.springframework.jdbc.core.*;
import java.sql.*;

public class CGUserCropInfoDAOImpl extends BaseSpringDao implements CGUserCropInfoDAO {

    @Override
    public void batchSave(final String mailAddress, final List<CGUserCropInfoVO> list) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_crop_info(mail_address, crop_id, crop_status, building_uuid) VALUES (?, ?, ?, ?)";
        this.getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int index) throws SQLException {
                final CGUserCropInfoVO vo = list.get(index);
                ps.setString(1, mailAddress);
                ps.setInt(2, vo.getCorpId());
                ps.setInt(3, vo.getCorpStatus());
                ps.setLong(4, vo.getBuildingUuid());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    public void removeByMailAddress(final String mailAddress) throws CityCacheDaoException {
        final String sql = "delete from cg_user_crop_info where mail_address = ?";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }

    @Override
    public List<CGUserCropInfoVO> queryInfoByMail(final String mailAddress) throws CityCacheDaoException {
        final String sql = "SELECT crop_id, crop_status, building_uuid FROM cg_user_crop_info where mail_address = ?";
        return (List<CGUserCropInfoVO>) this.getJdbcTemplate().query(sql, new Object[]{mailAddress}, new RowMapper() {
            @Override
            public CGUserCropInfoVO mapRow(final ResultSet rs, final int arg1) throws SQLException {
                final CGUserCropInfoVO vo = new CGUserCropInfoVO();
                vo.setCorpId(rs.getInt(1));
                vo.setCorpStatus(rs.getInt(2));
                vo.setBuildingUuid(rs.getLong(3));
                return vo;
            }
        });
    }
}
