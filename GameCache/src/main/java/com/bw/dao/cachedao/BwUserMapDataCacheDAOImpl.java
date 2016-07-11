package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bw.cache.utils.IocUtils;
import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.dao.BwUserMapDataDAO;
import com.bw.exception.CacheDaoException;

public class BwUserMapDataCacheDAOImpl extends CacheDao implements
        BwUserMapDataDAO {

    private static Logger logger = Logger.getLogger(BwUserMapDataCacheDAOImpl.class);
    public BwUserMapDataDAO bwUserMapDataDaoImpl;
    //单一主键
    String key_prefix_single = "bw_user_map_data_single_";
    //多主键
    String key_prefix_mutiable = "bw_user_map_data_mutiable_";
    //单一主键 
    String key_prefix_single_build_id = "bw_user_map_data_";
    //多主键
    String key_prefix_mutiable_build_id_mail = "bw_user_map_data_mail_mutiable_";

    public void delete(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {
        String part_key = bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getUniquenessbuildid() + "_" + bwusermapdatavo.getBuildid();
        String keySingle = key_prefix_single + part_key;
        if (this.getCache().get(keySingle) != null) {
            bwUserMapDataDaoImpl.delete(bwusermapdatavo);
            this.getCache().remove(keySingle);
        }

    }

    @Override
    public BwUserMapDataVO queryBwUserMapDataVOById(
            BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {
        String part_key = bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getUniquenessbuildid() + "_" + bwusermapdatavo.getBuildid();
        String keySingle = key_prefix_single + part_key;
        Object o = this.getCache().get(keySingle);
//		o=null;
        if (null != o) {
            return (BwUserMapDataVO) o;
        } else {
            BwUserMapDataVO tempVo = bwUserMapDataDaoImpl.queryBwUserMapDataVOById(bwusermapdatavo);
            if (null != tempVo) {
                this.getCache().put(keySingle, tempVo);
                return tempVo;
            }
        }
        return null;
    }

    @Override
    public long queryBwUserMapDataVOCount(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {

        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Long> queryBwUserMapDataVOIds(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {//
        String keyMultiableID = key_prefix_mutiable_build_id_mail + bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getBuildid();
        Object o = this.getCache().get(keyMultiableID);
        if (null == o) {
            List<Long> list = bwUserMapDataDaoImpl.queryBwUserMapDataVOIds(bwusermapdatavo);
            if (list != null && list.size() > 0) {
                this.getCache().put(keyMultiableID, list);
                return list;
            } else {
                return null;
            }
        } else {
            return (List<Long>) o;
        }
//		return bwUserMapDataDaoImpl.queryBwUserMapDataVOIds(bwusermapdatavo);
    }

    @Override
    public void save(BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {

        //先放入数据库
        bwUserMapDataDaoImpl.save(bwusermapdatavo);
        String part_key_save = bwusermapdatavo.getUniquenessbuildid() + "_" + bwusermapdatavo.getBuildid();
        String keySingle_save = key_prefix_single + bwusermapdatavo.getMailaddress() + "_" + part_key_save;
        this.getCache().put(keySingle_save, bwusermapdatavo);
        //先判断list是否存在
        String keyMutiable = key_prefix_mutiable + "_" + bwusermapdatavo.getMailaddress();
        Object o = this.getCache().get(keyMutiable);
        List<String> listvalue = new ArrayList<String>();
        if (null == o) {//从数据库获取用户地图信息
            //以mail地址为key查询用户所有的建筑信息
            List<BwUserMapDataVO> bumdListTemp = bwUserMapDataDaoImpl.queryBwUserMapDataVOIdsForUUID(bwusermapdatavo);
            if (bumdListTemp != null) {
                for (BwUserMapDataVO uniq_id : bumdListTemp) {
//						if(uniq_id==bwusermapdatavo.getUniquenessbuildid()){
//							continue;
//						}
                    String part_key = uniq_id.getUniquenessbuildid() + "_" + uniq_id.getBuildid();
                    listvalue.add(part_key);
                }
                bumdListTemp = null;
            } else {
                listvalue.add(part_key_save);
            }

        } else {
            listvalue = (List<String>) o;
            listvalue.add(part_key_save);
        }
        this.getCache().put(keyMutiable, IocUtils.removeDuplicateWithOrderStr(listvalue));
        listvalue = null;
        //增加uuid数量
        String keyMultiableID = key_prefix_mutiable_build_id_mail + bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getBuildid();
        Object uuidlist = this.getCache().get(keyMultiableID);
        if (null != uuidlist) {
            List<Long> l = (List<Long>) uuidlist;
            l.add(bwusermapdatavo.getUniquenessbuildid());
            this.getCache().put(keyMultiableID, l);
        }
    }

    @Override
    public void update(BwUserMapDataVO bwusermapdatavo)
            throws CacheDaoException {
//		    bwUserMapDataDaoImpl.update(bwusermapdatavo);
        String part_key = bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getUniquenessbuildid() + "_" + bwusermapdatavo.getBuildid();
        String keySingle = key_prefix_single + part_key;
        this.getCache().put(keySingle, bwusermapdatavo);

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BwUserMapDataVO> queryBwUserMapDataVO(
            BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {
        //String keySingle=key_prefix_single+bwusermapdatavo.getMailaddress()+"_"+bwusermapdatavo.getUniquenessbuildid();
        String keyMutiable = key_prefix_mutiable + "_" + bwusermapdatavo.getMailaddress();
        //先判断多主键 if no 查询数据库 set list 再根据每一条从缓存读取，有add NO query put add
        Object o = this.getCache().get(keyMutiable);
        List<BwUserMapDataVO> bumdList = null;
        if (null == o) {//从数据库获取用户地图信息
            //以mail地址为key查询用户所有的建筑信息
            List<BwUserMapDataVO> bumdListTemp = bwUserMapDataDaoImpl.queryBwUserMapDataVOIdsForUUID(bwusermapdatavo);
            if (bumdListTemp != null && bumdListTemp.size() > 0) {
                List<String> listvalue = new ArrayList<String>();
                bumdList = new ArrayList<BwUserMapDataVO>();
                for (BwUserMapDataVO uniq_id : bumdListTemp) {
                    String part_key = uniq_id.getUniquenessbuildid() + "_" + uniq_id.getBuildid();
                    String keySingle = key_prefix_single + bwusermapdatavo.getMailaddress() + "_" + part_key;
                    Object srcO = this.getCache().get(keySingle);
                    if (null != srcO) {//如果缓存立面有就利用缓存立面的值
                        bumdList.add((BwUserMapDataVO) srcO);
                    } else {
                        //再从数据库中取值
                        bwusermapdatavo.setUniquenessbuildid(uniq_id.getUniquenessbuildid());
                        bwusermapdatavo.setBuildid(uniq_id.getBuildid());
                        BwUserMapDataVO tempVo = bwUserMapDataDaoImpl.queryBwUserMapDataVOById(bwusermapdatavo);
                        if (null != tempVo) {
                            this.getCache().put(keySingle, tempVo);
                            bumdList.add(tempVo);
                        }
                    }
                    listvalue.add(part_key);
                }
                if (listvalue.size() > 0) {
                    this.getCache().put(keyMutiable, IocUtils.removeDuplicateWithOrderStr(listvalue));
                }
                listvalue = null;
                bumdListTemp = null;
            }

        } else {
            List<String> subkeyStrList = (ArrayList<String>) o;
            bumdList = new ArrayList<BwUserMapDataVO>();
            for (String str : subkeyStrList) {//获取单条记录的数据
                String keySingle = key_prefix_single + bwusermapdatavo.getMailaddress() + "_" + str;
                Object oSingle = this.getCache().get(keySingle);
                if (null == oSingle) {//从数据库获取
                    String buildId = str.substring(str.lastIndexOf("_") + 1, str.length());
                    long uniqueId = Long.parseLong(str.substring(0, str.indexOf("_")));
                    bwusermapdatavo.setUniquenessbuildid(uniqueId);
                    bwusermapdatavo.setBuildid(Integer.parseInt(buildId));
                    BwUserMapDataVO tempVo = bwUserMapDataDaoImpl.queryBwUserMapDataVOById(bwusermapdatavo);
                    if (null != tempVo) {
                        this.getCache().put(keySingle, tempVo);
                        bumdList.add(tempVo);
                    } else {
                        logger.error("数据库无数据,确认是否产生逻辑错误mailAddress:" + bwusermapdatavo.getMailaddress() + "_" + bwusermapdatavo.getUniquenessbuildid());
                    }

                } else {
                    bumdList.add((BwUserMapDataVO) oSingle);
                }

            }

        }

        //再判断单主键
        return bumdList;
    }

    public BwUserMapDataDAO getBwUserMapDataDaoImpl() {
        return bwUserMapDataDaoImpl;
    }

    public void setBwUserMapDataDaoImpl(BwUserMapDataDAO bwUserMapDataDaoImpl) {
        this.bwUserMapDataDaoImpl = bwUserMapDataDaoImpl;
    }

    @Override
    public int queryUserBuildCount(String mailAddress, int buildId)
            throws CacheDaoException {
        String key = key_prefix_single_build_id + mailAddress + "_" + buildId;
        Object o = this.getCache().get(key);
        if (null == o) {
            int temp = bwUserMapDataDaoImpl.queryUserBuildCount(mailAddress, buildId);
            this.getCache().put(key, temp);
            return temp;
        } else {
            return (Integer) o;
        }
    }

    @Override
    public void updateUserBuildCount(String mailAddress, int buildId,
            int buildCount) throws CacheDaoException {

        String key = key_prefix_single_build_id + mailAddress + "_" + buildId;
        this.getCache().put(key, buildCount);
    }

    @Override
    public List<BwUserMapDataVO> queryBwUserMapDataVOIdsForUUID(
            BwUserMapDataVO bwusermapdatavo) throws CacheDaoException {
        return null;
    }

    @Override
    public void batchUpdate(List<BwUserMapDataVO> listtime1)
            throws CacheDaoException {

    }
}
