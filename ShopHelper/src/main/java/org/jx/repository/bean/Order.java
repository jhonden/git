package org.jx.repository.bean;

import java.util.List;

public class Order {
	private String id;
	private User user;
	private int pcount;
	private String createDateStr;
	private double sellPrice;
	List<OrderDetail> ods ;
	
	public List<OrderDetail> getOds() {
		return ods;
	}
	public void setOds(List<OrderDetail> ods) {
		this.ods = ods;
	}
	public String getUname() {
		if(user == null){
			return null;
		}
		return user.getName();
	}
	public void setUname(String uname) {
		if(user == null){
			user = new User();
		}
		user.setName(uname);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserid() {
		if(user == null){
			return 0;
		}
		return user.getId();
	}
	public void setUserid(int userid) {
		if(user == null){
			user = new User();
		}
		user.setId(userid);
	}
	
	
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	
}
