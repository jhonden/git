package org.jx.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jx.repository.PtypeRepository;
import org.jx.repository.bean.Page;
import org.jx.repository.bean.Ptype;
import org.jx.service.PtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PtypeServiceImpl implements PtypeService{
	private static Log log = LogFactory.getLog(PtypeServiceImpl.class);
	
	@Autowired
    PtypeRepository ptypeRpo;

	public List<Ptype> findAll(){
		return ptypeRpo.findAll();
	}
	
	public String add(Ptype ptype){
		try {
			ptypeRpo.add(ptype);
			return "0";
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("添加失败", e);
			}
			return "-1";
		}
	}
	
	public void findPages(Page page){
		ptypeRpo.findByPage(page);
	}

	@Override
	public int delete(Ptype ptype) {
		return ptypeRpo.delete(ptype);
	}
	
	@Override
	public int update(Ptype ptype) {
		return ptypeRpo.update(ptype);
	}
	
	@Override
	public Ptype load(Ptype ptype) {
		return ptypeRpo.load(ptype);
	}
	
}
