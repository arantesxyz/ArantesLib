package dev.arantes.lib.utils;
import dev.arantes.lib.utils.formats.Portuguese;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberFormat {
    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "K");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "B");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "Q");
        suffixes.put(1_000_000_000_000_000_000L, "L");
    }

    @Deprecated
    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();

        long truncated = value / (divideBy / 10); //the number part of the output times 10

        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);

        // todo add multilanguage support
        String suffix = Portuguese.valueOf(e.getValue()).toString(truncated + "");

        return hasDecimal ? (truncated / 10d) + " " + suffix : (truncated / 10) + " " + suffix;
    }

    public static String limitDecimals(double value) {
        return limitDecimals(value, "#.##");
    }

    public static String limitDecimals(double value, String pattern) {
        return new DecimalFormat(pattern, new DecimalFormatSymbols(new Locale("pt", "BR")))
                .format(value);
    }

    public static String format(double valor) {
        final String[] simbols = new String[]{"", "k", "M", "B", "T", "Q", "QQ", "S", "SS", "O",
                "N", "D", "UN", "DD", "TD", "QD", "QID", "SD", "SSD", "OD", "ND"};
        int index;
        for (index = 0; valor / 1000.0 >= 1.0; valor /= 1000.0, ++index) {
        }
        return limitDecimals(valor) + simbols[index];
    }
}
