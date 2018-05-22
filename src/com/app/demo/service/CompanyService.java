package com.app.demo.service;
import java.util.List;

import com.app.demo.pojo.CompanyPojo;

public interface CompanyService {

	CompanyPojo addCompany(CompanyPojo b1);		//register Company
	CompanyPojo getCompanyByUserName(String cUserName);	//getDetails
	List<CompanyPojo> getCompanies();
	CompanyPojo validateCompany(String cUserName,String cPassword);
	String deleteCompany(String cUserName);
	CompanyPojo updateCompany(CompanyPojo comp);
}
