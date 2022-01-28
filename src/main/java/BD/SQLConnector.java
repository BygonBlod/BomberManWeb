package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
	private static SQLConnector instance = null;
	private String url;
	private String user;
	private String pwd;

	public SQLConnector(String url, String username, String pwd) {
		this.url = url;
		this.user = username;
		this.pwd = pwd;
	}

	public static SQLConnector getInstance() {
		if (instance == null) {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				instance = new SQLConnector("jdbc:mariadb://localhost:3306/BombermanWeb", "etud", "Bygon");
				return instance;
			} catch (ClassNotFoundException e) {
				System.out.println("Classe non trouver");
				e.printStackTrace();
			}
		} else {
			System.out.println("existe");
			return instance;
		}
		return null;
	}

	public Connection getConnection() {
		Connection res = null;
		try {
			res = DriverManager.getConnection(this.url, this.user, this.pwd);
		} catch (SQLException e) {
			System.out.println("création de la connexion à la base de donnée ne s'est pas faite");
			e.printStackTrace();
		}
		return res;
	}

}
