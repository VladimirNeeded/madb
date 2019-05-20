package servlet;

import dao.GoodDaoSQL;
import dao.UserDao;
import dao.UserDaoSQL;
import dao.UserDaoHibImpl;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/account")
public class accountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(accountServlet.class);
    private static final GoodDaoSQL goodDao = new GoodDaoSQL();
    private static final UserDao userDao = new UserDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String passwordFromForm = req.getParameter("password");
       // String role = UserDaoSQL.selectRole(login).get();
        User user = userDao.getUser(login).get();
        String rightPassword = UserDaoSQL.selectPassword(user.getLogin()).get();

        if (HashUtil.getSHA512SecurePassword(passwordFromForm).equals(rightPassword)) {
            req.setAttribute("login", login);
            req.getSession().setAttribute("user", user);
            LOGGER.info("Sign In was successfully");
            if (user.getRole().equals("admin")) {
                LOGGER.info("To the account sign in admin " + user.getLogin());
                req.setAttribute("users", userDao.getAllUsers().get());
                req.setAttribute("goods", goodDao.getAllGoods());
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else {
                LOGGER.info("To the account sign in user " + user.getLogin());
                req.getRequestDispatcher("/goods").forward(req, resp);
            }
        } else {
            req.setAttribute("isLogin", false);
            req.getRequestDispatcher("/Sign_In.jsp").forward(req, resp);
        }
    }
}