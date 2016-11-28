package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
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
    public static final String TABLE_NAME = "HAUT";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String MATIERE = "matiere";
    public static final String COUCHE = "couche";
    public static final String NIVEAU = "niveau";
    public static final String SALE_PROPRE = "sale_propre";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeH";
    public static final String COUPE = "coupeH";

    public HautDAO(Context pContext) {
        super(pContext);
    }

    public void insert(Haut h){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") FROM " + TABLE_NAME+";", new String[]{});
        while(res.moveToNext()){
            id = res.getInt(0)+1;
        }
        h.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, h.getIdObjet());
        values.put(DRESSING, h.getIdDressing());
        values.put(COULEUR, h.getCouleur().getCouleur());
        values.put(MATIERE, h.getMatiere().name());
        values.put(SALE_PROPRE, h.isSale());
        values.put(TYPE, h.getTypeH().name());
        values.put(COUPE,h.getCoupeH().name());
        values.put(IMAGE, h.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        h.setNiveau(this.getNiveau(h));
        h.setCouche(this.getCouche(h));
        h.setSignes(this.getSignes(h));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
