package com.memoquest.dao;

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
}
