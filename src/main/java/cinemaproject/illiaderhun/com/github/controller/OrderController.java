package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.service.MovieService;
import cinemaproject.illiaderhun.com.github.service.OrderService;

import java.util.ArrayList;

public class OrderController {

    public ArrayList<Order> getOrderForIndexPage(int scheduleId) {
        return new OrderService().readByScheduleId(scheduleId);
    }

    public ArrayList<Order> getCustomerOrders(Integer userId) {
        ArrayList<Order> orders = new OrderService().readByUserId(userId);
        SсheduleController sсheduleController = new SсheduleController();
        MovieController movieController = new MovieController();

        orders.forEach(
                order -> order.setDate(sсheduleController.getSсheduleForIndexPage(order.getScheduleId()).getDateTime())
        );

        orders.forEach(
                order -> {
                    Schedule theSchedule = sсheduleController.getSсheduleForIndexPage(order.getScheduleId());
                    order.setMovie(movieController.getMovieById(theSchedule.getMovieId()).getTitle());
                }
        );

        return orders;
    }

    public void makeOrder(Integer row, Integer col, Integer movieId, Integer userId) {
        Integer scheduleId = new MovieController().getMovieById(movieId).getScheduleId();
        Order theOrder = new Order(0, row, col, userId, scheduleId);
        new OrderService().create(theOrder);
    }
}
