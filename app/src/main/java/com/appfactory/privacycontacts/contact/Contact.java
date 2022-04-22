package com.appfactory.privacycontacts.contact;

import androidx.annotation.NonNull;

import com.appfactory.privacycontacts.db.ContactEntity;
import com.appfactory.privacycontacts.db.DataEncryption;

import java.util.UUID;


public class Contact {

    private final String name;
    private final String phoneNumber;
    private final String emailAddress;
    private final String userPicture;
    @NonNull
    private final String id;


    private Contact(String name, String phoneNumber, String emailAddress, String userPicture, @NonNull String id) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userPicture = userPicture;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getUserPicture() {
        return userPicture;
    }

    @NonNull
    public String getId() {
        return id;
    }


    public static class Builder {

        public static Contact createContact(ContactEntity contactEntity) {
            DataEncryption dataEncryption = DataEncryption.getInstance();
            String id = contactEntity.id;
            try {
                String name = dataEncryption.decryptText(contactEntity.name);
                String phoneNumber = dataEncryption.decryptText(contactEntity.phoneNumber);
                String emailAddress = dataEncryption.decryptText(contactEntity.emailAddress);
                String userPicture = dataEncryption.decryptText(contactEntity.userPicture);
                if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                    return null;
                }
                return new Contact(name, phoneNumber, emailAddress, userPicture, id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static Contact createContact(String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                return null;
            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, UUID.randomUUID().toString());

        }

        private static boolean checkValidParams(String name, String phoneNumber, String emailAddress, String userPicture) {
            if (name == null || name.isEmpty() ||
                    phoneNumber == null ||
                    emailAddress == null ||
                    userPicture == null) {
                return false;
            }
            return true;
        }

        public static Contact createUpdatedContact(Contact oldContact, String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                return null;
            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, oldContact.id);
        }
    }
}
