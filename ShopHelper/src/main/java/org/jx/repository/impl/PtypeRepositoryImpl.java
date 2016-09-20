package org.jx.repository.impl;

import java.util.List;

import org.jx.repository.PtypeRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Ptype;
import org.jx.repository.impl.BaseRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class PtypeRepositoryImpl extends BaseRepository<Ptype> implements PtypeRepository{
	static final String table = " tbl_sh_ptype ";
	static final String findAllSql = "select id as id, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description from tbl_sh_ptype";
	static final String addSql = "insert into tbl_sh_ptype(name,create_date,description) values(:name,datetime('now'),:description)";
	static final String findByPage = "select id as id, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description from tbl_sh_ptype where 1=1 #other# limit :limit offset :offset";
	static final String deleteSql = "delete from tbl_sh_ptype where id = :id";
	static final String updateSql = "update tbl_sh_ptype set name = :name, description = :description where id = :id";
	static final String loadSql = "select id as id, name as name, description as description from tbl_sh_ptype where id = :id";
	
	public List<Ptype> findAll() {
		return jdbcTemplate.query(findAllSql, new BeanPropertyRowMapper<Ptype>(Ptype.class));
	}
	
	
	public void add(Ptype ptype){
		jdbcTemplate.update(addSql, new BeanPropertySqlParameterSource(ptype));
	}
	
	
	public void findByPage(Page page){
		StringBuilder where = new StringBuilder();
		page.appendLike(where, "name");
		String pageSql = _genPageSql(table, 
				                        new String[]{"id as id, name as name, strftime('%Y-%m-%d',create_date) as createDateStr, description as description"}, 
				                        where.toString(), 
				                        null, null, null, page);
		_findByPage(_genCountSql(table, where.toString()), pageSql, page, new BeanPropertyRowMapper<Ptype>(Ptype.class));
	}
	
	@Override
	public int delete(Ptype ptype) {
		return jdbcTemplate.update(deleteSql, new BeanPropertySqlParameterSource(ptype));
	}

	@Override
	public int update(Ptype ptype) {
		return jdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(ptype));
	}
	
	@Override
	public Ptype load(Ptype ptype) {
		return jdbcTemplate.queryForObject(loadSql, new BeanPropertySqlParameterSource(ptype), new BeanPropertyRowMapper<Ptype>(Ptype.class));
	}
}
