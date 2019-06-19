<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>First Web Application</title>
</head>
<body>
	<h1 align="center">Here are the list of your todos:</h1>
	<h3 align="left">
		<a href="/add-todos"> Add To do Task</a>
	</h3>
	<div>
	<h3 align="center"><font color="red">${errorMessage } </font></h3>
		<table align="center" border="2">
			<tr>
				<th>ID</th>
				<th>Todo Desc</th>
				<th>Target Date</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.id}</td>
							<td>${todo.desc}</td>
							<td>${todo.targetDate}</td>
							<td><a href="/edit-todos/${todo.id}">Edit</a> &nbsp; <a
								href="/delete-todos/${todo.id}">Delete</a></td>
						</tr>
					</c:forEach>
			</tr>
		</table>
	</div>
	<h3 align="left">
		<a href="/logout">logout</a>
	</h3>
</body>