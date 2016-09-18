package org.jx.service;

import java.util.List;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.User;


public interface UserService {
	
	public List<User> findAll();
	
	public String add(User user);
	
	public void findPages(Page page);

	public int delete(User user);
	
	public int update(User user);
	
	public User load(User user);

	public User login(User user);
}
