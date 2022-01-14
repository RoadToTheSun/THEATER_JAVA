package Theater.Model.Factory;

import Theater.Model.Session;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SessionFactory {
    public static List<Session> generate(int limit) {
        List<Session> sessions = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < limit; i++) {

            int month = (int) Math.ceil(Math.random()*7);
            int day = rnd.nextInt(31)+1;
            LocalTime time = LocalTime.of(rnd.nextInt(24), rnd.nextInt(60));

            Session.Builder sessionRaw = new Session.Builder().date(LocalDate.of(LocalDate.now().getYear(), month, day));
            Session s = sessionRaw.id(Integer.parseInt(String.valueOf(month).concat(String.valueOf(day)).concat(String.valueOf(time.toSecondOfDay())))).time(time).build();
            sessions.add(s);
        }
        return sessions;
    }
}
