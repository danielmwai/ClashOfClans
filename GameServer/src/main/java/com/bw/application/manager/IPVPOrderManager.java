package com.bw.application.manager;

import com.bw.application.exception.ManagerServerException;
import com.bw.application.message.PVPGradeOrder.PVPGradeOrderResponse;

public interface IPVPOrderManager {

    /**
     * @param boweiId
     * @param builder
     * @return
     * @throws ManagerServerException 获取用户pvp分数排行
     */
    public int getPVPGradeOrder(String boweiId, PVPGradeOrderResponse.Builder builder) throws ManagerServerException;
}
