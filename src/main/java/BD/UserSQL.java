package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.User;

public class UserSQL {
	private Connection connexion;

	public UserSQL() {
		SQLConnector connect = SQLConnector.getInstance();
		connexion = connect.getConnection();

	}

	public User connexion(String name, String pwd) {

		PreparedStatement query = null;
		ResultSet resultat = null;
		try {
			query = connexion.prepareStatement("select * from Users where name=? and password=? ;");
			query.setString(1, name);
			query.setString(2, pwd);
			resultat = query.executeQuery();
			while (resultat.next()) {
				int idU = resultat.getInt("id");
				String nameU = resultat.getString("name");
				String pwdU = resultat.getString("password");
				User res = new User();
				int nbP = resultat.getInt("nbParty");
				int nbW = resultat.getInt("nbWin");
				res.setNbParty(nbP);
				res.setNbWin(nbW);
				return res;
			}
			query.close();
			resultat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean ajouterUser(String name, String pwd) {
		PreparedStatement query = null;
		int resultat = -1;
		System.out.println("ajout");
		try {
			query = connexion.prepareStatement("insert into Users (name,password,nbParty,nbWin) values (?,?,?,?) ;");
			query.setString(1, name);
			query.setString(2, pwd);
			query.setInt(3, 0);
			query.setInt(4, 0);
			resultat = query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat == -1) {
			return false;
		} else
			return true;
	}

	public void supprimerUser(String name) {
		PreparedStatement query = null;
		try {
			query = connexion.prepareStatement("delete from Users where name=? ;");
			query.setString(1, name);
			int resultat = query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
