package test.memoquest.utils;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.utils.MyDateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import test.memoquest.model.CompleteListeTest;

/**
 * Created by franck on 20/09/2014.
 */
public class MyDateUtilsTest extends AndroidTestCase {

    public void testcompareDate1InfDate2() throws ParseException {

        String dateStr1 = MyDateUtils.getDateTime();
        String dateStr2 = MyDateUtils.getDateTime();


        assertEquals(new Integer(1), MyDateUtils.compareDates(dateStr1, dateStr2));
    }
}
