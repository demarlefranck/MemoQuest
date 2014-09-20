package test.memoquest.model;

import com.memoquest.model.CompleteListe;
import com.memoquest.model.MotDefInternalBdd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franck on 20/09/2014.
 */
public class CompleteListeTest {

    private ListeInternalBddTest listeInternalBddTest;
    private MotDefInternalBddTest motDefInternalBddTest;

    public CompleteListeTest(){
        listeInternalBddTest = new ListeInternalBddTest();
        motDefInternalBddTest = new MotDefInternalBddTest();
    }

    public CompleteListe createOneCompleteListeWithOneMotDef(){
        CompleteListe completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(listeInternalBddTest.createOneListeInternalBdd(1));
        List<MotDefInternalBdd> motDefInternalBdds = new ArrayList<MotDefInternalBdd>();
        motDefInternalBdds.add(motDefInternalBddTest.createOneMotDefInternalBdd(1));
        completeListe.setMotDefInternalBdds(motDefInternalBdds);
        return completeListe;
    }

    public CompleteListe createOneCompleteListeWithNMotDef(int i){
        CompleteListe completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(listeInternalBddTest.createOneListeInternalBdd(i));
        completeListe.setMotDefInternalBdds(motDefInternalBddTest.createNMotDefInternalBdd(i));
        return completeListe;
    }

    public List<CompleteListe> createNCompleteListeWithNMotDef(int i){
        List<CompleteListe> completeListes = new ArrayList<CompleteListe>();
        for(int j = 0; j != i; j++){
            completeListes.add(createOneCompleteListeWithNMotDef(j));
        }
        return completeListes;
    }
}
