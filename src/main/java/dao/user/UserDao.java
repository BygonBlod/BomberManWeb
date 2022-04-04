package dao.user;

import Bean.User;

public interface UserDao {

	boolean inscrire(String name, String password);

	boolean exist(String name);

	boolean changeNbParty(String name, int nb);

	User get(String name);

	void delete(String name);

	boolean login(String name, String password);

}
