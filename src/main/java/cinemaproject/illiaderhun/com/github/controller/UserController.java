package cinemaproject.illiaderhun.com.github.controller;

import cinemaproject.illiaderhun.com.github.dao.entities.User;
import cinemaproject.illiaderhun.com.github.service.UserService;
import cinemaproject.illiaderhun.com.github.util.PasswordEncoder;

public class UserController {

    public User createNewUser(String name, String email, String surname, String password) {
        if (getUserByEmail(email) != null) {
            return null;
        }

        User theUser = new User.Builder(name, email)
                .surname(surname)
                .password(PasswordEncoder.encodeIt(password))
                .roleId(1)
                .build();

        new UserService().create(theUser);

        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return new UserService().readByEmail(email);
    }

    public User validateUserByEmailPassword(String email, String password) {

        User theUser = getUserByEmail(email);

        if (PasswordEncoder.encodeIt(password).equalsIgnoreCase(theUser.getPassword())) {
            return theUser;
        }

        return null;
    }
}
