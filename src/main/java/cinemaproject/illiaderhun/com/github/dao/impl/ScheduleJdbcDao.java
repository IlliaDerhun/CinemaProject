package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Schedule;
import cinemaproject.illiaderhun.com.github.dao.interfaces.ScheduleDao;
import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class ScheduleJdbcDao implements ScheduleDao<Schedule, Integer> {

    private static final Logger LOGGER = Logger.getLogger(ScheduleJdbcDao.class.getSimpleName());

    @NotNull
    private Connection connection = ConnectionManager.getConnection();

    @NotNull
    private Properties properties = QueriesManager.getProperties("schedule");

    @Override
    public boolean create(Schedule theEntity) {
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
        Integer scheduleId = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("readInsertedId"))){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                scheduleId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error("method setInsertedId caught SQLException");
            LOGGER.trace(e);
        }

        LOGGER.info("method setInsertedId return id: " + scheduleId);
        return scheduleId;
    }

    private void setStatement(PreparedStatement statement, Schedule theEntity) throws SQLException {
        LOGGER.info("method setStatement start");
        statement.setDate(1, theEntity.getDateTime());
        statement.setString(2, theEntity.getDayOfWeek());
        statement.setInt(3, theEntity.getMovieId());
        statement.setInt(4, theEntity.getOrderId());
    }

    @Override
    public Schedule read(Integer entityId) {
        LOGGER.info("method read started with id: " + entityId);
        Schedule theSchedule = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, entityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theSchedule = madeSchedule(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method read caught SQLException");
            LOGGER.trace(e);
        }

        return theSchedule;
    }

    private Schedule madeSchedule(ResultSet resultSet) throws SQLException {
        LOGGER.info("method madeSchedule started with resultSet: " + resultSet);

        Integer id = resultSet.getInt("id");
        Date dateTime = resultSet.getDate("date_time");
        String dayOfWeek = resultSet.getString("day_of_week");
        Integer movieId = resultSet.getInt("movie_id");
        Integer orderId = resultSet.getInt("order_id");

        Schedule theSchedule = new Schedule.Builder(dateTime, dayOfWeek)
                .setId(id)
                .setMovieId(movieId)
                .setOrderId(orderId)
                .build();

        LOGGER.info("method madeSchedule return: " + theSchedule);
        return theSchedule;
    }

    @Override
    public boolean update(Schedule theEntity) {
        LOGGER.info("method update start with: " + theEntity);
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("update"))){
            setStatement(statement, theEntity);
            statement.setInt(5, theEntity.getId());

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
