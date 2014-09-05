package com.memoquest.dao;


import android.content.Context;


import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.dao.rest.get.RestGetListesDao;
import com.memoquest.dao.rest.post.RestPostListeDao;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListOfListe;
import com.memoquest.model.Liste;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListeDao {

    public Boolean restPostListe(Liste liste, Integer userId) throws TechnicalAppException {

        RestPostListeDao restPostListeDao = new RestPostListeDao();

        restPostListeDao.setUserId(userId);

        restPostListeDao.setListe(liste);

        restPostListeDao.execute();

        try {

            if(restPostListeDao.get()){
            }
            else{
                throw new TechnicalAppException("echec de la creation de la liste dans le serveur");
            }

        } catch (InterruptedException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        } catch (ExecutionException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        }

        return true;
    }



    public List<Liste> restGetListes(Integer userId){

        RestGetListesDao restGetListesDao = new RestGetListesDao();
        ListOfListe listOfListe = null;
        restGetListesDao.setUserId(userId);
        restGetListesDao.execute();

        try {

            listOfListe = restGetListesDao.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*
        Voir pour Eliminer ListOfList
         */


        return listOfListe.getEntities();
    }

    public List<Liste> getListes(Context context) {

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        return internalBdd.getListes();

    }

    public void reloadBddListTable(Context context, Integer userId){

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        internalBdd.deleteAllListe();

        List<Liste> listes = restGetListes(userId);

        internalBdd.addListOfListe(listes);
    }

}