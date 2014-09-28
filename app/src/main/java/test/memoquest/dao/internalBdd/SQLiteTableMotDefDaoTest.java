package test.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import test.memoquest.model.MotDefInternalBddTest;

public class SQLiteTableMotDefDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private MotDefInternalBddTest motDefInternalBddTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        motDefInternalBddTest = new MotDefInternalBddTest();
    }

    public void testAddMotDefToInternalBdd() throws TechnicalAppException {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        int nbListes = 10;
        for (int i = 0; i != nbListes; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(i);
            Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        }

        assertEquals(nbListes, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }


    public void testUpdateMotDefToInternalBdd() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(1);
        Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        motDefExpected.setId(id);

        MotDefInternalBdd motDefResult1 = db.getSqLiteTableMotDefDao().getMotDefInternalBddById(db.getWritableDatabase(), id);
        motDefInternalBddTest.compareAttributesOfTwoMotDef(motDefExpected, motDefResult1);

        MotDefInternalBdd motDefModify = motDefResult1;
        motDefModify.setMot("motModify");
        motDefModify.setDefinition("DefinitionModify");
        motDefModify.setMustDeleted(true);
        motDefModify.setMotDefServerId(14);
        motDefModify.setUpdateUser(1000);
        motDefModify.setUpdateTime(MyDateUtils.getDateTime());
        db.getSqLiteTableMotDefDao().updateMotDefToInternalBdd(db.getWritableDatabase(), motDefModify);

        MotDefInternalBdd motDefReality2 = db.getSqLiteTableMotDefDao().getMotDefInternalBddById(db.getWritableDatabase(), id);
        motDefInternalBddTest.compareAttributesOfTwoMotDef(motDefModify, motDefReality2);

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }


    public void testGetAllMotDefInternalBddByListeId() throws Exception {

        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        int nbMotDef = 10;
        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(i);
            Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        }
        for (int i = 0; i != nbMotDef; i++) {
            assertEquals(1, db.getSqLiteTableMotDefDao().getAllMotDefInternalBddByListeId(db.getWritableDatabase(), i).size());
        }

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetAllMotDefInternalBddByListeId2() throws Exception {
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        int nbMotDef = 10;
        int listeId = 42;
        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(listeId);
            Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        }

        assertEquals(nbMotDef, db.getSqLiteTableMotDefDao().getAllMotDefInternalBddByListeId(db.getWritableDatabase(), listeId).size());

        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testGetMotDefInternalBddById() throws Exception {
        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(1);
        Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        motDefExpected.setId(id);

        MotDefInternalBdd motDefReality = db.getSqLiteTableMotDefDao().getMotDefInternalBddById(db.getWritableDatabase(), motDefExpected.getId());
        motDefInternalBddTest.compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.getSqLiteTableMotDefDao().deleteMotDefInternalBddById(db.getWritableDatabase(), motDefExpected);
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testDeleteListeInternalBddById() throws Exception {
        MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(1);
        Integer id = db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDefExpected);
        motDefExpected.setId(id);

        MotDefInternalBdd motDefReality = db.getSqLiteTableMotDefDao().getMotDefInternalBddById(db.getWritableDatabase(), motDefExpected.getId());
        motDefInternalBddTest.compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.getSqLiteTableMotDefDao().deleteMotDefInternalBddById(db.getWritableDatabase(), motDefExpected);
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }
}