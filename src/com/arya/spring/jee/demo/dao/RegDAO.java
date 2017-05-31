package com.arya.spring.jee.demo.dao;

import java.util.List;

import com.arya.spring.jee.demo.dto.RegDTO;
import com.arya.spring.jee.demo.exception.DaoLearException;
import com.arya.spring.jee.demo.exception.ServiceLearException;

public interface RegDAO {
	
	public boolean saveUser(RegDTO regdto);
	public RegDTO loginWithEmailID(RegDTO regdto);
	public RegDTO loginWithPhonNo(RegDTO regdto);
	public RegDTO loginWithUseName(RegDTO regdto);
	public RegDTO searchUserInfo(RegDTO regdto);
	public List getAllinfo() throws DaoLearException;
	public List<RegDTO> getList();
	public  boolean isUpdateRendomPwd(String to,String rendomPWD);
	public String getUserForgotenPwd(RegDTO regdto,String uemail);
	public String getUserRendomPwd(RegDTO regdto, String varificationcode);
	public boolean isUpdateNewPwd(String confirmPassword, String varificationcode);
	public void setNullRendomPwd(String varificationcode);
}
