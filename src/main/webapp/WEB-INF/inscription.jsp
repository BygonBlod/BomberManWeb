
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<%@ include file="menu.jsp"  %>
	<c:if test="${sessionScope.user.exist()}">
		<p>Cette utilisateur existe déjà</p>
	</c:if>

    <form action="inscription" method="post" >
		    <label for="name">Enter your name: </label>
		    <input type="text" name="name" id="name" required></br
		    <label for="pwd">Enter your password: </label>
		    <input type="password" name="pwd" id="pwd" required></br>
		    <input type="submit" value="Inscription!">
	</form>
</body>
</html>