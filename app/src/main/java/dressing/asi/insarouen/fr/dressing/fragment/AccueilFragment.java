package dressing.asi.insarouen.fr.dressing.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Utilisateur;
import dressing.asi.insarouen.fr.dressing.elements.utilisateur.Morphologie;
import dressing.asi.insarouen.fr.dressing.fragment.dressing.AccueilDressingFragment;

public class AccueilFragment extends Fragment {
    private static final String USER_ID = "user_id";
    private int userId;

    public AccueilFragment() {
    }

    public static AccueilFragment newInstance(int userId) {
        AccueilFragment fragment = new AccueilFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accueil, container, false);

        // Récupération des boutons
        Button dressingB = (Button) view.findViewById(R.id.dressing);
        Button corbeilleB = (Button) view.findViewById(R.id.corbeille);
        Button tenueB = (Button) view.findViewById(R.id.tenue);

        // Récupération des TextView à remplir
        TextView prenomV = (TextView) view.findViewById(R.id.userPrenom);
        TextView nomV = (TextView) view.findViewById(R.id.userNom);
        TextView ageV = (TextView) view.findViewById(R.id.ageUtilisateur);
        TextView couleurCheveuxV = (TextView) view.findViewById(R.id.couleurCheveuxUtilisateur);
        TextView tailleV = (TextView) view.findViewById(R.id.tailleUtilisateur);
        TextView couleurPrefereeV = (TextView) view.findViewById(R.id.couleurPrefereeUtilisateur);

        // Récupération de l'utilisateur courant
        UtilisateurDAO u = new UtilisateurDAO(getActivity());
        u.open();
        Utilisateur utilisateur = u.findUserById(userId);

        // Remplissage des TextView avec les infos de l'utilisateur
        prenomV.setText(utilisateur.getPrenom());
        nomV.setText(utilisateur.getNom());
        ageV.setText(Integer.toString(utilisateur.getAge()));
        tailleV.setText(Integer.toString(utilisateur.getTaille()));
        couleurCheveuxV.setText(utilisateur.getCouleurCheveux().toString());
        couleurPrefereeV.setText(utilisateur.getCouleurPreferee().toString());

        // On prend un conseil aléatoire en fonction de la morphologie de l'utilisateur
        TextView conseil = (TextView) view.findViewById(R.id.textAdvice);
        conseil.setText(getAdviceRandomly(utilisateur.getMorphologie()));

        // Redirection du bouton dressing
        // Definir un onItemClickListener sur la liste des items de mon drawer
        dressingB.setOnClickListener(new DressingClickListener());

        return view;
    }

    private String getAdviceRandomly(Morphologie m) {
        String[] conseils = null;
        switch (m) {
            case Huit:
                conseils = getResources().getStringArray(R.array.arrayAdviceHuit);
                break;
            case O:
                conseils = getResources().getStringArray(R.array.arrayAdviceO);
                break;
            case A:
                conseils = getResources().getStringArray(R.array.arrayAdviceA);
                break;
            case V:
                conseils = getResources().getStringArray(R.array.arrayAdviceV);
                break;
            case X:
                conseils = getResources().getStringArray(R.array.arrayAdviceX);
                break;
            case H:
                conseils = getResources().getStringArray(R.array.arrayAdviceH);
                break;
        }
        Random rand = new Random();
        int alea = rand.nextInt(conseils.length);
        return conseils[alea];
    }

    private class DressingClickListener implements View.OnClickListener {

        @Override
        public void onClick( View view) {
            AccueilDressingFragment dressingFragment = AccueilDressingFragment.newInstance(userId);
            // récupération du manager
            FragmentManager fragmentManager = getFragmentManager();
            // Commençons une transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Remplace notre vue par le fragment
            fragmentTransaction.replace(R.id.frame, dressingFragment);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
