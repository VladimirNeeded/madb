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
        String newPassword = req.getParameter("new_password");
        if (choice.equals("Change Password") && newPassword != null) {
            UserDao.updatePassword(newPassword, login);
        }else if (choice.equals("Delete account")){
            UserDao.deleteAccount(login);
            out.print("Account deleted");
        }
    }
}