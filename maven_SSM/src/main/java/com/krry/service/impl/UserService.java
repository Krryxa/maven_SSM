package com.krry.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krry.entity.User;
import com.krry.mapper.UserMapper;
import com.krry.service.IUserService;

/**
 * 实现service层接口
 * @author asusaad
 *
 */
@Service
public class UserService implements IUserService{

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据用户名查询用户是否存在
	 */
	public User getLogin(String name) {
		User user = userMapper.getLogin(name);
		return user;
	}

	public User getpass(String name, String password) {
		User userpas = userMapper.getpass(name, password);
		return userpas;
	}

	public User getothernameres(String name) {
		User user = userMapper.getothernameres(name);
		return user;
	}

	public User getemailres(String email) {
		//根据账号查询，用户是否存在
		User user = userMapper.getemailres(email);
		return user;
	}

	public void saveUser(User user) {
		userMapper.saveUser(user);
		
	}

}
