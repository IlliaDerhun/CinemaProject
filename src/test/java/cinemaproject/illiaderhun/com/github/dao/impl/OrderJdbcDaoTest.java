package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderJdbcDaoTest {

    private Order expectedOrder;
    private Order someOrder;
    private OrderJdbcDao orderJdbcDao;

    @Before
    public void setUp() throws Exception {
        expectedOrder = new Order(4, 1, 2, 9, 1);

        someOrder = new Order(0, 1, 3, 9, 1);

        orderJdbcDao = new OrderJdbcDao();
    }

    @Test
    public void readByUserId() {
        assertEquals(expectedOrder, orderJdbcDao.readByUserId(9));
    }

    @Test
    public void create() {
        assertTrue(orderJdbcDao.create(someOrder));
        orderJdbcDao.delete(someOrder.getId());
    }

    @Test
    public void read() {
        assertEquals(expectedOrder, orderJdbcDao.read(4));
    }

    @Test
    public void update() {
        orderJdbcDao.create(someOrder);
        someOrder.setCol(78);
        someOrder.setRow(87);
        orderJdbcDao.update(someOrder);
        assertEquals(someOrder, orderJdbcDao.read(someOrder.getId()));
        orderJdbcDao.delete(someOrder.getId());
    }

    @Test
    public void delete() {
        orderJdbcDao.create(someOrder);
        someOrder = orderJdbcDao.read(someOrder.getId());
        assertTrue(orderJdbcDao.delete(someOrder.getId()));
    }
}