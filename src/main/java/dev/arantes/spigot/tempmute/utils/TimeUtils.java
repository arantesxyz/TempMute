package dev.arantes.spigot.tempmute.utils;

public class TimeUtils {
    // Probably not the best readable function but fast
    public static String formatMillisToString(long millis) {
        long seconds = millis / 1000;
        if (seconds < 60) {
            return seconds + "s";
        }

        long minutes = seconds / 60;
        seconds = seconds % 60;

        if (minutes < 60) {
            return String.format("%dm %ds", minutes, seconds);
        }

        long hours = minutes / 60;
        minutes = minutes % 60;

        if (hours < 24) {
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        }

        int days = (int) hours / 24;
        hours = hours % 24;

        if (days < 7) {
            return String.format("%dd %dh %dm %ds", days, hours, minutes, seconds);
        }

        int weeks = days / 7;
        days = days % 7;

        if (weeks < 4) {
            return String.format("%dw %dd %dh %dm %ds", weeks, days, hours, minutes, seconds);
        }

        int months = weeks / 4;
        weeks = weeks % 4;

        if (months < 12) {
            return String.format("%dM %dw %dd %dh %dm %ds", months, weeks, days, hours, minutes, seconds);
        }

        int years = months / 12;
        months = months % 12;

        return String.format("%dy %dM %dw %dd %dh %dm %ds", years, months, weeks, days, hours, minutes, seconds);
    }
}