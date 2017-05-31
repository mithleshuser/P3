package com.arya.spring.jee.demo.dao;

import java.util.List;

import com.arya.spring.jee.demo.dto.FilePropertiseDTO;

public interface FileuploadingDAO {
	public void saveFile(FilePropertiseDTO filePropertise);

	public List<FilePropertiseDTO> getFileList();

	public FilePropertiseDTO getFileById(int fileid);

	public boolean doDeleateRow(int id);

	public void getSaveDBtoXlsx();

}