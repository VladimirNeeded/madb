package servlet;

import dao.GoodDaoSql;
import model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (value = "/goods")
public class GoodsServlet extends HttpServlet {
    private static final GoodDaoSql goodDao = new GoodDaoSql();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> allGoods = goodDao.getAllGoods();
        req.setAttribute("goods", allGoods);
        req.getRequestDispatcher("marketPlace.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}