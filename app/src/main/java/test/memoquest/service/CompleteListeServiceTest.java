package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.UserService;

import java.util.List;

import test.memoquest.model.CompleteListeTest;
import test.memoquest.model.ListeInternalBddTest;
import test.memoquest.model.UserTest;

/**
 * Created by franck on 20/09/2014.
 */
public class CompleteListeServiceTest extends AndroidTestCase {


    private ListeService listeService;
    private CompleteListeTest completeListeTest;
    private ListeInternalBddTest listeInternalBddTest;

    public void setUp() throws TechnicalAppException, FonctionalAppException {
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        listeService = new ListeService(context);
        completeListeTest = new CompleteListeTest();
        listeInternalBddTest = new ListeInternalBddTest();
        UserService userService = new UserService(context);
        UserTest userTest = new UserTest();
        userService.addUserInternalBddActive(userTest.createOneUser(1));
    }


    public void testGetListeInternalBddByUserNotFound() throws TechnicalAppException, FonctionalAppException {

        CompleteListe completeListeExpected = completeListeTest.createOneCompleteListeWithNMotDef(1);
        assertEquals(0, listeService.getListeInternalBddByUser(1).size());

        listeService.addListeInternalBdd(completeListeExpected.getListeInternalBdd());
        assertEquals(0, listeService.getListeInternalBddByUser(10000000).size());
    }

    public void testGetListeInternalBddByUser() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 10;
        List<CompleteListe> completeListeExpecteds = completeListeTest.createNCompleteListeWithNMotDef(nbCompleteListe);

        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(0, listeService.getListeInternalBddByUser(j).size());
        }

        for (int j = 0; j != nbCompleteListe; j++) {
            listeService.addListeInternalBdd(completeListeTest.createOneCompleteListeWithNMotDef(j).getListeInternalBdd());
        }

        assertEquals(1, listeService.getListeInternalBddByUser(0).size());
        assertEquals(1, listeService.getListeInternalBddByUser(1).size());
        assertEquals(1, listeService.getListeInternalBddByUser(2).size());
        assertEquals(1, listeService.getListeInternalBddByUser(3).size());
        assertEquals(1, listeService.getListeInternalBddByUser(4).size());
        assertEquals(1, listeService.getListeInternalBddByUser(5).size());
        assertEquals(1, listeService.getListeInternalBddByUser(6).size());
        assertEquals(1, listeService.getListeInternalBddByUser(7).size());
        assertEquals(1, listeService.getListeInternalBddByUser(8).size());
        assertEquals(1, listeService.getListeInternalBddByUser(9).size());

    }

    public void testGetCompleteListeBddByListeId() throws TechnicalAppException, FonctionalAppException {

        ListeInternalBdd listeInternalBddExpected = listeInternalBddTest.createOneListeInternalBdd(1);

        int id = listeService.addListeInternalBdd(listeInternalBddExpected);

        ListeInternalBdd listeInternalBddResult = listeService.getListeInternalBddById(id);

        assertEquals(listeInternalBddExpected, listeInternalBddResult);
    }
}
