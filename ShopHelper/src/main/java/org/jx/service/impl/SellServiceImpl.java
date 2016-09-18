package org.jx.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.ProductRepository;
import org.jx.repository.PtypeRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Product;
import org.jx.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellServiceImpl implements SellService{
	private static Log log = LogFactory.getLog(SellServiceImpl.class);
	
	@Autowired
    PtypeRepository ptypeRpo;
	@Autowired
    ProductRepository productRpo;

	public List<Product> findAll(){
		return productRpo.findAll();
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
}
