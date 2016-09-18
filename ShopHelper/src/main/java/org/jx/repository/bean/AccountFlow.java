package org.jx.repository.bean;

public class AccountFlow {
	public static final String TYPE_MINUS = "0";
	public static final String TYPE_PLUS = "1";
	
	private int id;
	private String accountId;
	private String type;
	private Double amount;
	private String createDateStr;
	private String remark;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getAccountId() {
		return accountId;
	}



	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
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
		return "AccountFlow [id=" + id + ", accountId=" + accountId + ", type="
				+ type + ", amount=" + amount + ", createDateStr="
				+ createDateStr + ", remark=" + remark + "]";
	}
}
