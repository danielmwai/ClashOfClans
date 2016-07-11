package com.bw.dao;

import com.bw.cache.vo.*;
import com.bw.exception.*;
import java.util.*;

public interface CGFriendRelationDAO {

    public static final String seq = "cg_friend_relation_id_seq";

    void save(final CGFriendRelationVO p0) throws CityCacheDaoException;

    void delete(final CGFriendRelationVO p0) throws CityCacheDaoException;

    List<String> queryAllCGFriendRelationVO(final CGFriendRelationVO p0) throws CityCacheDaoException;

    long queryCGFriendRelationVOCount(final CGFriendRelationVO p0) throws CityCacheDaoException;
}
