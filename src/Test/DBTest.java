package Test;

import Theater.DataBase.RepositoryItem.SessionRepository;
import Theater.DataBase.RepositoryItem.SpectacleRepository;
import Theater.DataBase.RepositoryItem.TicketPriceRepository;
import Theater.DataBase.RepositoryItem.TicketRepository;
import Theater.Model.Customer;
import Theater.Model.Session;
import Theater.Model.Theater;
import Theater.Model.Ticket;

import java.time.LocalDate;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        SpectacleRepository.getINSTANCE().findAll().forEach(System.out::println);;
        System.out.println("\n"+SpectacleRepository.getINSTANCE().findBy("Король Лир") + "\n");

        List<Session> sessionsFromDb = SessionRepository.getINSTANCE().findAll();
//        sessionsFromDb.forEach(s->SessionRepository.getINSTANCE().insertTicketsFromDb(s));
//        sessionsFromDb.forEach(System.out::println);
//        sessionsFromDb.forEach(s->s.getTickets().forEach(System.out::println));

        sessionsFromDb.forEach(s->SessionRepository.getINSTANCE().clearTickets(s));

        for (Session s : sessionsFromDb) {
            System.out.println(SessionRepository.getINSTANCE().insertGeneratedTickets(s, 1, Theater.SPOTS_NUMBER,9));
        }
        Ticket tToDelete = SessionRepository.getINSTANCE().findAll().get(sessionsFromDb.size() - 1).getTickets().get(0);
        Session s = sessionsFromDb.get(sessionsFromDb.size() - 1);
//        Ticket tToInsert = new Ticket
//                .Builder(s)
//                .number(s.getId()* 10L + 2)
//                .spot(1)
//                .customer(new Customer.Builder(1).login("admin").FIO("AAA").build())
//                .build();
//        System.out.println(TicketRepository.getINSTANCE().updatePrice(tToDelete.getNumber(), new Ticket.TicketPrice(tToDelete.getCurrentPrice().getPrice() + 300, tToDelete, LocalDate.now().plusYears(1))));
//        TicketRepository.getINSTANCE().findAll().forEach(System.out::println);
//        TicketPriceRepository.getINSTANCE().findAll().forEach(System.out::println);
//        System.out.println(TicketRepository.getINSTANCE().delete(tToDelete) + "\n");
//        System.out.println(TicketRepository.getINSTANCE().insert(tToInsert) + "\n");
//        System.out.println(TicketRepository.getINSTANCE().findAll());
    }
}
