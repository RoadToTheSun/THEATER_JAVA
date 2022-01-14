package Theater.Model.Factory;

import Theater.Model.Theater;

public class PriceGenerator {
    private static final int LOWEST = 300;
    private static final int LOW = 500;
    private static final int MEDIUM = 750;
    private static final int HIGH = 1000;
    private static final int HIGHEST = 1250;
    private static final int UNBELIEVABLE = 3500;

    public static int get(int spot) {
        for (int i = 0, t = 5; i < t; i++) {
            if (spot>i*Theater.SPOTS_NUMBER/t && spot<=(i+1)*Theater.SPOTS_NUMBER/t)
                return i==0? LOWEST : i==1 ? LOW : i==2 ? MEDIUM : i==3 ? HIGH : i==4 ? HIGHEST : UNBELIEVABLE;
        }
        return -1;
    }
}
