<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List Events</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Events</h2>
	<table>
		<tr>
			<td>NAME</td>
			<td>Create Date</td>
			<td>Description</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${events}" var="event">
			<tr>
			<td>${event.name}</td>
			<td>${event.createDate}</td>
			<td>${event.description}</td>
			<td><a href="<c:url value='/edit-${event.name}-event' />">update</a></td>
			<td><a href="<c:url value='/delete-${event.name}-event' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Events</a>
</body>
</html>