package com.app.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.demo.pojo.CompanyPojo;


@Repository
public class CompanyDaoImpl implements CompanyDao {

	@Autowired
	private SessionFactory factory;
	
	@Override
	public CompanyPojo addCompany(CompanyPojo b1)  {
		factory.getCurrentSession().saveOrUpdate(b1);
		return b1;
	}

	@Override
	public CompanyPojo getCompanyByUserName(String cName) {
		return (CompanyPojo) factory.getCurrentSession().get(CompanyPojo.class, cName);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyPojo> getCompanies(){
		System.out.println("in dao imple");
		return factory.getCurrentSession().getNamedQuery("CompanyPojo.findAll")
				.list();
	}

	@Override
	public CompanyPojo validateCompany(String cUserName, String cPassword) {
		return (CompanyPojo) factory
				.getCurrentSession()
				.createQuery(
						"select c from CompanyPojo c where c.cUserName = :em and c.cPassword = :pass")
				.setParameter("em", cUserName).setParameter("pass", cPassword)
				.uniqueResult();
	}

	@Override
	public String deleteCompany(String cUserName) {
		CompanyPojo c = getCompanyByUserName(cUserName);
		if (c != null)
			factory.getCurrentSession().delete(c);
		return "Company "+c.getcName()+"Un subcribed";
	}

	@Override
	public CompanyPojo updateCompany(CompanyPojo comp) {
		System.out.println("in dao " + comp);
		// accept detached cust pojo & rets persistent
		factory.getCurrentSession().update(comp);
		return comp;// rets persistent
	}

}
