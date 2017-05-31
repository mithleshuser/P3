<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>  
  <div style="color: teal; font-size: 15px"> List of Employees Details 
    <h3>Employees</h3>
    <c:if  test="${!empty employeeList}">
    <table border="1" bgcolor="black" width="600px">
    <tr style="background-color: teal; color: white; text-align: center;" height="40px">
        <th>RVK ID</th>  
        <th>USER NAME</th> 
        <th>EMAIL ID</th>  
        <th>PHON NUMBER</th> 
        <th>PASSWORD</th>
        <th>Edit</th> 
        <th>Delete</th> 
    </tr>
    <c:forEach items="${employeeList}" var="emp">
        <tr style="background-color: white; color: black; text-align: center;"height="30px">
            <td>${emp.rvk_id}</td>
            <td>${emp.uname}</td>
            <td>${emp.uemail} </td>
            <td>${emp.upnonNo}</td>
            <td>${emp.upassword}</td>
            <td><a href="edit?id=${user.id}">Edit</a></td>  
      		<td><a href="delete?id=${user.id}">Delete</a></td> 
        </tr>
    </c:forEach>
    </table>
    </c:if>
    </div>
    </center>
</body>
</html>