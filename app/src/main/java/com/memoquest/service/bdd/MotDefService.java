package com.memoquest.service.bdd;

import android.content.Context;

import com.memoquest.dao.bdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.List;

/**
 * Created by franck on 20/09/2014.
 */
public class MotDefService {

    SQLiteDatabaseManager db;
    private UserService userService;

    public MotDefService(Context context){
        db = new SQLiteDatabaseManager(context);
        userService = new UserService(context);
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

    public Integer addMotDefInternalBdd(MotDefInternalBdd motDef) throws TechnicalAppException, FonctionalAppException {




        motDef.setMustDeleted(false);




        motDef.setCreateUser(userService.getUserInternalBddActif().getId());
        motDef.setCreateTime(MyDateUtils.getDateTime());
        motDef.setUpdateUser(userService.getUserInternalBddActif().getId());
        motDef.setUpdateTime(MyDateUtils.getDateTime());


        return db.getSqLiteTableMotDefDao().addMotDefToInternalBdd(db.getWritableDatabase(), motDef);
    }

    public void updateMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        db.getSqLiteTableMotDefDao().updateMotDefToInternalBdd(db.getWritableDatabase(), motDefInternalBdd);
    }

    public void deleteMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        db.getSqLiteTableMotDefDao().deleteMotDefInternalBddById(db.getWritableDatabase(), motDefInternalBdd);
    }
}