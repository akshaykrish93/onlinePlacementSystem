package com.app.demo.dao;

import java.util.List;

import com.app.demo.pojo.CompanyPojo;


public interface CompanyDao {

	CompanyPojo addCompany(CompanyPojo b1);		//register Company
	CompanyPojo getCompanyByUserName(String cUserName);	//getDetails
	List<CompanyPojo> getCompanies();
	CompanyPojo validateCompany(String cUserName,String cPassword);
	String deleteCompany(String cUserName);
	CompanyPojo updateCompany(CompanyPojo comp);
}
