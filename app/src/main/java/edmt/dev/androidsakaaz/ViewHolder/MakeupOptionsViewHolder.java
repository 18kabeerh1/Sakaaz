package edmt.dev.androidsakaaz.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakaaz.sakaaz.R;

import edmt.dev.androidsakaaz.Interface.ItemClickListener;

/**
 * Created by 18kabeerh1 on 30/11/2017.
 */

public class MakeupOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView makeup_name;
    public ImageView makeup_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MakeupOptionsViewHolder(View itemView) {
        super(itemView);

        makeup_name = (TextView) itemView.findViewById(R.id.makeupoption_name);
        makeup_image = (ImageView) itemView.findViewById(R.id.makeupoption_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
