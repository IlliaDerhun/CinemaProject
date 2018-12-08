package cinemaproject.illiaderhun.com.github.service;

import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.dao.impl.ScheduleJdbcDao;
import cinemaproject.illiaderhun.com.github.dao.interfaces.ScheduleDao;
import org.apache.log4j.Logger;

public class ScheduleService implements ScheduleDao<Schedule, Integer> {

    private static final Logger LOGGER = Logger.getLogger(ScheduleService.class.getSimpleName());
    private ScheduleJdbcDao scheduleJdbcDao;

    @Override
    public boolean create(Schedule theEntity) {
        LOGGER.info("method create start with: " + theEntity);
        checkScheduleJdbcDaoAndCreateIfDoesntExist();
        return scheduleJdbcDao.create(theEntity);
    }

    @Override
    public Schedule read(Integer entityId) {
        LOGGER.info("method read start with: " + entityId);
        checkScheduleJdbcDaoAndCreateIfDoesntExist();
        return scheduleJdbcDao.read(entityId);
    }

    @Override
    public boolean update(Schedule theEntity) {
        LOGGER.info("method update start with: " + theEntity);
        checkScheduleJdbcDaoAndCreateIfDoesntExist();
        return scheduleJdbcDao.update(theEntity);
    }

    @Override
    public boolean delete(Integer entityId) {
        LOGGER.info("method delete start with: " + entityId);
        checkScheduleJdbcDaoAndCreateIfDoesntExist();
        return scheduleJdbcDao.delete(entityId);
    }

    private void checkScheduleJdbcDaoAndCreateIfDoesntExist() {
        if (scheduleJdbcDao == null) {
            scheduleJdbcDao = new ScheduleJdbcDao();
        }
    }
}
