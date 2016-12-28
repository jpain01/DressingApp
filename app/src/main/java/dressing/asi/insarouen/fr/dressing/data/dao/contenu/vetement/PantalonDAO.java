package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.CoupeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.TypeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.CoupePantalon;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.TypePantalon;

/**
 * Created by julie on 22/10/16.
 */

public class PantalonDAO extends VetementDAO {
    public static final String TABLE_NAME = "PANTALON";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String MATIERE = "matiere";
    public static final String COUCHE = "couche";
    public static final String NIVEAU = "niveau";
    public static final String SALE_PROPRE = "sale_propre";
    public static final String IMAGE = "image";
    public static final String TYPE = "typeP";
    public static final String COUPE = "coupeP";

    public PantalonDAO(Context pContext) {
        super(pContext);
    }

    public void insert(Pantalon p){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") FROM " + TABLE_NAME+";", new String[]{});
        while(res.moveToNext()){
            id = res.getInt(0)+1;
        }
        p.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, p.getIdObjet());
        values.put(DRESSING, p.getIdDressing());
        values.put(COULEUR, p.getCouleur().getCouleur());
        values.put(MATIERE, p.getMatiere().name());
        values.put(SALE_PROPRE, p.isSale());
        values.put(TYPE, p.getTypeP().name());
        values.put(COUPE,p.getCoupeP().name());
        values.put(IMAGE, p.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();

        // Certtains attributs sont calculés automatiquement, il faut donc les attribuer à l'objet après la requête
        p.setNiveau(this.getNiveau(p));
        p.setCouche(this.getCouche(p));
        p.setSignes(this.getSignes(p));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }

    public ArrayList<Pantalon> findAll(int idDressing) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +";", new String[]{});
        ArrayList<Pantalon> pantalonList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Pantalon p = new Pantalon(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypePantalon.get(res.getString(res.getColumnIndex(TYPE))), CoupePantalon.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            pantalonList.add(p);
        }

        res.close();
        return pantalonList;
    }

    public ArrayList<Contenu> findByType(int idDressing, String typePantalon) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +" AND "+ TYPE +"= '"+ typePantalon +"';", new String[]{});
        ArrayList<Contenu> pantalonList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Pantalon p = new Pantalon(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), Matiere.get(res.getString(res.getColumnIndex(MATIERE))), (res.getInt(res.getColumnIndex(SALE_PROPRE)) ==1), TypePantalon.get(res.getString(res.getColumnIndex(TYPE))), CoupePantalon.get(res.getString(res.getColumnIndex(COUPE))), res.getInt(res.getColumnIndex(COUCHE)), Niveau.get(res.getString(res.getColumnIndex(NIVEAU))));
            pantalonList.add(p);
        }

        res.close();
        return pantalonList;
    }
}
