package com.bw.dao;

import java.util.*;
import com.bw.cache.vo.*;
import com.bw.exception.*;

public interface CGUserBuildingInfoDAO {

    void batchSave(final String p0, final List<CGUserBuildingInfoVO> p1) throws CityCacheDaoException;

    void removeByMailAddress(final String p0) throws CityCacheDaoException;

    List<CGUserBuildingInfoVO> queryInfoByMail(final String p0) throws CityCacheDaoException;
}
