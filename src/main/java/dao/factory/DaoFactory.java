package dao.factory;

import dao.user.UserDao;

public interface DaoFactory {

    UserDao getUserDao();

}
