package com.bw.looters.ms.storage.dao;

import com.bw.looters.ms.storage.dto.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author zhYou
 * @see com.bw.looters.ms.storage.dto.UserDto
 */
@Component
public interface UserDao {

	UserDto select(@Param("username") String username);

	int insert(UserDto dto);

	int update(UserDto dto);

	int delete(@Param("username") String username);
}
