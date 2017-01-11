package dressing.asi.insarouen.fr.dressing.activity.consultation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.notice.NoticeActivity;
import dressing.asi.insarouen.fr.dressing.activity.utilisateur.ConnexionActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.VetementDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Chaussures;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Vetement;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;

import static dressing.asi.insarouen.fr.dressing.activity.contenu.ContenuItemAdapter.POSITION_LIST;

/**
 * Created by julie on 28/12/16.
 */

public class ConsultationActivity extends AppCompatActivity {
    private static final int RESULT_CODE_CANCEL = 1;
    public static final int RESULT_CODE_DELETE_OK = 2;
    public static final String CONTENU_ID = "id";
    public static final String CONTENU_TYPE = "type";
    public static final String DRESSING_ID = "dressing_id";
    private int id;
    private String type;
    private int idDressing;
    private Contenu contenu;
    private int positionItem;
    private AppCompatActivity activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.consultation_activity);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        // Récupération des éléments
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView couleurView = (TextView) findViewById(R.id.couleur);
        TextView typeContenuView = (TextView) findViewById(R.id.typeContenu);
        TextView typeView = (TextView) findViewById(R.id.type);
        TextView labelCoupeView = (TextView) findViewById(R.id.labelCoupe);
        TextView coupeView = (TextView) findViewById(R.id.coupe);
        TextView labelMatiereView = (TextView) findViewById(R.id.labelMatiere);
        TextView matiereView = (TextView) findViewById(R.id.matiere);
        FloatingActionButton suppFAB = (FloatingActionButton) findViewById(R.id.suppContenu);
        final FloatingActionButton corbeilleFAB = (FloatingActionButton) findViewById(R.id.corbeilleContenu);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        // Ma petite flèche :D
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        // Récupération de l'identifiant et de l'objet associé
        id = getIntent().getIntExtra(CONTENU_ID, 0);
        type = getIntent().getStringExtra(CONTENU_TYPE);
        idDressing = getIntent().getIntExtra(DRESSING_ID, 0);
        positionItem = getIntent().getIntExtra(POSITION_LIST, 0);
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
                    corbeilleFAB.setVisibility(View.VISIBLE);
                    if (((Vetement)contenu).isSale()){
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    } else {
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white_empty));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    }
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
                    corbeilleFAB.setVisibility(View.VISIBLE);
                    if (((Vetement)contenu).isSale()){
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    } else {
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white_empty));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    }
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
                    corbeilleFAB.setVisibility(View.VISIBLE);
                    if (((Vetement)contenu).isSale()){
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    } else {
                        corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.corbeille_white_empty));
                        corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorFushia)));
                    }
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

        corbeilleFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                ((Vetement)contenu).setSale(!((Vetement)contenu).isSale());
                VetementDAO v = new VetementDAO(activity);
                v.updtadeSalePropre((Vetement)contenu);
                Log.v("sale : ", ((Vetement)contenu).isSale() ? "1" : "0");
                if (((Vetement)contenu).isSale()){
                    corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.corbeille_white));
                    corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorFushia)));
                    Toast.makeText(getApplicationContext(), "Votre vêtement a été ajouté à la corbeille à linge", Toast.LENGTH_LONG).show();
                } else {
                    corbeilleFAB.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.corbeille_white_empty));
                    corbeilleFAB.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorFushia)));
                    Toast.makeText(getApplicationContext(), "Votre vêtement a été retiré de la corbeille à linge", Toast.LENGTH_LONG).show();
                }
            }
        });

        suppFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                if (contenu instanceof Sac) {
                                    SacDAO sac = new SacDAO(getApplication());
                                    sac.open();
                                    sac.delete(contenu.getIdObjet());
                                    sac.close();
                                } else if (contenu instanceof Chaussures) {
                                    ChaussuresDAO chaussures = new ChaussuresDAO(getApplication());
                                    chaussures.open();
                                    chaussures.delete(contenu.getIdObjet());
                                    chaussures.close();
                                } else if (contenu instanceof Haut) {
                                    HautDAO haut = new HautDAO(getApplication());
                                    haut.open();
                                    haut.delete(contenu.getIdObjet());
                                    haut.close();
                                } else if (contenu instanceof Pantalon) {
                                    PantalonDAO pantalon = new PantalonDAO(getApplication());
                                    pantalon.open();
                                    pantalon.delete(contenu.getIdObjet());
                                    pantalon.close();
                                } else {
                                    AutreDAO autre = new AutreDAO(getApplication());
                                    autre.open();
                                    autre.delete(contenu.getIdObjet());
                                    autre.close();
                                }

                                Intent intent = new Intent();
                                intent.putExtra(POSITION_LIST, positionItem);
                                setResult(RESULT_CODE_DELETE_OK, intent);
                                activity.finish();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Êtes vous sûr(e) de vouloir supprimer ce vêtement ?").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.deconnexion:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent disconectIntent = new Intent(ConsultationActivity.this, ConnexionActivity.class);
                                startActivity(disconectIntent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Êtes vous sûr(e) de vouloir quitter ?").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();

                return true;
            case R.id.notice:
                Intent settingsIntent = new Intent(ConsultationActivity.this, NoticeActivity.class);
                startActivity(settingsIntent);
                overridePendingTransition(R.anim.slide_up, R.anim.stay); //Animation transition slide down
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CODE_CANCEL, intent);
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
