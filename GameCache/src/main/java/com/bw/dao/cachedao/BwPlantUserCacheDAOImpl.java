package com.bw.dao.cachedao;

import java.util.List;

import com.bw.baseJar.vo.NullObjectVO;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.dao.BwPlantUserDAO;
import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao 日期:2012-12-14 平台用户数据
 */
public class BwPlantUserCacheDAOImpl extends CacheDao implements BwPlantUserDAO {

    public BwPlantUserDAO bwPlantUserDaoImpl;
    private String singel_prefix_key_id = "bw_plant_user_id_";
    private String singel_prefix_key_mail = "bw_plant_user_mailAddress_";

    public void delete(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String key = singel_prefix_key_id + bwplantuservo.getBoweiid();
        String key_mailAddress = singel_prefix_key_mail + bwplantuservo.getMailaddress();
        bwPlantUserDaoImpl.delete(bwplantuservo);
        this.getCache().remove(key);
        this.getCache().remove(key_mailAddress);

    }

    @Override
    public BwPlantUserVO queryBwPlantUserVOById(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {
        String key = singel_prefix_key_id + bwplantuservo.getBoweiid();
        Object o = this.getCache().get(key);
        if (null != o && !(o instanceof NullObjectVO)) {
            BwPlantUserVO t = (BwPlantUserVO) o;
            return t;
        } else if (null == o) {//从数据库获取
            BwPlantUserVO t = bwPlantUserDaoImpl.queryBwPlantUserVOById(bwplantuservo);
            if (t != null) {
                this.getCache().put(key, t);
                return t;
            } else {
                this.getCache().put(key, new NullObjectVO());
                return null;
            }
        }
        return null;
    }

    @Override
    public long queryBwPlantUserVOCount(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {

        return 0;
    }

    @Override
    public List<Long> queryBwPlantUserVOIds(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {

        return null;

    }

    @Override
    public void save(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String key_id = singel_prefix_key_id + bwplantuservo.getBoweiid();
        bwPlantUserDaoImpl.save(bwplantuservo);
        String key_mailAddress = singel_prefix_key_mail + bwplantuservo.getMailaddress();
        this.getCache().put(key_mailAddress, bwplantuservo.getBoweiid());
        this.getCache().put(key_id, bwplantuservo);
    }

    @Override
    public void update(BwPlantUserVO bwplantuservo) throws CacheDaoException {
        bwPlantUserDaoImpl.update(bwplantuservo);
        String key = singel_prefix_key_id + bwplantuservo.getBoweiid();
        this.getCache().put(key, bwplantuservo);

    }

    @Override
    public List<BwPlantUserVO> queryBwPlantUserVO(BwPlantUserVO bwplantuservo)
            throws CacheDaoException {

        return null;
    }

    public BwPlantUserDAO getBwPlantUserDaoImpl() {
        return bwPlantUserDaoImpl;
    }

    public void setBwPlantUserDaoImpl(BwPlantUserDAO bwPlantUserDaoImpl) {
        this.bwPlantUserDaoImpl = bwPlantUserDaoImpl;
    }

    @Override
    public String queryBwPlantUserVOByMailAddress(
            BwPlantUserVO bwplantuservo) throws CacheDaoException {
        String key = singel_prefix_key_mail + bwplantuservo.getMailaddress();
        Object o = this.getCache().get(key);
        if (null != o && !(o instanceof NullObjectVO)) {
            return o.toString();
        } else if (null == o) {//从数据库获取
            String t = bwPlantUserDaoImpl.queryBwPlantUserVOByMailAddress(bwplantuservo);
            if (t != null) {
                this.getCache().put(key, t);
                return t;
            } else {
                this.getCache().put(key, new NullObjectVO());
                return null;
            }
        }
        return null;
    }

}
