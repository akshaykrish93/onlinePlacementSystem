package com.app.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.demo.dao.CompanyDao;
import com.app.demo.pojo.CompanyPojo;

@Service("dao_based_service")
//mandatory
@Transactional
//mandatory
public class CompanyServiceDaoImp implements CompanyService {

	@Autowired
	private CompanyDao dao;
	@Override
	@Transactional
	public CompanyPojo addCompany(CompanyPojo b1) {
		return dao.addCompany(b1);
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyPojo getCompanyByUserName(String cUserName) {
		CompanyPojo c=dao.getCompanyByUserName(cUserName);
		System.out.println("in service : getDetails "+c);
		return c;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyPojo> getCompanies() {
		List<CompanyPojo> l1=dao.getCompanies();
		System.out.println("in service imple");
		return l1;
	}

	@Override
	@Transactional(readOnly = true)
	public CompanyPojo validateCompany(String cUserName, String cPassword) {
		return dao.validateCompany(cUserName, cPassword);
	}

	@Override
	public String deleteCompany(String cUserName) {
		// TODO Auto-generated method stub
		return dao.deleteCompany(cUserName);
	}

	@Override
	public CompanyPojo updateCompany(CompanyPojo comp) {
		CompanyPojo c = dao.updateCompany(comp);// c --- PERSISTENT
		
		// persistent state in service method
		return c;
	}

}
