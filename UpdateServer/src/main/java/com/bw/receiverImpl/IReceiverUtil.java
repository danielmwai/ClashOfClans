package com.bw.receiverImpl;

import java.io.Serializable;

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

/**
 * @author denny zhao 日期:2012-12-11 接收游戏服务器发过来的信息
 */
public interface IReceiverUtil extends Serializable {
    // public void receiveCurrency(Object message);
    // 接收用户信息

    public void receiveBwUserVO(BwUserVO buvo);

    // 魔法
    public void receiveBwUserSpellVO(BwUserSpellVO buvo);

    // 地图
    public void receiveBwUserMapDataVO(BwUserMapDataVO buvo);

    // 用户兵力
    public void receiveBwUserCharacterVO(BwUserCharacterVO buvo);

    // 战斗统计
    public void receiveBwUserBattleStatisticsVO(BwUserBattleStatisticsVO buvo);

    // 金矿 药水
    public void receiveBwMineCollectorVO(BwMineCollectorVO buvo);

    // 金库 药库
    public void receiveBwMineCollectorAllVO(BwMineCollectorAllVO buvo);

    // 战斗
    public void receiveBwBattleVO(BwBattleVO buvo);

    // 兵营
    public void receiveBwBarrackVO(BwBarrackVO bbvo);

    public void receiveBwLogVO(BwLogVO vo);
}
