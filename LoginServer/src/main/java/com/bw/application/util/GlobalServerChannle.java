package com.bw.application.util;

import org.apache.log4j.*;
import com.bw.dao.*;
import java.util.concurrent.*;
import com.bw.baseJar.vo.*;
import java.util.*;

public class GlobalServerChannle {

    private final Logger logger;
    private CmTableDao cmTableDao;
    public static ConcurrentHashMap<String, CityServerChannleVO> serverChannleMap;

    public GlobalServerChannle() {
        this.logger = Logger.getLogger(GlobalServerChannle.class);
    }

    public void setCmTableDao(final CmTableDao cmTableDao) {
        this.cmTableDao = cmTableDao;
    }

    public void init() {
        if (GlobalServerChannle.serverChannleMap == null) {
            GlobalServerChannle.serverChannleMap = new ConcurrentHashMap<String, CityServerChannleVO>();
            final List<CityServerChannleVO> list = this.cmTableDao.initAllServerChannle();
            if (list != null && list.size() > 0) {
                for (final CityServerChannleVO cityServerChannleVO : list) {
                    GlobalServerChannle.serverChannleMap.put(String.valueOf(cityServerChannleVO.getId()), cityServerChannleVO);
                }
            } else {
                this.logger.error("\u9519\u8bef\u4fe1\u606f:\u6e38\u620f\u9891\u9053\u5217\u8868\u4e3a\u7a7a");
            }
        }
    }
}
