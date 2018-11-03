package cinemaproject.illiaderhun.com.github.dao.interfaces;

public interface OrderDao<Order, Integer> extends Dao<Order, Integer> {

    Order readByUserId(Integer userId);

}
