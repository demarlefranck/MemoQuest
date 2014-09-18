package test.memoquest.dao;


import android.test.InstrumentationTestCase;

public class ListeDaoTest extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 5;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}