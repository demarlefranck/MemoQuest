package com.memoquest.service;

import android.content.Context;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.bdd.ListeService;
import com.memoquest.service.bdd.MotDefService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franck on 26/09/2014.
 */
public class CompleteListeService {

    private MotDefService motDefService;
    private ListeService listeService;

    public CompleteListeService(Context context){
        motDefService = new MotDefService(context);
        listeService = new ListeService(context);
    }

    public CompleteListe getCompleteListeByListeId(int listeId) throws FonctionalAppException {
        CompleteListe completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(listeService.getListeInternalBddById(listeId));
        List<MotDefInternalBdd> motDefList = new ArrayList<MotDefInternalBdd>();
        motDefList = motDefService.getAllMotDefServiceByListe(listeId);
        completeListe.setMotDefInternalBdds(motDefList);
        return completeListe;
    }


    public int addCompleteListe(CompleteListe completeList) throws FonctionalAppException {


        if(completeList.getListeInternalBdd().getId() != null){

            updateCompleteListe(completeList);

        } else{

            return addListeAndMotDef(completeList);
        }
        return 0;
    }

    public int addListeAndMotDef(CompleteListe completeList) throws FonctionalAppException {

        int newListeId = 0;

        completeList.getListeInternalBdd().setMustDeleted(false);
        List<MotDefInternalBdd> motDefInternalBdd = completeList.getMotDefInternalBdds();

        try {

            newListeId = listeService.addListeInternalBdd(completeList.getListeInternalBdd());

            if(motDefInternalBdd != null) {
                for (MotDefInternalBdd motDef : motDefInternalBdd) {
                    motDef.setMotDefListeInternalBddId(completeList.getListeInternalBdd().getId());
                    motDefService.addMotDefInternalBdd(motDef);
                }
            }

        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "addCompleteListe(): probleme" + e.toString());
        }

        return newListeId;
    }

    public void updateCompleteListe(CompleteListe completeList) throws FonctionalAppException {

        List<MotDefInternalBdd> motDefInternalBdd = completeList.getMotDefInternalBdds();

        try {

            listeService.updateListeInternalBdd(completeList.getListeInternalBdd());

            if (motDefInternalBdd != null) {
                for (MotDefInternalBdd motDef : motDefInternalBdd) {

                    if(motDef.getId() == null){

                        motDef.setMotDefListeInternalBddId(completeList.getListeInternalBdd().getId());
                        motDefService.addMotDefInternalBdd(motDef);

                    } else {

                        motDefService.updateMotDefInternalBdd(motDef);
                    }
                }
            }

        } catch (TechnicalAppException e) {
            throw new FonctionalAppException(this.getClass().getSimpleName() + "addCompleteListe(): probleme" + e.toString());
        }
    }
}
