package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;

/**
 * Created by julie on 22/10/16.
 */

public class PantalonDAO extends VetementDAO {
    public static final String TABLE_GRAN_PARENT = "CONTENU";
    public static final String TABLE_PARENT = "VETEMENT";
    public static final String TABLE_NAME = "PANTALON";
    public static final String KEY = "idObjet";
    public static final String MATIERE = "matiere";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeP";
    public static final String COUPE = "coupeP";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + TYPE + " VARCHAR(20) NOT NULL,"
                + COUPE + " VARCHAR(20) NOT NULL,"
                + "CHECK ("+ TYPE +" IN ('Pantalon','Pantacourt','Jogging')),"
                + "CHECK ("+ COUPE +" IN ('Slim','Droit','Evase','Baggy'))"
                + ")INHERITS("+ TABLE_PARENT +");"
                ;
    }

    public void insert(Pantalon p){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_GRAN_PARENT, new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        p.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, p.getIdObjet());
        values.put(DRESSING, p.getIdDressing());
        values.put(COULEUR, p.getCouleur().getCouleur());
        values.put(MATIERE, p.isSale());
        values.put(TYPE, p.getTypeP().name());
        values.put(COUPE,p.getCoupeP().name());
        values.put(IMAGE, p.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        p.setNiveau(getNiveau(p));
        p.setCouche(getCouche(p));
        p.setSale(getSalePropre(p));
        p.setSignes(getSignes(p));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
