package com.krry.entity;

import java.sql.Date;
import java.sql.Timestamp;


/**
 * 
 * User
 * @author krry
 * @version 1.0.0
 *
 */
public class User {

	 // 主键,自动递增
	 private String id;
	 // 用户名
	 private String username;
	 //	  密码
	 private String password;
	 //email
	 private String email;
	 //自动生成创建时间
	 private String createTime;

	 public User(String id,String username,String password,String email,String createTime) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createTime = createTime;
	}
	 
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	 
	 public String getEmail() {
		return email;
	 }

	 public void setEmail(String email) {
		this.email = email;
	 }
	 
	 public String getId() {
		 return id;
	 }
	
	 public void setId(String id) {
		 this.id = id;
	 }
	
	 public String getUsername() {
		 return username;
	 }
	
	 public void setUsername(String username) {
		 this.username = username;
	 }
	
	 public String getPassword() {
		 return password;
	 }
	
	 public void setPassword(String password) {
		 this.password = password;
	 }
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", createTime=" + createTime
				+ "]";
	}

	


}