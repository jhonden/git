package org.jx.service;

import java.util.List;

import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;


public interface BuyLogService {
	
	public List<BuyLog> findAll();
	
	public String add(BuyLog ptype);
	
	public void findPages(Page page);

	public int delete(BuyLog ptype);
	
	public int update(BuyLog ptype);
	
	public BuyLog load(BuyLog ptype);
}
