package com.example.MiniReddit.domain.api;

import java.time.LocalDateTime;

import com.example.MiniReddit.domain.user.User;
import com.example.MiniReddit.domain.user.UserDao;

public class UserService {
	private UserDao userDao = new UserDao();

	public void register(UserRegistration userRegistration) {
		User userToSave = UserMapper.map(userRegistration);
		userDao.save(userToSave);
	}

	private static class UserMapper {
		static User map(UserRegistration userRegistration) {
			return new User(
					userRegistration.getUsername(),
					userRegistration.getEmail(),
					userRegistration.getPassword(),
					LocalDateTime.now()
			);
		}
	}
}
