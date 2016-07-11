package com.bw.dao.cachedao;

import java.util.ArrayList;
import java.util.List;

import com.bw.cache.utils.IocUtils;
import com.bw.dao.CommonDAO;
import com.bw.exception.CacheDaoException;

public class CommonCacheDAOImpl extends CacheDao implements CommonDAO {
    //拒绝登陆

    private String deny_login_key = "deny_login_key_";
    //停服维护
    private String stop_server_maintain = "stop_server_maintain_";
    //存储每个游戏服务器 在线用户列表
    private String Area_Server_user_list = "Area_Server_user_list_";

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getDenyLogin() throws CacheDaoException {
        String key = deny_login_key;
        Object o = this.getCache().get(key);
        if (null != o) {
            return (List<String>) o;
        }
        return null;
    }

    @Override
    public int getStopServerMaintain() throws CacheDaoException {
        // TODO Auto-generated method stub
        String key = stop_server_maintain;
        Object o = this.getCache().get(key);
        if (null != o) {
            return Integer.parseInt(o.toString());
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addDenyLogin(String boweiId) throws CacheDaoException {
        String key = deny_login_key;
        Object o = this.getCache().get(key);
        List<String> tempList = new ArrayList<String>();
        if (null != o) {
            tempList = (List<String>) o;
            tempList.add(boweiId);
            this.getCache().put(key, IocUtils.removeDuplicateWithOrderStr(tempList));
        }
        tempList.clear();
    }

    @Override
    public void setStopServerMaintain(int maintainFlag) throws CacheDaoException {
        String key = stop_server_maintain;
        this.getCache().put(key, maintainFlag);

    }

    @Override
    public void saveFileToCache(String key, byte[] fileContent)
            throws CacheDaoException {
        this.getCache().put(key, fileContent);

    }

    @Override
    public byte[] getFileFromCache(String key) throws CacheDaoException {
        Object o = this.getCache().get(key);
        if (null != o) {
            return (byte[]) o;
        }
        return null;
    }

    @Override
    public boolean isExistFileFromCache(String key) throws CacheDaoException {

        return this.getCache().keyExists(key);
    }

    @Override
    public boolean saveServerUserList(List<String> mailAddressList, String subKey)
            throws CacheDaoException {
        String key = Area_Server_user_list + subKey;
        if (null != mailAddressList && mailAddressList.size() > 0) {
            this.getCache().put(key, mailAddressList);
            return true;
        }

        return false;
    }

    @Override
    public List<String> getServerUserList(String subKey) throws CacheDaoException {
        String key = Area_Server_user_list + subKey;
        Object o = this.getCache().get(key);
        if (null != o) {
            return (List<String>) o;
        }
        return null;
    }

}
