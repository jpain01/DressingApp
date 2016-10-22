package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;

/**
 * Created by julie on 22/10/16.
 */

public class AutreDAO extends VetementDAO {
    public static final String TABLE_GRAN_PARENT = "CONTENU";
    public static final String TABLE_PARENT = "VETEMENT";
    public static final String TABLE_NAME = "AUTRE";
    public static final String KEY = "idObjet";
    public static final String MATIERE = "matiere";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeA";
    public static final String COUPE = "coupeA";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + TYPE + " VARCHAR(20) NOT NULL,"
                + COUPE + " VARCHAR(20) NOT NULL,"
                + "CHECK ("+ TYPE +" IN ('Jupe','Short','Robe','Combinaison')),"
                + "CHECK ("+ COUPE +" IN ('Long','Court'))"
                + ")INHERITS("+ TABLE_PARENT +");"
                ;
    }

    public void insert(Autre a){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_GRAN_PARENT, new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        a.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, a.getIdObjet());
        values.put(DRESSING, a.getIdDressing());
        values.put(COULEUR, a.getCouleur().getCouleur());
        values.put(MATIERE, a.isSale());
        values.put(TYPE, a.getTypeA().name());
        values.put(COUPE,a.getCoupeA().name());
        values.put(IMAGE, a.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        a.setNiveau(getNiveau(a));
        a.setCouche(getCouche(a));
        a.setSale(getSalePropre(a));
        a.setSignes(getSignes(a));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
