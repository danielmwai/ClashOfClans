package com.bw.looters.ms.biz.repo;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.bw.looters.ms.biz.model.User;
import com.bw.looters.ms.storage.dao.UserDao;
import com.bw.looters.ms.storage.dto.UserDto;

/**
 * @author zhYou
 */
@Component
public class UserRepo {

	public User getUser(String username) {
		UserDto userDto = userDao.select(username);
		if (userDto == null) {
			return null;
		}
		return new User(userDto.getUsername(), userDto.getPassword());
	}

	@Resource
	private UserDao userDao;
}
