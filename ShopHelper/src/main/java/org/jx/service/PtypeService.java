package org.jx.service;

import java.util.List;

import org.jx.repository.bean.Page;
import org.jx.repository.bean.Ptype;


public interface PtypeService {
	
	public List<Ptype> findAll();
	
	public String add(Ptype ptype);
	
	public void findPages(Page page);

	public int delete(Ptype ptype);
	
	public int update(Ptype ptype);
	
	public Ptype load(Ptype ptype);
}
