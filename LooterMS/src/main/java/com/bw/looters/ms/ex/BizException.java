package com.bw.looters.ms.ex;

import java.util.Arrays;

/**
 * @author zhYou
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -2013319941948022170L;
	private BizEx bizEx;
	private Object[] param;

	public BizException(BizEx be) {
		super(be.getCode() + "&" + be.getMsg());
		this.bizEx = be;
	}

	public BizException(BizEx be, Object... param) {
		super(be.getCode() + "&" + be.getMsg() + ":" + Arrays.toString(param));
		this.bizEx = be;
		this.param = param;
	}

	public BizEx getBizEx() {
		return bizEx;
	}

	public Object[] getParam() {
		return param;
	}
}
