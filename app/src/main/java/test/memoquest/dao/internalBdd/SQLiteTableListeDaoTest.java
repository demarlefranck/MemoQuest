package test.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.memoquest.model.ListeInternalBddTest;

public class SQLiteTableListeDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;

    private ListeInternalBddTest listeInternalBddTest;



    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        listeInternalBddTest = new ListeInternalBddTest();
    }

    public ListeInternalBdd addListeTest(int i) throws Exception {

        ListeInternalBdd listeInternalBdd = listeInternalBddTest.createOneListeInternalBdd(i);
        db.addListeInternalBdd(listeInternalBdd);
        return listeInternalBdd;
    }

    public void addNListeTest(int i) throws Exception {
        List<ListeInternalBdd> listeInternalBdds = new ArrayList<ListeInternalBdd>();

        for (int j = 0; j != i; j++) {
            addListeTest(j);
        }
    }

    public void testGetAllListeInternalBdd() throws Exception {
        assertEquals(0, db.getAllListeInternalBdd().size());

        addNListeTest(10);
        assertEquals(10, db.getAllListeInternalBdd().size());

        db.deleteAllListeInternalBdd();
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    /*
        Ne compare pas IdAd car peut etre differents
     */
    public void compareAttributesOfTwoListes(ListeInternalBdd listeExpected, ListeInternalBdd listeReality){

        assertEquals(listeExpected.getId(), listeReality.getId());
        assertEquals(listeExpected.getNom(), listeReality.getNom());
        assertEquals(listeExpected.getTheme(), listeReality.getTheme());
        assertEquals(listeExpected.getCathegory(), listeReality.getCathegory());
        assertEquals(listeExpected.getShared(), listeReality.getShared());
        assertEquals(listeExpected.getMustDeleted(), listeReality.getMustDeleted());
        assertEquals(listeExpected.getCreateUser(), listeReality.getCreateUser());
        assertEquals(listeExpected.getCreateTime(), listeReality.getCreateTime());
        assertEquals(listeExpected.getUpdateUser(), listeReality.getUpdateUser());
        assertEquals(listeExpected.getUpdateTime(), listeReality.getUpdateTime());
    }

    public void testDeleteListeInternalBddWithIdAi() throws Exception {
        ListeInternalBdd listeExpected = addListeTest(1);

        ListeInternalBdd listeReality = db.getListeInternalBddWithName(listeExpected.getNom());
        compareAttributesOfTwoListes(listeExpected, listeReality);

        db.deleteListeInternalBddWithIdAi(listeReality);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    public void testDeleteListeInternalBddWithId() throws Exception {

        ListeInternalBdd listeExpected = addListeTest(1);

        ListeInternalBdd listeReality = db.getListeInternalBddWithId(listeExpected.getId());
        compareAttributesOfTwoListes(listeExpected, listeReality);

        db.deleteListeInternalBddWithId(listeExpected);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }


    public void testGetListeInternalBddWithId() throws Exception {
        ListeInternalBdd listeExpected = addListeTest(1);

        ListeInternalBdd listeReality = db.getListeInternalBddWithId(listeExpected.getId());
        compareAttributesOfTwoListes(listeExpected, listeReality);

        db.deleteListeInternalBddWithId(listeExpected);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    public void testGetListeInternalBddWithName() throws Exception {
        ListeInternalBdd listeExpected = addListeTest(1);

        ListeInternalBdd listeReality = db.getListeInternalBddWithName(listeExpected.getNom());
        compareAttributesOfTwoListes(listeExpected, listeReality);

        db.deleteListeInternalBddWithId(listeExpected);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }

    public void testDeleteAllListe() throws Exception {
        testGetAllListeInternalBdd();
    }

    public void testUpdateListeInternalBdd() throws Exception {
        ListeInternalBdd listeExpected = addListeTest(1);

        ListeInternalBdd listeReality = db.getListeInternalBddWithId(listeExpected.getId());
        compareAttributesOfTwoListes(listeExpected, listeReality);

        ListeInternalBdd listeModify = listeReality;

        listeModify.setNom("nomModify");
        listeModify.setTheme("themeModify");
        listeModify.setCathegory("cathegoryMdify");
        listeModify.setUpdateUser(1000);
        listeModify.setUpdateTime(MyDateUtils.getDateTime());
        listeModify.setMustDeleted(true);

        db.updateListeInternalBdd(listeModify);

        ListeInternalBdd listeReality2 = db.getListeInternalBddWithIdAi(listeReality.getIdAi());
        compareAttributesOfTwoListes(listeModify, listeReality2);

        db.deleteListeInternalBddWithId(listeExpected);
        assertEquals(0, db.getAllListeInternalBdd().size());
    }
}

