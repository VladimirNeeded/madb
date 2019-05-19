package servlet;

import dao.GoodDao;
import dao.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editGood")
public class editGoodsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(editGoodsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String choice = req.getParameter("button");
        String idGood = req.getParameter("id");

        String newDescription = req.getParameter("Description");
        String newName = req.getParameter("name");
        String newPrice = req.getParameter("Price");

        if (choice.equals("Change Description") && newDescription != "") {
            GoodDao.updateValue("Description", newDescription, idGood);
            req.setAttribute("changePassword", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            logger.info("Description of good changed");

        } else if (choice.equals("Change name") && newName != ""){
            GoodDao.updateValue("name", newName, idGood);
            req.setAttribute("changeName", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            logger.info("Name of good changed");

        } else if (choice.equals("Change Price") && newPrice != "") {
            GoodDao.updateValue("Price", newPrice, idGood);
            req.setAttribute("changePrice", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            logger.info("Price of good changed");
        }
    }
}