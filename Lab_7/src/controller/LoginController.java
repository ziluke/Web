package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Manager.*;
import domain.User;

public class LoginController extends HttpServlet {

    public LoginController() {
        super();
    }

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        RequestDispatcher rd = null;
        DBManager manager = new DBManager();
        User user = manager.authenticate(username, password);
        if (user != null) {
            HttpSession session = req.getSession();
            int ct;
            if (session.getAttribute("nrPlayers") == null)
                ct = 0;
            else
                ct = (int) session.getAttribute("nrPlayers");
            if (ct < 2) {
                rd = req.getRequestDispatcher("/success.jsp");
                session.setAttribute("user", user);
                session.setAttribute("nrPlayers", ct + 1);
            } else {
                rd = req.getRequestDispatcher("/error.jsp");
            }
        } else {
            rd = req.getRequestDispatcher("/error.jsp");
        }

        rd.forward(req, resp);
    }
}
