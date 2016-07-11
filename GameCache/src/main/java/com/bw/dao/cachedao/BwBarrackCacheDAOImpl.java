package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.List;

import com.bw.cache.utils.IocUtils;
import com.bw.cache.vo.BwBarrackVO;
import com.bw.dao.BwBarrackDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao
 *
 */
public class BwBarrackCacheDAOImpl extends CacheDao implements BwBarrackDAO {

    private BwBarrackDAO bwBarrackDaoImpl;
    //单一键
    String key_prefix_single = "bw_user_barrack_";
    //多主键
    String key_prefix_mutiable = "bw_user_character";
    //获取兵营所有的userMapId
    String key_prefix_userMapId = "bw_user_barrack_user_map_id_";

    public void delete(BwBarrackVO bwbarrackvo) throws CacheDaoException {
        bwBarrackDaoImpl.delete(bwbarrackvo);
        String key = key_prefix_single + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid() + "_" + bwbarrackvo.getUsercharacterid();
        this.getCache().remove(key);
        //移除multi
        //先判断list是否存在
        String mult_key = key_prefix_mutiable + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid();
        Object o = this.getCache().get(mult_key);
        List<Long> listvalue = new ArrayList<Long>();
        if (null == o) {//
            List<Long> cList = bwBarrackDaoImpl.queryBwBarrackVOForCharactarId(bwbarrackvo);
            if (cList != null && cList.size() > 0) {
                for (long chId : cList) {
                    listvalue.add(chId);
                }
            }
        } else {
            listvalue = (List<Long>) o;
            listvalue.remove(bwbarrackvo.getUsercharacterid());
            if (listvalue.size() > 0) {
                this.getCache().put(mult_key, IocUtils.removeRepeatValue(listvalue));
            }
        }
        listvalue.clear();
        listvalue = null;
    }

    @Override
    public BwBarrackVO queryBwBarrackVOById(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String key = key_prefix_single + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid() + "_" + bwbarrackvo.getUsercharacterid();
        Object o = this.getCache().get(key);

//		this.getCache().remove(key);
        if (o != null) {
            return (BwBarrackVO) o;
        } else {
            BwBarrackVO tempVO = bwBarrackDaoImpl.queryBwBarrackVOById(bwbarrackvo);
            if (null != tempVO) {
                this.getCache().put(key, tempVO);
                return tempVO;
            }
        }
        return null;
    }

    @Override
    public long queryBwBarrackVOCount(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwBarrackVOIds(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwBarrackVO bwbarrackvo) throws CacheDaoException {
        bwBarrackDaoImpl.save(bwbarrackvo);

        //String part_key_save=bwbarrackvo.getMailAddress()+"_"+bwbarrackvo.getUsercharacterid();
        String keySingle_save = key_prefix_single + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid() + "_" + bwbarrackvo.getUsercharacterid();
        this.getCache().put(keySingle_save, bwbarrackvo);

        System.out.println("From DB:" + bwbarrackvo.getId());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        //更新multi_key
        //先判断list是否存在
        String mult_key = key_prefix_mutiable + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid();
        Object o = this.getCache().get(mult_key);
        List<Long> listvalue = new ArrayList<Long>();
        if (null == o) {//
            List<Long> cList = bwBarrackDaoImpl.queryBwBarrackVOForCharactarId(bwbarrackvo);
            if (cList != null && cList.size() > 0) {
                for (long chId : cList) {
//						if(uniq_id==bwusermapdatavo.getUniquenessbuildid()){
//							continue;
//						}
                    listvalue.add(chId);
                }
            } else {
                listvalue.add((long) bwbarrackvo.getUsercharacterid());
            }

        } else {
            listvalue = (List<Long>) o;
            listvalue.add((long) bwbarrackvo.getUsercharacterid());
        }
        this.getCache().put(mult_key, IocUtils.removeRepeatValue(listvalue));
    }

    @Override
    public void update(BwBarrackVO bwbarrackvo) throws CacheDaoException {
        bwBarrackDaoImpl.update(bwbarrackvo);
        String keySingle = key_prefix_single + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid() + "_" + bwbarrackvo.getUsercharacterid();
        this.getCache().put(keySingle, bwbarrackvo);

    }

    @Override
    public List<BwBarrackVO> queryBwBarrackVO(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        return bwBarrackDaoImpl.queryBwBarrackVO(bwbarrackvo);
    }

    public BwBarrackDAO getBwBarrackDaoImpl() {
        return bwBarrackDaoImpl;
    }

    public void setBwBarrackDaoImpl(BwBarrackDAO bwBarrackDaoImpl) {
        this.bwBarrackDaoImpl = bwBarrackDaoImpl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> getAllBarrackUserMapId(String mailAddress)
            throws CacheDaoException {
        String key = key_prefix_userMapId + mailAddress;
        Object o = this.getCache().get(key);
        if (null != o) {
            return (List<Long>) this.getCache().get(key);
        } else {//从数据库查询 此处应当用不上
            BwBarrackVO bwbarrackvo = new BwBarrackVO();
            bwbarrackvo.setMailAddress(mailAddress);
            List<Long> temp = bwBarrackDaoImpl.queryBwBarrackVOIds(bwbarrackvo);
            if (null != temp && temp.size() > 0) {
                this.getCache().put(key, temp);

            }
            return temp;
        }
        //return	(List<Long>)this.getCache().get(key);
    }

    @Override
    public void putAllBarrackUserMapId(String mailAddress,
            List<Long> userMapIdList) throws CacheDaoException {
        String key = key_prefix_userMapId + mailAddress;
        this.getCache().put(key, userMapIdList);
    }

    @Override
    public void batchUpdate(List<BwBarrackVO> bwbarrackvolist)
            throws CacheDaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Long> queryBwBarrackVOForCharactarId(BwBarrackVO bwbarrackvo)
            throws CacheDaoException {
        String mult_key = key_prefix_mutiable + bwbarrackvo.getMailAddress() + "_" + bwbarrackvo.getUsermapdataid();
        Object o = this.getCache().get(mult_key);
        List<Long> resultList = null;
        if (null != o) {
            return (List<Long>) o;
        } else {//从数据库获取

            List<Long> cList = bwBarrackDaoImpl.queryBwBarrackVOForCharactarId(bwbarrackvo);

            if (null != cList && cList.size() > 0) {
                resultList = new ArrayList<Long>();
                for (Long charactarId : cList) {
                    resultList.add(charactarId);
                }
                if (resultList.size() > 0) {
                    this.getCache().put(mult_key, resultList);
                }
            }
        }
        return resultList;
    }

}
