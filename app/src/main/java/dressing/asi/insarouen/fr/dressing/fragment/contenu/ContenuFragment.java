package dressing.asi.insarouen.fr.dressing.fragment.contenu;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.data.dao.ContenuDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;

import static android.R.id.toggle;
import static dressing.asi.insarouen.fr.dressing.R.layout.toolbar;

/**
 * Created by julie on 20/12/16.
 */

public class ContenuFragment extends Fragment {
    private static final String USER_ID = "user_id";
    private static final String TYPE_CONTENU = "type_contenu";
    private int userId;
    private String typeContenu;
    private RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;

    public ContenuFragment() {
    }


    public static ContenuFragment newInstance(int userId, String typeContenu) {
        ContenuFragment fragment = new ContenuFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        args.putString(TYPE_CONTENU, typeContenu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
            typeContenu = getArguments().getString(TYPE_CONTENU);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Récupération de l'identifiant du dressing de l'utilisateur courant
        UtilisateurDAO u = new UtilisateurDAO(getActivity());
        u.open();
        int idDressing = u.getIdDressing(userId);
        u.close();

        // Création de la liste contenant tous les vêtements du type voulu
        ArrayList<Contenu> listeContenus = new ArrayList<>();
        String errorMessage = "";
        switch (typeContenu) {
            case "Dressing Complet":
                ContenuDAO contenu = new ContenuDAO(getActivity());
                contenu.open();
                listeContenus = contenu.findAll(idDressing, getActivity());
                contenu.close();
                errorMessage = "Votre dressing est vide ! N'hésitez pas à ajouter des éléments";
                break;
            case "Sac":
                SacDAO sac = new SacDAO(getActivity());
                sac.open();
                listeContenus = sac.findAll(idDressing);
                sac.close();
                errorMessage = "Vous n'avez pas encore de sacs";
                break;
            case "Chaussure":
                ChaussuresDAO chaussures = new ChaussuresDAO(getActivity());
                chaussures.open();
                listeContenus = chaussures.findAll(idDressing);
                chaussures.close();
                errorMessage = "Vous n'avez pas encore de chaussures";
                break;
            case "Tee-shirt":
                HautDAO hautsT = new HautDAO(getActivity());
                hautsT.open();
                listeContenus = hautsT.findByType(idDressing, "Teeshirt");
                hautsT.close();
                errorMessage = "Vous n'avez pas encore de tee-shirts";
                break;
            case "Veste":
                HautDAO hautsV = new HautDAO(getActivity());
                hautsV.open();
                listeContenus = hautsV.findByType(idDressing, "Veste");
                hautsV.close();
                errorMessage = "Vous n'avez pas encore de vestes";
                break;
            case "Chemisier":
                HautDAO hautsC = new HautDAO(getActivity());
                hautsC.open();
                listeContenus = hautsC.findByType(idDressing, "Chemisier");
                hautsC.close();
                errorMessage = "Vous n'avez pas encore de chemisiers";
                break;
            case "Pull":
                HautDAO hautsP = new HautDAO(getActivity());
                hautsP.open();
                listeContenus = hautsP.findByType(idDressing, "Pull");
                hautsP.close();
                errorMessage = "Vous n'avez pas encore de pulls";
                break;
            case "Manteau":
                HautDAO hautsM = new HautDAO(getActivity());
                hautsM.open();
                listeContenus = hautsM.findByType(idDressing, "Manteau");
                hautsM.close();
                errorMessage = "Vous n'avez pas encore de manteaux";
                break;
            case "Pantalon":
                PantalonDAO pantalonsP = new PantalonDAO(getActivity());
                pantalonsP.open();
                listeContenus = pantalonsP.findByType(idDressing, "Pantalon");
                pantalonsP.close();
                errorMessage = "Vous n'avez pas encore de pantalons";
                break;
            case "Jogging":
                PantalonDAO pantalonsJ = new PantalonDAO(getActivity());
                pantalonsJ.open();
                listeContenus = pantalonsJ.findByType(idDressing, "Jogging");
                pantalonsJ.close();
                errorMessage = "Vous n'avez pas encore de joggings";
                break;
            case "Pantacourt":
                PantalonDAO pantalonsPc = new PantalonDAO(getActivity());
                pantalonsPc.open();
                listeContenus = pantalonsPc.findByType(idDressing, "Pantacourt");
                pantalonsPc.close();
                errorMessage = "Vous n'avez pas encore de pantacourts";
                break;
            case "Short":
                AutreDAO autresS = new AutreDAO(getActivity());
                autresS.open();
                listeContenus = autresS.findByType(idDressing, "Short");
                autresS.close();
                errorMessage = "Vous n'avez pas encore de shorts";
                break;
            case "Jupe":
                AutreDAO autresJ = new AutreDAO(getActivity());
                autresJ.open();
                listeContenus = autresJ.findByType(idDressing, "Jupe");
                autresJ.close();
                errorMessage = "Vous n'avez pas encore de jupes";
                break;
            case "Combinaison":
                AutreDAO autresC = new AutreDAO(getActivity());
                autresC.open();
                listeContenus = autresC.findByType(idDressing, "Combinaison");
                autresC.close();
                errorMessage = "Vous n'avez pas encore de combinaisons";
                break;
            case "Robe":
                AutreDAO autresR = new AutreDAO(getActivity());
                autresR.open();
                listeContenus = autresR.findByType(idDressing, "Robe");
                autresR.close();
                errorMessage = "Vous n'avez pas encore de robes";
                break;
        }

        if (listeContenus.size()!=0) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_contenu_list, container, false);

            // Récupérer le recycler
            mRecyclerView = (RecyclerView) view.findViewById(R.id.cardContenuList);
            GridLayoutManager gridLayoutManager;

            // 2 colones de Cardviews si mode portrait ou 3 si mode paysage
            gridLayoutManager = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? new GridLayoutManager(getActivity(), 2): new GridLayoutManager(getActivity(), 3);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(gridLayoutManager);

            // Création de l'adapter
            ContenuItemAdapter contenuItemAdapter = new ContenuItemAdapter(listeContenus, getActivity());
            mRecyclerView.setAdapter(contenuItemAdapter);

            return view;
        } else {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_liste_vide, container, false);
            // Set l'erreur
            TextView erreur = (TextView) view.findViewById(R.id.labelError);
            erreur.setText(errorMessage);
            return view;
        }
    }
}
