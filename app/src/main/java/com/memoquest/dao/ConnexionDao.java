package com.memoquest.dao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.memoquest.dao.rest.get.RestGetConnectionDao;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.service.InternalBdd.UserService;

import java.util.concurrent.ExecutionException;

public class ConnexionDao {


    public Integer isAuthentifiateByServeur(String loginTextStr, String passwordTextStr){


        //REMPLACER PAR SERVICE REST QUI RETOURNE L'ID DU USER

        if(loginTextStr.equals("demarl_f@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 10;
        }
        else if(loginTextStr.equals("dupe_j@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 11;
        }
        else if(loginTextStr.equals("chave_k@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 12;
        }
        else if(loginTextStr.equals("fourni_c@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 13;
        }
        else if(loginTextStr.equals("grosje_s@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 14;
        }
        else if(loginTextStr.equals("devill_b@etna-alternance.net") && passwordTextStr.equals("toto")){
            return 15;
        }
        else{
            return null;
        }
    }

    public Boolean checkInternetConenction(Context context){

        ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (check != null) {
            NetworkInfo[] info = check.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkServerConnection() {
        RestGetConnectionDao restGetConnectionDao = new RestGetConnectionDao();
        restGetConnectionDao.execute();
        try {
            if(restGetConnectionDao.get()) {
                Log.e("INFO", "ServerConenction OK");
                return true;
            }
        } catch (InterruptedException e) {
            new TechnicalAppException("ConnexionDaoTest.class: checkServerConnection(): Probleme de connection: " + e.toString());
        } catch (ExecutionException e) {
            new TechnicalAppException("ConnexionDaoTest.class: checkServerConnection(): Probleme de connection: " + e.toString());
        }
        return false;
    }

    public Boolean isConnected(Context context) {

        return checkInternetConenction(context);
    }
}
