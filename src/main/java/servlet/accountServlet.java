package servlet;

import dao.GoodDao;
import dao.UserDao;
import model.Goods;
import model.User;
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
            if (role.equals("admin")) {
                List<Goods> allGoods = goodDao.getAllGoods();
                req.setAttribute("list", UserDao.selectUsers().get());
                req.setAttribute("goods", allGoods);
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/goods").forward(req, resp);
            }
        } else {
            req.setAttribute("isLogin", false);
            req.getRequestDispatcher("/Sign_In.jsp").forward(req, resp);
        }
    }
}