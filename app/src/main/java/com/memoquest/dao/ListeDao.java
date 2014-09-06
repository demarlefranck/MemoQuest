package com.memoquest.dao;


import android.content.Context;
import android.util.Log;


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


        Log.i("DEBUG", "restPostListe");

        RestPostListeDao restPostListeDao = new RestPostListeDao();

        restPostListeDao.setUserId(userId);

        restPostListeDao.setListe(liste);

        restPostListeDao.execute();


        Log.i("DEBUG", "restPostListeDao.execute OK");
/*
        try {

            if(restPostListeDao.get()){

                Log.i("DEBUG", "Post Liste OK");
            }
            else{
                throw new TechnicalAppException("echec de la creation de la liste dans le serveur");
            }

        } catch (InterruptedException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        } catch (ExecutionException e) {
            throw new TechnicalAppException("ListeService.class, restPostListe(): " + e.toString());
        }
*/
        return true;
    }



    public List<Liste> restGetListes(Integer userId){


        Log.i("DEBUG", "restGetListes");

        RestGetListesDao restGetListesDao = new RestGetListesDao();
      //  ListOfListe listOfListe = null;

        List<Liste> listes = null;

        restGetListesDao.setUserId(userId);
        restGetListesDao.execute();

        try {

            listes = restGetListesDao.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*
        Voir pour Eliminer ListOfList
         */


     //   return listOfListe.getEntities();
    return listes;

    }

    public List<Liste> getListes(Context context) {


        Log.i("DEBUG", "getListes");

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        return internalBdd.getListes();

    }

    public void reloadBddListTable(Context context, Integer userId){

        Log.i("DEBUG", "reloadBddListTable");

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        internalBdd.deleteAllListe();

        Log.i("DEBUG","internalBdd.deleteAllListe OK");

        List<Liste> listes = restGetListes(userId);


        Log.i("DEBUG","restGetListes OK");

        internalBdd.addListes(listes);


        Log.i("DEBUG","internalBdd.addListes OK");
    }

}