package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.utils.MyDateUtils;

import test.memoquest.model.UserInternalBddTest;

/**
 * Created by franck on 20/09/2014.
 */
public class UserServiceTest extends AndroidTestCase {


    private UserService userService;
    private UserInternalBddTest userTest;
    private SQLiteDatabaseManager db;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        userService = new UserService(context);
        userTest = new UserInternalBddTest();
        db = new SQLiteDatabaseManager(context);
    }

    public void compareAttributesOfTwoUser(UserInternalBdd userExpected, UserInternalBdd userReality){
        assertEquals(userExpected.getId(), userReality.getId());
        assertEquals(userExpected.getEmail(), userReality.getEmail());
        assertEquals(userExpected.getPassword(), userReality.getPassword());
        assertEquals(userExpected.getPseudo(), userReality.getPseudo());
        assertEquals(userExpected.getActif(), userReality.getActif());
        assertEquals(userExpected.getCreateUser(), userReality.getCreateUser());
        assertEquals(userExpected.getCreateTime(), userReality.getCreateTime());
        assertEquals(userExpected.getUpdateUser(), userReality.getUpdateUser());
        assertEquals(userExpected.getUpdateTime(), userReality.getUpdateTime());
    }

    public void testGetAllUserInternalBdd() throws TechnicalAppException, FonctionalAppException {

        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
        assertEquals(0, userService.getAllUserInternalBdd().size());

        int nbListe = 10;
        for (int i = 0; i != nbListe; i++) {
            UserInternalBdd userInternalBdd = userTest.createOneUser(i);
            userService.addUserInternalBddActif(userInternalBdd);
        }

        assertEquals(nbListe, userService.getAllUserInternalBdd().size());

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetUserInternalBddActif() throws TechnicalAppException, FonctionalAppException {

        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
        assertEquals(0, userService.getAllUserInternalBdd().size());

        int nbListe = 10;
        for (int i = 0; i != nbListe; i++) {
            UserInternalBdd userInternalBdd = userTest.createOneUser(i);
            userService.addUserInternalBddActif(userInternalBdd);
        }

        UserInternalBdd userInternalBdd2 = userTest.createOneUser(10);
        Integer id = userService.addUserInternalBddActif(userInternalBdd2);
        userInternalBdd2.setId(id);

        assertEquals(id, userService.getUserInternalBddActif().getId());

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testIsAuthentifiate() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        assertEquals(0, userService.getAllUserInternalBdd().size());

        assertFalse(userService.isAuthentifiate());

        UserInternalBdd userInternalBdd = userTest.createOneUser(1);
        Integer id = userService.addUserInternalBddActif(userInternalBdd);
        assertEquals(1, userService.getAllUserInternalBdd().size());

        assertTrue(userService.isAuthentifiate());

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateUserInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());

        UserInternalBdd userExpected = userTest.createOneUser(1);
        Integer id = userService.addUserInternalBddActif(userExpected);
        userExpected.setId(id);

        UserInternalBdd userResult1 = userService.getUserInternalBddActif();
        compareAttributesOfTwoUser(userExpected, userResult1);

        UserInternalBdd userModify = userResult1;
        userModify.setPassword("PasswordModify");
        userModify.setPseudo("PseudoModify");
        userModify.setEmail("EmailModify");
        userModify.setActif(true);
        userModify.setServerId(1200);
        userModify.setUpdateTime(MyDateUtils.getDateTime());
        userService.updateUserInternalBdd(userModify);

        UserInternalBdd userResult2 = userService.getUserInternalBddActif();
        compareAttributesOfTwoUser(userModify, userResult2);

        db.getSqLiteTableUserDao().deleteAllUserInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase()).size());
    }
}
