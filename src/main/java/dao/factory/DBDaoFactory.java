package dao.factory;

import dao.user.UserDao;
import dao.user.UserDb;

public class DBDaoFactory implements DaoFactory {

	private static DBDaoFactory dbdaoFactory = null;

	@Override
	public UserDao getUserDao() {
		return new UserDb();
	}

	public static DBDaoFactory getInstance() {
		if (dbdaoFactory == null) {
			dbdaoFactory = new DBDaoFactory();
		}

		return dbdaoFactory;
	}
}
