package com.appfactory.privacycontacts.contact;

import java.util.ArrayList;
import java.util.logging.Logger;

public class ContactsManager {
    private final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    private final ContactRepository contactRepository = new ContactRepository();

    public void addContact(Contact aContact) {
        contactsList.add(aContact);
        contactRepository.saveContact(aContact);
        Logger.getLogger("The contact " + aContact + " was successfully added to the contacts list.");
    }

    public Contact updateContact(Contact aContact, String name, String phoneNUmber, String emailAddress, String userPicture) {
        if (aContact.getName().equals(name) &&
                aContact.getPhoneNumber().equals(phoneNUmber) &&
                aContact.getEmailAddress().equals(emailAddress) &&
                aContact.getUserPicture().equals(userPicture)) {
            return null;
        }
        Contact newContact = Contact.Builder.createUpdatedContact(aContact, name, phoneNUmber, emailAddress, userPicture);
        contactsList.add(contactsList.indexOf(aContact), newContact);
        contactsList.remove(aContact);
        contactRepository.updateContact(newContact);
        Logger.getLogger("UpdateContact \n New details: " + newContact + "\n Old details: " + aContact);
        return newContact;
    }

    public void removeContact(Contact aContact) {
        contactsList.remove(aContact);
        contactRepository.removeContact(aContact);
        Logger.getLogger(this + " removeContact\n The " + aContact + " \n Was successfully removed from the contacts list.");
    }

    public ArrayList<Contact> getAllContacts() {
        if(contactsList.isEmpty()){
            ArrayList<Contact> repoContacts = contactRepository.getAllContacts();
            contactsList.addAll(repoContacts);
        }
        return contactsList;
    }
}
