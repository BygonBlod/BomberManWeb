package BD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		pwd = hash(pwd);
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

	public boolean exist(String name) {
		PreparedStatement query = null;
		ResultSet resultat = null;
		try {
			query = connexion.prepareStatement("select * from Users where name=?  ;");
			query.setString(1, name);
			resultat = query.executeQuery();
			while (resultat.next()) {
				return true;
			}
			query.close();
			resultat.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

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

	public boolean changeNbParty(String name, int nbW) {
		PreparedStatement query = null;
		int resultat = -1;
		try {
			query = connexion
					.prepareStatement("update Users set nbParty=nbParty +1 ,nbWin=nbWin+" + nbW + " where name=? ;");
			query.setString(1, name);
			System.out.println(nbW);
			resultat = query.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultat == -1) {
			return false;
		} else
			return true;
	}

	public String hash(String pwd) {
		String res = "";

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(pwd.getBytes());

			byte byteData[] = md.digest();

			// convertir le tableau de bits en une format hexadécimal - méthode 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			res = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return res;
	}

}
