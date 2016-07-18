package com.bw.log;

import com.bw.cache.vo.BwLogVO;
import com.bw.jms.sender.SenderUtil;

public class ActionLog {

    private SenderUtil senderUtil;

    /**
     * 宝石购买资源日志
     *
     * @param roleId 角色编号
     * @param gem 花费的宝石
     * @param item 购买的资源类型
     * @param num 购买的资源数量
     */
    public void logBuyResource(long roleId, int gem, int item, int num) {
        BwLogVO log = new BwLogVO(roleId, Action.BUG_RESOURCE, gem, item, num);
        senderUtil.sendBwLogVO(log);
    }

    /**
     * 加速日志
     *
     * @param roleId 角色编号
     * @param gem 花费的宝石
     * @param acceType 加速的类型
     * @param target 加速的目标
     * @param acceTime 加速节约的时间
     */
    public void logAccelerate(long roleId, int gem, int acceType, long target, long acceTime) {
        BwLogVO log = new BwLogVO(roleId, Action.ACCELERATE, gem, acceType, target, acceTime);
        senderUtil.sendBwLogVO(log);
    }

    public SenderUtil getSenderUtil() {
        return senderUtil;
    }

    public void setSenderUtil(SenderUtil senderUtil) {
        this.senderUtil = senderUtil;
    }
}
