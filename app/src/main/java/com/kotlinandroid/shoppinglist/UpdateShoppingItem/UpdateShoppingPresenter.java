package com.kotlinandroid.shoppinglist.UpdateShoppingItem;

import android.content.Context;
import android.os.AsyncTask;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;
import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItemDatabase;

public class UpdateShoppingPresenter implements UpdateShoppingContractor.Presenter {

    UpdateShoppingContractor.View view;

    @Override
    public void setView(UpdateShoppingContractor.View view) {
        this.view = view;
    }

    @Override
    public void getShoppingItem(String itemName, String itemWeight, ShoppingItem shoppingItemEntities,int checkBox) {
        class UpdateClass extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                shoppingItemEntities.setItemName(itemName);
                shoppingItemEntities.setItemWeight(itemWeight);
                if(checkBox == 1){
                    shoppingItemEntities.setCheckItem(true);
                }else{
                    shoppingItemEntities.setCheckItem(false);
                }
                ShoppingItemDatabase.getDatabaseInstance((Context) view)
                        .shopingItemDao()
                        .updateData(shoppingItemEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.closeActivity();
            }
        }
        UpdateClass updateClass = new UpdateClass();
        updateClass.execute();
    }

    @Override
    public void deleteItem(ShoppingItem shoppingItemEntities) {
        class DeleteClass extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                ShoppingItemDatabase.getDatabaseInstance((Context) view)
                        .shopingItemDao()
                        .deleteData(shoppingItemEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                view.closeActivity();
            }
        }
        DeleteClass deleteClass = new DeleteClass();
        deleteClass.execute();
    }
}
