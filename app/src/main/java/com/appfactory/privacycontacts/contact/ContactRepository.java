package com.appfactory.privacycontacts.contact;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ContactRepository {
    ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ContactRepository() {
    }

    ArrayList<Contact> getAllContacts() {
        Logger.getLogger("Successfully load all contacts from repository");
        return contactList;
    }

    public void saveContact(Contact aContact) {
        Logger.getLogger(aContact + "\n" + "The contact is saved in Repository." + "\n");
    }

    public void updateContact(Contact aContact) {
        Logger.getLogger(aContact + "\n" + "Contact has been updated in Repository." + "\n");
    }

    public void removeContact(Contact aContact) {
        Logger.getLogger(aContact + "\n" + "Contact has been removed from Repository." + "\n");
    }
}
