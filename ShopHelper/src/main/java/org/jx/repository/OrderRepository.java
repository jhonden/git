package org.jx.repository;

import org.jx.repository.bean.Order;

public interface OrderRepository extends FindPage{
	public void add(Order order);
	
	public Order load(Order order);

}
