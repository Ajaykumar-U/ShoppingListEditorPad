package com.kotlinandroid.shoppinglist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShopingItemDao {

    @Query("SELECT * FROM ShoppingItemEntity")
    List<ShoppingItemEntity> getData();

    @Insert
    void insertData(ShoppingItemEntity shoppingItemEntity);

    @Update
    void updateData(ShoppingItemEntity shoppingItemEntity);

    @Delete
    void deleteData(ShoppingItemEntity shoppingItemEntity);
}
