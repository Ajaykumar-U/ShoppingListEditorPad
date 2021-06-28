package com.kotlinandroid.shoppinglist.DatabaseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ShoppingItem implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "itemWeight")
    private String itemWeight;

    @ColumnInfo(name = "checkItem")
    private boolean checkItem;

    public boolean isCheckItem() {
        return checkItem;
    }

    public void setCheckItem(boolean checkItem) {
        this.checkItem = checkItem;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }
}
