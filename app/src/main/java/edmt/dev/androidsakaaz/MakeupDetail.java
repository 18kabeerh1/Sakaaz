package edmt.dev.androidsakaaz;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sakaaz.sakaaz.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import edmt.dev.androidsakaaz.Database.Database;
import edmt.dev.androidsakaaz.model.Makeup;
import edmt.dev.androidsakaaz.model.Order;

public class MakeupDetail extends AppCompatActivity {

    TextView makeup_name, makeup_price, makeup_description;
    ImageView makeup_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String makeupId="";

    FirebaseDatabase database;
    DatabaseReference Makeups;

    Makeup currentMakeup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_makeup_detail );

        //Firebase
        database=FirebaseDatabase.getInstance();
        Makeups=database.getReference("Makeups");

        //Init View
        numberButton=(ElegantNumberButton) findViewById(R.id.number_button );
        btnCart=(FloatingActionButton)findViewById( R.id.btnCart );

        btnCart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart( new Order(
                        makeupId,
                        currentMakeup.getName(),
                        numberButton.getNumber(),
                        currentMakeup.getPrice(),
                        currentMakeup.getDiscount()
                ) );

                Toast.makeText( MakeupDetail.this, "Added to Cart", Toast.LENGTH_SHORT ).show();
            }
        } );

        makeup_description=(TextView)findViewById( R.id.makeup_description );
        makeup_name=(TextView)findViewById( R.id.makeup_name);
        makeup_price=(TextView)findViewById( R.id.makeup_price );
        makeup_image=(ImageView)findViewById( R.id.img_makeup );

        collapsingToolbarLayout =(CollapsingToolbarLayout) findViewById( R.id.collasping );
        collapsingToolbarLayout.setExpandedTitleTextAppearance( R.style.ExpandedAppbar );
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar );


        //Get Makeup Id from Intent
        if (getIntent() !=null)
            makeupId=getIntent().getStringExtra( "MakeupId");
        if (!makeupId.isEmpty())
        {
            getDetailMakeup(makeupId);
        }
    }

    private void getDetailMakeup(String makeupId) {
        Makeups.child(makeupId).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentMakeup = dataSnapshot.getValue(Makeup.class);

                //Set Image
                Picasso.with( getBaseContext()).load(currentMakeup.getImage())
                        .into( makeup_image );

                collapsingToolbarLayout.setTitle(currentMakeup.getName());

                makeup_price.setText(currentMakeup.getPrice());

                makeup_name.setText(currentMakeup.getName());

                makeup_description.setText(currentMakeup.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }
}
