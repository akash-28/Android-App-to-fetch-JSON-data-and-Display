package com.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.recyclerview.MainActivity.EXTRA_DESCRIPTION;
import static com.recyclerview.MainActivity.EXTRA_NAME;
import static com.recyclerview.MainActivity.EXTRA_PRICE;
import static com.recyclerview.MainActivity.EXTRA_URL;

/**
 * Created by AKASH on 12/28/2017.
 */

public class DetailActivity extends AppCompatActivity {

   public ImageButton order;
   public ImageView img;
   public TextView title, desc , itemPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);



        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String price = intent.getStringExtra(EXTRA_PRICE);

        img = (ImageView) findViewById(R.id.detailFoodImage);
        title = (TextView) findViewById(R.id.detailFoodName);
        desc = (TextView) findViewById(R.id.detailFoodDescription);
        itemPrice = (TextView) findViewById(R.id.itemPrice);

        Picasso.with(this).load(imageUrl).placeholder(R.drawable.fork).fit().centerInside().into(img);
        title.setText(name);
        desc.setText(description);
        itemPrice.setText("Price : "+price);


        order = (ImageButton) findViewById(R.id.buyButton);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Add to cart", Toast.LENGTH_LONG).show();

                //Toast.makeText(this ,"Adding to cart", Toast.LENGTH_LONG);
                Log.v("working................" , "maybe");
            }
        });


    }


}
