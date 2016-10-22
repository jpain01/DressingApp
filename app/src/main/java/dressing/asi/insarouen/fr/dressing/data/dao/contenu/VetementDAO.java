package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

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
    public static final String TABLE_PARENT = "CONTENU";
    public static final String TABLE_NAME = "VETEMENT";
    public static final String KEY = "idObjet";
    public static final String MATIERE = "matiere";
    public static final String COUCHE = "couche";
    public static final String NIVEAU = "niveau";
    public static final String SALE_PROPRE = "sale_propre";
    public static final String IMAGE = "image";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + MATIERE + " VARCHAR(30) REFERENCES MATIERE_SAISON ON DELETE CASCADE,"
                + COUCHE + " INTEGER NOT NULL,"
                + NIVEAU + " VARCHAR(20) NOT NULL,"
                + SALE_PROPRE + " BOOLEAN NOT NULL,"
                + IMAGE + " VARCHAR(200),"
                + "CHECK (" + MATIERE + " IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),"
                + "CHECK (" + NIVEAU + " IN ('Haut','Bas','Hautbas')),"
                + "CHECK (" + NIVEAU + ">0 AND " + NIVEAU + "<4)"
                + ")INHERITS(" + TABLE_PARENT + ")"
                ;
    }

    public Niveau getNiveau(Vetement v){
        Niveau niveau = null;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + NIVEAU + " from " + TABLE_NAME + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
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
        Cursor res = mDb.rawQuery("select " + COUCHE + " from " + TABLE_NAME + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
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
        Cursor res = mDb.rawQuery("select " + SALE_PROPRE + " from " + TABLE_NAME + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
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
        Cursor res = mDb.rawQuery("select signe from " + TABLE_NAME + "v, CORRESPOND c where v.idobjet=c.idobjet and v.idobjet= ?", new String[]{String.valueOf(v.getIdObjet())});
        mDb.close();
        List rowValues = new ArrayList();
        while (res.moveToNext()) {
            rowValues.add(Morphologie.get(res.getString(1)));
        }
        resultat = (Morphologie[]) rowValues.toArray(new Morphologie[rowValues.size()]);
        res.close();
        return resultat;
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
