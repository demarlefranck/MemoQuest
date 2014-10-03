package com.memoquest.dao;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.memoquest.dao.rest.get.RestGetConnectionDao;
import com.memoquest.exception.TechnicalAppException;

import java.util.concurrent.ExecutionException;

public class ConnexionDao {


    public Integer isAuthentifiateByServeur(String loginTextStr, String passwordTextStr){


        //REMPLACER PAR SERVICE REST QUI RETOURNE L'ID DU USER

        String password = "toto";
        String login10 = "demarl_f@etna-alternance.net";
        String login11 = "dupe_j@etna-alternance.net";
        String login12 = "chave_k@etna-alternance.net";
        String login13 = "fourni_c@etna-alternance.net";
        String login14 = "grosje_s@etna-alternance.net";
        String login15 = "devill_b@etna-alternance.net";

        if(loginTextStr.equals(login10) && passwordTextStr.equals(password)){

            return 10;

        } else if(loginTextStr.equals(login11) && passwordTextStr.equals(password)){

            return 11;

        } else if(loginTextStr.equals(login12) && passwordTextStr.equals(password)){

            return 12;

        } else if(loginTextStr.equals(login13) && passwordTextStr.equals(password)){

            return 13;
        } else if(loginTextStr.equals(login14) && passwordTextStr.equals(password)){

            return 14;

        } else if(loginTextStr.equals(login15) && passwordTextStr.equals(password)){

            return 15;
        }
        return null;
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
        }
        catch (ExecutionException e) {
            new TechnicalAppException("ConnexionDaoTest.class: checkServerConnection(): Probleme de connection: " + e.toString());
        }
        catch (InterruptedException e) {
            new TechnicalAppException("ConnexionDaoTest.class: checkServerConnection(): Probleme de connection: " + e.toString());
        }
        return false;
    }

    public Boolean isConnected(Context context) {
        return checkInternetConenction(context);
    }
}
