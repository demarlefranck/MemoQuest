package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.service.CompleteListeService;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.UserService;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

import test.memoquest.model.CompleteListeTest;
import test.memoquest.model.ListeInternalBddTest;
import test.memoquest.model.MotDefInternalBddTest;
import test.memoquest.model.UserInternalBddTest;

/**
 * Created by franck on 20/09/2014.
 */
public class CompleteListeServiceTest extends AndroidTestCase {

    private SQLiteDatabaseManager db;
    private UserService userService;
    private CompleteListeService completeListeService;
    private UserInternalBddTest userInternalBddTest;
    private ListeInternalBddTest listeInternalBddTest;
    private MotDefInternalBddTest motDefInternalBddTest;
    private CompleteListeTest completeListeTest;

    public void setUp() throws TechnicalAppException, FonctionalAppException {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new SQLiteDatabaseManager(context);
        userInternalBddTest = new UserInternalBddTest();
        motDefInternalBddTest = new MotDefInternalBddTest();
        listeInternalBddTest = new ListeInternalBddTest();
        completeListeTest = new CompleteListeTest();
        completeListeService = new CompleteListeService(context);
        userService = new UserService(context);
        userService.addUserInternalBddActif(userInternalBddTest.createOneUser(1));
    }

    public void testGetCompleteListeById() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        CompleteListe completeListeExpected = new CompleteListe();
        completeListeExpected.setListeInternalBdd(listeInternalBddTest.createOneListeInternalBdd(1));
        List<MotDefInternalBdd> motDefInternalBddList = new ArrayList<MotDefInternalBdd>();
        int nbMotDef = 10;
        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(userService.getUserInternalBddActif().getId());
            motDefInternalBddList.add(motDefExpected);
        }
        completeListeExpected.setMotDefInternalBdds(motDefInternalBddList);

        Integer id = completeListeService.addCompleteListe(completeListeExpected);
        completeListeExpected.getListeInternalBdd().setId(id);

        CompleteListe completeListeResult = completeListeService.getCompleteListeByListeId(id);

        assertEquals(completeListeExpected.getMotDefInternalBdds().size(), completeListeResult.getMotDefInternalBdds().size());
        listeInternalBddTest.compareAttributesOfTwoListes(completeListeExpected.getListeInternalBdd(), completeListeResult.getListeInternalBdd());

        for (int i = 0; i != nbMotDef; i++) {
            completeListeTest.compareAttributesOfTwoMotDef( completeListeExpected.getMotDefInternalBdds().get(i),
                                                            completeListeResult.getMotDefInternalBdds().get(i));
        }

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }

    public void testUpdateCompleteListeById() throws TechnicalAppException, FonctionalAppException {
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());

        CompleteListe completeListeExpected = new CompleteListe();
        completeListeExpected.setListeInternalBdd(listeInternalBddTest.createOneListeInternalBdd(1));
        List<MotDefInternalBdd> motDefInternalBddList = new ArrayList<MotDefInternalBdd>();
        int nbMotDef = 10;
        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(userService.getUserInternalBddActif().getId());
            motDefInternalBddList.add(motDefExpected);
        }
        completeListeExpected.setMotDefInternalBdds(motDefInternalBddList);

        Integer id = completeListeService.addCompleteListe(completeListeExpected);
        completeListeExpected.getListeInternalBdd().setId(id);

        CompleteListe completeListeResult = completeListeService.getCompleteListeByListeId(id);
        assertEquals(completeListeExpected.getMotDefInternalBdds().size(), completeListeResult.getMotDefInternalBdds().size());
        listeInternalBddTest.compareAttributesOfTwoListes(completeListeExpected.getListeInternalBdd(), completeListeResult.getListeInternalBdd());

        for (int i = 0; i != nbMotDef; i++) {
            completeListeTest.compareAttributesOfTwoMotDef( completeListeExpected.getMotDefInternalBdds().get(i),
                                                            completeListeResult.getMotDefInternalBdds().get(i));
        }

        /*
        Update Phase
         */
        CompleteListe completeListeExpected2 = completeListeResult;

        for (int i = 0; i != nbMotDef; i++) {
            MotDefInternalBdd motDefExpected = motDefInternalBddTest.createOneMotDefInternalBdd(userService.getUserInternalBddActif().getId());
            completeListeExpected2.getMotDefInternalBdds().add(motDefExpected);
        }

        completeListeService.updateCompleteListe(completeListeExpected2);
        completeListeExpected2.getListeInternalBdd().setId(id);

        CompleteListe completeListeResult2 = completeListeService.getCompleteListeByListeId(id);
        assertEquals(completeListeExpected2.getMotDefInternalBdds().size(), completeListeResult2.getMotDefInternalBdds().size());
        listeInternalBddTest.compareAttributesOfTwoListes(completeListeExpected2.getListeInternalBdd(), completeListeResult2.getListeInternalBdd());

        for (int i = 0; i != nbMotDef; i++) {
            completeListeTest.compareAttributesOfTwoMotDef( completeListeExpected.getMotDefInternalBdds().get(i),
                                                            completeListeResult2.getMotDefInternalBdds().get(i));
        }

        db.getSqLiteTableListeDao().deleteAllListeInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableListeDao().getAllListeInternalBdd(db.getWritableDatabase()).size());
        db.getSqLiteTableMotDefDao().deleteAllMotDefInternalBdd(db.getWritableDatabase());
        assertEquals(0, db.getSqLiteTableMotDefDao().getAllMotDefInternalBdd(db.getWritableDatabase()).size());
    }
}
