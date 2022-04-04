
<!DOCTYPE html>
<ul>
	<li><a href="/BomberManWeb/">Accueil</a></li>
	<c:if test="${!sessionScope.user.isConnect()}">
		<li><a href="/BomberManWeb/connexion">Connexion</a></li>
	</c:if>
	<c:if test="${sessionScope.user.isConnect()}">
		<li><a href="/BomberManWeb/connecter/profil">votre profil</a></li>
		<li><a href="/BomberManWeb/connecter/deconnexion">déconnexion</a></li>
	</c:if>	
</ul>