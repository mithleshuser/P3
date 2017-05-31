<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="border: 10px solid; margin: 0% 10%; padding-bottom: 90%; position: relative; color: black; background-color: #FDFDD7">

		<div style="border: 5px solid; margin: 0% 0%; position: relative; color: black; background-color: teal; color: white;">
			<center>
				<h1>LOGIN MODULE</h1>
			</center>
		</div>
		
		<div style="border: 1px solid; margin: 0% 20%; position: relative; color: black; background-color: pink;">
			<center>
				<h1>For Reset Password</h1>
				<form method="post" action="ReSetPWD" enctype="multipart/form-data">
<hr>
					<table border="0">
					
						<tr>
							<td></td>
							<td><input type="text" name="varificationcode" size="50" placeholder="Varification Code"/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="password" name="newpassword" size="50" placeholder="Chose New Password"/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="password" name="confirmPassword" size="50" placeholder="Confirm Password"/></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="logVerify" /></td>
							<td>
							<th colspan="2"><a href="loginOption">Login Option !!</a></th>
							</td>
						</tr>

					</table>
					<hr>
				</form>
			</center>
		</div>
	</div>
</body>
</html>