package test.memoquest.service;

import android.test.InstrumentationTestCase;
import android.test.RenamingDelegatingContext;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.service.ListeSynchroService;

import test.memoquest.model.ListeInternalBddTest;

public class ListeSynchroServiceTest extends InstrumentationTestCase {

    private ListeSynchroService listeSynchroService;
    private ListeInternalBddTest listeInternalBddTest;

    public void setUp(){
        listeSynchroService = new ListeSynchroService();
        listeInternalBddTest = new ListeInternalBddTest();
    }

    public void testCompareListes() throws Exception {
        ListeInternalBdd liste1 = listeInternalBddTest.createOneListeInternalBdd(1);
        ListeInternalBdd liste2 = listeInternalBddTest.createOneListeInternalBdd(2);
        ListeInternalBdd listeResult =listeSynchroService.compareListes(liste1, liste2);
        assertNotSame(listeResult, liste1);
        assertEquals(listeResult, liste2);
    }
}