package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieJdbcDaoTest {

    private Movie expectedMovie;
    private Movie someMovie;
    private MovieJdbcDao movieJdbcDao;

    @Before
    public void setUp() throws Exception {
        expectedMovie = new Movie.Builder("Мистер Бин", "Британский комедийный телесериал, состоящий из 14 получасовых серий.")
                .setId(1)
                .setTime("00:30:45")
                .setPoster("images/posters/MrBean")
                .setScheduleId(1)
                .build();

        expectedMovie.setPrice(7.45);

        someMovie = new Movie.Builder("SomeTitle", "SomeDescription")
                .setTime("02:45:35")
                .setPoster("Path to some poster")
                .setScheduleId(1)
                .build();

        someMovie.setPrice(75.00);

        movieJdbcDao = new MovieJdbcDao();
    }

    @Test
    public void readByTitle() {
        assertEquals(expectedMovie, movieJdbcDao.readByTitle("Мистер Бин"));
    }

    @Test
    public void readByScheduleId() {
        assertEquals(expectedMovie, movieJdbcDao.readByScheduleId(1));
    }

    @Test
    public void create() {
        assertTrue(movieJdbcDao.create(someMovie));
        movieJdbcDao.delete(someMovie.getId());
    }

    @Test
    public void read() {
        assertEquals(expectedMovie, movieJdbcDao.read(1));
    }

    @Test
    public void update() {
        movieJdbcDao.create(someMovie);
        someMovie.setDescription("Yet another description");
        someMovie.setTitle("Yet another title");
        someMovie.setPrice(8.45);
        someMovie.setTime("00:59:59");
        movieJdbcDao.update(someMovie);
        assertEquals(someMovie, movieJdbcDao.read(someMovie.getId()));
        movieJdbcDao.delete(someMovie.getId());
    }

    @Test
    public void delete() {
        movieJdbcDao.create(someMovie);
        assertTrue(movieJdbcDao.delete(someMovie.getId()));
    }
}