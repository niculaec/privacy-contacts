package com.appfactory.privacycontacts.contact;

import java.util.ArrayList;
import com.appfactory.privacycontacts.utills.Logger;

public class ContactsManager {
    private final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    private final ContactRepository contactRepository = new ContactRepository();

    public ContactsManager() {
        Contact contact1 = Contact.Builder.createContact("asdasd","077777777","asd@yahoo.com","786a87sd6a");
        addContact(contact1);
    }

    public void addContact(Contact aContact) {
        contactsList.add(aContact);
        contactRepository.saveContact(aContact);
        Logger.log("The contact " + aContact + " was successfully added to the contacts list.");
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
        Logger.log("UpdateContact \n New details: " + newContact + "\n Old details: " + aContact);
        return newContact;
    }

    public void removeContact(Contact aContact) {
        contactsList.remove(aContact);
        contactRepository.removeContact(aContact);
        Logger.log(this + " removeContact\n The " + aContact + " \n Was successfully removed from the contacts list.");
    }

    //Please add method getContact.
    public Contact getContact(String id){
        for (Contact contact :contactsList) {
            if (contact.getId().equals(id))
                return contact;
        }
        return null;
    }

    public ArrayList<Contact> getAllContacts() {
        if(contactsList.isEmpty()){
            ArrayList<Contact> repoContacts = contactRepository.getAllContacts();
            contactsList.addAll(repoContacts);
        }
        return contactsList;
    }
}
