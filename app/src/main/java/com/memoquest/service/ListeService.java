package com.memoquest.service;

import android.content.Context;
import android.util.Log;

import com.memoquest.dao.ListeDao;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.Liste;

import java.util.List;

public class ListeService {

    private UserService userService;
    private ConnexionService connexionService;
    private ListeDao listeDao;

    public ListeService(){
        userService = new UserService();
        listeDao = new ListeDao();
        connexionService = new ConnexionService();
    }

    public Boolean addListe(Liste liste, Context context) throws FonctionalAppException {

        if(connexionService.isConnected(context)){
            Integer userId = userService.getId();

            try {
                if(listeDao.restPostListe(liste, userId)){

                    Log.i("DEBUG","Liste ajoute");
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

/*
    public List<Liste> getListes(Context context) {

        return listeDao.getListes(context);
    }

    public void reloadBddListTable(Context context) {

        Integer userId = userService.getId();
        listeDao.reloadBddListTable(context, userId);
    }
    */
}