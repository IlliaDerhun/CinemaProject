package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.User;
import cinemaproject.illiaderhun.com.github.dao.interfaces.UserDao;
import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import com.sun.istack.internal.NotNull;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserJdbcDao implements UserDao<User, Integer> {

    private static final Logger LOGGER = Logger.getLogger(UserJdbcDao.class.getSimpleName());

    @NotNull
    private Connection connection = ConnectionManager.getConnection();

    @NotNull
    private Properties properties = QueriesManager.getProperties("user");

    @Override
    public User readByEmail(String email) {
        LOGGER.info("method readByEmail started with email: " + email);
        User theUser = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("selectByEmail"))) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theUser = madeUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method readByEmail caught SQLException");
            LOGGER.trace(e);
        }

        return theUser;
    }

    @Override
    public boolean create(User theEntity) {
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
        Integer userId = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("readInsertedId"))){
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            LOGGER.error("method setInsertedId caught SQLException");
            LOGGER.trace(e);
        }

        LOGGER.info("method setInsertedId return id: " + userId);
        return userId;
    }

    private void setStatement(PreparedStatement statement, User theEntity) throws SQLException {
        LOGGER.info("method setStatement start");
        statement.setString(1, theEntity.getName());
        statement.setString(2, theEntity.getSurname());
        statement.setString(3, theEntity.getEmail());
        statement.setString(4, theEntity.getPassword());
        statement.setInt(5, theEntity.getRoleId());
    }

    @Override
    public User read(Integer entityId) {
        LOGGER.info("method read started with id: " + entityId);
        User theUser = null;

        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("select"))) {
            statement.setInt(1, entityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                theUser = madeUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("method read caught SQLException");
            LOGGER.trace(e);
        }

        return theUser;
    }

    private User madeUser(ResultSet resultSet) throws SQLException {
        LOGGER.info("method madeUser started with resultSet: " + resultSet);

        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Integer roleId = resultSet.getInt("role_id");

        User theUser = new User.Builder(name, email)
                .userId(id)
                .surname(surname)
                .password(password)
                .roleId(roleId)
                .build();

        LOGGER.info("method madeUser return: " + theUser);
        return theUser;
    }

    @Override
    public boolean update(User theEntity) {
        LOGGER.info("method update start with: " + theEntity);
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("update"))){
            setStatement(statement, theEntity);
            statement.setInt(6, theEntity.getId());

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
