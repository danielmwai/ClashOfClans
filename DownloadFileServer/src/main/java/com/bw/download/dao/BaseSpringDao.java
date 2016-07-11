package com.bw.download.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @creator:赵清有 2010-7-19下午06:26:59 dao层 数据库操作基础类
 */
public abstract class BaseSpringDao {

    protected JdbcTemplate jdbcTemplate;

    protected Logger log = Logger.getLogger(BaseSpringDao.class);

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param seqName
     * @return 获取每个表的id
     */
    public long getId(String seqName) {
        String sql = "select NextVal('" + seqName + "')";
        return jdbcTemplate.queryForLong(sql);
    }
}
