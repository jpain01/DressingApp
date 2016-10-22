package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Chaussures;

/**
 * Created by julie on 22/10/16.
 */

public class ChaussuresDAO extends ContenuDAO {
    public static final String TABLE_PARENT = "CONTENU";
    public static final String TABLE_NAME = "CHAUSSURE";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String TYPE = "typeC";
    public static final String IMAGE = "image";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + TYPE + " VARCHAR(20) NOT NULL,"
                + IMAGE + " VARCHAR(200) ,"
                + "CHECK ("+ TYPE +" IN ('Escarpins','Ballerines','Baskets','Bottesplates','Bottesatalons','Sandales'))"
                + ")INHERITS("+ TABLE_PARENT +");"
                ;
    }

    public void insert(Chaussures c){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_PARENT , new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        c.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, c.getIdObjet());
        values.put(DRESSING, c.getIdDressing());
        values.put(COULEUR, c.getCouleur().getCouleur());
        values.put(TYPE, c.getTypeC().name());
        values.put(IMAGE, c.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}