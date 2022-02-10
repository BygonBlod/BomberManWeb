
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
    </head>
    <body>
    <%@ include file="menu.jsp"  %>
    <c:if test="${sessionScope.user.isWrongConnect()}">
		<p>Mauvais pseudo ou mot de passe</p>
	</c:if>
    <form action="" method="post" >
		    <label for="name">Entrer votre pseudo: </label>
		    <input type="text" name="name" id="name" required></br>
		    <label for="pwd">Entrer votre mot de passe: </label>
		    <input type="password" name="pwd" id="pwd" required></br>
		    <input type="submit" value="Connexion!">
	</form>
	<p>Si vous n'avez pas de compte vous pouvez vous inscrire ici :<a href="/Oui/inscription">inscription</a></p>
    </body>
</html>