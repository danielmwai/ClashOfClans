package com.bw.looters.ms.biz.model;

/**
 * @author zhYou
 */
public class User {

	private String username;
	private String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * 验证密码是否有效
	 *
	 * @param password
	 *            需要验证的密码
	 * @return true, 密码有效, false密码无效
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
