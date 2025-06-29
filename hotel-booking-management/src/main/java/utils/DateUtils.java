package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public static boolean isDateRangeValid(LocalDate startDate, LocalDate endDate) {
        return startDate.isBefore(endDate) || startDate.isEqual(endDate);
    }
}