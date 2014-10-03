package test.java.com.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.bdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.utils.MyDateUtils;

import test.java.com.memoquest.model.ListeInternalBddTest;


public class SQLiteTableListeDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private ListeInternalBddTest listeInternalBddTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        listeInternalBddTest = new ListeInternalBddTest();
    }

    public void testAddListeInternalBdd() throws TechnicalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        int nbListes = 10;
        for (int i = 0; i != nbListes; i++) {
            ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(i);
            Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        }

        assertEquals(nbListes, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateListeInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(1);
        Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        listeExpected.setId(id);

        ListeInternalBdd listeResult1 = db.getSqLiteTableListeDao().getListeInternalBddById(db.getWritableDatabase(), id);
        assertEquals(listeExpected, listeResult1);

        ListeInternalBdd listeModify = listeResult1;
        listeModify.setNom("nomModify");
        listeModify.setTheme("themeModify");
        listeModify.setCategory("categoryMdify");
        listeModify.setUpdateUser(1000);
        listeModify.setUpdateTime(MyDateUtils.getDateTime());
        listeModify.setMustDeleted(true);
        db.getSqLiteTableListeDao().updateListeInternalBdd(db.getWritableDatabase(), listeModify);

        ListeInternalBdd listeReality2 = db.getSqLiteTableListeDao().getListeInternalBddById(db.getWritableDatabase(), id);
        assertEquals(listeModify, listeReality2);

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetListeInternalBddByUser() throws Exception {

        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        int nbListes = 10;
        for (int i = 0; i != nbListes; i++) {
            ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(i);
            Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        }
        for (int i = 0; i != nbListes; i++) {
            assertEquals(1, db.getSqLiteTableListeDao().getListeInternalBddByUser(db.getWritableDatabase(), i).size());
        }

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetListeInternalBddByUser2() throws Exception {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());

        int nbListes = 10;
        int userId = 42;
        for (int i = 0; i != nbListes; i++) {
            ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(userId);
            Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        }
        for (int i = 0; i != nbListes; i++) {
            assertEquals(nbListes, db.getSqLiteTableListeDao().getListeInternalBddByUser(db.getWritableDatabase(), userId).size());
        }

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetListeInternalBddById() throws Exception {
        ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(1);
        Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        listeExpected.setId(id);

        ListeInternalBdd listeReality = db.getSqLiteTableListeDao().getListeInternalBddById(db.getWritableDatabase(), listeExpected.getId());
        assertEquals(listeExpected, listeReality);

        db.getSqLiteTableListeDao().deleteListeInternalBddById(db.getWritableDatabase(), listeExpected);
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }

    public void testDeleteListeInternalBddById() throws Exception {
        ListeInternalBdd listeExpected = listeInternalBddTest.createOneListeInternalBdd(1);
        Integer id = db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeExpected);
        listeExpected.setId(id);

        ListeInternalBdd listeReality = db.getSqLiteTableListeDao().getListeInternalBddById(db.getWritableDatabase(), listeExpected.getId());
        assertEquals(listeExpected, listeReality);

        db.getSqLiteTableListeDao().deleteListeInternalBddById(db.getWritableDatabase(), listeExpected);
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
    }
}