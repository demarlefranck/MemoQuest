package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.Liste;
import com.memoquest.model.ListeInternalBdd;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class SQLiteTableListeDao {

    private static final String NAME_TABLE_LISTE = "TableListe";

    private static final String KEY_LISTE_ID_TEMP = "liste_id_temp";
    private static final String KEY_LISTE_ID = "liste_id";
    private static final String KEY_LISTE_NOM = "liste_nom";
    private static final String KEY_LISTE_THEME = "liste_theme";
    private static final String KEY_LISTE_CATHEGORY = "liste_cathegory";
    private static final String KEY_LISTE_WILL_DELETE = "liste_will_delete";
    private static final String KEY_LISTE_SYNCHRO = "liste_synchro";

    public SQLiteTableListeDao() {}

    public String getCreateTableListeRequest(){

        return "CREATE TABLE " +  NAME_TABLE_LISTE + " ( " +
                KEY_LISTE_ID_TEMP + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_LISTE_ID + " INTEGER, " +
                KEY_LISTE_NOM + " TEXT UNIQUE, " +
                KEY_LISTE_THEME + " TEXT, " +
                KEY_LISTE_CATHEGORY + " TEXT, " +
                KEY_LISTE_WILL_DELETE + " INTEGER, " +
                KEY_LISTE_SYNCHRO + " INTEGER )";
    }


    public void addListe(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {
        ContentValues values = new ContentValues();
        values.put(KEY_LISTE_ID, listeInternalBdd.getId());
        values.put(KEY_LISTE_NOM, listeInternalBdd.getNom());
        values.put(KEY_LISTE_THEME, listeInternalBdd.getTheme());
        values.put(KEY_LISTE_CATHEGORY, listeInternalBdd.getCathegory());
        values.put(KEY_LISTE_SYNCHRO, listeInternalBdd.getSynchro());

        db.insert(NAME_TABLE_LISTE, null, values);
        db.close();
    }

    public void addListes(SQLiteDatabase db, List<ListeInternalBdd> listesInternalBdd) {

        for(ListeInternalBdd listeInternalBdd : listesInternalBdd){
            addListe(db, listeInternalBdd);
        }
    }

    public List<ListeInternalBdd> getAllListeInternalBdd(SQLiteDatabase db) {

        List<ListeInternalBdd> listes = new LinkedList<ListeInternalBdd>();
        String query = "SELECT  * FROM " + NAME_TABLE_LISTE;

        Cursor cursor = db.rawQuery(query, null);

        ListeInternalBdd listeInternalBdd = null;

        if (cursor.moveToFirst())
        {
            do {
                listeInternalBdd = new ListeInternalBdd();
                listeInternalBdd.setIdTemp(cursor.getInt(0));
                listeInternalBdd.setId(cursor.getInt(1));
                listeInternalBdd.setNom(cursor.getString(2));
                listeInternalBdd.setTheme(cursor.getString(3));
                listeInternalBdd.setCathegory(cursor.getString(4));
                listeInternalBdd.setSynchro(cursor.getInt(5) > 0);
                listes.add(listeInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return listes;
    }

    public ListeInternalBdd getListeInternalBddWithId(SQLiteDatabase db, int id) throws TechnicalAppException, FonctionalAppException {

        List<ListeInternalBdd> listes = new LinkedList<ListeInternalBdd>();
        String query = "SELECT  * FROM " + NAME_TABLE_LISTE
                        + " WHERE " + KEY_LISTE_ID + " = " + id  + ";";

        Cursor cursor = db.rawQuery(query, null);

        ListeInternalBdd listeInternalBdd = null;

        if (cursor.moveToFirst())
        {
            do {
                listeInternalBdd = new ListeInternalBdd();
                listeInternalBdd.setIdTemp(cursor.getInt(0));
                listeInternalBdd.setId(cursor.getInt(1));
                listeInternalBdd.setNom(cursor.getString(2));
                listeInternalBdd.setTheme(cursor.getString(3));
                listeInternalBdd.setCathegory(cursor.getString(4));
                listeInternalBdd.setSynchro(cursor.getInt(5) > 0);
                listes.add(listeInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if(listes.size() > 1)
            throw new TechnicalAppException("Plus d'une liste à été trouvée avec l'id: " + id);
        else if(listes.size()== 0)
            throw new FonctionalAppException("Aucune liste à été trouvée avec l'id: " + id);
        else
            return listes.get(0);
    }

    public ListeInternalBdd getListeInternalBddWithName(SQLiteDatabase db, String name) throws TechnicalAppException, FonctionalAppException {

        List<ListeInternalBdd> listes = new LinkedList<ListeInternalBdd>();
        String query = "SELECT * FROM " + NAME_TABLE_LISTE
                + " WHERE " + KEY_LISTE_NOM + " = \"" + name + "\";";

        Cursor cursor = db.rawQuery(query, null);

        ListeInternalBdd listeInternalBdd = null;

        if (cursor.moveToFirst())
        {
            do {
                listeInternalBdd = new ListeInternalBdd();
                listeInternalBdd.setIdTemp(cursor.getInt(0));
                listeInternalBdd.setId(cursor.getInt(1));
                listeInternalBdd.setNom(cursor.getString(2));
                listeInternalBdd.setTheme(cursor.getString(3));
                listeInternalBdd.setCathegory(cursor.getString(4));
                listeInternalBdd.setSynchro(cursor.getInt(5) > 0);
                listes.add(listeInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if(listes.size() > 1)
            throw new TechnicalAppException("Plus d'une liste à été trouvée avec l'id: " + name);
        else if(listes.size()== 0)
            throw new FonctionalAppException("Aucune liste à été trouvée avec l'id: " + name);
        else
            return listes.get(0);
    }

    public boolean deleteListeInternalBddWithIdTemp(SQLiteDatabase db, ListeInternalBdd listeInternalBdd) {
        return db.delete(NAME_TABLE_LISTE, KEY_LISTE_ID_TEMP + "=" + listeInternalBdd.getIdTemp(), null) > 0;
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
