package com.bw.dao;

import java.util.List;

import com.bw.exception.CacheDaoException;

/**
 * @author denny zhao
 *
 */
public interface CommonDAO {

    /**
     * @return @throws CacheDaoException 获取拒绝登陆列表
     */
    public List<String> getDenyLogin() throws CacheDaoException;

    /**
     * @param boweiId
     * @throws CacheDaoException 增加拒绝登陆用户
     */
    public void addDenyLogin(String boweiId) throws CacheDaoException;

    /**
     * @return @throws CacheDaoException 停服维护 0正常 2停服维护
     */
    public int getStopServerMaintain() throws CacheDaoException;

    /**
     * @throws CacheDaoException 设置停服维护 0正常 2停服维护
     */
    public void setStopServerMaintain(int maintainFlag) throws CacheDaoException;

    /**
     * @param key
     * @param fileContent
     * @throws CacheDaoException 文件key=defenceId+"_"+attackId+"_"+battleId
     */
    public void saveFileToCache(String key, byte[] fileContent) throws CacheDaoException;

    /**
     * @param key
     * @return
     * @throws CacheDaoException 查询文件内容
     */
    public byte[] getFileFromCache(String key) throws CacheDaoException;

    public boolean isExistFileFromCache(String key) throws CacheDaoException;

    /**
     * @param mailAddressList
     * @return
     * @throws CacheDaoException 保存用户列表信息
     */
    public boolean saveServerUserList(List<String> mailAddressList, String subKey) throws CacheDaoException;

    /**
     * @param key areaId_appId
     * @return
     * @throws CacheDaoException
     */
    public List<String> getServerUserList(String key) throws CacheDaoException;
}
