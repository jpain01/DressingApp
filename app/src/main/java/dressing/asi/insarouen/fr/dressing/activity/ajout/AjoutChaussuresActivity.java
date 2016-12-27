package dressing.asi.insarouen.fr.dressing.activity.ajout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import dressing.asi.insarouen.fr.dressing.R;

/**
 * Created by julie on 23/12/16.
 */

public class AjoutChaussuresActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.ajout_chaussures_activity);


        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.dressingToolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.ajoutChaussures);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//            overridePendingTransition(R.anim.stay, R.anim.slide_down); //Animation transition slide down
        }
        return super.onOptionsItemSelected(item);
    }
}
