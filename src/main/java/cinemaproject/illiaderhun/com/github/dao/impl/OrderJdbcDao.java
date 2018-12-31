package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.Order;
import cinemaproject.illiaderhun.com.github.dao.interfaces.OrderDao;
import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderJdbcDao implements OrderDao<Order, Integer> {

    private static final Logger LOGGER = Logger.getLogger(OrderJdbcDao.class.getSimpleName());

    @NotNull
    private Connection connection = ConnectionManager.getConnection();

    @NotNull
    private Properties properties = QueriesManager.getProperties("order");

    @Override
    public  ArrayList<Order> readByUserId(Integer userId) {
        LOGGER.info("method readByUserId started with id: " + userId);
        ArrayList<Order> theOrders = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByUserId"))) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                do {
                    theOrders.add(madeOrder(resultSet));
                } while (resultSet.next()) ;
            }
        } catch (SQLException e) {
            LOGGER.error("method readByUserId caught SQLException");
            LOGGER.trace(e);
        }

        return theOrders;
    }

    @Override
    public ArrayList<Order> readByScheduleId(Integer scheduleId) {
        LOGGER.info("method readByScheduleId started with id: " + scheduleId);
        ArrayList<Order> orders = new ArrayList<>();
        scheduleId = read(scheduleId).getScheduleId();
        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByScheduleId"))) {
            statement.setInt(1, scheduleId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                do {
                    orders.add(madeOrder(resultSet));
                } while (resultSet.next()) ;
            }
        } catch (SQLException e) {
            LOGGER.error("method readByScheduleId caught SQLException");
            LOGGER.trace(e);
        }

        return orders;
    }

    private Order madeOrder(ResultSet resultSet) throws SQLException {
        LOGGER.info("method madeOrder started with resultSet: " + resultSet);

        Integer id = resultSet.getInt("id");
        Integer row = resultSet.getInt("row");
        Integer col = resultSet.getInt("col");
        Integer userId = resultSet.getInt("user_id");
        Integer scheduleId = resultSet.getInt("schedule_id");

        Order theOrder = new Order(id, row, col, userId, scheduleId);

        LOGGER.info("method madeOrder return: " + theOrder);
        return theOrder;
    }

    @Override
    public boolean create(Order theEntity) {
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

    private void setStatement(PreparedStatement statement, Order theEntity) throws SQLException {
        LOGGER.info("method setStatement start");
        statement.setInt(1, theEntity.getRow());
        statement.setInt(2, theEntity.getCol());
        statement.setInt(3, theEntity.getUserId());
        statement.setInt(4, theEntity.getScheduleId());
    }

    @Override
    public Order read(Integer entityId) {
        LOGGER.info("method read started with id: " + entityId);
        Order theOrder = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, entityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theOrder = madeOrder(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method read caught SQLException");
            LOGGER.trace(e);
        }

        return theOrder;
    }

    @Override
    public boolean update(Order theEntity) {
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
