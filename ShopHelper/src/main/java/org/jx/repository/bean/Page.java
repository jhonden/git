package org.jx.repository.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page {
	private int pageId;
	private int pageSize = 10;
	private int pageCount;
	private int rows;
	private Map<String,String> conditions;
	private List datas;
	
	public Page() {
		conditions = new HashMap<String, String>();
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getOffset(){
		return (pageId - 1) * pageSize;
	}
	
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		if(rows >= this.pageSize && this.rows % this.pageSize == 0){
			this.pageCount = this.rows / this.pageSize;
		}else{
			this.pageCount = this.rows / this.pageSize + 1;
		}
		
	}
	
	public Page appendEq(StringBuilder where,String column, String key){
		String value = conditions.get(key);
		if(value != null && value.trim().length() > 0){
			where.append(" and ").append(column).append(" = :").append(key).append(" ");
		}
		return this;
	}
	
	public Page appendEq(StringBuilder where,String pkey){
		String key;
		if(pkey != null && pkey.indexOf(".") != -1){
			key = pkey.split("\\.")[1];
		}else{
			key = pkey;
		}
		String value = conditions.get(key);
		if(value != null && value.trim().length() > 0){
			where.append(" and ").append(pkey).append(" = :").append(key).append(" ");
		}
		return this;
	}
	public Page appendLike(StringBuilder where,String column, String key){
		String value = conditions.get(key);
		if(value != null && value.trim().length() > 0){
			String newKey = "new"+key.trim();
			String newValue = "%"+value.trim()+"%";
			where.append(" and ").append(column).append(" like :").append(newKey).append(" ");
			conditions.put(newKey,newValue);
		}
		return this;
	}
	
	public Page appendLike(StringBuilder where,String pkey){
		String key;
		if(pkey != null && pkey.indexOf(".") != -1){
			key = pkey.split("\\.")[1];
		}else{
			key = pkey;
		}
		String value = conditions.get(key);
		if(value != null && value.trim().length() > 0){
			String newKey = "new"+key.trim();
			String newValue = "%"+value.trim()+"%";
			where.append(" and ").append(pkey).append(" like :").append(newKey).append(" ");
			conditions.put(newKey,newValue);
		}
		return this;
	}
	
	public Map<String, String> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
}
