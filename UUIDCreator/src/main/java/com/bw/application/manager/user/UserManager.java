package com.bw.application.manager.user;

import com.bw.application.exception.ManagerServerException;
import com.bw.cache.vo.BwPlantUserVO;
import com.bw.cache.vo.BwUserVO;
import com.commonSocket.net.exception.OperationFailedException;

public interface UserManager {

    /**
     * @param uservo
     * @return
     * @throws OperationFailedException 判断该用户是否存在 根据邮箱地址
     */
    public int isExistForUser(BwPlantUserVO bwPlantUserVO) throws ManagerServerException;

    public BwUserVO getUserVOByMailAddress(BwUserVO uservo) throws ManagerServerException;

    public int UserRegister(BwPlantUserVO bwPlantUserVO) throws ManagerServerException;

    /**
     * @param uservo
     * @throws ManagerServerExceptio 初始化用户信息 包括 用户、大厅,数目,石头的基本信息
     */
    public void initPlantUserInfor(BwPlantUserVO uservo) throws ManagerServerException;

    public void initUserInfor(BwUserVO uservo) throws ManagerServerException;

    public int initAndLoginForThirdParty(BwPlantUserVO uservo) throws ManagerServerException;

}
