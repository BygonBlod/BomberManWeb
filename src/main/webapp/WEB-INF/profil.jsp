<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profil</title>
	</head>
	<body>
		<%@ include file="menu.jsp"  %>
		
		<table>
		<thead><tr><td></td><td>Vos informations</td></tr></thead>
		<tbody>
			<tr><td>pseudo</td><td><c:out value="${ user.name }"></c:out></td></tr>
			<tr><td>nombre de partie jouer</td><td><c:out value="${ user.nbParty }"></c:out></td></tr>
			<tr><td>nombre de partie gagner</td><td><c:out value="${ user.nbWin }"></c:out></td></tr>
		</tbody>
		</table>
	</body>
</html>