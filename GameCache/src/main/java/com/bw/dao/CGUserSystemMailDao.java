package com.bw.dao;

import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;

public interface CGUserSystemMailDao {

    public static final String seq = "cg_user_system_mail_id_seq";

    void save(final CGUserSystemMailVO p0) throws CityCacheDaoException;

    void update(final CGUserSystemMailVO p0) throws CityCacheDaoException;

    void remove(final CGUserSystemMailVO p0) throws CityCacheDaoException;

    void clearAll() throws CityCacheDaoException;

    void removeBatch(final List<CGUserSystemMailVO> p0) throws CityCacheDaoException;

    CGUserSystemMailVO querySystemMailById(final long p0, final String p1) throws CityCacheDaoException;

    List<Long> queryAllSystemMail() throws CityCacheDaoException;

    List<Long> queryMySystemMail(final String p0) throws CityCacheDaoException;

    List<CGUserSystemMailVO> findSystemMailTimeout() throws CityCacheDaoException;

    int countSystemMail() throws CityCacheDaoException;

    Long queryPastSystemMail() throws CityCacheDaoException;
}
