package servlet;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/Registration")
public class RegistrationServlet extends HttpServlet {

    private static final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        userDao.addUser(new User(login, password));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Sign_In.jsp");
        requestDispatcher.forward(req, resp);
    }
}