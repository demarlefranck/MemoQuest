package com.memoquest.service;

import android.content.Context;

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
        completeListe.setListeInternalBdd(db.getListeInternalBddWithId(listeId));
        completeListe.setMotDefInternalBdds(motDefService.getAllMotDefServiceForListe(listeId));
        return completeListe;
    }

    public void addListe(CompleteListe completeList){

        db.addListeInternalBdd(completeList.getListeInternalBdd());

        for(MotDefInternalBdd motDef : completeList.getMotDefInternalBdds()){
            motDefService.addMotDefInternalBdd(motDef);
        }
    }

/*
        public Boolean addListeOLD(ListeRest liste, Context context) throws FonctionalAppException {

        if(connexionService.isConnected(context)){
            Integer userId = userService.getId();

            try {
                if(listeDao.restPostListe(liste, userId)){

                    Log.i("DEBUG","ListeRest ajoute");
                    //reloadBddListTable(context);


                    Log.i("DEBUG","BDD recharger");
                    return true;
                }
            } catch (TechnicalAppException e) {
                throw new FonctionalAppException("Un probleme est surevenu lors de la creation de la liste");
            }
        }
        else{
            throw new FonctionalAppException("Une connexion internet est requise");
        }

        return false;
    }
*/

/*
    public List<ListeRest> getListes(Context context) {

        return listeDao.getListes(context);
    }

    public void reloadBddListTable(Context context) {

        Integer userId = userService.getId();
        listeDao.reloadBddListTable(context, userId);
    }
    */
}