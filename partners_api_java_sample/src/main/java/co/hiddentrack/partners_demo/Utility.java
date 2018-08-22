package co.hiddentrack.partners_demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utility {

    private static final Calendar calendar = Calendar.getInstance();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static Date stringToDate(String string) throws ParseException {
        return format.parse(string);
    }

    public static String dateToString(Date date) throws ParseException {
        return format.format(date);
    }
}
