package dao.user;

import Bean.User;
import dao.engie.SqlEngine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UserDb implements UserDao {

    private final SqlEngine sqlEngine;

    public UserDb() {
        this.sqlEngine = SqlEngine.getInstance();
    }

    @Override
    public boolean inscrire(String name, String password) {
        PreparedStatement query = null;
        int resultat = -1;
        System.out.println("ajout");
        password = hash(password);
        try {
            query = this.sqlEngine.getConnection().prepareStatement("insert into Users (name,password,nbParty,nbWin) values (?,?,?,?) ;");
            query.setString(1, name);
            query.setString(2, password);
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

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public boolean login(String name, String password) {
        return false;
    }

    @Override
    public User findById(int unId) {
        return null;
    }

    @Override
    public Set<User> findAll() {
        return null;
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
