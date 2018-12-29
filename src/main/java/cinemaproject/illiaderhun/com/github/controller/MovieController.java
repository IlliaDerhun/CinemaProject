package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.service.MovieService;

public class MovieController {

    public Movie getMovieForIndexPage() {
        int movieId = 1;
        return new MovieService().read(movieId);
    }
}
