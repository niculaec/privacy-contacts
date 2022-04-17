package com.appfactory.privacycontacts.db;

import com.appfactory.privacycontacts.contact.Contact;
import org.w3c.dom.Entity;

public class Converter {

        public static Contact entityToContact(ContactEntity contactEntity) {
            return Contact.Builder.createContact(contactEntity.id, contactEntity.name, contactEntity.phoneNumber, contactEntity.emailAddress, contactEntity.userPicture);
        }

        public static ContactEntity contactToEntity(Contact contact) {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.id = contact.getId();
            contactEntity.name = contact.getName();
            contactEntity.phoneNumber = contact.getPhoneNumber();
            contactEntity.emailAddress = contact.getEmailAddress();
            contactEntity.userPicture = contact.getUserPicture();

            return contactEntity;
        }
}

