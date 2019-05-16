package servlet;

import dao.DbConnector;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(value = "/adminPage")
public class adminPageServlet extends HttpServlet {

    Connection connection = DbConnector.connect();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String choice = req.getParameter("button");

        try {

            if (choice.equals("Edit")) {
                String goodID = req.getParameter("id");
                req.setAttribute("id", goodID);
                req.getRequestDispatcher("editGoods.jsp").forward(req, resp);
            }else {
                req.setAttribute("login", choice);
                req.getRequestDispatcher("editUsers.jsp").forward(req, resp);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}