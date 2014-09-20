package test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.ListeService;
import com.memoquest.service.MotDefService;

import java.util.List;

import test.memoquest.model.CompleteListeTest;
import test.memoquest.model.MotDefInternalBddTest;

/**
 * Created by franck on 20/09/2014.
 */
public class MotDefServiceTest extends AndroidTestCase {


    private MotDefService motDefService;
    private MotDefInternalBddTest motDefInternalBddTest;

    public void setUp(){
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        motDefService = new MotDefService(context);
        motDefInternalBddTest = new MotDefInternalBddTest();
    }

    public void testGetOneMotDefByListId() throws TechnicalAppException, FonctionalAppException {

        int listeId = 1;
        MotDefInternalBdd motDefInternalBdd = motDefInternalBddTest.createOneMotDefInternalBdd(listeId);

        assertEquals(0, motDefService.getAllMotDefServiceForListe(listeId).size());

        motDefService.addMotDefInternalBdd(motDefInternalBdd);
        assertEquals(1, motDefService.getAllMotDefServiceForListe(listeId).size());
    }

    public void testGetMotDefByListIdNotFound() throws TechnicalAppException, FonctionalAppException {
        int listeId = 1;
        MotDefInternalBdd motDefInternalBdd = motDefInternalBddTest.createOneMotDefInternalBdd(listeId);

        assertEquals(0, motDefService.getAllMotDefServiceForListe(listeId).size());

        motDefService.addMotDefInternalBdd(motDefInternalBdd);
        assertEquals(0, motDefService.getAllMotDefServiceForListe(10000000).size());
    }

    public void testGetAnyMotDefByListId() throws TechnicalAppException, FonctionalAppException {

        int nbMotDef = 10;

        for (int j = 0; j != nbMotDef; j++) {
            assertEquals(0, motDefService.getAllMotDefServiceForListe(j).size());
        }

        for (int j = 0; j != nbMotDef; j++) {
            MotDefInternalBdd motDefInternalBdd = motDefInternalBddTest.createOneMotDefInternalBdd(j);
            motDefService.addMotDefInternalBdd(motDefInternalBdd);
        }

        for (int j = 0; j != nbMotDef; j++) {
            assertEquals(1, motDefService.getAllMotDefServiceForListe(j).size());
        }
    }


}
