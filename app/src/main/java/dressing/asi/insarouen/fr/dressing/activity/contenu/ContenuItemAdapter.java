package dressing.asi.insarouen.fr.dressing.activity.contenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.view.ActionMode;

import com.bignerdranch.android.multiselector.ModalMultiSelectorCallback;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;

import java.util.List;

import dressing.asi.insarouen.fr.dressing.R;
import dressing.asi.insarouen.fr.dressing.activity.consultation.ConsultationActivity;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.ChaussuresDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.SacDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.AutreDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.HautDAO;
import dressing.asi.insarouen.fr.dressing.data.dao.contenu.vetement.PantalonDAO;
import dressing.asi.insarouen.fr.dressing.data.model.Contenu;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Chaussures;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.Sac;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Haut;
import dressing.asi.insarouen.fr.dressing.data.model.contenu.vetement.Pantalon;

/**
 * Created by julie on 15/12/16.
 */

public class ContenuItemAdapter extends RecyclerView.Adapter<ContenuItemAdapter.DressingViewHolder> {
    public static final int REQUEST_CODE_CONSULT_CONTENU = 1;
    public static final String POSITION_LIST = "position_list";
    public static final String CONTENU_ID = "id";
    public static final String CONTENU_TYPE = "type";
    public static final String DRESSING_ID = "dressing_id";
    private RecyclerView mRecyclerView;
    private List<Contenu> mContenuList;
    private Context mContext;
    private MultiSelector mMultiSelector = new MultiSelector();
    private android.support.v7.view.ActionMode.Callback mDeleteMode = new ModalMultiSelectorCallback(mMultiSelector) {

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            super.onCreateActionMode(actionMode, menu);
            ((AppCompatActivity)mContext).getMenuInflater().inflate(R.menu.menu_delete_contenu, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            if (menuItem.getItemId() == R.id.delete) {
                actionMode.finish();

                for (int i = mContenuList.size(); i >= 0; i--) {
                    if (mMultiSelector.isSelected(i, 0)) { // (1)
                        // remove item from list
                        Contenu contenu = mContenuList.get(i);
                        if (contenu instanceof Sac) {
                            SacDAO sac = new SacDAO(mContext);
                            sac.open();
                            sac.delete(contenu.getIdObjet());
                            sac.close();
                        } else if (contenu instanceof Chaussures) {
                            ChaussuresDAO chaussures = new ChaussuresDAO(mContext);
                            chaussures.open();
                            chaussures.delete(contenu.getIdObjet());
                            chaussures.close();
                        } else if (contenu instanceof Haut) {
                            HautDAO haut = new HautDAO(mContext);
                            haut.open();
                            haut.delete(contenu.getIdObjet());
                            haut.close();
                        } else if (contenu instanceof Pantalon) {
                            PantalonDAO pantalon = new PantalonDAO(mContext);
                            pantalon.open();
                            pantalon.delete(contenu.getIdObjet());
                            pantalon.close();
                        } else {
                            AutreDAO autre = new AutreDAO(mContext);
                            autre.open();
                            autre.delete(contenu.getIdObjet());
                            autre.close();
                        }
                        mContenuList.remove(i);
                        mRecyclerView.getAdapter().notifyItemRemoved(i);
                        mRecyclerView.getAdapter().notifyItemRangeChanged(i, getItemCount());
                    }
                }

                mMultiSelector.clearSelections();
                return true;
            }
            return false;
        }
    };


    public ContenuItemAdapter(List<Contenu> contenuItemList, Context context, RecyclerView view) {
        this.mContenuList = contenuItemList;
        this.mContext = context;
        this.mRecyclerView = view;
    }

    @Override
    public int getItemCount() {
        return mContenuList.size();
    }

    @Override
    public void onBindViewHolder(final DressingViewHolder dressingViewHolder, final int position) {
        final Contenu contenu = mContenuList.get(position);
        // On click sur la card view
        dressingViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mMultiSelector.tapSelection(dressingViewHolder)) {
                    Intent intent = new Intent(mContext, ConsultationActivity.class);
                    intent.putExtra(CONTENU_ID, contenu.getIdObjet());
                    intent.putExtra(DRESSING_ID, contenu.getIdDressing());
                    intent.putExtra(POSITION_LIST, position);
                    if (contenu instanceof Sac) {
                        intent.putExtra(CONTENU_TYPE, "sac");
                    } else if (contenu instanceof Chaussures) {
                        intent.putExtra(CONTENU_TYPE, "chaussures");
                    } else if (contenu instanceof Haut) {
                        intent.putExtra(CONTENU_TYPE, "haut");
                    } else if (contenu instanceof Pantalon) {
                        intent.putExtra(CONTENU_TYPE, "pantalon");
                    } else {
                        intent.putExtra(CONTENU_TYPE, "autre");
                    }
                    ((Activity)mContext).startActivityForResult(intent, REQUEST_CODE_CONSULT_CONTENU);
                }
            }
        });
        if (BitmapFactory.decodeFile(contenu.getImage())==null) {
            dressingViewHolder.mImageView.setImageResource(mContext.getResources().getIdentifier(contenu.getImage(), "drawable", mContext.getPackageName()));
        } else {
            dressingViewHolder.mImageView.setImageBitmap(BitmapFactory.decodeFile(contenu.getImage()));
        }

        // on long click sur la card view
        dressingViewHolder.mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!mMultiSelector.isSelectable()) {
                    ((AppCompatActivity) mContext).startSupportActionMode(mDeleteMode);
                    mMultiSelector.setSelectable(true);
                    mMultiSelector.setSelected(dressingViewHolder, true);
                    return true;
                }
                return false;
            }
        });

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

    public class DressingViewHolder extends SwappingHolder {
        protected ImageView mImageView;
        protected CardView mCardView;

        public DressingViewHolder(View view) {
            super(view, mMultiSelector);
            mImageView = (ImageView) view.findViewById(R.id.imgContenuItem);
            mCardView = (CardView) view.findViewById(R.id.itemContenu);
            mCardView.setLongClickable(true);
        }
    }
}
