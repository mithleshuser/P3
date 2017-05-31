package com.arya.spring.jee.demo.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class RegDTO {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "custom_id", strategy = "com.arya.spring.jee.demo.util.CustomIdentifier")
	@GeneratedValue(generator = "custom_id")
	private String rvk_id;
	private String uname;
	private String uemail;
	private String upnonNo;
	private String upassword;
	private String rendompwd;

	public String getRendompwd() {
		return rendompwd;
	}

	public void setRendompwd(String rendompwd) {
		this.rendompwd = rendompwd;
	}

	public String getRvk_id() {
		return rvk_id;
	}

	public void setRvk_id(String rvk_id) {
		this.rvk_id = rvk_id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUpnonNo() {
		return upnonNo;
	}

	public void setUpnonNo(String upnonNo) {
		this.upnonNo = upnonNo;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

}
