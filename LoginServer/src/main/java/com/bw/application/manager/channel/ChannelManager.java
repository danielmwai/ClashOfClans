package com.bw.application.manager.channel;

import com.commonSocket.net.exception.*;
import java.util.concurrent.*;
import com.bw.baseJar.vo.*;

public interface ChannelManager {

    void registerChannelInfo() throws OperationFailedException, Exception;

    void registerChannelActive(final int p0, final int p1) throws OperationFailedException, Exception;

    void resetChannelActive(final int p0) throws OperationFailedException, Exception;

    ConcurrentSkipListMap<Integer, CityServerChannleVO> getChannelInfoMap();

    ConcurrentSkipListMap<Integer, Boolean> getChannelActiveMap();
}
