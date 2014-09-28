package com.memoquest.service.InternalBdd;

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

    public List<MotDefInternalBdd> getAllMotDefServiceByListe(Integer liste_id) throws FonctionalAppException {
        try {
            return db.getSqLiteTableMotDefDao().getAllMotDefInternalBddByListeId(db.getWritableDatabase(), liste_id);
        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getAllMotDefServiceForListe(): probleme" + e.toString());
        }
    }

    public MotDefInternalBdd getMotDefInternalBddById(Integer motDefId) throws FonctionalAppException {
        try {
            return db.getSqLiteTableMotDefDao().getMotDefInternalBddById(db.getWritableDatabase(), motDefId);
        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getMotDefInternalBddById(): probleme" + e.toString());
        }
    }

    public Integer addMotDefInternalBdd(MotDefInternalBdd motDef) {
        return db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDef);
    }

    public void updateMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        db.getSqLiteTableMotDefDao().updateMotDefToInternalBdd(db.getWritableDatabase(), motDefInternalBdd);
    }

    public void deleteMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        db.getSqLiteTableMotDefDao().deleteMotDefInternalBddById(db.getWritableDatabase(), motDefInternalBdd);
    }
}