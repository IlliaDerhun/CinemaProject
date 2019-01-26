package cinemaproject.illiaderhun.com.github.dao.interfaces;

import java.util.ArrayList;

public interface MovieDao<Movie, Integer> extends Dao<Movie, Integer> {

    Movie readByTitle(String title);
    Movie readByScheduleId(Integer scheduleId);

    ArrayList<Movie> getAllMovies();
}
