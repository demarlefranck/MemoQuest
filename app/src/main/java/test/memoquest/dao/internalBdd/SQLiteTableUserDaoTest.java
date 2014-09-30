package test.memoquest.dao.internalBdd;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.List;

import test.memoquest.model.UserInternalBddTest;

public class SQLiteTableUserDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private UserInternalBddTest userTest;

    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        userTest = new UserInternalBddTest();
    }

    public void testAddUserInternalBdd() throws TechnicalAppException {
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        int nbUser = 10;
        for (int i = 0; i != nbUser; i++) {
            UserInternalBdd userExpected = userTest.createOneUser(i);
            Integer id = db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected);
        }

        assertEquals(nbUser, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetUserInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        UserInternalBdd userExpected = userTest.createOneUser(1);
        Integer id = db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected);
        userExpected.setId(id);

        UserInternalBdd userResult1 = db.getSqLiteTableUserDao().getUserInternalBddActifById(db.getWritableDatabase(), id);
        assertEquals(userExpected, userResult1);

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateUserInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        UserInternalBdd userExpected = userTest.createOneUser(1);
        Integer id = db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected);
        userExpected.setId(id);

        UserInternalBdd userResult1 = db.getSqLiteTableUserDao().getUserInternalBddActifById(db.getWritableDatabase(), id);
        assertEquals(userExpected, userResult1);

        UserInternalBdd userModify = userResult1;
        userModify.setPassword("PasswordModify");
        userModify.setPseudo("PseudoModify");
        userModify.setEmail("EmailModify");
        userModify.setActif(false);
        userModify.setServerId(1200);
        userModify.setUpdateTime(MyDateUtils.getDateTime());
        db.getSqLiteTableUserDao().updateUserInternalBdd(db.getWritableDatabase(), userModify);

        UserInternalBdd userResult2 = db.getSqLiteTableUserDao().getUserInternalBddActifById(db.getWritableDatabase(), id);
        assertEquals(userModify, userResult2);

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetUserInternalBddActif2() throws Exception {

        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        int nbUser = 10;
        for (int i = 0; i != nbUser; i++) {
            UserInternalBdd userExpected = userTest.createOneUser(i);
            userExpected.setActif(false);
            db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected);
        }

        UserInternalBdd userExpected2 = userTest.createOneUser(nbUser + 1);
        Integer id = db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected2);
        userExpected2.setId(id);

        assertEquals(nbUser + 1, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        UserInternalBdd userResult1 = db.getSqLiteTableUserDao().getUserInternalBddActif(db.getWritableDatabase());
        assertEquals(userExpected2, userResult1);

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetUserInternalBddActif() throws Exception {

        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        UserInternalBdd userExpected = userTest.createOneUser(1);
        Integer id = db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userExpected);
        userExpected.setId(id);

        UserInternalBdd userResult1 = db.getSqLiteTableUserDao().getUserInternalBddActif(db.getWritableDatabase());
        assertEquals(userExpected, userResult1);

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }
}