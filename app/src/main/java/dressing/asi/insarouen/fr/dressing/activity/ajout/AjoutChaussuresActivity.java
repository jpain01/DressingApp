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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.consultation.ConsultationActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Chaussures;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.chaussures.TypeChaussures;

/**
 * Created by julie on 23/12/16.
 */

public class AjoutChaussuresActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private static final String DRESSING_ID = "dressing_id";
    public static final String CONTENU_ID = "id";
    public static final String CONTENU_TYPE = "type";
    private int dressingId;
    private String pathImage;
    private Couleur couleur;
    private TypeChaussures typeC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ajout_chaussures_activity);

        // Récupération des éléments
        Button loadButton = (Button) findViewById(R.id.loadPicture);
        Button validerButton = (Button) findViewById(R.id.validerAjoutChaussures);
        dressingId = getIntent().getIntExtra(DRESSING_ID, 0);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.ajoutChaussures);
        }

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

        // Attention :
        // normalement gérer les permissions grâce à ce tuto : http://www.captechconsulting.com/blogs/runtime-permissions-best-practices-and-how-to-gracefully-handle-permission-removal
        // Mais là pas beaucoup de temps alors changer la version du target sdk version dans le gradle de 23 à 22

        validerButton.setOnClickListener(new AjoutChaussuresActivity.CreationChaussuresListener());
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
            imageView.setImageBitmap(BitmapFactory.decodeFile(pathImage));
        }
    }

    private class CreationChaussuresListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Récupération des
            Button loadButton = (Button) findViewById(R.id.loadPicture);
            Spinner typeSpinner = (Spinner) findViewById(R.id.typeChaussures);
            typeC = TypeChaussures.getfromInt(typeSpinner.getSelectedItemPosition()+ 1);
            Spinner couleurSpinner = (Spinner) findViewById(R.id.colorChaussures);
            couleur = new Couleur(couleurSpinner.getSelectedItemPosition() + 1);

            //Vérification infos
            if ( pathImage==null ) {
                loadButton.setError(getString(R.string.error));
            } else {
                // Creation de l'objet sac
                Chaussures c = new Chaussures(couleur, pathImage, dressingId, 0, typeC);

                // Insertion du sac en BD
                ChaussuresDAO chaussures = new ChaussuresDAO(getApplicationContext());
                chaussures.open();
                chaussures.insert(c);
                chaussures.close();

                Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                intent.putExtra(CONTENU_ID, c.getIdObjet());
                intent.putExtra(DRESSING_ID, c.getIdDressing());
                intent.putExtra(CONTENU_TYPE, "chaussures");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }
    }
}
