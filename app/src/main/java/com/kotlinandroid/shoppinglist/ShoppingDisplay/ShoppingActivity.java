package com.kotlinandroid.shoppinglist.ShoppingDisplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kotlinandroid.shoppinglist.AddShoppingItem.AddActivity;
import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;
import com.kotlinandroid.shoppinglist.R;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity implements ShoppingContractor.View {

    RecyclerView recyclerView;
    FloatingActionButton fabButton;
    ShoppingContractor.Presenter presenter = new ShoppingPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.setView(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShoppingActivity.this));
        presenter.getShoppingItemList();
        fabButton = findViewById(R.id.fabButton);
        fabButton.setOnClickListener(v -> {
            Intent intent = new Intent(ShoppingActivity.this,AddActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void displayData(ArrayList<ShoppingItem> itemList) {
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(itemList, ShoppingActivity.this);
        recyclerView.setAdapter(recyclerAdapter);
    }

}