package com.memoquest.dao;

import android.util.Log;

import com.memoquest.dao.rest.get.RestGetConnectionDao;

import java.util.concurrent.ExecutionException;

public class ConnexionDao {

    public Boolean isAuthentifiate(String loginTextStr, String passwordTextStr){


        //REMPLACER PAR SERVICE SOAP


        if(loginTextStr.equals("toto") && passwordTextStr.equals("toto")){
            return true;
        }
        else{
            return false;
        }

    }

    public Boolean isConnected() {

        RestGetConnectionDao restGetConnectionDao = new RestGetConnectionDao();
        restGetConnectionDao.execute();
        try {
            if(restGetConnectionDao.get()) {
                Log.e("INFO", "Connection OK");
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
