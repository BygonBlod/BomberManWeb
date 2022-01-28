package Bean;

import BD.UserSQL;

public class User {
	private int id;
	private String name;
	private String password;
	private boolean isConnect;
	private boolean wrongConnect;
	private UserSQL userSql;

	public User(UserSQL u) {
		this.userSql = u;
		this.wrongConnect = false;
	}

	public boolean exist() {
		if (userSql.connexion(name, password))
			return true;
		else
			return false;
	}

	public void connection() {
		if (!isConnect) {
			if (userSql.connexion(name, password)) {
				this.isConnect = true;
				this.wrongConnect = false;
				System.out.println("vous êtes connecté");
			} else {
				this.wrongConnect = true;
				System.out.println("ce compte n'existe pas");
			}
		}
	}

	public void deconnection() {
		if (isConnect) {
			this.isConnect = false;
		}
	}

	public void inscription() {
		if (!this.exist()) {
			if (userSql.ajouterUser(name, password))
				this.isConnect = true;
		}
	}

	public boolean isConnect() {
		return isConnect;
	}

	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isWrongConnect() {
		return wrongConnect;
	}

	public void setWrongConnect(boolean wrongConnect) {
		this.wrongConnect = wrongConnect;
	}

}
