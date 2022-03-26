package com.appfactory.privacycontacts.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.appfactory.privacycontacts.contact.Contact;
import com.google.android.gms.wearable.DataItem;

@Database(entities = {Contact.class}, version = 1) //entities is the name of the class followed by .class. Version represent the version of database.
public abstract class AppDatabase  extends RoomDatabase {// AppDatabase is a subclass of RoomDatabase

        private static AppDatabase instance;

        public static AppDatabase getInstance(Context context){
            if (instance == null){
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app-database").allowMainThreadQueries().build();
                // initialize the instance using a builder object, the first argument is the application context, second is the database class name to define the database, third is the name of the database.
            }
            return instance; // return the instance
        }

        public static void destroyInstance(){// this method will be used to de-reference the database object within the singleton.
            instance = null;
        }

}
