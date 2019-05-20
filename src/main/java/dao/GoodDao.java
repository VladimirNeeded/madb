package dao;

import model.Goods;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GoodDao {

    private static final Logger LOGGER = Logger.getLogger(GoodDao.class);

    private final static Connection CONNECTION = DbConnector.connect();

    public void addGood(Goods good) {
        try {
            String sqlAdd = "INSERT INTO `mate_academy`.`goods` (`name`,`description`,`price`) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sqlAdd);
            preparedStatement.setString(1, good.getName());
            preparedStatement.setString(2, good.getDescription());
            preparedStatement.setDouble(3, good.getPrice());
            preparedStatement.execute();
            LOGGER.info("Product '" + good.getName() + "' added");
        } catch (SQLException e) {
            LOGGER.error("Cant' add product", e);
        }
    }

    public Optional<Goods> getGoodById (int id) {
        try {
            String sqlSelect = "SELECT * FROM mate_academy.goods WHERE id = ?;";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, id);
            ResultSet resultGood = preparedStatement.executeQuery();
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
            Statement statement = CONNECTION.createStatement();
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

    public static void updateValue (String whatChange, String newValue, String id){
        try {
            String sqlUpdate = "UPDATE `mate_academy`.`goods` SET `" + whatChange +"` = '" + newValue + "' " +
                    "WHERE `id` = ?;";
            PreparedStatement preparedStatement = CONNECTION.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            LOGGER.info(whatChange + "was update");
        } catch (SQLException e) {
            LOGGER.error("Can't update values", e);
        }
    }
}