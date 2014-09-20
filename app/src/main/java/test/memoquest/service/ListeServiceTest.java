package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.service.ListeService;

import java.util.List;

import test.memoquest.model.CompleteListeTest;

/**
 * Created by franck on 20/09/2014.
 */
public class ListeServiceTest extends AndroidTestCase {


    private ListeService listeService;
    private CompleteListeTest completeListeTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        listeService = new ListeService(context);
        completeListeTest = new CompleteListeTest();
    }

    public void testGetListeByUser() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 1;
        CompleteListe completeListeExpected = completeListeTest.createOneCompleteListeWithNMotDef(nbCompleteListe);

        assertEquals(0, listeService.getListeByUser(nbCompleteListe).size());

        listeService.addListe(completeListeExpected);
        assertEquals(1, listeService.getListeByUser(nbCompleteListe).size());
    }

    public void testGetListeByUserNotFound() throws TechnicalAppException, FonctionalAppException {

        CompleteListe completeListeExpected = completeListeTest.createOneCompleteListeWithNMotDef(1);
        assertEquals(0, listeService.getListeByUser(1).size());

        listeService.addListe(completeListeExpected);
        assertEquals(0, listeService.getListeByUser(10000000).size());
    }

    public void testGetNListeByUser() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 10;
        List<CompleteListe> completeListeExpecteds = completeListeTest.createNCompleteListeWithNMotDef(nbCompleteListe);

        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(0, listeService.getListeByUser(j).size());
        }

        for (int j = 0; j != nbCompleteListe; j++) {
            listeService.addListe(completeListeTest.createOneCompleteListeWithNMotDef(j));
        }

        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(1, listeService.getListeByUser(j).size());
        }
    }


}
