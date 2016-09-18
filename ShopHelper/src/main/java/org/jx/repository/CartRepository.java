package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.CartIterm;
import org.jx.repository.bean.Product;
import org.jx.repository.bean.User;

public interface CartRepository{
	

	public List<CartIterm> findByUser(User user);
	
	public String findIdByUser(User user);
	
	public CartIterm isProductExist(CartIterm cart);
	
	public void add(CartIterm cart);

	public int deleteByUser(User user);
	
	public int deleteProduct(CartIterm cart);
	
	public int updatePcount(CartIterm cart);
	
}
