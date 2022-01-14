package Theater.Model.Factory;

import Theater.Model.Spectacle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpectaclesFactory {
    public static List<Spectacle> getSpectacles() {
        Spectacle s1 = new Spectacle.Builder("KL").description("KL").duration(2.5).build();
        Spectacle s2 = new Spectacle.Builder("SnowFlake").description("SnowFlake").duration(1.8).build();
        Spectacle s3 = new Spectacle.Builder("WhiteTiger").description("WhiteTiger").duration(3).build();
        Spectacle s4 = new Spectacle.Builder("Ba-ba-booy").description("Ba-ba-booy").duration(2.).build();
        Spectacle s5 = new Spectacle.Builder("La-La-Lend").description("La-La-Lend").duration(4.1).build();
//        Map<Integer, Set<Spectacle>> map = new HashMap<>();
//        map.put(1, Stream.of(s1, s3, s4).collect(Collectors.toSet()));
//        map.put(2, Stream.of(s2).collect(Collectors.toSet()));
//        map.put(3, Stream.of(s1, s5).collect(Collectors.toSet()));
//        map.put(4, Stream.of(s1, s5, s2, s3, s4).collect(Collectors.toSet()));
//        map.put(5, Stream.of(s4, s1, s5, s2).collect(Collectors.toSet()));
        return Stream.of(s1, s2, s3, s4, s5).collect(Collectors.toList());
    }
}
