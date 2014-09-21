package com.memoquest.service.InternalBdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;

import java.util.List;

public class UserService {


    private SQLiteDatabaseManager db;

    public UserService(Context context) {

        db = new SQLiteDatabaseManager(context);
    }

    public UserInternalBdd getUserInternalBddActive() throws TechnicalAppException, FonctionalAppException {

        return db.getUserInternalBddActive();
    }

    public Boolean isAuthentifiate() throws TechnicalAppException, FonctionalAppException {

        if(getUserInternalBddActive() == null){
            return false;
        }
        else{
            return true;
        }
    }

    public List<UserInternalBdd> getAllUserInternalBdd() throws TechnicalAppException, FonctionalAppException {
        return db.getAllUserInternalBdd();
    }

    public void updateAllUserInternalBddToNoActive() throws TechnicalAppException, FonctionalAppException {
        List<UserInternalBdd> userInternalBdds = getAllUserInternalBdd();

        for(UserInternalBdd userInternalBdd : userInternalBdds){
            userInternalBdd.setActive(false);
            updateUserInternalBdd(userInternalBdd);
        }
    }

    public void updateUserInternalBdd(UserInternalBdd userInternalBdd) {
        db.updateUserInternalBdd(userInternalBdd);
    }



    public void addUserInternalBddActive(UserInternalBdd userInternalBdd) throws TechnicalAppException, FonctionalAppException {

        updateAllUserInternalBddToNoActive();

        userInternalBdd.setActive(true);

        Boolean find = false;
        List<UserInternalBdd> userInternalBddList = getAllUserInternalBdd();
        for(UserInternalBdd user : userInternalBddList){
            if(user.getId() == userInternalBdd.getId()){
                find = true;
            }
        }

        if(find)
            updateUserInternalBdd(userInternalBdd);
        else
            db.addUserInternalBdd(userInternalBdd);
    }
}
