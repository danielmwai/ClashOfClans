package com.bw.application.manager.channel;

import com.commonSocket.net.Service;
import org.apache.log4j.*;
import java.util.concurrent.atomic.*;
import com.bw.baseJar.vo.*;
import com.bw.dao.*;
import java.util.concurrent.*;
import com.commonSocket.net.exception.*;
import java.util.*;

public class ChannelManagerImpl implements ChannelManager, Service {

    private Logger logger;
    private AtomicBoolean started;
    private ConcurrentSkipListMap<Integer, Boolean> channelActiveMap;
    private ConcurrentSkipListMap<Integer, CityServerChannleVO> channelInfoMap;
    private ExecutorService channelService;
    private CmTableDao cmTableDao;
    private int egisTime;

    public CmTableDao getCmTableDao() {
        return this.cmTableDao;
    }

    public void setCmTableDao(final CmTableDao cmTableDao) {
        this.cmTableDao = cmTableDao;
    }

    public ChannelManagerImpl() {
        this.logger = Logger.getLogger(this.getClass());
        this.started = new AtomicBoolean(false);
    }

    @Override
    public void start() {
        if (this.started.compareAndSet(false, true)) {
            this.channelService = Executors.newCachedThreadPool();
            this.channelActiveMap = new ConcurrentSkipListMap<Integer, Boolean>();
            this.channelInfoMap = new ConcurrentSkipListMap<Integer, CityServerChannleVO>();
            try {
                this.registerChannelInfo();
            } catch (OperationFailedException e) {
                this.logger.error(e);
            } catch (Exception e2) {
                this.logger.error(e2);
            }
        }
    }

    @Override
    public void stop() {
        if (this.started.compareAndSet(true, false)) {
            this.channelActiveMap.clear();
            this.channelActiveMap = null;
        }
    }

    @Override
    public void registerChannelInfo() throws OperationFailedException, Exception {
        final List<CityServerChannleVO> channleList = this.cmTableDao.initAllServerChannle();
        if (channleList == null) {
            throw new Exception("registerChannelInfo error!");
        }
        for (final CityServerChannleVO cityServerChannleVO : channleList) {
            this.channelInfoMap.put((int) cityServerChannleVO.getId(), cityServerChannleVO);
            this.channelActiveMap.put((int) cityServerChannleVO.getId(), false);
        }
    }

    @Override
    public void registerChannelActive(final int appId, final int currentRole) throws OperationFailedException, Exception {
        if (this.channelActiveMap.containsKey(new Integer(appId))) {
            this.resetChannelActive(appId);
            final CityServerChannleVO wGgameChannleVO = this.channelInfoMap.get(new Integer(appId));
            wGgameChannleVO.setUserCount(currentRole);
        }
    }

    @Override
    public void resetChannelActive(final int appId) throws OperationFailedException, Exception {
        this.channelActiveMap.put(new Integer(appId), true);
    }

    @Override
    public ConcurrentSkipListMap<Integer, Boolean> getChannelActiveMap() {
        return this.channelActiveMap;
    }

    public void setChannelActiveMap(final ConcurrentSkipListMap<Integer, Boolean> channelActiveMap) {
        this.channelActiveMap = channelActiveMap;
    }

    @Override
    public ConcurrentSkipListMap<Integer, CityServerChannleVO> getChannelInfoMap() {
        return this.channelInfoMap;
    }

    public void setChannelInfoMap(final ConcurrentSkipListMap<Integer, CityServerChannleVO> channelInfoMap) {
        this.channelInfoMap = channelInfoMap;
    }

    public ExecutorService getChannelService() {
        return this.channelService;
    }

    public void setChannelService(final ExecutorService channelService) {
        this.channelService = channelService;
    }

    public int getEgisTime() {
        return this.egisTime;
    }

    public void setEgisTime(final int egisTime) {
        this.egisTime = egisTime;
    }
}
