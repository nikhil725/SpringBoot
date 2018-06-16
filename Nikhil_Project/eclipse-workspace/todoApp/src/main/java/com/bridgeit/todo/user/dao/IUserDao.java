package com.bridgeit.todo.user.dao;

import java.util.List;

import com.bridgeit.todo.user.model.User;

public interface IUserDao {

	public int addUser(User user);
	public User validateUser(User user);
	public User getUserByEmaiId(String email);
	public User getUserById(int id);
	public User getUserByRandomId(String randomId);
	public User updateRecord(User user);
	List<User> checkEmailId(String emailId);
	User getuser(User user);
	
	
	
}
