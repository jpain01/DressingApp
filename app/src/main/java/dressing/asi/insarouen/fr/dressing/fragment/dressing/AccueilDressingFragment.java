package dressing.asi.insarouen.fr.dressing.fragment.dressing;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;

public class AccueilDressingFragment extends Fragment {
    private static final String USER_ID = "user_id";
    private int userId;
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

        return view;
    }

}
