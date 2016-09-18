package org.jx.repository;

import java.util.List;

import org.jx.repository.bean.Ptype;

public interface PtypeRepository extends FindPage{
	

	public List<Ptype> findAll();
	
	
	public void add(Ptype ptype);


	public int delete(Ptype ptype);
	
	public int update(Ptype ptype);
	
	public Ptype load(Ptype ptype);

}
