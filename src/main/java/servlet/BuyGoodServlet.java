package servlet;

import Service.MailService;
import dao.CodeDao;
import model.Code;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buy")
public class BuyGoodServlet extends HttpServlet {
    MailService mailService = new MailService();
    CodeDao codeDao = new CodeDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer goodId = Integer.valueOf(req.getParameter("good_id"));
        String codeValue = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Code code = new Code(codeValue, user.getId(), goodId);
        if (codeDao.checkCode(code)){
            req.setAttribute("isPay", true);
            req.getRequestDispatcher("/marketPlace.jsp").forward(req, resp);
        }else {
            req.setAttribute("isNotPay", true);
            req.getRequestDispatcher("/marketPlace.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer buyId = Integer.parseInt(req.getParameter("id"));
        String codeValue = mailService.sendMail(user.getEmail());
        Code code = new Code(codeValue, user.getId(), buyId);
        req.setAttribute("good_id", buyId);
        req.getRequestDispatcher("writeBuyCode.jsp").forward(req, resp);
    }
}
