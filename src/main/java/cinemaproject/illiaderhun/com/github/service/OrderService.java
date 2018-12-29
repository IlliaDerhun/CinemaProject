package cinemaproject.illiaderhun.com.github.service;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.impl.OrderJdbcDao;
import cinemaproject.illiaderhun.com.github.dao.interfaces.OrderDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class OrderService implements OrderDao<Order, Integer> {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getSimpleName());
    private OrderJdbcDao orderJdbcDao;

    @Override
    public Order readByUserId(Integer userId) {
        LOGGER.info("method readByUserId start with id: " + userId);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.readByUserId(userId);
    }

    @Override
    public ArrayList<Order> readByScheduleId(Integer scheduleId) {
        LOGGER.info("method readByScheduleId start with id: " + scheduleId);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.readByScheduleId(scheduleId);
    }

    @Override
    public boolean create(Order theEntity) {
        LOGGER.info("method create start with order: " + theEntity);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.create(theEntity);
    }

    @Override
    public Order read(Integer entityId) {
        LOGGER.info("method read start with id: " + entityId);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.read(entityId);
    }

    @Override
    public boolean update(Order theEntity) {
        LOGGER.info("method update start with order: " + theEntity);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.update(theEntity);
    }

    @Override
    public boolean delete(Integer entityId) {
        LOGGER.info("method delete start with order: " + entityId);
        checkOrderJdbcDaoAndCreateIfDoesntExist();
        return orderJdbcDao.delete(entityId);
    }

    private void checkOrderJdbcDaoAndCreateIfDoesntExist() {
        if (orderJdbcDao == null) {
            orderJdbcDao = new OrderJdbcDao();
        }
    }
}
