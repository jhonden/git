package org.jx.service;

import org.jx.repository.bean.Account;
import org.jx.repository.bean.Page;

public interface AccountService {
		public void inAccount(double money, String remark);
		public void outAccount(double money, String remark);
		public void findPages(Page page);
		public Account load();
		public Account todayInAccount();
		public Account todayOutAccount();
}
