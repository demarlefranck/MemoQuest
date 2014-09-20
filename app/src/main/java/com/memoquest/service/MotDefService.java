package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.dao.internalBdd.SQLiteTableMotDefDao;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;

import java.util.List;

/**
 * Created by franck on 20/09/2014.
 */
public class MotDefService {

    SQLiteDatabaseManager db;

    public MotDefService(Context context){
        db = new SQLiteDatabaseManager(context);
    }

    public List<MotDefInternalBdd> getAllMotDefServiceForListe(Integer liste_id) throws FonctionalAppException {
        try {
            return db.getAllMotDefForListe(liste_id);
        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getAllMotDefServiceForListe(): probleme" + e.toString());
        }
    }
}