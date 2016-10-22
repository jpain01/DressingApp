package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;

/**
 * Created by julie on 22/10/16.
 */

public class HautDAO extends VetementDAO {
    public static final String TABLE_GRAN_PARENT = "CONTENU";
    public static final String TABLE_PARENT = "VETEMENT";
    public static final String TABLE_NAME = "HAUT";
    public static final String KEY = "idObjet";
    public static final String MATIERE = "matiere";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeH";
    public static final String COUPE = "coupeH";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + TYPE + " VARCHAR(20) NOT NULL,"
                + COUPE + " VARCHAR(20) NOT NULL,"
                + "CHECK ("+ TYPE +" IN ('Teeshirt','Chemisier','Pull','Veste','Manteau')),"
                + "CHECK ("+ COUPE +" IN ('Cintre','Droit','Large'))"
                + ")INHERITS("+ TABLE_PARENT +");"
                ;
    }

    public void insert(Haut h){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_GRAN_PARENT, new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        h.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, h.getIdObjet());
        values.put(DRESSING, h.getIdDressing());
        values.put(COULEUR, h.getCouleur().getCouleur());
        values.put(MATIERE, h.isSale());
        values.put(TYPE, h.getTypeH().name());
        values.put(COUPE,h.getCoupeH().name());
        values.put(IMAGE, h.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        h.setNiveau(getNiveau(h));
        h.setCouche(getCouche(h));
        h.setSale(getSalePropre(h));
        h.setSignes(getSignes(h));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
