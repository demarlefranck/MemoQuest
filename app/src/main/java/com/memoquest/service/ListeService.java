package com.memoquest.service;

import android.util.Log;
import com.memoquest.dao.rest.get.RestGetAllListeDao;
import com.memoquest.dao.rest.post.RestPostListeDao;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListOfListe;
import com.memoquest.model.Liste;

import java.util.concurrent.ExecutionException;

/**
 * Created by franck on 30/08/2014.
 */
public class ListeService {

    private UserService userService;

    public ListeService(){
        userService = new UserService();
    }

    public void addListe(Liste liste) {

        try {
            if (restPostListe(liste))

                Log.i("INFO", "CREATION LISTE OK IL FAUT RECHARGER SQLITE");

        } catch (TechnicalAppException e) {



            Log.i("INFO", "faire une FonctionalAppException pour l'afficher sur l'ecran utilisateur");

            /*
                faire une FonctionalAppException

                pour l'afficher sur l'ecran utilisateur
             */


            e.printStackTrace();
        }
    }


    public ListOfListe restGetAllListe() {

        RestGetAllListeDao restGetAllListeDao = new RestGetAllListeDao();
        ListOfListe listOfListe = null;
        restGetAllListeDao.setUserId(userService.getId());
        restGetAllListeDao.execute();

        try {
            listOfListe = restGetAllListeDao.get();

            Log.e("INFO", "CECI EST UNE TRACE:   ListeService.class / getAllListeServer()");
            Log.e("INFO", "listOfListe.getEntities().get(1).getNom():                " + listOfListe.getEntities().get(1).getNom());


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*
        Voir alimenter la base de donnees interne SQLite
         */

        return listOfListe;
    }

    public Boolean restPostListe(Liste liste) throws TechnicalAppException {

        RestPostListeDao restPostListeDao = new RestPostListeDao();

        restPostListeDao.setUserId(userService.getId());

        restPostListeDao.setListe(liste);

        restPostListeDao.execute();

        try {

            if(restPostListeDao.get()){
                return true;
            }
            else{
                throw new TechnicalAppException("echec de la creation de la liste dans le serveur");
            }

        } catch (InterruptedException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        } catch (ExecutionException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        }
    }

}