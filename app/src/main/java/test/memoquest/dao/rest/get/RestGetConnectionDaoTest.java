package test.memoquest.dao.rest.get;

import android.test.InstrumentationTestCase;

public class RestGetConnectionDaoTest extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}