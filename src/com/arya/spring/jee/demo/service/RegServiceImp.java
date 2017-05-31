package com.arya.spring.jee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.arya.spring.jee.demo.dao.RegDAO;
import com.arya.spring.jee.demo.dto.RegDTO;

@Service
public class RegServiceImp implements RegService {
	@Autowired
	public RegDAO regDao;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private SimpleMailMessage simpleMailMessage;

	@Autowired
	private SimpleMailMessage preConfiguredMessage;

	@Override
	public boolean saveUser(RegDTO regdto) {
		return regDao.saveUser(regdto);
	}

	@Override
	public RegDTO loginWithEmailID(RegDTO regdto) {
		return regDao.loginWithEmailID(regdto);

	}

	@Override
	public RegDTO loginWithPhonNo(RegDTO regdto) {
		return regDao.loginWithPhonNo(regdto);

	}

	@Override
	public RegDTO loginWithUseName(RegDTO regdto) {
		return regDao.loginWithUseName(regdto);

	}

	@Override
	public RegDTO searchUserInfo(RegDTO regdto) {

		return regDao.searchUserInfo(regdto);
	}

	@Override
	public List<RegDTO> getList() {
		return regDao.getList();
	}

	// --------------------------------------------------------
	/**
	 * This method will send compose Password,url,msg and send the message
	 */
	@Override
	public boolean sendMainService(String to, String subject, String msg1, String rendomPWD, String msg2, String url) {
		if (isUpdateRendomPwd(to, rendomPWD)) {
			simpleMailMessage.setTo(to);
			simpleMailMessage.setSubject(subject);
			simpleMailMessage.setText(msg1 + ", " + rendomPWD + ", " + msg2 + ", " + url);
			mailSender.send(simpleMailMessage);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * This method will send a pre-configured message / if any technical problem
	 */
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

	@Override
	public boolean isUpdateRendomPwd(String to, String rendomPWD) {
		return regDao.isUpdateRendomPwd(to, rendomPWD);
	}

	@Override
	public String getUserForgotenPwd(RegDTO regdto, String uemail) {
		return regDao.getUserForgotenPwd(regdto, uemail);
	}

	@Override
	public String getUserRendomPwd(RegDTO regdto, String varificationcode) {
		return regDao.getUserRendomPwd(regdto, varificationcode);
	}

	@Override
	public boolean isUpdateNewPwd(String confirmPassword, String varificationcode) {
		return regDao.isUpdateNewPwd(confirmPassword, varificationcode);
	}

	@Override
	public void setNullRendomPwd(String varificationcode) {
		regDao.setNullRendomPwd(varificationcode);
		
	}

}
