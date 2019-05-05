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
import java.util.Optional;

@WebServlet(value = "/account")
public class accountServlet extends HttpServlet {

    Connection connection = DbConnector.connect();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Optional<String> role = UserDao.selectRole(login);
        User user = new User(name, surname, login, password);
        String sqlPassword = UserDao.selectPassword(user.getLogin()).get();
        if (password.equals(sqlPassword)){
            req.setAttribute("login", login);
            if (role.isPresent() && role.get().equals("admin")) {
                req.setAttribute("list", UserDao.selectUsers().get());
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            }else {
                req.getRequestDispatcher("/userPage.jsp").forward(req, resp);
            }
        }else {
            out.print("Login or password are not valid");
        }
    }
}