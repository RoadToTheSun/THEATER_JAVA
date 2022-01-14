package Theater.Service.Spectacle;

import Theater.Model.Session;
import Theater.Model.Spectacle;

import java.util.List;

public class DefaultSpectacleService implements SpectacleService {

    private DefaultSpectacleService() {
    }

    private static DefaultSpectacleService INSTANCE;

    public static DefaultSpectacleService getINSTANCE() {
        if (INSTANCE==null) INSTANCE = new DefaultSpectacleService();
        return INSTANCE;
    }

    @Override
    public int addSessions(Spectacle spec, Session... adding) {
        List<Session> sessions = spec.getSessions();
        int added = 0;
        for (Session session : adding) {
            if (!sessions.contains(session)) {
                session.link_spectacle(spec);
//                System.out.printf("Сеанс %s добавлен успешно\n", session.getId());
                sessions.add(session);
                added++;
            }
        }
        return added;
    }
}
