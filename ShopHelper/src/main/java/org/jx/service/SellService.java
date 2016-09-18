package org.jx.service;

import java.util.List;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;


public interface SellService {
	
	public List<Product> findAll();
	
	public String add(Product ptype);
	
	public void findPages(Page page);
	
}
