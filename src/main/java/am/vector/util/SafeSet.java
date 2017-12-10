package am.vector.util;

import java.time.LocalDate;

public class SafeSet {
    public static LocalDate date(String date){
        return date == null ? LocalDate.parse("1900-01-01") : LocalDate.parse(date);
    }
}
