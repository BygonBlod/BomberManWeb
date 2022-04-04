package dao.factory;

import dao.user.UserDao;

public class DBDaoFactory implements DaoFactory {

    private static DBDaoFactory dbdaoFactory = null;
    @Override
    public UserDao getUserDao() {
        return null;
    }

    public static DBDaoFactory getInstance() {
        if (dbdaoFactory == null){
            dbdaoFactory = new DBDaoFactory();
        }

        return dbdaoFactory;
    }
}
