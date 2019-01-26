package cinemaproject.illiaderhun.com.github.dao.interfaces;

public interface Dao<Entity, Integer> {

    boolean create(Entity theEntity);
    Entity read(Integer entityId);
    boolean update(Entity theEntity);
    boolean delete(Integer entityId);

}
