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

    /*
    TABLE LISTE
     */
    public int addListeInternalBdd(ListeInternalBdd listeInternalBdd) {
        return sqLiteTableListeDao.addListe(this.getWritableDatabase(), listeInternalBdd);
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

    public List<ListeInternalBdd> getListeInternalBddByUser(int createUser) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddByUser(this.getWritableDatabase(), createUser);
    }

    public ListeInternalBdd getListeInternalBddById(int id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableListeDao.getListeInternalBddById(this.getWritableDatabase(), id);
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

    public void deleteMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.deleteMotDefInternalBdd(this.getWritableDatabase(), motDefInternalBdd);
    }

    public List<MotDefInternalBdd> getAllMotDefInternalBdd() throws TechnicalAppException {
        return sqLiteTableMotDefDao.getAllMotDefInternalBdd(this.getWritableDatabase());
    }

    public List<MotDefInternalBdd> getAllMotDefByListeInternalBddId(int liste_id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableMotDefDao.getAllMotDefByListeInternalBddId(this.getWritableDatabase(), liste_id);
    }

    public MotDefInternalBdd getMotDefInternalBddById(int id) throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableMotDefDao.getMotDefInternalBddById(this.getWritableDatabase(), id);
    }

    public void updateMotDefInternalBdd(MotDefInternalBdd motDefInternalBdd) {
        sqLiteTableMotDefDao.updateMotDefToInternalBdd(this.getWritableDatabase(), motDefInternalBdd);
    }


    /*
    TABLE USER
     */
    public UserInternalBdd getUserInternalBddActif() throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableUserDao.getUserInternalBddActif(this.getWritableDatabase());
    }

    public List<UserInternalBdd> getAllUserInternalBdd() throws TechnicalAppException, FonctionalAppException {
        return sqLiteTableUserDao.getAllUserInternalBdd(this.getWritableDatabase());
    }

    public Integer addUserInternalBdd(UserInternalBdd user) {
        return sqLiteTableUserDao.addUserInternalBdd(this.getWritableDatabase(), user);
    }

    public void updateUserInternalBdd(UserInternalBdd user) {
        sqLiteTableUserDao.updateUserInternalBdd(this.getWritableDatabase(), user);
    }

    public void deleteAllUserInternalBdd() {
        sqLiteTableUserDao.deleteAllUserInternalBdd(this.getWritableDatabase());
    }
}