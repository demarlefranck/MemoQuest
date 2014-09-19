package test.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.User;

import test.memoquest.model.UserTest;

public class SQLiteTableUserDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private UserTest userTest;

    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        userTest = new UserTest();
    }

    public User addUser() {
        User user = userTest.createOneUser();
        db.addUser(user);
        return user;
    }

    public void testGetUser() {

        User userExpected = addUser();
        User userReality = null;
        try {
            userReality = db.getUser();
        } catch (TechnicalAppException e) {
            e.printStackTrace();
        } catch (FonctionalAppException e) {
            e.printStackTrace();
        }
        assertEquals(userExpected.getId(), userReality.getId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
    }
}