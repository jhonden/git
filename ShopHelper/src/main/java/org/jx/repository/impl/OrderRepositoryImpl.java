package org.jx.repository.impl;

import org.jx.repository.OrderRepository;
import org.jx.repository.bean.Order;
import org.jx.repository.bean.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends BaseRepository<Order> implements OrderRepository{
	static final String addSql = "insert into tbl_sh_order(id,userid,pcount,create_date,sell_price) values(:id,:userid,:pcount,datetime('now'),:sellPrice)";
	static final String loadSql = "select o.id as id, o.userid as userid, u.name as uname, o.pcount as pcount, strftime('%Y-%m-%d',o.create_date) as createDateStr, o.sell_price as sellPrice from tbl_sh_order o, tbl_sh_user u where o.id = :id and o.userid = u.id ";
	 
	
	public void add(Order order){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(order));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder(" and o.userid = u.id ");
		page.appendLike(where, "u.name", "uname");
		
		String pageSql = _genPageSql(" tbl_sh_order o, tbl_sh_user u ", 
				                        new String[]{"o.id as id, o.userid as userid, u.name as uname, o.pcount as pcount, strftime('%Y-%m-%d',o.create_date) as createDateStr, o.sell_price as sellPrice"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(" tbl_sh_order o, tbl_sh_user u ", where.toString()), pageSql, page, new BeanPropertyRowMapper<Order>(Order.class));
	}
	
	@Override
	public Order load(Order order) {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(order), new BeanPropertyRowMapper<Order>(Order.class));
	}
}
