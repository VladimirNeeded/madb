package servlet;

import dao.DbConnector;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(value = "/adminPage")
public class adminPageServlet extends HttpServlet {

    Connection connection = DbConnector.connect();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        PrintWriter out = resp.getWriter();
        String choice = req.getParameter("button");
        String login = req.getParameter("login");
//        String userLogin = req.getParameter("loginUser");
//        String newPassword = req.getParameter("password");
//        String newName = req.getParameter("name");
//        String newSurname = req.getParameter("surname");

        try {
            req.setAttribute("value", choice);
            req.setAttribute("login", login);
            req.getRequestDispatcher("editPage.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }

//        try {
//            out.print(login);
//            req.getRequestDispatcher("editPage.jsp").forward(req, resp);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
    }
}