package com.arya.spring.jee.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.arya.spring.jee.demo.dto.FilePropertiseDTO;
import com.arya.spring.jee.demo.dto.RegDTO;
import com.arya.spring.jee.demo.service.FileuploadingService;
import com.arya.spring.jee.demo.service.RegService;
import com.arya.spring.jee.demo.util.EncryptUtils;

@Controller
@RequestMapping("/")
public class RegLoginController {
	@Autowired
	public RegService regService;
	@Autowired
	private FileuploadingService fileuploadingService;
	
	private String saveDirectory = "E:/SERVERLOC/";
	
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	@RequestMapping(value = "/regs", method = RequestMethod.POST)
	public String saveRegInfo(RegDTO regdto, ModelMap map) {
		boolean flage = false;
		flage = regService.saveUser(regdto);
		if (flage) 
		{
			map.put("msg", " Successfully your Reg has Done, your Id is " + regdto.getRvk_id());
		} else {
			System.out.println("erreo page");
			return "error";
		}
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/LoginWithEmailID")
	public String userLogin1(RegDTO regdto, ModelMap map, HttpServletRequest request) {
		try {
			RegDTO dto = regService.loginWithEmailID(regdto);
			HttpSession htpsession = request.getSession(true);
			htpsession.setAttribute("firstName", dto.getUname());
			map.put("msg", "Mr." + dto.getUname() + " succefully logged In");
			System.out.println("Login successfull by " + dto.getUname());
			return "home";
		} catch (NullPointerException e) {
			map.put("msg", "Invalid User ID !!");
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/LoginWithNumber")
	public String userLogin2(RegDTO regdto, ModelMap map) {
		try {
			RegDTO dto = regService.loginWithPhonNo(regdto);
			map.put("msg", "Mr." + dto.getUname() + " succefully logged In");
			System.out.println("Login successfull by " + dto.getUname());
			return "loginsuccess";
		} catch (NullPointerException e) {
			map.put("msg", "Invalid User Phon Number !!");
			return "error";
		}
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/LoginWithUserName")
	public String userLogin3(RegDTO regdto, ModelMap map) {
		try {
			RegDTO dto = regService.loginWithUseName(regdto);
			map.put("msg", "Mr." + dto.getUname() + " succefully logged In");
			System.out.println("Login successfull by " + dto.getUname());
			return "loginsuccess";
		} catch (NullPointerException e) {
			map.put("msg", "Invalid User Phon Number !!");
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/SearchByrvkID")
	public String searchByRvkId(RegDTO regdto, ModelMap map) {
		try {
			RegDTO dto = regService.searchUserInfo(regdto);
			map.put("msg", "Hi Mr." + dto.getUname() + "Your RVKID :" + dto.getRvk_id() + "\t" + "your Pwd: "
					+ dto.getUpassword());
			System.out.println("Login successfull by " + dto.getUemail());
			return "loginsuccess";
		} catch (NullPointerException e) {
			map.put("msg", "This user does not exist !!");
			return "error";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
    public String getAllEmployees(ModelMap map)
    {
       map.addAttribute("employeeList", regService.getList());
        return "list";
    }
	

	// search you rvk id details ,
	@RequestMapping(method = RequestMethod.GET, value = "srarchByid")
	public String getSearchPage() {
		return "search";
	}

	// forgot password!! so request for search page your password page
	@RequestMapping(method = RequestMethod.GET, value = "searchyourpwd")
	public String getsearchyourpwd() {
		return "searchPwd";
	}

	// send email for url to reset new password
	@RequestMapping(method = RequestMethod.POST, value = "/sendemailForPwd")
	public String userLogin(RegDTO emaildto, ModelMap map) throws Exception {
		boolean ismailSend = false;
		String subject = "Hi Friend, you forgot password so you are here!!";
		String message1 = "Follow this instruction you can change your password by using some code which are auto generated, before seating your new password you must have to you this code= { ";

		// Request for enhancement
		// String useeForgetenpwd =
		// regService.getUserForgotenPwd(emaildto,emaildto.getUemail());
		// String rendomPWD = EncryptUtils.encodeUrl(useeForgetenpwd);

		String rendomPWD = EncryptUtils.getRandomPassword();
		String message2 = " }please click on this link , it will navigate you to that page where you can re-set your password !!";
		String url = "http://localhost:8080/J2EEPROJECT/resetpassword.jsp";
		// url = EncryptUtils.encodeUrl(url);
		ismailSend = regService.sendMainService(emaildto.getUemail(), subject, message1, rendomPWD, message2, url);
		if (ismailSend) {
			map.put("msg", " One mail has send to " + emaildto.getUemail() + " please check your mail");
		} else {
			regService.sendPreConfiguredMail("Due to technical probalam i am unable to help you ");
			map.put("msg"," Due to some technical problam this id  " + emaildto.getUemail() + "Unable to help this user");
			return "error";
		}
		return "confirmationmail";
	}
	// after seating new password you will get confirmation
	@RequestMapping(method = RequestMethod.POST, value = "/ReSetPWD")
	public String reSetPassword1(RegDTO regdto, ModelMap map, @RequestParam String varificationcode, String newpassword,
			String confirmPassword) {
		try {
			boolean isUpdateNewPwd = false;
			String db_rendom_Pwd = regService.getUserRendomPwd(regdto, varificationcode);
			if (varificationcode.equals(db_rendom_Pwd)) {
				isUpdateNewPwd = regService.isUpdateNewPwd(confirmPassword, varificationcode);
				if (isUpdateNewPwd) {
					regService.setNullRendomPwd(varificationcode);
		 			map.put("msg1", " Hii Mr. " + regdto.getUname()+ " you have succesfully change your password, Now you can login with new password");
				} else {
					map.put("msg", " Hii Mr. XYZ due to network problam you are unable to reset your passeord ");
					return "error";
					
				}
			}		
			map.put("msg", "you have succesfully chang your password !!");
			return "index";
		} catch (NullPointerException e) {
			map.put("msg", "Invalid User ID !!");
			return "error";
		}
	}

	// click forgot pwd get index page
	@RequestMapping(method = RequestMethod.GET, value = "loginOption")
	public String getIndexPage() {
		return "index";
	}

	// link all ready registor go to index page/ i have change need inhance or remove it 
	@RequestMapping(method = RequestMethod.GET, value = "alreadyregistered")
	public String goToIndexPage() {
		return "index";
	}

	// click "Login with e-mail" link go to loginwithemail.jsp
	@RequestMapping(method = RequestMethod.GET, value = "emailLogin")
	public String getLoginTypePage1() {
		return "loginwithemail";
	}

	// login with user name
	@RequestMapping(method = RequestMethod.GET, value = "usernamelogin")
	public String getLoginTypePage2() {
		return "loginwithusername";
	}

	// login with Phon number
	@RequestMapping(method = RequestMethod.GET, value = "phonnumberlogin")
	public String getLoginTypePage3() {
		return "loginwithpnumber";
	}

	// new registration
	@RequestMapping(method = RequestMethod.GET, value = "newreg")
	public String getNewRegistration() {
		return "reg";
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile")
	public String handleFileUpload(HttpServletRequest request,ModelMap map, @RequestParam CommonsMultipartFile[] fileUpload)throws Exception {
		System.out.println("description: " + request.getParameter("description"));
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(saveDirectory + aFile.getOriginalFilename()));
					System.out.println("Saving file: " + aFile.getOriginalFilename());
					FilePropertiseDTO filePropertise = new FilePropertiseDTO();  
					filePropertise.setFlocation(aFile.getStorageDescription());
					filePropertise.setFname(aFile.getOriginalFilename());
					filePropertise.setFsize(aFile.getSize());
					filePropertise.setFdate(new Date());
					
					System.out.println("date : " + filePropertise.getFdate());
					System.out.println("File Location : " + filePropertise.getFlocation());
					System.out.println("File size : " + filePropertise.getFsize());
					System.out.println("File Name : " + filePropertise.getFname());
					System.out.println("=======Ready for save to the database ======");
					fileuploadingService.saveFile(filePropertise);
				}
			}
		}else{
			return "Error";
		}
		map.put("msg","Your file aplod success ");
		return "home";
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/download/{id}" )
	public String downloadDocument(ModelMap map,@PathVariable int id, HttpServletResponse response) throws IOException {
	
	try{	
		FilePropertiseDTO fileObject = fileuploadingService.getFileById(id);
		//response.setContentType("application/force-download");   help!
		System.out.println("fileObject.getFsize()  "+fileObject.getFsize());
		response.setContentType(fileObject.getFname());
		response.setContentLength((int) fileObject.getFsize());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileObject.getFname() + "\"");
		String fullPath ="E://SERVERLOC//" + fileObject.getFname();
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		//-----------------------------------------------
		String mimeType = fileObject.getFname();
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		String headerKey = "Content-Disposition";
		System.out.println("downloadFile.getName() "+downloadFile.getName());
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		System.out.println("headerValue  :"+headerValue);
		response.setHeader(headerKey, headerValue);
		//----------------------------------------------------
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.flush();
		outStream.close();
	}catch(Exception e){
		//LOGGER.debug("Request could not be completed at this moment. Please try again. MITHLESH");
		e.printStackTrace();
	}
		return "home";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    public String getDeleatRow(ModelMap map,@PathVariable int id, HttpServletResponse response)
    {
		System.out.println("hii i am going to deleate");
		if(fileuploadingService.doDeleateRow(id)){
			map.put("msg", "one row has deleatede "+id);
		}else{
			map.put("msg", "some thing is wrong during deleation of this id = "+id);
		}
        return "home";
    }
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/viewallFile")
    public String getAllFileList(ModelMap map)
    {
       map.addAttribute("fileList", fileuploadingService.getFileList());
        return "home";
    }
	
	/*Generated exls file things testDataFilePath */
	@RequestMapping(method = RequestMethod.GET, value = "/downlodDBTable/intoEXLSfile")
    public String getSaveDBtoXlsx(ModelMap map,HttpServletRequest request, HttpServletResponse response)
    {
		System.out.println("yes i am fine /downlodDBTable/intoEXLSfile");
		fileuploadingService.getSaveDBtoXlsx();
		try {
			fileuploadingService.downloadXlsxFill(request,response);
		} catch (IOException e) {
			System.out.println("downloadXlsxFill() in this method ");
			e.printStackTrace();
		}
        return "home";
    }
	
}
