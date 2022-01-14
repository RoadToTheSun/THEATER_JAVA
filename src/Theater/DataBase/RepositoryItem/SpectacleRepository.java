package Theater.DataBase.RepositoryItem;

import Theater.DataBase.RepositoryImpl;
import Theater.Model.Spectacle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpectacleRepository extends RepositoryImpl<String, Spectacle> {

    private SpectacleRepository() {

    }

    private static SpectacleRepository INSTANCE;

    public static SpectacleRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new SpectacleRepository();
        return INSTANCE;
    }

    @Override
    public List<Spectacle> findAll() {
        return executeSelect("SELECT * FROM SPECTACLE");
    }

    @Override
    public Spectacle findBy(String name) {
        try {
            return executeSelect(String.format("SELECT * FROM SPECTACLE WHERE NAME LIKE '%s'", name)).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.err.printf("There are 0 spectacles with name \"%s\": %s%n", name, e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(Spectacle s) {
        return 0;
    }

    @Override
    protected List<Spectacle> executeSelect(String sql) {
        List<Spectacle> spectacles = new ArrayList<>();
        try (
                Connection connection = super.connection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
//            if (rs.getFetchSize()==0) return spectacles;
            while (rs.next()) {
                Spectacle s = new Spectacle
                        .Builder(rs.getString("name"))
                        .duration(rs.getDouble("duration"))
                        .genre(rs.getString("genre"))
                        .age(rs.getInt("age"))
                        .description(rs.getString("description"))
                        .rating(rs.getInt("rating"))
                        .build();
                spectacles.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spectacles;
    }
}

