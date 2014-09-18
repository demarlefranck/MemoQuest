package test.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.User;

import java.util.LinkedList;
import java.util.List;
/*
public class SQLiteTableUserDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;



    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);

    }

    public void testGetAllUser() throws Exception {
        User user = new User(null, "emailTest", "paswordTest");


        db.addListeInternalBdd(listeExpected);
        ListeInternalBdd listeReality = db.getAllListeInternalBdd().get(0);
        db.deleteListeInternalBddWithIdTemp(listeExpected);


        assertNotNull(listeReality.getIdTemp());


        assertEquals(listeExpected.getId(), listeReality.getId());
        assertEquals(listeExpected.getNom(), listeReality.getNom());
        assertEquals(listeExpected.getTheme(), listeReality.getTheme());
        assertEquals(listeExpected.getCathegory(), listeReality.getCathegory());
        assertEquals(listeExpected.getSynchro(), listeReality.getSynchro());
    }

    public void testGetListeInternalBddWithId() throws Exception {
        ListeInternalBdd listeExpected = new ListeInternalBdd(null, -1,"NameListeTest", "themeListeTest", "categoryListeTest", false, false);
        db.addListeInternalBdd(listeExpected);

        ListeInternalBdd listeReality = db.getListeInternalBddWithId(listeExpected.getId());
        db.deleteListeInternalBddWithIdTemp(listeExpected);

        assertNotNull(listeReality.getIdTemp());
        assertEquals(listeExpected.getId(), listeReality.getId());
        assertEquals(listeExpected.getNom(), listeReality.getNom());
        assertEquals(listeExpected.getTheme(), listeReality.getTheme());
        assertEquals(listeExpected.getCathegory(), listeReality.getCathegory());
        assertEquals(listeExpected.getSynchro(), listeReality.getSynchro());
    }

    public void testGetListeInternalBddWithName() throws Exception {
        ListeInternalBdd listeExpected = new ListeInternalBdd(null, -1,"NameListeTest", "themeListeTest", "categoryListeTest", false, false);
        db.addListeInternalBdd(listeExpected);
        ListeInternalBdd listeReality = db.getListeInternalBddWithName(listeExpected.getNom());
        db.deleteListeInternalBddWithIdTemp(listeExpected);

        assertNotNull(listeReality.getIdTemp());
        assertEquals(listeExpected.getId(), listeReality.getId());
        assertEquals(listeExpected.getNom(), listeReality.getNom());
        assertEquals(listeExpected.getTheme(), listeReality.getTheme());
        assertEquals(listeExpected.getCathegory(), listeReality.getCathegory());
        assertEquals(listeExpected.getSynchro(), listeReality.getSynchro());
    }

    public void testDeleteAllListe() throws Exception {
        ListeInternalBdd listeExpected1 = new ListeInternalBdd(null, -1,"NameListeTest1", "themeListeTest2", "categoryListeTest3", false, false);
        db.addListeInternalBdd(listeExpected1);
        ListeInternalBdd listeExpected2 = new ListeInternalBdd(null, -1,"NameListeTest2", "themeListeTest2", "categoryListeTest3", false, false);
        db.addListeInternalBdd(listeExpected2);
        ListeInternalBdd listeExpected3 = new ListeInternalBdd(null, -1,"NameListeTest3", "themeListeTest2", "categoryListeTest3", false, false);
        db.addListeInternalBdd(listeExpected3);
        assertEquals(3, db.getAllListeInternalBdd().size());

        db.deleteAllListeInternalBdd();
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    public void testDeleteListeInternalBddWithIdTemp() throws Exception {

        ListeInternalBdd listeExpected = new ListeInternalBdd(null, -1,"NameListeTest", "themeListeTest", "categoryListeTest", false, false);
        db.addListeInternalBdd(listeExpected);
        assertEquals(1, db.getAllListeInternalBdd().size());


        ListeInternalBdd listeReality = db.getListeInternalBddWithName(listeExpected.getNom());

        db.deleteListeInternalBddWithIdTemp(listeReality);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    public void testDeleteListeInternalBddWithId() throws Exception {

        ListeInternalBdd listeExpected = new ListeInternalBdd(null, -1,"NameListeTest", "themeListeTest", "categoryListeTest", false, false);
        db.addListeInternalBdd(listeExpected);
        assertEquals(1, db.getAllListeInternalBdd().size());

        db.deleteListeInternalBddWithId(listeExpected);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }
}
*/

/*

    public void addUser(SQLiteDatabase db, User user) {

    public List<User> getAllUser(SQLiteDatabase db) {

    public boolean deleteUserWithId(SQLiteDatabase db, User user) {

    public void deleteAllUsers(SQLiteDatabase db) {

    */