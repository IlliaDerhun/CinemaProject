package cinemaproject.illiaderhun.com.github.dao.impl;

import cinemaproject.illiaderhun.com.github.dao.entities.User;
import cinemaproject.illiaderhun.com.github.service.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserJdbcDaoTest {

    private User expectedUser;
    private User someUser;
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        expectedUser = new User.Builder("Petia", "petrov@mail.com")
                .userId(9)
                .surname("Petrov")
                .password("1234")
                .roleId(1)
                .build();

        someUser = new User.Builder("Vasia", "vasiliev@mail.com")
                .surname("Vasiliev")
                .password("456321")
                .roleId(1)
                .build();

        userService = new UserService();
    }

    @Test
    public void readByEmailShouldReturnValidEntityByValidEmail() {
        User actualUser = userService.readByEmail("petrov@mail.com");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void create() {
        assertTrue(userService.create(someUser));
        userService.delete(userService.readByEmail("vasiliev@mail.com").getId());
    }

    @Test
    public void readShouldReturnValidEntityByValidId() {
        User actualUser = userService.read(9);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void readShouldReturnNullByInvalidId() {
        User actualUser = userService.read(0);
        assertNull(actualUser);
    }

    @Test
    public void update() {
        userService.create(someUser);
        someUser.setName("NewName");
        assertTrue(userService.update(someUser));
        userService.delete(userService.readByEmail("vasiliev@mail.com").getId());
    }

    @Test
    public void delete() {
        userService.create(someUser);
        someUser = userService.readByEmail("vasiliev@mail.com");
        assertTrue(userService.delete(someUser.getId()));
    }
}