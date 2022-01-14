package Theater.Service.Spectacle;

import Theater.Model.Session;
import Theater.Model.Spectacle;

import java.util.HashMap;
import java.util.Map;

public interface SpectacleService {
    int addSessions(Spectacle s, Session ... adding);
    default String translit(String s) {
        Map<String, String>  mapper = new HashMap<>();
        String[] rus = {"а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к", "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ь", "э", "ю", "я", " "};
        String[] eng = {"a", "b", "v", "g", "d", "e", "e", "gh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "c", "ch", "sh", "sch", "", "", "e", "yu", "ya", "-"};
        for (int i = 0; i < rus.length; i++) {
            mapper.put(rus[i], eng[i]);
        }
        StringBuilder sb = new StringBuilder();

        Converter converter = russian -> {
            for (Character ch : russian.toLowerCase().toCharArray()) {
                if (Character.isLetterOrDigit(ch) || Character.isSpaceChar(ch)) {
                    String l = ch.toString();
                    sb.append(mapper.getOrDefault(l, l));
                }
            }
            return sb.toString();
        };

        return converter.convert(s);
    }
}
