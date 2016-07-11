package com.bw.dao;

import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.sql.*;

public interface CGUserCareDAO {

    void save(final CGUserCareVO p0) throws CityCacheDaoException;

    void refresh(final CGUserCareVO p0) throws CityCacheDaoException;

    Timestamp queryLastModify(final CGUserCareVO p0) throws CityCacheDaoException;
}
