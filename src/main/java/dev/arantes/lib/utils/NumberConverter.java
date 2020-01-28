package dev.arantes.lib.utils;

public class NumberConverter {
    public static long toLong(Object value) {
        return (value instanceof Number ? ((Number) value).longValue() : -1);
    }

    public static double toDouble(Object value) {
        return (value instanceof Number ? ((Number) value).doubleValue() : -1.0);
    }

    public static int toInt(Object value) {
        return (value instanceof Number ? ((Number) value).intValue() : -1);
    }
}