package com.app.demo.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;





import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
@Entity
@Table(name = "opms_company")
@NamedQueries(@NamedQuery(name = "CompanyPojo.findAll", query = "SELECT c FROM CompanyPojo c"))
public class CompanyPojo implements Serializable {
	//@Pattern(regexp="[^0-9]")
	private String cName;
	//@NotNull(message="Country must be specified.")
	private String cCountry;
	//@NotNull(message="State must be specified.")
	private String cState;
	//@NotNull(message="City must be specified.")
	private String cCity;
	//@NotNull(message="Designation must be specified.")

	private String cRepDesg;
	//@NotNull(message="UserName must be provided.")
	private String cUserName;
	//@NotNull(message="Email must be provided.")
	private String cEmail;
	//@Pattern(regexp="[^A-Za-z]")
	private String cPhone;							
	//@Size(min=6,max=15, message= "password should be between {min}---{max} characters")
	private String cPassword;
	//@Past
	private Date cDate;

	@Column(name="c_name" , length=30)
	public String getcName() {
		return cName;
	}
	
	public void setcName(String cName) {
		this.cName = cName;
	}
	
	@Column(name="c_country", length=20)
	public String getcCountry() {
		return cCountry;
	}
	
	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}
	
	@Column(name="c_state")
	public String getcState() {
		return cState;
	}
	
	public void setcState(String cState) {
		this.cState = cState;
	}
	
	@Column(name="c_city")
	public String getcCity() {
		return cCity;
	}
	
	public void setcCity(String cCity) {
		this.cCity = cCity;
	}
	
	@Column(name="c_repdesg")
	public String getcRepDesg() {
		return cRepDesg;
	}
	
	public void setcRepDesg(String cRepDesg) {
		this.cRepDesg = cRepDesg;
	}
	@Id
	@Column(name="c_username")
	public String getcUserName() {
		return cUserName;
	}
	
	public void setcUserName(String cUserName) {
		this.cUserName = cUserName;
	}
	
	@Column(name="c_email")
	public String getcEmail() {
		return cEmail;
	}
	
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}
	
	@Column(name="c_phone")
	public String getcPhone() {
		return cPhone;
	}
	
	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	@Column(name="c_password")
	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public CompanyPojo(){
		System.out.println("you are inside default ctor");
	}


	
	//@Temporal(TemporalType.DATE)
	@Column(name="c_regdate")
	public Date getDate() {
		return cDate;
	}

	public void setDate(Date cDate) {
		this.cDate = cDate;
	}

	public CompanyPojo(String cName, String cCountry, String cState,
			String cCity, String cRepDesg, String cUserName, String cEmail,
			String cPhone, String cPassword, Date newDate) {
		super();
		this.cName = cName;
		this.cCountry = cCountry;
		this.cState = cState;
		this.cCity = cCity;
		this.cRepDesg = cRepDesg;
		this.cUserName = cUserName;
		this.cEmail = cEmail;
		this.cPhone = cPhone;
		this.cPassword = cPassword;
		this.cDate = new Date();
	}
	
	

	public CompanyPojo(String cUserName) {
		super();
	
		this.cUserName = cUserName;
		
	}

	@Override
	public String toString() {
		return "CompanyPojo [cName=" + cName + ", cCountry=" + cCountry
				+ ", cState=" + cState + ", cCity=" + cCity + ", cRepDesg="
				+ cRepDesg + ", cUserName=" + cUserName + ", cEmail=" + cEmail
				+ ", cPhone=" + cPhone + ", cPassword=" + cPassword
				+ ", cDate=" + cDate + "]";
	}


	
	
	
	
}
