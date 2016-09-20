package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.BuyLog;

public interface BuyLogRepository extends FindPage{
	

	public List<BuyLog> findAll();
	
	
	public void add(BuyLog buyLog);


	public int delete(BuyLog buyLog);
	
	public int update(BuyLog buyLog);
	
	public BuyLog load(BuyLog buyLog);

}
