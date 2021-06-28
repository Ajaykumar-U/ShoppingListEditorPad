package com.kotlinandroid.shoppinglist.DatabaseModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ShoppingItem.class, version = 1)
public abstract class ShoppingItemDatabase extends RoomDatabase {
    public abstract ShoppingItemDao shopingItemDao();

    static ShoppingItemDatabase shoppingItemDatabase;

    public static synchronized ShoppingItemDatabase getDatabaseInstance(Context context){
        if(shoppingItemDatabase == null){
            shoppingItemDatabase = Room.databaseBuilder(context, ShoppingItemDatabase.class,"Shopping Table").build();
        }
        return shoppingItemDatabase;
    }
}
