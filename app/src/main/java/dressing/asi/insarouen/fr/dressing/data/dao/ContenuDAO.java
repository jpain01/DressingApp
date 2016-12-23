package dressing.asi.insarouen.fr.dressing.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;

/**
 * Created by julie on 22/10/16.
 */

public class ContenuDAO extends DAOBase {
    public ContenuDAO(Context pContext) {
        super(pContext);
    }

    public ArrayList<Contenu> findAll(int idDressing, Context context) {
        ArrayList<Contenu> resultList = new ArrayList<>();
        // Récupération des Sacs
        SacDAO s = new SacDAO(context);
        s.open();
        resultList.addAll(s.findAll(idDressing));
        s.close();
        // Récupération des Chaussures
        ChaussuresDAO c = new ChaussuresDAO(context);
        c.open();
        resultList.addAll(c.findAll(idDressing));
        c.close();
        // Récupération des Vêtements
        VetementDAO v = new VetementDAO(context);
        v.open();
        resultList.addAll(v.findAll(idDressing, context));
        v.close();

        return resultList;
    }
}
