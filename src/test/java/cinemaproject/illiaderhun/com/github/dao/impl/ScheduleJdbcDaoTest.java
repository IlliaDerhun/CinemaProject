package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScheduleJdbcDaoTest {

    private Schedule expectedSchedule;
    private Schedule someSchedule;
    private ScheduleJdbcDao scheduleJdbcDao;

    @Before
    public void setUp() throws Exception {
        expectedSchedule = new Schedule.Builder(new Date(119, 10, 19), "Monday")
                .setId(1)
                .setMovieId(1)
                .setOrderId(1)
                .build();

        someSchedule = new Schedule.Builder(new Date(118, 10, 20), "Monday")
                .setMovieId(1)
                .setOrderId(1)
                .build();

        scheduleJdbcDao = new ScheduleJdbcDao();
    }

    @Test
    public void create() {
        assertTrue(scheduleJdbcDao.create(someSchedule));
        scheduleJdbcDao.delete(someSchedule.getId());
    }

    @Test
    public void readShouldReturnValidEntityByValidId() {
        Schedule actualSchedule = scheduleJdbcDao.read(1);
        assertEquals(expectedSchedule.toString(), actualSchedule.toString());
    }


    @Test
    public void update() {
        scheduleJdbcDao.create(someSchedule);
        someSchedule.setDayOfWeek("Friday");
        assertTrue(scheduleJdbcDao.update(someSchedule));
        scheduleJdbcDao.delete(someSchedule.getId());
    }

    @Test
    public void delete() {
        scheduleJdbcDao.create(someSchedule);
        assertTrue(scheduleJdbcDao.delete(someSchedule.getId()));
    }
}