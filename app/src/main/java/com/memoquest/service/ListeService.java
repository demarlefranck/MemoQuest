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

    private UserService userService;
    private ConnexionService connexionService;
    private SQLiteDatabaseManager db;

    public ListeService(Context context){
        userService = new UserService();
        db = new SQLiteDatabaseManager(context);
        connexionService = new ConnexionService();
    }

    public List<ListeInternalBdd> getListeByUser(int createUser) throws TechnicalAppException, FonctionalAppException {
       return db.getListeInternalBddWithUser(createUser);
    }

    public void addListe(CompleteListe completeList){

        db.addListeInternalBdd(completeList.getListeInternalBdd());

        for(MotDefInternalBdd motDef : completeList.getMotDefInternalBdds()){
            db.addMotDefInternalBdd(motDef);
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