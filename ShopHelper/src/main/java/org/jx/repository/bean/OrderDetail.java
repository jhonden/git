package org.jx.repository.bean;

public class OrderDetail {
	private String orderId;
	private Product product;
	private String createDateStr;
	private double sellPrice;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getProductid() {
		if(product == null){
			return 0;
		}
		return product.getId();
	}
	public void setProductid(int productid) {
		if(product == null){
			product = new Product();
		}
		product.setId(productid);
	}
	public String getPname() {
		if(product == null){
			return null;
		}
		return product.getName();
	}
	public void setPname(String pname) {
		if(product == null){
			product = new Product();
		}
		product.setName(pname);
	}
	public int getPcount() {
		if(product == null){
			return 0;
		}
		return product.getPcount();
	}
	public void setPcount(int pcount) {
		if(product == null){
			product = new Product();
		}
		product.setPcount(pcount);
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
	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", product=" + product
				+ ", createDateStr=" + createDateStr + ", sellPrice="
				+ sellPrice + "]";
	}
}
