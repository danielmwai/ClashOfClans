package com.bw.application.manager;

import com.bw.application.exception.ManagerServerException;
import com.bw.baseJar.common.LineStatusEnum;
import com.bw.cache.vo.BwUserVO;

/**
 * @author denny zhao 离线用户统计
 */
public interface IOffLineManager {

    /**
     * @param mailAddress
     * @param pvpMark
     * @throws ManagerServerException
     */
    public void addOfflineMember(String mailAddress, int pvpMark) throws ManagerServerException;

    /**
     * @param mailAddress
     * @param pvpMark
     * @param lineStatus
     * @throws ManagerServerException 用户pvpmark变更的时候要注意 移除老的 分数段的记录
     */
    public void removeOfflineMember(String mailAddress, int pvpMark, LineStatusEnum lineStatus) throws ManagerServerException;

    /**
     * @param mailAddress
     * @param pvpMark
     * @return
     * @throws ManagerServerException 此方法影响性能 慎用(要看linkList 的大小)
     */
    public boolean IsContainMember(String mailAddress, int pvpMark) throws ManagerServerException;

    /**
     * @param mailAddress
     * @param pvpMark
     * @return mailAddress
     * @throws ManagerServerException 查找想匹配的玩家
     */
    public String battleMatching(String mailAddress, int pvpMark) throws ManagerServerException;

    /**
     * @param mailAddress
     * @param lineStatus
     * @return
     * @throws ManagerServerException 修改用户在线战斗状态
     */
    public boolean updateBattleLineStatus(String mailAddress, LineStatusEnum lineStatus) throws ManagerServerException;

    /**
     * @param mailAddress
     * @return
     * @throws ManagerServerException 战斗匹配最大分数:大厅等级,maxpvp,pvp比较出最大的
     */
    public int getMaxPVPMark(String mailAddress, int currentPVPMark) throws ManagerServerException;
}
