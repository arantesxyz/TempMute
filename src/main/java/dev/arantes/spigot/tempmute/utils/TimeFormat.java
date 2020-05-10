package dev.arantes.spigot.tempmute.utils;

public enum TimeFormat {
    s(1000L),
    m(60000L),
    h(3600000L),
    d(86400000L),
    w(604800000L),
    M(2592000000L),
    y(31104000000L);

    private long time;

    TimeFormat(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public static TimeFormat getFormat(String str) {
        str = str.trim();

        for (TimeFormat unit : values()) {
            if (str.matches("\\d+" + unit)) {
                return unit;
            }
        }

        return null;
    }
}
