package org.jx.repository.bean;

public class Account {
	private String id;
	private Double amount;
	private String createDateStr;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		if(amount == null)
			amount = 0.0;
		this.amount = amount;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", amount=" + amount + ", createDateStr="
				+ createDateStr + "]";
	}
}
