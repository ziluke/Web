package Manager;

import domain.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DBManager {
    private Statement stmt;

    public DBManager() {
        connect();
    }

    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/tictactoe", "root", "");
            stmt = con.createStatement();

            System.out.println("All good, connected to DB!");
        } catch (Exception ex) {
            System.out.println("Connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public User authenticate(String username, String password) {
        ResultSet res;
        User usr = null;
        System.out.println(username + " " + password);
        try {
            res = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'");
            if (res.next()) {
                usr = new User(res.getInt("ID"), res.getString("username"), res.getString("password"));
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usr;
    }
}
