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
    public void readByEmailShouldReturnValidEntityByValidEmail() {
        User actualUser = userJdbcDao.readByEmail("petrov@mail.com");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void create() {
        User someUser = new User.Builder("Vasia", "vasiliev@mail.com")
                .surname("Vasiliev")
                .password("456321")
                .roleId(1)
                .build();
        assertTrue(userJdbcDao.create(someUser));
    }

    @Test
    public void readShouldReturnValidEntityByValidId() {
        User actualUser = userJdbcDao.read(2);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void readShouldReturnNullByInvalidId() {
        User actualUser = userJdbcDao.read(0);
        assertNull(actualUser);
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}