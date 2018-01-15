package edmt.dev.androidsakaaz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sakaaz.sakaaz.R;
import com.squareup.picasso.Picasso;

import edmt.dev.androidsakaaz.Interface.ItemClickListener;
import edmt.dev.androidsakaaz.ViewHolder.MakeupViewHolder;
import edmt.dev.androidsakaaz.model.Category;
import edmt.dev.androidsakaaz.model.Makeup;

public class MakeupList extends AppCompatActivity {

    public static
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference MakeupList;

    String categoryId="";
    FirebaseRecyclerAdapter<Makeup,MakeupViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeup_list);

        //Firebase
        database = FirebaseDatabase.getInstance();
        MakeupList = database.getReference("Makeups");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_makeupoptions);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get Intent here
        if(getIntent()!=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty()&& categoryId !=null)
        {
            loadListMakeup(categoryId);
        }
    }

    private void loadListMakeup(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Makeup, MakeupViewHolder>(Makeup.class,
                R.layout.makeup_items,
                MakeupViewHolder.class,
                MakeupList.orderByChild("MenuId").equalTo(categoryId)  // like: Select * from Makeup where MenuId =
                ) {
            @Override
            protected void populateViewHolder(MakeupViewHolder viewHolder, Makeup model, int position) {
                viewHolder.txtMakeupName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageView);

                final Makeup local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new activity
                        Intent MakeupDetail = new Intent( MakeupList.this,MakeupDetail.class );
                        MakeupDetail.putExtra( "MakeupId",adapter.getRef( position ).getKey() ); //Send Makeup Id to new activity
                        startActivity(MakeupDetail);
                    }
                });
            }
        };


        recyclerView.setAdapter(adapter);

    }
}
