package Theater.DataBase;

import java.util.Collection;

public interface Repository<K, V> {
    Collection<V> findAll();
    V findBy(K id);
    int delete(V value);
}
