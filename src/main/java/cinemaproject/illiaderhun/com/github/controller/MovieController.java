package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.service.MovieService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieController {

    public Movie getMovieForIndexPage() {
        int movieId = 1;
        return new MovieService().read(movieId);
    }

    public Movie getMovieById(int movieId) {
        return new MovieService().read(movieId);
    }

    public ArrayList<Movie> getMovieForManagerPage() {
        return new MovieService().getAllMovies();
    }

    public void addNewMovie(String title, String description, String time, String price, String schedule) throws ParseException {
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date parsedDate = dateFormat.parse(time);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());*/

        Movie theMovie = new Movie.Builder(title, description)
                .setTime(time)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setScheduleId(Integer.parseInt(schedule))
                .build();

        new MovieService().create(theMovie);
    }
}
