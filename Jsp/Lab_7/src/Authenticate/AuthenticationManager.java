package Authenticate;

import DB.Manager;
import Model.User;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Set;

public class AuthenticationManager {

    public static String authenticate(String username, String password) {
        ResultSet rs;
        String result = "error";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from users where username='" + username + "' and password='" + password + "'");
            if (rs.next()) {

                result = "success";
            }
            rs.close();
            Manager.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}