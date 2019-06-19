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
	<h1>Add ToDos....</h1>

	<div>
		<form:form action="/edit-todos/${id}" method="post"	modelAttribute="edittodo">
			<table border="3">
				<tr>
					<td>Id:</td>
					<td><form:input path="id" disabled="true" /></td>
				</tr>
				<tr>
					<td>User:</td>
					<td><form:input path="user" disabled="true" />
				</tr>

				<tr>
					<td>Description:</td>
					<td><form:input path="desc" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Update"></td>
				</tr>
			</table>
		</form:form>
	</div>


</body>
</html>