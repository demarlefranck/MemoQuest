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

        for(MotDefInternalBdd motDef : db.getAllMotDefInternalBdd()){

            db.deleteMotDefInternalBdd(motDef);
        }
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testGetAllMotDefServiceForListe() throws Exception {
        assertEquals(0, db.getAllMotDefInternalBdd().size());

        addListMotDefTest(10);

        for (int j = 0; j != 10; j++) {
            assertEquals(1, db.getAllMotDefByListeInternalBddId(j).size());
        }

        for(MotDefInternalBdd motDef : db.getAllMotDefInternalBdd()){

            db.deleteMotDefInternalBdd(motDef);
        }
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    /*
        Ne compare pas IdAd car peut etre differents
     */
    public void compareAttributesOfTwoMotDef(MotDefInternalBdd motDefExpected, MotDefInternalBdd motDefReality){
        assertEquals(motDefExpected.getMotDefServerId(), motDefReality.getMotDefServerId());
        assertEquals(motDefExpected.getMotDefListeInternalBddId(), motDefReality.getMotDefListeInternalBddId());
        assertEquals(motDefExpected.getMotDefListeServerId(), motDefReality.getMotDefListeServerId());
        assertEquals(motDefExpected.getMot(), motDefReality.getMot());
        assertEquals(motDefExpected.getDefinition(), motDefReality.getDefinition());
        assertEquals(motDefExpected.getMustDeleted(), motDefReality.getMustDeleted());
        assertEquals(motDefExpected.getCreateUser(), motDefReality.getCreateUser());
        assertEquals(motDefExpected.getCreateTime(), motDefReality.getCreateTime());
        assertEquals(motDefExpected.getUpdateUser(), motDefReality.getUpdateUser());
        assertEquals(motDefExpected.getUpdateTime(), motDefReality.getUpdateTime());
    }

    public void testDeleteMotDefInternalBddById() throws Exception {

        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        List<MotDefInternalBdd> motDefRealitys = db.getAllMotDefByListeInternalBddId(motDefExpected.getMotDefListeInternalBddId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefRealitys.get(0));

        db.deleteMotDefInternalBdd(motDefRealitys.get(0));
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }


    public void testGetMotDefInternalBddById() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        List<MotDefInternalBdd> motDefRealitys = db.getAllMotDefByListeInternalBddId(motDefExpected.getMotDefListeInternalBddId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefRealitys.get(0));

        db.deleteMotDefInternalBdd(motDefRealitys.get(0));
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testGetMotDefInternalBddByName() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        List<MotDefInternalBdd> motDefRealitys = db.getAllMotDefByListeInternalBddId(motDefExpected.getMotDefListeInternalBddId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefRealitys.get(0));

        db.deleteMotDefInternalBdd(motDefRealitys.get(0));
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }

    public void testDeleteAllMotDef() throws Exception {
        testGetAllMotDefInternalBdd();
    }

    public void testUpdateMotDefInternalBdd() throws Exception {
        MotDefInternalBdd motDefExpected = addMotDefTest(1);

        List<MotDefInternalBdd> motDefRealitys = db.getAllMotDefByListeInternalBddId(motDefExpected.getMotDefListeInternalBddId());
        compareAttributesOfTwoMotDef(motDefExpected, motDefRealitys.get(0));

        MotDefInternalBdd motDefModify = motDefRealitys.get(0);
        motDefModify.setMot("motModify");
        motDefModify.setDefinition("definitionModify");
        motDefModify.setUpdateUser(1000);
        motDefModify.setUpdateTime(MyDateUtils.getDateTime());
  //      motDefModify.setMustDeleted(true);

        db.updateMotDefInternalBdd(motDefModify);

        MotDefInternalBdd motDefReality2 = db.getMotDefInternalBddById(motDefRealitys.get(0).getId());
        compareAttributesOfTwoMotDef(motDefModify, motDefReality2);

        db.deleteMotDefInternalBdd(motDefRealitys.get(0));
        assertEquals(0, db.getAllMotDefInternalBdd().size());
    }
}

