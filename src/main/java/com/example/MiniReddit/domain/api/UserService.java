package com.example.MiniReddit.domain.api;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

import org.apache.commons.codec.digest.DigestUtils;

import com.example.MiniReddit.domain.user.User;
import com.example.MiniReddit.domain.user.UserDao;

public class UserService {
	private UserDao userDao = new UserDao();

	public void register(UserRegistration userRegistration) {
		User userToSave = UserMapper.map(userRegistration);
		try {
			hashPasswordWithSha256(userToSave);
			userDao.save(userToSave);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private void hashPasswordWithSha256(User user) throws NoSuchAlgorithmException{
		String sha256Password = DigestUtils.sha3_256Hex(user.getPassword());
		user.setPassword(sha256Password);
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
