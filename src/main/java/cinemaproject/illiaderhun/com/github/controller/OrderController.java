package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.service.OrderService;

import java.util.ArrayList;

public class OrderController {

    public ArrayList<Order> getOrderForIndexPage(int scheduleId) {
        return new OrderService().readByScheduleId(scheduleId);
    }

    public ArrayList<Order> getCustomerOrders(Integer userId) {
        return new OrderService().readByScheduleId(userId);
    }
}
