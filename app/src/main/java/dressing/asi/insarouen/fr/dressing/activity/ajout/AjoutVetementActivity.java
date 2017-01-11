package dressing.asi.insarouen.fr.dressing.activity.ajout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.consultation.ConsultationActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Autre;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.vetement.Matiere;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.CoupeAutre;
import dressing.asi.insarouen.fr.dressing.elements.vetement.autre.TypeAutre;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.CoupeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.haut.TypeHaut;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.CoupePantalon;
import dressing.asi.insarouen.fr.dressing.elements.vetement.pantalon.TypePantalon;

/**
 * Created by julie on 23/12/16.
 */

public class AjoutVetementActivity extends AppCompatActivity {
    private Spinner coupeSpinner;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final String DRESSING_ID = "dressing_id";
    public static final String CONTENU_TYPE = "type";
    public static final String CONTENU_ID = "id";
    private int dressingId;
    private String pathImage;
    private Couleur couleur;
    private Matiere matiere;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ajout_vetement_activity);

        // Récupération des éléments
        Spinner typeSpinner = (Spinner) findViewById(R.id.typeVetement);
        coupeSpinner = (Spinner) findViewById(R.id.coupeVetement);
        Spinner matiereSpinner = (Spinner) findViewById(R.id.matiereVetement);
        Spinner couleurSpinner = (Spinner) findViewById(R.id.colorVetement);
        Button validerButton = (Button) findViewById(R.id.validerAjoutVetement);
        Button loadButton = (Button) findViewById(R.id.loadPicture);
        dressingId = getIntent().getIntExtra(DRESSING_ID, 0);


        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.ajoutVetement);
        }

        // Set dynamiquement le contenu de coupeSpinner
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                ArrayList<String> contenu = new ArrayList<String>();
                switch(parentView.getItemAtPosition(position).toString()) {
                    case "tee-shirt":
                    case "chemisier":
                    case "pull":
                    case "veste":
                    case "manteau":
                        contenu.clear();
                        contenu.add("cintré");
                        contenu.add("droit");
                        contenu.add("large");
                        break;
                    case "jupe":
                    case "short":
                    case "robe":
                    case "combinaison":
                        contenu.clear();
                        contenu.add("court");
                        contenu.add("long");
                        break;
                    default:
                        contenu.clear();
                        contenu.add("slim");
                        contenu.add("droit");
                        contenu.add("évasé");
                        contenu.add("baggy");
                        break;
                }
                final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_spinner, contenu);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                coupeSpinner.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        // Load la photo
        loadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        // Validation
        validerButton.setOnClickListener(new CreationVetementListener());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            pathImage = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(BitmapFactory.decodeFile(pathImage));
        }
    }

    private class CreationVetementListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Récupération des éléments
            Spinner typeSpinner = (Spinner) findViewById(R.id.typeVetement);
            type = typeSpinner.getSelectedItem().toString();
            coupeSpinner = (Spinner) findViewById(R.id.coupeVetement);
            Spinner matiereSpinner = (Spinner) findViewById(R.id.matiereVetement);
            matiere = Matiere.getfromInt(matiereSpinner.getSelectedItemPosition()+ 1);
            Spinner couleurSpinner = (Spinner) findViewById(R.id.colorVetement);
            couleur = new Couleur(couleurSpinner.getSelectedItemPosition() + 1);
            Button loadButton = (Button) findViewById(R.id.loadPicture);


            //Vérification infos
            if ( pathImage==null ) {
                loadButton.setError(getString(R.string.error));
            } else {
                // Le type de vêtement va déterminer si c'est un haut, pantalon ou autre :
                switch(type) {
                    case "tee-shirt":
                    case "chemisier":
                    case "pull":
                    case "veste":
                    case "manteau":
                        // Creation de l'objet haut
                        Haut h = new Haut(couleur, pathImage, dressingId, 0, matiere, false, TypeHaut.getfromInt(typeSpinner.getSelectedItemPosition()+1), CoupeHaut.getfromInt(coupeSpinner.getSelectedItemPosition()+1), 0, null);

                        // Insertion du sac en BD
                        HautDAO haut = new HautDAO(getApplicationContext());
                        haut.open();
                        haut.insert(h);
                        haut.close();

                        Intent intentH = new Intent(getApplicationContext(), ConsultationActivity.class);
                        intentH.putExtra(CONTENU_ID, h.getIdObjet());
                        intentH.putExtra(DRESSING_ID, h.getIdDressing());
                        intentH.putExtra(CONTENU_TYPE, "haut");
                        startActivity(intentH);
                        finish();
                        break;
                    case "jupe":
                    case "short":
                    case "robe":
                    case "combinaison":
                        // Creation de l'objet autre
                        Autre a = new Autre(couleur, pathImage, dressingId, 0, matiere, false, TypeAutre.getfromInt(typeSpinner.getSelectedItemPosition()-4), CoupeAutre.getfromInt(coupeSpinner.getSelectedItemPosition()+1), 0, null);

                        // Insertion du sac en BD
                        AutreDAO autre = new AutreDAO(getApplicationContext());
                        autre.open();
                        autre.insert(a);
                        autre.close();

                        Intent intentA = new Intent(getApplicationContext(), ConsultationActivity.class);
                        intentA.putExtra(CONTENU_ID, a.getIdObjet());
                        intentA.putExtra(DRESSING_ID, a.getIdDressing());
                        intentA.putExtra(CONTENU_TYPE, "autre");
                        startActivity(intentA);
                        finish();
                        break;
                    default:
                        // Creation de l'objet Pantalon
                        Pantalon p = new Pantalon(couleur, pathImage, dressingId, 0, matiere, false, TypePantalon.getfromInt(typeSpinner.getSelectedItemPosition()-8), CoupePantalon.getfromInt(coupeSpinner.getSelectedItemPosition()+1), 0, null);

                        // Insertion du sac en BD
                        PantalonDAO pantalon = new PantalonDAO(getApplicationContext());
                        pantalon.open();
                        pantalon.insert(p);
                        pantalon.close();

                        Intent intentP = new Intent(getApplicationContext(), ConsultationActivity.class);
                        intentP.putExtra(CONTENU_ID, p.getIdObjet());
                        intentP.putExtra(DRESSING_ID, p.getIdDressing());
                        startActivity(intentP);
                        finish();
                        break;
                }
            }
        }
    }
}
