package dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;

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

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + KEY + " PRIMARY KEY AUTOINCREMENT,"
                + DRESSING + " INTEGER REFERENCES DRESSING(idDressing) ON DELETE CASCADE,"
                + COULEUR + " INTEGER NOT NULL,"
                + MATIERE + " VARCHAR(30) REFERENCES MATIERE_SAISON(matiere) ON DELETE CASCADE CHECK ("+ MATIERE +" IN ('Laine','Coton','Jean','Lin','Velours','Cuir','Dentelle','Daim', 'Satin','Paillete')),"
                + COUCHE + " INTEGER NOT NULL CHECK (couche>0 AND couche<4),"
                + NIVEAU + " VARCHAR(20) NOT NULL CHECK (niveau IN ('Haut','Bas','Hautbas')),"
                + SALE_PROPRE + " BOOLEAN NOT NULL,"
                + IMAGE + " VARCHAR(200),"
                + TYPE + " VARCHAR(20) NOT NULL CHECK ("+ TYPE +" IN ('Pantalon','Pantacourt','Jogging')),"
                + COUPE + " VARCHAR(20) NOT NULL CHECK ("+ COUPE +" IN ('Slim','Droit','Evase','Baggy'))"
                + ");"
                ;
    }

    public static String dropTable(){
        return "DROP TABLE " + TABLE_NAME  + ";";
    }

    public void insert(Pantalon p){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM " + TABLE_NAME, new String[]{});
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
        p.setNiveau(this.getNiveau(p));
        p.setCouche(this.getCouche(p));
        p.setSale(this.getSalePropre(p));
        p.setSignes(this.getSignes(p));
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }
}
