package test.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.UserInternalBdd;

import test.memoquest.model.UserTest;

public class SQLiteTableUserDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private UserTest userTest;

    public void setUp() {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        userTest = new UserTest();
    }

    public UserInternalBdd addUser(int i) {
        UserInternalBdd user = userTest.createOneUser(i);
        db.addUserInternalBdd(user);
        return user;
    }

    public void compareAttributesOfTwoUser(UserInternalBdd userExpected, UserInternalBdd userReality){
        assertEquals(userExpected.getId(), userReality.getId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
        assertEquals(userExpected.getActive(), userReality.getActive());
        assertEquals(userExpected.getCreateUser(), userReality.getCreateUser());
        assertEquals(userExpected.getCreateTime(), userReality.getCreateTime());
        assertEquals(userExpected.getUpdateUser(), userReality.getUpdateUser());
        assertEquals(userExpected.getUpdateTime(), userReality.getUpdateTime());
    }

    public void testGetUserInternalBddActive() throws TechnicalAppException, FonctionalAppException {

        assertEquals(0, db.getAllUserInternalBdd().size());

        UserInternalBdd userExpected = addUser(1);
        UserInternalBdd userReality = db.getUserInternalBddActive();

        assertEquals(1, db.getAllUserInternalBdd().size());

        compareAttributesOfTwoUser(userExpected, userReality);

        db.deleteAllListeInternalBdd();
    }

    public void testUpdateUserInternalBddActive() throws TechnicalAppException, FonctionalAppException {

        assertEquals(0, db.getAllUserInternalBdd().size());

        UserInternalBdd userExpected = addUser(1);
        UserInternalBdd userReality = db.getUserInternalBddActive();
        assertEquals(1, db.getAllUserInternalBdd().size());

        UserInternalBdd userModify = userReality;
        userModify.setEmail("totototo@toto.fr");
        userModify.setPassword("passwordModif");
        db.updateUserInternalBdd(userModify);
        UserInternalBdd userReality2 = db.getUserInternalBddActive();
        compareAttributesOfTwoUser(userModify, userReality2);


//        db.deleteUser
    }
}