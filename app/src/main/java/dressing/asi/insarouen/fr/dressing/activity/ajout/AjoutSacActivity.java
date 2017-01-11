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
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.sac.TypeSac;

/**
 * Created by julie on 23/12/16.
 */

public class AjoutSacActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private static final String DRESSING_ID = "dressing_id";
    public static final String CONTENU_ID = "id";
    public static final String CONTENU_TYPE = "type";
    private int dressingId;
    private String pathImage;
    private Couleur couleur;
    private TypeSac typeS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ajout_sac_activity);

        // Récupération des éléments
        Button loadButton = (Button) findViewById(R.id.loadPicture);
        Button validerButton = (Button) findViewById(R.id.validerAjoutSac);
        dressingId = getIntent().getIntExtra(DRESSING_ID, 0);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.ajoutSac);
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

        validerButton.setOnClickListener(new CreationSacListener());
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

    private class CreationSacListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Récupération des éléments
            Button loadButton = (Button) findViewById(R.id.loadPicture);
            Spinner typeSpinner = (Spinner) findViewById(R.id.typeSac);
            typeS = TypeSac.getfromInt(typeSpinner.getSelectedItemPosition()+ 1);
            Spinner couleurSpinner = (Spinner) findViewById(R.id.colorSac);
            couleur = new Couleur(couleurSpinner.getSelectedItemPosition() + 1);

            //Vérification infos
            if ( pathImage==null ) {
                loadButton.setError(getString(R.string.error));
            } else {
                // Creation de l'objet sac
                Sac s = new Sac(couleur, pathImage, dressingId, 0, typeS);

                // Insertion du sac en BD
                SacDAO sac = new SacDAO(getApplicationContext());
                sac.open();
                sac.insert(s);
                sac.close();

                Intent intent = new Intent(getApplicationContext(), ConsultationActivity.class);
                intent.putExtra(CONTENU_ID, s.getIdObjet());
                intent.putExtra(DRESSING_ID, s.getIdDressing());
                intent.putExtra(CONTENU_TYPE, "sac");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }

        }
    }
}
