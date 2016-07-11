package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.dao.BwUserSpellDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author Administrator 用户魔法模块
 */
public class BwUserSpellCacheDAOImpl extends CacheDao implements BwUserSpellDAO {

    private static Logger logger = Logger.getLogger(BwUserMapDataCacheDAOImpl.class);
    public BwUserSpellDAO bwUserSpellDaoImpl;
    //单一主键
    String key_prefix_single = "bw_user_spell_";
    //多主键
    String key_prefix_mutiable = "bw_user_spell_mult_";

    public void delete(BwUserSpellVO bwuserspellvo) throws CacheDaoException {

    }

    @Override
    public BwUserSpellVO queryBwUserSpellVOById(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {
        String key = key_prefix_single + bwuserspellvo.getMailaddress() + "_" + bwuserspellvo.getSpelltypeid();
        Object o = this.getCache().get(key);
        if (o == null) {
            return (BwUserSpellVO) o;
        } else {
            BwUserSpellVO temp = bwUserSpellDaoImpl.queryBwUserSpellVOById(bwuserspellvo);
            if (null != temp) {
                this.getCache().put(key, temp);
            }
            return temp;
        }
    }

    @Override
    public long queryBwUserSpellVOCount(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserSpellVOIds(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserSpellVO bwuserspellvo) throws CacheDaoException {
        bwUserSpellDaoImpl.save(bwuserspellvo);
        String part_key_save = bwuserspellvo.getMailaddress() + "_" + bwuserspellvo.getSpelltypeid();
        String keySingle_save = key_prefix_single + part_key_save;
        this.getCache().put(keySingle_save, bwuserspellvo);

        //先判断list是否存在
        String keyMutiable = key_prefix_mutiable + bwuserspellvo.getMailaddress();
        Object o = this.getCache().get(keyMutiable);
        List<Long> listvalue = new ArrayList<Long>();
        if (null == o) {//从数据库获取用户地图信息
            //以mail地址为key查询用户所有的建筑信息
            List<Long> bumdListTemp = bwUserSpellDaoImpl.queryBwUserSpellVOIds(bwuserspellvo);
            if (bumdListTemp != null) {
                for (long chId : bumdListTemp) {
//						if(uniq_id==bwusermapdatavo.getUniquenessbuildid()){
//							continue;
//						}
                    listvalue.add(chId);
                }
                bumdListTemp = null;
            } else {
                listvalue.add((long) bwuserspellvo.getSpelltypeid());
            }

        } else {
            listvalue = (List<Long>) o;
            listvalue.add((long) bwuserspellvo.getSpelltypeid());
        }
        this.getCache().put(keyMutiable, listvalue);
        listvalue = null;
    }

    @Override
    public void update(BwUserSpellVO bwuserspellvo) throws CacheDaoException {
        bwUserSpellDaoImpl.update(bwuserspellvo);
        String key = key_prefix_single + bwuserspellvo.getMailaddress() + "_" + bwuserspellvo.getSpelltypeid();
        this.getCache().put(key, bwuserspellvo);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserSpellVO> queryBwUserSpellVO(BwUserSpellVO bwuserspellvo)
            throws CacheDaoException {
        String mult_key = key_prefix_mutiable + bwuserspellvo.getMailaddress();
        String subKey = "";
        //11
        Object o = this.getCache().get(mult_key);
        List<BwUserSpellVO> bumdList = null;
        if (null == o) {//从数据库获取用户魔法信息
            //以mail地址为key查询用户所有的魔法类型ID信息
            List<Long> bumdListTemp = bwUserSpellDaoImpl.queryBwUserSpellVOIds(bwuserspellvo);
            if (bumdListTemp != null) {
                List<Long> listvalue = new ArrayList<Long>();
                bumdList = new ArrayList<BwUserSpellVO>();
                for (long uniq_id : bumdListTemp) {
                    long part_key = uniq_id;
                    String keySingle = key_prefix_single + bwuserspellvo.getMailaddress() + "_" + part_key;
                    Object srcO = this.getCache().get(keySingle);
                    if (null != srcO) {//如果缓存立面有就利用缓存立面的值
                        bumdList.add((BwUserSpellVO) srcO);
                    } else {
                        //再从数据库中取值
                        bwuserspellvo.setSpelltypeid((int) uniq_id);
                        BwUserSpellVO tempVo = bwUserSpellDaoImpl.queryBwUserSpellVOById(bwuserspellvo);
                        if (null != tempVo) {
                            this.getCache().put(keySingle, tempVo);
                            bumdList.add(tempVo);
                        }

                    }
                    listvalue.add(part_key);

                }
                if (listvalue.size() > 0) {
                    this.getCache().put(mult_key, listvalue);
                }
                listvalue = null;
                bumdListTemp = null;
            }

        } else {
            List<Long> subkeyStrList = (ArrayList<Long>) o;
            bumdList = new ArrayList<BwUserSpellVO>();
            for (long str : subkeyStrList) {//获取单条记录的数据
                String keySingle = key_prefix_single + bwuserspellvo.getMailaddress() + "_" + str;
                Object oSingle = this.getCache().get(keySingle);
                if (null == oSingle) {//从数据库获取
                    long tempid = str;
                    bwuserspellvo.setSpelltypeid((int) tempid);
                    BwUserSpellVO tempVo = bwUserSpellDaoImpl.queryBwUserSpellVOById(bwuserspellvo);
                    if (null != tempVo) {
                        this.getCache().put(keySingle, tempVo);
                        bumdList.add(tempVo);
                    } else {
                        logger.error("数据库无数据,确认是否产生逻辑错误mailAddress:" + bwuserspellvo.getMailaddress() + "_" + bwuserspellvo.getSpelltypeid());
                    }

                } else {
                    bumdList.add((BwUserSpellVO) oSingle);
                }

            }

        }

        //再判断单主键
        return bumdList;
    }

    public BwUserSpellDAO getBwUserSpellDaoImpl() {
        return bwUserSpellDaoImpl;
    }

    public void setBwUserSpellDaoImpl(BwUserSpellDAO bwUserSpellDaoImpl) {
        this.bwUserSpellDaoImpl = bwUserSpellDaoImpl;
    }

    @Override
    public void batchUpdate(List<BwUserSpellVO> listtime1)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

}
