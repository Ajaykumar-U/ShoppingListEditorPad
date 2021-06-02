package com.kotlinandroid.shoppinglist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = ShoppingItemEntity.class, version = 1)
public abstract class ShopingItemDatabase extends RoomDatabase {
    public abstract ShopingItemDao shopingItemDao();
}
