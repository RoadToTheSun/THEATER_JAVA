package Theater.DataBase.RepositoryItem;

import Theater.DataBase.RepositoryImpl;
import Theater.Exceptions.NoCustomersFoundException;
import Theater.Model.Ticket;

import java.io.Console;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TicketPriceRepository extends RepositoryImpl<Integer, Ticket.TicketPrice> {

    private TicketPriceRepository() {}

    private static TicketPriceRepository INSTANCE;

    public static TicketPriceRepository getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new TicketPriceRepository();
        return INSTANCE;
    }

    @Override
    public List<Ticket.TicketPrice> findAll() {
        return executeSelect("SELECT * FROM TICKET_PRICE");
    }

    @Override
    public Ticket.TicketPrice findBy(Integer id) {
        return null;
    }

    @Override
    public int delete(Ticket.TicketPrice price) {
        return executeDelete(String.format("DELETE FROM TICKET_PRICE WHERE id = %s", price.getId()));
    }

    private int executeDelete(String sql) {
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int rowsInserted = statement.executeUpdate();
            return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean insert(Ticket.TicketPrice newPrice) {
        System.err.println(Date.valueOf(LocalDate.now()));
        return executeInsert(String.format("INSERT INTO TICKET_PRICE (id, ticket_number, price, date_from, date_to) VALUES (NULL, %s, %s, '%s', '%s');",
                newPrice.getTicketNumber(),
                newPrice.getPrice(),
                Date.valueOf(LocalDate.now()),
                Date.valueOf(newPrice.getDateTo())
        ));
    }

    private boolean executeInsert(String sql) {
        try (
                Connection connection = super.connection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected List<Ticket.TicketPrice> executeSelect(String sql) {
        List<Ticket.TicketPrice> prices = new ArrayList<>();
        try (
                Connection connection = super.connection.getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Ticket.TicketPrice price = new Ticket.TicketPrice(
                        rs.getInt(3),
                        TicketRepository.getINSTANCE().findBy(rs.getLong(2)),
                        rs.getDate(5).toLocalDate()
                );
                price.setId(rs.getInt(1));
                price.setDateFrom(rs.getDate(4).toLocalDate());

                prices.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prices;
    }
}
