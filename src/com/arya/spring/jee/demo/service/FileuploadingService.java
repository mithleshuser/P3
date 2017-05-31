package com.arya.spring.jee.demo.service;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arya.spring.jee.demo.dto.FilePropertiseDTO;
public interface FileuploadingService {
	public void saveFile(FilePropertiseDTO filePropertise);

	public List<FilePropertiseDTO> getFileList();

	public FilePropertiseDTO getFileById(int fileid);

	public boolean doDeleateRow(int id);
	public void getSaveDBtoXlsx();
	public void downloadXlsxFill(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
