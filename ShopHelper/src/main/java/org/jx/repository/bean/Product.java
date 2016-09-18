package org.jx.repository.bean;


public class Product{
		private int id;
		private String name;
		private Ptype ptype;
		private String icon ;
		private double sellPrice;
		private int pcount;
		private String createDateStr;
		private String description;
		private double buyPrice;
		
		public Product() {
		}
		
		public Product(int id) {
			this.id = id;
		}
		
		public double getBuyPrice() {
			return buyPrice;
		}
		
		public void addBuyLog(BuyLog buyLog){
			int newCount = buyLog.getBuyCount() + getPcount();
			double newPrice = (buyLog.getBuyPrice() * buyLog.getBuyCount() + getBuyPrice()* getPcount()) / newCount;
			setPcount(newCount);
			setBuyPrice(newPrice);
		}
		
		public void delBuyLog(BuyLog buyLog){
			int newCount = getPcount() - buyLog.getBuyCount();
			double newPrice = (getBuyPrice()* getPcount() - buyLog.getBuyPrice() * buyLog.getBuyCount()) / newCount;
			setPcount(newCount);
			setBuyPrice(newPrice);
		}

		public void setBuyPrice(double buyPrice) {
			this.buyPrice = buyPrice;
		}

		public void setTypeid(int typeid) {
			if(ptype == null){
				ptype = new Ptype();
			}
			ptype.setId(typeid);
		}
		
		public void setTypeName(String typeName){
			if(ptype == null){
				ptype = new Ptype();
			}
			ptype.setName(typeName);
		}
		
		public int getTypeid(){
			if(ptype == null)
				return -1;
			return ptype.getId();
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public double getSellPrice() {
			return sellPrice;
		}

		public void setSellPrice(double sellPrice) {
			this.sellPrice = sellPrice;
		}

		public int getPcount() {
			return pcount;
		}

		public void setPcount(int pcount) {
			this.pcount = pcount;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public void setCreateDateStr(String createDateStr) {
			this.createDateStr = createDateStr;
		}
		public String getCreateDateStr() {
			return createDateStr;
		}
		
		public void setPtype(Ptype ptype) {
			this.ptype = ptype;
		}
		
		public Ptype getPtype() {
			return ptype;
		}

		@Override
		public String toString() {
			return "Product [id=" + id + ", name=" + name + ", ptype=" + ptype
					+ ", icon=" + icon + ", sellPrice=" + sellPrice
					+ ", pcount=" + pcount + ", createDateStr=" + createDateStr
					+ ", description=" + description + ", buyPrice=" + buyPrice
					+ "]";
		}
		
	}