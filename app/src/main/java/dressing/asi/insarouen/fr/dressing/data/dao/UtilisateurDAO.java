package dressing.asi.insarouen.fr.dressing.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.model.Utilisateur;


/**
 * Created by julie on 22/10/16.
 */

public class UtilisateurDAO extends DAOBase {
    public static final String TABLE_NAME = "PERSONNE";
    public static final String KEY = "id";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String AGE = "age";
    public static final String TAILLE = "taille";
    public static final String LOGIN = "identifiant";
    public static final String PASSWORD = "mdp";
    public static final String COULEUR_CHEVEUX = "couleurCheveux";
    public static final String COULEUR_PREFEREE = "couleurPreferee";
    public static final String MORPHOLOGIE = "signe";

    public static String createTable(){
        return "CREATE TABLE " + TABLE_NAME  + "("
                + KEY + " SERIAL PRIMARY KEY ,"
                + NOM + " VARCHAR(20) NOT NULL,"
                + PRENOM + " VARCHAR(20) NOT NULL,"
                + AGE + " INTEGER NOT NULL,"
                + TAILLE + " INTEGER NOT NULL,"
                + LOGIN + " INTEGER NOT NULL,"
                + PASSWORD + " INTEGER NOT NULL,"
                + COULEUR_CHEVEUX + " INTEGER NOT NULL,"
                + COULEUR_PREFEREE + " INTEGER NOT NULL,"
                + MORPHOLOGIE + " INTEGER NOT NULL,"
                + "CHECK ("+ COULEUR_CHEVEUX +" IN ('Blond','Brun','Roux','Chatain')))"
                ;
    }

    public void insert(Utilisateur u){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") from MAX(idObjet) FROM "+ TABLE_NAME , new String[]{});
        while(res.moveToNext()){
            id = res.getInt(1)+1;
        }
        u.setId(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, u.getId());
        values.put(NOM, u.getNom());
        values.put(PRENOM, u.getPrenom());
        values.put(AGE, u.getAge());
        values.put(TAILLE, u.getTaille());
        values.put(LOGIN, u.getLogin());
        values.put(PASSWORD, u.getMdp());
        values.put(COULEUR_CHEVEUX, u.getCouleurCheveux().name());
        values.put(COULEUR_PREFEREE, u.getCouleurPreferee().getCouleur());
        values.put(MORPHOLOGIE, u.getMorphologie().name());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }

}
