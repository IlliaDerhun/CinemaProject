package cinemaproject.illiaderhun.com.github.service;

import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.impl.MovieJdbcDao;
import cinemaproject.illiaderhun.com.github.dao.interfaces.MovieDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class MovieService implements MovieDao<Movie, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MovieService.class.getSimpleName());
    private MovieJdbcDao movieJdbcDao;

    @Override
    public Movie readByTitle(String title) {
        LOGGER.info("method readByTitle start with title: " + title);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.readByTitle(title);
    }

    @Override
    public Movie readByScheduleId(Integer scheduleId) {
        LOGGER.info("method readByScheduleId start with scheduleId: " + scheduleId);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.readByScheduleId(scheduleId);
    }

    @Override
    public boolean create(Movie theEntity) {
        LOGGER.info("method create start with theEntity: " + theEntity);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.create(theEntity);
    }

    @Override
    public Movie read(Integer entityId) {
        LOGGER.info("method read start with entityId: " + entityId);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.read(entityId);
    }

    @Override
    public boolean update(Movie theEntity) {
        LOGGER.info("method update start with theEntity: " + theEntity);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.update(theEntity);
    }

    @Override
    public boolean delete(Integer entityId) {
        LOGGER.info("method delete start with entityId: " + entityId);
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.delete(entityId);
    }

    public ArrayList<Movie> getAllMovies() {
        LOGGER.info("method getAllMovies start");
        checkMovieJdbcDaoAndCreateIfDoesntExist();
        return movieJdbcDao.getAllMovies();
    }

    private void checkMovieJdbcDaoAndCreateIfDoesntExist() {
        if (movieJdbcDao == null) {
            movieJdbcDao = new MovieJdbcDao();
        }
    }
}
