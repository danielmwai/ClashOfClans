package com.bw.looters.ms.biz.facade;

import com.bw.looters.ms.biz.model.User;
import com.bw.looters.ms.biz.repo.UserRepo;
import com.bw.looters.ms.ex.BizEx;
import com.bw.looters.ms.ex.BizException;
import com.bw.looters.ms.web.vo.Res;
import com.bw.looters.ms.web.vo.ResUtil;
import com.bw.looters.ms.web.vo.req.LoginReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhYou
 */
@Component
public class MsFacade {

	public Res userLogin(LoginReq req) {
		User user = userRepo.getUser(req.getUsername());
		if (user == null) {
			throw new BizException(BizEx.USER_NOT_FOUND, req.getUsername());
		}
		if (!user.getPassword().equals(req.getPassword())) {
			throw new BizException(BizEx.PASSWORD_NOT_MATCH, req.getUsername());
		}
		return ResUtil.SUCCESS;
	}

	@Resource
	private UserRepo userRepo;
}
