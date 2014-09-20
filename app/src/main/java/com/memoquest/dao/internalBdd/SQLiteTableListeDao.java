package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.ListeInternalBdd;

import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class SQLiteTableListeDao {

    private static final String NAME_TABLE_LISTE = "TableListe";

    private static final String KEY_LISTE_ID_AI = "liste_id_ai";
    private static final String KEY_LISTE_ID = "liste_id";
    private static final String KEY_LISTE_NOM = "liste_nom";
    private static final String KEY_LISTE_THEME = "liste_theme";
    private static final String KEY_LISTE_CATHEGORY = "liste_category";
    private static final String KEY_LISTE_SHARED = "liste_shared";
    private static final String KEY_LISTE_MUST_DELETED = "liste_must_deleted";
    private static final String KEY_CREATE_USER = "create_user";
    private static final String KEY_CREATE_TIME = "create_time";
    private static final String KEY_UPDATE_USER = "update_user";
    private static final String KEY_UPDATE_TIME = "update_time";

    public SQLiteTableListeDao() {
    }

    public String getCreateTableListeRequest(){

        return "CREATE TABLE " +  NAME_TABLE_LISTE + " ( " +
                KEY_LISTE_ID_AI + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_LISTE_ID + " INTEGER, " +
                KEY_LISTE_NOM + " TEXT UNIQUE, " +
                KEY_LISTE_THEME + " TEXT, " +
                KEY_LISTE_CATHEGORY + " TEXT, " +
                KEY_LISTE_SHARED + " INTEGER, " +
                KEY_LISTE_MUST_DELETED + " INTEGER, " +
                KEY_CREATE_USER + " INTEGER, " +
                KEY_CREATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_UPDATE_USER + " INTEGER, " +
                KEY_UPDATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP )";
    }


    public ContentValues convertListeInternalBddToContentValues(ListeInternalBdd listeInternalBdd){
        ContentValues values = new ContentValues();
        values.put(KEY_LISTE_ID, listeInternalBdd.getId());
        values.put(KEY_LISTE_NOM, listeInternalBdd.getNom());
        values.put(KEY_LISTE_THEME, listeInternalBdd.getTheme());
        values.put(KEY_LISTE_CATHEGORY, listeInternalBdd.getCategory());
        values.put(KEY_LISTE_SHARED, listeInternalBdd.getShared());
        values.put(KEY_LISTE_MUST_DELETED, listeInternalBdd.getMustDeleted());
        values.put(KEY_CREATE_USER, listeInternalBdd.getCreateUser());
        values.put(KEY_CREATE_TIME, listeInternalBdd.getCreateTime());
        values.put(KEY_UPDATE_USER, listeInternalBdd.getUpdateUser());
        values.put(KEY_UPDATE_TIME, listeInternalBdd.getUpdateTime());
        return values;
    }

    public void addListe(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {
        db.insert(NAME_TABLE_LISTE, null, convertListeInternalBddToContentValues(listeInternalBdd));
        db.close();
    }

    public void updateListeInternalBdd(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {


        db.update(  NAME_TABLE_LISTE,
                    convertListeInternalBddToContentValues(listeInternalBdd),
                    KEY_LISTE_ID_AI + "=" + listeInternalBdd.getIdAi(), null);
        db.close();
    }

    public void addListes(SQLiteDatabase db, List<ListeInternalBdd> listesInternalBdd) {

        for(ListeInternalBdd listeInternalBdd : listesInternalBdd){
            addListe(db, listeInternalBdd);
        }
    }

    public List<ListeInternalBdd> mapBddResultToListeInternalBdd(SQLiteDatabase db, String query) throws TechnicalAppException {
        List<ListeInternalBdd> listes = new LinkedList<ListeInternalBdd>();
        ListeInternalBdd listeInternalBdd = null;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst())
        {
            do {
                listeInternalBdd = new ListeInternalBdd();
                listeInternalBdd.setIdAi(cursor.getInt(0));
                listeInternalBdd.setId(cursor.getInt(1));
                listeInternalBdd.setNom(cursor.getString(2));
                listeInternalBdd.setTheme(cursor.getString(3));
                listeInternalBdd.setCategory(cursor.getString(4));
                listeInternalBdd.setShared(cursor.getInt(5) > 0);
                listeInternalBdd.setMustDeleted(cursor.getInt(6) > 0);
                listeInternalBdd.setCreateUser(Integer.parseInt(cursor.getString(7)));
                listeInternalBdd.setCreateTime(cursor.getString(8));
                listeInternalBdd.setUpdateUser(Integer.parseInt(cursor.getString(9)));
                listeInternalBdd.setUpdateTime(cursor.getString(10));

                listes.add(listeInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listes;
    }

    public List<ListeInternalBdd> getAllListeInternalBdd(SQLiteDatabase db) throws TechnicalAppException {

        String query = "SELECT  * FROM " + NAME_TABLE_LISTE;
        return mapBddResultToListeInternalBdd(db, query);
    }


    public ListeInternalBdd convertListesToListeInternalBdd(List<ListeInternalBdd> listes, String searchParam) throws TechnicalAppException, FonctionalAppException {
        if(listes.size() > 1)
            throw new TechnicalAppException("Plus d'une liste à été trouvée avec l'id: " + searchParam);
        else if(listes.size()== 0)
            throw new FonctionalAppException("Aucune liste à été trouvée avec l'id: " + searchParam);
        else
            return listes.get(0);
    }

    public ListeInternalBdd getListeInternalBddWithIdAi(SQLiteDatabase db, int idAi) throws TechnicalAppException, FonctionalAppException {

        String query = "SELECT  * FROM " + NAME_TABLE_LISTE
                + " WHERE " + KEY_LISTE_ID_AI + " = " + idAi  + ";";

        List<ListeInternalBdd> listes = mapBddResultToListeInternalBdd(db, query);

        return convertListesToListeInternalBdd(listes, String.valueOf(idAi));
    }

    public ListeInternalBdd getListeInternalBddWithId(SQLiteDatabase db, int id) throws TechnicalAppException, FonctionalAppException {

        String query = "SELECT  * FROM " + NAME_TABLE_LISTE
                        + " WHERE " + KEY_LISTE_ID + " = " + id  + ";";

        List<ListeInternalBdd> listes = mapBddResultToListeInternalBdd(db, query);

        return convertListesToListeInternalBdd(listes, String.valueOf(id));
    }

    public List<ListeInternalBdd> getListeInternalBddWithUser(SQLiteDatabase db, int createUser) throws TechnicalAppException {

        String query = "SELECT * FROM " + NAME_TABLE_LISTE
                + " WHERE " + KEY_CREATE_USER + " = " + createUser + ";";

        return mapBddResultToListeInternalBdd(db, query);
    }

    public boolean deleteListeInternalBddWithIdAi(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {
        return db.delete(NAME_TABLE_LISTE, KEY_LISTE_ID_AI + "=" + listeInternalBdd.getIdAi(), null) > 0;
    }
    public boolean deleteListeInternalBddWithId(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {
        return db.delete(NAME_TABLE_LISTE, KEY_LISTE_ID + "=" + listeInternalBdd.getId(), null) > 0;
    }

    public void deleteAllListe(SQLiteDatabase db) {
        db.delete(NAME_TABLE_LISTE, null, null);
        db.close();
    }



    public static String getNameTableListe() {
        return NAME_TABLE_LISTE;
    }


}
