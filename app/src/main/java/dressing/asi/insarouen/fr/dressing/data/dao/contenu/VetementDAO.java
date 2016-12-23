package dressing.asi.insarouen.fr.dressing.data.dao.contenu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.Utilisateur;
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
        while(res.moveToNext()){
            niveau = Niveau.get(res.getString(0));
        }
        res.close();
        mDb.close();
        return niveau;
    }

    public int getCouche(Vetement v){
        int couche = 0;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + COUCHE + " from " + this.getTableName(v) + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
        while(res.moveToNext()){
            couche = res.getInt(0);
        }
        res.close();
        mDb.close();
        return couche;
    }

    public boolean getSalePropre(Vetement v){
        boolean sale_propre = false;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select " + SALE_PROPRE + " from " + this.getTableName(v) + " where " + KEY + " = ?", new String[]{String.valueOf(v.getIdObjet())});
        while(res.moveToNext()){
            sale_propre = res.getInt(0) > 0;
        }
        res.close();
        mDb.close();
        return sale_propre;
    }

    public Morphologie[] getSignes(Vetement v) {
        Morphologie[] resultat;
        SQLiteDatabase mDb = open();
        Cursor res = mDb.rawQuery("select signe from " + this.getTableName(v) + " p, CORRESPOND_"+this.getTableName(v)+" c where p.idobjet=c.idobjet and p.idobjet= ?", new String[]{String.valueOf(v.getIdObjet())});
        List rowValues = new ArrayList();
        while (res.moveToNext()) {
            rowValues.add(Morphologie.get(res.getString(0)));
        }
        resultat = (Morphologie[]) rowValues.toArray(new Morphologie[rowValues.size()]);
        res.close();
        mDb.close();
        return resultat;
    }

    @Override
    public ArrayList<Contenu> findAll(int idDressing, Context context) {
        ArrayList<Contenu> resultList = new ArrayList<>();
        // Récupération des pantalons
        PantalonDAO p = new PantalonDAO(context);
        p.open();
        resultList.addAll(p.findAll(idDressing));
        p.close();
        // Récupération des Hauts
        HautDAO h = new HautDAO(context);
        h.open();
        resultList.addAll(h.findAll(idDressing));
        h.close();
        // Récupération des Autres
        AutreDAO a = new AutreDAO(context);
        a.open();
        resultList.addAll(a.findAll(idDressing));
        a.close();

        return resultList;
    }
}
