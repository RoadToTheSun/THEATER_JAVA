package Theater.Web;

import Theater.DataBase.DBConnection;
import Theater.DataBase.RepositoryItem.SessionRepository;
import Theater.DataBase.RepositoryItem.SpectacleRepository;
import Theater.Model.Session;
import Theater.Model.Spectacle;
import Theater.Model.Theater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@WebServlet(name = "theater", urlPatterns = {"/theater"})
public class TheaterServlet extends HttpServlet {

    private Theater theater;
    private final Logger logger = Logger.getLogger(TheaterServlet.class.getCanonicalName());

    @Override
    public void init() throws ServletException {
        super.init();
        theater = Theater.getINSTANCE();
        theater.getSpectacles().clear();
        theater.getSpectacles().addAll(SpectacleRepository.getINSTANCE().findAll());
        List<Session> sessions = SessionRepository.getINSTANCE().findAll();
        for (Spectacle spectacle : theater.getSpectacles()) {
            spectacle.addSessions(sessions.stream().filter(s -> s.getSpectacleName().equals(spectacle.getName())).toArray(Session[]::new));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Session> sessions = theater.service().getAllSessions();
        sessions = SessionRepository.getINSTANCE().findAll();
        logger.info("Collecting sessions from DB...");
//        logger.info(spectacles.get(0).translit());
        req.setAttribute("name", Theater.NAME);
        req.setAttribute("playbill", sessions);

        getServletContext().getRequestDispatcher("/theater.jsp").forward(req, resp);
    }
}
