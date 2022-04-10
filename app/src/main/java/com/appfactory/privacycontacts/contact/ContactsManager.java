package com.appfactory.privacycontacts.contact;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import com.appfactory.privacycontacts.R;
import com.appfactory.privacycontacts.utills.Logger;

public class ContactsManager {
    //save a ref de tipul adaptor and save the contactList.
    private static final ContactsManager INSTANCE = new ContactsManager();
    private final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    private final ContactRepository contactRepository = new ContactRepository();
    public static final String ID_KEY = "id";
    private ArrayAdapter<Contact> arrayAdapter;


    private ContactsManager() {
        Contact contact1 = Contact.Builder.createContact("Sergiu","077777777","asd@yahoo.com","25544103354");
        Contact contact2 = Contact.Builder.createContact("Mama","077777777","asd@yahoo.com","25544103354");
        Contact contact3 = Contact.Builder.createContact("Alex","077773333","bad@yahoo.com","255441033333");
        addContact(contact1);
        addContact(contact2);
        addContact(contact3);
    }

    public static ContactsManager getInstance(){
        return INSTANCE;
    }

    public void addContact(Contact aContact) {
        contactsList.add(aContact);
        contactRepository.saveContact(aContact);
        notifyAdapter();
        Logger.log("The contact " + aContact + " was successfully added to the contacts list.");
    }

    public void notifyAdapter(){
        if(arrayAdapter != null){
            arrayAdapter.notifyDataSetChanged();
        }
    }

    public void setArrayAdapter(ArrayAdapter arrayAdapter){
        this.arrayAdapter = arrayAdapter;
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
        notifyAdapter();// notify the adaptor for changes.
        Logger.log("UpdateContact \n New details: " + newContact + "\n Old details: " + aContact);
        return newContact;
    }

    public void removeContact(Contact aContact) {
        contactsList.remove(aContact);
        contactRepository.removeContact(aContact);
        notifyAdapter();
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
