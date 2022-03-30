package com.appfactory.privacycontacts.contact;

import android.net.Uri;

import java.util.UUID;

public class Contact {
    private final String name;
    private final String phoneNumber;
    private final String emailAddress;
    private final String userPicture;
    private String id;

    private Contact(String name, String phoneNumber, String emailAddress, String userPicture, String id) {
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

    public String getId() {
        return id;
    }


    public static class Builder {


        public static Contact createContact(String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture)) {
                return null;
            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, UUID.randomUUID().toString());
        }

        private static boolean checkValidParams(String name, String phoneNumber, String emailAddress, String userPicture) {
            if (name == null &&
                    phoneNumber == null &&
                    emailAddress == null &&
                    userPicture == null) {
                return false;
            }
            return true;
        }

        public static Contact createUpdatedContact(Contact oldContact, String name, String phoneNumber, String emailAddress, String userPicture) {
            if (!checkValidParams(name, phoneNumber, emailAddress, userPicture) &&
                    oldContact.id == null) {

            }
            return new Contact(name, phoneNumber, emailAddress, userPicture, oldContact.id);
        }
    }
}
