package com.bw.looters.ms.web.ctrl;

import com.bw.looters.ms.ex.BizException;
import com.bw.looters.ms.web.vo.Res;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author zhYou
 */
@Controller
public class BizExCtrl {

	@ExceptionHandler(BizException.class)
	public Res handleBizEx(BizException ex) {
		Res res = new Res();
		res.setCode(ex.getBizEx().getCode());
		return res;
	}
}
