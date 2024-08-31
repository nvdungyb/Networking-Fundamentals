package org.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDateFormat {
    private static final String FORMAT = "yy-MM-dd hh:mm:ss";
    private static SimpleDateFormat df = new SimpleDateFormat(FORMAT);

    public static String format(Date date) {
        return df.format(date);
    }

    public static Date parse(String date) throws ParseException {
        return df.parse(date);
    }
}
