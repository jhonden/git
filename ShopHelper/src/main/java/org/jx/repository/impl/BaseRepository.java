package org.jx.repository.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseRepository<T> {
	/**
	 * 日志
	 */
	private static Log log = LogFactory.getLog(BaseRepository.class);
	@Autowired
	protected NamedParameterJdbcTemplate jdbcTemplate;
	
	
	protected String _genCountSql(String table, String where){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(table).append(" where 1=1 ").append(where);
		if(log.isDebugEnabled()){
			log.debug("countSql = "+sql.toString());
		}
		return sql.toString();
	}
	
	protected String _genPageSql(String table,  String[] cols, String where, String groupby, String having, String orderby, Page page){
		StringBuilder sql = new StringBuilder("select ");
		for(int i=0;i<cols.length; i++){
			if(i < cols.length-1)
				sql.append(cols[i]).append(" , ");
			else
				sql.append(cols[i]);
		}
		
		sql.append(" from ").append(table).append(" where 1=1 ");
		
		if(where != null)
			sql.append(where);
		
		if(groupby != null)
			sql.append(" group by ").append(groupby);
		
		if(having != null)
			sql.append(" having ").append(having);
		
		if(orderby != null)
			sql.append(" order by ").append(having);
		
		sql.append(" limit ").append(page.getPageSize()).append(" offset ").append(page.getOffset());
		if(log.isDebugEnabled()){
			log.debug("pageSql = "+sql.toString());
		}
		return sql.toString();
	}
	
	protected void _findByPage(String sqlCount, String sqlFindPage, Page page,RowMapper<T> mapper){
		int count = jdbcTemplate.queryForObject(sqlCount, page.getConditions(), Integer.class);
		List<T> items = jdbcTemplate.query(sqlFindPage, page.getConditions(), mapper);
		page.setRows(count);
		page.setDatas(items);
	}

}
