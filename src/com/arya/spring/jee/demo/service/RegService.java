package com.arya.spring.jee.demo.service;

import java.util.List;
import com.arya.spring.jee.demo.dto.RegDTO;

public interface RegService {
	
	//------------email service method---------------
	public  boolean isUpdateRendomPwd(String to,String rendomPWD);
	
	public boolean sendMainService(String to, String subject,String msg1,String rendomPWD, String msg2,String url);
	public void sendPreConfiguredMail(String message) ;
	
	//--------------save user details method----------
	public boolean saveUser(RegDTO regdto);
	
	//--------------login pattern method----------------
	public RegDTO loginWithEmailID(RegDTO regdto);
	public RegDTO loginWithPhonNo(RegDTO regdto);
	public RegDTO loginWithUseName(RegDTO regdto);
	
	//--------------search user information method-------
	public RegDTO searchUserInfo(RegDTO regdto);
	
	//--------display list of user details method -------
	public List<RegDTO> getList();

	public String getUserForgotenPwd(RegDTO regdto,String uemail);

	public String getUserRendomPwd(RegDTO regdto, String varificationcode);

	public boolean isUpdateNewPwd(String confirmPassword, String varificationcode);

	public void setNullRendomPwd(String varificationcode);

	
}
