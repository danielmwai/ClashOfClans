package com.bw.dao.cachedao;

import java.util.List;

import com.bw.cache.utils.NullObject;
import com.bw.cache.vo.CGUserVO;
import com.bw.dao.CGUserDAO;
import com.bw.exception.CacheDaoException;

public class CGUserCacheDaoImpl extends CacheDao implements CGUserDAO {

    private static final String KEY = "cg.user.";

    private String channelKey;

    private CGUserDAO userDAO;

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public void setUserDAO(CGUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 注册用户资料
     */
    @Override
    public void register(CGUserVO cgUserVO) throws CacheDaoException {

        userDAO.register(cgUserVO);

        initUserInfo(cgUserVO);

        String key = KEY + cgUserVO.getMailAddress();

        cache.put(key, cgUserVO);

    }

    /**
     * 初始化用户信息
     */
    @Override
    public void initUserInfo(CGUserVO cgUserVO) throws CacheDaoException {

        userDAO.initUserInfo(cgUserVO);

    }

    /**
     * 更新用户资料
     */
    @Override
    public void updateUser(CGUserVO cgUserVO) throws CacheDaoException {

        // userDAO.updateUser(cgUserVO);
        String key = KEY + cgUserVO.getMailAddress();

        cache.put(key, cgUserVO);

    }

    /**
     * 更新用户信息
     */
    @Override
    public void refreshUserInfo(CGUserVO cgUserVO) {

        // userDAO.refreshUserInfo(cgUserVO);
        String key = KEY + cgUserVO.getMailAddress();

        cache.put(key, cgUserVO);

    }

    /**
     * 清空用户信息
     */
    @Override
    public void removeUserInfo(CGUserVO cgUserVO) {

        userDAO.removeUserInfo(cgUserVO);

        String key = KEY + cgUserVO.getMailAddress();

        cache.remove(key);
    }

    /**
     * 清空用户资料
     */
    @Override
    public void removeUser(CGUserVO cgUserVO) throws CacheDaoException {

        userDAO.removeUser(cgUserVO);

        String key = KEY + cgUserVO.getMailAddress();

        cache.remove(key);
    }

    @Override
    public CGUserVO queryCGUserVOByMailAddress(CGUserVO cgUserVO) throws CacheDaoException {

        String key = KEY + cgUserVO.getMailAddress();

        Object o = cache.get(key);

        if (null == o) {

            CGUserVO uservo = userDAO.queryCGUserVOByMailAddress(cgUserVO);

            if (uservo != null) {

                cache.put(key, uservo);
            }
            return uservo;
        } else {
            return (CGUserVO) o;
        }
    }

    @Override
    public CGUserVO queryCGUserVOById(CGUserVO cgUserVO) throws CacheDaoException {

        String key = KEY + cgUserVO.getMailAddress();

        Object object = cache.get(key);

        if (object == null || object instanceof NullObject) {
            CGUserVO voById = userDAO.queryCGUserVOById(cgUserVO);
            if (voById != null) {

                this.getCache().put(key, voById);
                return voById;
            }
        } else {
            return (CGUserVO) object;
        }

        return null;
    }

    @Override
    public List<CGUserVO> queryCGUserVO(CGUserVO cgUserVO) throws CacheDaoException {

        return null;
    }

    @Override
    public long queryCGUserVOCount(CGUserVO cgUserVO) throws CacheDaoException {
        return 0;
    }

    /**
     * 昵称模糊查找
     */
    @Override
    public List<String> queryCGUserVOByNickName(CGUserVO cgUserVO) throws CacheDaoException {

        String key = KEY + "nick." + cgUserVO.getNickName();

        Object object = cache.get(key);

        List<String> list;

        if (object == null) {

            list = userDAO.queryCGUserVOByNickName(cgUserVO);

            cache.put(key, list, 600);

        } else {

            list = (List<String>) object;

        }

        return list;
    }

    /**
     * 随机推荐
     */
    public List<String> randomRecommend(String mailAddress) {

        // CGUserVO vo = new CGUserVO();
        //
        //
        //
        //
        // vo.setCmIslandDataId(6 + 1);
        // vo.setExp(new Random().nextLong(184100));
        // vo.setGoldenCount(new Random().nextInt(5524));
        // vo.setImageName("yunfeifeifeifei");
        // vo.setLevels(new Random().nextInt(100));
        // vo.setLocalArea("ch");
        // vo.setMachineNum("dsfsjdoifwej");
        // vo.setMailAddress("123@1.com");
        // vo.setNickName("feifeifei");
        // vo.setPraisesCount(new Random().nextInt(2316341));
        // vo.setScreenHeight(new Random().nextInt(900));
        // vo.setScreenWidth(new Random().nextInt(800));
        // vo.setSex(new Random().nextInt(2));
        //
        // userDAO.register(vo);
        // userDAO.initUserInfo(vo);
        // for (int i = 0; i < 50; i++) {
        // vo.setCmIslandDataId(i + 1);
        // vo.setExp(new Random().nextLong());
        // vo.setGoldenCount(new Random().nextInt(5524));
        // vo.setImageName("abc");
        // vo.setLevels(new Random().nextInt(100));
        // vo.setLocalArea("ch");
        // vo.setMachineNum("sdaoijbosdi");
        // vo.setMailAddress(UUID.randomUUID().toString() + "@164.com");
        // vo.setNickName("gaofushuai");
        // vo.setPraisesCount(new Random().nextInt(2316341));
        // vo.setScreenHeight(new Random().nextInt(900));
        // vo.setScreenWidth(new Random().nextInt(800));
        // vo.setSex(new Random().nextInt(2));
        //
        // userDAO.register(vo);
        // userDAO.initUserInfo(vo);
        // }
        String key = channelKey + ".user";

        Object object = cache.get(key);

        List<String> list = null;

        if (object != null) {

            list = (List<String>) object;

        } else {

            list = userDAO.randomRecommend(mailAddress);

        }

        return list;

    }

    @Override
    public void refreshPraises(CGUserVO cgUserVO) {

        // userDAO.refreshPraises(cgUserVO);
        String key = KEY + cgUserVO.getMailAddress();

        cache.put(key, cgUserVO);

    }

}
