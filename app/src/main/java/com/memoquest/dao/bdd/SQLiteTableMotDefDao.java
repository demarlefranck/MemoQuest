package com.memoquest.dao.bdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.memoquest.exception.FonctionalAppException;
import com.memoquest.exception.TechnicalAppException;
import com.memoquest.model.MotDefInternalBdd;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fdemarle on 18/09/2014.
 */
public class SQLiteTableMotDefDao {

    private static final String NAME_TABLE_MOT_DEF = "TableMotDef";

    private static final String KEY_MOT_DEF_ID = "MotDef_id";
    private static final String KEY_MOT_DEF_SERVER_ID = "MotDef_server_id";
    private static final String KEY_MOT_DEF_LISTE_ID = "MotDef_liste_id";
    private static final String KEY_MOT_DEF_LISTE_SERVER_ID = "MotDef_liste_server_id";
    private static final String KEY_MOT_DEF_MOT = "MotDef_mot";
    private static final String KEY_MOT_DEF_DEFINITION = "MotDef_definition";
    private static final String KEY_MOT_DEF_MUST_DELETED = "MotDef_must_deleted";
    private static final String KEY_CREATE_USER = "create_user";
    private static final String KEY_CREATE_TIME = "create_time";
    private static final String KEY_UPDATE_USER = "update_user";
    private static final String KEY_UPDATE_TIME = "update_time";

    public SQLiteTableMotDefDao() {

    }

    public String getCreateTableMotDefRequest(){
        return "CREATE TABLE " +  NAME_TABLE_MOT_DEF + " ( " +
                KEY_MOT_DEF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MOT_DEF_SERVER_ID + " INTEGER, " +
                KEY_MOT_DEF_LISTE_ID + " INTEGER, " +
                KEY_MOT_DEF_LISTE_SERVER_ID + " INTEGER, " +
                KEY_MOT_DEF_MOT + " TEXT, " +
                KEY_MOT_DEF_DEFINITION + " TEXT, " +
                KEY_MOT_DEF_MUST_DELETED + " INTEGER, " +
                KEY_CREATE_USER + " INTEGER, " +
                KEY_CREATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                KEY_UPDATE_USER + " INTEGER, " +
                KEY_UPDATE_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP )";
    }

    public ContentValues convertMotDefInternalBddToContentValues(MotDefInternalBdd motDefInternalBdd){
        ContentValues values = new ContentValues();
        values.put(KEY_MOT_DEF_ID, motDefInternalBdd.getId());
        values.put(KEY_MOT_DEF_SERVER_ID, motDefInternalBdd.getMotDefServerId());
        values.put(KEY_MOT_DEF_LISTE_ID, motDefInternalBdd.getMotDefListeInternalBddId());
        values.put(KEY_MOT_DEF_LISTE_SERVER_ID, motDefInternalBdd.getMotDefListeServerId());
        values.put(KEY_MOT_DEF_MOT, motDefInternalBdd.getMot());
        values.put(KEY_MOT_DEF_DEFINITION, motDefInternalBdd.getDefinition());
        values.put(KEY_MOT_DEF_MUST_DELETED, motDefInternalBdd.getMustDeleted());
        values.put(KEY_CREATE_USER, motDefInternalBdd.getCreateUser());
        values.put(KEY_CREATE_TIME, motDefInternalBdd.getCreateTime());
        values.put(KEY_UPDATE_USER, motDefInternalBdd.getUpdateUser());
        values.put(KEY_UPDATE_TIME, motDefInternalBdd.getUpdateTime());
        return values;
    }

    public List<MotDefInternalBdd> mapBddResultToMotDefInternalBdd(SQLiteDatabase db, String query) throws TechnicalAppException {
        List<MotDefInternalBdd> listes = new LinkedList<MotDefInternalBdd>();
        MotDefInternalBdd motDefInternalBdd = null;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                motDefInternalBdd = new MotDefInternalBdd();
                motDefInternalBdd.setId(cursor.getInt(0));
                motDefInternalBdd.setMotDefServerId(cursor.getInt(1));
                motDefInternalBdd.setMotDefListeInternalBddId(cursor.getInt(2));
                motDefInternalBdd.setMotDefListeServerId(cursor.getInt(3));
                motDefInternalBdd.setMot(cursor.getString(4));
                motDefInternalBdd.setDefinition(cursor.getString(5));
                motDefInternalBdd.setMustDeleted(cursor.getInt(6) > 0);
                motDefInternalBdd.setCreateUser(Integer.parseInt(cursor.getString(7)));
                motDefInternalBdd.setCreateTime(cursor.getString(8));
                motDefInternalBdd.setUpdateUser(Integer.parseInt(cursor.getString(9)));
                motDefInternalBdd.setUpdateTime(cursor.getString(10));
                listes.add(motDefInternalBdd);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listes;
    }

    public MotDefInternalBdd convertMotDefsToMotDefInternalBdd(List<MotDefInternalBdd> listes, String searchParam) throws TechnicalAppException, FonctionalAppException {
        if(listes.size() > 1){

            throw new TechnicalAppException("Plus d'un mot / definition à été trouvée avec l'id: " + searchParam);

        } else if(listes.isEmpty()){

            throw new FonctionalAppException("Aucun mot / definition à été trouvée avec l'id: " + searchParam);

        } else{

            return listes.get(0);
        }
    }

    public int addMotDefToInternalBdd(SQLiteDatabase db, MotDefInternalBdd motDefInternalBdd){
        long id = db.insert(NAME_TABLE_MOT_DEF, null, convertMotDefInternalBddToContentValues(motDefInternalBdd));
        db.close();
        return (int) id;
    }

    public void updateMotDefToInternalBdd(SQLiteDatabase db, MotDefInternalBdd motDefInternalBdd){
        db.update(  NAME_TABLE_MOT_DEF, convertMotDefInternalBddToContentValues(motDefInternalBdd),
                    KEY_MOT_DEF_ID + "=" + motDefInternalBdd.getId(), null);
        db.close();
    }

    public List<MotDefInternalBdd> getAllMotDefInternalBddByListeId(SQLiteDatabase db, Integer liste_id) throws TechnicalAppException, FonctionalAppException {
        String query = "SELECT * FROM " + NAME_TABLE_MOT_DEF + " WHERE " + KEY_MOT_DEF_LISTE_ID + " = " + liste_id  + ";";
        return mapBddResultToMotDefInternalBdd(db, query);
    }

    public MotDefInternalBdd getMotDefInternalBddById(SQLiteDatabase db, int id) throws TechnicalAppException, FonctionalAppException {
        String query = "SELECT * FROM " + NAME_TABLE_MOT_DEF + " WHERE " + KEY_MOT_DEF_ID + " = " + id  + ";";
        List<MotDefInternalBdd> listes = mapBddResultToMotDefInternalBdd(db, query);
        return convertMotDefsToMotDefInternalBdd(listes, String.valueOf(id));
    }

    public boolean deleteMotDefInternalBddById(SQLiteDatabase db, MotDefInternalBdd motDefInternalBdd){
        return db.delete(NAME_TABLE_MOT_DEF, KEY_MOT_DEF_ID + "=" + motDefInternalBdd.getId(), null) > 0;
    }

    public boolean deleteAllMotDefInternalBdd(SQLiteDatabase db){
        return db.delete(NAME_TABLE_MOT_DEF, null, null) > 0;
    }

    public List<MotDefInternalBdd> getAllMotDefInternalBdd(SQLiteDatabase db) throws TechnicalAppException {
        String query = "SELECT * FROM " + NAME_TABLE_MOT_DEF;
        return mapBddResultToMotDefInternalBdd(db, query);
    }

    public static String getNameTableMotDef() {
        return NAME_TABLE_MOT_DEF;
    }
}
