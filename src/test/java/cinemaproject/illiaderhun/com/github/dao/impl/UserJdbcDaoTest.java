package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.User;
import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.Properties;

import static org.junit.Assert.*;

public class UserJdbcDaoTest {

    private User expectedUser;
    private UserJdbcDao userJdbcDao;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User.Builder("Petia", "petrov@mail.com")
                .userId(2)
                .surname("Petrov")
                .password("1234")
                .roleId(1)
                .build();

        userJdbcDao = new UserJdbcDao();
    }

    @Test
    public void readByEmail() {
    }

    @Test
    public void create() {
    }

    @Test
    public void readShouldReturnValidEntityByValidId() {
        User actualUser = userJdbcDao.read(2);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}