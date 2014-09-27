package com.memoquest.utils;

import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class MyDateUtils {

    public static String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date convertDateStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.parse(dateString);
    }

    /*
        retourne -1 si dateStr1 < dateStr2
        retourne  0 si dateStr1 == dateStr2
        retourne  1 si dateStr1 > dateStr2
     */
    public static Integer compareDates(String dateStr1, String dateStr2) throws ParseException {

        Date date1 = MyDateUtils.convertDateStringToDate(dateStr1);
        Date date2 = MyDateUtils.convertDateStringToDate(dateStr2);

        if(date1.compareTo(date2) > 0) {
            return 1;
        }
        else if(date1.compareTo(date2) < 0) {
            return -1;
        }
        else if(date1.compareTo(date2) == 0) {
            return 0;
        }
        return null;
    }
}
