package dressing.asi.insarouen.fr.dressing.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.model.Utilisateur;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.CouleurCheveux;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;


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

    public UtilisateurDAO(Context pContext) {
        super(pContext);
    }

    public void insert(Utilisateur u){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ") FROM "+ TABLE_NAME , new String[]{});
        while(res.moveToNext()){
            id = res.getInt(0)+1;
        }
        u.setId(id);

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

    public boolean identifiantDejaPresent(String identifiant){
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM "+ TABLE_NAME+" where "+ LOGIN +"='"+ identifiant +"';" , new String[]{});
        if (res.moveToFirst()) {
            res.close();
            mDb.close();
            return true;
        } else {
            res.close();
            mDb.close();
            return false;
        }
    }

    public int isCorrectUser(String pswd, String id){
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select "+ KEY +" FROM "+ TABLE_NAME+" where "+ LOGIN +"='"+ id +"' and "+ PASSWORD +"='"+ pswd +"';" , new String[]{});
        if (res.moveToFirst()) {
            return res.getInt(0);
        } else {
            return 0;
        }
    }

    public Utilisateur findUserById(int id){
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * from "+ TABLE_NAME+" where "+ KEY +"="+ id +";", new String[]{});
        if (res.moveToFirst()) {
            return new Utilisateur(id, res.getString(res.getColumnIndex(NOM)),res.getString(res.getColumnIndex(PRENOM)), res.getString(res.getColumnIndex(LOGIN)), res.getString(res.getColumnIndex(PASSWORD)), res.getInt(res.getColumnIndex(AGE)), res.getInt(res.getColumnIndex(TAILLE)), new Couleur(res.getInt(res.getColumnIndex(COULEUR_PREFEREE))), CouleurCheveux.get(res.getString(res.getColumnIndex(COULEUR_CHEVEUX))), Morphologie.get(res.getString(res.getColumnIndex(MORPHOLOGIE))));
        } else {
            return null;
        }
    }

    public int getIdDressing(int idUtilisateur) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select idDressing from dressing where idPers = "+idUtilisateur+";", new String[]{});
        if (res.moveToFirst()) {
            return res.getInt(0);
        } else {
            return 0;
        }
    }

    public void delete(long id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }

}
