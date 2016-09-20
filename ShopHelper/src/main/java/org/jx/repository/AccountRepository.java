package org.jx.repository;

import org.jx.repository.bean.Account;

public interface AccountRepository {
	  public Account load();
	  
	  public void update(Account account);
}
