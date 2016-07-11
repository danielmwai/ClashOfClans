package com.bw.dao;

import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;

public interface CGUserInteractiveMailDao {

    public static final String seq = "cg_user_interactive_mail_id_seq";

    void save(final CGUserInteractiveMailVO p0) throws CityCacheDaoException;

    void saveBatch(final List<CGUserInteractiveMailVO> p0) throws CityCacheDaoException;

    void remove(final CGUserInteractiveMailVO p0) throws CityCacheDaoException;

    void removeBatch(final List<CGUserInteractiveMailVO> p0) throws CityCacheDaoException;

    List<CGUserInteractiveMailVO> queryAllInteractiveMail(final String p0) throws CityCacheDaoException;

    List<CGUserInteractiveMailVO> queryInteractiveMailByPage(final String p0, final int p1, final int p2) throws CityCacheDaoException;

    List<CGUserInteractiveMailVO> findInteractiveMailTimeout() throws CityCacheDaoException;
}
