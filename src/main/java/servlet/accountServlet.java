package servlet;

import dao.GoodDao;
import dao.UserDao;
import model.Goods;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/account")
public class accountServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(accountServlet.class);
    private static final GoodDao goodDao = new GoodDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String passwordFromForm = req.getParameter("password");
        String role = UserDao.selectRole(login).get();
        User user = UserDao.getUser(login).get();
        String rightPassword = UserDao.selectPassword(user.getLogin()).get();

        if (HashUtil.getSHA512SecurePassword(passwordFromForm).equals(rightPassword)) {
            req.setAttribute("login", login);
            req.getSession().setAttribute("user", user);
            logger.info("Sign In was successfully");
            if (role.equals("admin")) {
                logger.info("To the account sign in admin " + user.getLogin());
                List<Goods> allGoods = goodDao.getAllGoods();
                req.setAttribute("list", UserDao.selectUsers().get());
                req.setAttribute("goods", allGoods);
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else {
                logger.info("To the account sign in user " + user.getLogin());
                req.getRequestDispatcher("/goods").forward(req, resp);
            }
        } else {
            req.setAttribute("isLogin", false);
            req.getRequestDispatcher("/Sign_In.jsp").forward(req, resp);
        }
    }
}