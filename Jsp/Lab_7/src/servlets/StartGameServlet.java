package servlets;

import DB.Manager;
import Model.User;

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

@WebServlet("/StartGameServlet")
public class StartGameServlet extends HttpServlet {
    protected synchronized void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getPlayerNo() < 2) {
            String sql = "SELECT * FROM noofplayerslogged";
            try {
                PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
                ResultSet result = stmt.executeQuery();
                int PlayersLogged = -1;
                if (result.next()) {
                    PlayersLogged = result.getInt("NoOfPlayers");

                }
                req.getSession().setAttribute("NoOfPlayers", PlayersLogged);
                if (PlayersLogged >= 2) {
                    CreateBoard(req.getSession());
                    resp.sendRedirect("/board.jsp");
                } else {
                    resp.sendRedirect("/success.jsp");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                resp.sendRedirect("/success.jsp");
            }
        } else {
            resp.sendRedirect("/success.jsp");
        }


    }

    public void CreateBoard(HttpSession session) {
        String sql = "SELECT * FROM boardstate";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                String Board = result.getString("BoardState");
                int ActivePlayer = result.getInt("ActivePlayer");
                session.setAttribute("Board", Board);
                session.setAttribute("activePlayer", ActivePlayer);
            } else {
                User user = (User) session.getAttribute("user");
                int id = user.getPlayerNo();
                sql = "INSERT INTO boardstate(BoardState, ActivePlayer) VALUES ('         ', "+id+")";
                stmt = Manager.getConnection().prepareStatement(sql);
                stmt.execute();
                sql = "SELECT * FROM boardstate";
                stmt = Manager.getConnection().prepareStatement(sql);
                result = stmt.executeQuery();
                String Board = result.getString("BoardState");
                int ActivePlayer = result.getInt("ActivePlayer");
                session.setAttribute("Board", Board);
                session.setAttribute("activePlayer", ActivePlayer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
    }


}
