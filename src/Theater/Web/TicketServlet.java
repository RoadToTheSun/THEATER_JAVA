package Theater.Web;

import Theater.DataBase.RepositoryItem.CustomerRepository;
import Theater.DataBase.RepositoryItem.SessionRepository;
import Theater.DataBase.RepositoryItem.TicketRepository;
import Theater.Model.Customer;
import Theater.Model.Session;
import Theater.Model.Ticket;
import com.google.protobuf.MapEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;

@WebServlet(name = "tickets", urlPatterns = {"/tickets/*"})
public class TicketServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(TicketServlet.class.getCanonicalName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long number = extractId(req.getPathInfo());
        Ticket t = TicketRepository.getINSTANCE().findBy(number);
        Integer sessionId = Integer.valueOf(req.getParameter("session-id"));
        logger.info("SESSION ID: " + req.getParameter("session-id"));
        Session s = SessionRepository.getINSTANCE().findBy(sessionId);
        req.setAttribute("ticket", t);
        req.setAttribute("session", s);

        getServletContext().getRequestDispatcher("/ticket.jsp").forward(req, resp);
    }

    private Long extractId(String reqPath) {
        Long num = null;
        try {
            num = Long.valueOf(reqPath.split("[/?&]")[1]);
        } catch (NumberFormatException e) {
            logger.info(e.getMessage());
        }
        return num;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long number = extractId(req.getPathInfo());
        Ticket t = TicketRepository.getINSTANCE().findBy(number);
        Integer sessionId = Integer.valueOf(req.getParameter("session-id"));
        Session s = SessionRepository.getINSTANCE().findBy(sessionId);
        Map<String, String[]> paramsMap = req.getParameterMap();
        logger.info("REQUEST MAP: ");
        for (Map.Entry<String, String[]> entry : paramsMap.entrySet())
            logger.info(entry.getKey()+"->"+ Arrays.toString(entry.getValue()));

        req.setAttribute("session-id", sessionId);
        for (String p : paramsMap.keySet()) {
            switch (p) {
                case "insert": {
                    List<Customer> possibleCustomers = CustomerRepository.getINSTANCE().findAll();
                    logger.info("CUSTOMERS: " + possibleCustomers.toString());
                    req.setAttribute("customers", possibleCustomers);
                    req.setAttribute("spot", req.getParameter("insert"));
                    getServletContext().getRequestDispatcher("/ticket-insert.jsp").forward(req, resp);
//                    redirect to insertTicket.jsp
                    break;
                }
                case "delete": {
                    TicketRepository.getINSTANCE().delete(t);
                    logger.info("TICKET " + number + " WAS DELETED");
//                    getServletContext().getRequestDispatcher("/session.jsp").forward(req, resp);
                    resp.sendRedirect("/theater.jsp");
//                    redirect to theater.jsp
                    break;
                }
                case "update": {
                    //TODO
//                    TicketRepository.getINSTANCE().updatePrice(number);
//
                    break;
                }
            }
        }
    }
}
