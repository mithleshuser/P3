package com.arya.spring.jee.demo.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arya.spring.jee.demo.dao.FileuploadingDAO;
import com.arya.spring.jee.demo.dto.FilePropertiseDTO;
import com.arya.spring.jee.demo.util.FileDownlodUtil;
@Service
public class FileuploadingServiceImp implements FileuploadingService {	
	@Autowired
	public FileuploadingDAO fileuploadingDAO;
	
	@Override
	public void saveFile(FilePropertiseDTO filePropertise) {
		
		fileuploadingDAO.saveFile(filePropertise);
	}

	@Override
	public List<FilePropertiseDTO> getFileList() {
		
		return fileuploadingDAO.getFileList();
	}

	@Override
	public FilePropertiseDTO getFileById(int fileid) {
		
		return fileuploadingDAO.getFileById(fileid);
	}

	@Override
	public boolean doDeleateRow(int id) {
		return fileuploadingDAO.doDeleateRow(id);
	}

	@Override
	public void getSaveDBtoXlsx() {
		fileuploadingDAO.getSaveDBtoXlsx();
	}

	@Override
	public void downloadXlsxFill(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		FileDownlodUtil.downloadXlsxFill(request,response);
	}






}
