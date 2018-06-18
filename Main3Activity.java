package com.example.krishna.merged;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    Database db;
    Intent intro;
    String name,add,phone,rent,type,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db=new Database(this);
        TextView tx1=(TextView)findViewById(R.id.name);
        TextView tx2=(TextView)findViewById(R.id.add);
        TextView tx3=(TextView)findViewById(R.id.phone);
        TextView tx4=(TextView)findViewById(R.id.rent);
        TextView tx5=(TextView)findViewById(R.id.time);
        TextView tx6=(TextView)findViewById(R.id.type);
        intro=getIntent();
        String t=intro.getStringExtra("key");
        int k=Integer.parseInt(t);

        Cursor isgetloc=db.getAllData();
        if (isgetloc.getCount() == 0) {
            // show message
            Toast.makeText(Main3Activity.this, "Error,Nothing found", Toast.LENGTH_LONG).show();
        }
        for (int i=0;i<=k;i++) {
            isgetloc.moveToNext();
            name = isgetloc.getString(0);
            add = isgetloc.getString(1);
            phone=isgetloc.getString(2);
            rent=isgetloc.getString(3);
            time=isgetloc.getString(4);
            type=isgetloc.getString(5);}
            tx1.setText("Name :  "+name);
        tx2.setText("Address :  "+add);
        tx3.setText("Phone Number :  "+phone);
        tx4.setText("Rent :   "+rent);
        tx5.setText("Time :   "+time);
        tx6.setText("Type :   "+type);

    }
}
