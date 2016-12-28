package dressing.asi.insarouen.fr.dressing.fragment.contenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;

/**
 * Created by julie on 15/12/16.
 */

public class ContenuItemAdapter extends RecyclerView.Adapter<ContenuItemAdapter.DressingViewHolder> {
    public static final String HEROES_ID_EXTRA = "id";
    public static final String TYPE_ACTIVITY_EXTRA = "type";
    private List<Contenu> mContenuList;
    private Context mContext;

    public ContenuItemAdapter(List<Contenu> contenuItemList, Context context) {
        this.mContenuList = contenuItemList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mContenuList.size();
    }

    @Override
    public void onBindViewHolder(DressingViewHolder dressingViewHolder, int position) {
        final Contenu contenu = mContenuList.get(position);
//        dressingViewHolder.mTitleView.setText(contenu.getCouleur().toString());
//        dressingViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ConsultActivity.class);
//                intent.putExtra(HEROES_ID_EXTRA, heroes.getId());
//                intent.putExtra(TYPE_ACTIVITY_EXTRA, "hero");
//                mContext.startActivity(intent);
//            }
//        });
        if (BitmapFactory.decodeFile(contenu.getImage())==null) {
            dressingViewHolder.mImageView.setImageResource(mContext.getResources().getIdentifier(contenu.getImage(), "drawable", mContext.getPackageName()));
        } else {
            dressingViewHolder.mImageView.setImageBitmap(BitmapFactory.decodeFile(contenu.getImage()));
        }


    }

    @Override
    public DressingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.contenu_card_item, viewGroup, false);

        ImageView imgContenu = (ImageView)itemView.findViewById(R.id.imgContenuItem);
        imgContenu.setScaleType(ImageView.ScaleType.FIT_XY);

        return new DressingViewHolder(itemView);
    }

    public static class DressingViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mImageView;
        protected CardView mCardView;

        public DressingViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.imgContenuItem);
            mCardView = (CardView) view.findViewById(R.id.itemContenu);
        }
    }
}
