package com.kotlinandroid.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertShoppingItem(v);
            }
        });
        displayData();
    }

    private void displayData() {
        class DisplayingData extends AsyncTask<Void,Void,List<ShoppingItemEntity>>{

            @Override
            protected List<ShoppingItemEntity> doInBackground(Void... voids) {
                List<ShoppingItemEntity> dataList = ClientDatabase.getClientDatabase(getApplicationContext())
                        .getShopingItemDatabase().shopingItemDao().getData();
                return dataList;
            }

            @Override
            protected void onPostExecute(List<ShoppingItemEntity> shoppingItemEntities) {
                super.onPostExecute(shoppingItemEntities);
                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(shoppingItemEntities,MainActivity.this);
                recyclerView.setAdapter(recyclerAdapter);
            }
        }
        DisplayingData displayingData = new DisplayingData();
        displayingData.execute();
    }

    private void insertShoppingItem(View v) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
        this.finish();
    }

}