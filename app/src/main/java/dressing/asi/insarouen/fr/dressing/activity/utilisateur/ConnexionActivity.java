package dressing.asi.insarouen.fr.dressing.activity.utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.accueil.AccueilActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;

import static android.os.Build.ID;

/**
 * Created by julie on 03/12/16.
 */

public class ConnexionActivity extends AppCompatActivity {
    private Button connexionB = null ;
    private Button creationCompteB = null ;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.connexion);

        connexionB = (Button) findViewById(R.id.connexionButton);
        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                // Récupération du contenu des champs
                EditText id = (EditText) findViewById(R.id.identifiant);
                String chaineId = id.getText().toString();
                EditText psw = (EditText) findViewById(R.id.password);
                String chainePswd = psw.getText().toString();
                // Test si les champs sont remplis
                if (chaineId.length() == 0 || chainePswd.length() ==0) {
                    if (chaineId.length() == 0 ) {
                        id.setError("veuillez remplir un identifiant");
                    }
                    if (chainePswd.length() == 0) {
                        psw.setError("veuillez remplir un mot de passe");
                    }
                } else {
                    // Test si l'identifiant et le mot de passe sont correctes
                    UtilisateurDAO u = new UtilisateurDAO ( getApplicationContext () ) ;
                    u.open();
                    if (u.isCorrectUser(chainePswd, chaineId) != 0) {
                        // Redirection vers l'accueil
                        Intent intent = new Intent(ConnexionActivity.this,AccueilActivity.class);
                        intent.putExtra("ID",u.isCorrectUser(chainePswd, chaineId));
                        startActivity(intent);
                        id.setError(null);
                        psw.setError(null);
                    } else {
                        id.setError("Identifiant ou mot de passe incorrect");
                        psw.setError("Identifiant ou mot de passe incorrect");
                    }
                    u.close();
                }
            }
        });

        creationCompteB = (Button) findViewById(R.id.creationCompteButton);
        creationCompteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                // Redirection vers le formulaire de création de compte
                Intent intent = new Intent(ConnexionActivity.this,CreationActivity.class);
                startActivity(intent);
            }
        });
    }

}
