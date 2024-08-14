package dao;

import db.MyConnection;
import model.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDao {


    public static boolean accountExist(String email) throws SQLException {

        String e = email;
        ResultSet rs = null;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("select email from accounts ");
        rs = ps.executeQuery();
        while (rs.next()) {
            if (e.equals(rs.getString(1))) {
                return true;
            }
        }

        return false;
    }

    public static boolean fetchAccountDetails(Data data) throws SQLException {

        Connection con = MyConnection.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = con.prepareStatement("select * from accounts where ano = ? and email = ? and " +
                "security_pin = ?");
        ps.setString(1, data.getAccount_number());
        ps.setString(2, data.getEmail());
        ps.setString(3, data.getSecurity_pin());

        rs = ps.executeQuery();


        if (rs != null) { // Check if ResultSet is not null
            while (rs.next()) {
                System.out.println();
                System.out.print("Account NO : ");
                System.out.println(rs.getString(1) + " ");
                System.out.print("Account Holder : ");
                System.out.println(rs.getString(2) + " ");
                System.out.print("Email : ");
                System.out.println(rs.getString(3) + " ");
                System.out.print("Security Pin : ");
                System.out.println(rs.getString(4)+ " ");
                System.out.print("Balance : ");
                System.out.println(rs.getFloat(5)+ " ");
                System.out.println();
                System.out.println();
                return true;
            }
        }

        return false;
    }

    public static int saveAccount(Data data) throws SQLException {


        ResultSet rs = null;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into accounts ( ano, name , email, security_pin, balance " +
                ")" +
                " " +
                "values (?,?,?,?,?)");
        ps.setString(1, data.getAccount_number());
        ps.setString(2, data.getName());
        ps.setString(3,data.getEmail());
        ps.setString(4,data.getSecurity_pin());
        ps.setFloat(5, data.getBalance());
        int ans = ps.executeUpdate();


        return ans;
    }

    public static int transactionMoney(Data sender, Data receiver, float amount) throws SQLException
    {
        float amt = amount;
        ResultSet rs = null;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("update accounts set balance = balance - ? where ano = ?");
        ps.setFloat(1, amt);
        ps.setString(2, sender.getAccount_number());
        int ans = ps.executeUpdate();

        ps= con.prepareStatement("update accounts set balance = balance +  ? where ano = ?");
        ps.setFloat(1, amt);
        ps.setString(2, receiver.getAccount_number());
        int ans2 = ps.executeUpdate();

        if(ans == 1 && ans2 == 1)
        {
            return 1;
        }

        return 0;
    }

    public static int creditMoney(Data sender, float amount) throws SQLException
    {
        float amt = amount;
        ResultSet rs = null;
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("update accounts set balance = balance - ? where ano = ?");
        ps.setFloat(1, amt);
        ps.setString(2, sender.getAccount_number());
        int ans = ps.executeUpdate();

        return ans;
    }
}
