package com.bw.looters.ms.ex;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常参数定义
 *
 * @author zhYou
 */
public enum BizEx {
	EX_NOT_FOUND(1, "未定义该异常"), USER_NOT_FOUND(2, "该用户不存在"), PASSWORD_NOT_MATCH(3, "密码不匹配");

	private BizEx(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private static Map<Integer, BizEx> bizErrorMap = new HashMap<Integer, BizEx>(BizEx.values().length);

	static {
		for (BizEx be : BizEx.values()) {
			bizErrorMap.put(be.getCode(), be);
		}
	}

	public static BizEx valueOf(int code) {
		BizEx be = bizErrorMap.get(code);
		if (be == null) {
			throw new BizException(BizEx.EX_NOT_FOUND);
		}
		return be;
	}

	private int code; // 错误码
	private String msg; // 消息

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
