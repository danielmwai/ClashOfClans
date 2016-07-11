package com.bw.application.manager.user;

import com.bw.cache.vo.*;
import com.commonSocket.net.exception.*;

public interface UserManager {

    boolean isExistForUser(final CGUserVO p0) throws OperationFailedException;

    CGUserVO getUserVOByMailAddress(final CGUserVO p0) throws OperationFailedException;

    void UserRegister(final CGUserVO p0) throws OperationFailedException;
}
