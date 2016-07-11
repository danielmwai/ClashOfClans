package com.bw.jms.sender;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.cache.vo.BwLogVO;
import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.jms.base.JMSRuntimeException;

/**
 * 信息发生器,所有的发送者在这里注册
 *
 * @author denny zhao
 */
public class SenderUtil implements Serializable {

    Logger logger = Logger.getLogger(SenderUtil.class);
    private static final long serialVersionUID = 7946028530014495181L;
    // 用户信息发送者
    public BwUserVOSender bwUserVOSender;
    // 用户魔法发送者
    public BwUserSpellVOSender bwUserSpellVOSender;
    // 用户地图信息发送者
    public BwUserMapDataVOSender bwUserMapDataVOSender;
    // 用户兵力发送者
    public BwUserCharacterVOSender bwUserCharacterVOSender;
    // 战斗统计发送者
    public BwUserBattleStatisticsVOSender bwUserBattleStatisticsVOSender;
    // 金矿,药水收集发送者
    public BwMineCollectorVOSender bBwMineCollectorVOSender;
    // 金库,药库,大厅 存储发送者
    public BwMineCollectorAllVOSender bwMineCollectorAllVOSender;
    // 战斗列表发送者
    public BwBattleVOSender bwBattleVOSender;
    // 用户城墙发送者 兵营更新表
    public BwBarrackVOSender bwBarrackVOSender;
    // 发送用户日志
    public BwLogVOSender bwLogVOSender;

    public void sendBwLogVO(BwLogVO vo) {
        try {
            bwLogVOSender.sendJmsMessage(vo);
        } catch (Exception e) {
            logger.warn(e);
        }
    }

    public void sendUserVO(BwUserVO obj) throws JMSRuntimeException {
        try {
            this.bwUserVOSender.sendJmsMessage(obj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送用户信息发送异常:mailAddress:" + obj.getMailaddress());
        }
    }

    public void sendBwBarrackVO(BwBarrackVO obj) throws JMSRuntimeException {
        this.bwBarrackVOSender.sendJmsMessage(obj);
    }

    public void sendBwUserSpellVO(BwUserSpellVO obj) throws JMSRuntimeException {
        bwUserSpellVOSender.sendJmsMessage(obj);
    }

    public void sendBwUserMapDataVO(BwUserMapDataVO obj) throws JMSRuntimeException {
        bwUserMapDataVOSender.sendJmsMessage(obj);
    }

    public void sendBwUserCharacterVO(BwUserCharacterVO obj) throws JMSRuntimeException {
        bwUserCharacterVOSender.sendJmsMessage(obj);

    }

    public void sendBwUserBattleStatisticsVO(BwUserBattleStatisticsVO obj) throws JMSRuntimeException {
        bwUserBattleStatisticsVOSender.sendJmsMessage(obj);
    }

    public void sendBwMineCollectorVO(BwMineCollectorVO obj) throws JMSRuntimeException {
        bBwMineCollectorVOSender.sendJmsMessage(obj);
    }

    public void sendBwMineCollectorAllVO(BwMineCollectorAllVO obj) throws JMSRuntimeException {
        bwMineCollectorAllVOSender.sendJmsMessage(obj);
    }

    public void sendBwBattleVO(BwBattleVO obj) throws JMSRuntimeException {
        bwBattleVOSender.sendJmsMessage(obj);
    }

    public BwUserVOSender getBwUserVOSender() {
        return bwUserVOSender;
    }

    public void setBwUserVOSender(BwUserVOSender bwUserVOSender) {
        this.bwUserVOSender = bwUserVOSender;
    }

    public BwUserSpellVOSender getBwUserSpellVOSender() {
        return bwUserSpellVOSender;
    }

    public void setBwUserSpellVOSender(BwUserSpellVOSender bwUserSpellVOSender) {
        this.bwUserSpellVOSender = bwUserSpellVOSender;
    }

    public BwUserMapDataVOSender getBwUserMapDataVOSender() {
        return bwUserMapDataVOSender;
    }

    public void setBwUserMapDataVOSender(BwUserMapDataVOSender bwUserMapDataVOSender) {
        this.bwUserMapDataVOSender = bwUserMapDataVOSender;
    }

    public BwUserCharacterVOSender getBwUserCharacterVOSender() {
        return bwUserCharacterVOSender;
    }

    public void setBwUserCharacterVOSender(BwUserCharacterVOSender bwUserCharacterVOSender) {
        this.bwUserCharacterVOSender = bwUserCharacterVOSender;
    }

    public BwUserBattleStatisticsVOSender getBwUserBattleStatisticsVOSender() {
        return bwUserBattleStatisticsVOSender;
    }

    public void setBwUserBattleStatisticsVOSender(BwUserBattleStatisticsVOSender bwUserBattleStatisticsVOSender) {
        this.bwUserBattleStatisticsVOSender = bwUserBattleStatisticsVOSender;
    }

    public BwMineCollectorVOSender getbBwMineCollectorVOSender() {
        return bBwMineCollectorVOSender;
    }

    public void setbBwMineCollectorVOSender(BwMineCollectorVOSender bBwMineCollectorVOSender) {
        this.bBwMineCollectorVOSender = bBwMineCollectorVOSender;
    }

    public BwMineCollectorAllVOSender getBwMineCollectorAllVOSender() {
        return bwMineCollectorAllVOSender;
    }

    public void setBwMineCollectorAllVOSender(BwMineCollectorAllVOSender bwMineCollectorAllVOSender) {
        this.bwMineCollectorAllVOSender = bwMineCollectorAllVOSender;
    }

    public BwBattleVOSender getBwBattleVOSender() {
        return bwBattleVOSender;
    }

    public void setBwBattleVOSender(BwBattleVOSender bwBattleVOSender) {
        this.bwBattleVOSender = bwBattleVOSender;
    }

    public BwBarrackVOSender getBwBarrackVOSender() {
        return bwBarrackVOSender;
    }

    public void setBwBarrackVOSender(BwBarrackVOSender bwBarrackVOSender) {
        this.bwBarrackVOSender = bwBarrackVOSender;
    }

    public BwLogVOSender getBwLogVOSender() {
        return bwLogVOSender;
    }

    public void setBwLogVOSender(BwLogVOSender bwLogVOSender) {
        this.bwLogVOSender = bwLogVOSender;
    }
}
