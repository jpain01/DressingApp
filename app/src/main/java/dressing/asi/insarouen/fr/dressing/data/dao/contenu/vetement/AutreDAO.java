package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.CoupeAutre;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.TypeAutre;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.CoupeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.TypeHaut;

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

    public ArrayList<Autre> findAll(int idDressing) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +";", new String[]{});
        ArrayList<Autre> autreList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Autre a = new Autre(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeAutre.get(res.getString(res.getColumnIndex(TYPE))), CoupeAutre.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            autreList.add(a);
        }

        mDb.close();
        res.close();
        return autreList;
    }

    public ArrayList<Contenu> findByType(int idDressing, String typeAutre) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +" AND "+ TYPE +"= '"+ typeAutre +"';", new String[]{});
        ArrayList<Contenu> autreList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Autre a = new Autre(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeAutre.get(res.getString(res.getColumnIndex(TYPE))), CoupeAutre.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            autreList.add(a);
        }

        mDb.close();
        res.close();
        return autreList;
    }

    public Contenu findById(int idDressing, int id) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM "+ TABLE_NAME +" WHERE idDressing = "+ idDressing + " AND "+ KEY +"="+ id +";", new String[]{});

        Autre a = null;
        if (res.moveToFirst()) {
            a = new Autre(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypeAutre.get(res.getString(res.getColumnIndex(TYPE))), CoupeAutre.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
        }

        mDb.close();
        res.close();
        return a;
    }
}
