package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static boolean isExist(String email) throws SQLException {
        Connection con = MyConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("select email from user");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String e = rs.getString(1);
            if (e.equals(email)) {
                return true;
            }
        }

        return false;


    }

    public static int saveUser(User user) throws SQLException {
        Connection con = MyConnection.getConnection();

        PreparedStatement ps = con.prepareStatement("insert into user (name, email, password) values (?,?,?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        int ans = ps.executeUpdate();

        return ans;
    }

    public static String userLogin(User user) throws SQLException {
        Connection con = MyConnection.getConnection();
        ResultSet rs = null;
        String name = null;
        PreparedStatement ps = con.prepareStatement("Select name from user where email = ? and password = ?");


        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());

        rs = ps.executeQuery();


        if (rs.next()) {
            System.out.println(rs.getString("name"));
            name = rs.getString("name");

        }

        return name;


    }

}
