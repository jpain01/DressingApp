package dressing.asi.insarouen.fr.dressing.activity.utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.accueil.AccueilActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Utilisateur;
import dressing.asi.insarouen.fr.dressing.elements.Couleur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.CouleurCheveux;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;

/**
 * Created by julie on 03/12/16.
 */

public class CreationActivity extends AppCompatActivity {
    private Button validerB = null ;
    private String nom = null;
    private String prenom = null;
    private int age = 0;
    private int taille = 0;
    private CouleurCheveux couleurCheveux = null;
    private Couleur couleurPreferee = null;
    private Morphologie morphologie = null;
    private String identifiant = null;
    private String mdp = null;
    private String confirmMdp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.creation_compte);

        validerB = (Button) findViewById(R.id.validerCreationCompte);
        validerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V){
                // Récupération du contenu des champs
                EditText nomV = (EditText) findViewById(R.id.nomUtilisateur);
                nom = nomV.getText().toString();
                EditText prenomV = (EditText) findViewById(R.id.prenomUtilisateur);
                prenom = prenomV.getText().toString();
                Spinner ageV = (Spinner) findViewById(R.id.ageUtilisateur);
                age = Integer.parseInt(ageV.getSelectedItem().toString());
                Spinner tailleV = (Spinner) findViewById(R.id.tailleUtilisateur);
                taille = Integer.parseInt(tailleV.getSelectedItem().toString());
                Spinner couleurCheveuxV = (Spinner) findViewById(R.id.couleurCheveuxUtilisateur);
                couleurCheveux = CouleurCheveux.get(couleurCheveuxV.getSelectedItem().toString());
                Spinner couleurPrefereeV = (Spinner) findViewById(R.id.couleurPrefereeUtilisateur);
                couleurPreferee = new Couleur(couleurPrefereeV.getSelectedItemPosition() + 1);
                Spinner morphologieV = (Spinner) findViewById(R.id.morphologieUtilisateur);
                morphologie = Morphologie.getfromInt(morphologieV.getSelectedItemPosition()+ 1);
                EditText identifiantV = (EditText) findViewById(R.id.idUtilisateur);
                identifiant = identifiantV.getText().toString();
                EditText mdpV = (EditText) findViewById(R.id.mdp);
                mdp = mdpV.getText().toString();
                EditText confirmMdpV = (EditText) findViewById(R.id.confirmerMdp);
                confirmMdp = confirmMdpV.getText().toString();


                // Test si les champs sont remplis
                if (nom.length() == 0 || prenom.length() == 0 || identifiant.length() == 0 || mdp.length() == 0 || confirmMdp.length() == 0) {
                    if (nom.length() == 0 ) {
                        nomV.setError("Veuillez remplir un nom");
                    }
                    if (prenom.length() == 0) {
                        prenomV.setError("Veillez remplir un prénom");
                    }
                    if (identifiant.length() == 0) {
                        identifiantV.setError("Veuillez remplir un identifiant");
                    }
                    if (mdp.length() == 0) {
                        mdpV.setError("veuillez remplir un mot de passe");
                    }
                    if (confirmMdp.length() == 0) {
                        mdpV.setError("veuillez confirmer le mot de passe");
                    }
                } else {
                    // Test si le mot de passe de confirmation est le même que le premier mot de passe
                    if(!mdp.equals(confirmMdp)) {
                        confirmMdpV.setError("Le mot de passe ne correspond pas");
                    } else {
                        // Test si l'identifiant est valide
                        UtilisateurDAO u = new UtilisateurDAO ( getApplicationContext () ) ;
                        u.open();
                        if (u.identifiantDejaPresent(identifiant)) {
                            identifiantV.setError("Cet identifiant est déjà utilisé");
                        } else {
                            // Insertion de de l'utilisateur dans la base de données
                            u.insert(new Utilisateur(0, nom, prenom, identifiant, mdp, age, taille, couleurPreferee, couleurCheveux, morphologie ));

                            // Redirection vers l'accueil
                            Intent intent = new Intent(CreationActivity.this,AccueilActivity.class);
                            intent.putExtra("ID",u.isCorrectUser(mdp, identifiant));
                            Toast.makeText(getApplicationContext(), "Compte créé avec succès :)", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            nomV.setError(null);
                            prenomV.setError(null);
                            identifiantV.setError(null);
                            mdpV.setError(null);
                            confirmMdpV.setError(null);
                        }
                        u.close();
                    }
                }
            }
        });

    }
}
