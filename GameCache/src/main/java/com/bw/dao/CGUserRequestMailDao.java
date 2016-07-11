package com.bw.dao;

import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;

public interface CGUserRequestMailDao {

    public static final String seq = "cg_user_request_mail_id_seq";

    void save(final CGUserRequestMailVO p0) throws CityCacheDaoException;

    void remove(final CGUserRequestMailVO p0) throws CityCacheDaoException;

    void removeBatch(final List<CGUserRequestMailVO> p0) throws CityCacheDaoException;

    List<CGUserRequestMailVO> findRequestMailTimeout() throws CityCacheDaoException;

    List<CGUserRequestMailVO> queryAllRequestMail(final String p0) throws CityCacheDaoException;

    List<CGUserRequestMailVO> queryRequestMailByPage(final String p0, final int p1, final int p2) throws CityCacheDaoException;
}
