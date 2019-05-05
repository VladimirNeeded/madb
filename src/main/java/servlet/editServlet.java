package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/editPage")
public class editServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String adminOrUser = req.getParameter("value");
        String choice = req.getParameter("button");
        String login;
        if (adminOrUser.equals("Edit my account")){
            login = req.getParameter("login");
        }
        else {
            login = adminOrUser;
        }
        String newPassword = req.getParameter("password");
        String newName = req.getParameter("name");
        String newSurname = req.getParameter("surname");

        if (choice.equals("Change Password") && newPassword != "") {
            UserDao.updateValue("password", newPassword, login);
            out.print("Password was changed");
        }else if (choice.equals("Change name") && newName != ""){
            UserDao.updateValue("name", newName, login);
            out.print("Name was changed");
        }else if (choice.equals("Change surname") && newSurname != "") {
            UserDao.updateValue("surname", newSurname, login);
            out.print("Surname was changed");
        }else if (choice.equals("Delete account")){
            UserDao.deleteAccount(login);
            out.print("Account deleted");
        }
    }
}