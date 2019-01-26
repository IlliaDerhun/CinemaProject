package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Movie;
import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.interfaces.MovieDao;
import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class MovieJdbcDao implements MovieDao<Movie, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MovieJdbcDao.class.getSimpleName());

    @NotNull
    private Connection connection = ConnectionManager.getConnection();

    @NotNull
    private Properties properties = QueriesManager.getProperties("movie");

    @Override
    public Movie readByTitle(String title) {
        LOGGER.info("method readByTitle started with title: " + title);
        Movie theMovie = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByTitle"))) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theMovie = madeMovie(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method readByTitle caught SQLException");
            LOGGER.trace(e);
        }

        return theMovie;
    }

    private Movie madeMovie(ResultSet resultSet) throws SQLException {
        LOGGER.info("method madeMovie started with resultSet: " + resultSet);

        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        String time = resultSet.getString("time");
        BigDecimal price = resultSet.getBigDecimal("price");
        String poster = resultSet.getString("poster");
        Integer scheduleId = resultSet.getInt("schedule_id");

        Movie theMovie = new Movie.Builder(title, description)
                .setId(id)
                .setTime(time)
                .setPrice(price)
                .setPoster(poster)
                .setScheduleId(scheduleId)
                .build();

        LOGGER.info("method madeMovie return: " + theMovie);
        return theMovie;
    }

    @Override
    public Movie readByScheduleId(Integer scheduleId) {
        LOGGER.info("method readByScheduleId started with title: " + scheduleId);
        Movie theMovie = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByScheduleId"))) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theMovie = madeMovie(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method readByScheduleId caught SQLException");
            LOGGER.trace(e);
        }

        return theMovie;
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        LOGGER.info("method getAllMovies started");
        ArrayList<Movie> theMovies = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectAll"))) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                do {
                    theMovies.add(madeMovie(resultSet));
                } while (resultSet.next()) ;
            }
        } catch (SQLException e) {
            LOGGER.error("method readByUserId caught SQLException");
            LOGGER.trace(e);
        }

        return theMovies;
    }

    @Override
    public boolean create(Movie theEntity) {
        LOGGER.info("method create start with: " + theEntity);
        boolean result = false;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("insert"))) {
            setStatement(statement, theEntity);
            statement.executeUpdate();
            theEntity.setId(setInsertedId());
            result = true;
        } catch (SQLException e) {
            LOGGER.error("method create caught SQLException");
            LOGGER.trace(e);
        }

        return result;
    }

    private Integer setInsertedId() {
        LOGGER.info("method setInsertedId start");
        Integer orderId = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("readInsertedId"))){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                orderId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error("method setInsertedId caught SQLException");
            LOGGER.trace(e);
        }

        LOGGER.info("method setInsertedId return id: " + orderId);
        return orderId;
    }

    private void setStatement(PreparedStatement statement, Movie theEntity) throws SQLException {
        LOGGER.info("method setStatement start");
        statement.setString(1, theEntity.getTitle());
        statement.setString(2, theEntity.getDescription());
        statement.setString(3, theEntity.getTime());
        statement.setBigDecimal(4, theEntity.getPrice());
        statement.setString(5, theEntity.getPoster());
        statement.setInt(6, theEntity.getScheduleId());
    }

    @Override
    public Movie read(Integer entityId) {
        LOGGER.info("method read started with id: " + entityId);
        Movie theMovie = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, entityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theMovie = madeMovie(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method read caught SQLException");
            LOGGER.trace(e);
        }

        return theMovie;
    }

    @Override
    public boolean update(Movie theEntity) {
        LOGGER.info("method update start with: " + theEntity);
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("update"))){
            setStatement(statement, theEntity);
            statement.setInt(7, theEntity.getId());

            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.info("method update caught SQLException");
            LOGGER.trace(e);
        }
        return result;
    }

    @Override
    public boolean delete(Integer entityId) {
        LOGGER.info("method delete start with id: " + entityId);
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delete"))){
            statement.setInt(1, entityId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.error("method delete caught SQLException");
        }
        return result;
    }
}
