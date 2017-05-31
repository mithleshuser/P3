package com.arya.spring.jee.demo.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.arya.spring.jee.demo.dto.FilePropertiseDTO;
import com.arya.spring.jee.demo.util.Constants;
import com.arya.spring.jee.demo.util.ExcelLib;
@Repository
public class FileuploadingDAOImp implements FileuploadingDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public FileuploadingDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public FileuploadingDAOImp() {

	}

	@Override
	public void saveFile(FilePropertiseDTO filePropertise) {

		Session session = sessionFactory.openSession();
		System.out.println("session ready");
		Transaction transaction = session.beginTransaction();
		System.out.println("Tranjection ready to save");
		session.save(filePropertise);
		System.out.println("data persist");
		transaction.commit();
		System.out.println("tranjection close");
		session.close();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FilePropertiseDTO> getFileList() {
		Session session = sessionFactory.openSession();
		Query q1 = session.createQuery("from FilePropertiseDTO");
		List<FilePropertiseDTO> fileList = q1.list();
		int count = 1;
		for (FilePropertiseDTO fObj : fileList) {
			count++;
		}
		System.out.println(count);
		session.close();
		return fileList;
	}

	@Override
	public FilePropertiseDTO getFileById(int fileid) {
		Session session = sessionFactory.openSession();
		FilePropertiseDTO fObj =session.get(FilePropertiseDTO.class, fileid);
		System.out.println("i got the object from dao class ");
		session.close();
		return fObj;
	}

	@Override
	public boolean doDeleateRow(int id) {
		Session session = sessionFactory.openSession();
		String hql="DELETE FilePropertiseDTO WHERE id=:fileID";
		Query q = session.createQuery(hql);
		q.setInteger("fileID", id);
		if(q.executeUpdate()>0){
			return true;
		}
		return false;
	}
	
	
/*	working with exls operation */
	public void getSaveDBtoXlsx() {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Criteria ctr1 = session.createCriteria(FilePropertiseDTO.class);
			List<FilePropertiseDTO> list = session.createCriteria(FilePropertiseDTO.class).list();
			System.out.println("START");
			for(FilePropertiseDTO dto : list){
				System.out.println(dto.getFname()+", "+dto.getFlocation()+", "+dto.getFsize()+", "+dto.getFdate()+", "+dto.getClass()+", "+dto.getId());
			}
			System.out.println("DONE");
			ExcelLib lib = new ExcelLib();
			lib.writeToExcel(list);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public void saveXlsxDataToDb(){
		//xmlbeans-2.3.0.jar
		Session session=null;
		FilePropertiseDTO filePropertise = null;
		try {
			FileInputStream file = new FileInputStream(Constants.testDataFilePath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++){
				try {
				    session = sessionFactory.openSession();
					Transaction tx = session.beginTransaction();
					filePropertise = new FilePropertiseDTO();
					row = sheet.getRow(i);
					filePropertise.setFdate(Calendar.getInstance().getTime());
					filePropertise.setFlocation(String.valueOf(row.getCell(2).getRichStringCellValue()));
					filePropertise.setFname(String.valueOf(row.getCell(3).getRichStringCellValue()));
					filePropertise.setFsize((Integer.valueOf((int)row.getCell(4).getNumericCellValue())));
					session.save(filePropertise);
					tx.commit();
				} catch (HibernateException e) {
					e.printStackTrace();
				} finally {
					session.flush();
					session.close();
				}
			}
		} catch (FileNotFoundException ec) {
			ec.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*End here working with exls*/
}
