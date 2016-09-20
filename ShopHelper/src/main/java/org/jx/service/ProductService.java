package org.jx.service;

import java.util.List;

import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;


public interface ProductService {
	
	public List<Product> findAll();
	
	public String add(Product ptype);
	
	public void findPages(Page page);

	public int delete(Product ptype);
	
	public int update(Product ptype);
	
	public Product load(Product ptype);
	
	/**
	 * 更新售价和库存
	 * @param buyLog
	 * @return
	 */
	public boolean addBuyLog(BuyLog buyLog);
	
	/**
	 * 删除一条进货记录
	 * @param buyLog
	 * @return
	 */
	public boolean delBuyLog(BuyLog buyLog);
}
