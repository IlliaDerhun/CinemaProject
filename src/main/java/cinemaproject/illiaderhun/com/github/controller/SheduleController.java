package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.service.ScheduleService;

public class SheduleController {

    public Schedule getSheduleForIndexPage(int scheduleId) {
        return new ScheduleService().read(scheduleId);
    }

}
