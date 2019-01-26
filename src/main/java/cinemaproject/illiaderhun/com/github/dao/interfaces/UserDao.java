package cinemaproject.illiaderhun.com.github.dao.interfaces;

public interface UserDao<User, Integer> extends Dao<User, Integer> {

    User readByEmail(String email);

}
