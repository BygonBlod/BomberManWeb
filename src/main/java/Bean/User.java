package Bean;

import BD.UserSQL;

public class User {
	private int id;
	private String name;
	private String password;
	private int nbParty;
	private int nbWin;
	private boolean isConnect;
	private boolean wrongConnect;
	private UserSQL userSql;

	public User(UserSQL u) {
		this.userSql = u;
		this.wrongConnect = false;
		nbParty = 0;
		nbWin = 0;
	}

	public User() {
		this.wrongConnect = false;
		nbParty = 0;
		nbWin = 0;
	}

	public boolean exist() {
		if (userSql.connexion(name, password) != null)
			return true;
		else
			return false;
	}

	public void connection() {
		if (!isConnect) {
			User use = userSql.connexion(name, password);
			if (use != null) {
				this.isConnect = true;
				this.wrongConnect = false;
				this.nbParty = use.getNbParty();
				this.nbWin = use.getNbWin();
				System.out.println("vous êtes connecté");
			} else {
				this.wrongConnect = true;
				System.out.println("ce compte n'existe pas");
			}
		}
	}

	public void deconnection() {
		if (isConnect) {
			System.out.println("déconnection");
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbParty() {
		return nbParty;
	}

	public void setNbParty(int nbParty) {
		this.nbParty = nbParty;
	}

	public int getNbWin() {
		return nbWin;
	}

	public void setNbWin(int nbWin) {
		this.nbWin = nbWin;
	}

	public boolean changeNBParty(String win) {
		return userSql.changeNbParty(name, Integer.parseInt(win));
	}

}
