<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="border: 10px solid; margin: 0% 10%; padding-bottom: 90%; position: relative; color: black; background-color: pink;">

		<div style="border: 5px solid; margin: 0% 0%; position: relative; color: black; background-color: yellow;">
			<center>
				<h1>LOGIN MODULE (Index)</h1>
			</center>
		</div>
		<center>
	<form>
			<h1>${msg}</h1> 
			<h1>${msg1}</h1>
			<table border="0" >
	 			<tr>
					<a href="emailLogin" style="color: teal; font-size: 30px">Login with e-mail</a><br></br><hr></hr>
				</tr>
				<tr>
					<a href="usernamelogin" style="color: teal; font-size: 30px">Login with User name</a><br></br><hr></hr>
				</tr>
				<tr>
					<a href="phonnumberlogin" style="color: teal; font-size: 30px">login with Phon Number</a><br></br><hr></hr>
				</tr> 
				<tr>
					<a href="newreg" style="color: teal; font-size: 30px"> New Registration </a><br></br><hr></hr>
				</tr> 
				<tr>
					<!-- <a href="userlist"> User List </a><br></br><hr></hr> -->
  					<a href="list" style="color: teal; font-size: 30px">Click Here to see User List</a>  
				</tr>

			</table>
			
			
		<div
			style="border: 1px solid; margin: 0% 10%; position: relative; color: black; background-color: pink;">
			<center>
				<h1 style="color: teal; font-size: 20px">Login by Email Id/User Name / Phon Number</h1>
				<form method="post" action="LoginWithEmailID" enctype="multipart/form-data">

					<table border="0">
						<tr>
							<td style="color: teal;">Email Id</td>
							<td><input type="text" name="emailID" size="50" placeholder="Email Address/User Name/Phon Number"/></td>
						</tr>
						<tr>
							<td style="color: teal;">password:</td>
							<td><input type="password" name="emailPwd" size="50" placeholder="Password"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								value="Login" /></td>
							<td>
							<th colspan="2"><a href="srarchByid" >forgot password!!</a></th>
							</td>
						</tr>

					</table>
				</form>
			</center>
		</div>
	</div>
			
			
			
	</form>
	</center>
	
	</div>
</body>
</html>