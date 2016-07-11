package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.utils.NullObject;
import com.bw.cache.vo.CGUserCurrencyVO;
import com.bw.dao.CGUserCurrencyDAO;
import com.bw.exception.CacheDaoException;

public class CGUserCurrencyCacheDaoImpl extends CacheDao implements CGUserCurrencyDAO {

    private static final String KEY = "cg.user.currency.";

    private CGUserCurrencyDAO currencyDAO;

    public void setCurrencyDAO(CGUserCurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    /**
     * 新建用户代币数据
     */
    @Override
    public void save(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {

        currencyDAO.save(cgUserCurrencyVO);

        String key = KEY + cgUserCurrencyVO.getMailAddress();

        cache.put(key, cgUserCurrencyVO);
    }

    /**
     * 更新用户代币数据
     */
    @Override
    public void update(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {

//		currencyDAO.update(cgUserCurrencyVO);
        String key = KEY + cgUserCurrencyVO.getMailAddress();

        cache.put(key, cgUserCurrencyVO);
    }

    /**
     * 删除用户代币数据
     */
    @Override
    public void delete(CGUserCurrencyVO cgUserCurrencyVO) throws CacheDaoException {

        //currencyDAO.delete(cgUserCurrencyVO);
        String key = KEY + cgUserCurrencyVO.getMailAddress();

        cache.remove(key);
    }

    /**
     * 查询用户代币数据
     */
    @Override
    public CGUserCurrencyVO queryCGUserCurrencyVOById(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {

        String subKey = KEY + cgUserCurrencyVO.getMailAddress();

        Object object = cache.get(subKey);

        if (object != null && !(object instanceof NullObject)) {
            return (CGUserCurrencyVO) object;
        } else {
            CGUserCurrencyVO vo = currencyDAO.queryCGUserCurrencyVOById(cgUserCurrencyVO);
            if (vo != null) {
                cache.put(subKey, vo);
            } else {
                cache.put(subKey, new NullObject());
            }
            return vo;
        }
    }

    /**
     * 根据条件查询用户代币数据
     */
    @Override
    public List<CGUserCurrencyVO> queryCGUserCurrencyVO(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        return currencyDAO.queryCGUserCurrencyVO(cgUserCurrencyVO);
    }

    /**
     *
     */
    @Override
    public List<Long> queryCGUserCurrencyVOIds(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        return null;
    }

    @Override
    public long queryCGUserCurrencyVOCount(CGUserCurrencyVO cgUserCurrencyVO)
            throws CacheDaoException {
        String key = KEY + "count";

        Object object = cache.get(key);

        if (object != null && !(object instanceof NullObject)) {
            return (Long) object;
        } else {
            Long count = currencyDAO.queryCGUserCurrencyVOCount(cgUserCurrencyVO);
            cache.put(key, count);
            return count;
        }
    }
}
