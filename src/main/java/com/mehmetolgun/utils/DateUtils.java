package com.mehmetolgun.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static String getCurrentDate(Date date) {
        // Türkiye saat dilimi
        TimeZone timeZoneTR = TimeZone.getTimeZone("Europe/Istanbul");
        Calendar calendar = Calendar.getInstance(timeZoneTR);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        simpleDateFormat.setTimeZone(timeZoneTR);

        // Saat 15:01'den önce ise bir önceki iş günü verilerini almak için kontrol
        if (isWeekend(calendar)) {
            moveToPreviousBusinessDay(calendar);
        }

        return simpleDateFormat.format(calendar.getTime());
    }

    private static boolean isWeekend(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    private static boolean isBeforeUpdateTime(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && (hour < 15 || (hour == 15 && minute < 1));
    }

    private static void moveToPreviousBusinessDay(Calendar calendar) {
        do {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        } while (isWeekend(calendar));
    }
}
