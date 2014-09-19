package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.User;

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

    public SQLiteTableUserDao() {}

    public String getCreateTableUserRequest(){

        return "CREATE TABLE " +  NAME_TABLE_USER + " ( " +
                KEY_USER_ID_AI + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_USER_ID + " INTEGER, " +
                KEY_USER_EMAIL + " TEXT UNIQUE, " +
                KEY_USER_PASSWORD + " TEXT UNIQUE )";
    }

    public void addUser(SQLiteDatabase db, User user) {
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_PASSWORD, user.getPassword());
        db.insert(NAME_TABLE_USER, null, values);
        db.close();
    }

    public User getUser(SQLiteDatabase db) throws FonctionalAppException, TechnicalAppException {

        List<User> users = new LinkedList<User>();
        String query = "SELECT  * FROM " + NAME_TABLE_USER;

        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst())
        {
            do {
                user = new User();
                user.setId(cursor.getInt(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if(users.size() > 1)
            throw new TechnicalAppException("Plus d'un user à été trouvée");
        else if(users.size()== 0)
            throw new FonctionalAppException("Aucun user n'a été trouvé");
        else
            return users.get(0);
    }

    public List<User> getAllUser(SQLiteDatabase db) {

        List<User> users = new LinkedList<User>();
        String query = "SELECT  * FROM " + NAME_TABLE_USER;

        Cursor cursor = db.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst())
        {
            do {
                user = new User();
                user.setId(cursor.getInt(1));
                user.setEmail(cursor.getString(2));
                user.setPassword(cursor.getString(3));

                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return users;
    }

    public boolean deleteUserWithIdAi(SQLiteDatabase db, User user) {
        return db.delete(NAME_TABLE_USER, KEY_USER_ID_AI + "=" + user.getIdAi(), null) > 0;
    }

    public void deleteAllUsers(SQLiteDatabase db) {
        db.delete(NAME_TABLE_USER, null, null);
        db.close();
    }

    public String getNameTableUser() {
        return NAME_TABLE_USER;
    }
}