package com.appfactory.privacycontacts.DB;

import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;
import android.os.Build;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.db.AppDatabase;
import com.appfactory.privacycontacts.db.ContactDao;
import com.appfactory.privacycontacts.db.ContactEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class Db_test {
    private ContactDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        userDao = db.contactDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        ContactEntity contactEntity = Contact.Builder.createContact(0);
        contactEntity.name("Barack Obama");
        userDao.insertAll(contactEntity);
        List<Contact> contactList = userDao.getAll(contactEntity.name("Barack Obama"));
        assertThat(contactList.get(0), equals(contactEntity.name));
    }
}
