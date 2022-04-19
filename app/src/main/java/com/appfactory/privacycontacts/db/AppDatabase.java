package com.appfactory.privacycontacts.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ContactEntity.class}, version = 1, exportSchema = false)
//entities is the name of the class followed by .class. Version represent the version of database.

public abstract class AppDatabase extends RoomDatabase {// AppDatabase is a subclass of RoomDatabase

    public abstract ContactDao contactDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService DATABASE_EXECUTOR =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) { //only one instance of INSTANCE.
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "contacts-database").build();
                    // initialize the instance using a builder object, the first argument is the application context, second is the database class name to define the database, third is the name of the database.
                }
            }
        }
        return INSTANCE; // return the instance
    }

    public static void destroyInstance() {// this method will be used to de-reference the database object within the singleton.

        INSTANCE = null;
    }
}
