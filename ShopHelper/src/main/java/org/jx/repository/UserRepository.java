package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.User;

public interface UserRepository extends FindPage{
	

	public List<User> findAll();
	
	
	public void add(User ptype);


	public int delete(User ptype);
	
	public int update(User ptype);
	
	public User load(User ptype);


	public User login(User user);

}
