package org.jx.repository.bean;


public class BuyLog{
		private int id;
		private User user;
		private Product product;
		private int buyCount;
		private double buyPrice;
		private String createDateStr;
		private String remark;
		
		public BuyLog() {
		}
		
		public BuyLog(int id) {
			this.id = id;
		}
		
		public int getId() {
			return id;
		}

		public String getUname() {
			if(user == null)
				return null;
			return user.getName();
		}

		public void setUname(String uname) {
			if(user == null)
				user = new User();
			user.setName(uname);
		}

		public String getPname() {
			if(product == null)
				return null;
			return product.getName();
		}

		public void setPname(String pname) {
			if(product == null)
				product = new Product();
			product.setName(pname);
		}

		public int getUserid() {
			if(user == null)
				return 0;
			return user.getId();
		}

		public void setUserid(int userid) {
			if(user == null)
				user = new User();
			user.setId(userid);
		}
		
		public int getProductid() {
			if(product == null)
				return 0;
			return product.getId();
		}

		public void setProductid(int productid) {
			if(product == null)
				product = new Product();
			product.setId(productid);
		}

		public void setId(int id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public int getBuyCount() {
			return buyCount;
		}

		public void setBuyCount(int buyCount) {
			this.buyCount = buyCount;
		}

		public double getBuyPrice() {
			return buyPrice;
		}

		public void setBuyPrice(double buyPrice) {
			this.buyPrice = buyPrice;
		}

		public String getCreateDateStr() {
			return createDateStr;
		}

		public void setCreateDateStr(String createDateStr) {
			this.createDateStr = createDateStr;
		}
		

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		@Override
		public String toString() {
			return "BuyLog [id=" + id + ", user=" + user + ", product="
					+ product + ", buyCount=" + buyCount + ", buyPrice="
					+ buyPrice + ", createDateStr=" + createDateStr + "]";
		}
	}