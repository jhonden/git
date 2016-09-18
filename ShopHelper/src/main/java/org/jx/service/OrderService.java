package org.jx.service;

import org.jx.repository.bean.Order;
import org.jx.repository.bean.Page;


public interface OrderService {
	
	public void findPages(Page page);
	
	public Order load(Order order);
	
	public String add(Order order);
}
