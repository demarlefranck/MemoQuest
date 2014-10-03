package test.java.com.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.bdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.bdd.MotDefService;
import com.memoquest.service.bdd.UserService;
import com.memoquest.utils.MyDateUtils;

import test.java.com.memoquest.model.MotDefInternalBddTest;
import test.java.com.memoquest.model.UserInternalBddTest;

/**
 * Created by franck on 20/09/2014.
 */
public class MotDefServiceTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private MotDefService motDefService;
    private UserService userService;
    private UserInternalBddTest userInternalBddTest;
    private MotDefInternalBddTest motDefInternalBddTest;

    public void setUp() throws TechnicalAppException, FonctionalAppException {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        motDefService = new MotDefService(context);
        motDefInternalBddTest = new MotDefInternalBddTest();
        db = new SQLiteDatabaseManager(context);
        userService = new UserService(context);
        userInternalBddTest = new UserInternalBddTest();
        userService.addUserInternalBddActif(userInternalBddTest.createOneUser(1));
    }

    public void testGetMotDefInternalBddById() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(1);
        Integer id = motDefService.addMotDefInternalBdd(motDefExpected);
        motDefExpected.setId(id);

        MotDefInternalBdd motDefResult = motDefService.getMotDefInternalBddById(id);
        assertEquals(motDefExpected, motDefResult);

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateMotDefInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(1);
        Integer id = motDefService.addMotDefInternalBdd(motDefExpected);
        motDefExpected.setId(id);

        MotDefInternalBdd motDefResult = motDefService.getMotDefInternalBddById(id);
        assertEquals(motDefExpected, motDefResult);

        MotDefInternalBdd motDefModify = motDefResult;
        motDefModify.setMot("motModify");
        motDefModify.setDefinition("DefinitionModify");
        motDefModify.setMustDeleted(true);
        motDefModify.setMotDefServerId(14);
        motDefModify.setUpdateUser(1000);
        motDefModify.setUpdateTime(MyDateUtils.getDateTime());
        motDefService.updateMotDefInternalBdd(motDefModify);

        MotDefInternalBdd motDefResult2 = motDefService.getMotDefInternalBddById(id);
        assertEquals(motDefModify, motDefResult2);

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetMotDefInternalBddByListeId() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        int nbMotDef = 10;
        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(userService.getUserInternalBddActif().getId());
            motDefService.addMotDefInternalBdd(motDefExpected);
        }

        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(userService.getUserInternalBddActif().getId());
        motDefService.addMotDefInternalBdd(motDefExpected);

        assertEquals(nbMotDef + 1, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
        assertEquals(nbMotDef + 1, motDefService.getAllMotDefServiceByListe(motDefExpected.getMotDefListeServerId()).size());

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }
}
