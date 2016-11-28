package dressing.asi.insarouen.fr.dressing.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.data.DAOBase;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.sac.TypeSac;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.CoupePantalon;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.TypePantalon;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SacDAO s = new SacDAO(getApplicationContext());
        s.open();
        s.insert(new Sac(new Couleur(1), "image.jpg", 1, TypeSac.Sacados));
        s.close();

        PantalonDAO p = new PantalonDAO(getApplicationContext());
        p.open();
        p.insert(new Pantalon(new Couleur(1),"image.jpg",1, Matiere.Coton,false, TypePantalon.Pantalon, CoupePantalon.Slim));
        p.close();

        setContentView(R.layout.activity_main);
    }
}
