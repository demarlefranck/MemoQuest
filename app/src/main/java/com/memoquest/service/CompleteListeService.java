package com.memoquest.service;

import android.content.Context;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.service.InternalBdd.ListeService;
import com.memoquest.service.InternalBdd.MotDefService;

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








    public CompleteListe getCompleteListeByListeId(int listeId) throws TechnicalAppException, FonctionalAppException {

        CompleteListe completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(listeService.getListeInternalBddById(listeId));
        List<MotDefInternalBdd> motDefList = new ArrayList<MotDefInternalBdd>();
        motDefList = motDefService.getAllMotDefServiceForListe(listeId);
        completeListe.setMotDefInternalBdds(motDefList);
        return completeListe;
    }


    public int addCompleteListe(CompleteListe completeList){

        int newListeId = listeService.addListeInternalBdd(completeList.getListeInternalBdd());

        List<MotDefInternalBdd> motDefInternalBdd = completeList.getMotDefInternalBdds();

        if(motDefInternalBdd != null)
            for(MotDefInternalBdd motDef : motDefInternalBdd){
                motDefService.addMotDefInternalBdd(motDef);
            }
        return newListeId;
    }
}
