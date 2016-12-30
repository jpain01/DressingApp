package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.sac.TypeSac;

/**
 * Created by julie on 22/10/16.
 */

public class SacDAO extends ContenuDAO {
    public static final String TABLE_NAME = "SAC";
    public static final String KEY = "idObjet";
    public static final String DRESSING = "idDressing";
    public static final String COULEUR = "couleur";
    public static final String TYPE = "typeS";
    public static final String IMAGE = "image";

    public SacDAO(Context pContext) {
        super(pContext);
    }

    public void insert(Sac s){
        int id = 1;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select MAX(" + KEY + ")  FROM " + TABLE_NAME +";", new String[]{});
        while(res.moveToNext()){
            id = res.getInt(0)+1;
        }
        s.setIdObjet(id);
        res.close();

        ContentValues values = new ContentValues();
        values.put(KEY, s.getIdObjet());
        values.put(DRESSING, s.getIdDressing());
        values.put(COULEUR, s.getCouleur().getCouleur());
        values.put(TYPE, s.getTypeS().name());
        values.put(IMAGE, s.getImage());
        mDb.insert(TABLE_NAME,null,values);
        mDb.close();
    }

    public void delete(int id) {
        SQLiteDatabase mDb = open();
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
        mDb.close();
    }


    public ArrayList<Contenu> findAll(int idDressing) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM " + TABLE_NAME +" WHERE idDressing = "+ idDressing +";", new String[]{});
        ArrayList<Contenu> sacList = new ArrayList<>();

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            Sac s = new Sac(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), TypeSac.get(res.getString(res.getColumnIndex(TYPE))));
            sacList.add(s);
        }

        res.close();
        return sacList;
    }

    public Contenu findById(int idDressing, int id) {
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select * FROM "+ TABLE_NAME +" WHERE idDressing = "+ idDressing + " AND "+ KEY +"="+ id +";", new String[]{});

        Sac s = null;
        if (res.moveToFirst()) {
            s = new Sac(new Couleur(res.getInt(res.getColumnIndex(COULEUR))), res.getString(res.getColumnIndex(IMAGE)), res.getInt(res.getColumnIndex(DRESSING)), res.getInt(res.getColumnIndex(KEY)), TypeSac.get(res.getString(res.getColumnIndex(TYPE))));
        }

        mDb.close();
        res.close();
        return s;
    }
}
