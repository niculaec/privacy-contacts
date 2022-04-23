package com.appfactory.privacycontacts.db;

import com.appfactory.privacycontacts.contact.Contact;
import com.appfactory.privacycontacts.pin.PinManager;


public class Converter {

    public static Contact entityToContact(ContactEntity contactEntity) {
        return Contact.Builder.createContact(contactEntity);
    }

    public static ContactEntity contactToEntity(Contact contact) {
        DataEncryption dataEncryption = DataEncryption.getInstance();
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.id = contact.getId();

        try {
            contactEntity.name = dataEncryption.encryptText(contact.getName());
            contactEntity.phoneNumber = dataEncryption.encryptText(contact.getPhoneNumber());
            contactEntity.emailAddress = dataEncryption.encryptText(contact.getEmailAddress());
            contactEntity.userPicture = dataEncryption.encryptText(contact.getUserPicture());
            return contactEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

