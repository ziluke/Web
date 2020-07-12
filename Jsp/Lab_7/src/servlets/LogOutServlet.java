package servlets;

import Authenticate.AuthenticationManager;
import DB.Manager;
import Model.User;
import Controller.UserController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@WebServlet("/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected synchronized void  doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        checkNoOfPlayers();
        User user=(User) req.getSession().getAttribute("user");
        deleteLoggedUser(user.getUsername());
        resp.sendRedirect("/index.jsp");

    }
    private void checkNoOfPlayers()
    {
        String sql="SELECT * FROM noofplayerslogged";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                int PlayersLogged = result.getInt("NoOfPlayers");
                int decrement = PlayersLogged - 1;
                if(decrement<=0) {
                    DestroyBoard();
                    decrement = 0;
                }
                sql = "Update noofplayerslogged SET NoOfPlayers=" + decrement + ";";
                stmt = Manager.getConnection().prepareStatement(sql);
                stmt.execute();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void DestroyBoard() {
        String update = "UPDATE  boardstate SET BoardState='---------', ActivePlayer=0;";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(update);
            stmt.execute();


        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


    public synchronized void deleteLoggedUser(String username) {
        String sql = "SELECT * FROM users  WHERE username='" + username + "';";
        int id = -1;
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                id = result.getInt("ID");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (id != -1) {
            sql = "DELETE FROM loggedplayers where userID='" + id + "';";
            try {
                PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
                stmt.execute();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}