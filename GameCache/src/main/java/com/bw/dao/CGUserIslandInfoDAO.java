package com.bw.dao;

import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;

public interface CGUserIslandInfoDAO {

    void batchSave(final String p0, final List<CGUserIslandInfoVO> p1) throws CityCacheDaoException;

    void removeByMailAddress(final String p0) throws CityCacheDaoException;

    List<CGUserIslandInfoVO> queryInfoByMail(final String p0) throws CityCacheDaoException;
}
