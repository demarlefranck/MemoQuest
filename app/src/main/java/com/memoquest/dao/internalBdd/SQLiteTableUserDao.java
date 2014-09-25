package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.UserInternalBdd;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class SQLiteTableUserDao {

    private static final String NAME_TABLE_USER = "TableUser";

    private static final String KEY_USER_ID_AI = "user_id_ai";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_PASSWORD = "user_password";
    private static final String KEY_USER_ACTIVE = "user_active";
    private static final String KEY_CREATE_USER = "create_user";
    private static final String KEY_CREATE_TIME = "create_time";
    private static final String KEY_UPDATE_USER = "update_user";
    private static final String KEY_UPDATE_TIME = "update_time";

    public SQLiteTableUserDao() {}

    public String getCreateTableUserRequest(){

        return "CREATE TABLE " +  NAME_TABLE_USER + " ( " +
                KEY_USER_ID_AI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_USER_ID + " INTEGER, " +
                KEY_USER_EMAIL + " TEXT UNIQUE, " +
                KEY_USER_PASSWORD + " TEXT, " +
                KEY_USER_ACTIVE + " INTEGER, " +
                KEY_CREATE_USER + " INTEGER, " +
                KEY_CREATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_UPDATE_USER + " INTEGER, " +
                KEY_UPDATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP )";
    }

    private ContentValues convertUserInternalBddToContentValues(UserInternalBdd user) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_PASSWORD, user.getPassword());
        values.put(KEY_USER_ACTIVE, user.getActive());
        values.put(KEY_CREATE_USER, user.getCreateUser());
        values.put(KEY_CREATE_TIME, user.getCreateTime());
        values.put(KEY_UPDATE_USER, user.getUpdateUser());
        values.put(KEY_UPDATE_TIME, user.getUpdateTime());
        return values;
    }


    public List<UserInternalBdd> mapBddResultToUserInternalBdd(SQLiteDatabase db, String query) throws TechnicalAppException {
        List<UserInternalBdd> listes = new LinkedList<UserInternalBdd>();
        UserInternalBdd userInternalBdd = null;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                userInternalBdd = new UserInternalBdd();
                userInternalBdd.setIdAi(cursor.getInt(0));
                userInternalBdd.setId(cursor.getInt(1));
                userInternalBdd.setEmail(cursor.getString(2));
                userInternalBdd.setPassword(cursor.getString(3));
                userInternalBdd.setActive(cursor.getInt(4) > 0);
                userInternalBdd.setCreateUser(Integer.parseInt(cursor.getString(5)));
                userInternalBdd.setCreateTime(cursor.getString(6));
                userInternalBdd.setUpdateUser(Integer.parseInt(cursor.getString(7)));
                userInternalBdd.setUpdateTime(cursor.getString(8));
                listes.add(userInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listes;
    }

    public void addUserInternalBdd(SQLiteDatabase db, UserInternalBdd user) {
        db.insert(NAME_TABLE_USER, null, convertUserInternalBddToContentValues(user));
        db.close();
    }


    public void updateUserInternalBdd(SQLiteDatabase db, UserInternalBdd userInternalBdd) {

        db.update(NAME_TABLE_USER,
                convertUserInternalBddToContentValues(userInternalBdd),
                KEY_USER_ID_AI + "=" + userInternalBdd.getIdAi(), null);
        db.close();
    }

    public UserInternalBdd getUserInternalBddActive(SQLiteDatabase db) throws FonctionalAppException, TechnicalAppException {

        List<UserInternalBdd> users = new LinkedList<UserInternalBdd>();
        String query = "SELECT  * FROM " + NAME_TABLE_USER
                    + " WHERE " + KEY_USER_ACTIVE + " = " + 1 + ";";

        List<UserInternalBdd>  userInternalBddList = mapBddResultToUserInternalBdd(db, query);

        if(userInternalBddList.size() > 1)
            throw new TechnicalAppException("Plus d'un user a été trouvé");
        else if(userInternalBddList.size()== 0)
            return null;
        else
            return userInternalBddList.get(0);
    }

    public List<UserInternalBdd> getAllUserInternalBdd(SQLiteDatabase db) throws TechnicalAppException {

        List<UserInternalBdd> users = new LinkedList<UserInternalBdd>();
        String query = "SELECT  * FROM " + NAME_TABLE_USER;
        return mapBddResultToUserInternalBdd(db, query);
    }

    public boolean deleteUserInternalBddByIdAi(SQLiteDatabase db, UserInternalBdd user) {
        return db.delete(NAME_TABLE_USER, KEY_USER_ID_AI + "=" + user.getIdAi(), null) > 0;
    }

    public void deleteAllUserInternalBdd(SQLiteDatabase db) {
        db.delete(NAME_TABLE_USER, null, null);
        db.close();
    }

    public String getNameTableUser() {
        return NAME_TABLE_USER;
    }

    public void putUserInternalBddToActive(SQLiteDatabase db, UserInternalBdd userInternalBdd) throws TechnicalAppException {
        updateUserInternalBdd(db, userInternalBdd);
    }
}