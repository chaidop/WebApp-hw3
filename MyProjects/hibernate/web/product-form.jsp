<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="eng">
<body>
	<head>
		<title> Welcome to our Shop!</title>
		<style>
			body{
				background-image:url(astronomy.jpg);
				background-color:magenta;
				text-shadow:2px 2px 5px grey;
				color: white;
			}

			form{
				margin: auto;
				width: 50%;
				border: 3px solid pink;
				padding: 10px;
			}
		</style>
	</head>
	<h1 align="center">Product Submit Page</h1>
		<form action="<%=request.getContextPath() %>/" method="post">			
		Please insert your products characteristics
		<p>
			Barcode:
			<input name="barcode" required/><br>
			Name:
			<input name="name"  required/><br>
			Color:
			<input name="color" required/><br>
			Description:
			<input name="description" required/><br>
			<br><br>
			
			<center>
			<input type="submit" value="Save" >
		</center>
	    </form>       
k</body>
</html>
