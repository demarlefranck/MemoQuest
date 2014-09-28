package com.memoquest.dao.internalBdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.UserInternalBdd;

import java.util.List;

public class SQLiteDatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MemoQuestBdd";

    private SQLiteTableListeDao sqLiteTableListeDao;
    private SQLiteTableMotDefDao sqLiteTableMotDefDao;
    private SQLiteTableUserDao sqLiteTableUserDao;

    public SQLiteDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteTableListeDao = new SQLiteTableListeDao();
        sqLiteTableMotDefDao = new SQLiteTableMotDefDao();
        sqLiteTableUserDao = new SQLiteTableUserDao();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqLiteTableListeDao.getCreateTableListeRequest());
        db.execSQL(sqLiteTableMotDefDao.getCreateTableMotDefRequest());
        db.execSQL(sqLiteTableUserDao.getCreateTableUserRequest());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + sqLiteTableListeDao.getNameTableListe());
        db.execSQL("DROP TABLE IF EXISTS " + sqLiteTableMotDefDao.getNameTableMotDef());
        db.execSQL("DROP TABLE IF EXISTS " + sqLiteTableUserDao.getNameTableUser());
        this.onCreate(db);
    }

    public SQLiteTableListeDao getSqLiteTableListeDao() {
        return sqLiteTableListeDao;
    }

    public SQLiteTableMotDefDao getSqLiteTableMotDefDao() {
        return sqLiteTableMotDefDao;
    }

    public SQLiteTableUserDao getSqLiteTableUserDao() {
        return sqLiteTableUserDao;
    }
}