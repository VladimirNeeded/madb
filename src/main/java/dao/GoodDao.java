package dao;

import model.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodDao {

    public void addGood(Goods good);

    public Optional<Goods> getGoodById (int id);

    public List<Goods> getAllGoods ();

    public void updateValue(Goods goods);
}
