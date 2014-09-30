package test.memoquest.utils;

import android.test.AndroidTestCase;

import com.memoquest.utils.MyDateUtils;

import java.text.ParseException;

/**
 * Created by franck on 20/09/2014.
 */
public class MyDateUtilsTest extends AndroidTestCase {

    public void testcompareDate1InfDate2() throws ParseException, InterruptedException {

        String dateStr1 = MyDateUtils.getDateTime();
        Thread.sleep(1000);
        String dateStr2 = MyDateUtils.getDateTime();

        assertEquals(new Integer(-1), MyDateUtils.compareDates(dateStr1, dateStr2));
    }

    public void testcompareDate1SupDate2() throws ParseException, InterruptedException {

        String dateStr2 = MyDateUtils.getDateTime();
        Thread.sleep(1000);
        String dateStr1 = MyDateUtils.getDateTime();

        assertEquals(new Integer(1), MyDateUtils.compareDates(dateStr1, dateStr2));
    }

    public void testcompareDate1EqualDate2() throws ParseException, InterruptedException {

        String dateStr1 = MyDateUtils.getDateTime();
        String dateStr2 = MyDateUtils.getDateTime();

        assertEquals(new Integer(0), MyDateUtils.compareDates(dateStr1, dateStr2));
    }
}
