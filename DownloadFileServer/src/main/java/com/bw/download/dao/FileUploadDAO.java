package com.bw.download.dao;

public class FileUploadDAO extends BaseSpringDao {

    public void save(String mailAddress, String filePath) {

        String sql = "INSERT INTO cg_user_image_path(mail_address, file_path) VALUES (?, ?)";

        getJdbcTemplate().update(sql, new Object[]{mailAddress, filePath});

    }

    public void remove(String mailAddress) {

        String sql = "delete from cg_user_image_path where mail_address = ?";

        getJdbcTemplate().update(sql, new Object[]{mailAddress});
    }
}
