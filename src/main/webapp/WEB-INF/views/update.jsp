<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	table{
	margin : auto;
	background-color : LightSeaGreen;
	}
</style>
</head>
<body style="background-color : Beige">
<h1 style="text-align : center;">Update Book</h1>
<form action="update" method="post">
		<table style="with: 50%" border="2">
			<tr>
				<td>Note Id</td>
				<td><input type="text" name="noteId" value="${note.noteId}" /></td>
			</tr>
			<tr>
				<td>Title</td>
				<td><input type="text" name="noteTitle"value="${note.noteTitle}" /></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><input type="text" name="noteContent" value="${note.noteContent}" /></td>
			</tr>
			<tr>
				<td>Status</td>
				<td><input type="text" name="noteStatus" value="${note.noteStatus}" /></td>
			</tr>
			<tr>
				<td>Created At</td>
				<td><input type="text" name="createdAt" value="${note.createdAt}" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"	value="Update Note" /></td>
			</tr>

		</table>
	</form>

</body>
</html>