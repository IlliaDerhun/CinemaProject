package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.service.OrderService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderJdbcDaoTest {

    private Order expectedOrder;
    private Order someOrder;
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        expectedOrder = new Order(4, 1, 2, 9, 1);

        someOrder = new Order(0, 1, 3, 9, 1);

        orderService = new OrderService();
    }

    @Test
    public void readByUserId() {
        assertEquals(expectedOrder, orderService.readByUserId(9));
    }

    @Test
    public void readByScheduleId() {
        System.out.println(orderService.readByScheduleId(1));
    }

    @Test
    public void create() {
        assertTrue(orderService.create(someOrder));
        orderService.delete(someOrder.getId());
    }

    @Test
    public void read() {
        assertEquals(expectedOrder, orderService.read(4));
    }

    @Test
    public void update() {
        orderService.create(someOrder);
        someOrder.setCol(78);
        someOrder.setRow(87);
        orderService.update(someOrder);
        assertEquals(someOrder, orderService.read(someOrder.getId()));
        orderService.delete(someOrder.getId());
    }

    @Test
    public void delete() {
        orderService.create(someOrder);
        someOrder = orderService.read(someOrder.getId());
        assertTrue(orderService.delete(someOrder.getId()));
    }
}