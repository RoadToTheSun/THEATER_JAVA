package Theater.Model;

import Theater.Service.Spectacle.DefaultSpectacleService;
import Theater.Service.Spectacle.SpectacleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Spectacle implements Comparable<Spectacle> {

    private final SpectacleService service = DefaultSpectacleService.getINSTANCE();

    private String name;
    private String description;
    private double duration;
    private String genre;
    private int rating;
    private int age;

//    private List<Ticket> tickets;
    private List<SpectacleParticipant> participants;
    private List<Producer> producers;
    private List<Session> sessions;

    private Spectacle(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.duration = builder.duration;
        this.genre = builder.genre;
        this.rating = builder.rating;
        this.age = builder.age;
        participants = new ArrayList<>();
        producers = new ArrayList<>();
        sessions = new ArrayList<>(); // or array?
    }

    public static class Builder {
        private int age;
        private String name;
        private String description;
        private double duration;
        private String genre;
        private int rating;

        public Builder(String name) {
            this.name = name;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder duration(double duration) {
            this.duration = duration;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }
        public Builder rating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Spectacle build() {
            return new Spectacle(this);
        }
    }

    public int addSessions(Session ... s) {
        return service.addSessions(this, s);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public int getAge() {
        return age;
    }

    public String translit() {
        return service.translit(name);
    }

    @Override
    public int compareTo(Spectacle o) {
        return rating!=o.rating ? o.rating-rating : o.age-age;
    }

    @Override
    public String toString() {
        return "Spectacle{" + "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", age=" + age +
                '}';
    }

    public class Producer {
        private int UID;
        private String FIO;

        private Map<Spectacle, List<Integer>> spectaclesWithYearsOfProduction;
    }



//    public List<Ticket> getTickets() {
//        return tickets;
//    }
}
