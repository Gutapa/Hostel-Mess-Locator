package com.example.krishna.merged;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Messo.db";
    public static final String TABLE_NAME = "messo_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Address";
    public static final String COL_3 = "Phone";
    public static final String COL_4 = "Rent";
    public static final String COL_5 = "In_Time";
    public static final String COL_6= "Type";
    public static final String COL_7= "Latitude";
    public static final String COL_8= "Longitude";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (Name Text,Address Text,Phone Text,Rent Text,In_Time Text,Type Text,Latitude Text,Longitude Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name,String Address,String Phone,String Rent,String In_Time,String Type,String Latitude,String Longitude) {
        if(Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Rent.isEmpty() || In_Time.isEmpty() || Type.isEmpty() || Latitude.isEmpty() || Longitude.isEmpty() || "".equals(Name) || "".equals(Address) || "".equals(Phone) || "".equals(Rent) || "".equals(In_Time) || "".equals(Type) || "".equals(Latitude) || "".equals(Longitude)) {
            return false;
        }else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, Name);
            contentValues.put(COL_2, Address);
            contentValues.put(COL_3, Phone);
            contentValues.put(COL_4, Rent);
            contentValues.put(COL_5, In_Time);
            contentValues.put(COL_6, Type);
            contentValues.put(COL_7, Latitude);
            contentValues.put(COL_8, Longitude);


            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getLatLon() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Latitude,Longitude,Type from "+TABLE_NAME,null);
        return res;
    }


    public boolean updateData(String Name,String Address,String Phone,String Rent,String In_Time,String Type,String Latitude,String Longitude) {
        if(Name.isEmpty() || Address.isEmpty() || Phone.isEmpty() || Rent.isEmpty() || In_Time.isEmpty() || Type.isEmpty() || Latitude.isEmpty() || Longitude.isEmpty() || "".equals(Name) || "".equals(Address) || "".equals(Phone) || "".equals(Rent) || "".equals(In_Time) || "".equals(Type) || "".equals(Latitude) || "".equals(Longitude)){
         //   Toast.makeText(Database.this,"Update Unsuccessful",Toast.LENGTH_LONG).show();
            return false;
        }else {


            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_1, Name);
            contentValues.put(COL_2, Address);
            contentValues.put(COL_3, Phone);
            contentValues.put(COL_4, Rent);
            contentValues.put(COL_5, In_Time);
            contentValues.put(COL_6, Type);
            contentValues.put(COL_7, Latitude);
            contentValues.put(COL_8, Longitude);
            db.update(TABLE_NAME, contentValues, "Name = ?", new String[]{Name});
            return true;
        }
    }

    public Integer deleteData (String Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "Name = ?",new String[] {Name});
    }
}