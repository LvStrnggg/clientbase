package dev.you.client.utils;

public final class MathUtils {
    public static double roundToDecimal(double n, double point) {
        return point * Math.round(n / point);
    }
}
