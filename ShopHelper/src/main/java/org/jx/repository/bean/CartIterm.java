package org.jx.repository.bean;

public class CartIterm {
	private String id;
	private int userid;
	private Product product;
	private String createDateStr;
	
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getProductid() {
		if(product == null){
			return 0;
		}
		return product.getId();
	}
	public void setProductid(int productid) {
		if(product == null)
			product = new Product();
		product.setId(productid);
	}
	public int getPcount() {
		if(product == null){
			return 0;
		}
		return product.getPcount();
	}
	
	public void addPcount(int toAdd){
		if(product == null)
			product = new Product();
		product.setPcount(product.getPcount() + toAdd);
	}
	
	public void setPcount(int pcount) {
		if(product == null)
			product = new Product();
		product.setPcount(pcount);
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public double getSellPrice() {
		if(product == null){
			return 0;
		}
		return product.getSellPrice();
	}
	public void setSellPrice(double sellPrice) {
		if(product == null)
			product = new Product();
		product.setSellPrice(sellPrice);	
	}
	
	public void setPname(String pname){
		if(product == null)
			product = new Product();
		product.setName(pname);
	}
	
	public String getPname(){
		if(product == null){
			return null;
		}
		return product.getName();
	}
	@Override
	public String toString() {
		return "CartIterm [id=" + id + ", userid=" + userid + ", product="
				+ product + ", createDateStr=" + createDateStr + "]";
	}
	public OrderDetail toOrderDetail() {
		OrderDetail od = new OrderDetail();
		od.setOrderId(id);
		od.setProductid(getProductid());
		od.setPname(getPname());
		od.setSellPrice(getSellPrice());
		return od;
	}
	
}
