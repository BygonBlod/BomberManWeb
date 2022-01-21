<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Test</title>
    </head>
    <body>
    <%@ include file="menu.jsp"  %>
    <form action="" method="get" >
		    <label for="name">Enter your name: </label>
		    <input type="text" name="name" id="name" required></br>
		    <label for="pwd">Enter your password: </label>
		    <input type="password" name="pwd" id="pwd" required></br>
		    <input type="submit" value="Connection!">
	</form>
	<p>Si vous n'avez pas de compte vous pouvez vous inscrire ici :<a href="/Oui/inscription">inscription</a></p>
    </body>
</html>