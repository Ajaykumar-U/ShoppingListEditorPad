package com.kotlinandroid.shoppinglist.AddShoppingItem;

public interface AddItemContractor {
    interface Presenter{
        void setView(AddItemContractor.View view);
        void getItemInput(String itemName,String itemWeight);
    }
    interface View{
        void closeAddActivity();
    }
}
