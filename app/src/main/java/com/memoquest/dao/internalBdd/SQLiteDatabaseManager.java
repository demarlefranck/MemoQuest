package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.Liste;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.User;

import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MemoQuestBdd";

    private SQLiteTableListeDao sqLiteTableListeDao;
    private SQLiteTableUserDao sqLiteTableUserDao;


    public SQLiteDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteTableListeDao = new SQLiteTableListeDao();
        sqLiteTableUserDao = new SQLiteTableUserDao();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String allRequest = sqLiteTableListeDao.getCreateTableListeRequest() + "; " +
                            sqLiteTableUserDao.getCreateTableListeRequest() + "; "
                            ;
        db.execSQL(allRequest);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String allRequest = "DROP TABLE IF EXISTS " + sqLiteTableListeDao.getNameTableListe() + "; "
                          + "DROP TABLE IF EXISTS " + sqLiteTableUserDao.getNameTableListe() + "; "
                            ;

        db.execSQL(allRequest);
        this.onCreate(db);
    }

    public void addListeInternalBdd(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.addListe(this.getWritableDatabase(), listeInternalBdd);
    }

    public void addListesInternalBdd(List<ListeInternalBdd> listes) {
        sqLiteTableListeDao.addListes(this.getWritableDatabase(), listes);
    }

    public void deleteAllListeInternalBdd() {
        sqLiteTableListeDao.deleteAllListe(this.getWritableDatabase());
    }

    public void deleteListeInternalBddWithIdTemp(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.deleteListeInternalBddWithIdTemp(this.getWritableDatabase(), listeInternalBdd);
    }

    public void deleteListeInternalBddWithId(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.deleteListeInternalBddWithId(this.getWritableDatabase(), listeInternalBdd);
    }

    public List<ListeInternalBdd> getAllListeInternalBdd() {
        return sqLiteTableListeDao.getAllListeInternalBdd(this.getWritableDatabase());
    }

    public ListeInternalBdd getListeInternalBddWithId(int id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddWithId(this.getWritableDatabase(), id);
    }
    public ListeInternalBdd getListeInternalBddWithName(String name) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddWithName(this.getWritableDatabase(), name);
    }

    public void addUser(User user) {
        sqLiteTableUserDao.addUser(this.getWritableDatabase(), user);
    }

    public void getAllUser() {
        sqLiteTableUserDao.getAllUser(this.getWritableDatabase());
    }

    public void deleteUserWithId(User user) {
        sqLiteTableUserDao.deleteUserWithId(this.getWritableDatabase(), user);
    }

    public void deleteAllUsers() {
        sqLiteTableUserDao.deleteAllUsers(this.getWritableDatabase());
    }

}