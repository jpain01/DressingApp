package dressing.asi.insarouen.fr.dressing.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dressing.asi.insarouen.fr.dressing.R;

/**
 * Created by julie on 14/12/16.
 */

public class DrawerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Object> mNavigationDrawers;

    public DrawerAdapter(Context context, ArrayList<Object> NavigationDrawers) {
        this.mContext = context;
        this.mNavigationDrawers = NavigationDrawers;
    }

    @Override
    public int getCount() {
        return mNavigationDrawers.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavigationDrawers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int type = getItemViewType(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.drawer_list, null);
        }


        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drwIcon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.drwTitle);

        NavigationDrawer navigationDrawer = (NavigationDrawer) mNavigationDrawers.get(position);

        imgIcon.setImageResource(navigationDrawer.getIcon());
        txtTitle.setText(navigationDrawer.getTitle());

        return convertView;
    }
}
