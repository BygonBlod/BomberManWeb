
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
</head>
<body>
	<%@ include file="menu.jsp"  %>
	<c:if test="${sessionScope.user.exist()}">
		<p>Cet utilisateur existe déjà</p>
	</c:if>

    <form action="inscription" method="post" >
		    <label for="name">entrer votre pseudo: </label>
		    <input type="text" name="name" id="name" required></br
		    <label for="pwd">entrer votre mot de passe : </label>
		    <input type="password" name="pwd" id="pwd" required></br>
		    <input type="submit" value="Inscription!">
	</form>
</body>
</html>