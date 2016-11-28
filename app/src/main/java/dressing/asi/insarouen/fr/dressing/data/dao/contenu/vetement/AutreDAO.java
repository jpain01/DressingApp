package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;

/**
 * Created by julie on 22/10/16.
 */

public class AutreDAO extends VetementDAO {
    public static final String TABLE_NAME = "AUTRE";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String MATIERE = "matiere";
    public static final String COUCHE = "couche";
    public static final String NIVEAU = "niveau";
    public static final String SALE_PROPRE = "sale_propre";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeA";
    public static final String COUPE = "coupeA";

    public AutreDAO(Context pContext) {
        super(pContext);
    }

    public void insert(Autre a){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ")  FROM " + TABLE_NAME+";", new String[]{});
        while(res.moveToNext()){
            id = res.getInt(0)+1;
        }
        a.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, a.getIdObjet());
        values.put(DRESSING, a.getIdDressing());
        values.put(COULEUR, a.getCouleur().getCouleur());
        values.put(MATIERE, a.getMatiere().name());
        values.put(SALE_PROPRE, a.isSale());
        values.put(TYPE, a.getTypeA().name());
        values.put(COUPE,a.getCoupeA().name());
        values.put(IMAGE, a.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        a.setNiveau(this.getNiveau(a));
        a.setCouche(this.getCouche(a));
        a.setSignes(this.getSignes(a));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
