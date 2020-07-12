package Controller;

import DB.Manager;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserController {

    public static synchronized User createUserWithLoggedPlayers(String username,int PlayersLogged) {
        String sql = "SELECT * FROM users  WHERE username='" + username + "';";
        User user=new User();
        user.setPlayerNo(PlayersLogged);
        int id = -1;
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                user.setUsername(result.getString("username"));
                id = result.getInt("ID");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        if (id != -1) {
            sql = "INSERT INTO loggedplayers(userID) values ('" + id + "');";
            try {
                PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
                stmt.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
        }
        return user;
    }
}