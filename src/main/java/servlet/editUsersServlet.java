package servlet;

import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editUser")
public class editUsersServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(editUsersServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String choice = req.getParameter("button");

        String newPassword = req.getParameter("password");
        String newName = req.getParameter("name");
        String newSurname = req.getParameter("surname");

        if (choice.equals("Change Password") && newPassword != "") {
            UserDao.updateValue("password", newPassword, login);
            req.setAttribute("changePassword", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            logger.info(login + "'s password changed");
        } else if (choice.equals("Change name") && newName != ""){
            UserDao.updateValue("name", newName, login);
            req.setAttribute("changeName", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            logger.info(login + "'s name changed");
        } else if (choice.equals("Change surname") && newSurname != "") {
            UserDao.updateValue("surname", newSurname, login);
            req.setAttribute("changeSurname", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            logger.info(login + "'s surname changed");
        } else if (choice.equals("Delete account")){
            UserDao.deleteAccount(login);
            req.setAttribute("deleteAccount", true);
            req.getRequestDispatcher("/editUsers.jsp").forward(req, resp);
            logger.info("Account '" + login + "' deleted");
        }
    }
}