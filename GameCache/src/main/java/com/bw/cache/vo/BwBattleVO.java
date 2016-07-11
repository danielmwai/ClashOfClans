package com.bw.cache.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 战斗表
 */
public class BwBattleVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7861455156726655057L;
    /**
     * attacker
     *
     * 攻击者
     */
    private String attacker;
    /**
     * battle_id
     *
     *
     */
    private long battleId;
    /**
     * battle_time
     *
     * 战斗时间
     */
    private String battleTime;
    /**
     * defencer
     *
     * 防御者
     */
    private String defencer;
    /**
     * get_elixir
     *
     *
     */
    private long getElixir;
    /**
     * get_golden
     *
     *
     */
    private long getGolden;
    /**
     * pvp_mark
     *
     *
     */
    private long pvpMark;
    //战斗结果0胜利 1失败
    private int battleResult;
    //攻击者兵力使用情况
    private List<BwBattleCharacterUsedCountVO> bwBattleCharacterUsedCountVOList;
    //是否使用工会兵力
    private boolean useClansFlag;
    //战斗恢放名称 防御者+攻击者名字
    private String replayName;
    //上传验证码
    private long verifyCode;
    //星星数量
    private int starCount;

    public String getAttacker() {
        return attacker;
    }

    public void setAttacker(String attacker) {
        this.attacker = attacker;
    }

    public long getBattleid() {
        return battleId;
    }

    public void setBattleid(long battleId) {
        this.battleId = battleId;
    }

    public String getBattletime() {
        return battleTime;
    }

    public void setBattletime(String battleTime) {
        this.battleTime = battleTime;
    }

    public String getDefencer() {
        return defencer;
    }

    public void setDefencer(String defencer) {
        this.defencer = defencer;
    }

    public long getGetelixir() {
        return getElixir;
    }

    public void setGetelixir(long getElixir) {
        this.getElixir = getElixir;
    }

    public long getGetgolden() {
        return getGolden;
    }

    public void setGetgolden(long getGolden) {
        this.getGolden = getGolden;
    }

    public long getPvpmark() {
        return pvpMark;
    }

    public void setPvpmark(long pvpMark) {
        this.pvpMark = pvpMark;
    }

    public int getBattleResult() {
        return battleResult;
    }

    public void setBattleResult(int battleResult) {
        this.battleResult = battleResult;
    }

    public List<BwBattleCharacterUsedCountVO> getBwBattleCharacterUsedCountVOList() {
        return bwBattleCharacterUsedCountVOList;
    }

    public void setBwBattleCharacterUsedCountVOList(
            List<BwBattleCharacterUsedCountVO> bwBattleCharacterUsedCountVOList) {
        this.bwBattleCharacterUsedCountVOList = bwBattleCharacterUsedCountVOList;
    }

    public boolean isUseClansFlag() {
        return useClansFlag;
    }

    public void setUseClansFlag(boolean useClansFlag) {
        this.useClansFlag = useClansFlag;
    }

    public String getReplayName() {
        return replayName;
    }

    public void setReplayName(String replayName) {
        this.replayName = replayName;
    }

    public long getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(long verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

}
