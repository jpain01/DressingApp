package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;

/**
 * Created by julie on 22/10/16.
 */

public class SacDAO extends ContenuDAO {
    public static final String TABLE_PARENT = "CONTENU";
    public static final String TABLE_NAME = "SAC";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String TYPE = "typeS";
    public static final String IMAGE = "image";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + TYPE + " VARCHAR(20) NOT NULL,"
                + IMAGE + " VARCHAR(200) ,"
                + "CHECK ("+ TYPE +" IN ('Sacados','Sacamain','Pochette'))"
                + ")INHERITS("+ TABLE_PARENT +");"
                ;
    }

    public void insert(Sac s){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_PARENT , new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        s.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, s.getIdObjet());
        values.put(DRESSING, s.getIdDressing());
        values.put(COULEUR, s.getCouleur().getCouleur());
        values.put(TYPE, s.getTypeS().name());
        values.put(IMAGE, s.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
