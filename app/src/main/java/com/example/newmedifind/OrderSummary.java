package com.example.newmedifind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.Random;

public class OrderSummary extends AppCompatActivity {

    TextView Orderslogan, Pricedetails, Priceamount, Discountamount, Totalamountprice,Statement;
    Button Placeorder;
    RecyclerView recyclerView;
    OrderRecyclerAdapter orderrecyclerAdapter;
    FirebaseDatabase rootnode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        intia();

        String username = LoginActivity.getUsername();

        reference = FirebaseDatabase.getInstance().getReference("OrderSummary").child(username);


        FirebaseRecyclerOptions<OrderAdder> options =
                new FirebaseRecyclerOptions.Builder<OrderAdder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("OrderSummary").child(username), OrderAdder.class)
                        .build();


        orderrecyclerAdapter = new OrderRecyclerAdapter(options);

        recyclerView.setAdapter(orderrecyclerAdapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                float sum = 0;
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    String med_price = String.valueOf(dataSnapshot1.child("med_price").getValue());
                    sum = sum + Float.parseFloat(med_price);
                    //Log.i("med_price",med_price);
                }
                BigDecimal price = round(sum, 2);
                String finalPrice = String.valueOf(price);
                Priceamount.setText(finalPrice);
                Random random = new Random();
                int discountPercentage = random.nextInt(5) + 1;

                float discountPrice = discount(price.floatValue(), discountPercentage);

                BigDecimal roundoffdiscountprice=round(discountPrice,2);
                 float totalprice1=(price.floatValue() - roundoffdiscountprice.floatValue());
                BigDecimal totalprice2 =round(totalprice1,2);
                String totalprice =String.valueOf(totalprice2);

                Discountamount.setText("-"+(roundoffdiscountprice));
                Totalamountprice.setText(totalprice);
                Statement.setText("You will save"+" Rs."+roundoffdiscountprice +" on this order");
                //Log.i("value", finalPrice);
                //Log.i("percentage",String.valueOf(discountPercentage));
                //Log.i("finalPrice",String.valueOf(price.floatValue() - discountPrice));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        orderrecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orderrecyclerAdapter.stopListening();
    }

    private void intia() {

        Orderslogan = findViewById(R.id.orderSlogan);
        Pricedetails = findViewById(R.id.pricedetails);
        Priceamount = findViewById(R.id.priceamount);
        Discountamount = findViewById(R.id.discountamount);
        Totalamountprice = findViewById(R.id.totalamountprice);
        Placeorder = findViewById(R.id.placeorderbutton);
        Statement = findViewById(R.id.statement);
        recyclerView = findViewById(R.id.orderrecyclerView);

    }

    private BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;

    }

    private float discount(float floatValue, int discountPercentage) {
        float discountPrice = (floatValue * discountPercentage) / 100;
        return discountPrice;
    }
}