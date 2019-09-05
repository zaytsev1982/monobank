package ua.com.monobank.formatter;

import java.util.Date;

public class DateFormatter {

    public static Date getDate(String s) {
        long aLong = Long.parseLong(s);
        Date date = new Date(aLong * 1000);
        return date;
    }

}
