package Theater.Web;

import Theater.DataBase.RepositoryItem.SessionRepository;
import Theater.DataBase.RepositoryItem.SpectacleRepository;
import Theater.DataBase.RepositoryItem.TicketRepository;
import Theater.Model.Session;
import Theater.Model.Spectacle;
import Theater.Model.Theater;
import Theater.Model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "spectacles", urlPatterns = {"/spectacles/*"})
public class SpectacleServlet extends HttpServlet {

    private Theater theater = Theater.getINSTANCE();
    private List<Spectacle> spectacles;
    private final Logger logger = Logger.getLogger(SpectacleServlet.class.getCanonicalName());

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request {" + req.getRequestURI() + "}, pathInfo {" + req.getPathInfo() + "}");
        String[] name = req.getPathInfo().split("/");
        spectacles = theater.getSpectacles();
        String page;
        if (name.length==0) {
            logger.info("SEARCHING FOR SPECTACLES IN DB...");
            req.setAttribute("playbill", spectacles);
            page = "/spectacles.jsp";
        } else {
            logger.info("REQUESTED SPECTACLE IS " + name[1]);
            Spectacle spectacle = SpectacleRepository.getINSTANCE().findBy(name[1]);
//            List<Session> sessions = theater.service().getSessionsOnSpectacle(spectacle.getName());
            List<Session> sessions = SessionRepository.getINSTANCE().findAll().stream().filter(s->s.getSpectacleName().equals(spectacle.getName())).collect(Collectors.toList());
            req.setAttribute("spectacle", spectacle);
            req.setAttribute("sessions", sessions);
            page = "/spectacle.jsp";
        }
        getServletContext().getRequestDispatcher(page).forward(req, resp);
    }
}
