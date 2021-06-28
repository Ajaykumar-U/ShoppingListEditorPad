package com.kotlinandroid.shoppinglist.ShoppingDisplay;

import com.kotlinandroid.shoppinglist.DatabaseModel.ShoppingItem;

import java.util.ArrayList;

public interface ShoppingContractor {
    interface Presenter{
        void setView(ShoppingContractor.View view);
        void getShoppingItemList();
    }
    interface View{
        void displayData(ArrayList<ShoppingItem> itemList);
    }
}
