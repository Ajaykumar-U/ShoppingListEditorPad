package com.kotlinandroid.shoppinglist.UpdateShoppingItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItemDatabase;
import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;
import com.kotlinandroid.shoppinglist.R;
import com.kotlinandroid.shoppinglist.ShoppingDisplay.ShoppingActivity;

public class UpdateActivity extends AppCompatActivity implements UpdateShoppingContractor.View {

    EditText etUpdateName,etUpdateWeight;
    Button btnDelete,btnUpdate;
    CheckBox checkBoxFinished;
    UpdateShoppingContractor.Presenter presenter = new UpdateShoppingPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        presenter.setView(this);

        final ShoppingItem shoppingItemEntities = (ShoppingItem) getIntent().getSerializableExtra("task");

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

    private void showDataToUpdate(ShoppingItem shoppingItemEntities) {
        etUpdateName.setText(shoppingItemEntities.getItemName());
        etUpdateWeight.setText(shoppingItemEntities.getItemWeight());
        checkBoxFinished.setChecked(shoppingItemEntities.isCheckItem());
    }

    private void updateData(ShoppingItem shoppingItemEntities) {
        final String itemName= etUpdateName.getText().toString();
        final String itemWeight = etUpdateWeight.getText().toString();
        int checkBox=0;
        if(checkBoxFinished.isChecked()){
            checkBox=1;
        }

        if(itemName.isEmpty()){
            etUpdateName.setError("required");
            etUpdateName.requestFocus();
            return;
        }
        if (itemWeight.isEmpty()){
            etUpdateWeight.setError("required");
            etUpdateWeight.requestFocus();
            return;
        }
        presenter.getShoppingItem(itemName,itemWeight,shoppingItemEntities,checkBox);
    }

    private void DeleteData(ShoppingItem shoppingItemEntities) {
        presenter.deleteItem(shoppingItemEntities);
    }

    @Override
    public void closeActivity() {
        Intent intent = new Intent(UpdateActivity.this, ShoppingActivity.class);
        startActivity(intent);
        UpdateActivity.this.finish();
    }
}