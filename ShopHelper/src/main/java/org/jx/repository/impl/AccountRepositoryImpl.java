package org.jx.repository.impl;

import org.jx.repository.AccountRepository;
import org.jx.repository.bean.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl extends BaseRepository<Account> implements AccountRepository{
	static final String updateSql = "update tbl_sh_account set amount = :amount where id = :id";
	static final String loadSql = "select id as id, amount as amount from tbl_sh_account limit 1";
	

	@Override
	public void update(Account account) {
		 jdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(account));
	}
	
	@Override
	public Account load() {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(""), new BeanPropertyRowMapper<Account>(Account.class));
	}
}
