package com.baoding.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class BaseDAO {
	
	protected NamedParameterJdbcTemplate jdbcTemplate;

	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
    
	@Resource
	public void setJdbcTemplate(ComboPooledDataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * ֻ֧��BeanPropertyRowMapper
	 * @param obj
	 * @param sql
	 * @return
	 */
	protected <T>T load(T obj, String sql){
		SqlParameterSource sps = new BeanPropertySqlParameterSource(obj);
		return (T)getJdbcTemplate().queryForObject(sql, sps, new BeanPropertyRowMapper(obj.getClass()));
	} 
	
	protected int update(Object obj, String sql){
		SqlParameterSource sps = new BeanPropertySqlParameterSource(obj);
		return getJdbcTemplate().update(sql, sps);
	} 
	
	protected int[] updateAll(List objs, String sql){
		SqlParameterSource[] spes = new BeanPropertySqlParameterSource[objs.size()];
		for(int i=0;i<spes.length;i++){
			spes[i] = new BeanPropertySqlParameterSource(objs.get(i));
		}
		return getJdbcTemplate().batchUpdate(sql, spes);
	} 
	
	/**
	 * ��ҳ��ѯ
	 * @param page
	 * @param sqlFindByPage  ��ҳsql���
	 * @param sqlFindRows  ��ѯ����sql���  
	 * @param rowMaper
	 */
	 protected void findByPage(Page page, String sqlFindByPage, String sqlFindRows, RowMapper rowMaper) {
		 page.condition.put("startIndex", page.getStartIndex()+"");
		 page.condition.put("endIndex", page.getEndIndex()+"");
		 SqlParameterSource ps=new MapSqlParameterSource(page.condition); 
		 List list = getJdbcTemplate().query(sqlFindByPage, ps, rowMaper);
		 page.setItems(list);
		 int rows = getJdbcTemplate().queryForObject( sqlFindRows, ps,Integer.class);
		 page.setRows(rows);
		 page.toPageCount(rows);
	}
	
	 /**
	  * ƴ�ӷ�ҳ��ѯsql,Ҫ�ô˷������������ʵ��getSqlFindRows�����������������Ļ�����һ������������where  1=1
	  * @param page
	  * @param tabName  ����
	  * @param otherCondition  ����������������������
	  * @param cols  Ҫ��ѯ������
	  * @return
	  */
	 protected String getSqlFindByPage(Page page,String tabName,String otherCondition, String... cols){
		 	StringBuilder buf = new StringBuilder("SELECT  ");
//		 	buf.append(" FROM (SELECT ROWNUM RN, s.* ")
//		 	   .append(" FROM (SELECT ");
		 	for(int i = 0; i<cols.length ; i++){
		 		String col = cols[i];
		 		if(i == cols.length - 1){
		 			buf.append(" ").append(col).append(" ");
		 		}else{
		 			buf.append(" ").append(col).append(" ,");
		 		}
		 	}
		 	buf.append(" from " + tabName);
			String where = getSqlFindRows(page);
			where = where.substring(where.lastIndexOf("where 1=1"));
			buf.append(" "+where+" ");
			if(otherCondition != null){
				buf.append(otherCondition);
			}
			buf.append(" limit "+page.getStartIndex()+","+page.getEndIndex());
			return buf.toString();
			
	}
	 
	 /**
	  * cah
	  * @param page
	  * @return
	  */
	 protected  String getSqlFindRows(Page page){
		 return null;
	 }

}
