package com.memoquest.dao.internalBdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.memoquest.model.Liste;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MemoQuestBdd";

    private static final String NAME_TABLE_LISTE = "TableListe";
    private static final String KEY_LISTE_ID = "liste_id";
    private static final String KEY_LISTE_NOM = "liste_nom";
    private static final String KEY_LISTE_THEME = "liste_theme";
    private static final String KEY_LISTE_CATHEGORY = "liste_cathegory";

    public SQLiteDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableListe = "CREATE TABLE " +  NAME_TABLE_LISTE + " ( " +
                                            KEY_LISTE_ID + " INTEGER, " +
                                            KEY_LISTE_NOM + " TEXT, " +
                                            KEY_LISTE_THEME + " TEXT, " +
                                            KEY_LISTE_CATHEGORY + " TEXT )";
        db.execSQL(createTableListe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE_LISTE);
        this.onCreate(db);
    }

    public void addListOfListe(List<Liste> listOfListe) {

        SQLiteDatabase db = this.getWritableDatabase();

        for(Liste liste : listOfListe){

            Log.d("ajout de la liste avec le nom :", liste.getNom());

            ContentValues values = new ContentValues();
            values.put(KEY_LISTE_ID, liste.getId());
            values.put(KEY_LISTE_NOM, liste.getNom());
            values.put(KEY_LISTE_THEME, liste.getTheme());
            values.put(KEY_LISTE_CATHEGORY, liste.getCategory());

            db.insert(NAME_TABLE_LISTE, null, values);
        }
        db.close();
    }

    public void addListe(Liste liste) {

        Log.d("ajout de la liste avec le nom :", liste.getNom());

        // 1.obtenir l'accée à la base
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. creation d'un ContentValues pour ajouter une ligne dans la base
        ContentValues values = new ContentValues();
        values.put(KEY_LISTE_ID, liste.getId());
        values.put(KEY_LISTE_NOM, liste.getNom());
        values.put(KEY_LISTE_THEME, liste.getTheme());
        values.put(KEY_LISTE_CATHEGORY, liste.getCategory());

        db.insert(NAME_TABLE_LISTE, null, values);
        db.close();
    }


    public void deleteAllListe() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NAME_TABLE_LISTE, "1 = ?", new String[] { String.valueOf(1) });
        db.close();
    }


    public List<Liste> getListes() {

        List<Liste> listes = new ArrayList<Liste>();
        String query = "SELECT  * FROM " + NAME_TABLE_LISTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Liste liste;
        if (cursor.moveToFirst())
        {
            do {
                liste = new Liste();
                liste.setId(Integer.parseInt(cursor.getString(0)));
                liste.setNom(cursor.getString(1));
                liste.setTheme(cursor.getString(2));
                liste.setCategory(cursor.getString(3));
                listes.add(liste);
            } while (cursor.moveToNext());
        }
        Log.d("afficherTout()", listes.toString());
        return listes;
    }
}