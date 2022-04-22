package com.appfactory.privacycontacts.contact;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.appfactory.privacycontacts.db.AppDatabase;
import com.appfactory.privacycontacts.db.ContactDao;
import com.appfactory.privacycontacts.db.ContactEntity;
import com.appfactory.privacycontacts.db.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContactRepository {
    AppDatabase db = Room.databaseBuilder(APPLICATION, AppDatabase.class, "contacts-database").build();
    ContactDao contactDao = db.contactDao();
    private static Application APPLICATION;

    public static void setApplication(Application application) {
        APPLICATION = application;
    }

    void getAllContacts(ContactsCallBack contactsCallBack) {
        AppDatabase.DATABASE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                String threadName = Thread.currentThread().getName();

                List<ContactEntity> contactEntityList = contactDao.getAll();
                ArrayList<Contact> contactList = new ArrayList<Contact>();
                for (ContactEntity aContactEntity : contactEntityList) {
                    Contact contact = Converter.entityToContact(aContactEntity);
                    if (contact != null){
                        contactList.add(contact);
                    }
                }
                contactsCallBack.onCallBack(contactList);
                Logger.getLogger("Successfully load all contacts from database");
            }
        });
    }

    public void saveContact(Contact aContact) {
        AppDatabase.DATABASE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                ContactEntity contactEntity;
                contactEntity = Converter.contactToEntity(aContact);
                if (contactEntity != null) {
                    contactDao.insertAll(contactEntity);
                    Logger.getLogger(aContact + "\n" + "The contact is saved in database." + "\n");
                }
            }
        });
    }

    public void updateContact(Contact aContact) {
        AppDatabase.DATABASE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                ContactEntity contactEntity;
                contactEntity = Converter.contactToEntity(aContact);
                if (contactEntity != null) {
                    contactDao.update(contactEntity);
                    Logger.getLogger(aContact + "\n" + "Contact has been updated in Repository." + "\n");
                }
            }
        });
    }

    public void removeContact(Contact aContact) {
        AppDatabase.DATABASE_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                ContactEntity contactEntity;
                contactEntity = Converter.contactToEntity(aContact);
                if (contactEntity != null) {
                    contactDao.delete(contactEntity);
                    Logger.getLogger(aContact + "\n" + "Contact has been removed from Repository." + "\n");
                }
            }
        });
    }

    public interface ContactsCallBack {
        void onCallBack(ArrayList<Contact> contactsList);
    }
}
