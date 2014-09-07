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

        return true;
    }

    public List<Liste> restGetListes(Integer userId){

        RestGetListesDao restGetListesDao = new RestGetListesDao();

        List<Liste> listes = null;

        restGetListesDao.setUserId(userId);
        restGetListesDao.execute();

        try {

            listes = restGetListesDao.get();

        } catch (InterruptedException e) {
            new TechnicalAppException("ListeDao.class: restGetListes(): Probleme lors de la recuperation des listes: " + e.toString());
        } catch (ExecutionException e) {
            new TechnicalAppException("ListeDao.class: restGetListes(): Probleme lors de la recuperation des listes: " + e.toString());
        }
    return listes;
    }

    public List<Liste> getListes(Context context) {

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        return internalBdd.getListes();
    }

    public void reloadBddListTable(Context context, Integer userId){

        SQLiteDatabaseManager internalBdd = new SQLiteDatabaseManager(context);
        internalBdd.deleteAllListe();

        Log.i("DEBUG","internalBdd.deleteAllListe OK");

        List<Liste> listes = restGetListes(userId);

        Log.i("DEBUG","restGetListes OK");

        internalBdd.addListes(listes);

        Log.i("DEBUG","internalBdd.addListes OK");
    }

}