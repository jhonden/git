package org.jx.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.ProductRepository;
import org.jx.repository.PtypeRepository;
import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;
import org.jx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService{
	private static Log log = LogFactory.getLog(ProductServiceImpl.class);
	
	@Autowired
    PtypeRepository ptypeRpo;
	@Autowired
    ProductRepository productRpo;

	public List<Product> findAll(){
		return productRpo.findAll();
	}
	
	
	@Override
	public boolean addBuyLog(BuyLog buyLog) {
		Product old = productRpo.load(buyLog.getProduct());
		old.addBuyLog(buyLog);
		productRpo.updateCountAndPrice(old);
		return true;
	}
	
	@Transactional
	public boolean delBuyLog(BuyLog buyLog) {
		Product old = productRpo.load(buyLog.getProduct());
		old.delBuyLog(buyLog);
		productRpo.updateCountAndPrice(old);
		return true;
	}
	
	public String add(Product product){
		try {
			productRpo.add(product);
			return "0";
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return "-1";
		}
	}
	
	public void findPages(Page page){
		productRpo.findByPage(page);
	}

	@Override
	public int delete(Product product) {
		return productRpo.delete(product);
	}
	
	@Override
	public int update(Product product) {
		return productRpo.update(product);
	}
	
	@Override
	public Product load(Product product) {
		return productRpo.load(product);
	}
	
}
