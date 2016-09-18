package org.jx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.AccountFlowRepository;
import org.jx.repository.AccountRepository;
import org.jx.repository.CartRepository;
import org.jx.repository.ProductRepository;
import org.jx.repository.bean.Account;
import org.jx.repository.bean.AccountFlow;
import org.jx.repository.bean.CartIterm;
import org.jx.repository.bean.Order;
import org.jx.repository.bean.OrderDetail;
import org.jx.repository.bean.Product;
import org.jx.repository.bean.User;
import org.jx.service.AccountService;
import org.jx.service.CartService;
import org.jx.service.OrderService;
import org.jx.util.SerialNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CartServiceImpl implements CartService{
	private static Log log = LogFactory.getLog(CartServiceImpl.class);
	
	@Autowired
    CartRepository cartRpo;
	@Autowired
	ProductRepository productRpo;
	@Autowired
	OrderService orderService;
	@Autowired
	AccountService accountService;

	@Override
	public List<CartIterm> findByUser(User user) {
		return cartRpo.findByUser(user);
	}
	
	@Override
	public Map<String, Object> fingMyCart(User user) {
		List<CartIterm> iterms = cartRpo.findByUser(user);
		Map<String,Object> model = new HashMap<String,Object>();
		int count = 0;
		for(CartIterm iterm : iterms){
			count += iterm.getPcount();
		}
		model.put("itermCount", count);
		model.put("cartIterms", iterms);
		return model;
	}

	@Override
	public String findIdByUser(User user) {
		return cartRpo.findIdByUser(user);
	}

	@Transactional
	public String addProduct(Product product, User user) {
		int leftCount = productRpo.leftCount(product);
		if(leftCount < product.getPcount()){
			return "-1";
		}
		//变更库存
		Product pcount = new Product(product.getId());
		pcount.setPcount(leftCount - product.getPcount());
		productRpo.updateCount(pcount);
		
		CartIterm cartIterm = new CartIterm();
		String cartId = cartRpo.findIdByUser(user);
		if(cartId == null){
			cartId = SerialNumberGenerator.getInstance().next();
		}
		cartIterm.setId(cartId);
		cartIterm.setUserid(user.getId());
		cartIterm.setProduct(product);
		CartIterm exist = cartRpo.isProductExist(cartIterm);
		if(exist != null){
			cartIterm.addPcount(exist.getPcount());
			cartRpo.updatePcount(cartIterm);
			
		}else{
			cartRpo.add(cartIterm);
		}
		
		return "0";
	}

	@Override
	public int deleteByUser(User user) {
		List<CartIterm> iterms = cartRpo.findByUser(user);
		//归还库存
		for(CartIterm iterm : iterms){
			Product pcount = new Product(iterm.getProductid());
			int leftCount = productRpo.leftCount(pcount);
			pcount.setPcount(leftCount + iterm.getPcount());
			productRpo.updateCount(pcount);
		}
		return  cartRpo.deleteByUser(user);
	}

	@Override
	public int deleteByProduct(Product product, User user) {
		//归还库存
		Product pcount = new Product(product.getId());
		int leftCount = productRpo.leftCount(pcount);
		CartIterm cartIterm = new CartIterm();
		cartIterm.setUserid(user.getId());
		cartIterm.setProduct(product);
		CartIterm exists = cartRpo.isProductExist(cartIterm);
		pcount.setPcount(leftCount + exists.getPcount());
		productRpo.updateCount(pcount);
		//清空购物车
		return cartRpo.deleteProduct(cartIterm);
	}

	@Transactional
	public String updatePcount(Product product, User user) {
		int leftCount = productRpo.leftCount(product);
		CartIterm cartIterm = new CartIterm();
		cartIterm.setUserid(user.getId());
		cartIterm.setProduct(product);
		CartIterm exist = cartRpo.isProductExist(cartIterm);
		int newLeftCount = leftCount + exist.getPcount() - cartIterm.getPcount();
		//库存不足
		if(newLeftCount < 0){
			return "-1";
		}
		//变更库存
		Product pcount = new Product(product.getId());
		pcount.setPcount(newLeftCount);
		productRpo.updateCount(pcount);
		
		cartRpo.updatePcount(cartIterm);
		return "0";
	}

	@Transactional
	public void cashMyCart(User user) {
		List<CartIterm> iterms = cartRpo.findByUser(user);
		if(iterms == null || iterms.size() == 0)
			return ;
		//构建订单明细
		List<OrderDetail> ods = new ArrayList<OrderDetail>();
		double sellPrice = 0;
		for(CartIterm iterm : iterms){
			ods.add(iterm.toOrderDetail());
			sellPrice += iterm.getPcount() * iterm.getSellPrice();
		}
		//构建订单概要
		Order order = new Order();
		order.setId(iterms.get(0).getId());
		order.setPcount(iterms.size());
		order.setUserid(user.getId());
		order.setSellPrice(sellPrice);
		order.setOds(ods);
		//保存订单记录
		orderService.add(order);
		//清空购物车
		cartRpo.deleteByUser(user);
		accountService.inAccount(order.getSellPrice(), "销售入账");
	}
	
}
