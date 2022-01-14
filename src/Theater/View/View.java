package Theater.View;

import Theater.Model.*;
import Theater.Model.Factory.CustomerFactory;
import Theater.Model.Factory.SessionFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class View {
    public static void run() {
        Theater theater = Theater.getINSTANCE();
        List<Customer> possibleCustomers = CustomerFactory.generate(1, 10);
        List<Spectacle> spectacles = theater.getSpectacles();
        List<Customer> theaterCustomers = theater.getCustomers();

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);

        for (Spectacle s : theater.getSpectacles()) {
            for (Session ses : SessionFactory.generate(3)) {
                ses.addTickets(3, 1);
                s.addSessions(ses);
            }
        }
        spectacles.forEach(s->s.getSessions().forEach(System.out::println));

        System.out.print("Your name: ");
        Customer me = new Customer.Builder(theater.getCustomers().size() + 1).FIO(sc.nextLine()).build();
//        theaterCustomers.add(me);
        theater.addCustomers(me);
        System.out.println(theaterCustomers);
//        System.out.println(theater.getCustomers());

        quit :
        while (true) {
            System.out.println("What?");
            System.out.println("1: See all customers");
            System.out.println("2: Add a customer");
            System.out.println("3: See playbill");
            System.out.println("4: See sessions on spectacle");
            System.out.println("5: Look at available/booked tickets for session");
            System.out.println("6: Book a ticket on session");
            System.out.println("7: See all sessions");
            sc = new Scanner(System.in);
            switch (sc.nextInt()) {
                case 1:
//                    theater.getCustomers().forEach(System.out::println);
                    theaterCustomers.forEach(System.out::println);
                    break;
                case 2:
                    String fio;
                    while (true) {
                        sc = new Scanner(System.in);
                        System.out.print("Фамилия:");
                        fio = sc.nextLine();
                        if (fio.length() < 1 || fio.isBlank()) break;
                        Customer c = new Customer.Builder(theater.getCustomers().size() + 1).FIO(fio).build();
//                        theaterCustomers.add(c);
                        theater.addCustomers(c);
                    }
                    break;
                case 3:
                    spectacles.forEach(s->s.getSessions().forEach(System.out::println));
                    break;
                case 4:
                    while (true) {
                        sc = new Scanner(System.in);
                        System.out.println("\nSpectacle name:");
                        String name = sc.nextLine();
                        if (name.length() < 1 || name.isBlank()) break;
                        theater.service().getSessionsOnSpectacle(name).forEach(System.out::println);
                    }
                    break;
                case 5:
                    while (true) {
                        sc = new Scanner(System.in);
                        System.out.print("Session id:");
                        int id = sc.nextInt();

                        sessionTickets :
                        System.out.printf("Session №%d%n", id);
                        System.out.println("1: Available tickets");
                        System.out.println("2: Booked tickets");
                        sc = new Scanner(System.in);
                        boolean onlyBookings = sc.nextInt() != 1;
//                    getTickets :
                        List<Ticket> tickets = theater.service().getTicketsForSession(id, onlyBookings);
//                        Session cur_session = theater.service().findSessionById(id);
//                        List<Ticket> ticketsForSession = cur_session.getTickets(onlyBookings);
                        tickets.forEach(System.out::println);
                        System.out.println();

                        if (!onlyBookings && !tickets.isEmpty()) {
                            label:
                            while (true) {
                                label2:
                                System.out.println("1: Book a ticket for session");
                                System.out.println("2: Update price for a ticket");
                                //TODO how to goto -getTickets- label?
                                System.out.println("3: Back");
                                sc = new Scanner(System.in);
                                switch (sc.nextInt()) {
                                    case 1:
                                        sc = new Scanner(System.in);
                                        System.out.printf("Customer: %s%n", me);
                                        System.out.print("Place: ");
                                        int spot = Integer.parseInt(sc.nextLine());
                                        boolean booked = theater.service().bookTicketForSession(id, me, spot);
                                        System.out.println(booked ? "Успешно" : "");
                                        break;
                                    case 2:
                                        sc = new Scanner(System.in);
                                        System.out.println("Number of ticket: ");
                                        long number = sc.nextLong();
                                        Ticket editing = theater.service().findSessionById(id).findTicketById(number);
                                        System.out.println("Current price: " + editing.getCurrentPrice());

                                        sc = new Scanner(System.in);
                                        System.out.println("Enter date in format year-mm-dd");
                                        List<String> date = Arrays.stream(sc.nextLine().split("[ -]+")).collect(Collectors.toList());
                                        LocalDate newDate = LocalDate.of(Integer.parseInt(date.get(0)), Integer.parseInt(date.get(1)), Integer.parseInt(date.get(2)));
                                        System.out.println(newDate);

                                        sc = new Scanner(System.in);
                                        System.out.print("Enter new price: ");
                                        int price = sc.nextInt();
                                        Ticket.TicketPrice oldPrice = editing.updatePrice(new Ticket.TicketPrice(price, editing, newDate));
                                        System.out.printf("Price updated: %s : %s to %s -> %s to %s%n",
                                                editing.getCurrentPrice().getPrice(),
                                                oldPrice.getDateFrom(),
                                                oldPrice.getDateTo(),
                                                editing.getCurrentPrice().getDateFrom(),
                                                editing.getCurrentPrice().getDateTo());
                                        break;
                                    case 3:
                                        break label;
                                    default:
                                        break;
                                }
                            }
                        }
                        else {

                        }
                        break;
                    }
                    break;
                case 6:
                    sc = new Scanner(System.in);
                    System.out.println("Customer id: ");
                    int c_id = sc.nextInt();
                    Customer cur = theater.service().findCustomerById(c_id);
                    System.out.printf("Customer: %s%n", cur);

                    sc = new Scanner(System.in);
                    System.out.print("Session ID: ");
                    int session_id = Integer.parseInt(sc.nextLine());

                    sc = new Scanner(System.in);
                    System.out.print("Place: ");
                    int spot = Integer.parseInt(sc.nextLine());
                    theater.service().bookTicketForSession(session_id, cur, spot);

                case 7:
                    List<Session> sessions = theater.service().getAllSessions();
                    System.out.printf("%25s%5d", "Total: ", sessions.size());
                    spectacles.forEach(s->s.getSessions().forEach(System.out::println));
                    break;
                default:
                    break quit;
            }
            System.out.println();
        }
    }
}
