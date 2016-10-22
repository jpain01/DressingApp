package dressing.asi.insarouen.fr.dressing.data.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;

/**
 * Created by julie on 22/10/16.
 */

public class ContenuDAO extends DAOBase {
    public static final String TABLE_NAME = "CONTENU";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + KEY + " SERIAL PRIMARY KEY,"
                + DRESSING + " INTEGER REFERENCES DRESSING ON DELETE CASCADE,"
                + COULEUR + "INTEGER NOT NULL )"
                ;
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
