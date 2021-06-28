package com.kotlinandroid.shoppinglist.DatabaseModel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShoppingItemDao {

    @Query("SELECT * FROM ShoppingItem")
    List<ShoppingItem> getData();

    @Insert
    void insertData(ShoppingItem shoppingItem);

    @Update
    void updateData(ShoppingItem shoppingItem);

    @Delete
    void deleteData(ShoppingItem shoppingItem);
}
