package dressing.asi.insarouen.fr.dressing.activity.consultation;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Chaussures;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;
import dressing.asi.insarouen.fr.dressing.fragment.contenu.ContenuItemAdapter;

/**
 * Created by julie on 28/12/16.
 */

public class ConsultationActivity extends AppCompatActivity {
    public static final String CONTENU_ID = "id";
    public static final String CONTENU_TYPE = "type";
    public static final String DRESSING_ID = "dressing_id";
    private int id;
    private String type;
    private int idDressing;
    private Contenu contenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.consultation_activity);

        // Récupération des éléments
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView couleurView = (TextView) findViewById(R.id.couleur);
        TextView typeContenuView = (TextView) findViewById(R.id.typeContenu);
        TextView typeView = (TextView) findViewById(R.id.type);
        TextView labelCoupeView = (TextView) findViewById(R.id.labelCoupe);
        TextView coupeView = (TextView) findViewById(R.id.coupe);
        TextView labelMatiereView = (TextView) findViewById(R.id.labelMatiere);
        TextView matiereView = (TextView) findViewById(R.id.matiere);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        // Récupération de l'identifiant et de l'objet associé
        id = getIntent().getIntExtra(CONTENU_ID, 0);
        type = getIntent().getStringExtra(CONTENU_TYPE);
        idDressing = getIntent().getIntExtra(DRESSING_ID, 0);
        if (id==0) {
            Toast t = Toast.makeText(this, "Une erreur s'est produite ! ", Toast.LENGTH_LONG);
            t.show();
        } else {
            switch (type) {
                case "sac":
                    SacDAO s = new SacDAO(this);
                    contenu = s.findById(idDressing, id);
                    s.close();
                    // Affichage des infos
                    typeContenuView.setText("Sac");
                    typeView.setText(((Sac) contenu).getTypeS().toString());
                    break;
                case "chaussures":
                    ChaussuresDAO c = new ChaussuresDAO(this);
                    contenu = c.findById(idDressing, id);
                    c.close();
                    // Affichage des infos
                    typeContenuView.setText("Chaussures");
                    typeView.setText(((Chaussures) contenu).getTypeC().toString());
                    break;
                case "haut":
                    HautDAO h = new HautDAO(this);
                    contenu = h.findById(idDressing, id);
                    h.close();
                    // Affichage des infos
                    typeContenuView.setText("Vêtement");
                    typeView.setText(((Haut) contenu).getTypeH().toString());
                    labelCoupeView.setVisibility(View.VISIBLE);
                    coupeView.setText(((Haut) contenu).getCoupeH().toString());
                    labelMatiereView.setVisibility(View.VISIBLE);
                    matiereView.setText(((Haut) contenu).getMatiere().toString());
                    break;
                case "autre":
                    AutreDAO a = new AutreDAO(this);
                    contenu = a.findById(idDressing, id);
                    a.close();
                    // Affichage des infos
                    typeContenuView.setText("Vêtement");
                    typeView.setText(((Autre) contenu).getTypeA().toString());
                    labelCoupeView.setVisibility(View.VISIBLE);
                    coupeView.setText(((Autre) contenu).getCoupeA().toString());
                    labelMatiereView.setVisibility(View.VISIBLE);
                    matiereView.setText(((Autre) contenu).getMatiere().toString());
                    break;
                case "pantalon":
                    PantalonDAO p = new PantalonDAO(this);
                    contenu = p.findById(idDressing, id);
                    p.close();
                    // Affichage des infos
                    typeContenuView.setText("Vêtement");
                    typeView.setText(((Pantalon) contenu).getTypeP().toString());
                    labelCoupeView.setVisibility(View.VISIBLE);
                    coupeView.setText(((Pantalon) contenu).getCoupeP().toString());
                    labelMatiereView.setVisibility(View.VISIBLE);
                    matiereView.setText(((Pantalon) contenu).getMatiere().toString());
                    break;
            }
        }

        // Affichage des infos
        if (BitmapFactory.decodeFile(contenu.getImage())==null) {
            imageView.setImageResource(this.getResources().getIdentifier(contenu.getImage(), "drawable", this.getPackageName()));
        } else {
            imageView.setImageBitmap(BitmapFactory.decodeFile(contenu.getImage()));
        }

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        couleurView.setText(contenu.getCouleur().toString());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
