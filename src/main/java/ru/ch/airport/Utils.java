package ru.ch.airport;

public class Utils {

    public static String nvl(String value, String def) {
        if (value == null) {
            return def;
        }
        return value;
    }

}
