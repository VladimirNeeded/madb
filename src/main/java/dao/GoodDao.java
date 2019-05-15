package dao;

import model.Goods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodDao {

    private final static Connection connection = DbConnector.connect();

    public void addGood(Goods good) {
        try {
            Statement statement = connection.createStatement();
            String sqlAdd = "INSERT INTO `mate_academy`.`goods` (`name`,`description`,`price`) " +
                    "VALUES ('" + good.getName() + "', '" + good.getDescription() +"', '"
                    + good.getPrice() + "');";
            statement.execute(sqlAdd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Goods> getGoodById (int id) {
        try {
            Statement statement = connection.createStatement();
            String sqlSelect = "SELECT * FROM mate_academy.goods WHERE id = '" + id + "';";
            ResultSet resultGood = statement.executeQuery(sqlSelect);
            while (resultGood.next()) {
                int goodId = resultGood.getInt("id");
                String name = resultGood.getString("name");
                String description = resultGood.getString("description");
                double price = resultGood.getDouble("price");
                Optional<Goods> resGood = Optional.of(new Goods(goodId, name, description, price));
                return resGood;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Goods> getAllGoods () {
        List<Goods> allGoods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sqlSelect = "SELECT * FROM mate_academy.goods";
            ResultSet resultGood = statement.executeQuery(sqlSelect);
            while (resultGood.next()) {
                int goodId = resultGood.getInt("id");
                String name = resultGood.getString("name");
                String description = resultGood.getString("description");
                double price = resultGood.getDouble("price");
                allGoods.add(new Goods(goodId, name, description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allGoods;
    }
}