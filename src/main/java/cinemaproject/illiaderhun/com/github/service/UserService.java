package cinemaproject.illiaderhun.com.github.service;

import cinemaproject.illiaderhun.com.github.dao.entities.User;
import cinemaproject.illiaderhun.com.github.dao.impl.UserJdbcDao;
import cinemaproject.illiaderhun.com.github.dao.interfaces.UserDao;
import org.apache.log4j.Logger;

public class UserService implements UserDao<User, Integer> {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getSimpleName());
    private UserJdbcDao userJdbcDao;

    @Override
    public User readByEmail(String email) {
        LOGGER.info("method readByEmail start with email: " + email);
        checkUserJdbcDaoAndCreateIfDoesntExist();
        return userJdbcDao.readByEmail(email);
    }

    @Override
    public boolean create(User theEntity) {
        LOGGER.info("method create start with entity: " + theEntity);
        checkUserJdbcDaoAndCreateIfDoesntExist();
        return userJdbcDao.create(theEntity);
    }

    @Override
    public User read(Integer entityId) {
        LOGGER.info("method read start with: " + entityId);
        checkUserJdbcDaoAndCreateIfDoesntExist();
        return userJdbcDao.read(entityId);
    }

    @Override
    public boolean update(User theEntity) {
        LOGGER.info("method update start with entity: " + theEntity);
        checkUserJdbcDaoAndCreateIfDoesntExist();
        return userJdbcDao.update(theEntity);
    }

    @Override
    public boolean delete(Integer entityId) {
        LOGGER.info("method delete start with id: " + entityId);
        checkUserJdbcDaoAndCreateIfDoesntExist();
        return userJdbcDao.delete(entityId);
    }

    private void checkUserJdbcDaoAndCreateIfDoesntExist() {
        if (userJdbcDao == null) {
            userJdbcDao = new UserJdbcDao();
        }
    }
}
