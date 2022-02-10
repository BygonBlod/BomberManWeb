
<!DOCTYPE html>
<ul>
	<li><a href="/Oui/">Accueil</a></li>
	<c:if test="${!sessionScope.user.isConnect()}">
		<li><a href="/Oui/connexion">Connexion</a></li>
	</c:if>
	<c:if test="${sessionScope.user.isConnect()}">
		<li><a href="/Oui/connecter/profil">votre profil</a></li>
		<li><a href="/Oui/deconnexion">déconnexion</a></li>
	</c:if>
	
	
	
</ul>