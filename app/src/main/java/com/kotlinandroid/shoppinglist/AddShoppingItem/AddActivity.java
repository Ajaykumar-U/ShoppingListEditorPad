package com.kotlinandroid.shoppinglist.AddShoppingItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kotlinandroid.shoppinglist.R;
import com.kotlinandroid.shoppinglist.ShoppingDisplay.ShoppingActivity;

public class AddActivity extends AppCompatActivity implements AddItemContractor.View {

    EditText etName,etWeight;
    Button btnInsert;

    AddItemContractor.Presenter presenter = new AddItemPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etInsertName);
        etWeight = findViewById(R.id.etInsertWeight);

        presenter.setView(this);


        btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    private void insertData() {
        String itemName = etName.getText().toString();
        String itemWeight = etWeight.getText().toString();

        if(itemName.isEmpty()){
            etName.setError("Required");
            etName.requestFocus();
            return;
        }

        if(itemWeight.isEmpty()){
            etWeight.setError("Required");
            etWeight.requestFocus();
            return;
        }

        presenter.getItemInput(itemName,itemWeight);
        Log.d("TAG1",itemName+" "+itemWeight);
    }

    @Override
    public void closeAddActivity() {
        Intent intent = new Intent(getApplicationContext(), ShoppingActivity.class);
        startActivity(intent);
        AddActivity.this.finish();
    }

}