<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
	.xyz{
	width:200px;
	}
</style>
</head>
<body>
	<div style="border: 10px solid; margin: 0% 10%; padding-bottom: 90%; position: relative; color: black; background-color: #FDFDD7;">
		<div
			style="border: 5px solid; margin: 0% 0%; position: relative; color: black; background-color: teal; color: white;">
			<center>
				<h1>LOGIN MODULE</h1>
			</center>
		</div>
		<div style="border: 1px solid; margin: 0% 10%; position: relative; color: black; background-color: pink;">
			<center>
				<h1>--: BEFORE LOGIN NEED REGISTRATION :-- </h1>
				<form method="POST" action="regs">
		<center>
			<table border="1" width="65%" cellpadding="5">
			  
				<thead>
					<tr>
						<h1>ENTER INFORMATION HERE</h1>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="xyz">User Name</td>
						<td><input type="text" name="uname" value="" size="40" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Email Id</td>
						<td><input type="text" name="uemail" value="" size="40" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Phon No</td>
						<td><input type="text" name="upnonNo" value="" size="40" /></td>
						<td></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="upassword" value="" size="40" /></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" /></td>
						<td>
							<center>
								<input type="reset" value="Reset" />
							</center>
						</td>
						<td><a href="emailLogin">Already registered!!</a></td>
					</tr>

				</tbody>
			</table>
		</center>
	</form>
			</center>
		</div>
	</div>
</body>
</html>