package servlet;

import dao.DbConnector;
import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet(value = "/account")
public class accountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Optional<String> role = UserDao.selectRole(login);
        User user = new User(name, surname, login, password);
        String sqlPassword = UserDao.selectPassword(user.getLogin()).get();
        if (password.equals(sqlPassword)) {
            req.setAttribute("login", login);
            if (role.isPresent() && role.get().equals(Roles.admin)) {
                req.setAttribute("list", UserDao.selectUsers().get());
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/marketPlace.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("isLogin", false);
            req.getRequestDispatcher("/Sign_In.jsp").forward(req, resp);
        }
    }
    enum Roles {
        admin,
        user
    }
}