package com.bw.dao.springdao;

import com.bw.dao.*;
import com.bw.exception.*;

public class CGUserCheatDAOImpl extends BaseSpringDao implements CGUserCheatDAO {

    @Override
    public void save(final String mailAddress, final int cheatType) throws CityCacheDaoException {
        final String sql = "INSERT INTO cg_user_cheat(mail_address, error_type) VALUES (?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{mailAddress, cheatType});
    }
}
