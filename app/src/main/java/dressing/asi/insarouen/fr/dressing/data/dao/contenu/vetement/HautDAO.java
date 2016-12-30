package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.CoupeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.TypeHaut;

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

    public ArrayList<Haut> findAll(int idDressing) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +";", new String[]{});
        ArrayList<Haut> hautList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Haut h = new Haut(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeHaut.get(res.getString(res.getColumnIndex(TYPE))), CoupeHaut.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            hautList.add(h);
        }

        res.close();
        return hautList;
    }

    public ArrayList<Contenu> findByType(int idDressing, String typeHaut) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +" AND "+ TYPE +"= '"+ typeHaut +"';", new String[]{});
        ArrayList<Contenu> hautList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Haut h = new Haut(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeHaut.get(res.getString(res.getColumnIndex(TYPE))), CoupeHaut.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            hautList.add(h);
        }

        res.close();
        return hautList;
    }

    public Contenu findById(int idDressing, int id) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM "+ TABLE_NAME +" WHERE idDressing = "+ idDressing + " AND "+ KEY +"="+ id +";", new String[]{});

        Haut h = null;
        if (res.moveToFirst()) {
            h = new Haut(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeHaut.get(res.getString(res.getColumnIndex(TYPE))), CoupeHaut.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
        }

        mDb.close();
        res.close();
        return h;
    }
}
