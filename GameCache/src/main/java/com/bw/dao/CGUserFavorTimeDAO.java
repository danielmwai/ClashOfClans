package com.bw.dao;

import java.sql.*;
import com.bw.exception.*;
import com.bw.cache.vo.*;

public interface CGUserFavorTimeDAO {

    void save(final String p0, final Timestamp p1) throws CityCacheDaoException;

    void favoring(final String p0) throws CityCacheDaoException;

    void remove(final String p0) throws CityCacheDaoException;

    CGUserFavorTimeVO queryFavorTime(final String p0) throws CityCacheDaoException;
}
