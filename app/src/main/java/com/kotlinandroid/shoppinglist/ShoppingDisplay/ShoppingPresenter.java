package com.kotlinandroid.shoppinglist.ShoppingDisplay;

import android.content.Context;
import android.os.AsyncTask;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;
import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItemDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShoppingPresenter implements ShoppingContractor.Presenter {

    ShoppingContractor.View view;

    @Override
    public void setView(ShoppingContractor.View view) {
        this.view = view;
    }

    @Override
    public void getShoppingItemList() {
        class DisplayingData extends AsyncTask<Void,Void, List<ShoppingItem>> {

            @Override
            protected List<ShoppingItem> doInBackground(Void... voids) {
                List<ShoppingItem> dataList = ShoppingItemDatabase.getDatabaseInstance((Context) view)
                        .shopingItemDao().getData();
                return dataList;
            }

            @Override
            protected void onPostExecute(List<ShoppingItem> shoppingItem) {
                super.onPostExecute(shoppingItem);
                view.displayData((ArrayList<ShoppingItem>) shoppingItem);
            }
        }
        DisplayingData displayingData = new DisplayingData();
        displayingData.execute();
    }
}
