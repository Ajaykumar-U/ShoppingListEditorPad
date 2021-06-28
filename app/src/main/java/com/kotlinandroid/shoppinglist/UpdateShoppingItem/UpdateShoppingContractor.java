package com.kotlinandroid.shoppinglist.UpdateShoppingItem;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;

public interface UpdateShoppingContractor {
    interface Presenter{
        void setView(UpdateShoppingContractor.View view);
        void getShoppingItem(String itemName, String itemWeight, ShoppingItem shoppingItemEntities,int checkBox);
        void deleteItem(ShoppingItem shoppingItemEntities);
    }
    interface View{
        void closeActivity();
    }
}
