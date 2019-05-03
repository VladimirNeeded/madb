package servlet;

import dao.DbConnector;
import dao.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(value = "/account")
public class AccountServlet extends HttpServlet {

    Connection connection = DbConnector.connect();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String choice = req.getParameter("button");
        String login = req.getParameter("login");
        String newPassword = req.getParameter("password");
        String newName = req.getParameter("name");
        String newSurname = req.getParameter("surname");

        if (choice.equals("Change Password") && newPassword != null) {
            UserDao.updateValue("password", newPassword, login);
            out.print("Password was changed");
        }else if (choice.equals("Change name") && newName != null){
            UserDao.updateValue("name", newName, login);
            out.print("Name was changed");
        }else if (choice.equals("Change surname") && newSurname != null) {
            UserDao.updateValue("surname", newSurname, login);
            out.print("Surname was changed");
        }else if (choice.equals("Delete account")){
            UserDao.deleteAccount(login);
            out.print("Account deleted");
        }
    }
}