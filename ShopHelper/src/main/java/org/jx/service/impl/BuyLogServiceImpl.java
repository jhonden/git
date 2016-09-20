package org.jx.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.AccountFlowRepository;
import org.jx.repository.AccountRepository;
import org.jx.repository.BuyLogRepository;
import org.jx.repository.PtypeRepository;
import org.jx.repository.bean.Account;
import org.jx.repository.bean.AccountFlow;
import org.jx.repository.bean.BuyLog;
import org.jx.repository.bean.Page;
import org.jx.service.AccountService;
import org.jx.service.BuyLogService;
import org.jx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BuyLogServiceImpl implements BuyLogService{
	private static Log log = LogFactory.getLog(BuyLogServiceImpl.class);
	
	@Autowired
    PtypeRepository ptypeRpo;
	@Autowired
    ProductService productService;
	@Autowired
	BuyLogRepository buyLogRpo;
	@Autowired
	AccountService accountService;

	public List<BuyLog> findAll(){
		return buyLogRpo.findAll();
	}
	
	@Transactional
	public String add(BuyLog buyLog){
		try {
			buyLogRpo.add(buyLog);
			productService.addBuyLog(buyLog);
			//出账
			double cash = buyLog.getBuyCount() * buyLog.getBuyPrice();
			accountService.outAccount(cash, "进货出账");
			return "0";
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return "-1";
		}
	}
	
	public void findPages(Page page){
		buyLogRpo.findByPage(page);
	}

	@Transactional
	public int delete(BuyLog buyLog) {
		try {
			buyLog = buyLogRpo.load(buyLog);
			buyLogRpo.delete(buyLog);
			productService.delBuyLog(buyLog);
			//入账
			double cash = buyLog.getBuyCount() * buyLog.getBuyPrice();
			accountService.inAccount(cash, "撤销进货入账");
			return 1;
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return -1;
		}
	}
	
	@Override
	public int update(BuyLog buyLog) {
		return buyLogRpo.update(buyLog);
	}
	
	@Override
	public BuyLog load(BuyLog buyLog) {
		return buyLogRpo.load(buyLog);
	}
	
}
