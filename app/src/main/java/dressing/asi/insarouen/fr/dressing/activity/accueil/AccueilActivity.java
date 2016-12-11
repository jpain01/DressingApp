package dressing.asi.insarouen.fr.dressing.activity.accueil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.utilisateur.ConnexionActivity;

/**
 * Created by julie on 05/12/16.
 */

public class AccueilActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.accueil);

        // Récupération de l'intent
        Intent i = getIntent();
        int id = i.getIntExtra("ID", 0);

        TextView test = (TextView) findViewById(R.id.test);
        test.setText("Coucou utilisateur n° "+id);
    }

}
