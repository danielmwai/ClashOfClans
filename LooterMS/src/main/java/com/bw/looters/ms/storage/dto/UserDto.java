package com.bw.looters.ms.storage.dto;

/**
 * 管理平台用户信息
 *
 * @author zhYou
 */
public class UserDto {

	private String username; // 用户名
	private String password; // 密码

	public UserDto() {
	}

	public UserDto(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserDto");
		sb.append("{username='").append(username).append('\'');
		sb.append(", password='").append(password).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public String getUsername() {
		System.out.println("GETTING  username  FROM  DB \n\n\t " + username + "\n\n\t");

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		System.out.println("GETTING  PASSWORD  FROM  DB \n\n\t " + password + "\n\n\t");
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
