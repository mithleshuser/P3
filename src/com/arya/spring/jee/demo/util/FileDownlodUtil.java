package com.arya.spring.jee.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class FileDownlodUtil {

	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(method = RequestMethod.GET)
	public static void downloadXlsxFill(HttpServletRequest request, HttpServletResponse response) throws IOException {
System.out.println(" *************Hello Brother   ***************");
		ServletContext context = request.getServletContext();
		String fullPath = Constants.testDataFilePath;
		File downloadFile = new File(fullPath);
		System.out.println("downloadFile  " + downloadFile);
		// get MIME type of the file
		String mimeType = context.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		System.out.println("downloadFile.getName() " + downloadFile.getName());
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		System.out.println("headerValue  :" + headerValue);
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		FileInputStream inputStream = new FileInputStream(downloadFile);
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();

	}

}
