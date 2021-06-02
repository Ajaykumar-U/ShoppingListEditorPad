package com.kotlinandroid.shoppinglist;

import android.content.Context;

import androidx.room.Room;

public class ClientDatabase  {
    static ClientDatabase clientDatabase;
    ShopingItemDatabase shopingItemDatabase;
    Context context;

    ClientDatabase(Context ctx){
        this.context = ctx;
        shopingItemDatabase = Room.databaseBuilder(context,ShopingItemDatabase.class,"Shopping Table").build();
    }

    public static synchronized ClientDatabase getClientDatabase(Context ctx) {
        if(clientDatabase == null){
            clientDatabase = new ClientDatabase(ctx);
        }
        return clientDatabase;
    }

    ShopingItemDatabase getShopingItemDatabase(){
        return shopingItemDatabase;
    }
}
