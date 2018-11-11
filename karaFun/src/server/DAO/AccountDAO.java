/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.DAO;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import share.ConnectDB;

/**
 *
 * @author Sy Phung
 */
public class AccountDAO extends ConnectDB {

    public AccountDAO() {
        super();
    }

    public static ArrayList<Account> getAllAccount() throws SQLException {
        ArrayList<Account> a = new ArrayList<>();
        String sql = "SELECT * FROM account";
        Statement s = (Statement) conn.prepareStatement(sql);

        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Account a1 = new Account(rs.getString("Username"), rs.getString("password"));
            a.add(a1);
        }
        return a;
    }

    public Account getAccount(String username) throws SQLException {
        String sql = "SELECT * FROM account WHERE Username LIKE ?";
        PreparedStatement s = conn.prepareStatement(sql);
        s.setString(1, username);
        ResultSet rs = s.executeQuery();
        if (rs.next()) {
            Account a1 = new Account(rs.getString("Username"), rs.getString("password"));
            return a1;
        } else {
            return null;
        }
    }

    public boolean insert(Account c) throws SQLException {
        try {
            String sql = "INSERT INTO `account` ( `Username`, `Password`) VALUES ( ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, c.getUsername());
            ps.setString(2, c.getPassword());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
//    public static void main(String[] args) throws SQLException {
//        AccountDAO a = new AccountDAO();
////        Account a1 = a.getAccount("tab");
////        System.out.println(a1.getPassword());
//        a.insert(new Account("", ""));
//    }
}
