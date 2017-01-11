package dressing.asi.insarouen.fr.dressing.activity.contenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.notice.NoticeActivity;
import dressing.asi.insarouen.fr.dressing.activity.utilisateur.ConnexionActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;

import static dressing.asi.insarouen.fr.dressing.activity.consultation.ConsultationActivity.RESULT_CODE_DELETE_OK;
import static dressing.asi.insarouen.fr.dressing.activity.contenu.ContenuItemAdapter.POSITION_LIST;

/**
 * Created by julie on 11/01/17.
 */

public class ContenuActivity extends AppCompatActivity {
    private static final String USER_ID = "user_id";
    private static final String TYPE_CONTENU = "type_contenu";
    private int userId;
    private int idDressing;
    private String typeContenu;
    private RecyclerView mRecyclerView;
    private String errorMessage;
    private ArrayList<Contenu> listeContenus;
    private TextView erreur;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.contenu_list_activity);

        // Coucou je veux que mon action bar soit celle-ci :)
        final Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(typeContenu);

        // Ma petite flèche :D
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }

        // Récupération des entrées
        Intent intent = getIntent();
        userId = intent.getIntExtra(USER_ID, 0);
        typeContenu = intent.getStringExtra(TYPE_CONTENU);

        // Récupération de l'identifiant du dressing de l'utilisateur courant
        UtilisateurDAO u = new UtilisateurDAO(this);
        u.open();
        idDressing = u.getIdDressing(userId);
        u.close();

        // Création de la liste contenant tous les vêtements du type voulu
        listeContenus = new ArrayList<>();
        setContenu();

        // Récupérer le recycler
        mRecyclerView = (RecyclerView) findViewById(R.id.cardContenuList);
        GridLayoutManager gridLayoutManager;

        // 2 colones de Cardviews si mode portrait ou 3 si mode paysage
        gridLayoutManager = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? new GridLayoutManager(this, 2) : new GridLayoutManager(this, 3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // Création de l'adapter
        ContenuItemAdapter contenuItemAdapter = new ContenuItemAdapter(listeContenus, this, mRecyclerView);
        mRecyclerView.setAdapter(contenuItemAdapter);

        // Erreur
        erreur = (TextView) findViewById(R.id.labelError);
        if (listeContenus.size()==0) {
            erreur.setVisibility(View.VISIBLE);
            erreur.setText(errorMessage);
        }
    }

    public void setContenu() {
        switch (typeContenu) {
            case "Dressing Complet":
                ContenuDAO contenu = new ContenuDAO(this);
                contenu.open();
                listeContenus = contenu.findAll(idDressing, this);
                contenu.close();
                errorMessage = "Votre dressing est vide ! N'hésitez pas à ajouter des éléments";
                break;
            case "Sac":
                SacDAO sac = new SacDAO(this);
                sac.open();
                listeContenus = sac.findAll(idDressing);
                sac.close();
                errorMessage = "Vous n'avez pas encore de sacs";
                break;
            case "Chaussure":
                ChaussuresDAO chaussures = new ChaussuresDAO(this);
                chaussures.open();
                listeContenus = chaussures.findAll(idDressing);
                chaussures.close();
                errorMessage = "Vous n'avez pas encore de chaussures";
                break;
            case "Tee-shirt":
                HautDAO hautsT = new HautDAO(this);
                hautsT.open();
                listeContenus = hautsT.findByType(idDressing, "Teeshirt");
                hautsT.close();
                errorMessage = "Vous n'avez pas encore de tee-shirts";
                break;
            case "Veste":
                HautDAO hautsV = new HautDAO(this);
                hautsV.open();
                listeContenus = hautsV.findByType(idDressing, "Veste");
                hautsV.close();
                errorMessage = "Vous n'avez pas encore de vestes";
                break;
            case "Chemisier":
                HautDAO hautsC = new HautDAO(this);
                hautsC.open();
                listeContenus = hautsC.findByType(idDressing, "Chemisier");
                hautsC.close();
                errorMessage = "Vous n'avez pas encore de chemisiers";
                break;
            case "Pull":
                HautDAO hautsP = new HautDAO(this);
                hautsP.open();
                listeContenus = hautsP.findByType(idDressing, "Pull");
                hautsP.close();
                errorMessage = "Vous n'avez pas encore de pulls";
                break;
            case "Manteau":
                HautDAO hautsM = new HautDAO(this);
                hautsM.open();
                listeContenus = hautsM.findByType(idDressing, "Manteau");
                hautsM.close();
                errorMessage = "Vous n'avez pas encore de manteaux";
                break;
            case "Pantalon":
                PantalonDAO pantalonsP = new PantalonDAO(this);
                pantalonsP.open();
                listeContenus = pantalonsP.findByType(idDressing, "Pantalon");
                pantalonsP.close();
                errorMessage = "Vous n'avez pas encore de pantalons";
                break;
            case "Jogging":
                PantalonDAO pantalonsJ = new PantalonDAO(this);
                pantalonsJ.open();
                listeContenus = pantalonsJ.findByType(idDressing, "Jogging");
                pantalonsJ.close();
                errorMessage = "Vous n'avez pas encore de joggings";
                break;
            case "Pantacourt":
                PantalonDAO pantalonsPc = new PantalonDAO(this);
                pantalonsPc.open();
                listeContenus = pantalonsPc.findByType(idDressing, "Pantacourt");
                pantalonsPc.close();
                errorMessage = "Vous n'avez pas encore de pantacourts";
                break;
            case "Short":
                AutreDAO autresS = new AutreDAO(this);
                autresS.open();
                listeContenus = autresS.findByType(idDressing, "Short");
                autresS.close();
                errorMessage = "Vous n'avez pas encore de shorts";
                break;
            case "Jupe":
                AutreDAO autresJ = new AutreDAO(this);
                autresJ.open();
                listeContenus = autresJ.findByType(idDressing, "Jupe");
                autresJ.close();
                errorMessage = "Vous n'avez pas encore de jupes";
                break;
            case "Combinaison":
                AutreDAO autresC = new AutreDAO(this);
                autresC.open();
                listeContenus = autresC.findByType(idDressing, "Combinaison");
                autresC.close();
                errorMessage = "Vous n'avez pas encore de combinaisons";
                break;
            case "Robe":
                AutreDAO autresR = new AutreDAO(this);
                autresR.open();
                listeContenus = autresR.findByType(idDressing, "Robe");
                autresR.close();
                errorMessage = "Vous n'avez pas encore de robes";
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ContenuItemAdapter.REQUEST_CODE_CONSULT_CONTENU){
            if(resultCode == RESULT_CODE_DELETE_OK) {
                Intent intent = getIntent();
                int position = intent.getIntExtra(POSITION_LIST, 0);
                if (position!=0) {
                    listeContenus.remove(position);
                } else {
                    setContenu();
                }
                ContenuItemAdapter contenuItemAdapter = new ContenuItemAdapter(listeContenus, this, mRecyclerView);
                mRecyclerView.setAdapter(contenuItemAdapter);

                if (listeContenus.size()==0) {
                    erreur.setVisibility(View.VISIBLE);
                    erreur.setText(errorMessage);
                }
            }
        }
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
                                Intent disconectIntent = new Intent(ContenuActivity.this, ConnexionActivity.class);
                                disconectIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
                Intent settingsIntent = new Intent(ContenuActivity.this, NoticeActivity.class);
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
        super.onBackPressed();
        // Animation de folie :D
        overridePendingTransition(R.anim.stay, android.R.anim.slide_out_right);
    }
}
