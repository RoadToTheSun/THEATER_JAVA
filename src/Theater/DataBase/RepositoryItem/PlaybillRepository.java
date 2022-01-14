package Theater.DataBase.RepositoryItem;

import Theater.DataBase.DBConnection;
import Theater.DataBase.RepositoryImpl;
import Theater.Model.Spectacle;

import javax.sql.rowset.serial.SerialRef;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PlaybillRepository /* extends RepositoryImpl<Integer, Set<Spectacle>*/ {

    private PlaybillRepository() {}

    private static PlaybillRepository INSTANCE;

    public static PlaybillRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new PlaybillRepository();
        return INSTANCE;
    }

    public Map<Integer, Set<Spectacle>> findAll() {
        return execute("SELECT * FROM PLAYBILL");
    }

    public Set<Spectacle> findBy(Integer month) {
        try {
            return execute(String.format("SELECT * FROM PLAYBILL WHERE MONTH = %s", month)).get(month);
        } catch (IndexOutOfBoundsException e) {
            System.err.printf("No spectacles found in %s month: %s", month, e.getMessage());
        }
        return null;
    }

    protected Map<Integer, Set<Spectacle>> execute(String sql) {
        Map<Integer, Set<Spectacle>> spectaclesMap = new TreeMap<>(Comparator.comparing(Integer::intValue));
        try (
                Connection connection = DBConnection.getINSTANCE().getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
        ) {
            ResultSet rs = statement.executeQuery(sql);
//            if (rs.getFetchSize()==0) return spectaclesMap;
            initMap(spectaclesMap, rs);
            while (rs.next()) {
                int month = rs.getInt("month");
                String spectacle_name = rs.getString("spectacle_name");
                Spectacle s = SpectacleRepository.getINSTANCE().findBy(spectacle_name);
//                Spectacle s = ((Spectacle) rs.getRef(2));
                spectaclesMap.get(month).add(s);
            }
        } catch (SQLException e) {
            System.err.println((e.getMessage()));
        }
        return spectaclesMap;
    }

    private void initMap(Map<Integer, Set<Spectacle>> spectaclesMap, ResultSet rs) throws SQLException {
        while (rs.next()) {
            spectaclesMap.put(rs.getInt("month"), new TreeSet<>());
        }
        rs.beforeFirst();
    }
}
