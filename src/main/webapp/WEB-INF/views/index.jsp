<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	h1{
	text-align: center;
	}
	
	.t1{
	background-color : Coral;
	margin:auto;
	}
	
	.t2{
	background-color : Lavender;
	margin:auto;
	}
	
	div{
	text-align: center;
	}
</style>
</head>
<body style="background-color : CadetBlue;"></body>
	<h1>Add Note</h1>
	<form action="add" method="post">
		<table class="t1" style="with: 50%" border="2">
			<tr>
				<td>Title</td>
				<td><input type="text" name="noteTitle" /></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><input type="text" name="noteContent" /></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><input type="text" name="noteStatus" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"	value="Add Note" /></td>
			</tr>
		</table>
	</form>

	<br>
	<br>

	<c:if test="${not empty error}">
	<div>
	   <p style="color:red">Error</p>: ${error}
	</div>
	</c:if>
	
	<h1>Note List</h1>
	<table class="t2" style="with: 50%" border="2">
		<tr>
			<td>Note ID</td>
			<td>Title</td>
			<td>Content</td>
			<td>Status</td>
			<td>Created at</td>
		</tr>
		
			<c:forEach items="${noteList}" var="note">
			<tr>
				<td>${note.noteId}</td>
				<td>${note.noteTitle}</td>
				<td>${note.noteContent}</td>
				<td>${note.noteStatus}</td>
				<td>${note.createdAt}</td>
				<td><a href="delete?noteId=${note.noteId}"><button>Delete</button></a></td>
				<td><a href="updateNote?noteId=${note.noteId}"><button>Update</button></a></td>
			</tr>
		</c:forEach>
	</table>