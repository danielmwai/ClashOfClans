package com.bw.looters.ms.web.vo;

/**
 * 所有返回数据的基类
 *
 * @author zhYou
 */
public class Res {

	private int code;
	private String msg;

	public Res() {
	}

	public Res(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
