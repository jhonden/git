package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.BuyLogRepository;
import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class BuyLogRepositoryImpl extends BaseRepository<BuyLog> implements BuyLogRepository{
	static final String findAllSql = "select l.id as id, u.id as userid,p.id as productid, strftime('%Y-%m-%d',l.create_date) as createDateStr, l.buy_count as buyCount, l.buy_price as buyPrice from tbl_sh_buy_log l, tbl_sh_user u, tbl_sh_products p where l.userid = u.id and l.productid = p.id";
	static final String addSql = "insert into tbl_sh_buy_log(userid,productid,create_date,buy_count,buy_price,remark) values(:userid,:productid,datetime('now'),:buyCount,:buyPrice,:remark)";
	static final String deleteSql = "delete from tbl_sh_buy_log where id = :id";
	static final String updateSql = "update tbl_sh_buy_log set typeid =:typeid, name = :name, description =:description,icon =:icon,sell_price = :sellPrice where id = :id";
	static final String loadSql = "select l.id as id, u.id as userid,p.id as productid, strftime('%Y-%m-%d',l.create_date) as createDateStr, l.buy_count as buyCount, l.buy_price as buyPrice from tbl_sh_buy_log l, tbl_sh_user u, tbl_sh_products p where l.id = :id and l.userid = u.id and l.productid = p.id";
	 
	public List<BuyLog> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<BuyLog>(BuyLog.class));
	}
	
	
	public void add(BuyLog buyLog){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(buyLog));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder(" and l.userid = u.id and l.productid = p.id ");
		page.appendEq(where, "u.id", "userid");
		page.appendLike(where, "p.name");
		
		String pageSql = _genPageSql(" tbl_sh_buy_log l, tbl_sh_user u, tbl_sh_products p ", 
				                        new String[]{"l.id as id, u.id as userid,u.name as uname,p.id as productid, p.name as pname, strftime('%Y-%m-%d',l.create_date) as createDateStr, l.buy_count as buyCount, l.buy_price as buyPrice, l.remark as remark"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(" tbl_sh_buy_log l, tbl_sh_user u, tbl_sh_products p ", where.toString()), pageSql, page, new BeanPropertyRowMapper<BuyLog>(BuyLog.class));
	}
	
	@Override
	public int delete(BuyLog buyLog) {
		return jdbcTemplate.update(deleteSql, new BeanPropertySqlParameterSource(buyLog));
	}

	@Override
	public int update(BuyLog buyLog) {
		return jdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(buyLog));
	}
	
	@Override
	public BuyLog load(BuyLog buyLog) {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(buyLog), new BeanPropertyRowMapper<BuyLog>(BuyLog.class));
	}
}
