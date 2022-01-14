package Theater.DataBase.RepositoryItem;

import Theater.DataBase.RepositoryImpl;
import Theater.Model.Factory.TicketsFactory;
import Theater.Model.Session;
import Theater.Model.Spectacle;
import Theater.Model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SessionRepository extends RepositoryImpl<Integer, Session> {

    private SessionRepository() {}

    private static SessionRepository INSTANCE;

    public static SessionRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new SessionRepository();
        return INSTANCE;
    }

    @Override
    public List<Session> findAll() {
        return executeSelect("SELECT * FROM SESSION");
    }

    @Override
    public Session findBy(Integer id) {
        try {
            return executeSelect(String.format("SELECT * FROM SESSION WHERE id = %d", id)).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.err.printf("There are 0 sessions with id \"%s\": %s%n", id, e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(Session s) {
        return 0;
    }

    @Override
    protected List<Session> executeSelect(String sql) {
        List<Session> sessions = new ArrayList<>();
        try (
                Connection connection = super.connection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet rs = statement.executeQuery(sql);
//            if (rs.getFetchSize()==0) return spectacles;
            while (rs.next()) {
                int id = rs.getInt("id");
                Spectacle spec = SpectacleRepository.getINSTANCE().findBy(rs.getString("spectacle_name"));
                Session s = new Session
                        .Builder()
                        .id(id)
                        .date(rs.getDate("date").toLocalDate())
                        .time(rs.getTime("time").toLocalTime())
                        //TODO
                        // .spectacle((Spectacle) rs.getRef("spectacle_name"))
                        .spectacle(spec)
                        .build();
                sessions.add(s);
            }
//            sessions.forEach(this::insertTicketsFromDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    public void insertTicketsFromDb(Session s) {
//        List<Ticket> tickets = TicketRepository.getINSTANCE().findBySessionId(s.getId());
        List<Ticket> tickets = TicketRepository.getINSTANCE().findBySessionId(s.getId());
        s.addTickets(tickets.toArray(new Ticket[0]));
    }

    public int insertGeneratedTickets(Session s, int spotFrom, int spotTo, int step) {
        Ticket[] tickets = TicketsFactory.generate(s, spotFrom, spotTo, step).toArray(new Ticket[0]);
        int rows = 0;
        for (Ticket t : tickets) {
            Ticket.TicketPrice price = t.getCurrentPrice();
//            rows += executeInsertDelete(String.format("INSERT INTO TICKET (NUMBER, SESSION_ID, PLACE, CUR_PRICE) VALUES(%s, %s, %s, %s)", t.getNumber(), t.getSessionId(), t.getSpot(), t.getCurrentPrice().getPrice()));
            TicketRepository.getINSTANCE().insert(t);
            rows++;
            //TODO
//            executeInsertDelete(String.format("INSERT INTO TICKET_PRICE (ID, TICKET_NUMBER, PRICE, DATE_FROM, DATE_TO) VALUES(%s, %s, %s, %s, %s)", price.getId(), price.getTicketNumber(), price.getPrice(), price.getDateFrom(), price.getDateTo()));
        }
        return rows;
    }

    public int clearTickets(Session s, int ... spots) {
        List<Ticket> tToDelete = TicketRepository.getINSTANCE().findAll().stream().filter(t -> t.getSessionId() == s.getId()).collect(Collectors.toList());
        if (spots!=null && spots.length>0) {
            tToDelete = tToDelete.stream().filter(t-> Arrays.stream(spots).anyMatch(spot->spot==t.getSpot())).collect(Collectors.toList());
        }
        int rows = 0;
        for (Ticket t : tToDelete) {
            rows+=TicketRepository.getINSTANCE().delete(t);
        }
        return rows;
    }



    private int executeInsertDelete(String sql) {
        int rows = 0;
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            rows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
