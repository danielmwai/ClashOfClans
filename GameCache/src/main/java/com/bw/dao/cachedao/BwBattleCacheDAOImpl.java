package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bw.baseJar.common.CommonGameData;
import com.bw.cache.vo.BwBattleDestoryVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.dao.BwBattleDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao
 *
 */
public class BwBattleCacheDAOImpl extends CacheDao implements BwBattleDAO {

    public BwBattleDAO bwBattleDaoImpl;
    //保存的战斗配对信息
    String key_prefix_battling = "bw_battleing_";
    //保存的战斗列表(已经打完的)
    String key_prefix_list = "bw_battled_list_";
    //新增的被打记录
    String key_prefix_list_new_count = "bw_battled_list_new_count_";
    //保存 墓碑信息(被摧毁的情况)
    String key_prefix_destory = "bw_battled_destory_";
    String key_prefix_verifycode = "key_prefix_verifycode_";

    public void delete(BwBattleVO bwbattlevo) throws CacheDaoException {
//		String key=this.key_prefix_battling+bwbattlevo.getDefencer()+"_"+bwbattlevo.getAttacker();
        String key = this.key_prefix_battling + bwbattlevo.getAttacker();
        this.getCache().remove(key);
    }

    @Override
    public BwBattleVO queryBwBattleVOById(BwBattleVO bwbattlevo)
            throws CacheDaoException {
        String key = this.key_prefix_battling + bwbattlevo.getAttacker();
        Object o = this.getCache().get(key);

        return o == null ? null : (BwBattleVO) o;
    }

    @Override
    public long queryBwBattleVOCount(BwBattleVO bwbattlevo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwBattleVOIds(BwBattleVO bwbattlevo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwBattleVO bwbattlevo) throws CacheDaoException {
//		bwBattleDaoImpl.save(bwbattlevo);//不在这里保存 在战斗结果中保存
        String key = this.key_prefix_battling + bwbattlevo.getAttacker();
        this.getCache().put(key, bwbattlevo);
    }

    @Override
    public void update(BwBattleVO bwbattlevo) throws CacheDaoException {
        //验证码缓存
//		String vertyCode=key_prefix_verifycode+bwbattlevo
        String key = this.key_prefix_battling + bwbattlevo.getAttacker();
        this.getCache().put(key, bwbattlevo);
    }

    @SuppressWarnings("unchecked")
    @Override
    public LinkedList<BwBattleVO> queryBwBattleVO(String mailAddress)
            throws CacheDaoException {

        String key = key_prefix_list + mailAddress;
        Object o = this.getCache().get(key);
        return o == null ? null : (LinkedList<BwBattleVO>) o;
    }

    public BwBattleDAO getBwBattleDaoImpl() {
        return bwBattleDaoImpl;
    }

    public void setBwBattleDaoImpl(BwBattleDAO bwBattleDaoImpl) {
        this.bwBattleDaoImpl = bwBattleDaoImpl;
    }

    @Override
    public void deleteBattleDestory(String mailAddress)
            throws CacheDaoException {
        // TODO Auto-generated method stub
        String key = key_prefix_destory + mailAddress;
        this.getCache().remove(key);
    }

    @Override
    public void deleteBwBattledListVO(String mailAddress)
            throws CacheDaoException {

        this.getCache().remove(mailAddress);

    }

    @Override
    public void updateBattleDestory(String mailAddress, List<BwBattleDestoryVO> bwBattleDestoryVOList)
            throws CacheDaoException {
        String key = key_prefix_destory + mailAddress;
        this.getCache().put(key, bwBattleDestoryVOList);

    }

    @Override
    public List<BwBattleDestoryVO> queryBwBattleDestoryVO(String mailAddress)
            throws CacheDaoException {
        String key = key_prefix_destory + mailAddress;
        Object object = this.getCache().get(key);

        return object == null ? null : (List<BwBattleDestoryVO>) object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void saveBattledList(String defencerMailAddress, BwBattleVO bwBattledListVO)
            throws CacheDaoException {
        //数量增加
        String key_count = key_prefix_list_new_count + defencerMailAddress;
        Object o_count = this.getCache().get(key_count);
        if (o_count == null) {
            this.getCache().put(key_count, 1);
        } else {
            int newBattleCount = Integer.parseInt(o_count.toString());
            this.getCache().put(key_count, newBattleCount + 1);
        }
        //新增一条战斗记录到数据库 暂时先记录一下
        bwBattleDaoImpl.save(bwBattledListVO);
//		bwBattledListVO.setReplayName(defencerMailAddress+"_"+attackMailAddress);
        String key = key_prefix_list + defencerMailAddress;
        Object o = this.getCache().get(key);
        LinkedList<BwBattleVO> battleList = null;
        if (o == null) {
            battleList = new LinkedList<BwBattleVO>();
            battleList.add(bwBattledListVO);
        } else {
            battleList = (LinkedList<BwBattleVO>) o;
            if (battleList.size() > CommonGameData.BATTLE_LIST_MAX_COUNT) {
                battleList.removeLast();
//				battleList.set(CommonGameData.BATTLE_LIST_MAX_COUNT-1,bwBattledListVO);
            }
            battleList.add(bwBattledListVO);

        }
        this.getCache().put(key, battleList);
        battleList = null;
    }

    @Override
    public void batchUpdate(List<BwBattleVO> bwbattlevoList)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public long queryBwBattleVOCountFlag(String mailAddress)
            throws CacheDaoException {
        //数量增加
        String key_count = key_prefix_list_new_count + mailAddress;
        Object o_count = this.getCache().get(key_count);
        if (o_count == null) {
            return 0;
        } else {
            int newBattleCount = Integer.parseInt(o_count.toString());
            return newBattleCount;
        }
    }

    @Override
    public void updateBwBattleVOCountFlag(String mailAddress, int count)
            throws CacheDaoException {
        String key_count = key_prefix_list_new_count + mailAddress;
        this.getCache().put(key_count, count);

    }

}
