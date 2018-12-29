package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.service.OrderService;

import java.util.ArrayList;

public class OrderController {

    public ArrayList<Order> getOrderForIndexPage(int scheduleId) {
        return new OrderService().readByScheduleId(scheduleId);
    }

    public ArrayList<Integer> getRowPlace (int scheduleId) {
        ArrayList<Integer> rowPlaces = new ArrayList<>();
        getOrderForIndexPage(scheduleId).stream()
                .forEach(row -> rowPlaces.add(row.getRow()));
        return rowPlaces;
    }
    public ArrayList<Integer> getColPlace (int scheduleId) {
        ArrayList<Integer> colPlaces = new ArrayList<>();
        getOrderForIndexPage(scheduleId).stream()
                .forEach(row -> colPlaces.add(row.getCol()));
        return colPlaces;
    }

}
