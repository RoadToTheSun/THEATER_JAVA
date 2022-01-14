package Theater.DataBase;

import java.util.Collection;

public abstract class RepositoryImpl<ID, T> implements Repository<ID, T> {

    protected final DBConnection connection = DBConnection.getINSTANCE();

//    @Override
//    public abstract Collection<T> findAll();
//
//    @Override
//    public abstract T findBy(ID id);

    protected abstract Collection<T> executeSelect(String sql);
}
