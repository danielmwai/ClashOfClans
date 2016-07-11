package com.bw.dao;

import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;

public interface CGUserCropInfoDAO {

    void batchSave(final String p0, final List<CGUserCropInfoVO> p1) throws CityCacheDaoException;

    void removeByMailAddress(final String p0) throws CityCacheDaoException;

    List<CGUserCropInfoVO> queryInfoByMail(final String p0) throws CityCacheDaoException;
}
