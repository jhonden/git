package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.ProductRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends BaseRepository<Product> implements ProductRepository{
	static final String table = " tbl_sh_products ";
	static final String findAllSql = "select id as id,typeid as typeid, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description,icon as icon,sell_price as sellPrice,pcount as pcount from tbl_sh_products";
	static final String addSql = "insert into tbl_sh_products(typeid,name,description,create_date ,icon,sell_price ,pcount,buy_price) values(:typeid,:name,:description,datetime('now'),:icon,:sellPrice,:pcount,:buyPrice)";
	static final String deleteSql = "delete from tbl_sh_products where id = :id";
	static final String updateSql = "update tbl_sh_products set typeid =:typeid, name = :name, description =:description,icon =:icon,sell_price = :sellPrice where id = :id";
	static final String loadSql = "select id as id,typeid as typeid, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description,icon as icon,sell_price as sellPrice,pcount as pcount ,buy_price as buyPrice from tbl_sh_products where id = :id";
	static final String SQL_UPDATE_COUNT_AND_PRICE = "update tbl_sh_products set pcount = :pcount, buy_price = :buyPrice where id = :id";
	static final String leftCount = "select pcount from tbl_sh_products where id = :id";
	static final String updateCount = "update tbl_sh_products set pcount = :pcount where id = :id";
	
	@Override
	public int updateCountAndPrice(Product product) {
		return jdbcTemplate.update(SQL_UPDATE_COUNT_AND_PRICE, new BeanPropertySqlParameterSource(product));
	}
	
	@Override
	public int leftCount(Product product) {
		return jdbcTemplate.queryForObject(leftCount, new BeanPropertySqlParameterSource(product),Integer.class);
	}
	
	@Override
	public int updateCount(Product product) {
		return jdbcTemplate.update(updateCount, new BeanPropertySqlParameterSource(product));
	}
	
	public List<Product> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	
	public void add(Product product){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(product));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder(" and p.typeid = t.id ");
		page.appendEq(where, "p.typeid")
			.appendLike(where, "p.name");
		String pageSql = _genPageSql(" tbl_sh_products p, tbl_sh_ptype t ", 
				                        new String[]{"p.id as id, p.typeid as typeid,t.name as typeName, p.name as name, strftime('%Y-%m-%d',p.create_date) as createDateStr, p.description as description,p.icon as icon,p.sell_price as sellPrice,p.pcount as pcount, buy_price as buyPrice"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(" tbl_sh_products p, tbl_sh_ptype t ", where.toString()), pageSql, page, new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	@Override
	public int delete(Product product) {
		return jdbcTemplate.update(deleteSql, new BeanPropertySqlParameterSource(product));
	}

	@Override
	public int update(Product product) {
		return jdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(product));
	}
	
	@Override
	public Product load(Product product) {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(product), new BeanPropertyRowMapper<Product>(Product.class));
	}
}
