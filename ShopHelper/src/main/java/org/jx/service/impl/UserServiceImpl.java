package org.jx.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.UserRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.User;
import org.jx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
	private static Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
    UserRepository userRpo;

	public List<User> findAll(){
		return userRpo.findAll();
	}
	
	public String add(User user){
		try {
			userRpo.add(user);
			return "0";
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return "-1";
		}
	}
	
	public void findPages(Page page){
		userRpo.findByPage(page);
	}

	@Override
	public int delete(User user) {
		return userRpo.delete(user);
	}
	
	@Override
	public int update(User user) {
		return userRpo.update(user);
	}
	
	@Override
	public User load(User user) {
		return userRpo.load(user);
	}
	
	@Override
	public User login(User user) {
		return userRpo.login(user);
	}
}
