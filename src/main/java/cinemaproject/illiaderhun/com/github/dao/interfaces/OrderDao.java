package cinemaproject.illiaderhun.com.github.dao.interfaces;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;

import java.util.ArrayList;

public interface OrderDao<Order, Integer> extends Dao<Order, Integer> {

    ArrayList<Order> readByUserId(Integer userId);
    ArrayList<Order> readByScheduleId(Integer scheduleId);

}
