package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;

public class BddService {

    private ListeService listeService;

    public BddService(){

        listeService = new ListeService();
    }

    public void reloadAllTables(Context context){

        listeService.reloadBddListTable(context);
    }
}