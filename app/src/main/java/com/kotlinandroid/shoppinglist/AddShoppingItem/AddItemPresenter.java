package com.kotlinandroid.shoppinglist.AddShoppingItem;

import android.content.Context;
import android.os.AsyncTask;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;
import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItemDatabase;

public class AddItemPresenter implements AddItemContractor.Presenter {

    AddItemContractor.View view;

    @Override
    public void setView(AddItemContractor.View view) {
        this.view = view;
    }

    @Override
    public void getItemInput(String itemName, String itemWeight) {
        class InsertingData extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                ShoppingItem shoppingItem = new ShoppingItem();
                shoppingItem.setItemName(itemName);
                shoppingItem.setItemWeight(itemWeight);
                ShoppingItemDatabase.getDatabaseInstance((Context) view).shopingItemDao().insertData(shoppingItem);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.closeAddActivity();
            }
        }
        InsertingData insertingData=new InsertingData();
        insertingData.execute();
    }
}
