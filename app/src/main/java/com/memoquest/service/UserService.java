package com.memoquest.service;

/**
 * Created by franck on 31/08/2014.
 */
public class UserService {

    private int id;

    public int getId() {

        /*
        ALLER CHERCHER L ID DANS LA BDD INTERNE

         OU SU LE SERVEUR SI IL N'Y A PAS
         */

        this.id = 4;

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
