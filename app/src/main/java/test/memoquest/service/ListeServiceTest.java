package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.utils.MyDateUtils;

import java.util.List;

import test.memoquest.model.ListeInternalBddTest;
import test.memoquest.model.UserInternalBddTest;

/**
 * Created by franck on 20/09/2014.
 */
public class ListeServiceTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private ListeService listeService;
    private UserService userService;
    private UserInternalBddTest userInternalBddTest;
    private ListeInternalBddTest listeInternalBddTest;

    public void setUp() throws TechnicalAppException, FonctionalAppException {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        listeService = new ListeService(context);
        listeInternalBddTest = new ListeInternalBddTest();
        db = new SQLiteDatabaseManager(context);
        userService = new UserService(context);
        userInternalBddTest = new UserInternalBddTest();
        userService.addUserInternalBddActif(userInternalBddTest.createOneUser(1));
    }


    public void testGetListeInternalBddById() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(1);
        Integer id = listeService.addListeInternalBdd(listeExpected);
        listeExpected.setId(id);

        ListeInternalBdd listeResult = listeService.getListeInternalBddById(id);
        listeInternalBddTest.compareAttributesOfTwoListes(listeExpected, listeResult);

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetListeInternalBddByUserNotFound() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        ListeInternalBdd listeInternalBdd = listeInternalBddTest.createOneListeInternalBdd(1);
        assertEquals(0, listeService.getListeInternalBddByUser(1).size());

        listeService.addListeInternalBdd(listeInternalBdd);
        assertEquals(0, listeService.getListeInternalBddByUser(10000000).size());

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateListeInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(1);
        Integer id = listeService.addListeInternalBdd(listeExpected);
        listeExpected.setId(id);

        ListeInternalBdd listeResult1 = listeService.getListeInternalBddById(id);
        listeInternalBddTest.compareAttributesOfTwoListes(listeExpected, listeResult1);


        ListeInternalBdd listeModify = listeResult1;
        listeModify.setNom("nomModify");
        listeModify.setTheme("themeModify");
        listeModify.setCategory("categoryMdify");
        listeModify.setUpdateUser(1000);
        listeModify.setUpdateTime(MyDateUtils.getDateTime());
        listeModify.setMustDeleted(true);
        listeService.updateListeInternalBdd(listeModify);

        ListeInternalBdd listeReality2 = listeService.getListeInternalBddById(id);
        listeInternalBddTest.compareAttributesOfTwoListes(listeModify, listeReality2);

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetListeInternalBddByUser() throws TechnicalAppException, FonctionalAppException {

        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        int nbListe = 10;
        for (int i = 0; i != nbListe; i++) {
            ListeInternalBdd listeInternalBdd = listeInternalBddTest.createOneListeInternalBdd(userService.getUserInternalBddActif().getId());
            listeService.addListeInternalBdd(listeInternalBdd);
        }
        assertEquals(nbListe, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
        assertEquals(nbListe, listeService.getListeInternalBddByUser(userService.getUserInternalBddActif().getId()).size());

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }
}
