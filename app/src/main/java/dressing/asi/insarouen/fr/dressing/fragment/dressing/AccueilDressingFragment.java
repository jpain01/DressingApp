package dressing.asi.insarouen.fr.dressing.fragment.dressing;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.ajout.AjoutChaussuresActivity;
import dressing.asi.insarouen.fr.dressing.activity.ajout.AjoutSacActivity;
import dressing.asi.insarouen.fr.dressing.activity.ajout.AjoutVetementActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.UtilisateurDAO;

public class AccueilDressingFragment extends Fragment {
    private static final String USER_ID = "user_id";
    private static final String DRESSING_ID = "dressing_id";
    private int userId;
    private int dressingId;
    private RecyclerView mRecyclerView;

    public AccueilDressingFragment() {
    }


    public static AccueilDressingFragment newInstance(int userId) {
        AccueilDressingFragment fragment = new AccueilDressingFragment();
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
        // Récupération de l'identifiant du dressing de l'utilisateur courant
        UtilisateurDAO u = new UtilisateurDAO(getActivity());
        u.open();
        dressingId = u.getIdDressing(userId);
        u.close();

        // Redéfinir le titre de la toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Dressing");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dressing_list, container, false);

        // Récupérer le recycler
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cardList);
        GridLayoutManager gridLayoutManager;

        // 2 colones de Cardviews si mode portrait ou 3 si mode paysage
        gridLayoutManager = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ? new GridLayoutManager(getActivity(), 2): new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        // Création de la liste
        String[] arrListDressing = getResources().getStringArray(R.array.arrListDressing);
        String[] arrListIcon = getResources().getStringArray(R.array.arrIconDressing);
        ArrayList<DressingItem> items = new ArrayList<>();

        for(int i = 0; i < arrListDressing.length; i++)
        {
            items.add(new DressingItem(arrListDressing[i], arrListIcon[i], userId));
        }

        // Création de l'adapter
        DressingItemAdapter dressingItemAdapter = new DressingItemAdapter(items, getActivity());
        mRecyclerView.setAdapter(dressingItemAdapter);

        // Action d'un bouton d'ajout
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.ajouterContenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), v);
                getActivity().getMenuInflater().inflate(R.menu.menu_ajout_contenu, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.sac:
                                Intent addSacIntent = new Intent(getActivity(), AjoutSacActivity.class);
                                addSacIntent.putExtra(DRESSING_ID, dressingId);
                                startActivity(addSacIntent);
                                return true;
                            case R.id.chaussures:
                                Intent addChaussuresIntent = new Intent(getActivity(), AjoutChaussuresActivity.class);
                                addChaussuresIntent.putExtra(DRESSING_ID, dressingId);
                                startActivity(addChaussuresIntent);
                                return true;
                            case R.id.vetement:
                                Intent addVetementIntent = new Intent(getActivity(), AjoutVetementActivity.class);
                                addVetementIntent.putExtra(DRESSING_ID, dressingId);
                                startActivity(addVetementIntent);
                                return true;
                            default:
                                return true;
                        }
                    }
                });

            }
        });

        return view;
    }

}
