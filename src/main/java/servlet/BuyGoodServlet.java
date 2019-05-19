package servlet;

import Service.MailService;
import dao.CodeDao;
import model.Code;
import model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buy")
public class BuyGoodServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(BuyGoodServlet.class);
    MailService mailService = new MailService();
    CodeDao codeDao = new CodeDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int goodId = Integer.parseInt(req.getParameter("goodId"));
        String codeValue = req.getParameter("code");
        User user = (User) req.getSession().getAttribute("user");
        Code code = new Code(codeValue, user.getId(), goodId);
        if (codeDao.checkCode(code)){
            req.setAttribute("isPay", true);
            req.getRequestDispatcher("/afterBuy.jsp").forward(req, resp);
            LOGGER.info("Payment was successful");
        }else {
            req.setAttribute("isNotPay", true);
            req.getRequestDispatcher("/afterBuy.jsp").forward(req, resp);
            LOGGER.info("Payment failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer buyId = Integer.parseInt(req.getParameter("id"));
        String codeValue = mailService.sendMail(user.getEmail());
        LOGGER.info("Code was send to the email: " + user.getEmail());
        Code codeOnMail = new Code(codeValue, user.getId(), buyId);
        codeDao.addCode(codeOnMail);
        req.setAttribute("userId", user.getId());
        req.setAttribute("goodId", buyId);
        req.setAttribute("valueMailCode", codeValue);
        req.getRequestDispatcher("writeBuyCode.jsp").forward(req, resp);
    }
}