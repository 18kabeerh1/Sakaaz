package edmt.dev.androidsakaaz.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.sakaaz.sakaaz.R;

import edmt.dev.androidsakaaz.Interface.ItemClickListener;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by 18kabeerh1 on 16/11/2017.
 */

public class MakeupViewHolder extends ViewHolder implements View.OnClickListener {

    public TextView txtMakeupName;
    public ImageView imageView;

    private ItemClickListener itemClickListener;

    public MakeupViewHolder(View itemView) {
        super(itemView);

        txtMakeupName = (TextView) itemView.findViewById(R.id.makeupoption_name);
        imageView = (ImageView) itemView.findViewById(R.id.makeupoption_image);

        itemView.setOnClickListener(this);
    }


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view){
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
