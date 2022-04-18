package com.appfactory.privacycontacts.contact;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.appfactory.privacycontacts.ContactAdapter;
import com.appfactory.privacycontacts.utills.Logger;

public class ContactsManager {
    //save a ref of Adapter type and save the contactList.
    private static final ContactsManager INSTANCE = new ContactsManager();
    private final ArrayList<Contact> contactsList = new ArrayList<Contact>();
    private final ContactRepository contactRepository = new ContactRepository();
    public static final String ID_KEY = "id";
    private WeakReference<ContactAdapter> arrayAdapterWeakReference;


    private ContactsManager() {
    }

    public static ContactsManager getInstance() {
        return INSTANCE;
    }

    public void addContact(Contact aContact) {
        contactsList.add(aContact);
        contactRepository.saveContact(aContact);
        notifyAdapter();
        Logger.log("The contact " + aContact + " was successfully added to the contacts list.");
    }

    public void notifyAdapter() {
        if (arrayAdapterWeakReference != null) {
            ContactAdapter contactAdapter = arrayAdapterWeakReference.get();
            if (contactAdapter != null) {
                contactAdapter.notifyDataSetChanged();
            }
        }
    }

    public void setArrayAdapter(ContactAdapter contactAdapter) {
        this.arrayAdapterWeakReference = new WeakReference<ContactAdapter>(contactAdapter);
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

    /**
     * Remove the selected contact from contactsList
     *
     * @param aContact remove aContact from the list
     */
    public void removeContact(Contact aContact) {
        contactsList.remove(aContact);
        contactRepository.removeContact(aContact);
        notifyAdapter();
        Logger.log(this + " removeContact\n The " + aContact + " \n Was successfully removed from the contacts list.");
    }

    /**
     * To get a contact.
     *
     * @param id compare the contact Id with id.
     * @return a contact or null
     */
    public Contact getContact(String id) {
        for (Contact contact : contactsList) {
            if (contact.getId().equals(id))
                return contact;
        }
        return null;
    }

    /**
     * Getting all contacts from contactList
     *
     * @return a list of contacts
     */
    public void getAllContacts(ContactRepository.ContactsCallBack contactsCallBack) {
        if (contactsList.isEmpty()) {
            ContactRepository.ContactsCallBack contactsCallBackInternal = new ContactRepository.ContactsCallBack() {
                @Override
                public void onCallBack(ArrayList<Contact> contactsList) {
                    ContactsManager.this.contactsList.addAll(contactsList);
                    contactsCallBack.onCallBack(contactsList);
                }
            };
            contactRepository.getAllContacts(contactsCallBackInternal);
        } else {
            contactsCallBack.onCallBack(contactsList);
        }
    }
}
