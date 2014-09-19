package com.memoquest.dao.internalBdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;
import com.memoquest.model.MotDefInternalBdd;
import com.memoquest.model.User;

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

    /*
    TABLE LISTE
     */
    public void addListeInternalBdd(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.addListe(this.getWritableDatabase(), listeInternalBdd);
    }

    public void addListListeInternalBdd(List<ListeInternalBdd> listes) {
        sqLiteTableListeDao.addListes(this.getWritableDatabase(), listes);
    }

    public void deleteAllListeInternalBdd() {
        sqLiteTableListeDao.deleteAllListe(this.getWritableDatabase());
    }

    public void deleteListeInternalBddWithIdAi(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.deleteListeInternalBddWithIdAi(this.getWritableDatabase(), listeInternalBdd);
    }

    public void deleteListeInternalBddWithId(ListeInternalBdd listeInternalBdd) {
        sqLiteTableListeDao.deleteListeInternalBddWithId(this.getWritableDatabase(), listeInternalBdd);
    }

    public List<ListeInternalBdd> getAllListeInternalBdd() throws TechnicalAppException {
        return sqLiteTableListeDao.getAllListeInternalBdd(this.getWritableDatabase());
    }

    public ListeInternalBdd getListeInternalBddWithIdAi(int idAi) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddWithIdAi(this.getWritableDatabase(), idAi);
    }

    public ListeInternalBdd getListeInternalBddWithId(int id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddWithId(this.getWritableDatabase(), id);
    }
    public ListeInternalBdd getListeInternalBddWithName(String name) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddWithName(this.getWritableDatabase(), name);
    }

    public void updateListeInternalBdd(ListeInternalBdd listeModify) {
        sqLiteTableListeDao.updateListeInternalBdd(this.getWritableDatabase(), listeModify);
    }



    /*
    TABLE MOT DEFINITION
     */
    public void addMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.addMotDefToInternalBdd(this.getWritableDatabase(), motDefInternalBdd);
    }

    public void addListMotDefInternalBdd(List<MotDefInternalBdd> motDefInternalBdds) {
        sqLiteTableMotDefDao.addAllMotDef(this.getWritableDatabase(), motDefInternalBdds);
    }

    public void deleteAllMotDefInternalBdd() {
        sqLiteTableMotDefDao.deleteAllMotDef(this.getWritableDatabase());
    }

    public void deleteMotDefInternalBddWithIdAi(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.deleteMotDefInternalBddWithIdAi(this.getWritableDatabase(), motDefInternalBdd);
    }

    public void deleteMotDefInternalBddWithId(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.deleteMotDefInternalBddWithId(this.getWritableDatabase(), motDefInternalBdd);
    }

    public List<MotDefInternalBdd> getAllMotDefInternalBdd() throws TechnicalAppException {
        return sqLiteTableMotDefDao.getAllMotDefInternalBdd(this.getWritableDatabase());
    }

    public MotDefInternalBdd getMotDefInternalBddWithIdAi(int idAi) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableMotDefDao.getMotDefInternalBddWithIdAi(this.getWritableDatabase(), idAi);
    }

    public MotDefInternalBdd getMotDefInternalBddWithId(int id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableMotDefDao.getMotDefInternalBddWithId(this.getWritableDatabase(), id);
    }
    public MotDefInternalBdd getMotDefInternalBddWithMot(String mot) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableMotDefDao.getMotDefInternalBddWithMot(this.getWritableDatabase(), mot);
    }

    public void updateMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.updateMotDefToInternalBdd(this.getWritableDatabase(), motDefInternalBdd);
    }


    /*
    TABLE USER
     */
    public void addUser(User user) {
        sqLiteTableUserDao.addUser(this.getWritableDatabase(), user);
    }

    public User getUser() throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableUserDao.getUser(this.getWritableDatabase());
    }
/*
    public void getAllUser() {
        sqLiteTableUserDao.getAllUser(this.getWritableDatabase());
    }

   public void deleteUserWithIdAi(User user) {
        sqLiteTableUserDao.deleteUserWithIdAi(this.getWritableDatabase(), user);
    }

    public void deleteAllUsers() {
        sqLiteTableUserDao.deleteAllUsers(this.getWritableDatabase());
    }
*/
}