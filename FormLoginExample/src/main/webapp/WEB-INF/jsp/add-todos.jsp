<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Add ToDos....</h1>

	<div align="center">
		<form:form action="/add-todos" method="post" modelAttribute="todo">
		<font color="red">${errorMessage }</font>
			<table border="3">
				<tr>
					<td>Id:</td>
					<td><form:input path="id" /></td>
					<td><font color="red"> <form:errors path="id"></form:errors></font>
					</td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><form:input path="desc" /></td>
					<td><font color="red"> <form:errors path="desc"></form:errors>
					</font></td>
				</tr>
				<tr>
					<td><input type="submit" value="add"></td>
				</tr>
			</table>
		</form:form>
	</div>


</body>
</html>