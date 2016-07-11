package com.bw.dao;

import java.util.List;

import com.bw.cache.vo.BwLogVO;
import com.bw.log.Action;

public interface BwLogDAO {

    public void insert(BwLogVO log);

    public void batchInsert(List<BwLogVO> logs);

    public List<BwLogVO> selectByRoleIdAndActionAndTime(long roleId, Action action, long startTime, long endTime);
}
