package com.kotlinandroid.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText etUpdateName,etUpdateWeight;
    Button btnDelete,btnUpdate;
    CheckBox checkBoxFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final ShoppingItemEntity shoppingItemEntities = (ShoppingItemEntity) getIntent().getSerializableExtra("task");

        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdateWeight = findViewById(R.id.etUpdateWeight);
        checkBoxFinished = findViewById(R.id.cbUpdate);

        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        showDataToUpdate(shoppingItemEntities);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData(shoppingItemEntities);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(shoppingItemEntities);
            }
        });
    }

    private void showDataToUpdate(ShoppingItemEntity shoppingItemEntities) {
        etUpdateName.setText(shoppingItemEntities.getItemName());
        etUpdateWeight.setText(shoppingItemEntities.getItemWeight());
        checkBoxFinished.setChecked(shoppingItemEntities.isCheckItem());
    }

    private void updateData(ShoppingItemEntity shoppingItemEntities) {
        final String str1= etUpdateName.getText().toString();
        final String str2 = etUpdateWeight.getText().toString();

        if(str1.isEmpty()){
            etUpdateName.setError("required");
            etUpdateName.requestFocus();
            return;
        }
        if (str2.isEmpty()){
            etUpdateWeight.setError("required");
            etUpdateWeight.requestFocus();
            return;
        }

        class UpdateClass extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                shoppingItemEntities.setItemName(str1);
                shoppingItemEntities.setItemWeight(str2);
                shoppingItemEntities.setCheckItem(checkBoxFinished.isChecked());
                ClientDatabase.getClientDatabase(getApplicationContext()).getShopingItemDatabase()
                        .shopingItemDao()
                        .updateData(shoppingItemEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                UpdateActivity.this.finish();
            }
        }
        UpdateClass updateClass = new UpdateClass();
        updateClass.execute();
    }

    private void DeleteData(ShoppingItemEntity shoppingItemEntities) {
        class DeleteClass extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                ClientDatabase.getClientDatabase(getApplicationContext())
                        .getShopingItemDatabase()
                        .shopingItemDao()
                        .deleteData(shoppingItemEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);
                UpdateActivity.this.finish();
            }
        }
        DeleteClass deleteClass = new DeleteClass();
        deleteClass.execute();
    }
}