package com.memoquest.model;

import java.util.List;

/**
 * Created by fdemarle on 19/09/2014.
 */
public class CompleteListe {

    private ListeInternalBdd listeInternalBdd;
    private List<MotDefInternalBdd> motDefInternalBdds;

    public ListeInternalBdd getListeInternalBdd() {
        return listeInternalBdd;
    }

    public void setListeInternalBdd(ListeInternalBdd listeInternalBdd) {
        this.listeInternalBdd = listeInternalBdd;
    }

    public List<MotDefInternalBdd> getMotDefInternalBdds() {
        return motDefInternalBdds;
    }

    public void setMotDefInternalBdds(List<MotDefInternalBdd> motDefInternalBdds) {
        this.motDefInternalBdds = motDefInternalBdds;
    }

    @Override
    public String toString() {
        String result = "liste: \\n";

        if(listeInternalBdd != null){
            result = result + listeInternalBdd.toString();
        }

        result = result + "liste de motDef: \\n";

        if(motDefInternalBdds != null) {
            for (MotDefInternalBdd motDefInternalBdd : motDefInternalBdds) {
                result = result + "motDef: \\n" + motDefInternalBdd.toString();
            }
        }
        return result;
    }
}