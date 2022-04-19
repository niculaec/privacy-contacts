package com.appfactory.privacycontacts.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.appfactory.privacycontacts.contact.Contact;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contactentity")
    List<ContactEntity> getAll();

    @Insert
    void insertAll(ContactEntity... contactEntities);

    @Update
    void update(ContactEntity contactEntities);

    @Delete
    void delete(ContactEntity contactEntity);
}
