package org.jx.service;

import java.util.List;
import java.util.Map;

import org.jx.repository.bean.CartIterm;
import org.jx.repository.bean.Product;
import org.jx.repository.bean.User;


public interface CartService {
	
	public List<CartIterm> findByUser(User user);
	
	public Map<String,Object> fingMyCart(User user);
	
	public String findIdByUser(User user);
	
	public String addProduct(Product cart, User user);

	public int deleteByUser(User user);
	
	public int deleteByProduct(Product product, User user);
	
	public String updatePcount(Product product, User user);

	public void cashMyCart(User user);
	
}
