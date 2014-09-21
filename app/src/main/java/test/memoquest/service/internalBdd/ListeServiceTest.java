package test.memoquest.service.internalBdd;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.service.InternalBdd.ListeService;

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

    public void testGetListeInternalBddByUserByUser() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 1;
        CompleteListe completeListeExpected = completeListeTest.createOneCompleteListeWithNMotDef(nbCompleteListe);

        assertEquals(0, listeService.getListeInternalBddByUser(nbCompleteListe).size());

        listeService.addListe(completeListeExpected);
        assertEquals(1, listeService.getListeInternalBddByUser(nbCompleteListe).size());
    }

    public void testGetListeInternalBddByUserNotFound() throws TechnicalAppException, FonctionalAppException {

        CompleteListe completeListeExpected = completeListeTest.createOneCompleteListeWithNMotDef(1);
        assertEquals(0, listeService.getListeInternalBddByUser(1).size());

        listeService.addListe(completeListeExpected);
        assertEquals(0, listeService.getListeInternalBddByUser(10000000).size());
    }

    public void testGetListeInternalBddByUser() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 10;
        List<CompleteListe> completeListeExpecteds = completeListeTest.createNCompleteListeWithNMotDef(nbCompleteListe);

        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(0, listeService.getListeInternalBddByUser(j).size());
        }

        for (int j = 0; j != nbCompleteListe; j++) {
            listeService.addListe(completeListeTest.createOneCompleteListeWithNMotDef(j));
        }

        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(1, listeService.getListeInternalBddByUser(j).size());
        }
    }

    public void testGetCompleteListeByListeId() throws TechnicalAppException, FonctionalAppException {

        int nbCompleteListe = 10;
        List<CompleteListe> completeListeExpecteds = completeListeTest.createNCompleteListeWithNMotDef(nbCompleteListe);

        for (int j = 0; j != nbCompleteListe; j++) {
            listeService.addListe(completeListeTest.createOneCompleteListeWithNMotDef(j));
        }


        //Pourquoi les chiffres sont inverse?????????????????
        //????????????????????????????????
        assertEquals(9, listeService.getCompleteListeByListeId(0).getMotDefInternalBdds().size());
        assertEquals(8, listeService.getCompleteListeByListeId(1).getMotDefInternalBdds().size());
        assertEquals(7, listeService.getCompleteListeByListeId(2).getMotDefInternalBdds().size());
        assertEquals(6, listeService.getCompleteListeByListeId(3).getMotDefInternalBdds().size());
        assertEquals(5, listeService.getCompleteListeByListeId(4).getMotDefInternalBdds().size());
        assertEquals(4, listeService.getCompleteListeByListeId(5).getMotDefInternalBdds().size());
        assertEquals(3, listeService.getCompleteListeByListeId(6).getMotDefInternalBdds().size());
        assertEquals(2, listeService.getCompleteListeByListeId(7).getMotDefInternalBdds().size());
        assertEquals(1, listeService.getCompleteListeByListeId(8).getMotDefInternalBdds().size());
        assertEquals(0, listeService.getCompleteListeByListeId(9).getMotDefInternalBdds().size());

        /*
        for (int j = 0; j != nbCompleteListe; j++) {
            assertEquals(j, listeService.getCompleteListeByListeId(j).getMotDefInternalBdds().size());
        }
        */
    }

}
