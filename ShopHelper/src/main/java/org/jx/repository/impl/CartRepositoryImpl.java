package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.CartRepository;
import org.jx.repository.bean.CartIterm;
import org.jx.repository.bean.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryImpl extends BaseRepository<CartIterm> implements CartRepository{
	static final String findByUser = "select c.id as id, c.userid as userid, c.productid as productid,p.name as pname, c.pcount as pcount, (p.sell_price * c.pcount) as sellPrice from tbl_sh_buy_car c, tbl_sh_products p where c.productid = p.id and c.userid = :id";
	static final String findIdByUser = "select c.id as id from tbl_sh_buy_car c  where c.userid = :id limit 1";
	static final String isProductExist = "select * from tbl_sh_buy_car c  where c.userid = :userid and productid =:productid";
	static final String addSql = "insert into tbl_sh_buy_car(id,userid,productid,pcount,create_date) values(:id,:userid,:productid,:pcount,datetime('now'))";
	static final String deleteByUser = "delete from tbl_sh_buy_car where userid = :id";
	static final String deleteProduct = "delete from tbl_sh_buy_car where userid = :userid and productid = :productid";
	static final String updatePcount = "update tbl_sh_buy_car set pcount = :pcount where userid = :userid and productid = :productid";

	@Override
	public List<CartIterm> findByUser(User user) {
		return jdbcTemplate.query(findByUser, new BeanPropertySqlParameterSource(user), new BeanPropertyRowMapper<CartIterm>(CartIterm.class));
	}
	
	@Override
	public CartIterm isProductExist(CartIterm cart) {
		try {
			return jdbcTemplate.queryForObject(isProductExist, new BeanPropertySqlParameterSource(cart), new BeanPropertyRowMapper<CartIterm>(CartIterm.class));
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	
	public String findIdByUser(User user){
		try {
			return jdbcTemplate.queryForObject(findIdByUser, new BeanPropertySqlParameterSource(user), String.class);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	public void add(CartIterm item){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(item));
	}
	
	public int deleteByUser(User user) {
		return jdbcTemplate.update(deleteByUser, new BeanPropertySqlParameterSource(user));
	}
	
	public int deleteProduct(CartIterm item) {
		return jdbcTemplate.update(deleteProduct, new BeanPropertySqlParameterSource(item));
	}
	
	@Override
	public int updatePcount(CartIterm item) {
		return jdbcTemplate.update(updatePcount, new BeanPropertySqlParameterSource(item));
	}
	
}
