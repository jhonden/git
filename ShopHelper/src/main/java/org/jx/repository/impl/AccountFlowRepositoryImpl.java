package org.jx.repository.impl;

import org.jx.repository.AccountFlowRepository;
import org.jx.repository.bean.Account;
import org.jx.repository.bean.AccountFlow;
import org.jx.repository.bean.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AccountFlowRepositoryImpl extends BaseRepository<AccountFlow> implements AccountFlowRepository{
	static final String addSql = "insert into tbl_sh_account_flow(account_id,type,amount,create_date,remark) values(:accountId,:type,:amount,datetime('now'),:remark)";
	static final String todayAmountSql = "select sum(amount) as amount from tbl_sh_account_flow where strftime('%Y-%m-%d',create_date) = :createDateStr and type = :type";
	
	public void add(AccountFlow flow){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(flow));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder();
		page.appendEq(where, "type");
		page.appendLike(where, "remark");
		String pageSql = _genPageSql(" tbl_sh_account_flow ", 
				                        new String[]{"id as id, account_id as accountId, type as type , amount as amount, strftime('%Y-%m-%d',create_date) as createDateStr, remark as remark"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(" tbl_sh_account_flow ", where.toString()), pageSql, page, new BeanPropertyRowMapper<AccountFlow>(AccountFlow.class));
	}
	
	@Override
	public Account loadTodayAccount(AccountFlow aflow) {
		Account account = jdbcTemplate.queryForObject(todayAmountSql, new BeanPropertySqlParameterSource(aflow), new BeanPropertyRowMapper<Account>(Account.class));
		account.setId(aflow.getAccountId());
		return account;
	}
	
}
