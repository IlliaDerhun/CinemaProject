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
        return null;
    }

    @Override
    public boolean create(User theEntity) {
        return false;
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
        return false;
    }

    @Override
    public boolean delete(Integer entityId) {
        return false;
    }
}
