
package com.dreamgames;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Country {
    TURKEY,
    UNITED_STATES,
    UNITED_KINGDOM,
    FRANCE,
    GERMANY;

    private static final List<Country> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Country randomCountry() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
