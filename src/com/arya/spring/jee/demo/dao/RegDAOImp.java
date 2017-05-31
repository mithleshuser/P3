package com.arya.spring.jee.demo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.arya.spring.jee.demo.dto.RegDTO;
import com.arya.spring.jee.demo.exception.DaoLearException;
@Repository
public class RegDAOImp extends Exception implements RegDAO{
	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean saveUser(RegDTO regdto) {
		try {
			System.out.println("Hii SaveUser");
			Session session = sessionFactory.openSession();
			System.out.println(" session open ");
			Transaction transaction = session.beginTransaction();
			System.out.println("session.beginTransaction(); DONE");
			session.save(regdto);
			System.out.println("save DONE");
			transaction.commit();
			System.out.println("COMMIT DONE");
			session.close();
			System.out.println("RETURN ");
		}catch (Exception ex) {
			ex.printStackTrace();
						
		}
		
		return true;
	}
	
/*	*/


	@Override
	public RegDTO loginWithEmailID(RegDTO regdto)throws NullPointerException {
		Session s1 = sessionFactory.openSession();
		Transaction tx = s1.beginTransaction();
		Query query = s1.createQuery("from RegDTO where uemail=:e_mail and upassword=:e_Pwd");
		query.setString("e_mail", regdto.getUemail());
		query.setString("e_Pwd", regdto.getUpassword());
		RegDTO validUser = (RegDTO) query.uniqueResult();
		tx.commit();
		s1.close();
		return validUser;	
	}
	@Override
	public RegDTO loginWithPhonNo(RegDTO regdto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO where upnonNo=:pNO and upassword=:pwd");
		query.setString("pNO", regdto.getUpnonNo());
		query.setString("pwd", regdto.getUpassword());
		RegDTO validUser = (RegDTO) query.uniqueResult();
		transaction.commit();
		session.close();
		return validUser;
	}
	
	@Override
	public RegDTO loginWithUseName(RegDTO regdto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO where uname=:u_name and upassword=:pwd");
		query.setString("u_name", regdto.getUname());
		query.setString("pwd", regdto.getUpassword());
		RegDTO validUser = (RegDTO) query.uniqueResult();
		transaction.commit();
		session.close();
		return validUser;
	}

	@Override
	public RegDTO searchUserInfo(RegDTO regdto) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO where rvk_id=:rvk_ID");
		query.setString("rvk_ID", regdto.getRvk_id());
		RegDTO userInfo = (RegDTO) query.uniqueResult();
		transaction.commit();
		session.close();
		return userInfo;
	}

	//User List
	@Override
	public List getAllinfo()throws DaoLearException{
		List results = null;
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO");
		results = (List) query.list();
		if(results==null) throw new DaoLearException("query.list(): returns empty List");
		transaction.commit();
		session.close();
		return results;
	}


	//------------------Pending logic----------------------
		
	@Override
	@SuppressWarnings("unchecked")	
	public List<RegDTO> getList() {
		Session session = sessionFactory.openSession();	
		Query q1 = session.createQuery("from RegDTO");
		List<RegDTO> employeeList=q1.list();
		for(RegDTO pobj : employeeList){
			System.out.println("RVK ID :" + pobj.getRvk_id());
			System.out.println("First name: " + pobj.getUname());
			System.out.println("Last Name :" + pobj.getUemail());
			System.out.println("Qualification :" + pobj.getUpnonNo());
			System.out.println("--------------------------------------");
		}
		session.close();
		return employeeList;
	}
//-------------------------------------------------------
// before sending email update the random password to the database ;	

	@Override
	public boolean isUpdateRendomPwd(String to, String rendomPWD) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "UPDATE RegDTO set rendompwd = :r_PWD WHERE uemail = :em_to";
		Query query = session.createQuery(hql);			
		query.setString("r_PWD", rendomPWD);
		query.setString("em_to", to);
		int result = query.executeUpdate();
		transaction.commit();
		session.close();
		if(result==1){
			return true;
		}
		return false;
	}
	
//before sending the mail and password check this user authentiaction 
	
	@Override
	public String getUserForgotenPwd(RegDTO regdto,String u_email) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO where uemail=:e_mail");
		query.setString("e_mail", regdto.getUemail());
		RegDTO userInfo = (RegDTO) query.uniqueResult();
		String pwd = userInfo.getUpassword();
		System.out.println("final  "+pwd);
		transaction.commit();
		session.close();
		return pwd;
		
	}

	@Override
	public String getUserRendomPwd(RegDTO regdto, String varificationcode) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("from RegDTO where rendompwd=:v_code");
		query.setString("v_code", varificationcode);
		RegDTO userInfo = (RegDTO) query.uniqueResult();
		String pwd = userInfo.getRendompwd();
		transaction.commit();
		session.close();
		return pwd;

	}

	@Override
	public boolean isUpdateNewPwd(String confirmPassword, String varificationcode) {
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "UPDATE RegDTO set upassword = :c_pwd  WHERE rendompwd = :v_code";
		Query query = session.createQuery(hql);		
		query.setString("c_pwd", confirmPassword);
		query.setString("v_code", varificationcode);
		int result = query.executeUpdate();
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public void setNullRendomPwd(String varificationcode) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "UPDATE RegDTO set rendompwd = :nullval  WHERE rendompwd = :v_code";
		Query query = session.createQuery(hql);	
		query.setString("nullval", null);
		query.setString("v_code", varificationcode);
		int result = query.executeUpdate();
		transaction.commit();
		session.close();
		if(result>1){
			System.out.println("rendompwd = :null  DONE");
		}
		
		
	}
	

}
