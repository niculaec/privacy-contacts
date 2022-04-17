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
    AppDatabase db = Room.databaseBuilder(APPLICATION ,AppDatabase.class, "contacts-database").build();
    ContactDao contactDao = db.contactDao();
    private static Application APPLICATION;
    ContactEntity contactEntity;

    public static void setApplication(Application application) {
        APPLICATION = application;
    }
    public ContactRepository() {
    }

    ArrayList<Contact> getAllContacts() {
        List<ContactEntity> contactEntityList = contactDao.getAll();
        ArrayList<Contact> contactList = new ArrayList<Contact>();
        for (ContactEntity aContactEntity: contactEntityList) {
            contactList.add(Converter.entityToContact(aContactEntity));
        }
        Logger.getLogger("Successfully load all contacts from database");
        return contactList;
    }

    public void saveContact(Contact aContact) {

        contactEntity = Converter.contactToEntity(aContact);
        contactDao.insertAll(contactEntity);
        Logger.getLogger(aContact + "\n" + "The contact is saved in database." + "\n");
    }

    public void updateContact(Contact aContact) {
        contactEntity = Converter.contactToEntity(aContact);
        contactDao.update(contactEntity);
        Logger.getLogger(aContact + "\n" + "Contact has been updated in Repository." + "\n");
    }

    public void removeContact(Contact aContact) {
        contactEntity = Converter.contactToEntity(aContact);
        contactDao.delete(contactEntity);
        Logger.getLogger(aContact + "\n" + "Contact has been removed from Repository." + "\n");
    }
}
