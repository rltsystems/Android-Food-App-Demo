package com.daclink.drew.sp22.cst438_project01_starter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Recipe.class}, version = 2)
@TypeConverters(TypeConvertor.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "AppDatabase.db";
    public static final String USER_TABLE = "users";
    public static final String RECIPE_TABLE = "recipes";
    private static volatile AppDatabase instance;
    private static final Object LOCK = new Object();

    public abstract UserDao getUserDao();

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME).build();
                }
            }
        }
        return instance;
    }
}
