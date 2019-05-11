package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editPage")
public class editServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            req.setAttribute("changePassword", true);
            req.getRequestDispatcher("/editPage.jsp").forward(req, resp);
        }else if (choice.equals("Change name") && newName != ""){
            UserDao.updateValue("name", newName, login);
            req.setAttribute("changeName", true);
            req.getRequestDispatcher("/editPage.jsp").forward(req, resp);
        }else if (choice.equals("Change surname") && newSurname != "") {
            UserDao.updateValue("surname", newSurname, login);
            req.setAttribute("changeSurname", true);
            req.getRequestDispatcher("/editPage.jsp").forward(req, resp);
        }else if (choice.equals("Delete account")){
            UserDao.deleteAccount(login);
            req.setAttribute("deleteAccount", true);
            req.getRequestDispatcher("/editPage.jsp").forward(req, resp);
        }
    }
}