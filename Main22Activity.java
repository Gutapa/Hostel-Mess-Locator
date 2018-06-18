package com.example.krishna.merged;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main22Activity extends AppCompatActivity {

    Database myDb;
    EditText editName,editAddress,editPhone ,editRent,editInTime,editType,editLat,editLon;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;

    Button btnviewUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

            myDb = new Database(this);

            editName = (EditText)findViewById(R.id.editText_name);
            editAddress = (EditText)findViewById(R.id.editText_address);
            editPhone = (EditText)findViewById(R.id.editText_phone);
            editRent = (EditText)findViewById(R.id.editText_rent);
            editInTime = (EditText)findViewById(R.id.editText_intime);
            editType = (EditText)findViewById(R.id.editText_type);
            editLat=(EditText)findViewById(R.id.editText_Lat);
            editLon=(EditText)findViewById(R.id.editText_Lon);
            btnAddData = (Button)findViewById(R.id.button_add);
            btnviewAll = (Button)findViewById(R.id.button_viewAll);
            btnviewUpdate= (Button)findViewById(R.id.button_update);
            btnDelete= (Button)findViewById(R.id.button_delete);
            AddData();
            viewAll();
            UpdateData();
            DeleteData();
        }
        public void DeleteData() {
            btnDelete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer deletedRows = myDb.deleteData(editName.getText().toString());
                            if(deletedRows > 0)
                                Toast.makeText(Main22Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Main22Activity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }
        public void UpdateData() {
            btnviewUpdate.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isUpdate = myDb.updateData(editName.getText().toString(),
                                    editAddress.getText().toString(),
                                    editPhone.getText().toString(),editRent.getText().toString(),editInTime.getText().toString(),editType.getText().toString(),editLat.getText().toString(),editLon.getText().toString());
                            if(isUpdate == true){
                                Toast.makeText(Main22Activity.this,"Data Update",Toast.LENGTH_LONG).show();}
                            else{
                                Toast.makeText(Main22Activity.this,"Data not Updated",Toast.LENGTH_LONG).show();}
                        }
                    }
            );
        }
        public  void AddData() {
            btnAddData.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = myDb.insertData(editName.getText().toString(),
                                    editAddress.getText().toString(),
                                    editPhone.getText().toString(),editRent.getText().toString(),editInTime.getText().toString(),editType.getText().toString(),editLat.getText().toString(),editLon.getText().toString());

                            if(isInserted == true)
                                Toast.makeText(Main22Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Main22Activity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }

        public void viewAll() {
            btnviewAll.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Cursor res = myDb.getAllData();
                            if(res.getCount() == 0) {
                                // show message
                                showMessage("Error","Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Name :"+ res.getString(0)+"\n");
                                buffer.append("Address :"+ res.getString(1)+"\n");
                                buffer.append("Phone :"+ res.getString(2)+"\n");
                                buffer.append("Rent :"+ res.getString(3)+"\n");
                                buffer.append("In_Time :"+ res.getString(4)+"\n");
                                buffer.append("Type :"+ res.getString(5)+"\n\n");
                            }

                            // Show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
            );
        }

        public void showMessage(String title,String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }



    }

