package com.memoquest.service.InternalBdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.dao.internalBdd.SQLiteDatabaseManager;
import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.UserInternalBdd;
import com.memoquest.utils.MyDateUtils;

import java.util.List;

public class UserService {


    private SQLiteDatabaseManager db;

    public UserService(Context context) {

        db = new SQLiteDatabaseManager(context);
    }

    public UserInternalBdd getUserInternalBddActif() throws TechnicalAppException, FonctionalAppException {

        return db.getSqLiteTableUserDao().getUserInternalBddActif(db.getWritableDatabase());
    }

    public Boolean isAuthentifiate() throws TechnicalAppException, FonctionalAppException {

        if(getUserInternalBddActif() == null){
            return false;
        }
        else{
            return true;
        }
    }

    public List<UserInternalBdd> getAllUserInternalBdd() throws TechnicalAppException, FonctionalAppException {

        return db.getSqLiteTableUserDao().getAllUserInternalBdd(db.getWritableDatabase());
    }

    public void updateAllUserInternalBddToNoActif() throws TechnicalAppException, FonctionalAppException {
        List<UserInternalBdd> userInternalBdds = getAllUserInternalBdd();

        for(UserInternalBdd userInternalBdd : userInternalBdds){
            userInternalBdd.setActif(false);
            updateUserInternalBdd(userInternalBdd);
        }
    }

    public void updateUserInternalBdd(UserInternalBdd userInternalBdd) throws FonctionalAppException {
        userInternalBdd.setUpdateUser(-1);
        userInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        db.getSqLiteTableUserDao().updateUserInternalBdd(db.getWritableDatabase(), userInternalBdd);
    }

    public Integer addUserInternalBddActif(UserInternalBdd userInternalBdd) throws TechnicalAppException, FonctionalAppException {

        updateAllUserInternalBddToNoActif();

        userInternalBdd.setCreateUser(-1);
        userInternalBdd.setCreateTime(MyDateUtils.getDateTime());
        userInternalBdd.setUpdateUser(-1);
        userInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
        userInternalBdd.setActif(true);

        return db.getSqLiteTableUserDao().addUserInternalBdd(db.getWritableDatabase(), userInternalBdd);
    }
}
