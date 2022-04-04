package dao.user;

import Bean.User;

import java.util.Set;

public interface UserDao {

    boolean inscrire(String name, String password);
    boolean exist(String name);
    void update(User user);
    void delete(User user);
    boolean login(String name, String password);
    User findById(int unId);
    Set<User> findAll();

}
