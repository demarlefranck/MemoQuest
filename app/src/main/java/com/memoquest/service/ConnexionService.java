package com.memoquest.service;

import com.memoquest.dao.ConnexionDao;

public class ConnexionService {

    private ConnexionDao connexionDao;

    public ConnexionService(){
        connexionDao = new ConnexionDao();
    }

    public Boolean isAuthentifiate(String loginTextStr, String passwordTextStr){
        return connexionDao.isAuthentifiate(loginTextStr, passwordTextStr);
    }

    public Boolean isConnected(){
        return connexionDao.isConnected();
    }
}
