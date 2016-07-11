package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bw.cache.utils.IocUtils;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.dao.BwUserCharacterDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao
 *
 */
public class BwUserCharacterCacheDAOImpl extends CacheDao implements
        BwUserCharacterDAO {

    private static Logger logger = Logger.getLogger(BwUserMapDataCacheDAOImpl.class);
    public BwUserCharacterDAO bwUserCharacterDaoImpl;
    //单一主键
    String key_prefix_single = "bw_user_character_";
    //多主键
    String key_prefix_mutiable = "bw_user_character_mult_";

    public void delete(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
    }

    @Override
    public BwUserCharacterVO queryBwUserCharacterVOById(
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {
        String key = key_prefix_single + bwusercharactervo.getMailaddress() + "_" + bwusercharactervo.getChararchterid();
        Object o = this.getCache().get(key);
        if (o != null) {
            return (BwUserCharacterVO) o;
        } else {
            BwUserCharacterVO tempVO = bwUserCharacterDaoImpl.queryBwUserCharacterVOById(bwusercharactervo);
            if (null != tempVO) {
                this.getCache().put(key, tempVO);
                return tempVO;
            }
        }
        return null;
    }

    @Override
    public long queryBwUserCharacterVOCount(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwUserCharacterVOIds(
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwUserCharacterVO bwUserCharacterVO)
            throws CacheDaoException {
        bwUserCharacterDaoImpl.save(bwUserCharacterVO);
        String part_key_save = bwUserCharacterVO.getMailaddress() + "_" + bwUserCharacterVO.getChararchterid();
        String keySingle_save = key_prefix_single + part_key_save;
        this.getCache().put(keySingle_save, bwUserCharacterVO);

        //先判断list是否存在
        String keyMutiable = key_prefix_mutiable + bwUserCharacterVO.getMailaddress();
        Object o = this.getCache().get(keyMutiable);
        List<Long> listvalue = new ArrayList<Long>();
        if (null == o) {//从数据库获取用户地图信息
            //以mail地址为key查询用户所有的建筑信息
            List<Long> bumdListTemp = bwUserCharacterDaoImpl.queryBwUserCharacterVOIds(bwUserCharacterVO);
            if (bumdListTemp != null && bumdListTemp.size() > 0) {
                for (long chId : bumdListTemp) {
//						if(uniq_id==bwusermapdatavo.getUniquenessbuildid()){
//							continue;
//						}
                    listvalue.add(chId);
                }
                bumdListTemp.clear();
                bumdListTemp = null;
            } else {
                listvalue.add((long) bwUserCharacterVO.getChararchterid());
            }

        } else {
            listvalue = (List<Long>) o;
            listvalue.add((long) bwUserCharacterVO.getChararchterid());
        }
        this.getCache().put(keyMutiable, IocUtils.removeRepeatValue(listvalue));
        listvalue.clear();
        listvalue = null;

    }

    @Override
    public void update(BwUserCharacterVO bwusercharactervo)
            throws CacheDaoException {
        bwUserCharacterDaoImpl.update(bwusercharactervo);
        String part_key = bwusercharactervo.getMailaddress() + "_" + bwusercharactervo.getChararchterid();
        String keySingle = key_prefix_single + part_key;
        this.getCache().put(keySingle, bwusercharactervo);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserCharacterVO> queryBwUserCharacterVO(
            BwUserCharacterVO bwusercharactervo) throws CacheDaoException {
        String mult_key = key_prefix_mutiable + bwusercharactervo.getMailaddress();
        String subKey = "";
        //11
        Object o = this.getCache().get(mult_key);
        List<BwUserCharacterVO> bumdList = null;
        if (null == o) {//从数据库获取用户地图信息
            //以mail地址为key查询用户所有的建筑信息
            List<Long> bumdListTemp = bwUserCharacterDaoImpl.queryBwUserCharacterVOIds(bwusercharactervo);
            if (bumdListTemp != null) {
                List<Long> listvalue = new ArrayList<Long>();
                bumdList = new ArrayList<BwUserCharacterVO>();
                for (long uniq_id : bumdListTemp) {
                    long part_key = uniq_id;
                    String keySingle = key_prefix_single + bwusercharactervo.getMailaddress() + "_" + part_key;
                    Object srcO = this.getCache().get(keySingle);
                    if (null != srcO) {//如果缓存立面有就利用缓存立面的值
                        bumdList.add((BwUserCharacterVO) srcO);
                    } else {
                        //再从数据库中取值
                        bwusercharactervo.setChararchterid((int) uniq_id);
                        BwUserCharacterVO tempVo = bwUserCharacterDaoImpl.queryBwUserCharacterVOById(bwusercharactervo);
                        if (null != tempVo) {
                            this.getCache().put(keySingle, tempVo);
                            bumdList.add(tempVo);
                        }

                    }
                    listvalue.add(part_key);

                }
                if (listvalue.size() > 0) {
                    this.getCache().put(mult_key, IocUtils.removeRepeatValue(listvalue));
                }
                listvalue = null;
                bumdListTemp = null;
            }

        } else {
            List<Long> subkeyStrList = (ArrayList<Long>) o;
            bumdList = new ArrayList<BwUserCharacterVO>();
            for (long str : subkeyStrList) {//获取单条记录的数据
                String keySingle = key_prefix_single + bwusercharactervo.getMailaddress() + "_" + str;
                Object oSingle = this.getCache().get(keySingle);
                if (null == oSingle) {//从数据库获取
                    long tempid = str;
                    bwusercharactervo.setChararchterid((int) tempid);
                    BwUserCharacterVO tempVo = bwUserCharacterDaoImpl.queryBwUserCharacterVOById(bwusercharactervo);
                    if (null != tempVo) {
                        this.getCache().put(keySingle, tempVo);
                        bumdList.add(tempVo);
                    } else {
                        logger.error("数据库无数据,确认是否产生逻辑错误mailAddress:" + bwusercharactervo.getMailaddress() + "_" + bwusercharactervo.getChararchterid());
                    }

                } else {
                    bumdList.add((BwUserCharacterVO) oSingle);
                }

            }

        }

        //再判断单主键
        return bumdList;
        //11
        //return bwUserCharacterDaoImpl.queryBwUserCharacterVO(bwusercharactervo);
    }

    public BwUserCharacterDAO getBwUserCharacterDaoImpl() {
        return bwUserCharacterDaoImpl;
    }

    public void setBwUserCharacterDaoImpl(
            BwUserCharacterDAO bwUserCharacterDaoImpl) {
        this.bwUserCharacterDaoImpl = bwUserCharacterDaoImpl;
    }

    @Override
    public void batchUpdate(List<BwUserCharacterVO> listtime1)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

}
