package com.example.Sorela;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText InputNumber, InputName, InputDesc, Inputprice, Inputquantity;
    Button Create_Btn, Retrieve_Btn, RetrieveBy_Btn, Update_Btn, Delete_Btn;
    comshopDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputNumber = (EditText) findViewById(R.id.InputIDNumber);
        InputName = (EditText) findViewById(R.id.InputProdName);
        InputDesc = (EditText) findViewById(R.id.InputProdDesc);
        Inputprice = (EditText) findViewById(R.id.InputPrice);
        Inputquantity = (EditText) findViewById(R.id.InputQuantity);

        Create_Btn = findViewById(R.id.CreateBtn);
        Retrieve_Btn = findViewById(R.id.RetrieveBtn);
        RetrieveBy_Btn = findViewById(R.id.RetrieveByBtn);
        Update_Btn = findViewById(R.id.UpdateBtn);
        Delete_Btn = findViewById(R.id.DeleteBtn);

        DB = new comshopDB(this);

        Create_Btn.setOnClickListener(new View.OnClickListener(){
                                          @Override
                                          public void onClick(View view){
                                              String id = InputNumber.getText().toString();
                                              String name = InputName.getText().toString();
                                              String desc = InputDesc.getText().toString();
                                              String price = Inputprice.getText().toString();
                                              String quantity = Inputquantity.getText().toString();

                                              if (id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !price.isEmpty() && !quantity.isEmpty()){
                                                  InputNumber.setError("Product ID Number is required");
                                                  InputNumber.requestFocus();
                                                  return;
                                              }
                                              if (name.isEmpty() && !id.isEmpty() && !desc.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                                                  InputName.setError("Product Name is required");
                                                  InputName.requestFocus();
                                                  return;
                                              }
                                              if (desc.isEmpty() && !id.isEmpty() && !name.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                                                  InputDesc.setError("Product Description is required");
                                                  InputDesc.requestFocus();
                                                  return;
                                              }
                                              if (price.isEmpty() && !id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !quantity.isEmpty()) {
                                                  Inputprice.setError("Product price required");
                                                  Inputprice.requestFocus();
                                                  return;
                                              }
                                              if (quantity.isEmpty() && !id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !price.isEmpty()) {
                                                  Inputquantity.setError("Product quantity required");
                                                  Inputquantity.requestFocus();
                                                  return;
                                              }
                                              if (id.isEmpty() || name.isEmpty() || desc.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                                                  Toast.makeText(MainActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                                              }
                                              else
                                              {
                                                  Boolean checkinsertdata = DB.insertuserdata(id, name, desc, price, quantity);
                                                  if (checkinsertdata == true) {
                                                      Toast.makeText(MainActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                                      InputNumber.getText().clear();
                                                      InputName.getText().clear();
                                                      InputDesc.getText().clear();
                                                      Inputprice.getText().clear();
                                                      Inputquantity.getText().clear();
                                                  }
                                                  else
                                                      Toast.makeText(MainActivity.this, "Product already exists", Toast.LENGTH_SHORT).show();
                                              }

                                          }
                                      }
        );
        Update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = InputNumber.getText().toString();
                String name = InputName.getText().toString();
                String desc = InputDesc.getText().toString();
                String price = Inputprice.getText().toString();
                String quantity = Inputquantity.getText().toString();

                if (id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                    InputNumber.setError("Product ID Number is required");
                    InputNumber.requestFocus();
                    return;
                }
                if (name.isEmpty() && !id.isEmpty() && !desc.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                    InputName.setError("Product name required");
                    InputName.requestFocus();
                    return;
                }
                if (desc.isEmpty() && !id.isEmpty() && !name.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                    InputDesc.setError("Product description required");
                    InputDesc.requestFocus();
                    return;
                }
                if (price.isEmpty() && !id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !quantity.isEmpty()) {
                    Inputprice.setError("Product price required");
                    Inputprice.requestFocus();
                    return;
                }
                if (quantity.isEmpty() && !id.isEmpty() && !name.isEmpty() && !desc.isEmpty() && !price.isEmpty()) {
                    Inputquantity.setError("Product quantity required");
                    Inputquantity.requestFocus();
                    return;
                }
                if (id.isEmpty() || name.isEmpty() || desc.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Some fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkupdatedata = DB.updateuserdata(id, name, desc, price, quantity);
                    if (checkupdatedata == true) {
                        Toast.makeText(MainActivity.this, "Product Updated", Toast.LENGTH_SHORT).show();
                        InputNumber.getText().clear();
                        InputName.getText().clear();
                        InputDesc.getText().clear();
                        Inputprice.getText().clear();
                        Inputquantity.getText().clear();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Invalid product ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Retrieve_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Product/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Description: " + res.getString(2) + "\n");
                    buffer.append("Price: " + res.getString(3) + "\n");
                    buffer.append("Quantity: " + res.getString(4) + "\n\n");
                }

                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Products");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        RetrieveBy_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = InputNumber.getText().toString();
                SQLiteDatabase simpledb = getApplicationContext().openOrCreateDatabase("comshop.db",Context.MODE_PRIVATE,null);
                Cursor c = simpledb.rawQuery("Select * from ComshopInventory where id ='"+n+"'",null);
                if (c.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "No Product/s Exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (c.moveToNext()) {
                    buffer.append("ID: " + c.getString(0) + "\n");
                    buffer.append("Name: " + c.getString(1) + "\n");
                    buffer.append("Description: " + c.getString(2) + "\n");
                    buffer.append("Price: " + c.getString(3) + "\n");
                    buffer.append("Quantity: " + c.getString(4) + "\n\n");

                }
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Products");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        Delete_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = InputNumber.getText().toString();
                Boolean checkudeletedata = DB.deletedata(id);

                if (id.isEmpty()) {
                    InputNumber.setError("Product ID is required!");
                    InputNumber.requestFocus();
                    return;
                }

                if (checkudeletedata == true) {
                    Toast.makeText(MainActivity.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                    InputNumber.getText().clear();
                }
                else
                    Toast.makeText(MainActivity.this, "Invalid product ID", Toast.LENGTH_SHORT).show();
            }
        });
    }
}