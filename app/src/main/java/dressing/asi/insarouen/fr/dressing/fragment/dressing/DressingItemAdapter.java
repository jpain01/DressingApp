package dressing.asi.insarouen.fr.dressing.fragment.dressing;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.fragment.contenu.ContenuFragment;

/**
 * Created by julie on 15/12/16.
 */

public class DressingItemAdapter extends RecyclerView.Adapter<DressingItemAdapter.DressingViewHolder> {
    public static final String USER_ID = "id";
    public static final String TYPE_CONTENU = "type";
    private List<DressingItem> mDressingList;
    private Context mContext;

    public DressingItemAdapter(List<DressingItem> dressingItemList, Context context) {
        this.mDressingList = dressingItemList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDressingList.size();
    }

    @Override
    public void onBindViewHolder(DressingViewHolder dressingViewHolder, int position) {
        final DressingItem item = mDressingList.get(position);
        dressingViewHolder.mTitleView.setText(item.getTitle());
        dressingViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContenuFragment contenuFragment = null;
                contenuFragment = ContenuFragment.newInstance(item.getIdUtilisateur(),item.getTitle());
                // récupération du manager
                FragmentManager fragmentManager = ((Activity) mContext).getFragmentManager();
                // Commençons une transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Remplace notre vue par le fragment qu'on veut
                fragmentTransaction.replace(R.id.frame, contenuFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        dressingViewHolder.mImageView.setImageResource(mContext.getResources().getIdentifier(item.getIcon(), "drawable", mContext.getPackageName()));
    }

    @Override
    public DressingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.dressing_card_item, viewGroup, false);

        ImageView imgDressing = (ImageView)itemView.findViewById(R.id.imgDressingItem);
        imgDressing.setScaleType(ImageView.ScaleType.FIT_XY);

        return new DressingViewHolder(itemView);
    }

    public static class DressingViewHolder extends RecyclerView.ViewHolder {
        protected TextView mTitleView;
        protected ImageView mImageView;
        protected CardView mCardView;

        public DressingViewHolder(View view) {
            super(view);
            mTitleView = (TextView) view.findViewById(R.id.title);
            mImageView = (ImageView) view.findViewById(R.id.imgDressingItem);
            mCardView = (CardView) view.findViewById(R.id.itemDressing);
        }
    }
}
