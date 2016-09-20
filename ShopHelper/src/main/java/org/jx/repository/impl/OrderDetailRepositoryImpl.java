package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.OrderDetailRepository;
import org.jx.repository.bean.Order;
import org.jx.repository.bean.OrderDetail;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailRepositoryImpl extends BaseRepository<OrderDetail> implements OrderDetailRepository{
	static final String findAllSql = "select od.order_id as orderId, od.product_id as productid, p.name as pname, od.pcount as pcount, strftime('%Y-%m-%d',od.create_date) as createDateStr, od.sell_price as sellPrice from tbl_sh_order_detail od , tbl_sh_products p where od.order_id = :id and od.productid = p.id";
	static final String addSql = "insert into tbl_sh_order_detail(order_id,product_id,pcount,create_date,sell_Price) values(:orderId,:productid,:pcount,datetime('now'),:sellPrice)";
	 
	public List<OrderDetail> findAll(Order order) {
		return jdbcTemplate.query(findAllSql, new BeanPropertySqlParameterSource(order), new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class));
	}
	
	public void add(OrderDetail od){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(od));
	}
	
	
}
