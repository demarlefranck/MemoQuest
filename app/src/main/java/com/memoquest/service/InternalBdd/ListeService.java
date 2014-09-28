package com.memoquest.service.InternalBdd;

import android.content.Context;
import android.util.Log;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.CompleteListe;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.ArrayList;
import java.util.List;

public class ListeService {

    private SQLiteDatabaseManager db;
    private UserService userService;

    public ListeService(Context context){
        db = new SQLiteDatabaseManager(context);
        userService = new UserService(context);
    }

    public List<ListeInternalBdd> getListeInternalBddByUser(Integer createUser) throws FonctionalAppException {

        try {
            return db.getSqLiteTableListeDao().getListeInternalBddByUser(db.getWritableDatabase(), createUser);
        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getListeInternalBddByUser(): probleme" + e.toString());
        }
    }

    public ListeInternalBdd getListeInternalBddById(int listeId) throws FonctionalAppException {
        try {
            return db.getSqLiteTableListeDao().getListeInternalBddById(db.getWritableDatabase(), listeId);
        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getListeInternalBddById(): probleme" + e.toString());
        }
    }

    public int addListeInternalBdd(ListeInternalBdd listeInternalBdd) throws FonctionalAppException, TechnicalAppException {
        listeInternalBdd.setCreateUser(userService.getUserInternalBddActif().getId());
        listeInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        listeInternalBdd.setUpdateUser(userService.getUserInternalBddActif().getId());
        listeInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        return db.getSqLiteTableListeDao().addListeInternalBdd(db.getWritableDatabase(), listeInternalBdd);
    }

    public void updateListeInternalBdd(ListeInternalBdd listeInternalBdd) throws FonctionalAppException, TechnicalAppException {
        listeInternalBdd.setUpdateUser(userService.getUserInternalBddActif().getId());
        listeInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        db.getSqLiteTableListeDao().updateListeInternalBdd(db.getWritableDatabase(), listeInternalBdd);
    }
}