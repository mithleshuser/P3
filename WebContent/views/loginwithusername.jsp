<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="border: 10px solid; margin: 0% 10%; padding-bottom: 90%; position: relative; color: black; background-color: #FDFDD7;">

		<div
			style="border: 5px solid; margin: 0% 0%; position: relative; color: black; background-color: teal; color: white;">
			<center>
				<h1>LOGIN MODULE</h1>
			</center>
		</div>
		<div style="border: 1px solid; margin: 0% 20%; position: relative; color: black; background-color: pink;">
			<center>
				<h1>LOGIN WITH USER NAME</h1>
					<form method="post" action="LoginWithUserName" enctype="multipart/form-data">

					<table border="0">
						<tr>
							<td>User Name:</td><td><input type="text" name="uname" size="50" /></td>
						</tr>
						<tr>
							<td>password:</td><td><input type="password" name="upassword" size="50" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="submit"value="Login" /></td>
							<td><th colspan="2"><a href="loginOption">Login Option!!</a></th></td>
						</tr>
					</table>
				</form>
			</center>
		</div>
	</div>
</body>
</html>