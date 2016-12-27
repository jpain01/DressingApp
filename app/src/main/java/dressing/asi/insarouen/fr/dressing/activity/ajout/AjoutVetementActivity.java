package dressing.asi.insarouen.fr.dressing.activity.ajout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;

/**
 * Created by julie on 23/12/16.
 */

public class AjoutVetementActivity extends AppCompatActivity {
    private Spinner coupeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ajout_vetement_activity);

        // Récupération des éléments
        Spinner typeSpinner = (Spinner) findViewById(R.id.typeVetement);
        coupeSpinner = (Spinner) findViewById(R.id.coupeVetement);
        Spinner matiereSpinner = (Spinner) findViewById(R.id.matiereVetement);
        Spinner couleurSpinner = (Spinner) findViewById(R.id.colorVetement);
        Button validationButton = (Button) findViewById(R.id.validerAjoutVetement);


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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//            overridePendingTransition(R.anim.stay, R.anim.slide_down); //Animation transition slide down
        }
        return super.onOptionsItemSelected(item);
    }
}
