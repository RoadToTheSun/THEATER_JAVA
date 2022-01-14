package Theater.Web;

import Theater.DataBase.RepositoryItem.SessionRepository;
import Theater.DataBase.RepositoryItem.TicketRepository;
import Theater.Model.Session;
import Theater.Model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "sessions", urlPatterns = {"/sessions"})
public class SessionServlet extends HttpServlet {

    private Session session;
    private List<Ticket> tickets;
    private Logger logger = Logger.getLogger(SessionServlet.class.getCanonicalName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = SessionRepository.getINSTANCE().findBy(Integer.valueOf(req.getParameter("session-id")));
        tickets = TicketRepository.getINSTANCE().findBySessionId(session.getId());
        req.setAttribute("session", session);
        req.setAttribute("tickets", tickets);
        logger.info("TICKETS ON SESSION: " + tickets);
        getServletContext().getRequestDispatcher("/session.jsp").forward(req, resp);
    }
}
