package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Vetement;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Niveau;

/**
 * Created by julie on 22/10/16.
 */

public class VetementDAO extends ContenuDAO {
    public static final String KEY = "idObjet";
    public static final String MATIERE = "matiere";
    public static final String COUCHE = "couche";
    public static final String NIVEAU = "niveau";
    public static final String SALE_PROPRE = "sale_propre";
    public static final String IMAGE = "image";

    public VetementDAO(Context pContext) {
        super(pContext);
    }

    private String getTableName(Vetement v) {
        if(v.isAutre()){
            return "AUTRE";
        }else if(v.isHaut()) {
            return "HAUT";
        }else {
            return "PANTALON";
        }
    }

    public Niveau getNiveau(Vetement v){
        Niveau niveau = null;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + NIVEAU + " from " + this.getTableName(v) + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
        mDb.close();
        while(res.moveToNext()){
            niveau = Niveau.get(res.getString(1));
        }
        res.close();
        return niveau;
    }

    public int getCouche(Vetement v){
        int couche = 0;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + COUCHE + " from " + this.getTableName(v) + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
        mDb.close();
        while(res.moveToNext()){
            couche = res.getInt(1);
        }
        res.close();
        return couche;
    }

    public boolean getSalePropre(Vetement v){
        boolean sale_propre = false;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + SALE_PROPRE + " from " + this.getTableName(v) + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
        mDb.close();
        while(res.moveToNext()){
            sale_propre = res.getInt(1) > 0;
        }
        res.close();
        return sale_propre;
    }

    public Morphologie[] getSignes(Vetement v) {
        Morphologie[] resultat;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select signe from " + this.getTableName(v) + "v, CORRESPOND c where v.idobjet=c.idobjet and v.idobjet= ?", new String[]{String.valueOf(v.getIdObjet())});
        mDb.close();
        List rowValues = new ArrayList();
        while (res.moveToNext()) {
            rowValues.add(Morphologie.get(res.getString(1)));
        }
        resultat = (Morphologie[]) rowValues.toArray(new Morphologie[rowValues.size()]);
        res.close();
        return resultat;
    }
}
