package com.kotlinandroid.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etName,etWeight;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etInsertName);
        etWeight = findViewById(R.id.etInsertWeight);

        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        String strName = etName.getText().toString();
        String strWeight = etWeight.getText().toString();

        if(strName.isEmpty()){
            etName.setError("Required");
            etName.requestFocus();
            return;
        }

        if(strWeight.isEmpty()){
            etWeight.setError("Required");
            etWeight.requestFocus();
            return;
        }

        class InsertingData extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                ShoppingItemEntity shoppingItemEntity = new ShoppingItemEntity();
                shoppingItemEntity.setItemName(strName);
                shoppingItemEntity.setItemWeight(strWeight);
                ClientDatabase.getClientDatabase(getApplicationContext())
                        .getShopingItemDatabase().shopingItemDao().insertData(shoppingItemEntity);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                AddActivity.this.finish();
            }
        }
        InsertingData insertingData=new InsertingData();
        insertingData.execute();
    }
}