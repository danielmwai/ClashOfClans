package com.bw.dao.cachedao;

import com.bw.cache.utils.IocUtils;
import com.bw.cache.vo.BwBlacklistVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.dao.BwBlacklistDAO;
import com.bw.exception.CacheDaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhYou
 */
public class BwBlacklistCacheDAOImpl extends CacheDao implements BwBlacklistDAO {
    //单一key

    private static final String key_prefix = "bw_blacklist_";
    //多个key
    private static final String key_prefix_multi = "bw_blacklist_multi_";

    @Override
    public long insert(BwBlacklistVO blacklist) throws CacheDaoException {
        long id = 0;
        id = bwBlacklistDaoImpl.insert(blacklist);
        blacklist.setId(id);
        String key = key_prefix + blacklist.getBoweiId();
        cache.put(key, blacklist);
//        111
        //先判断list是否存在
        String keyMutiable = key_prefix_multi;
        Object o = this.getCache().get(keyMutiable);
        List<String> listvalue = new ArrayList<String>();
        if (null == o) {//从数据库获取所有的拒绝登陆人员
            List<String> bumdListTemp = bwBlacklistDaoImpl.queryBoweiIdList();
            if (bumdListTemp != null && bumdListTemp.size() > 0) {
                for (String chId : bumdListTemp) {
                    listvalue.add(chId);
                }
                bumdListTemp.clear();
                bumdListTemp = null;
            } else {
                listvalue.add(blacklist.getBoweiId());
            }

        } else {
            listvalue = (List<String>) o;
            listvalue.add(blacklist.getBoweiId());
        }
        this.getCache().put(keyMutiable, IocUtils.removeDuplicateWithOrderStr(listvalue));
        listvalue.clear();
        listvalue = null;
        return id;
    }

    @Override
    public BwBlacklistVO select(String boweiId) throws CacheDaoException {
        String key = key_prefix + boweiId;
        BwBlacklistVO vo = (BwBlacklistVO) cache.get(key);
        if (vo == null) {
            vo = bwBlacklistDaoImpl.select(boweiId);
            if (null != vo) {
                cache.put(key, vo);
            }

        }
        return vo;
    }

    /**
     *
     * @return
     */
    @Override
    public List<BwBlacklistVO> selectAll() throws CacheDaoException {
//        return bwBlacklistDaoImpl.selectAll();
        String mult_key = key_prefix_multi;
        Object o = this.getCache().get(mult_key);
        List<BwBlacklistVO> bumdList = null;
        if (null == o) {//从数据库获取所有被拒绝登陆的用户
            List<String> bumdListTemp = bwBlacklistDaoImpl.queryBoweiIdList();
            if (bumdListTemp != null) {
                List<String> listvalue = new ArrayList<String>();
                bumdList = new ArrayList<BwBlacklistVO>();
                for (String bowei_id : bumdListTemp) {
                    String part_key = bowei_id;
                    String keySingle = key_prefix + bowei_id;
                    Object srcO = this.getCache().get(keySingle);
                    if (null != srcO) {//如果缓存立面有就利用缓存立面的值
                        bumdList.add((BwBlacklistVO) srcO);
                    } else {
                        //再从数据库中取值
                        BwBlacklistVO tempVo = bwBlacklistDaoImpl.select(bowei_id);
                        if (null != tempVo) {
                            this.getCache().put(keySingle, tempVo);
                            bumdList.add(tempVo);
                        }

                    }
                    listvalue.add(part_key);

                }
                if (listvalue.size() > 0) {
                    this.getCache().put(mult_key, IocUtils.removeDuplicateWithOrderStr(listvalue));
                }
                listvalue.clear();
                listvalue = null;
                bumdListTemp.clear();
                bumdListTemp = null;
            }

        } else {
            List<String> subkeyStrList = (ArrayList<String>) o;
            bumdList = new ArrayList<BwBlacklistVO>();
            for (String bowei_id : subkeyStrList) {//获取单条记录的数据
                String keySingle = key_prefix + bowei_id;
                Object oSingle = this.getCache().get(keySingle);
                if (null == oSingle) {//从数据库获取
                    String tempid = bowei_id;
                    BwBlacklistVO tempVo = bwBlacklistDaoImpl.select(bowei_id);
                    if (null != tempVo) {
                        this.getCache().put(keySingle, tempVo);
                        bumdList.add(tempVo);
                    }

                } else {
                    bumdList.add((BwBlacklistVO) oSingle);
                }

            }

        }

        //再判断单主键
        return bumdList;
    }

    @Override
    public void update(BwBlacklistVO vo) throws CacheDaoException {
        bwBlacklistDaoImpl.update(vo);

        String key = key_prefix + vo.getBoweiId();
        cache.put(key, vo);
    }

    @Override
    public void delete(String boweiId) throws CacheDaoException {
        String mult_key = key_prefix_multi;
        Object o = this.getCache().get(mult_key);
        List<String> bumdListTemp = null;

        if (null == o) {//从数据库获取所有被拒绝登陆的用户
            bumdListTemp = bwBlacklistDaoImpl.queryBoweiIdList();
            if (bumdListTemp != null && bumdListTemp.size() > 0) {
            }
        } else {
            bumdListTemp = (ArrayList<String>) o;
        }
        if (bumdListTemp != null && bumdListTemp.size() > 0) {
            for (int x = 0; x < bumdListTemp.size(); x++) {
                String bowei_Id = bumdListTemp.get(x);
                if (bowei_Id.equalsIgnoreCase(boweiId)) {
                    bumdListTemp.remove(x);
                }
            }
            if (bumdListTemp.size() > 0) {
                this.getCache().put(mult_key, bumdListTemp);
            }
        }

        String key = key_prefix + boweiId;
        cache.remove(key);
        bwBlacklistDaoImpl.delete(boweiId);
    }

    private BwBlacklistDAO bwBlacklistDaoImpl;

    public BwBlacklistDAO getBwBlacklistDaoImpl() {
        return bwBlacklistDaoImpl;
    }

    public void setBwBlacklistDaoImpl(BwBlacklistDAO bwBlacklistDaoImpl) {
        this.bwBlacklistDaoImpl = bwBlacklistDaoImpl;
    }

    @Override
    public List<String> queryBoweiIdList() throws CacheDaoException {
        return null;
    }
}
