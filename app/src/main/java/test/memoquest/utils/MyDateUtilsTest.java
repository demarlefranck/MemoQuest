package test.memoquest.utils;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.CompleteListeService;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.utils.MyDateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import test.memoquest.model.CompleteListeTest;
import test.memoquest.model.ListeInternalBddTest;
import test.memoquest.model.MotDefInternalBddTest;
import test.memoquest.model.UserInternalBddTest;

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
