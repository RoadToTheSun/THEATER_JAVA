//package Theater.Model;
//
//import Theater.DataBase.RepositoryItem.SpectacleRepository;
//import Theater.Model.Factory.SpectaclesFactory;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class Playbill {
//
//    private Map<Integer, Set<Spectacle>> spectacles;
//
//    private Playbill() {
//        this.spectacles = SpectaclesFactory.getSpectacles();
//    }
//
//    private static Playbill INSTANCE;
//
//    public static Playbill getINSTANCE() {
//        if (INSTANCE == null)
//            INSTANCE = new Playbill();
//        return INSTANCE;
//    }
//
//    public Set<Spectacle> info() {
//        Set<Spectacle> allSpectacles = new HashSet<>();
//        for (Map.Entry<Integer, Set<Spectacle>> entry :
//                spectacles.entrySet()) {
//            allSpectacles.addAll(entry.getValue());
//        }
//        return allSpectacles;
//    }
//
//    public Set<Spectacle> get(int month) {
//        return spectacles.get(month);
//    }
//
//    public Map<Integer, Set<Spectacle>> getAll() {
//        return spectacles;
//    }
//
//    public void setSpectacles(Map<Integer, Set<Spectacle>> spectacles) {
//        this.spectacles = spectacles;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format("%20s\n", "Расписание")).append(String.format("%-10s%30s%20s%n", "Месяц", "Название", "Продолжительность"));
//        for (Map.Entry<Integer, Set<Spectacle>> entry : spectacles.entrySet()) {
//            for (Spectacle s : entry.getValue()) {
//                sb.append(String.format("%-10s%30s%20s%n", entry.getKey(), s.getName(), s.getDuration()));
//            }
//        }
//        return sb.toString();
//    }
//}
