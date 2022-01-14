package Theater.DataBase.RepositoryItem;

import Theater.DataBase.RepositoryImpl;
import Theater.Model.Customer;
import Theater.Model.Session;
import Theater.Model.Ticket;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepository extends RepositoryImpl<Long, Ticket> {

    private TicketRepository() {}

    private static TicketRepository INSTANCE;

    public static TicketRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new TicketRepository();
        return INSTANCE;
    }

    @Override
    public List<Ticket> findAll() {
        return executeSelect("SELECT * FROM TICKET");
    }

    @Override
    public Ticket findBy(Long number) {
        try {
            return executeSelect(String.format("SELECT * FROM TICKET WHERE NUMBER = %s", number)).get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updatePrice(long num, Ticket.TicketPrice newPrice) {
        return executeUpdate("UPDATE TICKET SET cur_price = ? WHERE number = ? ", num, newPrice);
    }

    private int executeUpdate(String sql, long num, Ticket.TicketPrice newPrice) {
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setInt(1, newPrice.getPrice());
            statement.setLong(2, num);
            int rowsInserted = statement.executeUpdate();
            TicketPriceRepository.getINSTANCE().insert(newPrice);
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Ticket t) {
        return executeDelete(String.format("DELETE FROM TICKET WHERE number = %s", t.getNumber()), t.getNumber());
    }

    private int executeDelete(String sql, Long num) {
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            List<Ticket.TicketPrice> pricesToDelete = TicketPriceRepository.getINSTANCE().findAll().stream().filter(p-> p.getTicketNumber().equals(num)).collect(Collectors.toList());
            for (Ticket.TicketPrice price : pricesToDelete) {
                TicketPriceRepository.getINSTANCE().delete(price);
            }
            int rowsInserted = statement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean insert(Ticket nt) {
        return executeInsert(String.format("INSERT INTO TICKET (number, session_id, place, customer_id, cur_price) VALUES (%s, %s, %s, %s, %s);",
                nt.getNumber(),
                nt.getSessionId(),
                nt.getSpot(),
                //TODO
                null,
                nt.getCurrentPrice().getPrice()
        ), nt);
    }

    private boolean executeInsert(String sql, Ticket nt) {
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int rowsInserted = statement.executeUpdate();
            TicketPriceRepository.getINSTANCE().insert(nt.getCurrentPrice());
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public List<Ticket> findBySessionId(int id) {
        return findAll().stream().filter(t->t.getSessionId()==id).collect(Collectors.toList());
    }

    @Override
    protected List<Ticket> executeSelect(String sql) {
        List<Ticket> tickets = new ArrayList<>();
        try (
                Connection connection = super.connection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //TODO Session s = (Session) rs.getRef("session_id");
                //TODO Customer c = (Customer) rs.getRef("customer_id");
                Ticket t = new Ticket
                        .Builder(SessionRepository.getINSTANCE().findBy(rs.getInt("session_id")))
                        .number(rs.getLong("number"))
                        .spot(rs.getInt("place"))
                        .customer(CustomerRepository.getINSTANCE().findBy(rs.getInt("customer_id")))
                        .build();
//                t.getPrices().clear();
//                t.getPrices().addAll(TicketPriceRepository.getINSTANCE().findAll().stream().takeWhile(p->p.getTicketNumber().equals(t.getNumber())).collect(Collectors.toList()));
                tickets.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
