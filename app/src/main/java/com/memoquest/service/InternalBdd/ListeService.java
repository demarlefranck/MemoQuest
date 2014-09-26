package com.memoquest.service.InternalBdd;

import android.content.Context;
import android.util.Log;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;

import java.util.ArrayList;
import java.util.List;

public class ListeService {

    private MotDefService motDefService;
    private SQLiteDatabaseManager db;

    public ListeService(Context context){
        motDefService = new MotDefService(context);
        db = new SQLiteDatabaseManager(context);
    }

    public List<ListeInternalBdd> getListeInternalBddByUser(int createUser) throws TechnicalAppException, FonctionalAppException {
       return db.getListeInternalBddWithUser(createUser);
    }

    public CompleteListe getCompleteListeByListeId(int listeId) throws TechnicalAppException, FonctionalAppException {

        CompleteListe completeListe = new CompleteListe();
        completeListe.setListeInternalBdd(db.getListeInternalBddWithIdAi(listeId));
        List<MotDefInternalBdd> motDefList = new ArrayList<MotDefInternalBdd>();
        motDefList = motDefService.getAllMotDefServiceForListe(listeId);
        completeListe.setMotDefInternalBdds(motDefList);
        return completeListe;
    }

    public int addListe(CompleteListe completeList){

        int newListeId = db.addListeInternalBdd(completeList.getListeInternalBdd());

        List<MotDefInternalBdd> motDefInternalBdd = completeList.getMotDefInternalBdds();

        if(motDefInternalBdd != null)
            for(MotDefInternalBdd motDef : motDefInternalBdd){
                motDefService.addMotDefInternalBdd(motDef);
            }
        return newListeId;
    }
}