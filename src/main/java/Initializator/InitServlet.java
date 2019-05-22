package Initializator;

import dao.UserDao;
import dao.UserDaoHibImpl;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Init", loadOnStartup = 1)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        UserDao userDao = new UserDaoHibImpl();
        User user = new User(0,"Vladimir", "Tretyak", "VladmirTRE", "123", "voha.tretyak@gmail.com", "admin");
        userDao.addUser(user);
    }
}