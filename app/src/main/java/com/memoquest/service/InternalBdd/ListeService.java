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

    public List<ListeInternalBdd> getListeInternalBddByUser(int createUser) throws FonctionalAppException {

        try {

            return db.getListeInternalBddByUser(createUser);

        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getListeInternalBddByUser(): probleme" + e.toString());
        }
    }

    public int addListeInternalBdd(ListeInternalBdd listeInternalBdd) throws FonctionalAppException {

        listeInternalBdd.setCreateUser(userService.getCurrentUserId());
        listeInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        listeInternalBdd.setUpdateUser(userService.getCurrentUserId());
        listeInternalBdd.setUpdateTime(MyDateUtils.getDateTime());

        return db.addListeInternalBdd(listeInternalBdd);
    }


    public ListeInternalBdd getListeInternalBddById(int listeId) throws FonctionalAppException {
        try {

            return db.getListeInternalBddById(listeId);

        } catch (TechnicalAppException e) {
            throw  new FonctionalAppException(this.getClass().getSimpleName() + "getListeInternalBddById(): probleme" + e.toString());
        }
    }
}