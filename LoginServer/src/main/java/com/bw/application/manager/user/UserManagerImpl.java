package com.bw.application.manager.user;

import com.bw.dao.*;
import com.bw.cache.vo.*;
import com.commonSocket.net.exception.*;

public class UserManagerImpl implements UserManager {

    private static final int INIT_ISLAND = 12;
    private static final int INIT_PRAISES = 50;
    private static final int INIT_LEVEL = 1;
    private static final int INIT_GOLD = 20;
    private static final int INIT_EXP = 50;
    private CGUserDAO cguserDAO;

    @Override
    public boolean isExistForUser(final CGUserVO uservo) throws OperationFailedException {
        final CGUserVO uservoTemp = this.cguserDAO.queryCGUserVOByMailAddress(uservo);
        return uservoTemp != null;
    }

    public CGUserDAO getCguserDAO() {
        return this.cguserDAO;
    }

    public void setCguserDAO(final CGUserDAO cguserDAO) {
        this.cguserDAO = cguserDAO;
    }

    @Override
    public CGUserVO getUserVOByMailAddress(final CGUserVO uservo) throws OperationFailedException {
        return this.cguserDAO.queryCGUserVOByMailAddress(uservo);
    }

    @Override
    public void UserRegister(final CGUserVO uservo) throws OperationFailedException {
        uservo.setCmIslandDataId(12);
        uservo.setExp(50L);
        uservo.setGoldenCount(20);
        uservo.setLevels(1);
        uservo.setPraisesCount(50);
        this.cguserDAO.register(uservo);
    }
}
