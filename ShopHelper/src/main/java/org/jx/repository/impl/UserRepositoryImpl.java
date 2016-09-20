package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.UserRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseRepository<User> implements UserRepository{
	static final String table = " tbl_sh_user ";
	static final String findAllSql = "select id as id, name as name, password as password,strftime('%Y-%m-%d',create_date) as createDateStr, mobile as mobile from tbl_sh_user";
	static final String addSql = "insert into tbl_sh_user(name,password,mobile,create_date) values(:name,:password,:mobile,datetime('now'))";
	static final String deleteSql = "delete from tbl_sh_user where id = :id";
	static final String updateSql = "update tbl_sh_user set password = :password, mobile = :mobile where id = :id";
	static final String loadSql = "select id as id, name as name, password as password,mobile as mobile,strftime('%Y-%m-%d',create_date) as createDateStr from tbl_sh_user where id = :id";
	static final String loginSql = "select id as id, name as name, password as password,mobile as mobile,strftime('%Y-%m-%d',create_date) as createDateStr from tbl_sh_user where name = :name and password = :password";
	
	public List<User> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<User>(User.class));
	}
	
	
	public void add(User user){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(user));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder();
		page.appendLike(where, "name");
		String pageSql = _genPageSql(table, 
				                        new String[]{"id as id, name as name, password as password,mobile as mobile,strftime('%Y-%m-%d',create_date) as createDateStr"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(table, where.toString()), pageSql, page, new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public int delete(User user) {
		return jdbcTemplate.update(deleteSql, new BeanPropertySqlParameterSource(user));
	}

	@Override
	public int update(User user) {
		return jdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(user));
	}
	
	@Override
	public User load(User user) {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(user), new BeanPropertyRowMapper<User>(User.class));
	}
	
	@Override
	public User login(User user) {
		try {
			return jdbcTemplate.queryForObject(loginSql, new BeanPropertySqlParameterSource(user), new BeanPropertyRowMapper<User>(User.class));
		} catch (DataAccessException e) {
			return null;
		}
	}
}
