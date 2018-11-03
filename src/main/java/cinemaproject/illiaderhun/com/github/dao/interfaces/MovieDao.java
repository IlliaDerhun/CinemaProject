package cinemaproject.illiaderhun.com.github.dao.interfaces;

public interface MovieDao<Movie, Integer> extends Dao<Movie, Integer> {

    Movie readByTitle(String title);
    Movie readByScheduleId(Integer scheduleId);

}
