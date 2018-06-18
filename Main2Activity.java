package com.example.krishna.merged;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.krishna.merged.Database;
import com.example.krishna.merged.Main3Activity;
import com.example.krishna.merged.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Database db;
    private ListView lvProduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    String Name,address,phone,type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        lvProduct = (ListView)findViewById(R.id.listview_product);

        mProductList = new ArrayList<>();
        db=new Database(this);
        Cursor isgetloc = db.getAllData();
        if (isgetloc.getCount() == 0) {
            // show message
            Toast.makeText(Main2Activity.this, "Error,Nothing found", Toast.LENGTH_LONG).show();
        }
        while (isgetloc.moveToNext()) {
            int i=1;
             Name = isgetloc.getString(0);
             address = isgetloc.getString(1);
             phone=isgetloc.getString(2);
             type=isgetloc.getString(3);
            mProductList.add(new Product(i, Name, address, type+"      "+phone));
        i++;
        }

        adapter = new ProductListAdapter(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                clicked(id,position);
            }
        });

    }

    private void clicked(long id, int position) {
        Intent into=new Intent(this,Main3Activity.class);
        into.putExtra("key",""+id);
        startActivity(into);
    }
}


