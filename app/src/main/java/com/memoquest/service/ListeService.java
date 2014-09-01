package com.memoquest.service;

import android.util.Log;
import com.memoquest.dao.rest.RestGetAllListeDao;
import com.memoquest.dao.rest.RestPostListeDao;
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

    public void restPostListe(Liste liste) {

        RestPostListeDao restPostListeDao = new RestPostListeDao();


        restPostListeDao.setUserId(userService.getId());

        restPostListeDao.setListe(liste);

        restPostListeDao.execute();
    }

}