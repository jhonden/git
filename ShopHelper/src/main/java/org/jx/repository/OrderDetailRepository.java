package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.Order;
import org.jx.repository.bean.OrderDetail;

public interface OrderDetailRepository {

	public List<OrderDetail> findAll(Order order);
	
	public void add(OrderDetail od);
}
