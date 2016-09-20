package org.jx.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.OrderDetailRepository;
import org.jx.repository.OrderRepository;
import org.jx.repository.bean.Order;
import org.jx.repository.bean.OrderDetail;
import org.jx.repository.bean.Page;
import org.jx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderServiceImpl implements OrderService{
	private static Log log = LogFactory.getLog(OrderServiceImpl.class);
	
	@Autowired
    OrderRepository orderRpo;
	@Autowired
	OrderDetailRepository odRpo;

	@Transactional
	public String add(Order order){
		try {
			//添加订单概要
			orderRpo.add(order);
			//添加订单明细
			for(OrderDetail od : order.getOds())
				odRpo.add(od);
			return "0";
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return "-1";
		}
	}
	
	public void findPages(Page page){
		orderRpo.findByPage(page);
	}

	@Override
	public Order load(Order order) {
		//查询概要
		order = orderRpo.load(order);
		//查询明细
		order.setOds(odRpo.findAll(order));
		return order;
	}
	
}
