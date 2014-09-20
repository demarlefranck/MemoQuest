package test.memoquest.dao.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;


import test.memoquest.model.MotDefInternalBddTest;

public class SQLiteTableMotDefDaoTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;

    private MotDefInternalBddTest motDefInternalBddTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        motDefInternalBddTest = new MotDefInternalBddTest();
    }

    public MotDefInternalBdd addMotDefTest(int i) throws Exception {

        MotDefInternalBdd motDefInternalBdd = motDefInternalBddTest.createOneMotDefInternalBdd(i);
        db.addMotDefInternalBdd(motDefInternalBdd);
        return motDefInternalBdd;
    }

    public List<MotDefInternalBdd> addListMotDefTest(int i) throws Exception {
        List<MotDefInternalBdd> motDefInternalBdds = new ArrayList<MotDefInternalBdd>();

        for (int j = 0; j != i; j++) {
            addMotDefTest(j);
        }
        return motDefInternalBdds;
    }

    public void testGetAllMotDefInternalBdd() throws Exception {
        assertEquals(0, db.getAllMotDefInternalBdd().size());

        addListMotDefTest(10);
        assertEquals(10, db.getAllMotDefInternalBdd().size());

        db.deleteAllMotDefInternalBdd();
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testGetAllMotDefServiceForListe() throws Exception {
        assertEquals(0, db.getAllMotDefInternalBdd().size());

        addListMotDefTest(10);

        for (int j = 0; j != 10; j++) {
            assertEquals(1, db.getAllMotDefForListe(j).size());
        }

        db.deleteAllMotDefInternalBdd();
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    /*
        Ne compare pas IdAd car peut etre differents
     */
    public void compareAttributesOfTwoMotDef(MotDefInternalBdd motDefExpected, MotDefInternalBdd motDefReality){

        assertEquals(motDefExpected.getId(), motDefReality.getId());
        assertEquals(motDefExpected.getMot(), motDefReality.getMot());
        assertEquals(motDefExpected.getDefinition(), motDefReality.getDefinition());
        assertEquals(motDefExpected.getMustDeleted(), motDefReality.getMustDeleted());
        assertEquals(motDefExpected.getMotDefListId(), motDefReality.getMotDefListId());
        assertEquals(motDefExpected.getCreateUser(), motDefReality.getCreateUser());
        assertEquals(motDefExpected.getCreateTime(), motDefReality.getCreateTime());
        assertEquals(motDefExpected.getUpdateUser(), motDefReality.getUpdateUser());
        assertEquals(motDefExpected.getUpdateTime(), motDefReality.getUpdateTime());
    }

    public void testDeleteMotDefInternalBddWithIdAi() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        MotDefInternalBdd motDefReality = db.getMotDefInternalBddWithMot(motDefExpected.getMot());
        compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.deleteMotDefInternalBddWithIdAi(motDefReality);
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testDeleteMotDefInternalBddWithId() throws Exception {

        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        MotDefInternalBdd motDefReality = db.getMotDefInternalBddWithId(motDefExpected.getId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.deleteMotDefInternalBddWithId(motDefExpected);
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }


    public void testGetMotDefInternalBddWithId() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        MotDefInternalBdd motDefReality = db.getMotDefInternalBddWithId(motDefExpected.getId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.deleteMotDefInternalBddWithId(motDefExpected);
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testGetMotDefInternalBddWithName() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        MotDefInternalBdd motDefReality = db.getMotDefInternalBddWithMot(motDefExpected.getMot());
        compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        db.deleteMotDefInternalBddWithId(motDefExpected);
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testDeleteAllMotDef() throws Exception {
        testGetAllMotDefInternalBdd();
    }

    public void testUpdateMotDefInternalBdd() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        MotDefInternalBdd motDefReality = db.getMotDefInternalBddWithId(motDefExpected.getId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefReality);

        MotDefInternalBdd motDefModify = motDefReality;
        motDefModify.setMot("motModify");
        motDefModify.setDefinition("definitionModify");
        motDefModify.setUpdateUser(1000);
        motDefModify.setUpdateTime(MyDateUtils.getDateTime());
  //      motDefModify.setMustDeleted(true);

        db.updateMotDefInternalBdd(motDefModify);

        MotDefInternalBdd motDefReality2 = db.getMotDefInternalBddWithIdAi(motDefReality.getIdAi());
        compareAttributesOfTwoMotDef(motDefModify, motDefReality2);

        db.deleteMotDefInternalBddWithId(motDefExpected);
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }
}

