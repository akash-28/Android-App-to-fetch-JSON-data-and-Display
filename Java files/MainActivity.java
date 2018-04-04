package com.recyclerview;

import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.OnItemClickListener {

    public static final String EXTRA_URL = "picture_url";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_CATEGORY = "category";



    private static final String URL_DATA = "http://www.appetit.tk/1dbaccess/connect_local.php?id=0" ;

    public RecyclerView recyclerView;
    public Adapter adapter;

    private List<ListItemModel> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listItems = new ArrayList<>();

    /*    getRecyclerViewData();
    }

    private void getRecyclerViewData(){*/

        final ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = new JSONArray(response);
                            for(int i=0; i<array.length();i++){
                                JSONObject o = array.getJSONObject(i);

                                ListItemModel item = new ListItemModel(
                                        o.getString("name"),
                                        o.getString("description"),
                                        o.getString("picture_url"),
                                        o.getString("price"),
                                        o.getString("category")

                                );
                                listItems.add(item);
                            }
                            adapter = new Adapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(MainActivity.this);


                            progressDialog.dismiss();

                            ImageView img = (ImageView) findViewById(R.id.default_placeholder);
                            img.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"error has occured",Toast.LENGTH_SHORT).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    @Override
    public void onItemClick(int position) {

        Intent detailIntent = new Intent(this, DetailActivity.class);
        ListItemModel clickedItem = listItems.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_NAME,clickedItem.getHead());
        detailIntent.putExtra(EXTRA_DESCRIPTION,clickedItem.getDesc());
        detailIntent.putExtra(EXTRA_PRICE, clickedItem.getPrice());


        startActivity(detailIntent);

    }
}
