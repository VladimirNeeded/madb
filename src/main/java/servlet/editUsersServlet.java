package servlet;

import dao.UserDao;
import dao.UserDaoHibImpl;
import dao.UserDaoSql;
import model.User;
import org.apache.log4j.Logger;
import utils.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editUser")
public class editUsersServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(editUsersServlet.class);
    private static UserDao userDao = new UserDaoHibImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String choice = req.getParameter("button");
        User user = userDao.getUser(UserDaoSql.getId(login)).get();

        String newPassword = HashUtil.getSHA512SecurePassword(req.getParameter("password"));
        String newName = req.getParameter("name");
        String newSurname = req.getParameter("surname");

        if (choice.equals("Change Password") && newPassword != "") {
            User newUser = new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), newPassword, user.getEmail(), user.getRole());
            userDao.updateValue(newUser);
            req.setAttribute("changePassword", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            LOGGER.info(login + "'s password changed");
        } else if (choice.equals("Change name") && newName != ""){
            User newUser = new User(user.getId(), newName, user.getSurname(), user.getLogin(), user.getPassword(), user.getEmail(), user.getRole());
            userDao.updateValue(newUser);
            req.setAttribute("changeName", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            LOGGER.info(login + "'s name changed");
        } else if (choice.equals("Change surname") && newSurname != "") {
            User newUser = new User(user.getId(), user.getName(), newSurname, user.getLogin(), user.getPassword(), user.getEmail(), user.getRole());
            userDao.updateValue(newUser);
            req.setAttribute("changeSurname", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            LOGGER.info(login + "'s surname changed");
        } else if (choice.equals("Delete account")){
            userDao.deleteAccount(user);
            req.setAttribute("deleteAccount", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            LOGGER.info("Account '" + login + "' deleted");
        }
    }
}