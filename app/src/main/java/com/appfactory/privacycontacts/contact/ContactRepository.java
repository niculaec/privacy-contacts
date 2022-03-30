package com.appfactory.privacycontacts.contact;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ContactRepository {
    ArrayList<Contact> contactList = new ArrayList<Contact>();

    public ContactRepository() {
        Contact contactRepo1 = Contact.Builder.createContact("bbbb","000000","asd@yahoo.com","5555");
        contactList.add(contactRepo1);
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
