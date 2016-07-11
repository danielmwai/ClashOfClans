package com.bw.receiverImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import com.bw.service.BwBarrackVOWriter;
import com.bw.service.BwBattleVOWriter;
import com.bw.service.BwLogVOWriter;
import com.bw.service.BwMineCollectorAllVOWriter;
import com.bw.service.BwMineCollectorVOWriter;
import com.bw.service.BwUserBattleStatisticsVOWriter;
import com.bw.service.BwUserCharacterVOWriter;
import com.bw.service.BwUserMapDataVOWriter;
import com.bw.service.BwUserSpellVOWriter;
import com.bw.service.BwUserVOWriter;

public class ReceiverUtilImpl implements IReceiverUtil {

    /**
     *
     */
    private static final long serialVersionUID = -3996014269275357836L;
    private static Logger logger = Logger.getLogger(ReceiverUtilImpl.class);
    private static int DEFAULT_MAX_VALUE = 500;
    public BwBattleVOWriter bwBattleVOWriter;
    public BwMineCollectorAllVOWriter bwMineCollectorAllVOWriter;
    public BwMineCollectorVOWriter bwMineCollectorVOWriter;
    public BwUserBattleStatisticsVOWriter bwUserBattleStatisticsVOWriter;
    public BwUserCharacterVOWriter bwUserCharacterVOWriter;
    public BwUserMapDataVOWriter bwUserMapDataVOWriter;
    public BwUserSpellVOWriter bwUserSpellVOWriter;
    public BwUserVOWriter bwUserVOWriter;
    public BwBarrackVOWriter bwBarrackVOWriter;
    public BwLogVOWriter bwLogVOWriter;
    private Map<String, BwLogVO> bwLogVOMap = new ConcurrentHashMap<String, BwLogVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwBattleVO> bwBattleVOMap = new ConcurrentHashMap<String, BwBattleVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwMineCollectorAllVO> bwMineCollectorAllVOMap = new ConcurrentHashMap<String, BwMineCollectorAllVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwMineCollectorVO> bwMineCollectorVOMap = new ConcurrentHashMap<String, BwMineCollectorVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwUserBattleStatisticsVO> bwUserBattleStatisticsVOMap = new ConcurrentHashMap<String, BwUserBattleStatisticsVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwUserCharacterVO> bwUserCharacterVOMap = new ConcurrentHashMap<String, BwUserCharacterVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwUserMapDataVO> bwUserMapDataVOMap = new ConcurrentHashMap<String, BwUserMapDataVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwUserSpellVO> bwUserSpellVOMap = new ConcurrentHashMap<String, BwUserSpellVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwUserVO> bwUserVOMap = new ConcurrentHashMap<String, BwUserVO>(DEFAULT_MAX_VALUE);
    private Map<String, BwBarrackVO> bwBarrackVOMap = new ConcurrentHashMap<String, BwBarrackVO>(DEFAULT_MAX_VALUE);
    private int logMaxCount;
    public int battleMaxCount;
    public int mineCollectorAllMaxCount;
    public int mineCollectorMaxCount;
    public int battleStatisticsMaxCount;
    public int userCharacterMaxCount;
    public int userMapDataMaxCount;
    public int userSpellMaxCount;
    public int userMaxCount;
    public int barrackMaxCount;

    @Override
    public void receiveBwBattleVO(BwBattleVO buvo) {
        String key = buvo.getDefencer() + "_" + buvo.getAttacker();
        bwBattleVOMap.put(key, buvo);
        if (bwBattleVOMap.size() >= this.getBattleMaxCount()) {
            Collection<BwBattleVO> c = new ArrayList<BwBattleVO>(this.getBattleMaxCount());
            c.addAll(bwBattleVOMap.values());
            bwBattleVOWriter.getClq().add(c);
            bwBattleVOMap.clear();
        }
    }

    @Override
    public void receiveBwMineCollectorAllVO(BwMineCollectorAllVO buvo) {
        String key = buvo.getMailAddress() + "_" + buvo.getUserbuildingdataid();
        bwMineCollectorAllVOMap.put(key, buvo);
        if (bwMineCollectorAllVOMap.size() >= this.getMineCollectorAllMaxCount()) {
            Collection<BwMineCollectorAllVO> c = new ArrayList<BwMineCollectorAllVO>(this.getMineCollectorAllMaxCount());
            c.addAll(bwMineCollectorAllVOMap.values());
            bwMineCollectorAllVOWriter.getClq().add(c);
            bwMineCollectorAllVOMap.clear();
        }

    }

    @Override
    public void receiveBwMineCollectorVO(BwMineCollectorVO buvo) {
        String key = buvo.getMailAddress() + "_" + buvo.getUserbuildingdataid();
        bwMineCollectorVOMap.put(key, buvo);
        if (bwMineCollectorVOMap.size() >= this.getMineCollectorMaxCount()) {
            Collection<BwMineCollectorVO> c = new ArrayList<BwMineCollectorVO>(this.getMineCollectorMaxCount());
            c.addAll(bwMineCollectorVOMap.values());
            bwMineCollectorVOWriter.getClq().add(c);
            bwMineCollectorVOMap.clear();
        }

    }

    @Override
    public void receiveBwUserBattleStatisticsVO(BwUserBattleStatisticsVO buvo) {
        String key = buvo.getMailaddress() + "_";
        bwUserBattleStatisticsVOMap.put(key, buvo);
        if (bwUserBattleStatisticsVOMap.size() >= this.getBattleStatisticsMaxCount()) {
            Collection<BwUserBattleStatisticsVO> c = new ArrayList<BwUserBattleStatisticsVO>(this.getBattleStatisticsMaxCount());
            c.addAll(bwUserBattleStatisticsVOMap.values());
            bwUserBattleStatisticsVOWriter.getClq().add(c);
            bwUserBattleStatisticsVOMap.clear();
        }

    }

    @Override
    public void receiveBwUserCharacterVO(BwUserCharacterVO buvo) {
        String key = buvo.getMailaddress() + "_" + buvo.getChararchterid();
        bwUserCharacterVOMap.put(key, buvo);
        if (bwUserCharacterVOMap.size() >= this.getUserCharacterMaxCount()) {
            Collection<BwUserCharacterVO> c = new ArrayList<BwUserCharacterVO>(this.getUserCharacterMaxCount());
            c.addAll(bwUserCharacterVOMap.values());
            bwUserCharacterVOWriter.getClq().add(c);
            bwUserCharacterVOMap.clear();
        }

    }

    @Override
    public void receiveBwUserMapDataVO(BwUserMapDataVO buvo) {
        String key = buvo.getMailaddress() + "_" + buvo.getUniquenessbuildid();
        bwUserMapDataVOMap.put(key, buvo);
        if (bwUserMapDataVOMap.size() >= this.getUserMapDataMaxCount()) {
            Collection<BwUserMapDataVO> c = new ArrayList<BwUserMapDataVO>(this.getUserMapDataMaxCount());
            c.addAll(bwUserMapDataVOMap.values());
            bwUserMapDataVOWriter.getClq().add(c);
            bwUserMapDataVOMap.clear();
        }

    }

    @Override
    public void receiveBwUserSpellVO(BwUserSpellVO buvo) {
        String userSpell = buvo.getMailaddress() + "_" + buvo.getSpelltypeid();
        bwUserSpellVOMap.put(userSpell, buvo);
        if (bwUserSpellVOMap.size() >= this.getUserSpellMaxCount()) {
            Collection<BwUserSpellVO> c = new ArrayList<BwUserSpellVO>(this.getUserSpellMaxCount());
            c.addAll(bwUserSpellVOMap.values());
            bwUserSpellVOWriter.getClq().add(c);
            bwUserSpellVOMap.clear();
        }
    }

    @Override
    public void receiveBwLogVO(BwLogVO vo) {
        String key = vo.getRoleId() + "_" + vo.getAction();
        bwLogVOMap.put(key, vo);
        if (bwLogVOMap.size() > getLogMaxCount()) {
            bwLogVOWriter.getClq().add(bwLogVOMap.values());
            bwLogVOMap.clear();
        }
    }

    public BwLogVOWriter getBwLogVOWriter() {
        return bwLogVOWriter;
    }

    public void setBwLogVOWriter(BwLogVOWriter bwLogVOWriter) {
        this.bwLogVOWriter = bwLogVOWriter;
    }

    public int getLogMaxCount() {
        return logMaxCount;
    }

    public void setLogMaxCount(int logMaxCount) {
        this.logMaxCount = logMaxCount;
    }

    @Override
    public void receiveBwUserVO(BwUserVO buvo) {
        String userKey = buvo.getMailaddress() + "_";
        bwUserVOMap.put(userKey, buvo);
        if (bwUserVOMap.size() >= this.getUserMaxCount()) {
            Collection<BwUserVO> c = new ArrayList<BwUserVO>(this.getUserMaxCount());
            c.addAll(bwUserVOMap.values());
            bwUserVOWriter.getClq().add(c);
            bwUserVOMap.clear();
        }
    }

    public BwBattleVOWriter getBwBattleVOWriter() {
        return bwBattleVOWriter;
    }

    public void setBwBattleVOWriter(BwBattleVOWriter bwBattleVOWriter) {
        this.bwBattleVOWriter = bwBattleVOWriter;
    }

    public BwMineCollectorAllVOWriter getBwMineCollectorAllVOWriter() {
        return bwMineCollectorAllVOWriter;
    }

    public void setBwMineCollectorAllVOWriter(
            BwMineCollectorAllVOWriter bwMineCollectorAllVOWriter) {
        this.bwMineCollectorAllVOWriter = bwMineCollectorAllVOWriter;
    }

    public BwMineCollectorVOWriter getBwMineCollectorVOWriter() {
        return bwMineCollectorVOWriter;
    }

    public void setBwMineCollectorVOWriter(
            BwMineCollectorVOWriter bwMineCollectorVOWriter) {
        this.bwMineCollectorVOWriter = bwMineCollectorVOWriter;
    }

    public BwUserBattleStatisticsVOWriter getBwUserBattleStatisticsVOWriter() {
        return bwUserBattleStatisticsVOWriter;
    }

    public void setBwUserBattleStatisticsVOWriter(
            BwUserBattleStatisticsVOWriter bwUserBattleStatisticsVOWriter) {
        this.bwUserBattleStatisticsVOWriter = bwUserBattleStatisticsVOWriter;
    }

    public BwUserCharacterVOWriter getBwUserCharacterVOWriter() {
        return bwUserCharacterVOWriter;
    }

    public void setBwUserCharacterVOWriter(
            BwUserCharacterVOWriter bwUserCharacterVOWriter) {
        this.bwUserCharacterVOWriter = bwUserCharacterVOWriter;
    }

    public BwUserMapDataVOWriter getBwUserMapDataVOWriter() {
        return bwUserMapDataVOWriter;
    }

    public void setBwUserMapDataVOWriter(BwUserMapDataVOWriter bwUserMapDataVOWriter) {
        this.bwUserMapDataVOWriter = bwUserMapDataVOWriter;
    }

    public BwUserSpellVOWriter getBwUserSpellVOWriter() {
        return bwUserSpellVOWriter;
    }

    public void setBwUserSpellVOWriter(BwUserSpellVOWriter bwUserSpellVOWriter) {
        this.bwUserSpellVOWriter = bwUserSpellVOWriter;
    }

    public BwUserVOWriter getBwUserVOWriter() {
        return bwUserVOWriter;
    }

    public void setBwUserVOWriter(BwUserVOWriter bwUserVOWriter) {
        this.bwUserVOWriter = bwUserVOWriter;
    }

    public int getBattleMaxCount() {
        return battleMaxCount;
    }

    public void setBattleMaxCount(int battleMaxCount) {
        this.battleMaxCount = battleMaxCount;
    }

    public int getMineCollectorAllMaxCount() {
        return mineCollectorAllMaxCount;
    }

    public void setMineCollectorAllMaxCount(int mineCollectorAllMaxCount) {
        this.mineCollectorAllMaxCount = mineCollectorAllMaxCount;
    }

    public int getMineCollectorMaxCount() {
        return mineCollectorMaxCount;
    }

    public void setMineCollectorMaxCount(int mineCollectorMaxCount) {
        this.mineCollectorMaxCount = mineCollectorMaxCount;
    }

    public int getBattleStatisticsMaxCount() {
        return battleStatisticsMaxCount;
    }

    public void setBattleStatisticsMaxCount(int battleStatisticsMaxCount) {
        this.battleStatisticsMaxCount = battleStatisticsMaxCount;
    }

    public int getUserCharacterMaxCount() {
        return userCharacterMaxCount;
    }

    public void setUserCharacterMaxCount(int userCharacterMaxCount) {
        this.userCharacterMaxCount = userCharacterMaxCount;
    }

    public int getUserMapDataMaxCount() {
        return userMapDataMaxCount;
    }

    public void setUserMapDataMaxCount(int userMapDataMaxCount) {
        this.userMapDataMaxCount = userMapDataMaxCount;
    }

    public int getUserSpellMaxCount() {
        return userSpellMaxCount;
    }

    public void setUserSpellMaxCount(int userSpellMaxCount) {
        this.userSpellMaxCount = userSpellMaxCount;
    }

    public int getUserMaxCount() {
        return userMaxCount;
    }

    public void setUserMaxCount(int userMaxCount) {
        this.userMaxCount = userMaxCount;
    }

    public int getBarrackMaxCount() {
        return barrackMaxCount;
    }

    public void setBarrackMaxCount(int barrackMaxCount) {
        this.barrackMaxCount = barrackMaxCount;
    }

    public BwBarrackVOWriter getBwBarrackVOWriter() {
        return bwBarrackVOWriter;
    }

    public void setBwBarrackVOWriter(BwBarrackVOWriter bwBarrackVOWriter) {
        this.bwBarrackVOWriter = bwBarrackVOWriter;
    }

    @Override
    public void receiveBwBarrackVO(BwBarrackVO bbvo) {
        String userKey = bbvo.getMailAddress() + "_" + bbvo.getUsermapdataid() + "_" + bbvo.getUsercharacterid();
        bwBarrackVOMap.put(userKey, bbvo);
        if (bwBarrackVOMap.size() >= this.getBarrackMaxCount()) {
            Collection<BwBarrackVO> c = new ArrayList<BwBarrackVO>(this.getBarrackMaxCount());
            c.addAll(bwBarrackVOMap.values());
            bwBarrackVOWriter.getClq().add(c);
            bwBarrackVOMap.clear();
        }

    }
}
