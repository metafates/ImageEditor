package util;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static int intInRange(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to + 1);
    }

    public static <T> T choice(T[] list) {
        return list[intInRange(0, list.length - 1)];
    }

    public static Color color() {
        Color[] rgb = {
            new Color(255, 0, 0),
            new Color(0, 255, 0),
            new Color(0, 0, 255)
        };

        return choice(rgb);
    }
}
