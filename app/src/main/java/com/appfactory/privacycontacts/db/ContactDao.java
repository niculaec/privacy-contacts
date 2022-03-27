package com.appfactory.privacycontacts.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.appfactory.privacycontacts.contact.Contact;

import java.util.ArrayList;
@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    ArrayList<Contact> getAll();

    @Query("SELECT * FROM contact WHERE name IN (:name)")
    ArrayList<Contact> loadAllByName (int [] name);

    @Insert
    void  insertAll (Contact...contacts);

    @Delete
    void delete (Contact contact);
}
