package com.baoding.support.news.dao;

import com.baoding.common.FindPage;
import com.baoding.support.news.entity.CompanyNews;
import com.baoding.support.news.entity.News;

public interface NewsDAO extends FindPage {
	
	//�����������
	public void add(News news);
	
	//��ӹ�˾����
	public void addCompanyNews(CompanyNews cn);

}
