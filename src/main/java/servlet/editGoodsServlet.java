package servlet;

import dao.GoodDao;
import dao.GoodDaoSql;
import model.Goods;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editGood")
public class editGoodsServlet extends HttpServlet {
    private static GoodDao goodDao = new GoodDaoSql();
    private static final Logger LOGGER = Logger.getLogger(editGoodsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String choice = req.getParameter("button");
        String idGood = req.getParameter("id");

        Goods good = goodDao.getGoodById(Integer.parseInt(idGood)).get();

        String newDescription = req.getParameter("Description");
        String newName = req.getParameter("name");
        Double newPrice = Double.parseDouble(req.getParameter("Price"));

        if (choice.equals("Change Description") && newDescription != "") {
            Goods newGood = new Goods(good.getId(), good.getName(), newDescription, good.getPrice());
            goodDao.updateValue(newGood);
            req.setAttribute("changePassword", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            LOGGER.info("Description of good changed");

        } else if (choice.equals("Change name") && newName != ""){
            Goods newGood = new Goods(good.getId(), newName, good.getDescription(), good.getPrice());
            goodDao.updateValue(newGood);
            req.setAttribute("changeName", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            LOGGER.info("Name of good changed");

        } else if (choice.equals("Change Price") && newPrice != null) {
            Goods newGood = new Goods(good.getId(), good.getName(), good.getDescription(), newPrice);
            goodDao.updateValue(newGood);
            req.setAttribute("changePrice", true);
            req.getRequestDispatcher("/editGoods.jsp").forward(req, resp);
            LOGGER.info("Price of good changed");
        }
    }
}