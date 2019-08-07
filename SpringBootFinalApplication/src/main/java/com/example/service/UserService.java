package com.example.service;

import java.util.List;

import com.example.bean.User;


public interface UserService {
	public void createUser(User user);
	public List<User> getUser();
	public User findById(long id);
	public User update(User user, long l);
	public void deleteUserById(long id);
	public User updatePartially(long id);
	public void UpdateUserById(String userName,Long id);
	
	
}
