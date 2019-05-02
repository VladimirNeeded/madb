package servlet;

import dao.DbConnector;
import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(value = "/Sign_In")
public class SignInServlet extends HttpServlet {

    Connection connection = DbConnector.connect();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = new User(login, password);
        String sqlPassword = UserDao.selectPassword(user.getLogin());
        if (password.equals(sqlPassword)){
            req.setAttribute("login", login);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/account.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            out.print("Login or password are not valid");
        }
    }
}
