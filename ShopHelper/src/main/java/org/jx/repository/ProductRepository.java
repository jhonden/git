package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.Product;

public interface ProductRepository extends FindPage{
	

	public List<Product> findAll();
	
	
	public void add(Product product);


	public int delete(Product product);
	
	public int update(Product product);
	
	public Product load(Product product);
	
	/**
	 * 更新单价和库存
	 * @param product
	 */
	public int updateCountAndPrice(Product product);


	public int leftCount(Product product);
	
	public int updateCount(Product product);

}
