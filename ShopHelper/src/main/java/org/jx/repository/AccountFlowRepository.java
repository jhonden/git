package org.jx.repository;

import org.jx.repository.bean.Account;
import org.jx.repository.bean.AccountFlow;

public interface AccountFlowRepository extends FindPage{
	public void add(AccountFlow flow);

	public Account loadTodayAccount(AccountFlow aflow);

}
