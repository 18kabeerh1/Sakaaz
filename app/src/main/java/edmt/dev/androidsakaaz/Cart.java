package edmt.dev.androidsakaaz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sakaaz.sakaaz.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import edmt.dev.androidsakaaz.Database.Database;
import edmt.dev.androidsakaaz.ViewHolder.CartAdapter;
import edmt.dev.androidsakaaz.model.Order;
import info.hoang8f.widget.FButton;

import static java.lang.Integer.*;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference request;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );

        //Firebase
        database = FirebaseDatabase.getInstance();
        request = database.getReference("Requests");

        //Init
        recyclerView= (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize (true);
        layoutManager= new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );

        txtTotalPrice = (TextView)findViewById( R.id.total);
        btnPlace = (FButton)findViewById( R.id.btnPlaceOrder);

        loadListMakeup();

    }

    private void loadListMakeup() {

        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        recyclerView.setAdapter(adapter);

        int total = 0;
        for(Order order:cart){
            total += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        }
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
    }
    }
