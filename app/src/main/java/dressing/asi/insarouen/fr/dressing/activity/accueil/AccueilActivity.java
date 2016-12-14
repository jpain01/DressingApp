package dressing.asi.insarouen.fr.dressing.activity.accueil;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.utilisateur.ConnexionActivity;
import dressing.asi.insarouen.fr.dressing.drawer.DrawerAdapter;
import dressing.asi.insarouen.fr.dressing.drawer.NavigationDrawer;

/**
 * Created by julie on 05/12/16.
 */

public class AccueilActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrwDrawerToggle;
    private DrawerLayout mDrwDrawerLayout;
    private ListView mDrwDrawerList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.accueil);

        // Déclaration
        DrawerAdapter drwDrawerAdapter;

        // Creer un tableau de titres
        String[] drwMenuTitles = getResources().getStringArray(R.array.strArrDrawerList);
        // Creer un tableau d'icones
        TypedArray drwMenuIcons = getResources().obtainTypedArray(R.array.arrDrawerListIcon);
        // Instancie une liste d'objets pour le drawer
        ArrayList<Object> drwDrawerItems = new ArrayList<>();

        // Remplie la liste d'objet avec les titres et les icones
        for(int i = 0; i < drwMenuTitles.length; i++) {
            drwDrawerItems.add(new NavigationDrawer(drwMenuTitles[i], drwMenuIcons.getResourceId(i, -1)));
        }
        // Pour un typedArray, tjrs faire recycle()
        drwMenuIcons.recycle();

        // Créer une instance de notre adapteur
        drwDrawerAdapter = new DrawerAdapter(getApplicationContext(), drwDrawerItems);

        // La magie ! Construit la liste drawer avec get view en faisant le for tout seul
        mDrwDrawerList = (ListView) findViewById(R.id.drwLvDrawerList);
        if(mDrwDrawerList != null) {
            mDrwDrawerList.setAdapter(drwDrawerAdapter);
        }

        // Faire le joli bouton hamburger
//        mDrwDrawerLayout = (DrawerLayout) findViewById(R.id.drwLyDrawerLayout);
//        mDrwDrawerToggle = new ActionBarDrawerToggle(this, mDrwDrawerLayout, toolbar, R.string.strOpenDrawer , R.string.strCloseDrawer);
//        mDrwDrawerLayout.addDrawerListener(mDrwDrawerToggle);
//        mDrwDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_general, menu);
        return true;
    }

}
