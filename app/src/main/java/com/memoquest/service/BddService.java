package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;

/**
 * Created by franck on 06/09/2014.
 */
public class BddService {

    private ListeService listeService;

    public BddService(){
        listeService = new ListeService();
    }

    public void reloadAllTables(Context context){
        listeService.reloadBddListTable(context);
    }
}
