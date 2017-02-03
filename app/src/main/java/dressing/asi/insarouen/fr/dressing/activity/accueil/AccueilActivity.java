package dressing.asi.insarouen.fr.dressing.activity.accueil;

import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.notice.NoticeActivity;
import dressing.asi.insarouen.fr.dressing.activity.utilisateur.ConnexionActivity;
import dressing.asi.insarouen.fr.dressing.drawer.DrawerAdapter;
import dressing.asi.insarouen.fr.dressing.drawer.NavigationDrawer;
import dressing.asi.insarouen.fr.dressing.fragment.AccueilFragment;
import dressing.asi.insarouen.fr.dressing.fragment.dressing.AccueilDressingFragment;

/**
 * Created by julie on 05/12/16.
 */

public class AccueilActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mDrwDrawerToggle;
    private DrawerLayout mDrwDrawerLayout;
    private ListView mDrwDrawerList;
    private int id;
    public static final int MENU_ACCUEIL = 1;
    public static final int MENU_DRESSING = 2;
    public static final int MENU_CORBEILLE = 3;
    public static final int MENU_TENUE = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.accueil);

        // Déclaration
        final Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);
        DrawerAdapter drwDrawerAdapter;
        AccueilFragment fragment;
        View header = getLayoutInflater().inflate(R.layout.drawer_header, null);

        // Recupération de l'indentifiant de l'utilisateur courant
        Intent intent = getIntent();
        id = intent.getIntExtra(ConnexionActivity.ID, 0);

        // On instancie le fragment
        fragment = AccueilFragment.newInstance(id);

        // Coucou je veux que mon action bar soit celle-ci :)
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.accueil);

        // Creer un tableau de titres
        String[] drwMenuTitles = getResources().getStringArray(R.array.strArrDrawerList);
        // Creer un tableau d'icones
        TypedArray drwMenuIcons = getResources().obtainTypedArray(R.array.arrDrawerListIcon);
        // Instancie une liste d'objets pour le drawer
        ArrayList<Object> drwDrawerItems = new ArrayList<>();

        // Remplie la liste d'objet avec les titres et les icones
        for (int i = 0; i < drwMenuTitles.length; i++) {
            drwDrawerItems.add(new NavigationDrawer(drwMenuTitles[i], drwMenuIcons.getResourceId(i, -1)));
        }
        // Pour un typedArray, tjrs faire recycle()
        drwMenuIcons.recycle();

        // Créer une instance de notre adapteur
        drwDrawerAdapter = new DrawerAdapter(getApplicationContext(), drwDrawerItems);

        // La magie ! Construit la liste drawer avec get view en faisant le for tout seul
        mDrwDrawerList = (ListView) findViewById(R.id.drwLvDrawerList);
        if (mDrwDrawerList != null) {
            mDrwDrawerList.addHeaderView(header);
            mDrwDrawerList.setAdapter(drwDrawerAdapter);
        }

        // Faire le joli bouton hamburger
        mDrwDrawerLayout = (DrawerLayout) findViewById(R.id.drwLyDrawerLayout);
        mDrwDrawerToggle = new ActionBarDrawerToggle(this, mDrwDrawerLayout, toolbar, R.string.strOpenDrawer, R.string.strCloseDrawer);
        mDrwDrawerLayout.addDrawerListener(mDrwDrawerToggle);
        mDrwDrawerToggle.syncState();

        // Definir un onItemClickListener sur la liste des items de mon drawer
        mDrwDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Mettre l'accueil par défaut
        FragmentManager fragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
            mDrwDrawerList.setItemChecked(MENU_ACCUEIL, true);
        }

        /*Listener sur le backstack pour mettre à jour l'item selectionner dans le drawer
        Quand un fragment est ajouté au backstack et donc que le backstack change, on met à jour l'item*/
        fragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        //Si y'a encore qqch dans le backstack sinon ça veut dire qu'on est revenu sur l'accueil
                        if(getFragmentManager().getBackStackEntryCount() > 0) {
                            String position = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName();
                            if(position != null)
                                mDrwDrawerList.setItemChecked(Integer.parseInt(position), true);

                        } else if (getFragmentManager().getBackStackEntryCount() == 0) {
                            mDrwDrawerList.setItemChecked(MENU_ACCUEIL, true);

                            //Pour avoir le burger
                            if(getSupportActionBar() != null)
                                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                                getSupportActionBar().setTitle(R.string.accueil);
                            mDrwDrawerToggle.syncState();
                            if(toolbar != null)
                                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mDrwDrawerLayout.openDrawer(mDrwDrawerList);
                                    }
                                });
                        }
                    }
                }
        );
    }

    // Classe interne pour définir le onItemClickListener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        private void selectItem(int position) {
            AccueilFragment fragment = null;
            AccueilDressingFragment dressingFragment = null;

            switch (position) {
                case MENU_ACCUEIL:
                    fragment = AccueilFragment.newInstance(id);
                    break;
                case MENU_DRESSING:
                    dressingFragment = AccueilDressingFragment.newInstance(id);
                    break;
                case MENU_CORBEILLE:
                    fragment = AccueilFragment.newInstance(id);
                    break;
                case MENU_TENUE:
                    Toast.makeText(getApplicationContext(), "Medias", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

            // récupération du manager
            FragmentManager fragmentManager = getFragmentManager();
            // Commençons une transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Remplace notre vue par le fragment qu'on veut
            if (fragment != null)
                fragmentTransaction.replace(R.id.frame, fragment);

            if (dressingFragment != null)
                fragmentTransaction.replace(R.id.frame, dressingFragment);

            fragmentTransaction.addToBackStack(Integer.toString(position));
            fragmentTransaction.commit();

            mDrwDrawerList.setItemChecked(position, true);
            mDrwDrawerLayout.closeDrawer(mDrwDrawerList);
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
                                Intent disconectIntent = new Intent(AccueilActivity.this, ConnexionActivity.class);
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
                Intent settingsIntent = new Intent(AccueilActivity.this, NoticeActivity.class);
                startActivity(settingsIntent);
                overridePendingTransition(R.anim.slide_up, R.anim.stay); //Animation transition slide down
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
