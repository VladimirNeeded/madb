package servlet;

import dao.UserDao;
import dao.UserDaoHibImpl;
import dao.UserDaoSQL;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Registration")
public class RegistrationServlet extends HttpServlet {
    private static final UserDao userDao = new UserDaoHibImpl();
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String role = "user";
        userDao.addUser(new User(name, surname, login, password, email, role));
        req.setAttribute("isRegistered", true);
        LOGGER.info("Registration was successfully");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/Sign_In.jsp");
        requestDispatcher.forward(req, resp);
    }
}