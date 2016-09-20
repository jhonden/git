package org.jx.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.AccountFlowRepository;
import org.jx.repository.AccountRepository;
import org.jx.repository.bean.Account;
import org.jx.repository.bean.AccountFlow;
import org.jx.repository.bean.Page;
import org.jx.service.AccountService;
import org.jx.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService{
	private static Log log = LogFactory.getLog(AccountServiceImpl.class);
	@Autowired
	AccountRepository accountRpo;
	@Autowired
	AccountFlowRepository accountFlowRpo;
	
	@Transactional
	public void inAccount(double money, String remark) {
		Account account = accountRpo.load();
		account.setAmount(account.getAmount() + money);
		accountRpo.update(account);
		//生成账务流水
		AccountFlow flow = new AccountFlow();
		flow.setAccountId(account.getId());
		flow.setAmount(money);
		flow.setType(AccountFlow.TYPE_PLUS);
		flow.setRemark(remark);
		accountFlowRpo.add(flow);
	}
	
	@Override
	public void outAccount(double money, String remark) {
		Account account = accountRpo.load();
		account.setAmount(account.getAmount() - money);
		accountRpo.update(account);
		//生成账务流水
		AccountFlow flow = new AccountFlow();
		flow.setAccountId(account.getId());
		flow.setAmount(money);
		flow.setType(AccountFlow.TYPE_MINUS);
		flow.setRemark(remark);
		accountFlowRpo.add(flow);
	}
	
	@Override
	public void findPages(Page page) {
		accountFlowRpo.findByPage(page);
	}
	
	@Override
	public Account load() {
		return accountRpo.load();
	}
	
	@Override
	public Account todayInAccount() {
		Account account = accountRpo.load();
		AccountFlow aflow = new AccountFlow();
		aflow.setAccountId(account.getId());
		aflow.setType(AccountFlow.TYPE_PLUS);
		aflow.setCreateDateStr(DateUtil.format("yyyy-MM-dd"));
		return accountFlowRpo.loadTodayAccount(aflow);
	}
	
	@Override
	public Account todayOutAccount() {
		Account account = accountRpo.load();
		AccountFlow aflow = new AccountFlow();
		aflow.setAccountId(account.getId());
		aflow.setType(AccountFlow.TYPE_MINUS);
		aflow.setCreateDateStr(DateUtil.format("yyyy-MM-dd"));
		return accountFlowRpo.loadTodayAccount(aflow);
	}
}
