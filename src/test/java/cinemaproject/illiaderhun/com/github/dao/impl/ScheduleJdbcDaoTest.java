package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.service.ScheduleService;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScheduleJdbcDaoTest {

    private Schedule expectedSchedule;
    private Schedule someSchedule;
    private ScheduleService scheduleService;

    @Before
    public void setUp() throws Exception {
        expectedSchedule = new Schedule.Builder(new Timestamp(1234L), "Monday")
                .setId(1)
                .setMovieId(1)
                .setOrderId(1)
                .build();

        someSchedule = new Schedule.Builder(new Timestamp(1234L), "Monday")
                .setMovieId(1)
                .setOrderId(1)
                .build();

        scheduleService = new ScheduleService();
    }

    @Test
    public void create() {
        assertTrue(scheduleService.create(someSchedule));
        scheduleService.delete(someSchedule.getId());
    }

    @Test
    public void readShouldReturnValidEntityByValidId() {
        Schedule actualSchedule = scheduleService.read(1);
        assertEquals(expectedSchedule.toString(), actualSchedule.toString());
    }


    @Test
    public void update() {
        scheduleService.create(someSchedule);
        someSchedule.setDayOfWeek("Friday");
        assertTrue(scheduleService.update(someSchedule));
        scheduleService.delete(someSchedule.getId());
    }

    @Test
    public void delete() {
        scheduleService.create(someSchedule);
        assertTrue(scheduleService.delete(someSchedule.getId()));
    }
}