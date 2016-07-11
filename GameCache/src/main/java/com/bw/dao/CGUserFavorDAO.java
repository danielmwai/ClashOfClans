package com.bw.dao;

import java.sql.*;
import com.bw.exception.*;

public interface CGUserFavorDAO {

    void favorFriend(final String p0, final String p1, final Timestamp p2) throws CityCacheDaoException;

    void remove(final String p0, final String p1) throws CityCacheDaoException;

    Timestamp isFavor(final String p0, final String p1) throws CityCacheDaoException;
}
